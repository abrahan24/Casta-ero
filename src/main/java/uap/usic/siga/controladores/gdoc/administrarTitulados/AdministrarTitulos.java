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
import uap.usic.siga.entidades.GdocConvenios;
import uap.usic.siga.entidades.GdocResoluciones;
import uap.usic.siga.entidades.GdocTiposTitulosGrados;
import uap.usic.siga.entidades.GdocTitulos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.GdocServicios;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

@Controller
@RequestMapping("/titulos")
public class AdministrarTitulos {

	@Autowired
	private GdocServicios gServicios;;

	@Autowired
	private PersonasServicios pServicios;;

	@Autowired
	private UsuariosServicios uServicio;
	

	
	

	@GetMapping("/inicioTitulos")
	public String inicioTitulos(Model model, HttpSession session) {
		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "titulos";
	}

	// ========= Muestra Formulario ===================
	@PostMapping(value = "/inicioFormTitulos")
	public String inicioFormTitulos(@ModelAttribute("gdocTitulos") GdocTitulos gdocTitulos, Model model,
			HttpSession session) {
		getDesplegarListasComunes(model, session);
		model.addAttribute("operation", "reg");
		return "titulos";
	}

	@PostMapping(value = "/registroTitulos")
	public String registrarTitulos(@Valid @ModelAttribute("gdocTitulos") GdocTitulos gdocTitulos, BindingResult result,
			HttpSession session, Model model) throws IOException {
		if (result.hasErrors()) {
			getDesplegarListasComunes(model, session);
			model.addAttribute("operation", "reg");
			return "titulos";
		}

		MultipartFile multipartFile = gdocTitulos.getFile();
		GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
		Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

		AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
		String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Gdoc/titulos");
		Integer ad = adjuntarArchivo.adjuntarArchivoTitulos(gdocTitulos, rutaArchivo);

		gdocArchivoAdjunto.setNombreArchivo(gdocTitulos.getNombreArchivo());
		gdocArchivoAdjunto.setUsuarios(usuarios);
		gdocArchivoAdjunto.setRutaArchivo("gDoc/titulos/");
		gdocArchivoAdjunto.setTipoArchivo(multipartFile.getContentType());
		//=============================================================================
		GdocArchivosAdjuntos gdocArchivosAdjuntos2 = gServicios.registrarGdocArchivoAdjunto(gdocArchivoAdjunto);
		//=============================================================================
		
		
		gdocTitulos.setGdocArchivosAdjuntos(gdocArchivosAdjuntos2); 
		gServicios.guardarGdocTitulos(gdocTitulos);

		model.addAttribute("busqueda", "find");
		getDesplegarListasComunes(model, session);
		return "titulos";
	}
	
