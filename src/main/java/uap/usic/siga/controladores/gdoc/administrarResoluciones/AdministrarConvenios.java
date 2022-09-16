package uap.usic.siga.controladores.gdoc.administrarResoluciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.eclipse.jdt.internal.compiler.env.IModule.IService;
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
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/convenios")
public class AdministrarConvenios {

	@Autowired
	private GdocServicios gServicios;;
	
	@Autowired
	private PersonasServicios pServicios;;
	
	@Autowired
	private UsuariosServicios uServicio;
	
	@Autowired
	private InstitucionesServicios iServicios;
	
	  @GetMapping("/inicioConvenios")
	   public String inicioConvenios(Model model, HttpSession session){
		    model.addAttribute("busqueda", "find");
	        getDesplegarListasComunes(model, session);
	        return "convenios";   
	    }
	  
	    @PostMapping(value = "/inicioFormConvenios")
	    public String inicioFormConvenios(@ModelAttribute("gdocConvenios") GdocConvenios gdocConvenios, Model model, HttpSession session) {
	        getDesplegarListasComunes(model, session);
	        model.addAttribute("operation", "reg");
	        return "convenios";
	    }

		  @PostMapping(value = "/registroConvenios")
		  public String registrarConvenio(@Valid @ModelAttribute("gdocConvenios") GdocConvenios gdocConvenios, BindingResult result, HttpSession session, Model model)  throws IOException { 
		  	    if (result.hasErrors()) {
		            getDesplegarListasComunes(model, session);
		            model.addAttribute("operation", "reg");
		            return "convenios";
		        }

		         MultipartFile multipartFile = gdocConvenios.getFile();
		         GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
		         Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

		        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
		         String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Gdoc/convenios");
		         Integer ad = adjuntarArchivo.adjuntarArchivoConvenios(gdocConvenios, rutaArchivo);
		       
		         gdocArchivoAdjunto.setNombreArchivo(gdocConvenios.getNombreArchivo());
		         gdocArchivoAdjunto.setUsuarios(usuarios);
		         gdocArchivoAdjunto.setRutaArchivo("gDoc/convenios/");
		         gdocArchivoAdjunto.setTipoArchivo(multipartFile.getContentType());
		         GdocArchivosAdjuntos gdocArchivosAdjuntos2 = gServicios.registrarGdocArchivoAdjunto(gdocArchivoAdjunto);
		         
		          gdocConvenios.setGdocArchivosAdjuntos(gdocArchivosAdjuntos2);
		          gServicios.guardarGdocConvenios(gdocConvenios);
		         		         
		         model.addAttribute("busqueda", "find");
			        getDesplegarListasComunes(model, session);
			        return "convenios";   
	  }
		  
		  @RequestMapping(value = "/openFile/{id}", method = RequestMethod.GET, produces = "application/pdf")
		    public @ResponseBody
		    Resource abrirArchivoMedianteResourse(HttpServletResponse response, @PathVariable("id") long idGdocConvenio) throws FileNotFoundException {
		        GdocArchivosAdjuntos gGdocArchivo = gServicios.buscarGdocArchivosAdjuntsPorIdGdocConvenio(idGdocConvenio);
		        File file = new File("C:/"+ gGdocArchivo.getRutaArchivo() + gGdocArchivo.getNombreArchivo());
		        response.setContentType("application/pdf");
		        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
		        response.setHeader("Content-Length", String.valueOf(file.length()));
		        return new FileSystemResource(file);
		    }

		   @PostMapping("/inicioModificarConvenios")
		    public String inicioModificarConvneios(@ModelAttribute("gdocConvenios") GdocConvenios gdocConvenios,
		           @RequestParam("idGdocConvenio") Long idGdocConvenio, Model model, HttpSession session) throws IOException {

		        model.addAttribute("gdocConvenios", gServicios.buscarGdocConvenios(idGdocConvenio));
		        model.addAttribute("urlUp", "actualizarConvenio");
		        model.addAttribute("operation", "mod");
		          getDesplegarListasComunes(model, session);
		        return "convenios";
		    }

