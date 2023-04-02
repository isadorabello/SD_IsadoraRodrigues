package br.inatel.labs.labjpa.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class NotaCompraItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Lado dominante -> toda tabela oposta ao 1, é o lado dominante, visto que na hr de ser criada a tabela no SQL, é nela que ficará o FK
	@ManyToOne
	private NotaCompra notaCompra;
	
	@ManyToOne
	private Produto produto;
	
	@NotNull
	@Positive
	private BigDecimal valorCompraProduto;
	
	@NotNull
	@Positive
	private Integer quantidade = 1;
	
	public BigDecimal getCalculoTotalItem() {
		return valorCompraProduto.multiply(BigDecimal.valueOf(quantidade));
	}
	
	// Construtores...
	
		public NotaCompraItem() {
			super();
		}

		public NotaCompraItem(NotaCompra nc, Produto p, BigDecimal vcp, Integer q) {
			super();
			this.notaCompra = nc;
			this.produto = p;
			this.valorCompraProduto = vcp;
			this.quantidade = q;
		}
		
		// Acessores e Modificadores...

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NotaCompra getNotaCompra() {
		return notaCompra;
	}

	public void setNotaCompra(NotaCompra notaCompra) {
		this.notaCompra = notaCompra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValorCompraProduto() {
		return valorCompraProduto;
	}

	public void setValorCompraProduto(BigDecimal valorCompraProduto) {
		this.valorCompraProduto = valorCompraProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		NotaCompraItem other = (NotaCompraItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "NotaCompraItem [id=" + id + ", valorCompraProduto=" + valorCompraProduto + ", quantidade=" + quantidade
				+ "]";
	}
	
	
}
