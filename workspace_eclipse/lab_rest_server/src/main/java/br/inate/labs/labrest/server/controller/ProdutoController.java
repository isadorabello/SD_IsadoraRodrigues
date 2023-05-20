package br.inate.labs.labrest.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.inate.labs.labrest.server.model.Produto;
import br.inate.labs.labrest.server.service.ProdutoService;

@RestController
@RequestMapping("/produto") // toda URI que chegar com esse padrão hello, será despachado para essa classe
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public List<Produto> getProduto() {
		return service.findAll();
	}
	
	/*
	 @GetMapping("/{id}") // formato de template
	public Produto getProdutoById(@PathVariable("id") Long produtoId) {
		Optional<Produto> opProduto = service.findById(produtoId);
		
		if(opProduto.isPresent()) {
			return opProduto.get();
		}
		
		String msg = String.format("Nenhum produto encontrado com id [%s]", produtoId);
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
	}
	 */
	
	@GetMapping("/{id}") // formato de template
	public Produto getProdutoById(@PathVariable("id") Long produtoId) {
		return service.findById(produtoId).orElseThrow(() -> {
			String msg = String.format("Nenhum produto encontrado com id [%s]", produtoId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
		});
	}
	
	@GetMapping("/pesquisa") // formato de template
	public Produto getProdutoByDescricao(@RequestParam("descricao") String descricao) {
		return service.findProdutoByDescricao(descricao);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Produto postProduto(@RequestBody Produto p) {
		Produto produtoCriado = service.create(p);
		return produtoCriado;
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void putProduto(@RequestBody Produto p) {
		service.update(p);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteProduto(@PathVariable("id") Long produtoId) {
		Produto produtoEncontrado = getProdutoById(produtoId);
		service.remove(produtoEncontrado);
	}
}
