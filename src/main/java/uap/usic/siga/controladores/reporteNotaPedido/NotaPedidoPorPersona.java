package uap.usic.siga.controladores.reporteNotaPedido;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/reportes")
public class NotaPedidoPorPersona {

    @Autowired
    private CajitaServicios cServicios;

    @Autowired
    private PersonasServicios pServicios;

    @GetMapping(value = "/formNotaPedidoPorPersona")
    public String reporteNotaPedidoPersonas(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model, HttpSession session) {

        getDesplegarListasComunes(model, session);
        return "formNotaPedidoPersona";
    }

    @PostMapping(value = "/reporteNotaPedidoPersonas")
    public String formDetallesNotaPedidoPersonas(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model,
            @RequestParam("idCjaIngreso") Long idCjaIngreso,
            HttpSession session) {

        model.addAttribute("lGastosP", pServicios.listarCjaGastosEjecutadosPersonasPorIdCjaIngresoIdUsuario(idCjaIngreso, (Long) session.getAttribute("currentUserId")));
        model.addAttribute("idCjaIngreso", idCjaIngreso);
        getDesplegarListasComunes(model, session);

        return "listarGastosPersona";

    }

    @PostMapping(value = "/imprimirNotaPedidoPersona")
    public String formNotaPedidoPorPersonas(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model,
            @RequestParam("idPersona") Long idPersona,
            @RequestParam("idCjaIngreso") Long idCjaIngreso,
            HttpSession session) {

        
        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(idPersona));
        model.addAttribute("lGastosPersonas", cServicios.listarGastosPorIdPersonaGestionPeriodoIdUsuario(idPersona, (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo"), (Long) session.getAttribute("currentUserId"),idCjaIngreso));
        
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        
        model.addAttribute("bSaldo", cServicios.buscarCajitaSaldoIngresosPorIdPersona("A", (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo"), personas.getIdPersona()));
        getDesplegarListasComunes(model, session);

        //return "uap/usic/siga/reporteNotaPorPedido/notaPedidoPorPersonas/formReporteNotaPedidoPersona";
        
        return "imprimirNotaPedidoPersonaImp";
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("lIngresos", cServicios.listarIngresosPorIdPersona(personas.getIdPersona()));
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());
        model.addAttribute("personas", personas);
        model.addAttribute("imprimirNPP", "imprimirNotaPedidoPersona");
        model.addAttribute("repNotaPedPers", "reporteNotaPedidoPersonas");
    }
}
