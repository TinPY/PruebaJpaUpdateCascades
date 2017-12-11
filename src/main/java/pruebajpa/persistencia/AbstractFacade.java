package pruebajpa.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pruebajpa.modelo.Etapa;
import pruebajpa.modelo.Proyecto;

@Stateless
public abstract class AbstractFacade<T> {

	@PersistenceContext(unitName = "PRUEBAJPAPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void Guardar(T entidad){
    	
    	try{
    		em.persist(entidad);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void Actualizar(T entidad){
    	
    	try{
    		em.merge(entidad);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void Borrar(T entidad){
    	
    	try{
    		em.remove(em.contains(entidad) ? entidad : em.merge(entidad));
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	
}
