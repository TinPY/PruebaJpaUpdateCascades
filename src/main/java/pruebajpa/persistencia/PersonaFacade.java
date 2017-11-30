package pruebajpa.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pruebajpa.modelo.Persona;
import pruebajpa.modelo.Proyecto;

@Stateless
public class PersonaFacade {

	@PersistenceContext(unitName = "PRUEBAJPAPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void GuardarPersona(Persona persona){
    	
    	try{
    		em.persist(persona);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    @SuppressWarnings("unchecked")
	public List<Persona> ObtenerPersonas(){
    	
    	Query consulta = em.createQuery("SELECT p FROM Persona p", Persona.class);
    	return consulta.getResultList();
    }
    
    public Persona ObtenerPersonaPorId(int id){
    	
    	return em.find(Persona.class, id);
    }
	
	
}
