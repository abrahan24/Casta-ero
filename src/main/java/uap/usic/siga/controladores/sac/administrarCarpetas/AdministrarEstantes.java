package uap.usic.siga.controladores.sac.administrarCarpetas;

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
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SacCarpetas;
import uap.usic.siga.entidades.SacEstantes;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SacArchivoContableServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/aEstantes")
public class AdministrarEstantes {

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private SacArchivoContableServicios aServicios;

    @GetMapping("/inicioEstantes")
    public String formInicioEstantes(@ModelAttribute("sacEstantes") SacEstantes sacEstantes, HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioEstantes";
    }

    @PostMapping(value = "/forEstanteNuevo")
    public String formEstanteNuevo(@ModelAttribute("sacEstantes") SacEstantes sacEstantes, HttpSession session, Model model, BindingResult result) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "inicioEstantes";
    }
    
    @PostMapping(value = "/formRegistrarEstantes")
    public String forrRegistrarEstante(@ModelAttribute("sacEstantes") @Valid SacEstantes sacEstantes, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioEstantes";
        }

      
        aServicios.registrarEstantes(sacEstantes);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioEstantes";

    }

    @PostMapping(value = "/inicioModificarEstantes")
    public String inicioModificarEstantes(@ModelAttribute("sacEstantes") SacEstantes sacEstantes, HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacEstante") Long idSacEstante) {

        model.addAttribute("sacEstantes", aServicios.buscarSacEstantesPorIdSacEstante(idSacEstante));
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model, session);
        return "inicioEstantes";
    }

    @PostMapping(value = "/formModificarEstantes")
    public String formModificarCarpeta(@ModelAttribute("sacEstantes") @Valid SacEstantes sacEstantes, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");
            return "inicioEstantes";
        }

        aServicios.modificarEstantes(sacEstantes);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioEstantes";
    }

    @PostMapping(value = "/inicioEliminarEstantes")
    public String inicioEliminarEstantes(@ModelAttribute("sacEstantes") SacEstantes sacEstantes, HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacEstante") Long idSacEstante) {

        model.addAttribute("sacEstantes", aServicios.buscarSacEstantesPorIdSacEstante(idSacEstante));
        model.addAttribute("operation", "elimEst");
        getDesplegarListasComunes(model, session);
        return "inicioEstantes";
    }

    @PostMapping(value = "/formEliminarEstantes")
    public String formEliminarEstantes(@ModelAttribute("sacEstantes") @Valid SacEstantes sacEstantes, BindingResult result, Model model, HttpSession session) {
       
        sacEstantes.setIdEstado("X");
        aServicios.eliminarEstantes(sacEstantes);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioEstantes";
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {

        Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));

        model.addAttribute("urlNEst", "forEstanteNuevo");
        model.addAttribute("regEstantes", "formRegistrarEstantes");
        model.addAttribute("lTiposCarpetas", aServicios.listarTposCarpetas());
        model.addAttribute("lEstantes", aServicios.listarEstantes());
        model.addAttribute("urlModEst", "inicioModificarEstantes");
        model.addAttribute("conModEst", "formModificarEstantes");
        model.addAttribute("iniElimEst", "inicioEliminarEstantes");
        model.addAttribute("confElimEst", "formEliminarEstantes");

    }

}
