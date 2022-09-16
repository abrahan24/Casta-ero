
package uap.usic.siga.controladores.administrarMnuEnlaces;

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
import uap.usic.siga.entidades.Menues;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuTiposEnlaces;
import uap.usic.siga.entidades.MnuTiposFunciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.MnuEnlacesServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/menues")
public class AdministrarMenues {
    
    @Autowired
    private PersonasServicios pServicios;

    @Autowired
    private MnuEnlacesServicios eServicios;
    
     @Autowired
    private MenuesServicios mServicios;
    
    @GetMapping(value = "/formMnuMenues")
    public String listarMenues(@ModelAttribute("menues") Menues menues, Model model, HttpSession session) {
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "regMenues";
    }
    
    @PostMapping(value = "/formMenuesNuevo")
    public String formRegistroMenues(@ModelAttribute("menues") Menues menues, Model model, HttpSession session) {
        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);

        return "regMenues";
    }
    
    @PostMapping(value = "/formRegistrarMenues")
    public String registrarMenues(@ModelAttribute("menues") @Valid Menues menues, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("operation", "reg");
            getDesplegarListasComunes(model, session);
            return "regMenues";
        }

       mServicios.registrarMenues(menues);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMenues";
    }
    
    @PostMapping(value = "/inicioModificarMenues")
    public String inicioModificarMenues(@ModelAttribute("menues") Menues menues, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMenu") Long idMenu) {
        model.addAttribute("menues", mServicios.buscarMenuesPorIdMenu(idMenu));
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model, session);

        return "regMenues";
    }

    @PostMapping("/confirmarModificacionMenues")
    public String confirmarModificarMenues(@ModelAttribute("menues") Menues menues, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMenu") Long idMenu) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");

            return "regMenues";
        }

        mServicios.modificarMenues(menues);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMenues";

    }

    @PostMapping(value = "/inicioEliminarMenues")
    public String inicioEliminarMenues(@ModelAttribute("menues") Menues menues, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMenu") Long idMenu) {
        model.addAttribute("menues", mServicios.buscarMenuesPorIdMenu(idMenu));
        model.addAttribute("operation", "elim");
        getDesplegarListasComunes(model, session);

        return "regMenues";
    }

    @PostMapping("/confirmarEliminacionMenues")
    public String confirmarEliminacionMenues(@ModelAttribute("menues") Menues menues, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMenu") Long idMenu) {

        menues.setIdEstado("X");
        mServicios.EliminarMenues(menues);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMenues";

    }
    
    @PostMapping(value = "/guardarMnuTiposFuncion", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<MnuTiposFunciones> getListarMnuTiposFunciones(@ModelAttribute @Valid MnuTiposFunciones mnuTiposFunciones, BindingResult result) {
        mServicios.registrarMnuTiposFunciones(mnuTiposFunciones);
        return mServicios.listarMnuTiposFunciones();
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("urlNMenu", "formMenuesNuevo");
        model.addAttribute("lTiposFunciones", mServicios.listarMnuTiposFunciones());
        model.addAttribute("lMenues", mServicios.listarMenues());
        model.addAttribute("lMnuEnlaces", eServicios.listarMnuEnlaces());
        model.addAttribute("regMenues", "formRegistrarMenues");
        model.addAttribute("urlModMenu", "inicioModificarMenues");
        model.addAttribute("urlConfModM", "confirmarModificacionMenues");
        model.addAttribute("urlElimMenu", "inicioEliminarMenues");
        model.addAttribute("urlConfElimM", "confirmarEliminacionMenues");

    }
}
