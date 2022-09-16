package uap.usic.siga.controladores.congreso.administrarCongresistas;

import java.io.IOException;
import java.util.List;

import javax.management.AttributeValueExp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import uap.usic.siga.entidades.CngCongresistas;
import uap.usic.siga.entidades.EscFrentes;
import uap.usic.siga.entidades.FclCarreras;
import uap.usic.siga.entidades.InsDireccionesFuncionales;
import uap.usic.siga.servicios.CongresoServicios;
import uap.usic.siga.servicios.EscrutinioServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
*
* @author Freddy Morales
*/
@Controller
@RequestMapping("/congresista")
public class AdministrarCongresistas {
	
	 @Autowired
	 private EscrutinioServicios eServicios;
	 
	 @Autowired
	 private InstitucionesServicios iServicios;
	 
	 @Autowired
	 private CongresoServicios cServicios; 
	 
	 @Autowired
	 private PersonasServicios pServicios;
	 
	 @GetMapping("/inicioCongresistas")
	    public String formInicioCongresistas(HttpSession session, Model model) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("lCongresistas", cServicios.listarCongresistasPorGestion(2021));
	         model.addAttribute("busqueda", "find");
	        model.addAttribute("operation", "table");
	        return "formCongresistas";
	    }

	 @PostMapping(value = "/inicioFormCongresistas")
	    public String inicioFormEscrutinio(@ModelAttribute("cngCongresistas") CngCongresistas cngCongresistas,Model model, HttpSession session) {
     		 Integer idUniv = 1;
		     Long  idUniversidad = new Long(idUniv);
		     getDesplegarListasComunes(model, session);
		      model.addAttribute("lTiposCongresistas",  cServicios.listarTiposCongresistas());
		      model.addAttribute("lCongresoUap", cServicios.listarCongresosGeneral());
		      model.addAttribute("lFacultades", iServicios.listarFacultadesPorIdUniversidad(idUniversidad));
		      model.addAttribute("lFrentes", eServicios.listarEscFrentesPorGestionPeriodo(2021, 2));
		      model.addAttribute("lPersonas", pServicios.listarPersonas());
		      model.addAttribute("lEstamentos", iServicios.listarEstamentos());
		      model.addAttribute("operation", "reg");
	           return "formCongresistas";
	    }
	 
	  @RequestMapping(value = "/lCarreras/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody
	    List<FclCarreras> getListarCarreras(@PathVariable("id") long id) {
	        return iServicios.listarCarrerasPorIdFacultad(id);
	    }
	 
	 @PostMapping(value = "/registroCongresistas")
	    public String registrarCongresita(@Valid CngCongresistas cngCongresistas, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {
		   if (result.hasErrors()) {
	            getDesplegarListasComunes(model, session);
	             model.addAttribute("operation", "reg");
	             return "formCongresistas";
	        }
		    cServicios.registrarCongresistas(cngCongresistas);
		    getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
	        return "formCongresistas";
 }
	 
	 @PostMapping(value = "/lanzarFormModificar")
	    public String lanzarFormModificar(@ModelAttribute("cngCongresistas") CngCongresistas cngCongresistas,Model model, HttpSession session,
	    																@RequestParam("idCngCongresista") Long idCngCongresista) {
  		     Integer idUniv = 1;
		     Long  idUniversidad = new Long(idUniv);
		     getDesplegarListasComunes(model, session);
		     model.addAttribute("cngCongresistas", cServicios.buscarCngCongresistasPorIdCngCongresistas(idCngCongresista));
		      model.addAttribute("lTiposCongresistas",  cServicios.listarTiposCongresistas());
		      model.addAttribute("lCongresoUap", cServicios.listarCongresosGeneral());
		      model.addAttribute("lFacultades", iServicios.listarFacultadesPorIdUniversidad(idUniversidad));
		      model.addAttribute("lFrentes", eServicios.listarEscFrentesPorGestionPeriodo(2021, 2));
		      model.addAttribute("lPersonas", pServicios.listarPersonas());
		      model.addAttribute("lEstamentos", iServicios.listarEstamentos());
		      model.addAttribute("operation", "mod");
	           return "formCongresistas";
	    }
	
	 
	 public void getDesplegarListasComunes(Model model, HttpSession session) {
		  model.addAttribute("urlforms", "formEscrutinioActa");
		  model.addAttribute("urlform", "inicioFormCongresistas");
		  model.addAttribute("regCng", "registroCongresistas");
		  model.addAttribute("urlMod", "lanzarFormModificar");
	  }
}
