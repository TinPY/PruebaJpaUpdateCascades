package pruebajpa.modelo;

import javax.persistence.*;

/**
 * Created by MartinPY on 26/10/2017.
 */
@Entity
@Table(name = "proyectos", schema = "public", catalog = "pruebajpa")
@SequenceGenerator(name="proyecto_id_seq", sequenceName="proyecto_id_seq",allocationSize=1)
public class Proyecto {
	
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="proyecto_id_seq")
    private int id;
    
    @Basic
    @Column(name = "nombreproyecto", nullable = false, length = 255)
    private String nombreproyecto;
    
    @ManyToOne
    @JoinColumn(name = "responsable", referencedColumnName = "id")
    private Persona personasResponsable;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proyecto proyecto = (Proyecto) o;

        if (id != proyecto.id) return false;
        if (nombreproyecto != null ? !nombreproyecto.equals(proyecto.nombreproyecto) : proyecto.nombreproyecto != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombreproyecto != null ? nombreproyecto.hashCode() : 0);
        return result;
    }

	public Persona getPersonasResponsable() {
		return personasResponsable;
	}

	public void setPersonasResponsable(Persona personasResponsable) {
		this.personasResponsable = personasResponsable;
	}
    
}
