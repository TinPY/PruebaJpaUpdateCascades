package pruebajpa.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rubro", schema = "public", catalog = "pruebajpa")
@SequenceGenerator(name="rubro_id_seq", sequenceName="rubro_id_seq", allocationSize=1)
public class Rubro implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rubro_id_seq")
    @Basic(optional = true)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 60)
    @Column(name = "rubro")
    private String rubro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
    

    
    
    

}