	@RequestMapping(value = "/openFile/{id}", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody
    Resource abrirArchivoMedianteResourse(HttpServletResponse response, @PathVariable("id") long idGdocTitulo) throws FileNotFoundException {
        GdocArchivosAdjuntos gGdocArchivo = gServicios.buscarGdocArchivosAdjuntsPorIdGdocTitulo(idGdocTitulo);
        File file = new File("C:/"+ gGdocArchivo.getRutaArchivo() + gGdocArchivo.getNombreArchivo());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
    }
	
	@PostMapping("/inicioModificarTitulos")
    public String inicioModificarTitulos(@ModelAttribute("gdocTitulos") GdocTitulos gdocTitulos,
           @RequestParam("idGdocTitulo") Long idGdocTitulo, Model model, HttpSession session) throws IOException {

        model.addAttribute("gdocTitulos", gServicios.buscarGdocTitulosGET(idGdocTitulo));
        model.addAttribute("urlUp", "actualizarTitulo");
        model.addAttribute("operation", "mod");
          getDesplegarListasComunes(model, session);
        return "titulos";
    }

   @PostMapping(value = "/actualizarTitulo")
	  public String modificarTitulo(@Valid @ModelAttribute("gdocTitulos") GdocTitulos gdocTitulos, BindingResult result, HttpSession session, Model model)  throws IOException { 
	  	    if (result.hasErrors()) {
	  	    	model.addAttribute("urlUp", "actualizarTitulo");
	            getDesplegarListasComunes(model, session);
	            model.addAttribute("operation", "mod");
	            return "titulos";
	        }

	         MultipartFile multipartFile = gdocTitulos.getFile();
	         GdocArchivosAdjuntos gdocArchivoAdjunto = new GdocArchivosAdjuntos();
	         Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

	        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
	         String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Gdoc/titulos");
	         Integer ad = adjuntarArchivo.adjuntarArchivoTitulos(gdocTitulos, rutaArchivo);
	       
	         if(ad == 1){
	             GdocArchivosAdjuntos bArchivosAdjuntos = gServicios.buscarGdocArchivosAdjuntsPorIdGdocTitulo(gdocTitulos.getIdGdocTitulo());
	             bArchivosAdjuntos.setNombreArchivo(gdocTitulos.getNombreArchivo());
	             bArchivosAdjuntos.setUsuarios(usuarios);
	             gServicios.actualizarGdocArchivosAdjuntos(bArchivosAdjuntos);
	         }
	         
	         gServicios.modificarGdocTitulos(gdocTitulos);
	         		         
	         model.addAttribute("busqueda", "find");
		        getDesplegarListasComunes(model, session);
		        return "titulos";   
  }
   
   @PostMapping("/inicioEliminarTitulos")
   public String inicioEliminarTitulos(@ModelAttribute("gdocTitulos") GdocTitulos gdocTitulos,
          @RequestParam("idGdocTitulo") Long idGdocTitulo, Model model, HttpSession session) throws IOException {

       model.addAttribute("gdocTitulos", gServicios.buscarGdocTitulosGET(idGdocTitulo));
       model.addAttribute("urlDel", "eliminarTitulos");
       model.addAttribute("operation", "del");
       getDesplegarListasComunes(model, session);
       return "titulos";
   }

  @PostMapping(value = "/eliminarTitulos")
   public String eliminarTitulos(@Valid GdocTitulos gdocTitulos, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

       if (result.hasErrors()) {
       	  model.addAttribute("urlDel", "eliminarContratadosSicoes");
		        model.addAttribute("operation", "del");
		        getDesplegarListasComunes(model, session);
		        return "titulos";
       }

       Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
       GdocArchivosAdjuntos bArchivosAdjuntos = gServicios.buscarGdocArchivosAdjuntsPorIdGdocTitulo(gdocTitulos.getIdGdocTitulo());
        bArchivosAdjuntos.setIdEstado("X");
        bArchivosAdjuntos.setUsuarios(usuarios);
        gServicios.actualizarGdocArchivosAdjuntos(bArchivosAdjuntos);
        
        gdocTitulos.setIdEstado("X");
        gServicios.modificarGdocTitulos(gdocTitulos);
        
       getDesplegarListasComunes(model, session);
       model.addAttribute("busqueda", "find");
	        return "titulos";
   }
   
	public void getDesplegarListasComunes(Model model, HttpSession session) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		GdocConsejos gdocConsejos = gServicios.buscarConsejoPorIdUsuarioIdMnuTipoFuncion(
				(Long) session.getAttribute("currentUserId"), (Long) session.getAttribute("idMnuTipoFuncion"));
		Integer i = 3;
		Long l = new Long(i);
		model.addAttribute("b", gServicios.buscarGdocArchivosAdjuntsPorIdGdocTitulo(l));
		String idEstado = "A";
		model.addAttribute("lTitulos", gServicios.listarGdocTitulosPorIdGdocConsejo(gdocConsejos.getIdGdocConsejo()));
		model.addAttribute("bGdocConsejos", gdocConsejos);

		model.addAttribute("lGradoAcademico", pServicios.listarGradosAcademicos());
		model.addAttribute("lTiposTitulos", gServicios.listarGdocTiposTitulos());
		model.addAttribute("lTiposTitulosGrados", gServicios.listarGdocTiposTitulosGrados());
		model.addAttribute("regTitulo", "registroTitulos");
		model.addAttribute("urlMod", "inicioModificarTitulos");
		model.addAttribute("urlELi", "inicioEliminarTitulos");
		model.addAttribute("urlConfirmarMod", "confirmarModificacionIngresos");
		model.addAttribute("urlEliminar", "eliminarIngresosFondos");
		model.addAttribute("urlNuevo", "inicioFormTitulos");
		model.addAttribute("urlEI", "inicioEliminarIngresos");
		model.addAttribute("urlCEI", "confirmarEliminarIngresos");
		model.addAttribute("urlClose", "inicioCerrarCaja");
		model.addAttribute("urlCloseConf", "confirmarCierreCaja");
	}

}
