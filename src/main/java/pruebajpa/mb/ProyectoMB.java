package pruebajpa.mb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pruebajpa.modelo.AgenteTarea;
import pruebajpa.modelo.Etapa;
import pruebajpa.modelo.Persona;
import pruebajpa.modelo.PresupuestoTarea;
import pruebajpa.modelo.Proyecto;
import pruebajpa.modelo.Rubro;
import pruebajpa.modelo.Tarea;
import pruebajpa.persistencia.AgenteTareaFacade;
import pruebajpa.persistencia.EtapaFacade;
import pruebajpa.persistencia.PersonaFacade;
import pruebajpa.persistencia.PresupuestoTareaFacade;
import pruebajpa.persistencia.ProyectoFacade;
import pruebajpa.persistencia.RubroFacade;
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

	@EJB
	private RubroFacade rubroFacade;

	@EJB
	private AgenteTareaFacade agenteTareaFacada;

	@EJB
	private PresupuestoTareaFacade presupuestoTareaFacada;

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

	public RubroFacade getRubroFacade() {
		return rubroFacade;
	}

	public AgenteTareaFacade getAgenteTareaFacada() {
		return agenteTareaFacada;
	}

	public void setAgenteTareaFacada(AgenteTareaFacade agenteTareaFacada) {
		this.agenteTareaFacada = agenteTareaFacada;
	}

	public PresupuestoTareaFacade getPresupuestoTareaFacada() {
		return presupuestoTareaFacada;
	}

	public void setPresupuestoTareaFacada(PresupuestoTareaFacade presupuestoTareaFacada) {
		this.presupuestoTareaFacada = presupuestoTareaFacada;
	}

	public void GuardarProyecto() {

		double numero = Math.random();

		System.out.println("ProyectoMB > GuardarProyecto");

		Proyecto proyecto = new Proyecto();
		proyecto.setNombreproyecto("Proyecto " + numero);

		proyectoFacade.GuardarProyecto(proyecto);

	}

	public void CargarProyectos() {

		this.setListaProyectos(proyectoFacade.ObtenerProyectos());

		System.out.println("Cantidad de Proyectos: " + this.getListaProyectos().size());

		for (Proyecto p : this.getListaProyectos()) {
			System.out.println("Proyecto > " + p.getNombreproyecto());
		}
	}

	public void TestGuardarProyectoInicial() {

		// obtenemos la persona que va a ser responsable
		Persona persona = this.getPersonaFacade().ObtenerPersonaPorId(1);

		// nuevo proyecto
		Proyecto proyecto = new Proyecto();
		proyecto.setNombreproyecto("Proyecto A");
		proyecto.setPersonasResponsable(persona);

		// persistimos el proyecto
		this.getProyectoFacade().GuardarProyecto(proyecto);

	}

	public void TestAgregarEtapasTareasPresupuestoAgentesInicial() {

		// obtenemos el proyecto guardado (id = 1)
		Proyecto proyecto = this.getProyectoFacade().ObtenerProyectoPorId(1);

		// creamos las etapas y tareas

		// ETAPA 1 -----------------------------------

		Etapa etapa1 = new Etapa();
		etapa1.setNombreetapa("Etapa 1");
		etapa1.setAvance(23);

		// Tarea 1A

		Tarea tarea1A = new Tarea();
		tarea1A.setNombretarea("Tarea A - Etapa 1");
		tarea1A.setPrioridad(80);

		// Agente Tarea
		AgenteTarea agenteTarea1 = new AgenteTarea();
		agenteTarea1.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(2));
		agenteTarea1.setFuncion("Entrevistador");
		agenteTarea1.setObservacion("asdasd");
		agenteTarea1.setHorasdedicadas(2);
		agenteTarea1.setCosto(new BigDecimal(800));
		tarea1A.AgregarAgenteTarea(agenteTarea1);

		AgenteTarea agenteTarea2 = new AgenteTarea();
		agenteTarea2.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(3));
		agenteTarea2.setFuncion("Entrevistador");
		agenteTarea2.setObservacion("qweqwe");
		agenteTarea2.setHorasdedicadas(2);
		agenteTarea2.setCosto(new BigDecimal(800));
		tarea1A.AgregarAgenteTarea(agenteTarea2);

		// Presupuesto Tarea

		PresupuestoTarea presupuestoTarea1 = new PresupuestoTarea();
		presupuestoTarea1.setDescripcion("Notebook Master Race");
		presupuestoTarea1.setCostounitario(new BigDecimal(30000));
		presupuestoTarea1.setCantidad(new BigDecimal(2));
		presupuestoTarea1.setTotal(new BigDecimal(60000));
		presupuestoTarea1.setRubro(this.getRubroFacade().ObtenerRubroPorId(1));
		presupuestoTarea1.setAporteuniversidad(new BigDecimal(50000));
		presupuestoTarea1.setAporteorganismo(new BigDecimal(10000));
		presupuestoTarea1.setAportecomitente(BigDecimal.ZERO);
		tarea1A.AgregarPresupuestoTarea(presupuestoTarea1);

		// Tarea 1B

		Tarea tarea1B = new Tarea();
		tarea1B.setNombretarea("Tarea B - Etapa 1");
		tarea1B.setPrioridad(62);

		AgenteTarea agenteTarea3 = new AgenteTarea();
		agenteTarea3.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(4));
		agenteTarea3.setFuncion("Analista");
		agenteTarea3.setObservacion("zxczxc");
		agenteTarea3.setHorasdedicadas(3);
		agenteTarea3.setCosto(new BigDecimal(1200));
		tarea1B.AgregarAgenteTarea(agenteTarea3);

		PresupuestoTarea presupuestoTarea2 = new PresupuestoTarea();
		presupuestoTarea2.setDescripcion("Escritorios y Sillas");
		presupuestoTarea2.setCostounitario(new BigDecimal(4000));
		presupuestoTarea2.setCantidad(new BigDecimal(2));
		presupuestoTarea2.setTotal(new BigDecimal(8000));
		presupuestoTarea2.setRubro(this.getRubroFacade().ObtenerRubroPorId(1));
		presupuestoTarea2.setAporteuniversidad(new BigDecimal(6000));
		presupuestoTarea2.setAporteorganismo(new BigDecimal(2000));
		presupuestoTarea2.setAportecomitente(BigDecimal.ZERO);
		tarea1B.AgregarPresupuestoTarea(presupuestoTarea2);

		// ETAPA 2 -----------------------------------

		Etapa etapa2 = new Etapa();
		etapa2.setNombreetapa("Etapa 2");
		etapa2.setAvance(12);

		// Tarea 2A

		Tarea tarea2A = new Tarea();
		tarea2A.setNombretarea("Tarea A - Etapa 2");
		tarea2A.setPrioridad(95);

		// Agente Tarea
		AgenteTarea agenteTarea4 = new AgenteTarea();
		agenteTarea4.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(3));
		agenteTarea4.setFuncion("Arquitecto Software");
		agenteTarea4.setObservacion("tretre");
		agenteTarea4.setHorasdedicadas(4);
		agenteTarea4.setCosto(new BigDecimal(1600));
		tarea2A.AgregarAgenteTarea(agenteTarea4);

		AgenteTarea agenteTarea5 = new AgenteTarea();
		agenteTarea5.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(4));
		agenteTarea5.setFuncion("Diseñador Interfaces");
		agenteTarea5.setObservacion("poipoi");
		agenteTarea5.setHorasdedicadas(4);
		agenteTarea5.setCosto(new BigDecimal(1600));
		tarea2A.AgregarAgenteTarea(agenteTarea5);

		PresupuestoTarea presupuestoTarea3 = new PresupuestoTarea();
		presupuestoTarea3.setDescripcion("Proyector");
		presupuestoTarea3.setCostounitario(new BigDecimal(8000));
		presupuestoTarea3.setCantidad(new BigDecimal(1));
		presupuestoTarea3.setTotal(new BigDecimal(8000));
		presupuestoTarea3.setRubro(this.getRubroFacade().ObtenerRubroPorId(1));
		presupuestoTarea3.setAporteuniversidad(new BigDecimal(6000));
		presupuestoTarea3.setAporteorganismo(new BigDecimal(2000));
		presupuestoTarea3.setAportecomitente(BigDecimal.ZERO);
		tarea2A.AgregarPresupuestoTarea(presupuestoTarea3);

		// Tarea 2B

		Tarea tarea2B = new Tarea();
		tarea2B.setNombretarea("Tarea B - Etapa 2");
		tarea2B.setPrioridad(79);

		// Agente Tarea
		AgenteTarea agenteTarea6 = new AgenteTarea();
		agenteTarea6.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(2));
		agenteTarea6.setFuncion("Desarrollador Java - Backend");
		agenteTarea6.setObservacion("kljasdlkj");
		agenteTarea6.setHorasdedicadas(4);
		agenteTarea6.setCosto(new BigDecimal(1600));
		tarea2B.AgregarAgenteTarea(agenteTarea6);

		// Presupuesto Tarea

		PresupuestoTarea presupuestoTarea4 = new PresupuestoTarea();
		presupuestoTarea4.setDescripcion("RedBull");
		presupuestoTarea4.setCostounitario(new BigDecimal(1000));
		presupuestoTarea4.setCantidad(new BigDecimal(1));
		presupuestoTarea4.setTotal(new BigDecimal(1000));
		presupuestoTarea4.setRubro(this.getRubroFacade().ObtenerRubroPorId(2));
		presupuestoTarea4.setAporteuniversidad(new BigDecimal(100));
		presupuestoTarea4.setAporteorganismo(BigDecimal.ZERO);
		presupuestoTarea4.setAportecomitente(BigDecimal.ZERO);
		tarea2B.AgregarPresupuestoTarea(presupuestoTarea4);

		// Tarea 2C

		Tarea tarea2C = new Tarea();
		tarea2C.setNombretarea("Tarea C - Etapa 2");
		tarea2C.setPrioridad(67);

		// Agente Tarea

		AgenteTarea agenteTarea7 = new AgenteTarea();
		agenteTarea7.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(2));
		agenteTarea7.setFuncion("Desarrollador Java - Frontend");
		agenteTarea7.setObservacion("lasdjklq");
		agenteTarea7.setHorasdedicadas(4);
		agenteTarea7.setCosto(new BigDecimal(1600));
		tarea2C.AgregarAgenteTarea(agenteTarea7);

		AgenteTarea agenteTarea8 = new AgenteTarea();
		agenteTarea8.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(2));
		agenteTarea8.setFuncion("Implementador");
		agenteTarea8.setObservacion("kjdfgjjksa");
		agenteTarea8.setHorasdedicadas(2);
		agenteTarea8.setCosto(new BigDecimal(800));
		tarea2C.AgregarAgenteTarea(agenteTarea8);

		// Presupuesto Tarea

		PresupuestoTarea presupuestoTarea5 = new PresupuestoTarea();
		presupuestoTarea5.setDescripcion("Servidor");
		presupuestoTarea5.setCostounitario(new BigDecimal(45000));
		presupuestoTarea5.setCantidad(new BigDecimal(1));
		presupuestoTarea5.setTotal(new BigDecimal(45000));
		presupuestoTarea5.setRubro(this.getRubroFacade().ObtenerRubroPorId(1));
		presupuestoTarea5.setAporteuniversidad(new BigDecimal(40000));
		presupuestoTarea5.setAporteorganismo(new BigDecimal(5000));
		presupuestoTarea5.setAportecomitente(BigDecimal.ZERO);
		tarea2C.AgregarPresupuestoTarea(presupuestoTarea5);

		PresupuestoTarea presupuestoTarea6 = new PresupuestoTarea();
		presupuestoTarea6.setDescripcion("Router Wifi");
		presupuestoTarea6.setCostounitario(new BigDecimal(1500));
		presupuestoTarea6.setCantidad(new BigDecimal(1));
		presupuestoTarea6.setTotal(new BigDecimal(1500));
		presupuestoTarea6.setRubro(this.getRubroFacade().ObtenerRubroPorId(1));
		presupuestoTarea6.setAporteuniversidad(new BigDecimal(1500));
		presupuestoTarea6.setAporteorganismo(BigDecimal.ZERO);
		presupuestoTarea6.setAportecomitente(BigDecimal.ZERO);
		tarea2C.AgregarPresupuestoTarea(presupuestoTarea6);

		// agregamos las tareas a cada etapa
		// version 1: se agrega a etapa y se setea manualmente.
		// version 2: (método simplificado de iphuy) usamos método AGREGAR &
		// SETTEAR

		etapa1.AgregarTarea(tarea1A);
		etapa1.AgregarTarea(tarea1B);

		etapa2.AgregarTarea(tarea2A);
		etapa2.AgregarTarea(tarea2B);
		etapa2.AgregarTarea(tarea2C);

		// agregamos las etapas al proyecto (PROYECTO SIN LISTA DE ETAPAS)
		etapa1.setProyectoid(proyecto);
		etapa2.setProyectoid(proyecto);

		// actualizamos el proyecto y persistimos etapas
		// this.getProyectoFacade().ActualizarProyecto(proyecto);

		this.getEtapaFacade().GuardarEtapa(etapa1);
		this.getEtapaFacade().GuardarEtapa(etapa2);

		// obtenemos de bd las etapas del proyecto
		Proyecto proyectoActualizado = this.getProyectoFacade().ObtenerProyectoPorId(1);
		List<Etapa> listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(1);

		System.out.println(
				"Proyecto ID " + proyectoActualizado.getId() + " - Nombre: " + proyectoActualizado.getNombreproyecto()
						+ " - Responsable: " + proyectoActualizado.getPersonasResponsable().getNombre());

		// CHEQUEO
		// Etapas
		for (Etapa e : listaEtapas) {
			System.out.println("Etapa: " + e.getNombreetapa());

			// Tareas
			for (Tarea t : e.getListaTareas()) {
				System.out.println("\t Tarea: " + t.getNombretarea());

				// Agente Tarea
				for (AgenteTarea at : t.getListaAgentesTarea()) {
					System.out.println("\t \t Agente Tarea: " + at.getFuncion());
				}

				// Presupuesto Tarea
				for (PresupuestoTarea prt : t.getListaPresupuestosTarea()) {
					System.out.println("\t \t Presupuesto Tarea: " + prt.getDescripcion());
				}

			}
		}
	}

	public void TestActualizarEtapasYTareasProyectoInicial() {

		// obtenemos el proyecto guardado (id = 1)
		Proyecto proyecto = this.getProyectoFacade().ObtenerProyectoPorId(1);

		// obtenemos las etapa actuales
		List<Etapa> listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("PRE ACTUALIZACION");
		for (Etapa e : listaEtapas) {
			System.out.println("Etapa: " + e.getNombreetapa());

			for (Tarea t : e.getListaTareas()) {
				System.out.println("Tarea: " + t.getNombretarea());
			}
		}

		// PRUEBA 1
		// quitamos etapa '1'
		System.out.println("PRUEBA 1");

		Etapa etapa1borrar = this.getEtapaFacade().ObtenerEtapaPorId(1);

		System.out.println("Borrado de Etapa Inicio");
		this.getEtapaFacade().BorrarEtapa(etapa1borrar);
		System.out.println("Borrado de Etapa Fin");

		// chequeo
		listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("ACTUALIZACION CHEQUEO PRUEBA 1 - Resultado: quitamos etapa '1'");

		// CHEQUEO
		// Etapas
		for (Etapa e : listaEtapas) {
			System.out.println("Etapa: " + e.getNombreetapa());

			// Tareas
			for (Tarea t : e.getListaTareas()) {
				System.out.println("\t Tarea: " + t.getNombretarea());

				// Agente Tarea
				for (AgenteTarea at : t.getListaAgentesTarea()) {
					System.out.println("\t \t Agente Tarea: " + at.getFuncion());
				}

				// Presupuesto Tarea
				for (PresupuestoTarea prt : t.getListaPresupuestosTarea()) {
					System.out.println("\t \t Presupuesto Tarea: " + prt.getDescripcion());
				}

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

		AgenteTarea agenteTarea1 = new AgenteTarea();
		agenteTarea1.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(4));
		agenteTarea1.setFuncion("Capacitacion");
		agenteTarea1.setObservacion("kjdfgjjksa");
		agenteTarea1.setHorasdedicadas(2);
		agenteTarea1.setCosto(new BigDecimal(550));
		tarea3A.AgregarAgenteTarea(agenteTarea1);

		AgenteTarea agenteTarea2 = new AgenteTarea();
		agenteTarea2.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(5));
		agenteTarea2.setFuncion("Documentador: manual de usuario");
		agenteTarea2.setObservacion("kjdfgjjksa");
		agenteTarea2.setHorasdedicadas(2);
		agenteTarea2.setCosto(new BigDecimal(450));
		tarea3A.AgregarAgenteTarea(agenteTarea2);

		// Presupuesto Tarea

		PresupuestoTarea presupuestoTarea = new PresupuestoTarea();
		presupuestoTarea.setDescripcion("Viaje de Capacitacion: Paris");
		presupuestoTarea.setCostounitario(new BigDecimal(37000));
		presupuestoTarea.setCantidad(new BigDecimal(5));
		presupuestoTarea.setTotal(new BigDecimal(185000));
		presupuestoTarea.setRubro(this.getRubroFacade().ObtenerRubroPorId(3));
		presupuestoTarea.setAporteuniversidad(new BigDecimal(150000));
		presupuestoTarea.setAporteorganismo(new BigDecimal(35000));
		presupuestoTarea.setAportecomitente(BigDecimal.ZERO);
		tarea3A.AgregarPresupuestoTarea(presupuestoTarea);

		etapa3.AgregarTarea(tarea3A);

		etapa3.setProyectoid(proyecto);

		getEtapaFacade().GuardarEtapa(etapa3); // persistencia

		// CHEQUEO
		listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("ACTUALIZACION CHEQUEO PRUEBA 2 - Resultado: agregamos etapa '3' con 1 tarea");

		// Etapas
		for (Etapa e : listaEtapas) {
			System.out.println("Etapa: " + e.getNombreetapa());

			// Tareas
			for (Tarea t : e.getListaTareas()) {
				System.out.println("\t Tarea: " + t.getNombretarea());

				// Agente Tarea
				for (AgenteTarea at : t.getListaAgentesTarea()) {
					System.out.println("\t \t Agente Tarea: " + at.getFuncion());
				}

				// Presupuesto Tarea
				for (PresupuestoTarea prt : t.getListaPresupuestosTarea()) {
					System.out.println("\t \t Presupuesto Tarea: " + prt.getDescripcion());
				}

			}
		}

		// PRUEBA 3
		// quitamos tarea 'B' de etapa '2' (Asumimos que conocemos el id de
		// ambas, HARDCODEADO)
		// TODO: obtenerlos por por otros medios

		System.out.println("PRUEBA 3");

		// Etapa etapa2 = this.getEtapaFacade().ObtenerEtapaPorId(2);

		Tarea tarea2B = this.getTareaFacade().ObtenerTareaPorId(4);
		Etapa etapa2 = tarea2B.getEtapa();

		etapa2.BorrarTarea(tarea2B);

		// inicio de proceso de desvinculación
		// etapa2.getListaTareas().remove(tarea2B);

		// desvinculacion con preRemove desde EntityManager
		// this.getTareaFacade().BorrarTarea(tarea2B);

		this.getEtapaFacade().ActualizarEtapa(etapa2);

		// persistimos Etapa, para ver si guarda
		// this.getEtapaFacade().ActualizarEtapa(etapa2);

		// CHEQUEO
		listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("ACTUALIZACION CHEQUEO PRUEBA 3 - Resultado: quitamos tarea 'B' de etapa '2'");

		// Etapas
		for (Etapa e : listaEtapas) {
			System.out.println("Etapa: " + e.getNombreetapa());

			// Tareas
			for (Tarea t : e.getListaTareas()) {
				System.out.println("\t Tarea: " + t.getNombretarea());

				// Agente Tarea
				for (AgenteTarea at : t.getListaAgentesTarea()) {
					System.out.println("\t \t Agente Tarea: " + at.getFuncion());
				}

				// Presupuesto Tarea
				for (PresupuestoTarea prt : t.getListaPresupuestosTarea()) {
					System.out.println("\t \t Presupuesto Tarea: " + prt.getDescripcion());
				}

			}
		}

		// PRUEBA 4
		// System.out.println("PRUEBA 4");
		// Etapa etapa2borrarTarea = this.getEtapaFacade().ObtenerEtapaPorId(2);
		// System.out.println("Tareas de Etapa 2: " +
		// etapa2borrarTarea.getListaTareas().size());
		// this.getTareaFacade().BorrarTarea(5);
		// System.out.println("Tareas de Etapa 2 asd: " +
		// etapa2borrarTarea.getListaTareas().size());

		// PRUEBA 5: actualizar una propiedad de una tarea

		System.out.println("PRUEBA 5");

		Tarea tarea3 = this.getTareaFacade().ObtenerTareaPorId(3); // "Tarea A -
																	// Etapa 2"

		System.out.println("Tarea A - Etapa 2 [ID: 3] - prioridad: " + tarea3.getPrioridad());

		tarea3.setPrioridad(0);

		this.getEtapaFacade().ActualizarEtapa(tarea3.getEtapa());

		Tarea tarea3actualizada = this.getTareaFacade().ObtenerTareaPorId(3); // "Tarea
																				// A
																				// -
																				// Etapa
																				// 2"

		System.out.println("ACTUALIZADA > Tarea A - Etapa 2 [ID: 3] - prioridad: " + tarea3actualizada.getPrioridad());

		// CHEQUEO
		listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(proyecto.getId());
		System.out.println("ACTUALIZACION CHEQUEO PRUEBA 5");

		// Etapas
		for (Etapa e : listaEtapas) {
			System.out.println("Etapa: " + e.getNombreetapa());

			// Tareas
			for (Tarea t : e.getListaTareas()) {
				System.out.println("\t Tarea: " + t.getNombretarea());

				// Agente Tarea
				for (AgenteTarea at : t.getListaAgentesTarea()) {
					System.out.println("\t \t Agente Tarea: " + at.getFuncion());
				}

				// Presupuesto Tarea
				for (PresupuestoTarea prt : t.getListaPresupuestosTarea()) {
					System.out.println("\t \t Presupuesto Tarea: " + prt.getDescripcion());
				}

			}
		}

	}

	public void Test() {

		// PRUEBA 1
		// quitamos etapa '1'
		System.out.println("PRUEBA 1");

		Etapa etapa1borrar = this.getEtapaFacade().ObtenerEtapaPorId(1);

		// for(Tarea t : etapa1borrar.getListaTareas()){
		// //agente tarea
		// for(AgenteTarea at : t.getListaAgentesTarea()){
		// this.getAgenteTareaFacada().Borrar(at);
		// }
		//
		// //presupuesto
		// for(PresupuestoTarea prt : t.getListaPresupuestosTarea()){
		// this.getPresupuestoTareaFacada().Borrar(prt);
		// }
		// }

		// Iterator<Tarea> it = etapa1borrar.getListaTareas().iterator();
		//
		// while(it.hasNext()){
		// etapa1borrar.BorrarTarea((Tarea)it.next());
		// }

		etapa1borrar.getListaTareas().clear();

		System.out.println("Borrado de Etapa Inicio");

		// this.getEtapaFacade().BorrarEtapa(etapa1borrar);

		this.getEtapaFacade().ActualizarEtapa(etapa1borrar);

		System.out.println("Borrado de Etapa Fin");

		// chequeo
		List<Etapa> listaEtapas = this.getEtapaFacade().ObtenerEtapasDeProyecto(1);
		System.out.println("ACTUALIZACION CHEQUEO PRUEBA 1 - Resultado: quitamos etapa '1'");

		// CHEQUEO
		// Etapas
		for (Etapa e : listaEtapas) {
			System.out.println("Etapa: " + e.getNombreetapa());

			// Tareas
			for (Tarea t : e.getListaTareas()) {
				System.out.println("\t Tarea: " + t.getNombretarea());

				// Agente Tarea
				for (AgenteTarea at : t.getListaAgentesTarea()) {
					System.out.println("\t \t Agente Tarea: " + at.getFuncion());
				}

				// Presupuesto Tarea
				for (PresupuestoTarea prt : t.getListaPresupuestosTarea()) {
					System.out.println("\t \t Presupuesto Tarea: " + prt.getDescripcion());
				}

			}
		}

	}

	public void TestListaEtapas() {

		Proyecto p = this.proyectoFacade.ObtenerProyectoPorId(1);

//		// p.BorrarEtapa(p.getListaEtapas().get(0));
//
//		p.getListaEtapas().remove(0);
//
//		this.getProyectoFacade().ActualizarProyecto(p);

		System.out.println("Proyecto: " + p.getNombreproyecto());

		// CHEQUEO
		// Etapas
		for (Etapa e : p.getListaEtapas()) {
			System.out.println("Etapa: " + e.getNombreetapa());

			// Tareas
			for (Tarea t : e.getListaTareas()) {
				System.out.println("\t Tarea: " + t.getNombretarea());

				// Agente Tarea
				for (AgenteTarea at : t.getListaAgentesTarea()) {
					System.out.println("\t \t Agente Tarea: " + at.getFuncion());
				}

				// Presupuesto Tarea
				for (PresupuestoTarea prt : t.getListaPresupuestosTarea()) {
					System.out.println("\t \t Presupuesto Tarea: " + prt.getDescripcion());
				}

			}
		}
	}

	public void TestKamikaze1() {

		Proyecto p = this.proyectoFacade.ObtenerProyectoPorId(1);

		System.out.println("Editamos atributo de una tarea de la etapa 2");
		System.out.println("Prioridad antes de ser modificada "
				+ p.getListaEtapas().get(1).getListaTareas().get(0).getPrioridad().toString());
		p.getListaEtapas().get(1).getListaTareas().get(0).setPrioridad(100);
		System.out.println("Prioridad despues de ser modificada "
				+ p.getListaEtapas().get(1).getListaTareas().get(0).getPrioridad().toString());

		

		System.out.println("--------------------------------------------------------------------------");

		System.out.println("Creamos Etapa 4 (completa)");
		Etapa etapa4 = new Etapa();
		etapa4.setNombreetapa("Etapa 4");
		etapa4.setAvance(99);

		// Tarea 1A

		Tarea tarea4A = new Tarea();
		tarea4A.setNombretarea("Tarea A - Etapa 4");
		tarea4A.setPrioridad(80);

		// Agente Tarea
		AgenteTarea agenteTarea1 = new AgenteTarea();
		agenteTarea1.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(2));
		agenteTarea1.setFuncion("Kamikaze 1");
		agenteTarea1.setObservacion("zxc");
		agenteTarea1.setHorasdedicadas(2);
		agenteTarea1.setCosto(new BigDecimal(1250));
		tarea4A.AgregarAgenteTarea(agenteTarea1);

		AgenteTarea agenteTarea2 = new AgenteTarea();
		agenteTarea2.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(3));
		agenteTarea2.setFuncion("Kamikaze 2");
		agenteTarea2.setObservacion("sdfbas");
		agenteTarea2.setHorasdedicadas(2);
		agenteTarea2.setCosto(new BigDecimal(1250));
		tarea4A.AgregarAgenteTarea(agenteTarea2);

		// Presupuesto Tarea

		PresupuestoTarea presupuestoTarea1 = new PresupuestoTarea();
		presupuestoTarea1.setDescripcion("Porron");
		presupuestoTarea1.setCostounitario(new BigDecimal(1500));
		presupuestoTarea1.setCantidad(new BigDecimal(2));
		presupuestoTarea1.setTotal(new BigDecimal(3000));
		presupuestoTarea1.setRubro(this.getRubroFacade().ObtenerRubroPorId(2));
		presupuestoTarea1.setAporteuniversidad(new BigDecimal(3000));
		presupuestoTarea1.setAporteorganismo(BigDecimal.ZERO);
		presupuestoTarea1.setAportecomitente(BigDecimal.ZERO);
		tarea4A.AgregarPresupuestoTarea(presupuestoTarea1);

		// Tarea 1B

		Tarea tarea4B = new Tarea();
		tarea4B.setNombretarea("Tarea B - Etapa 4");
		tarea4B.setPrioridad(62);

		AgenteTarea agenteTarea3 = new AgenteTarea();
		agenteTarea3.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(4));
		agenteTarea3.setFuncion("Limpieza Kamikazeada");
		agenteTarea3.setObservacion("kjsdhjskjdf");
		agenteTarea3.setHorasdedicadas(3);
		agenteTarea3.setCosto(new BigDecimal(1800));
		tarea4B.AgregarAgenteTarea(agenteTarea3);

		PresupuestoTarea presupuestoTarea2 = new PresupuestoTarea();
		presupuestoTarea2.setDescripcion("Escoba");
		presupuestoTarea2.setCostounitario(new BigDecimal(5000));
		presupuestoTarea2.setCantidad(new BigDecimal(1));
		presupuestoTarea2.setTotal(new BigDecimal(5000));
		presupuestoTarea2.setRubro(this.getRubroFacade().ObtenerRubroPorId(1));
		presupuestoTarea2.setAporteuniversidad(new BigDecimal(4000));
		presupuestoTarea2.setAporteorganismo(new BigDecimal(1000));
		presupuestoTarea2.setAportecomitente(BigDecimal.ZERO);
		tarea4B.AgregarPresupuestoTarea(presupuestoTarea2);
		
		etapa4.AgregarTarea(tarea4A);
		etapa4.AgregarTarea(tarea4B);

		p.AgregarEtapa(etapa4);

		System.out.println("--------------------------------------------------------------------------");

		System.out.println("Quitamos tarea de la etapa 0");
		p.getListaEtapas().get(1).getListaTareas().remove(0);
		
		System.out.println("--------------------------------------------------------------------------");
		
		System.out.println("Agregamos tarea nueva a etapa 2");
		
		Tarea tarea2D = new Tarea();
		tarea2D.setNombretarea("Tarea D - Etapa 2");
		tarea2D.setPrioridad(999);

		AgenteTarea agenteTarea2D = new AgenteTarea();
		agenteTarea2D.setPersona(this.getPersonaFacade().ObtenerPersonaPorId(4));
		agenteTarea2D.setFuncion("Desinfeccion");
		agenteTarea2D.setObservacion("quwehkjasjkd");
		agenteTarea2D.setHorasdedicadas(10);
		agenteTarea2D.setCosto(new BigDecimal(2000));
		tarea2D.AgregarAgenteTarea(agenteTarea2D);
		
		p.getListaEtapas().get(1).AgregarTarea(tarea2D);
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Borramos la etapa 0");
		p.getListaEtapas().remove(0);

		this.getProyectoFacade().ActualizarProyecto(p);
	}

}
