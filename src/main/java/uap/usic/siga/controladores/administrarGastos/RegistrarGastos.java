package uap.usic.siga.controladores.administrarGastos;

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
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.CjaProveedores;
import uap.usic.siga.entidades.CjaTiposGastos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Rectorado - USIC Fecha: 2019-04-29
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/gastos")
public class RegistrarGastos {

    @Autowired
    private CajitaServicios cServicio;

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private AdministrativosServicios aServicio;

    @GetMapping("/inicioGastos")
    public String formGastos(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutados, HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioGastos";
    }

    @PostMapping("/inicioGastosNuevo")
    public String InicioformGastos(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutados,
            @ModelAttribute("cjaTiposGastos") CjaTiposGastos cjaTiposGastos,
            HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("operation", "nuevo");
        return "inicioGastos";
    }

    @PostMapping("/registrarGastos")
    public String registrarGastos(@Valid @ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutados,
            BindingResult result, Model model,
            @RequestParam("saldoGb") Double saldo, 
            @RequestParam("retencionCheck") Boolean retencionCheck, 
            @RequestParam("retencion") Double retencion,HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("saldoGb", saldo);
            model.addAttribute("cja", cjaGastosEjecutados);
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "nuevo");
            return "inicioGastos";
        }

        cjaGastosEjecutados.setGestion((Integer) session.getAttribute("gestion"));
        cjaGastosEjecutados.setPeriodo((Integer) session.getAttribute("periodo"));
        
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        cjaGastosEjecutados.setUsuarios(usuarios);
        cServicio.insertarCjaGastosEjecutados(cjaGastosEjecutados);
      
        CjaIngresos bCjaIngreso = cServicio.buscarCjaIngresosPorIdCjaIngreso(cjaGastosEjecutados.getCjaIngresos().getIdCjaIngreso());
        bCjaIngreso.setSaldo(saldo);
        bCjaIngreso.setNroDocumento(cjaGastosEjecutados.getNroDocumento());
        cServicio.modificarCajaIngresosSaldo(bCjaIngreso);
        model.addAttribute("bCjaIngreso", bCjaIngreso);
        model.addAttribute("cjaGastosEjecutados", cjaGastosEjecutados);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "inicioGastos";
    }

    @PostMapping("/inicioModificarGasto")
    public String inicioModificarGastos(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutados, 
                                        @ModelAttribute("cjaTiposGastos") CjaTiposGastos cjaTiposGastos,
                                        @RequestParam("idCjaGastoEjecutado") Long idCjaGastoEjecutado, Model model, HttpSession session) {

        model.addAttribute("cjaGastosEjecutados", cServicio.buscarCjaGastoEjecutadosPorIdCjaGastoEjecutado(idCjaGastoEjecutado));
        model.addAttribute("operation", "edit");
        getDesplegarListasComunes(model, session);
        return "inicioGastos";
    }

    @PostMapping("/modificarGasto")
    public String modificarGastos(@Valid @ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutados,
            BindingResult result, Model model,
            @RequestParam("saldoMod") Double saldo, HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("saludo", "hola");
            model.addAttribute("operation", "edit");
            getDesplegarListasComunes(model, session);
            return "inicioGastos";

        }

        cjaGastosEjecutados.setGestion((Integer) session.getAttribute("gestion"));
        cjaGastosEjecutados.setPeriodo((Integer) session.getAttribute("periodo"));
        
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        cjaGastosEjecutados.setUsuarios(usuarios);
        model.addAttribute("bG", cjaGastosEjecutados);
        
        cServicio.modificarCjaGastosEjecutados(cjaGastosEjecutados);
        CjaIngresos bCjaIngreso = cServicio.buscarCjaIngresosPorIdCjaIngreso(cjaGastosEjecutados.getCjaIngresos().getIdCjaIngreso());
        bCjaIngreso.setSaldo(saldo);
        cServicio.modificarCajaIngresosSaldo(bCjaIngreso);
        model.addAttribute("bCjaIngreso", bCjaIngreso);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "inicioGastos";
    }

    @PostMapping("/inicioEliminarGastos")
    public String inicioEliminarGastos(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutados,
             @ModelAttribute("cjaTiposGastos") CjaTiposGastos cjaTiposGastos,
            @RequestParam("idCjaGastoEjecutado") Long idCjaGastoEjecutado, Model model, HttpSession session) {

        model.addAttribute("cjaGastosEjecutados", cServicio.buscarCjaGastoEjecutadosPorIdCjaGastoEjecutado(idCjaGastoEjecutado));
        model.addAttribute("operation", "delet");
        getDesplegarListasComunes(model, session);
        return "inicioGastos";
    }

    @PostMapping("/eliminarGastos")
    public String eliminarGastos(@Valid @ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutados,
            BindingResult result, Model model, HttpSession session) {

      
        cjaGastosEjecutados.setGestion((Integer) session.getAttribute("gestion"));
        cjaGastosEjecutados.setPeriodo((Integer) session.getAttribute("periodo"));
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
        cjaGastosEjecutados.setUsuarios(usuarios);
        cjaGastosEjecutados.setIdEstado("X");
        cServicio.modificarCjaGastosEjecutados(cjaGastosEjecutados);

        CjaIngresos bCjaIngreso = cServicio.buscarCjaIngresosPorIdCjaIngreso(cjaGastosEjecutados.getCjaIngresos().getIdCjaIngreso());
        bCjaIngreso.setSaldo(bCjaIngreso.getSaldo() + cjaGastosEjecutados.getTotalG());
        cServicio.modificarCajaIngresosSaldo(bCjaIngreso);
        model.addAttribute("bCjaIngreso", bCjaIngreso);

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "inicioGastos";
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {

        Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        CjaIngresos cjaIngresos = cServicio.buscarCajitaSaldoIngresosPorIdPersona("A", (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo"), bPersona.getIdPersona());
        model.addAttribute("pnlAdministrativo", aServicio.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(bPersona.getIdPersona(),  (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo")));
        model.addAttribute("bSaldo", cjaIngresos);
        model.addAttribute("resp", cServicio.buscarSaldoIngresos("A", (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo"), bPersona.getIdPersona()));
        model.addAttribute("lTiposGastos", cServicio.listarTiposGastos());
        model.addAttribute("lGastoClasificacion",cServicio.listarCjaGastosClasificaciones());
        model.addAttribute("lProveedores", cServicio.listarCajitaProveedores());
        model.addAttribute("lTiposClasificaciones", cServicio.listarCjaTiposClasificaciones());
        model.addAttribute("lGastosEjecutados", cServicio.listarGastosEjecutadosGestionPeriodoIdUsuarioIdCjaIngreso((Integer) session.getAttribute("periodo"), (Integer) session.getAttribute("gestion"), (Long) session.getAttribute("currentUserId")));
        model.addAttribute("urlN", "inicioGastosNuevo");
        model.addAttribute("urlR", "registrarGastos");
        model.addAttribute("urlIM", "inicioModificarGasto");
        model.addAttribute("urlIE", "inicioEliminarGastos");
        model.addAttribute("urlEG", "eliminarGastos");
        model.addAttribute("urlMG", "modificarGasto");
      
        model.addAttribute("lPersonas", aServicio.listarPersonaslAdministrativos());

    }

    @PostMapping(value = "/guardarCjaProveedores", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<CjaProveedores> getListarCjaProveedores(@ModelAttribute @Valid CjaProveedores cjaProveedores, BindingResult result) {
        cServicio.registarCjaProveedores(cjaProveedores);
        return cServicio.listarCajitaProveedores();
    }

    @PostMapping(value = "/guardarCjaTiposGastos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<CjaTiposGastos> getListarCajaTiposGastos(@ModelAttribute @Valid CjaTiposGastos cjaTiposGastos, BindingResult result) {
        cServicio.registrarTiposGastos(cjaTiposGastos);
        return cServicio.listarTiposGastos();
    }

}
