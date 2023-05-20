package br.inatel.labs.labrest.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.labs.labrest.client.model.dto.ProdutoDTO;
import reactor.core.publisher.Flux;

public class WebClientGetFluxProduto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<ProdutoDTO> listaProduto = new ArrayList<ProdutoDTO>();
		
		Flux<ProdutoDTO> fluxProduto = WebClient.create("http://localhost:8080").get()
				.uri("/produto").retrieve().bodyToFlux(ProdutoDTO.class);
		
		fluxProduto.subscribe(p -> listaProduto.add(p));
		
		fluxProduto.blockLast(); // é bloqueado a continuação do código até que o último "bloquinho" seja carregado/chegue
		
		System.out.println("Lista de produtos: ");
		System.out.println(listaProduto);
	}

}
