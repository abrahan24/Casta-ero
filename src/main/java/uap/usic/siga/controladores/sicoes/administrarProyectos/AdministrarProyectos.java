package uap.usic.siga.controladores.sicoes.administrarProyectos;

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
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.ScsProyectos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Rectorado - USIC
 * Fecha: 2020-01-20
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/aProyectos")
public class AdministrarProyectos {
    
    @Autowired
    private SicoesServicios scServicios;
   
    @Autowired
    private UsuariosServicios uServicio;
    
    @Autowired
    private InstitucionesServicios iServicios;
    
    @GetMapping("/inicioProyectos")
    public String formInicioProyectos(HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioProyectos";
    }

    @PostMapping(value = "/scdNuevoProyecto")
    public String scsNuevoProyecto(@ModelAttribute("scsProyectos") ScsProyectos scsProyectos, HttpSession session, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "inicioProyectos";
    }
    
    @PostMapping(value = "/registrarProyectosSicoes")
    public String registrarProyectosSicoes(@Valid ScsProyectos scsProyectos, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioProyectos";
        }
        
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));       
        scsProyectos.setUsuarios(usuarios);
        ScsProyectos gScsProyectos = scServicios.registrarScsProyectos(scsProyectos);
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioProyectos";
    }

    @PostMapping("/inicioModificarProyecto")
    public String inicioModificarProyecto(@ModelAttribute("scsProyectos") ScsProyectos scsProyectos,
           @RequestParam("idScsProyecto") Long idScsProyecto, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsProyectos", scServicios.buscarScsProyectosPorIdScsProyecto(idScsProyecto));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model, session);
        return "inicioProyectos";
    }

    @PostMapping(value = "/actualizarProyectosSicoes")
    public String actualizarProyectosSicoes(@Valid ScsProyectos scsProyectos, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioProyectos";
        }
        
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));       
        scsProyectos.setUsuarios(usuarios);
        scServicios.actualizarScsProyectos(scsProyectos);;
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioProyectos";
    }

    @PostMapping("/inicioEliminarProyecto")
    public String inicioEliminarProyecto(@ModelAttribute("scsProyectos") ScsProyectos scsProyectos,
           @RequestParam("idScsProyecto") Long idScsProyecto, Model model, HttpSession session) throws IOException {

        model.addAttribute("scsProyectos", scServicios.buscarScsProyectosPorIdScsProyecto(idScsProyecto));
        model.addAttribute("urlUp", "actualizarContratacion");
        model.addAttribute("operation", "delet");
        getDesplegarListasComunes(model, session);
        return "inicioProyectos";
    }

    @PostMapping(value = "/eliminarProyectosSicoes")
    public String eliminarProyectosSicoes(@Valid ScsProyectos scsProyectos, BindingResult result, HttpSession session, Model model, HttpServletRequest request) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioProyectos";
        }
        
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));       
        scsProyectos.setUsuarios(usuarios);
        scsProyectos.setIdEstado("X");
        scServicios.actualizarScsProyectos(scsProyectos);;
        
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioProyectos";
    }

    
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("urlNProy", "scdNuevoProyecto");
        model.addAttribute("regProy", "registrarProyectosSicoes");
        model.addAttribute("lScsProyectos", scServicios.listarScsProyectos());
        model.addAttribute("lScsTiposContratos", scServicios.listarScsTiposContratos());
        model.addAttribute("lScsModalidades", scServicios.listarScsModalidades());
        model.addAttribute("lScsTiposModalidades", scServicios.listarScsTiposModalidades());
     //   model.addAttribute("lPersonas", pServicios.listarPersonasComprobantes());
      //  model.addAttribute("lCjaTiposGastos", cServicios.listarTiposGastos());
        model.addAttribute("lScsFormularios", scServicios.listarScsFormularios());
        model.addAttribute("lScsBoletasRespaldatorioas", scServicios.listarScsBoletasRespaldatorias());
        model.addAttribute("lScsContrataciones", scServicios.listarScsContrataciones());       
        model.addAttribute("lUnidades", iServicios.listarUnidadesFuncionales());
        model.addAttribute("urlModProy", "inicioModificarProyecto");
        model.addAttribute("confModProy", "actualizarProyectosSicoes");
        model.addAttribute("iniElimProy", "inicioEliminarProyecto");
        model.addAttribute("confElimProy", "eliminarProyectosSicoes");

    }

   
}
