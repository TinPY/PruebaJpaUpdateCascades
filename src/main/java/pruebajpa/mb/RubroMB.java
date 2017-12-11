package pruebajpa.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pruebajpa.modelo.Persona;
import pruebajpa.modelo.Proyecto;
import pruebajpa.modelo.Rubro;
import pruebajpa.persistencia.PersonaFacade;
import pruebajpa.persistencia.ProyectoFacade;
import pruebajpa.persistencia.RubroFacade;

@ManagedBean(name = "rubroMB")
@SessionScoped
public class RubroMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5102542324584423188L;
	
	private Rubro actual;
	private List<Rubro> listaRubros;
	
	@EJB
	private RubroFacade rubroFacade;
	
	public Rubro getActual() {
		return actual;
	}

	public void setActual(Rubro actual) {
		this.actual = actual;
	}

	public List<Rubro> getListaRubros() {
		return listaRubros;
	}

	public void setListaRubros(List<Rubro> listaRubros) {
		this.listaRubros = listaRubros;
	}

	public RubroFacade getRubroFacade() {
		return rubroFacade;
	}


	public void InstalarRubros(){
		
		System.out.println("Creación de Rubros > Inicio");
		
		// Creamos Rubros
		Rubro rubroBienesUso = new Rubro();
		rubroBienesUso.setRubro("Bienes de Uso");
		this.getRubroFacade().Guardar(rubroBienesUso);
		
		Rubro rubroBienesConsumo = new Rubro();
		rubroBienesConsumo.setRubro("Bienes de Consumo");
		this.getRubroFacade().Guardar(rubroBienesConsumo);
		
		Rubro rubroViajes = new Rubro();
		rubroViajes.setRubro("Viajes");
		this.getRubroFacade().Guardar(rubroViajes);
		
		Rubro rubroConstruccion = new Rubro();
		rubroConstruccion.setRubro("Construccion");
		this.getRubroFacade().Guardar(rubroConstruccion);
		
		System.out.println("Rubros Creados");
		
	}
	
	public Rubro BuscarRubro(int id){
		return this.getRubroFacade().ObtenerRubroPorId(id);
	}
	
}
