package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
    public void gravar(Produto produto){
    	entityManager.persist(produto);
    }
    
    @SuppressWarnings("unchecked")
	public List<Produto> listar(){
    	return entityManager.createQuery("select p from Produto p").getResultList();
    }
}
