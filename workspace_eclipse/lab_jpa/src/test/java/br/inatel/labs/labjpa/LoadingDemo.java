package br.inatel.labs.labjpa;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.inatel.labs.labjpa.entity.NotaCompra;
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
	
	@Test
	public void demoLazyLoading() {
		try {
			
			NotaCompra nota = service.buscarPeloIdNotaCompra(1L);
			
			int tamanho = nota.getListaNotaCompraItem().size();
			
			System.out.println("O tamanho da lista da nota compra é" + tamanho);			
		}catch(Exception e) {
			// é necessário que o relacionamento/canal com o banco esteja aberto, ou seja, que a instância está em estado MANAGED
			// em qualquer outro estado, não há contexto de persistência e, portanto, o proxy não conseguirá trazer os dados do banco
			System.out.println("O Carregamento foi Lazy, por isso lançou a exception");
			e.printStackTrace();
		}
	}
	
	@Test
	public void demoPlanejamentoConsulta() {
		try {
			
			// O método chamado na service retorna uma instância que tem contexto de persistência
			NotaCompra nota = service.buscarPeloIdNotaCompraComListaItem(1L);
			
			int tamanho = nota.getListaNotaCompraItem().size();
			
			System.out.println("O tamanho da lista da nota compra é" + tamanho);
			System.out.println("Houve carregamento Lazy corretamente");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
