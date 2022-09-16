package uap.usic.siga.controladores.sac.administrarCarpetas;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SacCarpetas;
import uap.usic.siga.entidades.SacEstantes;
import uap.usic.siga.entidades.SacTiposCarpetas;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SacArchivoContableServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 *
 * @author Yessenia Velasco
 */
@Controller
@RequestMapping("/aCarpetas")
public class AdministrarCarpetas {

    @Autowired
    private CajitaServicios cServicio;

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private SacArchivoContableServicios aServicios;

    @GetMapping("/inicioCarpetas")
    public String formInicioCarpetas(@ModelAttribute("sacCarpetas") SacCarpetas sacCarpetas, HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioCarpetas";
    }

    @PostMapping(value = "/forCarpetaNueva")
    public String forrNuevaCarpeta(@ModelAttribute("sacCarpetas") SacCarpetas sacCarpetas, HttpSession session, Model model, BindingResult result) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "inicioCarpetas";
    }

    @PostMapping(value = "/formRegistrarCarpetas")
    public String forrRegistrarCarpeta(@ModelAttribute("sacCarpetas") @Valid SacCarpetas sacCarpetas, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
            sacCarpetas.setUsuarios(usuarios);
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioCarpetas";
        }

        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        sacCarpetas.setUsuarios(usuarios);
        aServicios.registrarCarpetas(sacCarpetas);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioCarpetas";

    }

    @PostMapping(value = "/inicioModificarCarpetas")
    public String inicioModificarCarpetas(@ModelAttribute("sacCarpetas") SacCarpetas sacCarpetas, HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacCarpeta") Long idSacCarpeta) {

        model.addAttribute("sacCarpetas", aServicios.buscarCarpetasPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model, session);
        return "inicioCarpetas";
    }

    @PostMapping(value = "/formModificarCarpetas")
    public String formModificarCarpeta(@ModelAttribute("sacCarpetas") @Valid SacCarpetas sacCarpetas, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
            sacCarpetas.setUsuarios(usuarios);
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "inicioCarpetas";
        }

        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        sacCarpetas.setUsuarios(usuarios);
        aServicios.modificarSacCarpetas(sacCarpetas);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioCarpetas";
    }

    @PostMapping(value = "/inicioEliminarCarpetas")
    public String inicioEliminarCarpetas(@ModelAttribute("sacCarpetas") SacCarpetas sacCarpetas, HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacCarpeta") Long idSacCarpeta) {

        model.addAttribute("sacCarpetas", aServicios.buscarCarpetasPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("operation", "elimC");
        getDesplegarListasComunes(model, session);
        return "inicioCarpetas";
    }

    @PostMapping(value = "/formEliminarCarpetas")
    public String formEliminarCarpeta(@ModelAttribute("sacCarpetas") @Valid SacCarpetas sacCarpetas, BindingResult result, Model model, HttpSession session) {

        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        sacCarpetas.setUsuarios(usuarios);
        sacCarpetas.setIdEstado("X");
        aServicios.eliminarSacCarpetas(sacCarpetas);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioCarpetas";
    }

    @PostMapping(value = "/guardarSacEstantes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<SacEstantes> getListarSacEstantes(@ModelAttribute @Valid SacEstantes sacEstantes, BindingResult result) {
        aServicios.registrarEstantes(sacEstantes);
        return aServicios.listarEstantes();
    }
    
    
    @PostMapping(value = "/guardarSacTiposCarpetas", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<SacTiposCarpetas> getListarSacTiposCarpetas(@ModelAttribute @Valid SacTiposCarpetas sacTiposCarpetas, BindingResult result) {
        aServicios.registrarSacTiposCarpetas(sacTiposCarpetas);
        return aServicios.listarTposCarpetas();
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {

        Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));

        model.addAttribute("urlNCar", "forCarpetaNueva");
        model.addAttribute("regCarpetas", "formRegistrarCarpetas");
        model.addAttribute("lTiposCarpetas", aServicios.listarTposCarpetas());
        model.addAttribute("lEstantes", aServicios.listarEstantes());
        model.addAttribute("lMeses", aServicios.ListarMeses());
        model.addAttribute("lCarpetas", aServicios.listarCarpetas());
        model.addAttribute("urlModCar", "inicioModificarCarpetas");
        model.addAttribute("conModCar", "formModificarCarpetas");
        model.addAttribute("iniElimCar", "inicioEliminarCarpetas");
        model.addAttribute("confElimCar", "formEliminarCarpetas");

    }

}
