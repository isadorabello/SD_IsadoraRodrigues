package br.inatel.labs.labjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.dto.TotalCompradoPorFornecedorDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class RelatorioService {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<TotalCompradoPorFornecedorDTO> pesquisarTotalCompradoPorFornecedor(){
		String query = "select new br.inatel.labs.labjpa.dto.TotalCompradoPorFornecedorDTO "
				+ " (f.razaoSocial, sum(i.quantidade * i.valorCompraProduto)) "
				+ " from NotaCompraItem i "
				+ " join i.notaCompra n "
				+ " join n.fornecedor f "
				+ " group by f.razaoSocial ";
		
		return em.createQuery(query, TotalCompradoPorFornecedorDTO.class).getResultList();
	}
	
}
