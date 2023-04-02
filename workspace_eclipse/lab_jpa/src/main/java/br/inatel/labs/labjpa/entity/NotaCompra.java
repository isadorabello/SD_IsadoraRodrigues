package br.inatel.labs.labjpa.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class NotaCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Para o Hibernate entender que se trata de um relacionamento OneToMany BI-direcional, um lado é escolhido automaticamente 
	// como dominante (Tabela oposta ao 1 -> NotaCompraItem), porém, ainda é necessário informar ao lado dominado, onde é feito
	// o mapeado do relacionamento
	@OneToMany (mappedBy = "notaCompra")
	private List<NotaCompraItem> listaNotaCompraItem;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@NotNull
	@Past
	private LocalDate dataEmissao;
	
	// PROGRAMAÇÃO FUNCIONAL
	public BigDecimal getCalculoTotalNota() {
		BigDecimal total = this.listaNotaCompraItem.stream()
				//.map(NotaCompraItem::getCalculoTotalItem)
			      .map( i -> i.getCalculoTotalItem())
			      .reduce( BigDecimal.ZERO, BigDecimal::add );
		return total;
	}
	
	// Construtores...
	
	public NotaCompra() {
		super();
	}

	public NotaCompra(LocalDate de, Fornecedor f) {
		super();
		this.dataEmissao = de;
		this.fornecedor = f;
	}
	
	// Acessores e Modificadores...

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<NotaCompraItem> getListaNotaCompraItem() {
		return listaNotaCompraItem;
	}

	public void setListaNotaCompraItem(List<NotaCompraItem> listaNotaCompraItem) {
		this.listaNotaCompraItem = listaNotaCompraItem;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaCompra other = (NotaCompra) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "NotaCompra [id=" + id + ", dataEmissao=" + dataEmissao + "]";
	}
	
}
