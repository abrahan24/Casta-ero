package uap.usic.siga.controladores.poais.administrarIdentificacion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uap.usic.siga.dto.PoaisIdentificacionesResponse;
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaTiposGastos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.Poais;
import uap.usic.siga.entidades.PoaisIdentificaciones;
import uap.usic.siga.entidades.PoaisSupervisores;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;

/**
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/poaiIdentificacion")
public class AdministrarIdentificacion {

	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public PoaisServicios poaServicios;

	@Autowired
	private PersonasServicios pServicios;

	@GetMapping("/inicioAdminIdent")
	public String formInicioIdentificacion(HttpSession session, Model model) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));
		
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year, 1);
		getDesplegarListasComunes(model, session);		
		
		model.addAttribute("lPoais", poaServicios.listarPoaisPorIdPnlPersonalAdministrativo(pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo(), year));
		
		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");
		
		return "inicioIdentificacion";
	}

	@PostMapping("/inicioIdentificacionNuevo")
	public String InicioformIdentificacion(
			@ModelAttribute("poaisIdentificaciones") PoaisIdentificaciones poaisIdentificaciones,
			@ModelAttribute("poaisSupervisores") PoaisSupervisores poaisSupervisores, HttpSession session,
			Model model) {

		getDesplegarListasComunes(model, session);
		model.addAttribute("operation", "reg");
		model.addAttribute("modal", true);
		return "inicioIdentificacion";
	}

	@PostMapping("/registrarPoaisIdentificacion")
	public String registrarIdentificacion(@Valid PoaisIdentificaciones poaisIdentificaciones, BindingResult result,
			HttpSession session, Model model, @RequestParam("gestion") Integer gestion,
			@RequestParam(name = "descripcion",required = false) String descripcion) throws IOException {

		if (result.hasErrors()) {
			getDesplegarListasComunes(model, session);
			model.addAttribute("operation", "reg");
			return "inicioIdentificacion";
		}
		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));

		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year, 1);

		Poais poais = new Poais();
		poais.setPnlPersonalAdministrativos(pnlPersonalAdministrativos);
		if (descripcion != null) {
			poais.setDescripcion(descripcion);
		}		
		poais.setGestion(gestion);
		Poais rPoais = poaServicios.registrarPoais(poais);

		poaisIdentificaciones.setPoais(rPoais);
		poaServicios.registrarPoaisIdentificaciones(poaisIdentificaciones);

		getDesplegarListasComunes(model, session);
		model.addAttribute("lPoais", poaServicios.listarPoaisPorIdPnlPersonalAdministrativo(
				pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo(), year));
		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");
		return "inicioIdentificacion";

	}

	@PostMapping(value = "/guardarSupervisor", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<PoaisIdentificacionesResponse> getListarSupervisores(
			@ModelAttribute @Valid PoaisSupervisores poaisSupervisores, BindingResult result) {
		poaServicios.registrarPoaisSupervisores(poaisSupervisores);
		return poaServicios.listarSupervisoresPorGestion(2021);
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
		model.addAttribute("urlN", "inicioIdentificacionNuevo");
		model.addAttribute("urlM", "inicioModificarComprobante");
		model.addAttribute("urlD", "inicioEliminarComprobante");
		model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
		model.addAttribute("bPnl", pnlPersonalAdministrativos);
		model.addAttribute("lPersonal", pServicios.listarPersonalAdministrativoPorGestion(year));
		model.addAttribute("regPoais", "registrarPoaisIdentificacion");
		model.addAttribute("urlMod", "lanzarFormModificar");
	}
}