		   @PostMapping(value = "/modificarConvenios")
			  public String modificarConvenio(@Valid @ModelAttribute("gdocConvenios") GdocConvenios gdocConvenios, BindingResult result, HttpSession session, Model model)  throws IOException { 
			  	    if (result.hasErrors()) {
			            getDesplegarListasComunes(model, session);
			            model.addAttribute("operation", "mod");
			            return "convenios";
			        }

			         MultipartFile multipartFile = gdocConvenios.getFile();
			         GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
			         Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

			        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
			         String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Gdoc/convenios");
			         Integer ad = adjuntarArchivo.adjuntarArchivoConvenios(gdocConvenios, rutaArchivo);
			       
			         if(ad == 1){
			             GdocArchivosAdjuntos bArchivosAdjuntos = gServicios.buscarGdocArchivosAdjuntsPorIdGdocConvenio(gdocConvenios.getIdGdocConvenio());
			             bArchivosAdjuntos.setNombreArchivo(gdocConvenios.getNombreArchivo());
			             bArchivosAdjuntos.setUsuarios(usuarios);
			             gServicios.actualizarGdocArchivosAdjuntos(bArchivosAdjuntos);
			         }
			         
			         gServicios.modificarGdocConvenios(gdocConvenios);
			         		         
			         model.addAttribute("busqueda", "find");
				        getDesplegarListasComunes(model, session);
				        return "convenios";   
		  }

		   @PostMapping("/inicioEliminarConvenios")
		    public String inicioEliminarConvneios(@ModelAttribute("gdocConvenios") GdocConvenios gdocConvenios,
		           @RequestParam("idGdocConvenio") Long idGdocConvenio, Model model, HttpSession session) throws IOException {

		        model.addAttribute("gdocConvenios", gServicios.buscarGdocConvenios(idGdocConvenio));
		        model.addAttribute("urlDel", "eliminarConvenios");
		        model.addAttribute("operation", "delet");
		        getDesplegarListasComunes(model, session);
		        return "convenios";
		    }

		   @PostMapping(value = "/eliminarConvenios")
		    public String eliminarConvenios(@Valid GdocConvenios gdocConvenios, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

		        if (result.hasErrors()) {
		        	  model.addAttribute("urlDel", "eliminarContratadosSicoes");
				        model.addAttribute("operation", "delet");
				        getDesplegarListasComunes(model, session);
				        return "convenios";
		        }

		        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
		        GdocArchivosAdjuntos bArchivosAdjuntos = gServicios.buscarGdocArchivosAdjuntsPorIdGdocConvenio(gdocConvenios.getIdGdocConvenio());
	             bArchivosAdjuntos.setIdEstado("X");
	             bArchivosAdjuntos.setUsuarios(usuarios);
	             gServicios.actualizarGdocArchivosAdjuntos(bArchivosAdjuntos);
	             
	             gdocConvenios.setIdEstado("X");
	             gServicios.modificarGdocConvenios(gdocConvenios);
	             
		        getDesplegarListasComunes(model, session);
		        model.addAttribute("busqueda", "find");
      	        return "convenios";
		    }
   
		   
	  public void getDesplegarListasComunes(Model model, HttpSession session) {
	        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
	        GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion((Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
	        Integer i = 3;
	        Long l = new Long(i);
	        model.addAttribute("b", gServicios.buscarGdocArchivosAdjuntosPorIdGdocResolucion(l));
	        String idEstado = "A";
	        model.addAttribute("lAutoridades", gServicios.listarAutoridades(gdocConsejos.getIdGdocConsejo()));
	        model.addAttribute("lConvenios", gServicios.listarGdocConveniosPorIdGdocConsejo(gdocConsejos.getIdGdocConsejo()));
	        model.addAttribute("bGdocConsejos", gdocConsejos);
	        model.addAttribute("lRepresentante", gServicios.listarGdocRepresentante());
	        model.addAttribute("lTiposConvenios", gServicios.listarGdocTiposConvenios());
	        model.addAttribute("lInstituciones", iServicios.listarInstituciones());
	        model.addAttribute("nConv", "registroConvenios");
	        model.addAttribute("urlMod", "inicioModificarConvenios");
	        model.addAttribute("urlConfirmarMod", "confirmarModificacionIngresos");
	        model.addAttribute("urlEliminar", "eliminarIngresosFondos");
	        model.addAttribute("urlRIn", "inicioFormConvenios");
	        model.addAttribute("urlEI", "inicioEliminarConvenios");
	        model.addAttribute("urlCEI", "confirmarEliminarIngresos");
	        model.addAttribute("urlClose", "inicioCerrarCaja");
	        model.addAttribute("urlCloseConf", "confirmarCierreCaja");
	     }

}
