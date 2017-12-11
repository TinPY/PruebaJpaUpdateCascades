package pruebajpa.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "agente_tarea", schema = "public", catalog = "pruebajpa")
@SequenceGenerator(name = "agente_tarea_id_seq", sequenceName = "agente_tarea_id_seq", allocationSize = 1)
public class AgenteTarea implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agente_tarea_id_seq")
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @JoinColumn(name = "tareaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tarea tareaid;
    
    @JoinColumn(name = "agenteid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persona persona;
    
    @Size(max = 50)
    @Column(name = "funcion", length = 50)
    private String funcion;
    
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    
    @Column(name = "costo")
    private BigDecimal costo;
    
    @Column(name = "horasdedicadas")
    private Integer horasdedicadas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tarea getTareaid() {
		return tareaid;
	}

	public void setTareaid(Tarea tareaid) {
		this.tareaid = tareaid;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public Integer getHorasdedicadas() {
		return horasdedicadas;
	}

	public void setHorasdedicadas(Integer horasdedicadas) {
		this.horasdedicadas = horasdedicadas;
	}
    
    
    
    
	
}
