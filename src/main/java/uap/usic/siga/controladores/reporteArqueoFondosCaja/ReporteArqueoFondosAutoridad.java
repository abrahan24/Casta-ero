package uap.usic.siga.controladores.reporteArqueoFondosCaja;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.funciones.TextoFecha;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/reportes")
public class ReporteArqueoFondosAutoridad {

    @Autowired
    private CajitaServicios cServicios;

    @Autowired
    private PersonasServicios pServicios;

    @Autowired
    private InstitucionesServicios iServicio;

    @GetMapping(value = "/formReporteArqueoFondosCajaAutoridad")
    public String reporteArqueoCajasAutoridad(@ModelAttribute("cjaGastosEjecutado") CjaGastosEjecutados cjaGastosEjecutado, Model model, HttpSession session) {

        model.addAttribute("operation", "form");
        getDesplegarListasComunes(model, session);
        return "arqueoCajaAurotidad";
    }

    @RequestMapping(value = "/lDireccionesArqueo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsDireccionesFuncionales> getListarDireccionesFuncionales(@PathVariable("id") long id) {
        return iServicio.listarDireccionesFuncionalesPorIdSede(id);
    }

    @RequestMapping(value = "/lUnidadesArqueo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsUnidadesFuncionales> getListarUnidadesFuncionales(@PathVariable("id") long id) {
        return iServicio.listarUnidadesFuncionalesPorIdDireccionFuncional(id);
    }

    @PostMapping(value = "/listarUnidadesFuncionales")
    public String formListarUnidadesFuncionales(@ModelAttribute("cjaGastosEjecutado") CjaGastosEjecutados cjaGastosEjecutado, Model model, HttpSession session,
            @RequestParam("idUnidadFuncional") Long idUnidadFuncional,
            @RequestParam("gestion") Integer gestion) {
        model.addAttribute("operation", "unidad");
        model.addAttribute("lIngresosU", cServicios.listarCjaIngresosPorIdUnidadFuncionalGestion(idUnidadFuncional, gestion));
        model.addAttribute("idUnidad", idUnidadFuncional);
        model.addAttribute("gestion", gestion);
        getDesplegarListasComunes(model, session);

        return "arqueoCajaAurotidad";
    }
    
    @PostMapping(value = "/reporteArqueoFondosCajaAutoridad")
    public String reporteArqueoFondosAutoridad(@ModelAttribute("cjaGastosEjecutado") CjaGastosEjecutados cjaGastosEjecutaso, Model model,
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

        getDesplegarListasComunes(model, session);
        model.addAttribute("bCajaIngreso", cServicios.buscarCjaIngresoPorIdCjaIngreso(idCjaIngreso));

        model.addAttribute("lGastosA", cServicios.listarGastoEjecutadosPorIdCajaIngresoFecIngresoFecPago(idCjaIngreso, fecIngresoF, fecGastoE));
        Long cont = idCjaIngreso - 1;
        model.addAttribute("idC", idCjaIngreso);
        CjaIngresos saldoAnterior = null;

        while (saldoAnterior == null && cont > 0) {
            saldoAnterior = cServicios.buscarCjaIngresoPorIdCjaIngresoIdEstadoI(cont, bPersona.getIdPersona());
            cont--;
        }
        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(bPersona.getIdPersona()));
        model.addAttribute("saldoAnterior", saldoAnterior);
        return "formReporteArqueoCajaAutoridadImp";

    }


    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("lSedes", iServicio.listarInsSedes());
        model.addAttribute("mostrarUnidaades", "listarUnidadesFuncionales");
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());
        model.addAttribute("repArqCajaAutoridad", "reporteArqueoFondosCajaAutoridad");
    }

}
