package org.ebook_premio.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ebook_premio.models.Trabalho;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TrabalhoDAO {
	
	@PersistenceContext
    private EntityManager manager;


	public void gravar(Trabalho trabalho){
		 manager.persist(trabalho);
    }
	
	public List<Trabalho> listar(){
	    return manager.createQuery("select t from Trabalho t", Trabalho.class).getResultList();
	}
	
	public Trabalho find(int id){
	    //return manager.find(Trabalho.class, id);
	    return manager.createQuery("select distinct(t) from Trabalho t join fetch t.precos precos where t.id = :id", Trabalho.class).setParameter("id", id).getSingleResult();
	}	    
}
