package br.inatel.labs.labjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// Notação informando que se trata de uma classe de serviço -> onde são colocadas regras de negócios
// Todos os métodos que fazem ESCRITA devem ser transacionais (org.springframework.transaction.annotation.Transactional)
@Service
@Transactional
public class ProdutoService {
	
	// Pedido de injeção de dependências no objeto
	@PersistenceContext
	private EntityManager em;
	
	// Retorna um produto pois, quando um novo é criado, ainda não possui uma chave primária, visto que a mesma é settada no SQL.
	// Assim, após a inserção do objeto no banco de dados (com a PK devidamente criada), o BD retorna o objeto completo (com a PK)
	public Produto salvar(Produto p) {
		return em.merge(p);
	}
	
	public Produto buscarPeloId(Long id) {
		// O método find é genérico, ele entende apenas que deve buscar a chave primária, mas não sabe qual classe. Por isso precisamos 
		// informa-lo qual a classe ele deve buscar a PK
		return em.find(Produto.class, id);
	}
	
	// Pincelando a linguagem de consulta OO criada pelo JPA
	public List<Produto> listar() {
		// Java Persistence Query Language -> selecionando instancias de Produto
		String jpql = "select ps from Produto ps"; // OU "select * from Produto"
		
		// Em tempo de execução, o método não entende que deve retornar o objeto encontrado no formato da classe informada no JPQL. Assim,
		// devemos passar como segundo parâmetro o tipo da classe
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	
	// Fora do contexto de persistência, o objeto estará Detachada, ou seja, sem conexão direta com o banco de dados. Por isso, precisamos 
	// fazer o merge antes de remover o objeto, para que ele possa entrar em estado Managed novamente (conexão direta com o BD)
	// Datached -> Managed  -> Removed
	public void remover(Produto p) {
		p = em.merge(p);
		em.remove(p);
	}
	
}
