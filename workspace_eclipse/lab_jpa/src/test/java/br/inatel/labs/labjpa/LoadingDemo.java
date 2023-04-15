package br.inatel.labs.labjpa;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.inatel.labs.labjpa.entity.NotaCompraItem;
import br.inatel.labs.labjpa.service.NotaCompraService;

@SpringBootTest
public class LoadingDemo {
	
	@Autowired
	private NotaCompraService service;
	
	@Test
	public void demoEagerLoading() {
		try {
			NotaCompraItem item = service.buscarPeloIdNotaCompraItem(1L);
			LocalDate dataEmissao = item.getNotaCompra().getDataEmissao();
			
			String descricao = item.getProduto().getDescricao();
			String razaoSocial = item.getNotaCompra().getFornecedor().getRazaoSocial();
			
			System.out.println("A descrição do produto é " + descricao);
			System.out.println("A razão social do fornecedor é " + razaoSocial);
			System.out.println("A data de emissão do produto foi " + dataEmissao);
			System.out.println("Houve carregamento Eager");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
