package br.inatel.labs.labjpa.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Para o Hibernate entender que se trata de um relacionamento ManyToMany BI-direcional, um lado é escolhido como dominante (Fornecedor),
	// assim, no lado dominado, informa-se onde foi mapeado o relacionamento
	@ManyToMany(mappedBy = "listaProduto")
	private List<Fornecedor> listaFornecedor;
	
	@NotNull
	@Size(min = 2, max = 200)
	private String descricao;
	
	// Construtores...
	
	public Produto() {
		super();
	}

	public Produto(String descricao) {
		super();
		this.descricao = descricao;
	}

	// Acessores e Modificadores...
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + "]";
	}
	
	
	
}
