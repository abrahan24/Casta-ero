package uap.usic.siga.controladores.escrutinio.administrarMesasSufragio;

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
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.entidades.EscMesasHabilitadas;
import uap.usic.siga.entidades.EscrutinioActas;
import uap.usic.siga.servicios.EscrutinioServicios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
*
* @author Freddy Morales
*/
@Controller
@RequestMapping("/mesas")
public class AdministrarMesasSufragio {

	 @Autowired
	 private EscrutinioServicios eServicios;
	 
	 @Autowired
	 private InstitucionesServicios iServicios;
	 
	 @GetMapping(value ="/inicioMesasSufragio")
	   public String formInicioMesasSufragio(HttpSession session, Model model) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("lMesasHabilitadas", eServicios.listarMesasSufragioHabilitadas((Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo")));
	        model.addAttribute("busqueda", "find");
	        model.addAttribute("operation", "table");
	        return "formMesasSufragio";
	    }
	 
	  @PostMapping(value = "/inicioFormMesaSufragio")
	    public String inicioFormEscrutinio(@ModelAttribute("escMesasHabilitadas")EscMesasHabilitadas escMesasHabilitadas ,Model model, HttpSession session) {
		      getDesplegarListasComunes(model, session);
	          model.addAttribute("operation", "reg");
	          return "formMesasSufragio";
	    }
	
	  @PostMapping(value = "/registroEscMesasHabilitadas")
	    public String registrarEscrutinioMesasHabilitadas(@Valid EscMesasHabilitadas escMesasHabilitadas, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {
		   if (result.hasErrors()) {
	            getDesplegarListasComunes(model, session);
	             model.addAttribute("operation", "reg");
		         return "formMesasSufragio";
	        }
		    EscMesasHabilitadas escMesasHabilitadas2 = eServicios.registrarEscrutinioMesasHabilitadas(escMesasHabilitadas);
		    
		    getDesplegarListasComunes(model, session);
             model.addAttribute("operation", "reg");
	        return "formMesasSufragio";
      }
	  
	  public void getDesplegarListasComunes(Model model, HttpSession session) {
		  Integer idUniv = 1;
		  Long idUniversidad = new Long(idUniv);
		  model.addAttribute("lFacultades", iServicios.listarFacultadesPorIdUniversidad(idUniversidad));
		  model.addAttribute("lEstamentos",iServicios.listarEstamentos());
		  model.addAttribute("lElecciones", eServicios.listarEleccionesPorGestionPeriodoL(2021, 2)); 
		  model.addAttribute("urlforms", "formEscrutinioActa");
		  model.addAttribute("urlform", "inicioFormMesaSufragio");
		  model.addAttribute("urlRegM", "registroEscMesasHabilitadas");
	  }
}
