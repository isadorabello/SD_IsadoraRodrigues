package br.inatel.labs.labjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.entity.NotaCompra;
import br.inatel.labs.labjpa.entity.NotaCompraItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class NotaCompraService {
	
	@PersistenceContext
	private EntityManager em;
	
	// NOTA COMPRA
	
	public NotaCompra salvar(NotaCompra nc) {
		return em.merge(nc);
	}
	
	public NotaCompra buscarPeloIdNotaCompra(Long id) {
		return em.find(NotaCompra.class, id);
	}
	
	public List<NotaCompra> listarNotaCompra() {
		return em.createQuery("select nc from NotaCompra nc", NotaCompra.class).getResultList();
	}
	
	public void remover(NotaCompra nc) {
		em.remove(em.merge(nc));
	}
	
	// NOTA COMPRA ITEM
	
	public NotaCompraItem salvar(NotaCompraItem nci) {
		return em.merge(nci);
	}
	
	public NotaCompraItem buscarPeloIdNotaCompraItem(Long id) {
		return em.find(NotaCompraItem.class, id);
	}
	
	public List<NotaCompraItem> listarNotaCompraItem() {
		return em.createQuery("select nci from NotaCompraItem nci", NotaCompraItem.class).getResultList();
	}
	
	public void remover(NotaCompraItem nci) {
		em.remove(em.merge(nci));
	}
}
