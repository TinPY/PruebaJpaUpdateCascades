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
	
	public void InstalarPersonas(){
		Persona persona1 = new Persona();
		persona1.setNombre("Tincho");
		persona1.setTelefono("154685");
		this.getPersonaFacade().GuardarPersona(persona1);
		
		Persona persona2 = new Persona();
		persona2.setNombre("Rodri");
		persona2.setTelefono("128389");
		this.getPersonaFacade().GuardarPersona(persona2);
		
		Persona persona3 = new Persona();
		persona3.setNombre("Romi");
		persona3.setTelefono("7456514");
		this.getPersonaFacade().GuardarPersona(persona3);
		
		Persona persona4 = new Persona();
		persona4.setNombre("Javi");
		persona4.setTelefono("9873123");
		this.getPersonaFacade().GuardarPersona(persona4);
		
		Persona persona5 = new Persona();
		persona5.setNombre("German");
		persona5.setTelefono("68413212");
		this.getPersonaFacade().GuardarPersona(persona5);
		
	}
	
	
}
