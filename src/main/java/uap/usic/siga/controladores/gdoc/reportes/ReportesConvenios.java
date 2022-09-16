package uap.usic.siga.controladores.gdoc.reportes;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import uap.usic.siga.entidades.GdocConsejos;
import uap.usic.siga.entidades.GdocConvenios;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;

@Controller
@RequestMapping("/repConvenios")
public class ReportesConvenios {

	@Autowired
	private GdocServicios gServicios;;
	
	@Autowired
	private PersonasServicios pServicios;;
	
	@Autowired
	private UsuariosServicios uServicio;
	
	@Autowired
	private InstitucionesServicios iServicios;
	
	  @GetMapping("/inicioReportConvenios")
	   public String inicioReporteConvenios(Model model, HttpSession session){
		    model.addAttribute("busqueda", "find");
		    model.addAttribute("lTiposConvenios", gServicios.listarGdocTiposConvenios());
		    getDesplegarListasComunes(model, session);
	        return "reportConvenios";   
	    }
	
	   @PostMapping("/imprimirReporteConvenios")
	    public String imprimirReporteConvneios(@RequestParam("idGdocTipoConvenio") Long idGdocTipoConvenio, HttpServletRequest request, Model model, HttpSession session) throws IOException {

		   String gestionT = "gestion" + idGdocTipoConvenio;
	       gestionT = request.getParameter(gestionT);
	       Integer gestion = Integer.parseInt(gestionT);
		   model.addAttribute("id", idGdocTipoConvenio);
	        model.addAttribute("gestion", gestion);
	        model.addAttribute("lConvneios", gServicios.listarGdocConveniosPorIdGdocTipoCovenioGestion(idGdocTipoConvenio, gestion));
	        model.addAttribute("busqueda", "find");
	          getDesplegarListasComunes(model, session);
	        return "imprimirReportesConvenios2";
	    }
	   
	   @PostMapping(value = "/imprimirConvenio")
	    public String imprimirResolucion(@RequestParam("idGdocTipoConvenio") Long idGdocTipoConvenio,
	    		@RequestParam("gestion") Integer gestion, Model model, HttpSession session)  {
		   model.addAttribute("bConvneios", gServicios.buscarGdocConveniosPorIdGdocTipoConvenioGestion(idGdocTipoConvenio, gestion));
	       return "imprimirReporteConvenios";
	    }
 
	   

	  public void getDesplegarListasComunes(Model model, HttpSession session) {
	        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
	        GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion((Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
	        String idEstado = "A";
	        model.addAttribute("lAutoridades", gServicios.listarAutoridades(gdocConsejos.getIdGdocConsejo()));
	        model.addAttribute("lConvenios", gServicios.listarGdocConveniosPorIdGdocConsejo(gdocConsejos.getIdGdocConsejo()));
	        model.addAttribute("bGdocConsejos", gdocConsejos);
	        model.addAttribute("lRepresentante", gServicios.listarGdocRepresentante());
	        model.addAttribute("lTiposConvenios", gServicios.listarGdocTiposConvenios());
	        model.addAttribute("lInstituciones", iServicios.listarInstituciones());
	        model.addAttribute("urlList", "imprimirReporteConvenios");
	        model.addAttribute("urlMod", "inicioModificarConvenios");
	        model.addAttribute("urlConfirmarMod", "confirmarModificacionIngresos");
	        model.addAttribute("urlEliminar", "eliminarIngresosFondos");
	        model.addAttribute("urlRIn", "inicioFormConvenios");
	        model.addAttribute("urlEI", "inicioEliminarConvenios");
	        model.addAttribute("urlCEI", "confirmarEliminarIngresos");
	        model.addAttribute("urlClose", "inicioCerrarCaja");
	        model.addAttribute("impRepCnv", "imprimirConvenio");
	     }

}
