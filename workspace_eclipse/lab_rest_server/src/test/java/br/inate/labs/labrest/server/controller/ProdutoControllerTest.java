package br.inate.labs.labrest.server.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inate.labs.labrest.server.model.Produto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutoControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void deveListarProdutos() {
		webTestClient.get().uri("/produto").exchange().expectStatus().isOk().expectBody().returnResult();
	}
	
	@Test
	void givenValidId_whenGetProdutoById_thanResponseWithValidProduto() {
		Long validId = 1L;
		
		Produto produtoValido = webTestClient
				.get().uri("/produto/"+validId)
				.exchange().expectStatus().isOk().expectBody(Produto.class).returnResult().getResponseBody();
		
		assertNotNull(produtoValido);
		assertEquals(produtoValido.getId(), validId);
	}
	
	@Test
	void givenInvalidId_whenGetProdutoById_thanResponseWithInvalidProduto() {
		Long invalidId = 99L;
		
		webTestClient
				.get().uri("/produto/"+invalidId)
				.exchange().expectStatus().isNotFound();
	}

}
