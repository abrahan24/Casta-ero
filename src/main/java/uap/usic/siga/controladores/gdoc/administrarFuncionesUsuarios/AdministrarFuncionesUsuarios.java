package uap.usic.siga.controladores.gdoc.administrarFuncionesUsuarios;

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
import uap.usic.siga.entidades.GdocUsrTiposFunciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;

@Controller
@RequestMapping("/funcionesuser")

public class AdministrarFuncionesUsuarios {
	
	@Autowired
	private GdocServicios gServicios;;

	@Autowired
	private PersonasServicios pServicios;;

	@Autowired
	private UsuariosServicios uServicio;
	
	@Autowired
	private MenuesServicios uMenuesServicios;
	
	@GetMapping("/inicioFuncionesUsuarios")
	public String inicioFuncionesUsuarios(Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "funcionesuser";
	}

	// ========= Muestra Formulario ===================
	@PostMapping(value = "/inicioFormFuncionesUsuarios")
	public String inicioFormFuncionesUsuarios(@ModelAttribute("gdocUsrTiposFunciones") GdocUsrTiposFunciones gdocUsrTiposFunciones, Model model,
			HttpSession session) {
		getDesplegarListasComunes(model, session);
		model.addAttribute("operation", "reg");
		return "funcionesuser";
	}

	@PostMapping(value = "/registroFuncionesUsuarios")
	public String registrarFuncionesUsuarios(@Valid @ModelAttribute("gdocUsrTiposFunciones") GdocUsrTiposFunciones gdocUsrTiposFunciones, BindingResult result,
			HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			getDesplegarListasComunes(model, session);
			model.addAttribute("operation", "reg");
			return "funcionesuser";
		}

		gServicios.guardarGdocUsrTiposFunciones(gdocUsrTiposFunciones);

		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "funcionesuser";
	}
	
	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion(
				(Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
		Integer i = 3;
		Long l = new Long(i);
		String idEstado = "A";
		model.addAttribute("lFuncionesUsuarios", gServicios.listarGdocUsrTiposFuncionesPorIdGdocConsejo());
		model.addAttribute("lConsejos", gServicios.listarGdocConsejos());
		model.addAttribute("lUsuarios", uServicio.listarUsuariosRegistrados());
		model.addAttribute("lMnuTiposFuncion", uMenuesServicios.listarMnuTiposFunciones());
		model.addAttribute("bGdocConsejos", gdocConsejos);
		model.addAttribute("regFuncionesUsr", "registroFuncionesUsuarios");
		model.addAttribute("urlNuevo", "inicioFormFuncionesUsuarios");
	}
}
