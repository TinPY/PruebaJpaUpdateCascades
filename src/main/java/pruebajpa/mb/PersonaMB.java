package pruebajpa.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pruebajpa.modelo.Persona;
import pruebajpa.modelo.Proyecto;
import pruebajpa.persistencia.PersonaFacade;
import pruebajpa.persistencia.ProyectoFacade;

@ManagedBean(name = "personaMB")
@SessionScoped
public class PersonaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5102542324584423188L;
	
	private Persona actual;
	private List<Persona> listaPersonas;
	private String nombrePersona;
	
	@EJB
	private PersonaFacade personaFacade;
	
	public Persona getActual() {
		return actual;
	}

	public void setActual(Persona actual) {
		this.actual = actual;
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public PersonaFacade getPersonaFacade() {
		return personaFacade;
	}
	
	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public void GuardarPersona(){
		
		double numero = Math.random();
		
		Persona persona = new Persona();
		persona.setNombre(nombrePersona);
		persona.setTelefono(String.valueOf(numero));
		
		this.getPersonaFacade().GuardarPersona(persona);
		
		this.setNombrePersona("");
				
	}
	
	public void CargarPersonas(){
		
		listaPersonas = this.getPersonaFacade().ObtenerPersonas();
		
		System.out.println("Cantidad de Personas: " + this.getListaPersonas().size());
		
		for(Persona p : this.getListaPersonas()){
			System.out.println("Persona > " + p.getNombre());
		}
		
	}
	
	
}
