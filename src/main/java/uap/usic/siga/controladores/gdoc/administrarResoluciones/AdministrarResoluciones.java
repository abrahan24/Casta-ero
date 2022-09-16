package uap.usic.siga.controladores.gdoc.administrarResoluciones;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import uap.usic.siga.entidades.GdocConvenios;
import uap.usic.siga.entidades.GdocResoluciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/resolucion")
public class AdministrarResoluciones {

	@Autowired
	private GdocServicios gServicios;;
	
	@Autowired
	private PersonasServicios pServicios;;
	
	@Autowired
	private UsuariosServicios uServicio;
	
	  @GetMapping("/inicioResol")
	    public String inicioResolucion(Model model, HttpSession session){
		    model.addAttribute("busqueda", "find");
	        getDesplegarListasComunes(model, session);
	        return "resoluciones";   
	    }
	  
	  @PostMapping(value = "/inicioFormResolucion")
	    public String inicioFormResolusiones(@ModelAttribute("gdocResoluciones") GdocResoluciones gdocResoluciones, Model model, HttpSession session) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("operation", "reg");
	        return "resoluciones";
	    }

	  @PostMapping(value = "/registroResolucion")
	  public String registrarResolusciones(@Valid @ModelAttribute("gdocResoluciones") GdocResoluciones gdocResoluciones, BindingResult result, HttpSession session, Model model)  throws IOException { 
	  	    if (result.hasErrors()) {
	            getDesplegarListasComunes(model, session);
	            model.addAttribute("operation", "reg");
	            return "resoluciones";
	        }

	         MultipartFile multipartFile = gdocResoluciones.getFile();
	         GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
	         Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

	         AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
	         String rutaArchivo = adjuntarArchivo.crearSacDirectorio("gDoc/resolucion");
	         Integer ad = adjuntarArchivo.adjuntarArchivoResolucion(gdocResoluciones, rutaArchivo);
	        
	         gdocArchivoAdjunto.setNombreArchivo(gdocResoluciones.getNombreArchivo());
	         gdocArchivoAdjunto.setUsuarios(usuarios);
	         gdocArchivoAdjunto.setRutaArchivo("gDoc/resolucion/");
	         gdocArchivoAdjunto.setTipoArchivo(multipartFile.getContentType());
	         GdocArchivosAdjuntos gdocArchivosAdjuntos2=gServicios.registrarGdocArchivoAdjunto(gdocArchivoAdjunto);
	         
	         gdocResoluciones.setGdocArchivosAdjuntos(gdocArchivosAdjuntos2);
	         gServicios.registrarGdocResoluciones(gdocResoluciones) ;
	         
	         model.addAttribute("busqueda", "find");
		        getDesplegarListasComunes(model, session);
		        return "resoluciones";   
		    }
	
	  @RequestMapping(value = "/openFile/{id}", method = RequestMethod.GET, produces = "application/pdf")
	    public @ResponseBody
	    Resource abrirArchivoMedianteResourse(HttpServletResponse response, @PathVariable("id") long idGdocResolucion) throws FileNotFoundException {

	        GdocArchivosAdjuntos gGdocArchivo = gServicios.buscarGdocArchivosAdjuntosPorIdGdocResolucion(idGdocResolucion);
	        File file = new File("C:/"+ gGdocArchivo.getRutaArchivo() + gGdocArchivo.getNombreArchivo());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        return new FileSystemResource(file);
	    }

	   @PostMapping("/inicioModificarResolucion")
	    public String inicioModificarResolucion(@ModelAttribute("gdocResoluciones") GdocResoluciones gdocResoluciones,
	           @RequestParam("idGdocResolucion") Long idGdocResolucion, Model model, HttpSession session) throws IOException {

	        model.addAttribute("gdocResoluciones", gServicios.buscarGdocResolucionesPorIdGdocResolucion(idGdocResolucion));
	        model.addAttribute("modRes", "actualizarResolucion");
	        model.addAttribute("operation", "mod");
	          getDesplegarListasComunes(model, session);
	        return "resoluciones";
	    }

	   @PostMapping(value = "/actualizarResolucion")
		  public String modificarResolusciones(@Valid @ModelAttribute("gdocResoluciones") GdocResoluciones gdocResoluciones, BindingResult result, HttpSession session, Model model)  throws IOException { 
		  	    if (result.hasErrors()) {
		  	    	 model.addAttribute("modRes", "actualizarResolucion");
		 	        model.addAttribute("operation", "mod");
		 	        getDesplegarListasComunes(model, session);
		 	        return "resoluciones";
		        }

		         MultipartFile multipartFile = gdocResoluciones.getFile();
		         GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
		         Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

		         AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
		         String rutaArchivo = adjuntarArchivo.crearSacDirectorio("gDoc/resolucion");
		         Integer ad = adjuntarArchivo.adjuntarArchivoResolucion(gdocResoluciones, rutaArchivo);
		        
		         if(ad == 1){
		             GdocArchivosAdjuntos bArchivosAdjuntos = gServicios.buscarGdocArchivosAdjuntosPorIdGdocResolucion(gdocResoluciones.getIdGdocResolucion());  
		             bArchivosAdjuntos.setNombreArchivo(gdocResoluciones.getNombreArchivo());
		             bArchivosAdjuntos.setUsuarios(usuarios);
		             gServicios.actualizarGdocArchivosAdjuntos(bArchivosAdjuntos);
		         }
		        
		         gServicios.actualizarGdocResoluciones(gdocResoluciones);
		         
		            model.addAttribute("busqueda", "find");
			        getDesplegarListasComunes(model, session);
			        return "resoluciones";   
			    }

	   @PostMapping("/inicioEliminarResolucion")
	    public String inicioEliminarResolucion(@ModelAttribute("gdocResoluciones") GdocResoluciones gdocResoluciones,
	           @RequestParam("idGdocResolucion") Long idGdocResolucion, Model model, HttpSession session) throws IOException {

	        model.addAttribute("gdocResoluciones", gServicios.buscarGdocResolucionesPorIdGdocResolucion(idGdocResolucion));
	        model.addAttribute("delRes", "eliminarResolucion");
	        model.addAttribute("operation", "del");
	          getDesplegarListasComunes(model, session);
	        return "resoluciones";
	    }

	   @PostMapping(value = "/eliminarResolucion")
	    public String eliminarResoluciones(@Valid GdocResoluciones gdocResoluciones, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

		    if (result.hasErrors()) {
	            model.addAttribute("delRes", "eliminarResolucion");
		        model.addAttribute("operation", "del");
		          getDesplegarListasComunes(model, session);
		        return "resoluciones";
	        }

	        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
	        GdocArchivosAdjuntos bArchivosAdjuntos = gServicios.buscarGdocArchivosAdjuntosPorIdGdocResolucion(gdocResoluciones.getIdGdocResolucion());
            bArchivosAdjuntos.setIdEstado("X");
            bArchivosAdjuntos.setUsuarios(usuarios);
            gServicios.actualizarGdocArchivosAdjuntos(bArchivosAdjuntos);
            
            gdocResoluciones.setIdEstado("X");
            gServicios.actualizarGdocResoluciones(gdocResoluciones);
            
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("busqueda", "find");
 	        return "resoluciones";
	    }
	   
	  public void getDesplegarListasComunes(Model model, HttpSession session) {
	        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
	        GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion((Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
	        Integer i = 3;
	        Long l = new Long(i);
	        model.addAttribute("b", gServicios.buscarGdocArchivosAdjuntosPorIdGdocResolucion(l));
	        String idEstado = "A";
	        model.addAttribute("lAutoridades", gServicios.listarAutoridades(gdocConsejos.getIdGdocConsejo()));
	        model.addAttribute("lResoluciones", gServicios.listarResoluciones(gdocConsejos.getIdGdocConsejo()));
	        model.addAttribute("bGdocConsejos", gdocConsejos);
	        model.addAttribute("nRsol", "registroResolucion");
	        model.addAttribute("urlMod", "modificarIngresos");
	        model.addAttribute("urlConfirmarMod", "confirmarModificacionIngresos");
	        model.addAttribute("urlEliminar", "eliminarIngresosFondos");
	        model.addAttribute("urlRIn", "inicioFormResolucion");
	        model.addAttribute("urlEI", "inicioEliminarResolucion");
	        model.addAttribute("urlCEI", "confirmarEliminarIngresos");
	        model.addAttribute("urlM", "inicioModificarResolucion");
	        model.addAttribute("urlCloseConf", "confirmarCierreCaja");
	     }


}
