package uap.usic.siga.controladores.gdoc.administrarTitulados;

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
import uap.usic.siga.entidades.GdocTitulados;
import uap.usic.siga.entidades.GdocTitulos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/titulados")
public class AdministrarTitulados {

	@Autowired
	private GdocServicios gServicios;;
	
	@Autowired
	private PersonasServicios pServicios;;
	
	@Autowired
	private UsuariosServicios uServicio;
	
	@GetMapping("/inicioTitulados")
    public String inicioTitulados(Model model, HttpSession session){
	    model.addAttribute("busqueda", "find");
        getDesplegarListasComunes(model, session);
        return "titulados";   
    }
	
	@PostMapping(value = "/inicioFormTitulados")
    public String inicioFormTitulados(@ModelAttribute("gdocTitulados") GdocTitulados gdocTitulados, Model model, HttpSession session) {
        getDesplegarListasComunes(model, session);
        model.addAttribute("operation", "reg");
        return "titulados";
    }
	
	
	@PostMapping(value = "/registroTitulados")
	  public String registrarTitulados(@Valid @ModelAttribute("gdocTitulados") GdocTitulados gdocTitulados, BindingResult result, HttpSession session, Model model)  throws IOException { 
	  	    if (result.hasErrors()) {
	            getDesplegarListasComunes(model, session);
	            model.addAttribute("operation", "reg");
	            return "titulados";
	        }
	  	  MultipartFile multipartFile = gdocTitulados.getFile();
	         GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
	         Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

	        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
	         String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Gdoc/titulados");
	         Integer ad = adjuntarArchivo.adjuntarArchivoTitulados(gdocTitulados, rutaArchivo);
	       
	         gdocArchivoAdjunto.setNombreArchivo(gdocTitulados.getNombreArchivo());
	         gdocArchivoAdjunto.setUsuarios(usuarios);
	         gdocArchivoAdjunto.setRutaArchivo("gDoc/titulados/");
	         gdocArchivoAdjunto.setTipoArchivo(multipartFile.getContentType());
	         GdocArchivosAdjuntos gdocArchivosAdjuntos2 = gServicios.registrarGdocArchivoAdjunto(gdocArchivoAdjunto);
	         
	          gdocTitulados.setGdocArchivosAdjuntos(gdocArchivosAdjuntos2);
	          gServicios.guardarGdocTitulados(gdocTitulados);
	         		         
	         model.addAttribute("busqueda", "find");
		        getDesplegarListasComunes(model, session);
		        return "titulados"; 
		        
		        
	}  
	
	
	@PostMapping("/inicioModificarTitulados")
    public String inicioModificarTitulados(@ModelAttribute("gdocTitulados") GdocTitulados gdocTitulados,
           @RequestParam("idGdocTitulado") Long idGdocTitulado, Model model, HttpSession session) throws IOException {

        model.addAttribute("gdocTitulados", gServicios.buscarGdocTituladosGET(idGdocTitulado));
        model.addAttribute("urlUp", "actualizarTitulado");
        model.addAttribute("operation", "mod");
          getDesplegarListasComunes(model, session);
        return "titulados";
    }

   @PostMapping(value = "/actualizarTitulado")
	  public String modificarTitulado(@Valid @ModelAttribute("gdocTitulados") GdocTitulados gdocTitulados, BindingResult result, HttpSession session, Model model)  throws IOException { 
	  	    if (result.hasErrors()) {
	  	    	model.addAttribute("urlUp", "actualizarTitulado");
	            getDesplegarListasComunes(model, session);
	            model.addAttribute("operation", "mod");
	            return "titulados";
	        }
	  	    
	  	  

	         MultipartFile multipartFile = gdocTitulados.getFile();
	         GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
	         Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

	        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
	         String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Gdoc/titulados");
	         Integer ad = adjuntarArchivo.adjuntarArchivoTitulados(gdocTitulados, rutaArchivo);
	       
	         if(ad == 1){
	             GdocArchivosAdjuntos bArchivosAdjuntos = gServicios.buscarGdocArchivosAdjuntsPorIdGdocTitulado(gdocTitulados.getIdGdocTitulado());
	             bArchivosAdjuntos.setNombreArchivo(gdocTitulados.getNombreArchivo());
	             bArchivosAdjuntos.setUsuarios(usuarios);
	             gServicios.actualizarGdocArchivosAdjuntos(bArchivosAdjuntos);
	         }
	         
	         gServicios.modificarGdocTitulados(gdocTitulados);
	         		         
	         model.addAttribute("busqueda", "find");
		        getDesplegarListasComunes(model, session);
		        return "titulados";   
  }
   

