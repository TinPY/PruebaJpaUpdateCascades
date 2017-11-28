package pruebajpa.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pruebajpa.modelo.Proyecto;
import pruebajpa.persistencia.ProyectoFacade;

@ManagedBean(name = "proyectoMB")
@SessionScoped
public class ProyectoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5102542324584423188L;
	
	private Proyecto actual;
	private List<Proyecto> listaProyectos;
	
	@EJB
	private ProyectoFacade proyectoFacade;
	
	public Proyecto getActual() {
		return actual;
	}

	public void setActual(Proyecto actual) {
		this.actual = actual;
	}

	public List<Proyecto> getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(List<Proyecto> listaProyectos) {
		this.listaProyectos = listaProyectos;
	}

	public ProyectoFacade getProyectoFacade() {
		return proyectoFacade;
	}

	public void GuardarProyecto(){
		
		double numero = Math.random();
		
		System.out.println("ProyectoMB > GuardarProyecto");
		
		Proyecto proyecto = new Proyecto();
		proyecto.setNombreproyecto("Proyecto " + numero);
		
		proyectoFacade.GuardarProyecto(proyecto);
		
	}
	
	public void CargarProyectos(){
		
		this.setListaProyectos(proyectoFacade.ObtenerProyectos());
		
		System.out.println("Cantidad de Proyectos: " + this.getListaProyectos().size());
		
		for(Proyecto p : this.getListaProyectos()){
			System.out.println("Proyecto > " + p.getNombreproyecto());
		}
	}
	
}
