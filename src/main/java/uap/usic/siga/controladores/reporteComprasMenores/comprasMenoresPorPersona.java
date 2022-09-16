package uap.usic.siga.controladores.reporteComprasMenores;

import java.util.List;
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
 *
 */
@Controller
@RequestMapping("/reportes")
public class comprasMenoresPorPersona {

    @Autowired
    private CajitaServicios cServicios;

    @Autowired
    private PersonasServicios pServicios;

    @GetMapping(value = "/ingresosComprasMenoresPersonas")
    public String gastosComprasMenoresPers(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model, HttpSession session) {

        getDesplegarListasComunes(model, session);
        return "formComMenoresPersona";
    }

    @PostMapping(value = "/gastosComprasMenoresPersona")
    public String gastosComprasMenoresPersonas(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model, @RequestParam("idCjaIngreso") Long idCjaIngreso,
            HttpSession session) {

        model.addAttribute("cjaIng", "cjaIngresosFondos");
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());

        getDesplegarListasComunes(model, session);

        model.addAttribute("lGastosP", pServicios.listarCjaGastosEjecutadosPersonasPorIdCjaIngresoIdUsuario(idCjaIngreso, (Long) session.getAttribute("currentUserId")));
         model.addAttribute("idCjaIngreso", idCjaIngreso);
        //model.addAttribute("lGastosE", cServicios.listarCjaGastosEjecutadosPorIdCjaIngresoIdUsuario(idCjaIngreso, (Long) session.getAttribute("currentUserId")));
        return "gastosMenoresPersona";
    }

    @PostMapping(value = "/imprimirComprasMenoresPersonas")
    public String imprimirComprasMenoresPersonas(@ModelAttribute("cjaGastosEjecutados") CjaGastosEjecutados cjaGastosEjecutado, Model model,
            @RequestParam("idPersona") Long idPersona,
             @RequestParam("idCjaIngreso") Long idCjaIngreso,
            HttpSession session) {

        Double totalGasto = 0.0;
//        CjaGastosEjecutados  bCjaGastos = cServicios.buscarCjaGastosEjecutadosPorIdCjaIngresoIdEstado(idPersona, idEstado)
//        model.addAttribute("bCjaGastos", bCjaGastos);
//        
        model.addAttribute("cjaIngreso", cServicios.buscarCjaIngresoPorIdCjaIngreso(idCjaIngreso));
        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(idPersona));
        model.addAttribute("lGastosPersonas", cServicios.listarGastosPorIdPersonaGestionPeriodoIdUsuario(idPersona, (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo"), (Long) session.getAttribute("currentUserId"),idCjaIngreso));

        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));     
        
        model.addAttribute("bSaldo", cServicios.buscarCajitaSaldoIngresosPorIdPersona("A", (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo"), personas.getIdPersona()));
        getDesplegarListasComunes(model, session);
        
        List<CjaGastosEjecutados> lGastos = cServicios.listarGastosPorIdPersonaGestionPeriodoIdUsuario(idPersona, (Integer) session.getAttribute("gestion"), (Integer) session.getAttribute("periodo"), (Long) session.getAttribute("currentUserId"),idCjaIngreso);
        for(CjaGastosEjecutados gasto : lGastos){
            totalGasto = totalGasto + gasto.getTotalG();
        }
        
        Literales literal = new Literales();
        model.addAttribute("literal",literal.convert(totalGasto));

        //return "uap/usic/siga/reporteComprasMenores/comprasMenoresPersonas/imprimirRecibo";
        return "formReporteComprasMPersonasImp";
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("lIngresos", cServicios.listarIngresosPorIdPersona(personas.getIdPersona()));
        model.addAttribute("repComMenPrs", "gastosComprasMenoresPersona");
        model.addAttribute("cjaIng", "cjaIngresosFondos");
        model.addAttribute("imprimirComMP", "imprimirComprasMenoresPersonas");
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());

    }

}
