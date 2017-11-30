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
		// version 2: (método simplificado de iphuy) usamos método AGREGAR & SETTEAR
		
		etapa1.agregarTarea(tarea1A);
		etapa1.agregarTarea(tarea1B);
		
		etapa2.agregarTarea(tarea2A);
		etapa2.agregarTarea(tarea2B);
		etapa2.agregarTarea(tarea2C);
		
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
		
		// quitamos etapa '1'
		Etapa etapa1borrar = this.getEtapaFacade().ObtenerEtapaPorId(1);
		this.getEtapaFacade().BorrarEtapa(etapa1borrar);
		
		// agregamos etapa '3' con 1 tarea
		Etapa etapa3 = new Etapa();
		etapa3.setNombreetapa("Etapa 3");
		etapa3.setAvance(57);
		
		Tarea tarea3A = new Tarea();
		tarea3A.setNombretarea("Tarea A - Etapa 3");
		tarea3A.setPrioridad(99);
		
		etapa3.agregarTarea(tarea3A);
		
		etapa3.setProyectoid(proyecto);
		
		getEtapaFacade().GuardarEtapa(etapa3); 			// persistencia
		
		// quitamos tarea 'B' de etapa '2' (Asumimos que conocemos el id de ambas, HARDCODEADO)
		// TODO: obtenerlos por por otros medios
		Etapa etapa2 = this.getEtapaFacade().ObtenerEtapaPorId(2);
		
		Tarea tarea2B = this.getTareaFacade().ObtenerTareaPorId(4);
		
		// inicio de proceso de desvinculación
		etapa2.getListaTareas().remove(tarea2B);
		
		
		// persistimos Etapa, para ver si guarda 
		
		
		// persistimos las etapas
		
		
		// verificamos las nuevas etapas del proyecto
		List<Etapa> etapasActualizadas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		
		System.out.println("Etapas Actualizadas");
		for(Etapa e : etapasActualizadas){
			System.out.println("Etapa: " + e.getNombreetapa());
			
			for(Tarea t : e.getListaTareas()){
				System.out.println("Tarea: " + t.getNombretarea());
			}
		}
		
		
	}
	
}
