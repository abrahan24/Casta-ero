package uap.usic.siga.controladores.escrutinio.administrarFrentesEleccion;

import java.io.IOException;

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

import uap.usic.siga.entidades.EscFrentes;
import uap.usic.siga.entidades.EscMesasHabilitadas;
import uap.usic.siga.servicios.EscrutinioServicios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
*
* @author Freddy Morales
*/
@Controller
@RequestMapping("/frentes")
public class AdministrarFrentesEleccion {

	 @Autowired
	 private EscrutinioServicios eServicios;
	 
	 @Autowired
	 private InstitucionesServicios iServicios;
	 
	 @GetMapping(value= "/inicioFrentesEleccion")
	   public String formInicioFrentesEleccion(HttpSession session, Model model) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("lFrentes", eServicios.listarEscFrentesPorGestionPeriodo((Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo")));
	        model.addAttribute("busqueda", "find");
	        model.addAttribute("operation", "table");
	        return "formFrentesEleccion";
	    }
	 
	  @PostMapping(value = "/inicioFormFrentes")
	    public String inicioFormEscrutinio(@ModelAttribute("escFrentes") EscFrentes escFrentes ,Model model, HttpSession session) {
		      getDesplegarListasComunes(model, session);
	          model.addAttribute("operation", "reg");
	          model.addAttribute("yo", "Algo");
	          return "formFrentesEleccion";
	    }
	
	   @PostMapping(value = "/registroFrentesEleccion")
	    public String registrarFrentesEleccion(@Valid EscFrentes escFrentes, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {
		   if (result.hasErrors()) {
	            getDesplegarListasComunes(model, session);
	             model.addAttribute("operation", "reg");
	             return "formFrentesEleccion";
	        }
		  //  EscFrentes escFrentes2 =
		   eServicios.registrarFrentesEleccion(escFrentes);
		    model.addAttribute("yo", "Yo soy el yo soy");
		    getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
	        return "formFrentesEleccion";
    }
	  
	 public void getDesplegarListasComunes(Model model, HttpSession session) {
		  Integer idUniv = 1;
		  Long idUniversidad = new Long(idUniv);
		  model.addAttribute("lFacultades", iServicios.listarFacultadesPorIdUniversidad(idUniversidad));
		  model.addAttribute("lEstamentos",iServicios.listarEstamentos());
		  model.addAttribute("lElecciones", eServicios.listarEleccionesPorGestionPeriodoL(2021, 2)); 
		  model.addAttribute("urlforms", "formFrentesEleccion");
		  model.addAttribute("urlform", "inicioFormFrentes");
		  model.addAttribute("urlreg", "registroFrentesEleccion");
	  }
}
