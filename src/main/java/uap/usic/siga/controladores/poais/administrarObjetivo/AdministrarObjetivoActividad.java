package uap.usic.siga.controladores.poais.administrarObjetivo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.Poais;
import uap.usic.siga.entidades.PoaisActividades;
import uap.usic.siga.entidades.PoaisDetallesActividades;
import uap.usic.siga.entidades.PoaisObjetivos;
import uap.usic.siga.entidades.PoaisResultados;
import uap.usic.siga.entidades.SacComprobantes;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.CargosServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;

@Controller
@RequestMapping("/poaiObjetivo")
public class AdministrarObjetivoActividad {

	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public PoaisServicios poaiServicios;

	@Autowired
	private PersonasServicios pServicios;

	@GetMapping("/AdministrarObjetivoActividad")
	public String formActividad(HttpSession session, Model model, RedirectAttributes flash) {
		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));

		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));

		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year, 1);

		PoaisObjetivos poaisObjetivos = new PoaisObjetivos();

		for (Poais poais : poaiServicios.listarPoaisPorIdPnlPersonalAdministrativo(
				pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo(), year)) {
			if (poaiServicios.buscarPoaisObjetivosPorIdPoai(poais.getIdPoai()) == null) {
				poaisObjetivos.setPoais(poais);
			} else {
				poaisObjetivos = poaiServicios.buscarPoaisObjetivosPorIdPoai(poais.getIdPoai());
			}
		}

		if (poaisObjetivos.getPoais() != null) {

			poaisObjetivos.setIdEstado("A");
			poaisObjetivos.setRegistro(new Date());

			poaiServicios.registrarPoaisObjetivos(poaisObjetivos);

			flash.addFlashAttribute("success", "Poai Objetivos Registrado con exito!");
		} else {
			// flash.addFlashAttribute("error", "Poai Objetivos ya tiene Registros!");
			// return "redirect:/";
		}

		getDesplegarListasComunes(model, session);

		model.addAttribute("lPoaisObjetivos", poaiServicios.listarPoaisObjetivosPorIdPersonalAdministrativo(
				pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo()));
		model.addAttribute("poaisObjetivos", poaisObjetivos);

		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");

		return "inicioObjetivoActividad";
	}

	@GetMapping("inicioObjetivoActividadNuevo")
	public String inicioObjetivoActividadNuevo(@ModelAttribute("pnlCargos") PnlCargos pnlCargos,
			@RequestParam(name = "idResultado") Long idResultado, Model model, HttpSession session) {
		getDesplegarListasComunes(model, session);

		PoaisResultados poaisResultados = poaiServicios.getPoaisResultados(idResultado);

		//PoaisActividades poaisActividades = new PoaisActividades();
		//poaisActividades.setPoaisResultados(poaisResultados);
		//poaiServicios.registrarPoaisActividades(poaisActividades);

		model.addAttribute("poaisResultados", poaisResultados);
		model.addAttribute("poaisActividades", new PoaisActividades());
		model.addAttribute("poaisMeses", poaiServicios.listarMeses());
		model.addAttribute("poaisSemanas", poaiServicios.listarSemanas());

		model.addAttribute("operation", "reg");
		model.addAttribute("modal", true);

		return "inicioObjetivoActividad";
	}
	
	@GetMapping("/eliminarActividad")
	public String eliminarActividad(@RequestParam("idActividad")Long idActividad, HttpSession session,
			Model model, RedirectAttributes flash) {
		
		PoaisActividades poaisActividades = poaiServicios.getPoaisActividades(idActividad);
		poaisActividades.setIdEstado("X");
		
		poaiServicios.registrarPoaisActividades(poaisActividades);
		flash.addAttribute("idResultado", poaisActividades.getPoaisResultados().getIdResultado());
		
		
		flash.addAttribute("success", "Actividad Eliminado con Exito");
		
		return "redirect:/poaiObjetivo/inicioObjetivoActividadNuevo";
	}

	@PostMapping("/registrarPoaisActividad")
	public String registrarActividad(@RequestParam("idResultado") Long idResultado,@RequestParam("ponderacion")Double ponderacion,
			@RequestParam("idActividad") Long idActividad, @RequestParam("actividad") String actividad,
			@RequestParam("medio") String medio, @RequestParam("semanas") String[] semanas, HttpSession session,
			Model model, RedirectAttributes flash) {

		PoaisResultados poaisResultados = poaiServicios.getPoaisResultados(idResultado);

		PoaisActividades poaisActividades = new PoaisActividades();
		poaisActividades.setPoaisResultados(poaisResultados);
		poaiServicios.registrarPoaisActividades(poaisActividades);
		//PoaisActividades poaisActividades = poaiServicios.getPoaisActividades(idActividad);
		poaisActividades.setMedioVerificacion(medio);
		poaisActividades.setActividad(actividad);
		poaisActividades.setPonderacion(ponderacion);
		
		poaiServicios.registrarPoaisActividades(poaisActividades);
		
		for (int i = 0; i < semanas.length; i++) {
			String aux[] = semanas[i].split(":");
			
			PoaisDetallesActividades poaisDetallesActividades = new PoaisDetallesActividades();
			
			poaisDetallesActividades.setPoaisActividades(poaisActividades);
			poaisDetallesActividades.setIdEstado("A");
			poaisDetallesActividades.setPoaisMeses(poaiServicios.getPoaisMeses(Long.parseLong(aux[0])));
			poaisDetallesActividades.setPoaisSemanas(poaiServicios.getPoaisSemanas(Long.parseLong(aux[1])));
			
			poaiServicios.registrarPoaisDetallesActividades(poaisDetallesActividades);
		}
		flash.addAttribute("idResultado", poaisActividades.getPoaisResultados().getIdResultado());
		
		flash.addAttribute("success", "Actividad Registrado con Exito");
		return "redirect:/poaiObjetivo/inicioObjetivoActividadNuevo";
	}

	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), 2021, 1);
		model.addAttribute("lSupervisores", poaiServicios.listarPoaisSupervisores());
		model.addAttribute("lCargos", aServicios.listarPnlCargos());
		model.addAttribute("urlR", "inicioObjetivoNuevo");
		model.addAttribute("urlN", "inicioObjetivoActividadNuevo");
		model.addAttribute("urlD", "inicioEliminarObjetivo");
		model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
		model.addAttribute("bPnl", pnlPersonalAdministrativos);
		model.addAttribute("lPersonal", pServicios.listarPersonalAdministrativoPorGestion(2021));
		model.addAttribute("regPoais", "registrarPoaisActividad");
		model.addAttribute("urlMod", "lanzarFormModificar");
	}

}
