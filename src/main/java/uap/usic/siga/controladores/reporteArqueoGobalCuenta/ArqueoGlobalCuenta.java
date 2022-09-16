package uap.usic.siga.controladores.reporteArqueoGobalCuenta;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.funciones.TextoFecha;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia Velasco
 */
@Controller
@RequestMapping("/reportes")
public class ArqueoGlobalCuenta {

    @Autowired
    private CajitaServicios cServicios;

    @Autowired
    private PersonasServicios pServicios;

    @GetMapping(value = "/formReporteArqueoGlobal")
    public String reporteArqueoGlobal(@ModelAttribute("cjaGastosEjecutado") CjaGastosEjecutados cjaGastosEjecutado, Model model, HttpSession session) {

        getDesplegarListasComunes(model, session);
        return "arqueoGlobalCuenta";
    }

    @PostMapping(value = "/reporteArqueoGlobalCuenta")
    public String formIngresosArqueoGlobalCuenta(@ModelAttribute("cjaGastosEjecutado") CjaGastosEjecutados cjaGastosEjecutaso, Model model,
            @RequestParam("idCjaIngreso") Long idCjaIngreso,
            HttpServletRequest request, HttpSession session) {
    	Personas bPersona = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));

        model.addAttribute("id", idCjaIngreso);
        String fecIngreso = "fecIngreso" + idCjaIngreso;
        String FecIngresoV = request.getParameter(fecIngreso);

        String fecGasto = "fecGasto" + idCjaIngreso;
        String fecGastoV = request.getParameter(fecGasto);
        String algo = "algo" + idCjaIngreso;
        algo = request.getParameter(algo);

        model.addAttribute("algo", algo);

        TextoFecha textoFecha = new TextoFecha();
        Date fecIngresoF = textoFecha.parseFecha(FecIngresoV);
        Date fecGastoE = textoFecha.parseFecha(fecGastoV);
        model.addAttribute("f1", fecIngresoF);
        model.addAttribute("f2", fecGastoE);

        model.addAttribute("cjaIng", "cjaIngresosFondos");
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());

        model.addAttribute("bCajaIngreso", cServicios.buscarCjaIngresoPorIdCjaIngreso(idCjaIngreso));

        model.addAttribute("lTiposCuenta", cServicios.listarGastoEjecutadosPorIdUsuarioFecIngresoFecPago((Long) session.getAttribute("currentUserId"), idCjaIngreso, fecIngresoF, fecGastoE));

        Long cont = idCjaIngreso - 1;
        model.addAttribute("idC", idCjaIngreso);
        CjaIngresos saldoAnterior = null;

        while (saldoAnterior == null && cont > 0) {
            saldoAnterior = cServicios.buscarCjaIngresoPorIdCjaIngresoIdEstadoI(cont, bPersona.getIdPersona());
            cont--;
        }

        model.addAttribute("saldoAnterior", saldoAnterior);
        getDesplegarListasComunes(model, session);
        return "formReporteArqueoGlobalImp";

    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("lIngresos", cServicios.listarIngresosPorIdPersona(personas.getIdPersona()));
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());
        model.addAttribute("repArqueoGlobal", "reporteArqueoGlobalCuenta");
    }

}
