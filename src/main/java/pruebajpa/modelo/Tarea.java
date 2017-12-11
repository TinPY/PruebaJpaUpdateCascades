package pruebajpa.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Created by MartinPY on 26/10/2017.
 */
@Entity
@Table(name = "tareas", schema = "public", catalog = "pruebajpa")
@SequenceGenerator(name="tarea_id_seq", sequenceName="tarea_id_seq",allocationSize=1)
public class Tarea {
    
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tarea_id_seq")	
	private int id;
	
	@Basic
    @Column(name = "nombretarea", nullable = false, length = 255)
    private String nombretarea;
    
	@Basic
    @Column(name = "prioridad", nullable = true)
    private Integer prioridad;
    
    @JoinColumn(name = "etapaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Etapa etapa;
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "tareaid", orphanRemoval=true )	// TODO: Nortia > AGREGAR orphanRemoval
    @Fetch(value = FetchMode.SELECT)																			// TODO: Nortia > AGREGAR/CAMBIAR FetchMode
    private List<AgenteTarea> listaAgentesTarea;
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "tarea", orphanRemoval=true )		// TODO: Nortia > AGREGAR orphanRemoval
    @Fetch(value = FetchMode.SELECT)																			// TODO: Nortia > AGREGAR/CAMBIAR FetchMode
    private List<PresupuestoTarea> listaPresupuestosTarea;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNombretarea() {
        return nombretarea;
    }

    public void setNombretarea(String nombretarea) {
        this.nombretarea = nombretarea;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
    
	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	
	public List<AgenteTarea> getListaAgentesTarea() {
		
		if(null == listaAgentesTarea){
			listaAgentesTarea = new ArrayList<AgenteTarea>();
		}
		
		return listaAgentesTarea;
	}

	public void setListaAgentesTarea(List<AgenteTarea> listaAgentesTarea) {
		this.listaAgentesTarea = listaAgentesTarea;
	}

	public List<PresupuestoTarea> getListaPresupuestosTarea() {
		
		if(null == listaPresupuestosTarea){
			listaPresupuestosTarea = new ArrayList<PresupuestoTarea>();
		}
		
		return listaPresupuestosTarea;
	}

	public void setListaPresupuestosTarea(List<PresupuestoTarea> listaPresupuestosTarea) {
		this.listaPresupuestosTarea = listaPresupuestosTarea;
	}
	
	
	/**
	 * Método que quita la referencia a la Etapa.
	 * Ocurre antes de un EntityManager.remove()
	 * 
	 */
//	@PreRemove
//	public void preRemove() {
//		System.out.println("Tarea [preRemove] " + this.getId() + " - Referencia Etapa > " +  this.getEtapa().getNombreetapa());
//		setEtapa(null);
//		System.out.println("Tarea [preRemove] " + this.getId() + " - Referencia Etapa > " +  this.getEtapa());
//	}
	
	// AGENTE TAREA
	
	/**
	 * TODO: AGREGAR A NORTIA [Tarea.AgregarAgenteTarea()]
	 * 
	 * Agregar a coleccion y setear agenteTarea a tarea
	 * 
	 * Método de agregar a coleccion con setter como en Iphuy, pero más simple, sin el método interno
	 * 
	 * @param agenteTarea
	 */
	public void AgregarAgenteTarea(AgenteTarea agenteTarea) {

		this.getListaAgentesTarea().add(agenteTarea);
       
        if (agenteTarea.getTareaid() != this) {
            agenteTarea.setTareaid(this);
        }
    }
	
	/**
	 * TODO: AGREGAR A NORTIA [Tarea.BorrarAgenteTarea()]
	 * 
	 * Quitar de la coleccion y setear tarea a null
	 * 
	 * @param agenteTarea
	 */
	public void BorrarAgenteTarea(AgenteTarea agenteTarea){
		
		this.getListaAgentesTarea().remove(agenteTarea);
		agenteTarea.setTareaid(null);
	}
	

	// PRESUPUESTO TAREA
	
	/**
	 * TODO: AGREGAR A NORTIA [Tarea.AgregarPresupuestoTarea()]
	 * 
	 * Agregar a coleccion y setear presupuestoTarea a tarea
	 * 
	 * Método de agregar a coleccion con setter como en Iphuy, pero más simple, sin el método interno
	 * 
	 * @param presupuestoTarea
	 */
	public void AgregarPresupuestoTarea(PresupuestoTarea presupuestoTarea) {

		this.getListaPresupuestosTarea().add(presupuestoTarea);
       
        if (presupuestoTarea.getTarea() != this) {
            presupuestoTarea.setTarea(this);
        }
    }
	
	/**
	 * TODO: AGREGAR A NORTIA [Tarea.BorrarPresupuestoTarea()]
	 * 
	 * Quitar de la coleccion y setear tarea a null
	 * 
	 * @param presupuestoTarea
	 */
	public void BorrarPresupuestoTarea(PresupuestoTarea presupuestoTarea){
		
		this.getListaPresupuestosTarea().remove(presupuestoTarea);
		presupuestoTarea.setTarea(null);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarea tarea = (Tarea) o;

        if (id != tarea.id) return false;
        if (nombretarea != null ? !nombretarea.equals(tarea.nombretarea) : tarea.nombretarea != null) return false;
        if (prioridad != null ? !prioridad.equals(tarea.prioridad) : tarea.prioridad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombretarea != null ? nombretarea.hashCode() : 0);
        result = 31 * result + (prioridad != null ? prioridad.hashCode() : 0);
        return result;
    }

    

    
    
    
}
