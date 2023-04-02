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
public class Fornecedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Lado dominante
	@ManyToMany
	private List<Produto> listaProduto;
	
	@NotNull
	@Size(min = 2, max = 200)
	private String razaoSocial;
	
	// Construtores...
	
	public Fornecedor() {
		super();
	}

	public Fornecedor(String razaoSocial) {
		super();
		this.razaoSocial = razaoSocial;
	}

	// Acessores e Modificadores...

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", razaoSocial=" + razaoSocial + "]";
	}
	
	
	
}
