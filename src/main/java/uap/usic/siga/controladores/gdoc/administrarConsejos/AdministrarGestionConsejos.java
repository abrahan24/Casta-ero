package uap.usic.siga.controladores.gdoc.administrarConsejos;

import java.io.IOException;

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

import uap.usic.siga.entidades.GdocConsejos;
import uap.usic.siga.entidades.GdocGestionConsejos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;

@Controller
@RequestMapping("/gestionconsejos")
public class AdministrarGestionConsejos {
	@Autowired
	private GdocServicios gServicios;;

	@Autowired
	private PersonasServicios pServicios;;

	@Autowired
	private UsuariosServicios uServicio;
	
	@GetMapping("/inicioGestionConsejos")
	public String inicioGestionConsejos(Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "gestionconsejos";
	}

	// ========= Muestra Formulario ===================
	@PostMapping(value = "/inicioFormGestionConsejos")
	public String inicioFormGestionConsejos(@ModelAttribute("gdocGestionConsejos") GdocGestionConsejos gdocGestionConsejos, Model model,
			HttpSession session) {
		getDesplegarListasComunes(model, session);
		model.addAttribute("operation", "reg");
		return "gestionconsejos";
	}

	@PostMapping(value = "/registroGestionConsejos")
	public String registrarGestionConsejos(@Valid @ModelAttribute("gdocGestionConsejos") GdocGestionConsejos gdocGestionConsejos, BindingResult result,
			HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			getDesplegarListasComunes(model, session);
			model.addAttribute("operation", "reg");
			return "gestionconsejos";
		}

		gServicios.guardarGdocGestionConsejosSET(gdocGestionConsejos);

		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "gestionconsejos";
	}
	
	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		//GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion(
				//(Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
		Integer i = 3;
		Long l = new Long(i);
		String idEstado = "A";
		model.addAttribute("lGestionConsejos", gServicios.listarGdocGestionConsejosPorIdGdocConsejo());
		model.addAttribute("lConsejos", gServicios.listarGdocConsejos());
		//model.addAttribute("bGdocConsejos", gdocConsejos);
		model.addAttribute("regGestionConsejo", "registroGestionConsejos");
		model.addAttribute("urlNuevo", "inicioFormGestionConsejos");
	}
}
