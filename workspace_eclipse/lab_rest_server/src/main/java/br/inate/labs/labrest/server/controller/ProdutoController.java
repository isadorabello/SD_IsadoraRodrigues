package br.inate.labs.labrest.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inate.labs.labrest.server.model.Produto;
import br.inate.labs.labrest.server.service.ProdutoService;

@RestController
@RequestMapping("/produto") // toda URI que chegar com esse padrão hello, será despachado para essa classe
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public List<Produto> findAll() {
		return service.findAll();
	}
}
