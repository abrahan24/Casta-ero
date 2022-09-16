package uap.usic.siga.controladores.administrarCargos;

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
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.CargosServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/cargos")
public class RegistrarCargos {

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private AdministrativosServicios aServicios;

    @Autowired
    private CargosServicios cServicio;

    @GetMapping(value = "/mostrarFormCargos")
    public String listarCargos(@ModelAttribute("pnlcargos") PnlCargos pnlcargos, Model model) {
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "registrarPnlCargos";
    }

    @PostMapping(value = "/formCargoNuevo")
    public String formRegistrarCargo(@ModelAttribute("pnlcargos") PnlCargos pnlcargos, Model model) {
        getDesplegarListasComunes(model);
        model.addAttribute("operation", "reg");
        return "registrarPnlCargos";
    }

    @PostMapping(value = "/registrarCargo")
    public String registrarPersonas(@Valid @ModelAttribute("pnlcargos") PnlCargos pnlcargos, BindingResult result, Model model) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model);
            model.addAttribute("operation", "reg");
            return "registrarPnlCargos";
        }
        cServicio.registrarPnlCargos(pnlcargos);
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "registrarPnlCargos";
    }

    @PostMapping(value = "/inicioModificarCargos")
    public String inicioModificarCargos(@ModelAttribute("pnlcargos") PnlCargos pnlcargos, Model model,
            @RequestParam("idPnlCargos") Long idPnlCargos) {
        model.addAttribute("pnlcargos", cServicio.buscarPnlCargosPorIdPnlCargo(idPnlCargos));
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model);
        return "registrarPnlCargos";
    }

    @PostMapping(value = "/confirmarModificacionCargos")
    public String confirmarModificacionCargos(@Valid @ModelAttribute("pnlcargos") PnlCargos pnlcargos, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("operation", "mod");
            getDesplegarListasComunes(model);
            return "registrarPnlCargos";
        }
        aServicios.actualizarPnlCargos(pnlcargos);
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "registrarPnlCargos";
    }

    public void getDesplegarListasComunes(Model model) {
        model.addAttribute("regCargos", "registrarCargo");
        model.addAttribute("urlNuevoC", "formCargoNuevo");
        model.addAttribute("urlModC", "inicioModificarCargos");
        model.addAttribute("urlConfC", "confirmarModificacionCargos");
        model.addAttribute("lPnlCargos", aServicios.listarPnlCargos());

    }

}
