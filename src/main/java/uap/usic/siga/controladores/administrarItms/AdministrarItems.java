package uap.usic.siga.controladores.administrarItms;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.InsDireccionesFuncionales;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.Instituciones;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.PnlItems;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PnlTiposAdministrativos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.AdministradorServicios;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Rectorado - USIC Fecha: 2019-03-28
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/items")
public class AdministrarItems {

    private static final Logger logger = LoggerFactory
            .getLogger(AdministrarItems.class);

    @Autowired
    private InstitucionesServicios iServicio;

    @Autowired
    private AdministrativosServicios aServicio;

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private AdministradorServicios adServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @GetMapping("/inicioItem")
    public String formItems(@ModelAttribute("pnlPersonalAdministrativos") PnlPersonalAdministrativos pnlPersonalAdministrativos, Model model, HttpSession session) {
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "inicioItem";
    }

    @PostMapping("/inicioNuevoItem")
    public String formNuevoItems(@ModelAttribute("pnlPersonalAdministrativos") PnlPersonalAdministrativos pnlPersonalAdministrativos,
            @ModelAttribute("insSedes") InsSedes insSedes,
            @ModelAttribute("insDireccionesFuncionales") InsDireccionesFuncionales insDireccionesFuncionales,
            @ModelAttribute("insUnidadesFuncionales") InsUnidadesFuncionales insUnidadesFuncionales, Model model, HttpSession session) {
        Integer iv = 1;
        Long lv = iv.longValue();
        getDesplegarListasComunes(model);
        model.addAttribute("operation", "reg");
        return "inicioItem";
    }

    @RequestMapping(value = "/lInsSedes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsSedes> getListarInsSedes(@PathVariable("id") long id) {
        return iServicio.listarSedesPorIdInstitucion(id);
      
    }
    
    @RequestMapping(value = "/lDireccionesItem/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsDireccionesFuncionales> getListarDireccionesFuncionalesArqGlobal(@PathVariable("id") long id) {
        return iServicio.listarDireccionesFuncionalesPorIdSede(id);
    }

    @RequestMapping(value = "/lUnidadesItem/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsUnidadesFuncionales> getListarUnidadesFuncionalesArqGlobal(@PathVariable("id") long id) {
        return iServicio.listarUnidadesFuncionalesPorIdDireccionFuncional(id);
    }
    
    
    
    
/*
    @RequestMapping(value = "/lUnidades/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsUnidadesFuncionales> getListarInsUnidadesFuncioanles(@PathVariable("id") long id) {
        return iServicio.listarUnidadesFuncionalesPorIdSede(id);
    }
*/
    @PostMapping("/registrarItems")
    public String registrarPnlAdministrativo(@Valid @ModelAttribute("pnlPersonalAdministrativos") PnlPersonalAdministrativos pnlPersonalAdministrativos, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("operation", "reg");
            getDesplegarListasComunes(model);
            return "inicioItem";

        }
      
        PnlPersonalAdministrativos bPersonalAdm = aServicio.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(pnlPersonalAdministrativos.getPersonas().getIdPersona(),(Integer) session.getAttribute("gestion"),(Integer)session.getAttribute("periodo"));

