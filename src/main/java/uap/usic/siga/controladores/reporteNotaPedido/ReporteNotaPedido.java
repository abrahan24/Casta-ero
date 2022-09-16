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
 * @author Yessenia Velasco
 */
@Controller
@RequestMapping("/reportes")
public class ReporteNotaPedido {

    @Autowired
    private CajitaServicios cServicios;

    @Autowired
    private PersonasServicios pServicios;

    @GetMapping(value = "/formReporteNotaPedido")
    public String reporteComprasMenores(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model, HttpSession session) {

        getDesplegarListasComunes(model, session);

        return "formNotaPedido";
    }

    @PostMapping(value = "/reporteNotaPedido")
    public String formIngresos(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model,
            @RequestParam("idCjaIngreso") Long idCjaIngreso,
            HttpSession session) {

        model.addAttribute("lGastosE", cServicios.listarCjaGastosEjecutadosPorIdCjaIngresoIdUsuario(idCjaIngreso, (Long) session.getAttribute("currentUserId")));
        getDesplegarListasComunes(model, session);

        return "listarNotaPedido";

    }

    @PostMapping(value = "/imprimirNotaPedido")
    public String reporteComprasMenores(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model,
            @RequestParam("idCjaGastoEjecutado") Long idCjaGastoEjecutado,
            HttpSession session) {

        Personas bPersona = pServicios.buscarPersonasPorIdCjaGastoEjecutado(idCjaGastoEjecutado);
        model.addAttribute("bPersona", bPersona);
        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(bPersona.getIdPersona()));

        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        
        model.addAttribute("bSaldo", cServicios.buscarCajitaSaldoIngresosPorIdPersona("A", (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo"), personas.getIdPersona()));
        getDesplegarListasComunes(model, session);

        model.addAttribute("bCjaGastos", cServicios.buscarCjaGastosEjecutadosPorIdCjaGastoEjecutado(idCjaGastoEjecutado));
        //return "uap/usic/siga/reporteNotaPorPedido/formReporteNotaPedido";
        return "imprimirNotaPedidoImp";
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("lIngresos", cServicios.listarIngresosPorIdPersona(personas.getIdPersona()));
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());
        model.addAttribute("personas", personas);
        model.addAttribute("listarNotaPedido", "imprimirNotaPedido");
        model.addAttribute("cjaIng", "cjaIngresosFondos");
        model.addAttribute("repNotaPed", "reporteNotaPedido");

    }

}
