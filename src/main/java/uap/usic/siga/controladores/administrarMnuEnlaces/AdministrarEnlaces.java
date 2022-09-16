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
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuTiposEnlaces;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.servicios.MnuEnlacesServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia Velasco
 */
@Controller
@RequestMapping("/mnuEnlaces")
public class AdministrarEnlaces {

    @Autowired
    private PersonasServicios pServicios;

    @Autowired
    private MnuEnlacesServicios eServicios;

    @GetMapping(value = "/formMnuEnlaces")
    public String listarMnuEnlaces(@ModelAttribute("mnuEnlaces") MnuEnlaces mnuEnlaces, Model model, HttpSession session) {
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "regMnuEnlaces";
    }

    @PostMapping(value = "/formEnlaceNuevo")
    public String formRegistroMnuEnlaces(@ModelAttribute("mnuEnlaces") MnuEnlaces mnuEnlaces, Model model, HttpSession session) {
        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);

        return "regMnuEnlaces";
    }

    @PostMapping(value = "/formRegistrarEnlaces")
    public String registrarEnlaces(@ModelAttribute("mnuEnlaces") @Valid MnuEnlaces mnuEnlaces, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("operation", "reg");
            getDesplegarListasComunes(model, session);
            return "regMnuEnlaces";
        }

        eServicios.registrarMnuEnlaces(mnuEnlaces);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMnuEnlaces";
    }

    @PostMapping(value = "/inicioModificarEnlaces")
    public String inicioModificarEnlaces(@ModelAttribute("mnuEnlaces") MnuEnlaces mnuEnlaces, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMnuEnlace") Long idMnuEnlace) {
        model.addAttribute("mnuEnlaces", eServicios.buscarMnuEnlacesPorIdMnuEnlace(idMnuEnlace));
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model, session);

        return "regMnuEnlaces";
    }

    @PostMapping("/confirmarModificacionEnlaces")
    public String confirmarModificarEnlaces(@ModelAttribute("mnuEnlaces") MnuEnlaces mnuEnlaces, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMnuEnlace") Long idMnuEnlace) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");

            return "regMnuEnlaces";
        }

        eServicios.modificarMnuEnlaces(mnuEnlaces);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMnuEnlaces";

    }

    @PostMapping(value = "/inicioEliminarEnlaces")
    public String inicioEliminarEnlaces(@ModelAttribute("mnuEnlaces") MnuEnlaces mnuEnlaces, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMnuEnlace") Long idMnuEnlace) {
        model.addAttribute("mnuEnlaces", eServicios.buscarMnuEnlacesPorIdMnuEnlace(idMnuEnlace));
        model.addAttribute("operation", "elim");
        getDesplegarListasComunes(model, session);

        return "regMnuEnlaces";
    }

    @PostMapping("/confirmarEliminacionEnlaces")
    public String confirmarEliminacionEnlaces(@ModelAttribute("mnuEnlaces") MnuEnlaces mnuEnlaces, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMnuEnlace") Long idMnuEnlace) {

        mnuEnlaces.setIdEstado("X");
        eServicios.EliminarMnuEnlaces(mnuEnlaces);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMnuEnlaces";

    }

    @PostMapping(value = "/guardarMnuTiposEnlaces", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<MnuTiposEnlaces> getListarMnuTiposEnlaces(@ModelAttribute @Valid MnuTiposEnlaces mnuTiposEnlaces, BindingResult result) {
        eServicios.registrarMnuTiposEnlaces(mnuTiposEnlaces);
        return eServicios.listarMnuTiposEnlaces();
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("urlNEnl", "formEnlaceNuevo");
        model.addAttribute("lTipoEnlaces", eServicios.listarMnuTiposEnlaces());
        model.addAttribute("lMnuEnlaces", eServicios.listarMnuEnlaces());
        model.addAttribute("regEnlaces", "formRegistrarEnlaces");
        model.addAttribute("urlModEnl", "inicioModificarEnlaces");
        model.addAttribute("urlConfMod", "confirmarModificacionEnlaces");
        model.addAttribute("urlElimEnl", "inicioEliminarEnlaces");
        model.addAttribute("urlConfElimE", "confirmarEliminacionEnlaces");

    }

}
