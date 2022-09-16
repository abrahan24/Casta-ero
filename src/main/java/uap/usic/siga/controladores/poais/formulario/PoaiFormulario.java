package uap.usic.siga.controladores.poais.formulario;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.Poais;
import uap.usic.siga.entidades.PoaisObjetivos;
import uap.usic.siga.entidades.PoaisRequisitos;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.CargosServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;

@Controller
@RequestMapping("/poaiFormulario")
public class PoaiFormulario {
	
	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public PoaisServicios poaServicios;

	@Autowired
	private PersonasServicios pServicios;
	
	@GetMapping("/")
	public String formulario(HttpSession session, Model model) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));
		
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), year, 1);
		getDesplegarListasComunes(model, session);		
		
		model.addAttribute("lPoais", poaServicios.listarPoaisPorIdPnlPersonalAdministrativo(pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo(), year));
		model.addAttribute("persona", personas);
		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");
		
		System.out.println("holsssss");
		
		return "poaiFormulario";
	}
	
	@GetMapping("/generar/{idPoai}")
	public String generar(HttpSession session, Model model, @PathVariable("idPoai")Long idPoai) {
		System.out.println("aquiiiiiiii 2");
		
		Personas persona = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		Date date = new Date();

		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYearFormat.format(date));
		
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(persona.getIdPersona(), year, 1);
		Poais poais = poaServicios.getPoai(idPoai);
		PoaisRequisitos poaisRequisitos = poaServicios.buscarPoaisRequisitosPorIdPoai(poais.getIdPoai());

		model.addAttribute("administrativo", pnlPersonalAdministrativos);
		model.addAttribute("persona", persona);
		model.addAttribute("poai", poais);
		model.addAttribute("plnISuperior", aServicios.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(poaServicios.getPoaiIdentificacionIdPoai(poais.getIdPoai()).getPoaisSupervisores().getPersonas().getIdPersona(), year, 1));
		model.addAttribute("identificacion", poaServicios.getPoaiIdentificacionIdPoai(poais.getIdPoai()));
		model.addAttribute("objetivo", poaServicios.getPoaiObjetivoIdPoai(poais.getIdPoai()));
		
		model.addAttribute("formaciones", poaServicios.listRequisitosFormacionesPorIdRequisito(poaisRequisitos.getIdRequisito()));
		model.addAttribute("experiencias", poaServicios.listarRequisitosExperienciasPorIdRequisitos(poaisRequisitos.getIdRequisito()));
		model.addAttribute("cumplimientos", poaServicios.listarPoaisRequisitosCumplimientosPorIdRequisito(poaisRequisitos.getIdRequisito()));
		model.addAttribute("cualidades", poaServicios.listarRequisitosCualidadesPorIdRequisito(poaisRequisitos.getIdRequisito()));
		
		
		
		return "generarPoai";
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
		model.addAttribute("regPoais", "generarPoaiFormulario");
		model.addAttribute("urlMod", "lanzarFormModificar");
	}

}
