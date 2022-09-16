package uap.usic.siga.controladores.escrutinio.reportesEleccionesMesa;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uap.usic.siga.entidades.FclEstamentos;
import uap.usic.siga.servicios.EscrutinioServicios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
*
* @author Freddy Morales
*/
@Controller
@RequestMapping("/resMesas")
public class ResultadosEscrutinioMesas {

   @Autowired
	 private EscrutinioServicios eServicios;
	 
	 @Autowired
	 private InstitucionesServicios iServicios;
	 
	 @GetMapping(value= "/inicioResultadoMesa")
	   public String formInicioResultadoEleccionMesas(HttpSession session, Model model) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("lEscrutinioActas", eServicios.listarEscrutinioActasResponseGestionPeriodo(2021, 2));
	        model.addAttribute("busqueda", "find");
	        model.addAttribute("operation", "table");
	        return "formResultadoMesas";
	    }
	 
	  @PostMapping(value = "/listarResultadosEle") 
	    public String listarResultadosEleccion(@RequestParam("idFacultad") Long idFacultad,
	    																@RequestParam("idEleccion") Long idEleccion,
	    																@RequestParam("idEstamento") Long idEstamento,  Model model, HttpSession session) {
		  getDesplegarListasComunes(model, session);
		   model.addAttribute("bEstamentos", iServicios.buscarEstamentoPorIdEstamento(idEstamento));  
		   model.addAttribute("lResultadosMesas", eServicios.listarSumatoriaActasPorMesasIdFacultadIdEstamentoIdEleccionGestion(idFacultad, idEleccion, idEstamento, 2021));
		   model.addAttribute("lResultadosSufragio", eServicios.listarSumatoriaActasPorIdFacultadIdEstamentoIdEleccionGestion(idFacultad, idEleccion, idEstamento, 2021));
	        model.addAttribute("operation", "lsum");
	        model.addAttribute("idFacultad", idFacultad);
	        model.addAttribute("idEleccion", idEleccion);
	        model.addAttribute("idEstamento", idEstamento);
	         return "formResultadoMesas";
	    }
	  
	  @PostMapping(value = "/imprimirResultadosGraficosMesas") 
	    public String imprimirResultadosEleccion(@RequestParam("idFacultad") Long idFacultad,
	    																@RequestParam("idEleccion") Long idEleccion,
	    																@RequestParam("idEstamento") Long idEstamento,  Model model, HttpSession session) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("bFacultad", iServicios.buscarFacultadPorIdFaculta(idFacultad));
	        model.addAttribute("bEstamento", iServicios.buscarEstamentoPorIdEstamento(idEstamento));
	        model.addAttribute("bEleccion", eServicios.buscarEleccionPorIdEleccion(idEleccion));
			model.addAttribute("lResultadosMesas", eServicios.listarSumatoriaActasPorMesasIdFacultadIdEstamentoIdEleccionGestion(idFacultad, idEleccion, idEstamento, 2021));
			model.addAttribute("lResultadosSufragio", eServicios.listarSumatoriaActasPorIdFacultadIdEstamentoIdEleccionGestion(idFacultad, idEleccion, idEstamento, 2021));
	        model.addAttribute("operation", "lsum");
	        model.addAttribute("idFacultad", idFacultad);
	        model.addAttribute("idEleccion", idEleccion);
	        model.addAttribute("idEstamento", idEstamento);
	        return "uap/usic/siga/escrutinio/resultadosEleccionesMesas/imprimirResultadosEleccionesMesas";
	    }
	
	  
	  public void getDesplegarListasComunes(Model model, HttpSession session) {
		  Integer idUniv = 1;
		  Long idUniversidad = new Long(idUniv);
		  model.addAttribute("lFacultades", iServicios.listarFacultadesPorIdUniversidad(idUniversidad));
		  model.addAttribute("lEstamentos",iServicios.listarEstamentos());
		  model.addAttribute("lElecciones", eServicios.listarEleccionesPorGestionPeriodoL(2021, 2)); 
		  model.addAttribute("lstsum", "listarResultadosEle");
		  model.addAttribute("urlform", "inicioFormFrentes");
		  model.addAttribute("urlreg", "registroFrentesEleccion");
		  model.addAttribute("urlimp", "imprimirResultadosGraficosMesas");
	  }
}