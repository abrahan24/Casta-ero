package uap.usic.siga.controladores.sicoes.administrarContratados;

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
import org.springframework.web.multipart.MultipartFile;
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.entidades.ScsPrsContratados;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

/**
 * Rectorado - USIC
 * Fecha: 2020-02-03
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/aContratados")
public class AdministrarContratados {
    
     @Autowired
    private SicoesServicios scServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private CajitaServicios cServicios;
    
    @Autowired
    private UsuariosServicios uServicio;
    
    @GetMapping("/inicioContratados")
    public String formInicioContratados(HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioContratados";
    }

    @PostMapping(value = "/foraNuevaContratado")
    public String forrNuevaContratado(@ModelAttribute("scsPrsContratados") ScsPrsContratados scsPrsContratados, HttpSession session, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "inicioContratados";
    }
  
    
    @PostMapping(value = "/registrarPrsContratados")
    public String registrarContratados(@Valid ScsPrsContratados scsPrsContratados, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioContratados";
        }

        MultipartFile multipartFile = scsPrsContratados.getFile();
        ScsArchivosAdjuntos scsArchivosAdjuntos = new ScsArchivosAdjuntos();
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
    
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/fotos");
        model.addAttribute("di", rutaArchivo);
        Integer ad = adjuntarArchivo.adjuntarFotosSicoes(scsPrsContratados, rutaArchivo);

        scsArchivosAdjuntos.setNombreArchivo(scsPrsContratados.getNombreArchivo());
        scsArchivosAdjuntos.setTipoArchivo(multipartFile.getContentType());
        scsArchivosAdjuntos.setUsuarios(usuarios);
        scsArchivosAdjuntos.setRutaArchivo("Sicoes/sico/");
        ScsArchivosAdjuntos scsArchivosAdjuntos1 = scServicios.registrarScsArchivosAdjuntos(scsArchivosAdjuntos);

        scsPrsContratados.setScsArchivosAdjuntos(scsArchivosAdjuntos1);
        ScsPrsContratados scsPrsContratados1 = scServicios.registrarScsPrsContratados(scsPrsContratados);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioContratados";
    }

    @PostMapping("/inicioModificarContratados")
    public String inicioModificarContratados(@ModelAttribute("scsPrsContratados") ScsPrsContratados scsPrsContratados,
           @RequestParam("idScsPrsContratado") Long idScsPrsContratado, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsPrsContratados", scServicios.buscarScsPrsContratadosPorIdScsPrsContratados(idScsPrsContratado));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model, session);
        return "inicioContratados";
    }

    @PostMapping(value = "/actualizarContratadosSicoes")
    public String modificarContratadosSicoes(@Valid ScsPrsContratados scsPrsContratados, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "inicioContratacion";
        }

        MultipartFile multipartFile = scsPrsContratados.getFile();
        ScsArchivosAdjuntos scsArchivosAdjuntos = new ScsArchivosAdjuntos();
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        String rutaArchivo = adjuntarArchivo.crearSacDirectorio("Sicoes/fotos");
        model.addAttribute("di", rutaArchivo);
        Integer ad = adjuntarArchivo.adjuntarFotosSicoes(scsPrsContratados, rutaArchivo);
        
        if(ad == 1){
            ScsArchivosAdjuntos bScsArchivosAdjuntos = scServicios.buscarScsArchivosAdjuntosPorIdScsArchivoAdjunto(scsPrsContratados.getScsArchivosAdjuntos().getIdScsArchivoAdjunto());
            bScsArchivosAdjuntos.setNombreArchivo(scsPrsContratados.getNombreArchivo());
            bScsArchivosAdjuntos.setUsuarios(usuarios);
            scServicios.modificarScsArchivosAdjuntos(bScsArchivosAdjuntos);
        }
      
        scServicios.actualizarScsPrsContratados(scsPrsContratados);

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "inicioContratados";
    }

    @PostMapping("/inicioEliminarContratadosSicoes")
    public String inicioEliminarContratados(@ModelAttribute("scsPrsContratados") ScsPrsContratados scsPrsContratados,
           @RequestParam("idScsPrsContratado") Long idScsPrsContratado, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsPrsContratados", scServicios.buscarScsPrsContratadosPorIdScsPrsContratados(idScsPrsContratado));
        model.addAttribute("urlDel", "eliminarContratadosSicoes");
        model.addAttribute("operation", "delet");
        getDesplegarListasComunes(model, session);
        return "inicioContratados";
    }

    
    @PostMapping(value = "/eliminarContratadosSicoes")
    public String eliminarContratadosSicoes(@Valid ScsPrsContratados scsPrsContratados, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "inicioContratados";
        }

        scsPrsContratados.setIdEstado("X");
        scServicios.actualizarScsPrsContratados(scsPrsContratados);

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "inicioContratados";
    }

    // ====== Listas Comunes de Módulo
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        //Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("urlNCont", "foraNuevaContratado");
        model.addAttribute("regCont", "registrarPrsContratados");
        model.addAttribute("lScsProyectos", scServicios.listarScsProyectos());
        model.addAttribute("lScsTiposContratos", scServicios.listarScsTiposContratos());
        model.addAttribute("lScsModalidades", scServicios.listarScsModalidades());
        model.addAttribute("lScsTiposModalidades", scServicios.listarScsTiposModalidades());
        model.addAttribute("lPersonas", pServicios.listarPersonasComprobantes());
        model.addAttribute("lCjaTiposGastos", cServicios.listarTiposGastos());
        model.addAttribute("lScsFormularios", scServicios.listarScsFormularios());
        model.addAttribute("lScsBoletasRespaldatorioas", scServicios.listarScsBoletasRespaldatorias());
        model.addAttribute("lScsPrsContratados", scServicios.listarScsPrsContratadoses());       
        model.addAttribute("urlModCont", "inicioModificarContratados");
        model.addAttribute("confModCont", "actualizarContratadosSicoes");
        model.addAttribute("iniElimCont", "inicioEliminarContratadosSicoes");
        model.addAttribute("confElimCar", "formEliminarCarpetas");

    }

}
