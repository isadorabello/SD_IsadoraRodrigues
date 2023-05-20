package br.inate.labs.labrest.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import br.inate.labs.labrest.server.model.Produto;
import jakarta.annotation.PostConstruct;

@Service
public class ProdutoService {
	//Simulando um banco de dados. Em um projeto real, isso seria substituido pelo banco de dados.
	private List<Produto> produtos = new ArrayList<>();
	
	@PostConstruct
	private void setup() {
		Produto p1 = new Produto(1L,"Biscoito Maisena", new BigDecimal(3.50));
		Produto p2 = new Produto(2L,"Banana Prata", new BigDecimal(14.00));
		Produto p3 = new Produto(3L,"Achocolatado Dois Padres", new BigDecimal(9.5));
		produtos.add(p1);
		produtos.add(p2);
		produtos.add(p3);
	}
	
	public List<Produto> findAll(){
		return this.produtos;
	}
	
	//O que é um optional? Recurso do JAVA, um objeto que embrulha outro objeto, faz com que o dev 
	//teste o conteúdo do objeto antes de acessar o conteúdo propriamente dito, evitando o NullPointerException
	public Optional<Produto> findById(Long id){
		return produtos.stream().filter(p -> p.getId() == id).findFirst();
	}

	public Produto create(Produto p) {
		long id = new Random().nextLong();
		p.setId(id);
		produtos.add(p);
		return p;
	}
	
	public void update(Produto p) {
		produtos.remove(p);
		produtos.add(p);
	}
	
	public void remove(Produto p) {
		produtos.remove(p);
	}
}
