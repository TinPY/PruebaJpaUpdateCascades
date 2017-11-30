package pruebajpa.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pruebajpa.modelo.Etapa;
import pruebajpa.modelo.Proyecto;
import pruebajpa.modelo.Tarea;

@Stateless
public class TareaFacade {

	@PersistenceContext(unitName = "PRUEBAJPAPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Tarea ObtenerTareaPorId(int id){
    	try{
    		return getEntityManager().find(Tarea.class, id);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public void GuardarTarea(Tarea tarea){
    	
    	try{
    		em.persist(tarea);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void ActualizarTarea(Tarea tarea){
    	
    	try{
    		em.merge(tarea);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void BorrarTarea(Tarea tarea){
    	
    	try{
    		em.remove(tarea);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	
}