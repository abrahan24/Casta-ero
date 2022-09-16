package uap.usic.siga.controladores.reporteGastosPorCuenta;

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
import uap.usic.siga.entidades.PdfUserDetails;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.funciones.TextoFecha;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia Velasco
 */
@Controller
@RequestMapping("/reportes")
public class ReporteGastosPorCuenta {

    @Autowired
    private CajitaServicios cServicios;

    @Autowired
    private PersonasServicios pServicios;

    @GetMapping(value = "/formReporteGastosPorCuenta")
    public String reporteGastosPorCuenta(@ModelAttribute("cjaGastosEjecutado") CjaGastosEjecutados cjaGastosEjecutado, Model model, HttpSession session) {
        model.addAttribute("repPorCuentaG", "reporteGastosPorCuentas");

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        Usuarios usuarios = ((PdfUserDetails) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("currentUserId", usuarios.getIdUsuario());

        Personas personas = pServicios.buscarPersonaPorIdUsuario(usuarios.getIdUsuario());
        model.addAttribute("lIngresos", cServicios.listarIngresosPorIdPersona(personas.getIdPersona()));
        // model.addAttribute("lCjaGastosEjecutados", cServicios.listarCjaGastosEjecutadosPorIdCjaIngresoIdPersona(1l, personas.getIdPersona()));
        model.addAttribute("personas", personas);
        return "gastosPorCuenta";
    }

    @PostMapping(value = "/reporteGastosPorCuentas")
    public String formIngresos(@ModelAttribute("cjaGastosEjecutado") CjaGastosEjecutados cjaGastosEjecutaso, Model model,
            @RequestParam("idCjaIngreso") Long idCjaIngreso,
            //  @RequestParam("fecIngreso1") String fecI,
            // @RequestParam("fecGasto1") String fecG,
            HttpServletRequest request, HttpSession session) {

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

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        Usuarios usuarios = ((PdfUserDetails) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("currentUserId", usuarios.getIdUsuario());

        Personas personas = pServicios.buscarPersonaPorIdUsuario(usuarios.getIdUsuario());
        model.addAttribute("personas", personas);
        model.addAttribute("lTiposCuenta", cServicios.listarGastoEjecutadosPorIdUsuarioFecIngresoFecPago((Long) session.getAttribute("currentUserId"), idCjaIngreso, fecIngresoF, fecGastoE));
        model.addAttribute("lTipoClasificacion", cServicios.listarTiposClasificacionResponseIdUsuarioIdCjaIngreso((Long) session.getAttribute("currentUserId"), idCjaIngreso, fecIngresoF, fecGastoE));
        model.addAttribute("lGastoClasificacion", cServicios.listarGastosClasificacionResponseIdUsuarioIdCajaIngreso((Long) session.getAttribute("currentUserId"), idCjaIngreso));

        //return "reportePorCuenta";
        return "formReportePorCuentaGastosImp";
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof PdfUserDetails)) {
            throw new IllegalArgumentException("Principal can not be null!");
        }
    }

}
