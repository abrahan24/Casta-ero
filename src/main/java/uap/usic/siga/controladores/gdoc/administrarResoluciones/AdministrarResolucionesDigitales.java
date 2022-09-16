package uap.usic.siga.controladores.gdoc.administrarResoluciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.eclipse.jdt.internal.compiler.apt.util.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import uap.usic.siga.entidades.GdocArchivosAdjuntos;
import uap.usic.siga.entidades.GdocConsejos;
import uap.usic.siga.entidades.GdocGestionConsejos;
import uap.usic.siga.entidades.GdocResoluciones;
import uap.usic.siga.entidades.GdocResolucionesDigitales;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;


@Controller
@RequestMapping("/resolucionDigital")
public class AdministrarResolucionesDigitales {

	@Autowired
	private GdocServicios gServicios;;
	
	@Autowired
	private PersonasServicios pServicios;;
	
	@Autowired
	private UsuariosServicios uServicio;
	
	    @GetMapping("/inicioResol")
	    public String inicioResolucion(Model model, HttpSession session) {
		    model.addAttribute("busqueda", "find");
	        getDesplegarListasComunes(model, session);
	        return "resolucionesDigitales";   
	    }
	  
	  @PostMapping(value = "/inicioFormResolucion")
	    public String inicioFormResolusiones(@ModelAttribute("gdocResolucionesDigitales") GdocResolucionesDigitales gdocResolucionesDigitales, Model model, HttpSession session) {
		   getDesplegarListasComunes(model, session);
	        model.addAttribute("operation", "reg");
	        return "resolucionesDigitales";
	    }

	  @PostMapping(value = "/registroResolucionDigitales")
	  public String registrarResolusciones(@Valid @ModelAttribute("gdocResolucionesDigitales") GdocResolucionesDigitales gdocResolucionesDigitales, BindingResult result, HttpSession session, Model model) { 
	  	    if (result.hasErrors()) {
	            getDesplegarListasComunes(model, session);
	            model.addAttribute("operation", "reg");
	            return "resolucionesDigitales";
	        }

	        gServicios.registrarGdocResolucionesDigitales(gdocResolucionesDigitales);
	         
	         model.addAttribute("busqueda", "find");
		        getDesplegarListasComunes(model, session);
		        return "resolucionesDigitales";   
		    }

	
	  @PostMapping(value = "/generarResolucion")
	    public String generarResolusiones(@RequestParam("idGdocResolucionDigital") Long idGdocResolucionDigital, Model model, HttpSession session)  {
		   getDesplegarListasComunes(model, session);
		   model.addAttribute("bResolucion", gServicios.buscarGdocResolucionesDigitalesPorIdGdocResolucionesDigitales(idGdocResolucionDigital));
	        model.addAttribute("operation", "reg");
	        return "imprimirResolucion";
	    }
  
	  
	  public void getDesplegarListasComunes(Model model, HttpSession session){
	        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
	        GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion((Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
	        GdocGestionConsejos bGestionConsejo = gServicios.buscarGdocGestionConsejosPorIdGdocConsejoGestion(gdocConsejos.getIdGdocConsejo(),  (Integer) session.getAttribute("gestion")); 
	        model.addAttribute("bGestionConsejo", gServicios.buscarGdocGestionConsejosPorIdGdocConsejoGestion(gdocConsejos.getIdGdocConsejo(), (Integer) session.getAttribute("gestion")));
	        model.addAttribute("bGdocConsejos", gdocConsejos);
	        model.addAttribute("gdocResolucionesDigitales",gServicios.buscarMaxGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestion(gdocConsejos.getIdGdocConsejo(), bGestionConsejo.getIdGdocGestionConsejo(), (Integer) session.getAttribute("gestion")));
	        model.addAttribute("lResoluciones", gServicios.listarGdocResolucionesDigitalesPorIdGdocConsejoIdGdocGestionConsejoGestion(gdocConsejos.getIdGdocConsejo(), bGestionConsejo.getIdGdocGestionConsejo(), (Integer) session.getAttribute("gestion")));
	        model.addAttribute("nRsol", "registroResolucionDigitales");
	        model.addAttribute("urlMod", "modificarIngresos");
	        model.addAttribute("urlConfirmarMod", "confirmarModificacionIngresos");
	        model.addAttribute("urlEliminar", "eliminarIngresosFondos");
	        model.addAttribute("urlRIn", "inicioFormResolucion");
	        model.addAttribute("urlEI", "inicioEliminarResolucion");
	        model.addAttribute("urlCEI", "confirmarEliminarIngresos");
	        model.addAttribute("urlM", "inicioModificarResolucion");
	        model.addAttribute("openFile", "openFile");
	        model.addAttribute("openResol", "generarResolucion");
	        
	        
	        
	     }

}
