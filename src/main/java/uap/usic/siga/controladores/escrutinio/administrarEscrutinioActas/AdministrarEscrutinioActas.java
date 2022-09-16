package uap.usic.siga.controladores.escrutinio.administrarEscrutinioActas;

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

import uap.usic.siga.entidades.EscDetalles;
import uap.usic.siga.entidades.EscFrentes;
import uap.usic.siga.entidades.EscrutinioActas;
import uap.usic.siga.entidades.SacPrestamosComprobantes;
import uap.usic.siga.servicios.EscrutinioServicios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/escrutinio")
public class AdministrarEscrutinioActas {

	 @Autowired
	 private EscrutinioServicios eServicios;
	 
	 @Autowired
	 private InstitucionesServicios iServicios;
	 
	 @GetMapping("/inicioEscrutinioActas")
	    public String formInicioEscrutinio(HttpSession session, Model model) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("lEscrutinios", eServicios.listarEscrutinioAcatasPorGestionPeriodo((Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo")));
	        model.addAttribute("busqueda", "find");
	        model.addAttribute("operation", "table");
	        return "formEscrutinioActas";
	    }
	  
	   @PostMapping(value = "/inicioFormEscrutinio")
	    public String inicioFormEscrutinio(Model model, HttpSession session) {
		    
		     Integer idUniv = 1;
			  Long idUniversidad = new Long(idUniv);
			  model.addAttribute("lFacultades", iServicios.listarFacultadesPorIdUniversidad(idUniversidad));
			  model.addAttribute("lEstamentos",iServicios.listarEstamentos());
			  model.addAttribute("lElecciones", eServicios.listarEleccionesPorGestionPeriodoL(2021, 2)); 
		      getDesplegarListasComunes(model, session);
	          model.addAttribute("operation", "listfcl");
	          return "formEscrutinioActas";
	    }
	   
	   @PostMapping(value = "/formEscrutinioActa") 
	    public String buscarFormEscrutinio(@ModelAttribute("escrutinioActas") EscrutinioActas escrutinioActass,
	    		                                                       @RequestParam("idFacultad") Long idFacultad,
	    																@RequestParam("idEleccion") Long idEleccion,
	    																@RequestParam("idEstamento") Long idEstamento,  Model model, HttpSession session) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("lMesasHabilitadas", eServicios.listarMesasHabilitadasPorIdEleccionIdFacultadIdEstamento(idEleccion, idFacultad, idEstamento));
	        model.addAttribute("lFrentes", eServicios.listarFrentesPorIdFacultadIdEleccion(idFacultad, idEstamento, idEleccion));
	        model.addAttribute("idFacultad", idFacultad);
	        model.addAttribute("idEleccion", idEleccion);
	        model.addAttribute("idEstamento", idEstamento);
	        model.addAttribute("operation", "reg");
	         return "formEscrutinioActas";
	    }
	   
	   @PostMapping(value = "/registroEscrutinioDetalle")
	    public String registrarEscrutinioDetalle(@Valid EscrutinioActas escrutinioActas, BindingResult result, HttpSession session, Model model, HttpServletRequest request,
	            			@RequestParam("idFrente") Long idFrente[], 
	            			@RequestParam("idFacultad") Long idFacultad,
							@RequestParam("idEleccion") Long idEleccion,
							@RequestParam("idEstamento") Long idEstamento) throws IOException {
		   if (result.hasErrors()) {
	            getDesplegarListasComunes(model, session);
	            model.addAttribute("operation", "reg");
	            model.addAttribute("lMesasHabilitadas", eServicios.listarMesasHabilitadasPorIdEleccionIdFacultadIdEstamento(idEleccion, idFacultad, idEstamento));
		        model.addAttribute("lFrentes", eServicios.listarFrentesPorIdFacultadIdEleccion(idFacultad, idEstamento, idEleccion));
		        model.addAttribute("idFacultad", idFacultad);
		        model.addAttribute("idEleccion", idEleccion);
		        model.addAttribute("idEstamento", idEstamento);
		         return "formEscrutinioActas";
	        }
		   
		   EscrutinioActas escrutinioActas2 = eServicios.registrarEscrutinioActas(escrutinioActas);
		   
		   for(int j=0; j<idFrente.length; j++) {
			   EscDetalles escDetalles = new EscDetalles();
			   EscFrentes escFrentes = eServicios.buscarFrentePorIdFrente(idFrente[j]);
			   String votosF = "votosFrente"+idFrente[j];
			   votosF =  request.getParameter(votosF);
			   Double votosFrente = Double.parseDouble(votosF);
			   escDetalles.setEscrutinioActas(escrutinioActas2);
			   escDetalles.setEscFrentes(escFrentes);
			   escDetalles.setVotoFrente(votosFrente);
			   escDetalles.setGestion(escrutinioActas2.getGestion());
			   escDetalles.setPeriodo(escrutinioActas2.getPeriodo());
			   escDetalles = eServicios.registrarEscrutinioDetalles(escDetalles);
		   }
		   
		   getDesplegarListasComunes(model, session);
		   model.addAttribute("operation", "reg");
		    model.addAttribute("lMesasHabilitadas", eServicios.listarMesasHabilitadasPorIdEleccionIdFacultadIdEstamento(idEleccion, idFacultad, idEstamento));
		    model.addAttribute("lFrentes", eServicios.listarFrentesPorIdFacultadIdEleccion(idFacultad, idEstamento, idEleccion));
		    model.addAttribute("idFacultad", idFacultad);
	        model.addAttribute("idEleccion", idEleccion);
	        model.addAttribute("idEstamento", idEstamento);
	       return "formEscrutinioActas";
	   }
	   
	  public void getDesplegarListasComunes(Model model, HttpSession session) {
		  
		  model.addAttribute("urlforms", "formEscrutinioActa");
		  model.addAttribute("urlform", "inicioFormEscrutinio");
		  model.addAttribute("regEstr", "registroEscrutinioDetalle");
	  }
}
