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
import uap.usic.siga.entidades.PoaisObjetivos;
import uap.usic.siga.entidades.PoaisResultados;
import uap.usic.siga.entidades.SacComprobantes;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.CargosServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;

@Controller
@RequestMapping("/poaiObjetivo")
public class AdministrarObjetivoContinuo {

	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public PoaisServicios poaiServicios;

	@Autowired
	private PersonasServicios pServicios;
	
	@GetMapping("/AdministrarObjetivoContinuo")
	public String formInicioIdentificacion(HttpSession session, Model model, RedirectAttributes flash) {
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

		// model.addAttribute("pnlCargos", pnlPersonalAdministrativos.getPnlCargos());
		model.addAttribute("lPoaisObjetivos", poaiServicios.listarPoaisObjetivosPorIdPersonalAdministrativo(
				pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo()));
		model.addAttribute("poaisObjetivos", poaisObjetivos);

		model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");

		/*
		 * model.addAttribute("lPoais",
		 * poaiServicios.listarPoaisPorIdPnlPersonalAdministrativo(
		 * pnlPersonalAdministrativos.getIdPnlPersonalAdministrativo(), 2021));
		 * model.addAttribute("lSupervisores", poaiServicios.listarPoaisSupervisores());
		 */

		return "inicioObjetivoContinio";
	}
	
	@PostMapping("/inicioEliminarContinuoObjetivo")
	public String inicioEliminarObjetivo(@RequestParam("idResultado")Long idResultado,Model model, HttpSession session, RedirectAttributes flash) {
		
		PoaisResultados poaisResultados = poaiServicios.getPoaisResultados(idResultado);
		poaisResultados.setIdEstado("X");
		
		poaiServicios.registrarPoaisResultados(poaisResultados);
		
		flash.addAttribute("success", "Resultado Eliminado con Exito!");
		
		return "redirect:/poaiObjetivo/AdministrarObjetivoContinuo";
	}

	@PostMapping("/inicioObjetivoContinuoNuevo")
	public String inicioObjetivo(@ModelAttribute("pnlCargos") PnlCargos pnlCargos,
			@ModelAttribute("poaisResultados") PoaisResultados poaisResultados,
			@ModelAttribute("poaisActividades") PoaisActividades poaisActividades,
			@RequestParam(name = "idObjetivo") Long idObjetivo, Model model, HttpSession session) {

		getDesplegarListasComunes(model, session);
		model.addAttribute("poaisObjetivos", poaiServicios.getPoaiObjetivos(idObjetivo));
		model.addAttribute("operation", "reg");
		model.addAttribute("modal", true);

		return "inicioObjetivoContinio";
	}

	@PostMapping("/registrarPoaisObjetivoContinuo")
	public String registrarResultados(@Valid PoaisObjetivos poaisObjetivos, BindingResult result,RedirectAttributes flash, HttpSession session,
			Model model, HttpServletRequest request, @RequestParam("objetivoResultado") String resultado[],
			@RequestParam("objetivoPonderacion") Double ponderacion[]) {

		poaisObjetivos = poaiServicios.getPoaiObjetivos(poaisObjetivos.getIdObjetivo());
		
		for (int i = 0; i < ponderacion.length; i++) {
			
			PoaisResultados poaisResultados = new PoaisResultados();
			
			poaisResultados.setPoaisObjetivos(poaisObjetivos);
			poaisResultados.setIdEstado("A");
			poaisResultados.setTipo("C");
			poaisResultados.setResultado(resultado[i]);
			poaisResultados.setPonderacion(ponderacion[i]);
			
			poaiServicios.registrarPoaisResultados(poaisResultados);
		}
		
		flash.addAttribute("success", "Resultados Continuos Registrados con Exito!");
		
		return "redirect:/poaiObjetivo/AdministrarObjetivoContinuo";
	}

	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), 2021, 1);
		model.addAttribute("lSupervisores", poaiServicios.listarPoaisSupervisores());
		model.addAttribute("lCargos", aServicios.listarPnlCargos());
		model.addAttribute("urlN", "inicioObjetivoContinuoNuevo");
		model.addAttribute("urlM", "inicioModificarContinuoObjetivo");
		model.addAttribute("urlD", "inicioEliminarContinuoObjetivo");
		model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
		model.addAttribute("bPnl", pnlPersonalAdministrativos);
		model.addAttribute("lPersonal", pServicios.listarPersonalAdministrativoPorGestion(2021));
		model.addAttribute("regPoais", "registrarPoaisObjetivoContinuo");
		model.addAttribute("urlMod", "lanzarFormModificar");
	}
	

}
