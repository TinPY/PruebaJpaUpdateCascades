package pruebajpa.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pruebajpa.modelo.Proyecto;

@Stateless
public class ProyectoFacade {

	@PersistenceContext(unitName = "PRUEBAJPAPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void GuardarProyecto(Proyecto proyecto){
    	
    	try{
    		em.persist(proyecto);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    @SuppressWarnings("unchecked")
	public List<Proyecto> ObtenerProyectos(){
    	
    	Query consulta = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class);
    	return consulta.getResultList();
    }
	
	
}