   @PostMapping("/inicioEliminarTitulados")
   public String inicioEliminarTitulados(@ModelAttribute("gdocTitulados") GdocTitulados gdocTitulados,
          @RequestParam("idGdocTitulado") Long idGdocTitulado, Model model, HttpSession session) throws IOException {

       model.addAttribute("gdocTitulados", gServicios.buscarGdocTituladosGET(idGdocTitulado));
       model.addAttribute("urlDel", "eliminarTitulados");
       model.addAttribute("operation", "del");
       getDesplegarListasComunes(model, session);
       return "titulados";
   }

  @PostMapping(value = "/eliminarTitulados")
   public String eliminarTitulados(@Valid GdocTitulados gdocTitulados, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

       if (result.hasErrors()) {
       	  model.addAttribute("urlDel", "eliminarContratadosSicoes");
		        model.addAttribute("operation", "del");
		        getDesplegarListasComunes(model, session);
		        return "titulados";
       }
      

  Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
  GdocArchivosAdjuntos bArchivosAdjuntos = gServicios.buscarGdocArchivosAdjuntsPorIdGdocTitulado(gdocTitulados.getIdGdocTitulado());
   bArchivosAdjuntos.setIdEstado("X");
   bArchivosAdjuntos.setUsuarios(usuarios);
   gServicios.actualizarGdocArchivosAdjuntos(bArchivosAdjuntos);
   
   gdocTitulados.setIdEstado("X");
   gServicios.modificarGdocTitulados(gdocTitulados);
   
  getDesplegarListasComunes(model, session);
  model.addAttribute("busqueda", "find");
       return "titulados";
}

   
   @RequestMapping(value = "/openFile/{id}", method = RequestMethod.GET, produces = "application/pdf")
   public @ResponseBody
   Resource abrirArchivoMedianteResourse(HttpServletResponse response, @PathVariable("id") long idGdocTitulado) throws FileNotFoundException {
       GdocArchivosAdjuntos gGdocArchivo = gServicios.buscarGdocArchivosAdjuntsPorIdGdocTitulado(idGdocTitulado);
       File file = new File("C:/"+ gGdocArchivo.getRutaArchivo() + gGdocArchivo.getNombreArchivo());
       response.setContentType("application/pdf");
       response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
       response.setHeader("Content-Length", String.valueOf(file.length()));
       return new FileSystemResource(file);
   }
	
	public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion((Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
        Integer i = 3;
        Long l = new Long(i);
        model.addAttribute("b", gServicios.buscarGdocArchivosAdjuntsPorIdGdocTitulado(l));
        String idEstado = "A";

        model.addAttribute("lTitulados", gServicios.listarGdocTituladosPorIdGdocConsejo(gdocConsejos.getIdGdocConsejo()));
        model.addAttribute("bGdocConsejos", gdocConsejos);
        model.addAttribute("lPersonas", pServicios.listarPersonas());
        model.addAttribute("lTitulos", gServicios.listarGdocTitulos(gdocConsejos.getIdGdocConsejo()));
        model.addAttribute("regTitulado", "registroTitulados");
        model.addAttribute("urlMod", "inicioModificarTitulados");
        model.addAttribute("urlConfirmarMod", "confirmarModificacionIngresos");
        model.addAttribute("urlEliminar", "eliminarIngresosFondos");
        model.addAttribute("urlEli", "inicioEliminarTitulados");
        model.addAttribute("urlNuevo", "inicioFormTitulados");
        model.addAttribute("urlEI", "inicioEliminarIngresos");
        model.addAttribute("urlCEI", "confirmarEliminarIngresos");
        model.addAttribute("urlClose", "inicioCerrarCaja");
     }
	
}
