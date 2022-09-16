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
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.MnuTiposFunciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.entidades.SisNivelesAccesos;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.MnuEnlacesServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/mnuFunciones")
public class AdministrarMnuFunciones {

    @Autowired
    private PersonasServicios pServicios;

    @Autowired
    private MnuEnlacesServicios eServicios;

    @Autowired
    private MenuesServicios mServicios;

    @Autowired
    private AdministrativosServicios aServicio;

    @GetMapping(value = "/formMnuFunciones")
    public String listarMnuFunciones(@ModelAttribute("mnuFunciones") MnuFunciones mnuFunciones, Model model, HttpSession session) {
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "regMnuFunciones";
    }

    @PostMapping(value = "/formMnuFuncionesNuevo")
    public String formRegistroMnuFunciones(@ModelAttribute("mnuFunciones") MnuFunciones mnuFunciones, Model model, HttpSession session) {
        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);

        return "regMnuFunciones";
    }

    @PostMapping(value = "/formRegistrarMnuFunciones")
    public String registrarMnuFunciones(@ModelAttribute("mnuFunciones") @Valid MnuFunciones mnuFunciones, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("operation", "reg");
            getDesplegarListasComunes(model, session);
            return "regMnuFunciones";
        }

        mServicios.registrarMnuFunciones(mnuFunciones);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMnuFunciones";
    }

    @PostMapping(value = "/inicioModificarMnuFunciones")
    public String inicioModificarMnuFunciones(@ModelAttribute("mnuFunciones") MnuFunciones mnuFunciones, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMnuFuncion") Long idMnuFuncion) {
        model.addAttribute("mnuFunciones", mServicios.buscarMnuFuncionesPorIdMnuFuncion(idMnuFuncion));
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model, session);

        return "regMnuFunciones";
    }

    @PostMapping("/confirmarModificacionMnuFuncion")
    public String confirmarModificacionMnuFunciones(@ModelAttribute("mnuFunciones") MnuFunciones mnuFunciones, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMnuFuncion") Long idMnuFuncion) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "mod");

            return "regMnuFunciones";
        }

        mServicios.modificarMnuFunciones(mnuFunciones);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMnuFunciones";

    }

    @PostMapping(value = "/inicioEliminarMnuFunciones")
    public String inicioEliminarMenues(@ModelAttribute("mnuFunciones") MnuFunciones mnuFunciones, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMnuFuncion") Long idMnuFuncion) {
        model.addAttribute("mnuFunciones", mServicios.buscarMnuFuncionesPorIdMnuFuncion(idMnuFuncion));
        model.addAttribute("operation", "elim");
        getDesplegarListasComunes(model, session);

        return "regMnuFunciones";
    }

    @PostMapping("/confirmarEliminacionMnuFunciones")
    public String confirmarEliminacionMnuFunciones(@ModelAttribute("mnuFunciones") MnuFunciones mnuFunciones, BindingResult result, Model model, HttpSession session,
            @RequestParam("idMnuFuncion") Long idMnuFuncion) {

        mnuFunciones.setIdEstado("X");
        mServicios.eliminaMnuFunciones(mnuFunciones);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "regMnuFunciones";

    }

    @PostMapping(value = "/guardarMnuTiposFuncion", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<MnuTiposFunciones> getListarMnuTiposFunciones(@ModelAttribute @Valid MnuTiposFunciones mnuTiposFunciones, BindingResult result) {
        mServicios.registrarMnuTiposFunciones(mnuTiposFunciones);
        return mServicios.listarMnuTiposFunciones();
    }

    @PostMapping(value = "/guardarSisAdministrador", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<SisAdministrador> getListarSisAdministrador(@ModelAttribute @Valid SisAdministrador sisAdministrador, BindingResult result) {
        mServicios.registrarSisAdministrador(sisAdministrador);
        return mServicios.listarSisAdministrador();
    }

    @PostMapping(value = "/guardarSisNivelesAccesos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<SisNivelesAccesos> getListarSisNivelesAccesos(@ModelAttribute @Valid SisNivelesAccesos sisNivelesAccesos, BindingResult result) {
        mServicios.registrarSisNivelesAccesos(sisNivelesAccesos);
        return mServicios.listarSisNivelesAccesos();
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("urlNMnuFuncion", "formMnuFuncionesNuevo");
        model.addAttribute("lTiposFunciones", mServicios.listarMnuTiposFunciones());
        model.addAttribute("lPersonas", aServicio.listarPersonaslAdministrativos());
        model.addAttribute("lSisAdministrador", mServicios.listarSisAdministrador());
        model.addAttribute("lNivelesAccesos", mServicios.listarSisNivelesAccesos());
        model.addAttribute("lMnuFunciones", mServicios.listarMnuFunciones());
        model.addAttribute("lMenues", mServicios.listarMenues());
        model.addAttribute("lMnuEnlaces", eServicios.listarMnuEnlaces());
        model.addAttribute("regMnuFuncion", "formRegistrarMnuFunciones");
        model.addAttribute("urlModMnuFuncion", "inicioModificarMnuFunciones");
        model.addAttribute("urlConfModF", "confirmarModificacionMnuFuncion");
        model.addAttribute("urlElimMnuFuncion", "inicioEliminarMnuFunciones");
        model.addAttribute("urlConfElimF", "confirmarEliminacionMnuFunciones");

    }

}
