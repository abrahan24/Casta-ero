package uap.usic.siga.controladores.poais.formulario;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.Poais;
import uap.usic.siga.entidades.PoaisActividades;
import uap.usic.siga.entidades.PoaisIdentificaciones;
import uap.usic.siga.entidades.PoaisResultados;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.CargosServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;

@Controller
@RequestMapping("/poaiSupervision")
public class PoaiSupervision {
	
	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public PoaisServicios poaiServicios;

	@Autowired
	private PersonasServicios pServicios;

	@Autowired
	private CargosServicios cargosServicios;

	@RequestMapping("/")
	public String supervision(HttpSession session, Model model, RedirectAttributes flash) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		
		model.addAttribute("identificaciones",poaiServicios.getAllPoaiSupervisor(personas.getIdPersona()));
		
		
		getDesplegarListasComunes(model, session);
		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");

		return "poaiSupervision";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String evaluacion(@RequestParam("id_actividad") Long id_actividad, @RequestParam("ponderacion") Double ponderacion, RedirectAttributes flash) {
		PoaisActividades actividades = poaiServicios.getPoaisActividades(id_actividad);
		
		actividades.setPorcentaje(ponderacion);
		actividades.setPuntaje(((ponderacion*actividades.getPoaisResultados().getPonderacion())/100));
		
		poaiServicios.registrarPoaisActividades(actividades);
		
		double aux = 0;
		for (PoaisActividades act : actividades.getPoaisResultados().getPoaisActividades()) {
			if (act.getPuntaje() != null) {
				aux = aux+act.getPuntaje();
			}			
		}
		
		PoaisResultados poaisResultados = poaiServicios.getPoaisResultados(actividades.getPoaisResultados().getIdResultado());		
		poaisResultados.setPuntaje(aux/poaisResultados.getPoaisActividades().size());
		
		poaiServicios.registrarPoaisResultados(poaisResultados);		
		flash.addAttribute("success", "Evaluacion de Actividad realizada con Exito!");
		
		return "redirect:/poaiSupervision/";
	}

	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), 2021, 1);
		model.addAttribute("lSupervisores", poaiServicios.listarPoaisSupervisores());
		model.addAttribute("lCargos", aServicios.listarPnlCargos());
		model.addAttribute("urlN", "inicioObjetivoNuevo");
		model.addAttribute("urlM", "inicioModificarObjetivo");
		model.addAttribute("urlD", "inicioEliminarObjetivo");
		model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
		model.addAttribute("bPnl", pnlPersonalAdministrativos);
		model.addAttribute("lPersonal", pServicios.listarPersonalAdministrativoPorGestion(2021));
		model.addAttribute("regPoais", "registrarPoaisObjetivo");
		model.addAttribute("urlMod", "lanzarFormModificar");
	}
}
