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
@Table(name = "presupuesto_tarea", schema = "public", catalog = "pruebajpa")
@SequenceGenerator(name="presupuesto_tarea_id_seq", sequenceName="presupuesto_tarea_id_seq", allocationSize=1)
public class PresupuestoTarea implements Serializable {
	

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="presupuesto_tarea_id_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @JoinColumn(name = "rubroid", referencedColumnName = "id")
    @ManyToOne
    private Rubro rubro;
    
    @JoinColumn(name = "tareaid", referencedColumnName = "id")
    @ManyToOne
    private Tarea tarea;

    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "costounitario")
    private BigDecimal costounitario;
    
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    
    @Column(name = "total")
    private BigDecimal total;
    
    @Column(name = "aportecomitente")
    private BigDecimal aportecomitente;
    
    @Column(name = "aporteuniversidad")
    private BigDecimal aporteuniversidad;
    
    @Column(name = "aporteorganismo")
    private BigDecimal aporteorganismo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCostounitario() {
		return costounitario;
	}

	public void setCostounitario(BigDecimal costounitario) {
		this.costounitario = costounitario;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getAportecomitente() {
		return aportecomitente;
	}

	public void setAportecomitente(BigDecimal aportecomitente) {
		this.aportecomitente = aportecomitente;
	}

	public BigDecimal getAporteuniversidad() {
		return aporteuniversidad;
	}

	public void setAporteuniversidad(BigDecimal aporteuniversidad) {
		this.aporteuniversidad = aporteuniversidad;
	}

	public BigDecimal getAporteorganismo() {
		return aporteorganismo;
	}

	public void setAporteorganismo(BigDecimal aporteorganismo) {
		this.aporteorganismo = aporteorganismo;
	}


}
