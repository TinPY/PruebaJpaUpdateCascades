package pruebajpa.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pruebajpa.modelo.Etapa;
import pruebajpa.modelo.Proyecto;

@Stateless
public class EtapaFacade {

	@PersistenceContext(unitName = "PRUEBAJPAPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Etapa ObtenerEtapaPorId(int id){
    	try{
    		return getEntityManager().find(Etapa.class, id);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    @SuppressWarnings("unchecked")
	public List<Etapa> ObtenerEtapasDeProyecto(int idProyecto){
    	
    	Query consulta = em.createQuery("SELECT e FROM Etapa e WHERE e.proyectoid.id = :idProyecto", Etapa.class)
    			.setParameter("idProyecto", idProyecto);
    	return consulta.getResultList();
    }
    
    public void GuardarEtapa(Etapa etapa){
    	
    	try{
    		em.persist(etapa);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void ActualizarEtapa(Etapa etapa){
    	
    	try{
    		em.merge(etapa);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void BorrarEtapa(Etapa etapa){
    	
    	try{
    		
    		if(em.contains(etapa)){
    			System.out.println("Si lo contiene: SIN MERGE");
    			em.remove(etapa);
    		}else{
    			System.out.println("No lo contiene: CON MERGE");
    			em.remove(em.merge(etapa));
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void BorrarEtapaVersion2(Etapa etapa){
    	
    	try{
    		System.out.println("EtapaFacade.BorrarVersion2 Inicio");
    		Etapa e = em.find(Etapa.class, etapa.getId());
    		em.remove(e);
    		System.out.println("EtapaFacade.BorrarVersion2 Fin");
    	}catch(Exception e){
    		
    	}
    	
    }
	
	
}
