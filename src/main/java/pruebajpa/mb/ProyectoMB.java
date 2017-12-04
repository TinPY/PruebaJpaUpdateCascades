package pruebajpa.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pruebajpa.modelo.Etapa;
import pruebajpa.modelo.Persona;
import pruebajpa.modelo.Proyecto;
import pruebajpa.modelo.Tarea;
import pruebajpa.persistencia.EtapaFacade;
import pruebajpa.persistencia.PersonaFacade;
import pruebajpa.persistencia.ProyectoFacade;
import pruebajpa.persistencia.TareaFacade;

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
	
	@EJB
	private PersonaFacade personaFacade;
	
	@EJB
	private EtapaFacade etapaFacade;
	
	@EJB
	private TareaFacade tareaFacade;
	
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
	
	public PersonaFacade getPersonaFacade() {
		return personaFacade;
	}
	
	public EtapaFacade getEtapaFacade() {
		return etapaFacade;
	}
	
	public TareaFacade getTareaFacade() {
		return tareaFacade;
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
	
	public void TestGuardarProyectoInicial(){
		
		// obtenemos la persona que va a ser responsable
		Persona persona = this.getPersonaFacade().ObtenerPersonaPorId(1);
		
		// nuevo proyecto
		Proyecto proyecto = new Proyecto();
		proyecto.setNombreproyecto("Proyecto A");
		proyecto.setPersonasResponsable(persona);
		
		// persistimos el proyecto
		this.getProyectoFacade().GuardarProyecto(proyecto);
		
	}
	
	public void TestAgregarEtapasYTareasProyectoInicial(){
		
		// obtenemos el proyecto guardado (id = 1)
		Proyecto proyecto = this.getProyectoFacade().ObtenerProyectoPorId(1);
		
		// creamos las etapas y tareas
		
		Etapa etapa1 = new Etapa();
		etapa1.setNombreetapa("Etapa 1");
		etapa1.setAvance(23);
		
		Tarea tarea1A = new Tarea();
		tarea1A.setNombretarea("Tarea A - Etapa 1");
		tarea1A.setPrioridad(80);
		
		Tarea tarea1B = new Tarea();
		tarea1B.setNombretarea("Tarea B - Etapa 1");
		tarea1B.setPrioridad(62);
		
		Etapa etapa2 = new Etapa();
		etapa2.setNombreetapa("Etapa 2");
		etapa2.setAvance(12);
		
		Tarea tarea2A = new Tarea();
		tarea2A.setNombretarea("Tarea A - Etapa 2");
		tarea2A.setPrioridad(95);
		
		Tarea tarea2B = new Tarea();
		tarea2B.setNombretarea("Tarea B - Etapa 2");
		tarea2B.setPrioridad(79);
		
		Tarea tarea2C = new Tarea();
		tarea2C.setNombretarea("Tarea C - Etapa 2");
		tarea2C.setPrioridad(67);
		
		// agregamos las tareas a cada etapa
		// version 1: se agrega a etapa y se setea manualmente.
		// version 2: (m�todo simplificado de iphuy) usamos m�todo AGREGAR & SETTEAR
		
		etapa1.AgregarTarea(tarea1A);
		etapa1.AgregarTarea(tarea1B);
		
		etapa2.AgregarTarea(tarea2A);
		etapa2.AgregarTarea(tarea2B);
		etapa2.AgregarTarea(tarea2C);
		
		// agregamos las etapas al proyecto (PROYECTO SIN LISTA DE ETAPAS)
		etapa1.setProyectoid(proyecto);
		etapa2.setProyectoid(proyecto);
		
		// actualizamos el proyecto y persistimos etapas
		//this.getProyectoFacade().ActualizarProyecto(proyecto);
	
		this.getEtapaFacade().GuardarEtapa(etapa1);
		this.getEtapaFacade().GuardarEtapa(etapa2);
		
		// obtenemos de bd las etapas del proyecto
		Proyecto proyectoActualizado = this.getProyectoFacade().ObtenerProyectoPorId(1);
		List<Etapa> listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(1);
		
		System.out.println("Proyecto ID " + proyectoActualizado.getId() + " - Nombre: " + proyectoActualizado.getNombreproyecto() + " - Responsable: " + proyectoActualizado.getPersonasResponsable().getNombre());
		
		for(Etapa e : listaEtapas){
			System.out.println("Etapa: " + e.getNombreetapa());
			
			for(Tarea t : e.getListaTareas()){
				System.out.println("Tarea: " + t.getNombretarea());
			}
		}
	}
	
	public void TestActualizarEtapasYTareasProyectoInicial(){
		
		// obtenemos el proyecto guardado (id = 1)
		Proyecto proyecto = this.getProyectoFacade().ObtenerProyectoPorId(1);
		
		// obtenemos las etapa actuales
		List<Etapa> listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("PRE ACTUALIZACION");
		for(Etapa e : listaEtapas){
			System.out.println("Etapa: " + e.getNombreetapa());
			
			for(Tarea t : e.getListaTareas()){
				System.out.println("Tarea: " + t.getNombretarea());
			}
		}
		
		// PRUEBA 1
		// quitamos etapa '1'
		System.out.println("PRUEBA 1");
		
		Etapa etapa1borrar = this.getEtapaFacade().ObtenerEtapaPorId(1);
		this.getEtapaFacade().BorrarEtapa(etapa1borrar);
		
		// chequeo
		listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("ACTUALIZACION CHEQUEO PRUEBA 1");
		for(Etapa e : listaEtapas){
			System.out.println("Etapa: " + e.getNombreetapa());
			
			for(Tarea t : e.getListaTareas()){
				System.out.println("Tarea: " + t.getNombretarea());
			}
		}
		
		// PRUEBA 2
		// agregamos etapa '3' con 1 tarea
		
		System.out.println("PRUEBA 2");
		
		Etapa etapa3 = new Etapa();
		etapa3.setNombreetapa("Etapa 3");
		etapa3.setAvance(57);
		
		Tarea tarea3A = new Tarea();
		tarea3A.setNombretarea("Tarea A - Etapa 3");
		tarea3A.setPrioridad(99);
		
		etapa3.AgregarTarea(tarea3A);
		
		etapa3.setProyectoid(proyecto);
		
		getEtapaFacade().GuardarEtapa(etapa3); 			// persistencia
		
		// chequeo
		listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("ACTUALIZACION CHEQUEO PRUEBA 2");
		for(Etapa e : listaEtapas){
			System.out.println("Etapa: " + e.getNombreetapa());
			
			for(Tarea t : e.getListaTareas()){
				System.out.println("Tarea: " + t.getNombretarea());
			}
		}
		
		// PRUEBA 3
		// quitamos tarea 'B' de etapa '2' (Asumimos que conocemos el id de ambas, HARDCODEADO)
		// TODO: obtenerlos por por otros medios
		
		System.out.println("PRUEBA 3");
		
		//Etapa etapa2 = this.getEtapaFacade().ObtenerEtapaPorId(2);
		
		Tarea tarea2B = this.getTareaFacade().ObtenerTareaPorId(4);
		Etapa etapa2 = tarea2B.getEtapa();
		
		etapa2.BorrarTarea(tarea2B);
		
		// inicio de proceso de desvinculaci�n
		//etapa2.getListaTareas().remove(tarea2B);
		
		// desvinculacion con preRemove desde EntityManager
		//this.getTareaFacade().BorrarTarea(tarea2B);
		
		this.getEtapaFacade().ActualizarEtapa(etapa2);
		
		// persistimos Etapa, para ver si guarda 
		//this.getEtapaFacade().ActualizarEtapa(etapa2);
		
		
		// chequeo
		listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("ACTUALIZACION CHEQUEO PRUEBA 3");
		for(Etapa e : listaEtapas){
			System.out.println("Etapa: " + e.getNombreetapa());
			
			for(Tarea t : e.getListaTareas()){
				System.out.println("Tarea: " + t.getNombretarea());
			}
		}
		
		// PRUEBA 4
//		System.out.println("PRUEBA 4");
//		Etapa etapa2borrarTarea = this.getEtapaFacade().ObtenerEtapaPorId(2);
//		System.out.println("Tareas de Etapa 2: " + etapa2borrarTarea.getListaTareas().size());
//		this.getTareaFacade().BorrarTarea(5);
//		System.out.println("Tareas de Etapa 2 asd: " + etapa2borrarTarea.getListaTareas().size());
		
		// PRUEBA 5: actualizar una propiedad de una tarea
		
		System.out.println("PRUEBA 5");
		
		Tarea tarea3 = this.getTareaFacade().ObtenerTareaPorId(3); // "Tarea A - Etapa 2"
		
		System.out.println("Tarea A - Etapa 2 [ID: 3] - prioridad: " + tarea3.getPrioridad());
		
		tarea3.setPrioridad(0);
		
		this.getEtapaFacade().ActualizarEtapa(tarea3.getEtapa());
		
		Tarea tarea3actualizada = this.getTareaFacade().ObtenerTareaPorId(3); // "Tarea A - Etapa 2"
		
		System.out.println("ACTUALIZADA > Tarea A - Etapa 2 [ID: 3] - prioridad: " + tarea3actualizada.getPrioridad());
		
		
	}
	
}
