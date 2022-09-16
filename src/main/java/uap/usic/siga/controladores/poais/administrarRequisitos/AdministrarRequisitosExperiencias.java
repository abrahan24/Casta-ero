package uap.usic.siga.controladores.poais.administrarRequisitos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.Poais;
import uap.usic.siga.entidades.PoaisRequisitos;
import uap.usic.siga.entidades.PoaisRequisitosCualidades;
import uap.usic.siga.entidades.PoaisRequisitosExperiencias;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;

/**
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/poaReqExperiencias")
public class AdministrarRequisitosExperiencias {

	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public PoaisServicios poaServicios;

	@Autowired
	private PersonasServicios pServicios;

	@GetMapping("/inicioReqExperiencia")
	public String formInicioRequisitoExperiencias(HttpSession session, Model model) {
		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));
		
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year, 1);

		Poais poais = poaServicios.buscarPoaisPorIdPnlPersonalAdministrativoGestionIdEstado(
				pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo(), year, "A");
		PoaisRequisitos poaisRequisitos = poaServicios.buscarPoaisRequisitosPorIdPoai(poais.getIdPoai());

		getDesplegarListasComunes(model, session);
		if (poaisRequisitos != null) {
			model.addAttribute("lRExperiencias",
					poaServicios.listarRequisitosExperienciasPorIdRequisitos(poaisRequisitos.getIdRequisito()));
		}
		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");
		return "inicioPoaReqExperiencia";
	}

	@PostMapping("/inicioRequiExperiencias")
	public String inicioFormRequiExperiencias(
			@ModelAttribute("poaisRequisitosExperiencias") PoaisRequisitosExperiencias poaisRequisitosExperiencias,
			HttpSession session, Model model) {

		getDesplegarListasComunes(model, session);
		model.addAttribute("operation", "reg");
		model.addAttribute("model", true);
		return "inicioPoaReqExperiencia";
	}

	@PostMapping("/registrarPoaRequisitoExperiencia")
	public String registrarRequisitoExperiencias(@Valid PoaisRequisitosExperiencias poaisRequisitosExperiencias,
			BindingResult result, HttpSession session, Model model, @RequestParam(name = "descripcion", required = false) String descripcion)
			throws IOException {

		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));
		
		if (result.hasErrors()) {
			getDesplegarListasComunes(model, session);
			model.addAttribute("operation", "reg");
			return "inicioPoaReqExperiencia";
		}

		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year, 1);
		Poais poais = poaServicios.buscarPoaisPorIdPnlPersonalAdministrativoGestionIdEstado(
				pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo(), year, "A");
		PoaisRequisitos poaisRequisitos = poaServicios.buscarPoaisRequisitosPorIdPoai(poais.getIdPoai());

		if (poaisRequisitos == null) {
			poaisRequisitos = new PoaisRequisitos();
			if (descripcion != null) {
				poaisRequisitos.setRequisito(descripcion);
			}
			poaisRequisitos.setPoais(poais);
			poaServicios.registrarPoaisRequisitos(poaisRequisitos);

			poaisRequisitosExperiencias.setPoaisRequisitos(poaisRequisitos);
			poaServicios.registrarPoaisRequisitosExperiencias(poaisRequisitosExperiencias);
		}

		poaisRequisitosExperiencias.setPoaisRequisitos(poaisRequisitos);
		poaServicios.registrarPoaisRequisitosExperiencias(poaisRequisitosExperiencias);

		getDesplegarListasComunes(model, session);
		model.addAttribute("lRExperiencias",
				poaServicios.listarRequisitosExperienciasPorIdRequisitos(poaisRequisitos.getIdRequisito()));
		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");
		return "inicioPoaReqExperiencia";

	}

	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));
		
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year, 1);
		model.addAttribute("lSupervisores", poaServicios.listarPoaisSupervisores());
		model.addAttribute("lCargos", aServicios.listarPnlCargos());
		model.addAttribute("urlN", "inicioRequiExperiencias");
		model.addAttribute("urlM", "inicioModificarComprobante");
		model.addAttribute("urlD", "inicioEliminarComprobante");
		model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
		model.addAttribute("bPnl", pnlPersonalAdministrativos);
		model.addAttribute("lPersonal", pServicios.listarPersonalAdministrativoPorGestion(year));
		model.addAttribute("regPoais", "registrarPoaRequisitoExperiencia");
		model.addAttribute("urlMod", "lanzarFormModificar");
	}
}
