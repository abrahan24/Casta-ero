package uap.usic.siga.controladores.reporteComprasMenores;

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
import uap.usic.siga.funciones.Literales;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/reportes")
public class ReporteComprasMenores {

    @Autowired
    private CajitaServicios cServicios;

    @Autowired
    private PersonasServicios pServicios;

    @GetMapping(value = "/formReporteComprasMenores")
    public String reporteComprasMenores(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model, HttpSession session) {

        getDesplegarListasComunes(model, session);
        return "formComMenores";
    }

    @PostMapping(value = "/reporteComprasMenores")
    public String formIngresos(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model, @RequestParam("idCjaIngreso") Long idCjaIngreso,
            HttpSession session) {

        model.addAttribute("cjaIng", "cjaIngresosFondos");
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());

        getDesplegarListasComunes(model, session);

        model.addAttribute("lGastosE", cServicios.listarCjaGastosEjecutadosPorIdCjaIngresoIdUsuario(idCjaIngreso, (Long) session.getAttribute("currentUserId")));
        return "listarGastosMenores";
    }

    @PostMapping(value = "/imprimirReporteComprasMenores")
    public String reporteComprasMenores(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model,
            @RequestParam("idCjaGastoEjecutado") Long idCjaGastoEjecutado,
            HttpSession session) {

        Personas bPersona = pServicios.buscarPersonasPorIdCjaGastoEjecutado(idCjaGastoEjecutado);
        model.addAttribute("bPersona", bPersona);
        
        CjaGastosEjecutados  bCjaGastos = cServicios.buscarCjaGastosEjecutadosPorIdCjaGastoEjecutado(idCjaGastoEjecutado);
        model.addAttribute("bCjaGastos", bCjaGastos);
        
       // model.addAttribute("bCjaGastos", cServicios.buscarCjaGastosEjecutadosPorIdCjaGastoEjecutado(idCjaGastoEjecutado));
        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(bPersona.getIdPersona()));

        Literales literal = new Literales();
        model.addAttribute("literal",literal.convert(bCjaGastos.getTotalG()));
       

        return "formReporteComprasMImp";
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("lIngresos", cServicios.listarIngresosPorIdPersona(personas.getIdPersona()));
        model.addAttribute("repComMen", "reporteComprasMenores");
        model.addAttribute("cjaIng", "cjaIngresosFondos");
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());

    }

}
