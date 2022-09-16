package uap.usic.siga.controladores.sicoes.administrarContrataciones;

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
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

/**
 * Rectorado - USIC 
 * Fecha: 2019-12-31
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/aContrataciones")
public class AdministrarContrataciones {
 
    @Autowired
    private SicoesServicios scServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private CajitaServicios cServicios;
    
    @Autowired
    private UsuariosServicios uServicio;
    
    @GetMapping("/inicioContratacion")
    public String formInicioContratacion(HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioContratacion";
    }

    @PostMapping(value = "/foraNuevaContratacion")
    public String forrNuevaContratacion(@ModelAttribute("scsContrataciones") ScsContrataciones scsContrataciones, HttpSession session, Model model) {

        model.addAttribute("operation", "reg");
        model.addAttribute("idScs", scServicios.extraerMaximoIdScsContratacion());
        getDesplegarListasComunes(model, session);
        return "inicioContratacion";
    }
    
    @PostMapping(value = "/registrarContratacion")
    public String registrarContratacion(@Valid ScsContrataciones scsContrataciones, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioContratacion";
        }

        MultipartFile multipartFile = scsContrataciones.getFile();
        ScsArchivosAdjuntos scsArchivosAdjuntos = new ScsArchivosAdjuntos();
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
    
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/sico");
        model.addAttribute("di", rutaArchivo);
        Integer ad = adjuntarArchivo.adjuntarArchivoSicoes(scsContrataciones, rutaArchivo);

        scsArchivosAdjuntos.setNombreArchivo(scsContrataciones.getNombreArchivo());
        scsArchivosAdjuntos.setTipoArchivo(multipartFile.getContentType());
        scsArchivosAdjuntos.setUsuarios(usuarios);
        scsArchivosAdjuntos.setRutaArchivo("Sicoes/sico/");
        ScsArchivosAdjuntos scsArchivosAdjuntos1 = scServicios.registrarScsArchivosAdjuntos(scsArchivosAdjuntos);

        scsContrataciones.setUsuarios(usuarios);
        scsContrataciones.setScsArchivosAdjuntos(scsArchivosAdjuntos1);
        ScsContrataciones scsContrataciones1 = scServicios.registrarScsContrataciones(scsContrataciones);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioContratacion";
    }

    @RequestMapping(value = "/openFile/{id}", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody
    Resource abrirArchivoMedianteResourse(HttpServletResponse response, @PathVariable("id") long idScsContratacion) throws FileNotFoundException {

        ScsArchivosAdjuntos aAdjunto = scServicios.buscarScsArchivosAdjuntosPorIdScsArchivoAdjunto(idScsContratacion);
        File file = new File("C:/" + aAdjunto.getRutaArchivo() + aAdjunto.getNombreArchivo());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
    }

    @PostMapping("/inicioModificarContrataciones")
    public String inicioModificarContrataciones(@ModelAttribute("scsContrataciones") ScsContrataciones scsContrataciones,
           @RequestParam("idScsContratacion") Long idScsContratacion, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsContrataciones", scServicios.buscarScsContratacionesPorIdScsContratacion(idScsContratacion));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "mod");
          getDesplegarListasComunes(model, session);
        return "inicioContratacion";
    }

    @PostMapping(value = "/actualizarContratacion")
    public String modificarContrataciones(@Valid ScsContrataciones scsContrataciones, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "inicioContratacion";
        }

        MultipartFile multipartFile = scsContrataciones.getFile();
        ScsArchivosAdjuntos scsArchivosAdjuntos = new ScsArchivosAdjuntos();
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/sico");
        model.addAttribute("di", rutaArchivo);
        Integer ad = adjuntarArchivo.adjuntarArchivoSicoes(scsContrataciones, rutaArchivo);

        model.addAttribute("hola",scsContrataciones.getScsArchivosAdjuntos().getIdScsArchivoAdjunto());
        
        if(ad == 1){
            ScsArchivosAdjuntos bScsArchivosAdjuntos = scServicios.buscarScsArchivosAdjuntosPorIdScsArchivoAdjunto(scsContrataciones.getScsArchivosAdjuntos().getIdScsArchivoAdjunto());
            bScsArchivosAdjuntos.setNombreArchivo(scsContrataciones.getNombreArchivo());
            bScsArchivosAdjuntos.setUsuarios(usuarios);
            scServicios.modificarScsArchivosAdjuntos(bScsArchivosAdjuntos);
        }
        scsContrataciones.setUsuarios(usuarios);
        scServicios.modificarScsContrataciones(scsContrataciones);

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "inicioContratacion";
    }

    @PostMapping("/inicioEliminarContrataciones")
    public String inicioEliminarContrataciones(@ModelAttribute("scsContrataciones") ScsContrataciones scsContrataciones,
           @RequestParam("idScsContratacion") Long idScsContratacion, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsContrataciones", scServicios.buscarScsContratacionesPorIdScsContratacion(idScsContratacion));
        model.addAttribute("urlDel", "eliminarContratacion");
        model.addAttribute("operation", "delet");
        getDesplegarListasComunes(model, session);
        return "inicioContratacion";
    }

    @PostMapping(value = "/eliminarContratacion")
    public String eliminarContrataciones(@Valid ScsContrataciones scsContrataciones, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "inicioContratacion";
        }

        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        scsContrataciones.setUsuarios(usuarios);
        scsContrataciones.setIdEstado("X");
        scServicios.modificarScsContrataciones(scsContrataciones);

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "inicioContratacion";
    }

    
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        //Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("urlNCont", "foraNuevaContratacion");
        model.addAttribute("regCont", "registrarContratacion");
        model.addAttribute("lScsProyectos", scServicios.listarScsProyectos());
        model.addAttribute("lScsTiposContratos", scServicios.listarScsTiposContratos());
        model.addAttribute("lScsModalidades", scServicios.listarScsModalidades());
        model.addAttribute("lScsTiposModalidades", scServicios.listarScsTiposModalidades());
        model.addAttribute("lPersonas", pServicios.listarPersonasComprobantes());
        model.addAttribute("lCjaTiposGastos", cServicios.listarTiposGastos());
        model.addAttribute("lScsFormularios", scServicios.listarScsFormularios());
        model.addAttribute("lScsBoletasRespaldatorioas", scServicios.listarScsBoletasRespaldatorias());
        model.addAttribute("lScsContrataciones", scServicios.listarScsContrataciones());       
        model.addAttribute("urlModCont", "inicioModificarContrataciones");
        model.addAttribute("confModCont", "actualizarContratacion");
        model.addAttribute("iniElimCont", "inicioEliminarContrataciones");
        model.addAttribute("confElimCar", "formEliminarCarpetas");

    }

}
