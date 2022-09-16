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
import org.springframework.web.multipart.MultipartFile;

import uap.usic.siga.entidades.GdocArchivosAdjuntos;
import uap.usic.siga.entidades.GdocConsejos;
import uap.usic.siga.entidades.GdocTitulos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/consejos")

public class AdministrarConsejos {

	
	@Autowired
	private GdocServicios gServicios;;

	@Autowired
	private PersonasServicios pServicios;;

	@Autowired
	private UsuariosServicios uServicio;
	
	@GetMapping("/inicioConsejos")
	public String inicioConsejos(Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "consejos";
	}

	// ========= Muestra Formulario ===================
	@PostMapping(value = "/inicioFormConsejos")
	public String inicioFormConsejos(@ModelAttribute("gdocConsejos") GdocConsejos gdocConsejos, Model model,
			HttpSession session) {
		getDesplegarListasComunes(model, session);
		model.addAttribute("operation", "reg");
		return "consejos";
	}

	@PostMapping(value = "/registroConsejos")
	public String registrarConsejos(@Valid @ModelAttribute("gdocConsejos") GdocConsejos gdocConsejos, BindingResult result,
			HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			getDesplegarListasComunes(model, session);
			model.addAttribute("operation", "reg");
			return "consejos";
		}

		gServicios.guardarGdocConsejos(gdocConsejos);

		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "consejos";
	}
	
	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion(
				(Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
		Integer i = 3;
		Long l = new Long(i);
		String idEstado = "A";
		model.addAttribute("lConsejos", gServicios.listarGdocConsejos());
		model.addAttribute("bGdocConsejos", gdocConsejos);
		model.addAttribute("regConsejo", "registroConsejos");
		model.addAttribute("urlNuevo", "inicioFormConsejos");
	}
}
