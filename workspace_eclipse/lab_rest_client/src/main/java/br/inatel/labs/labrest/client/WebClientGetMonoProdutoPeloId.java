package br.inatel.labs.labrest.client;

import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest.client.model.dto.ProdutoDTO;
import reactor.core.publisher.Mono;

public class WebClientGetMonoProdutoPeloId {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mono<ProdutoDTO> monoProduto = WebClient.create("http://localhost:8080").get()
				.uri("/produto/1").retrieve().bodyToMono(ProdutoDTO.class);
		
		monoProduto.subscribe();
		
		// é bloqueado a continuação do código até que o registro seja carregado/chegue
		ProdutoDTO produto = monoProduto.block();
		
		System.out.println("Produto encontrado: ");
		System.out.println(produto);
	}

}