        if (bPersonalAdm == null) {
            aServicio.guardarPersonalAdministrativo(pnlPersonalAdministrativos);
            getDesplegarListasComunes(model);
            model.addAttribute("busqueda", "find");

        } else {
            model.addAttribute("mensage", "err");
            model.addAttribute("operation", "reg");
            getDesplegarListasComunes(model);
            return "inicioItem";
        }
        return "inicioItem";
    }

    @PostMapping("/inicioModificarItem")
    public String inicioModificarItem(@ModelAttribute("pnlPersonalAdministrativos") PnlPersonalAdministrativos pnlPersonalAdministrativos, Model model,
            @RequestParam("idPnlPersonalAdministrativo") Long idPnlPersonalAdministrativo) {

        model.addAttribute("pnlPersonalAdministrativos", aServicio.buscarPersonalAdministrativoPorIdPnlPersonalAdministrativo(idPnlPersonalAdministrativo));
        model.addAttribute("operation", "modI");
        model.addAttribute("lUnidadesFunc", iServicio.listarUnidadesFuncionales());
        getDesplegarListasComunes(model);
        return "inicioItem";
    }

    @PostMapping("/confirmarModificacionItem")
    public String confirmarModificarItem(@ModelAttribute("pnlPersonalAdministrativos") @Valid PnlPersonalAdministrativos pnlPersonalAdministrativos, Model model, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("operation", "modI");
            getDesplegarListasComunes(model);
            return "inicioItem";
        }
        aServicio.modificarDatosPersonalAdministrativos(pnlPersonalAdministrativos);
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "inicioItem";
    }

    @PostMapping("/inicioEliminarItem")
    public String inicioEliminarItem(@ModelAttribute("pnlPersonalAdministrativos") PnlPersonalAdministrativos pnlPersonalAdministrativos, Model model,
            @RequestParam("idPnlPersonalAdministrativo") Long idPnlPersonalAdministrativo) {

        Integer banderita = 0;
        model.addAttribute("pnlPersonalAdministrativos", aServicio.buscarPersonalAdministrativoPorIdPnlPersonalAdministrativo(idPnlPersonalAdministrativo));
        model.addAttribute("operation", "eliminarI");
        model.addAttribute("lUnidadesFunc", iServicio.listarUnidadesFuncionales());
        getDesplegarListasComunes(model);

        PnlPersonalAdministrativos bPnlPersonalAdministrativo = aServicio.buscarPersonalAdministrativoPorIdPnlPersonalAdministrativo(idPnlPersonalAdministrativo);
        CjaIngresos bCjaIngresos = pServicio.buscarCjaIngresosPoIdPersonaIdEstado(bPnlPersonalAdministrativo.getPersonas().getIdPersona(), "A");
        CjaGastosEjecutados cjaGastoEjecutados = pServicio.buscarCjaGastosEjecutadosPorIdPersonaIdEstado(bPnlPersonalAdministrativo.getPersonas().getIdPersona(), "A");
        Usuarios bUsuarios = pServicio.buscarUsuariosPorIdPersonaIdEstado(bPnlPersonalAdministrativo.getPersonas().getIdPersona(), "A");

        if (bUsuarios != null) {
            banderita = 1;
            model.addAttribute("banderita", banderita);
            model.addAttribute("mensage", "err");
        }
        
         if (bCjaIngresos != null) {
            banderita = 1;
            model.addAttribute("banderita", banderita);
            model.addAttribute("mensage", "err");
        }

        if (cjaGastoEjecutados != null) {
            banderita = 1;
            model.addAttribute("banderita", banderita);
            model.addAttribute("mensage", "err");
        }

        model.addAttribute("banderita", banderita);
        return "inicioItem";
    }

    @PostMapping("/confirmarEliminacionItem")
    public String confirmarEliminacionItem(@ModelAttribute("pnlPersonalAdministrativos") @Valid PnlPersonalAdministrativos pnlPersonalAdministrativos, Model model, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("operation", "eliminarI");
            getDesplegarListasComunes(model);
            return "inicioItem";
        }
        pnlPersonalAdministrativos.setIdEstado("X");
        aServicio.eliminarRegistroPersonalAdministrativos(pnlPersonalAdministrativos);
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");
        return "inicioItem";
    }

    public void getDesplegarListasComunes(Model model) {

        Integer iv = 1;
        Long lv = iv.longValue();

        model.addAttribute("lInstituciones", iServicio.listarInstituciones());
        model.addAttribute("lCargos", aServicio.listarPnlCargos());
        model.addAttribute("lItemss", aServicio.listarPnlItems());
        model.addAttribute("lSedes", iServicio.listarSedesPorIdInstitucion(lv));
        model.addAttribute("lTiposAdministrativoss", aServicio.listarPnlTiposAdministrativos());
        model.addAttribute("lPersonas", pServicio.listarPersonas());
        model.addAttribute("url", "registrarItems");
        model.addAttribute("lAdministrativos", aServicio.listarPersonaslAdministrativos());
        model.addAttribute("urlNuevoI", "inicioNuevoItem");
        model.addAttribute("urlInicioModItem", "inicioModificarItem");
        model.addAttribute("urlConfModItem", "confirmarModificacionItem");
        model.addAttribute("urlInicioElimI", "inicioEliminarItem");
        model.addAttribute("urlConfElimI", "confirmarEliminacionItem");

    }

    @PostMapping(value = "/guardarPnlCargos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<PnlCargos> getListarPnlCargos(@ModelAttribute @Valid PnlCargos pnlCargos, BindingResult result) {
        aServicio.guardarPnlCargos(pnlCargos);
        return aServicio.listarPnlCargos();
    }

    @PostMapping(value = "/guardarPnlTipoAdministrativo", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<PnlTiposAdministrativos> getListarPnlTiposAdministrativos(@ModelAttribute @Valid PnlTiposAdministrativos pnlTiposAdministrativos, BindingResult result) {
        aServicio.guardarPnlTiposAdministrativos(pnlTiposAdministrativos);
        return aServicio.listarPnlTiposAdministrativos();
    }

    @PostMapping(value = "/guardarPnlItems", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<PnlItems> getListarPnlItems(@ModelAttribute @Valid PnlItems pnlItems, BindingResult result) {
        aServicio.guardarPnlItems(pnlItems);
        return aServicio.listarPnlItems();
    }

    @PostMapping(value = "/guardarInstituciones", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Instituciones> getListarInstituciones(@ModelAttribute @Valid Instituciones instituciones, BindingResult result) {
        iServicio.guardarInstituciones(instituciones);
        return iServicio.listarInstituciones();
    }

    @PostMapping(value = "/guardarInsSedes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<InsSedes> getListarInsSedes(@ModelAttribute @Valid InsSedes insSedes, BindingResult result) {
        iServicio.registrarInsSedes(insSedes);
        return iServicio.listarSedesPorIdInstitucion(insSedes.getInstituciones().getIdInstitucion());
    }
    
     @PostMapping(value = "/guardarInsDireccionesF", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<InsDireccionesFuncionales> getListarInsDireccionesFuncionales(@ModelAttribute @Valid InsDireccionesFuncionales insDireccionesFuncionales, BindingResult result) {
        iServicio.registrarInsDireccionesFuncionales(insDireccionesFuncionales);
        return iServicio.listarDireccionesFuncionalesPorIdSede(insDireccionesFuncionales.getInsSedes().getIdSede());
    }

    @PostMapping(value = "/guardarUnidadesFuncionales", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<InsUnidadesFuncionales> getListarUnidadesFuncionales(@ModelAttribute @Valid InsUnidadesFuncionales insUnidadesFuncionales, BindingResult result) {
        iServicio.guardarInsUnidadesFuncionales(insUnidadesFuncionales);
        return iServicio.listarUnidadesFuncionalesPorIdDireccionFuncional(insUnidadesFuncionales.getInsDireccionesFuncionales().getIdDireccionFuncional());
    }

}
