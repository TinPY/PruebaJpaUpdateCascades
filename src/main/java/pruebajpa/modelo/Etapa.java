package pruebajpa.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Created by MartinPY on 26/10/2017.
 */
@Entity
@Table(name = "etapas", schema = "public", catalog = "pruebajpa")
@SequenceGenerator(name="etapa_id_seq", sequenceName="etapa_id_seq",allocationSize=1)
public class Etapa {
    
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="etapa_id_seq")
	private int id;
	
	@Basic
    @Column(name = "nombreetapa", nullable = false, length = 255)
    private String nombreetapa;
	
	@Basic
    @Column(name = "avance", nullable = true)
    private Integer avance;
    
    @ManyToOne
    private Proyecto proyectoid;
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="etapaid")
    private List<Tarea> listaTareas;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNombreetapa() {
        return nombreetapa;
    }

    public void setNombreetapa(String nombreetapa) {
        this.nombreetapa = nombreetapa;
    }

    
    public Integer getAvance() {
        return avance;
    }

    public void setAvance(Integer avance) {
        this.avance = avance;
    }

    public Proyecto getProyectoid() {
		return proyectoid;
	}

	public void setProyectoid(Proyecto proyectoid) {
		this.proyectoid = proyectoid;
	}
	
	public List<Tarea> getListaTareas() {
		if(null == listaTareas){
			listaTareas = new ArrayList<Tarea>();
		}
		
		return listaTareas;
	}

	public void setListaTareas(List<Tarea> listaTareas) {
		this.listaTareas = listaTareas;
	}
	
	/**
	 * Agregar a coleccion y setear etapa a tarea
	 * 
	 * Método de agregar a coleccion con setter como en Iphuy, pero más simple, sin el método interno
	 * 
	 * @param tarea
	 */
	public void agregarTarea(Tarea tarea) {

		this.getListaTareas().add(tarea);
        
        if (tarea.getEtapa() != this) {
            tarea.setEtapa(this);
        }
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Etapa etapa = (Etapa) o;

        if (id != etapa.id) return false;
        if (nombreetapa != null ? !nombreetapa.equals(etapa.nombreetapa) : etapa.nombreetapa != null) return false;
        if (avance != null ? !avance.equals(etapa.avance) : etapa.avance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombreetapa != null ? nombreetapa.hashCode() : 0);
        result = 31 * result + (avance != null ? avance.hashCode() : 0);
        return result;
    }
}
