package pruebajpa.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pruebajpa.modelo.Etapa;
import pruebajpa.modelo.Proyecto;
import pruebajpa.modelo.Rubro;

@Stateless
public class AgenteTareaFacade extends AbstractFacade {

	@PersistenceContext(unitName = "PRUEBAJPAPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Rubro ObtenerRubroPorId(int id){
    	try{
    		return getEntityManager().find(Rubro.class, id);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
	
    
	
}
