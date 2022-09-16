package uap.usic.siga.controladores.poais.administrarRequisitos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Year;
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
import uap.usic.siga.entidades.PoaisRequisitosCumplimientos;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;

/**
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/poaisReqCumple")
public class AdministrarRequisitosCumplimientos {

	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public PoaisServicios poaServicios;

	@Autowired
	private PersonasServicios pServicios;

	@GetMapping("/inicioReqCumple")
	public String inicioRequisitoCumplimiento(HttpSession session, Model model) {
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
		model.addAttribute("lRCumplimiento",
				poaServicios.listarPoaisRequisitosCumplimientosPorIdRequisito(poaisRequisitos.getIdRequisito()));
		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");
		return "requiCumplimiento";
	}

	@PostMapping("/inicioRequiCumplimiento")
	public String InicioformIdentificacion(
			@ModelAttribute("poaisRequisitosCumplimientos") PoaisRequisitosCumplimientos poaisRequisitosCumplimientos,
			HttpSession session, Model model) {

		getDesplegarListasComunes(model, session);
		model.addAttribute("operation", "reg");
		model.addAttribute("model", true);
		return "requiCumplimiento";
	}

	@PostMapping("/registrarRequiCumple")
	public String registrarRequisitoCualidad(@Valid PoaisRequisitosCumplimientos poaisRequisitosCumplimientos,
			BindingResult result, HttpSession session, Model model, @RequestParam(name = "descripcion", required = false) String descripcion)
			throws IOException {

		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));
		
		if (result.hasErrors()) {
			getDesplegarListasComunes(model, session);
			model.addAttribute("operation", "reg");
			return "requiCumplimiento";
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

			poaisRequisitosCumplimientos.setPoaisRequisitos(poaisRequisitos);
			poaServicios.registrarPoaisRequisitosCumplimientos(poaisRequisitosCumplimientos);
		}

		poaisRequisitosCumplimientos.setPoaisRequisitos(poaisRequisitos);
		poaServicios.registrarPoaisRequisitosCumplimientos(poaisRequisitosCumplimientos);

		getDesplegarListasComunes(model, session);
		if (poaisRequisitos != null) {
			model.addAttribute("lRCumplimiento",
					poaServicios.listarPoaisRequisitosCumplimientosPorIdRequisito(poaisRequisitos.getIdRequisito()));
		}
		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");
		return "requiCumplimiento";

	}

	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));
		
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year, 1);
		model.addAttribute("lTipoCumplimiento", poaServicios.listarPoaisTiposCumplimientos());
		model.addAttribute("lCargos", aServicios.listarPnlCargos());
		model.addAttribute("urlN", "inicioRequiCumplimiento");
		model.addAttribute("urlM", "inicioModificarComprobante");
		model.addAttribute("urlD", "inicioEliminarComprobante");
		model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
		model.addAttribute("bPnl", pnlPersonalAdministrativos);
		model.addAttribute("lPersonal", pServicios.listarPersonalAdministrativoPorGestion(2021));
		model.addAttribute("regPoais", "registrarRequiCumple");
		model.addAttribute("urlMod", "lanzarFormModificar");
	}

}
