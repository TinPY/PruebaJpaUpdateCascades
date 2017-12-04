package pruebajpa.modelo;

import javax.persistence.*;

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
