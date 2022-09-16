package uap.usic.siga.controladores.sac.reportes.porMesGestion;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SacArchivoContableServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

/**
 * Rectorado - USIC
 * Fecha: 2019-09-30
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/porMesGestion")
public class PorMesGestion {
    
    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private SacArchivoContableServicios aServicios;

    @GetMapping("/inicioMesGestion")
    public String formInicioReporteMesGestion(HttpSession session, Model model) throws IOException {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioMesGestion";
    }

    @PostMapping("/listarMesesComp")
    public String listarMesesComprobantes(@RequestParam("gestion") Integer gestion,
            @RequestParam("idSacEstante") Long idSacEstante, Model model, HttpSession session) throws IOException {

        model.addAttribute("lMeses", aServicios.listarComprobantesMesesPorIdSacEstanteGestion(idSacEstante, gestion));
        model.addAttribute("urlC", "listarCarpetasComp");
        model.addAttribute("idSacEstante", idSacEstante);
        model.addAttribute("operation", "month");
        getDesplegarListasComunes(model, session);
        return "inicioMesGestion";

    }

    @PostMapping("/listarCarpetasComp")
    public String listarCarpetasComprobantes(@RequestParam("idMes") Long idMes,
                                             @RequestParam("idSacEstante") Long idSacEstante, Model model, HttpSession session) throws IOException {

        model.addAttribute("lCarpetas", aServicios.listarComprobantesCarpetasPorIdMes(idMes, idSacEstante));
        model.addAttribute("urlComp", "listarComprobantesReport");
        model.addAttribute("operation", "folder");
        getDesplegarListasComunes(model, session);
        return "inicioMesGestion";
    }

    @PostMapping("/listarComprobantesReport")
    public String listarComprobantesReportes(@RequestParam("idSacCarpeta") Long idSacCarpeta, Model model, HttpSession session) throws IOException {

        model.addAttribute("lSacComprobantes", aServicios.listarSacComprobantesPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("bSacCarpeta" ,aServicios.buscarCarpetasPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("urlPrint", "imprimirReporteComprobante");
        model.addAttribute("operation", "compr");
        model.addAttribute("lRazonSocial", aServicios.listarSacRazonSocialPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("lNroCheque", aServicios.listarNumerosChequesPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("lArchivos", aServicios.listarArchivosAjuntosPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("lNroComprobante", aServicios.listarNumeroComprobantesPorIdSacCarpeta(idSacCarpeta));
        getDesplegarListasComunes(model, session);
        return "inicioMesGestion";
    }
    
    @PostMapping("/imprimirReporteComprobante")
    public String imprimirReporteComprobantes(@RequestParam("idSacCarpeta") Long idSacCarpeta, Model model, HttpSession session) throws IOException {

        model.addAttribute("lSacComprobantes", aServicios.listarSacComprobantesPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("bSacCarpeta" ,aServicios.buscarCarpetasPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("urlPrint", "updateVoucherFile");
        model.addAttribute("operation", "compr");
        model.addAttribute("lRazonSocial", aServicios.listarSacRazonSocialPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("lNroCheque", aServicios.listarNumerosChequesPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("lArchivos", aServicios.listarArchivosAjuntosPorIdSacCarpeta(idSacCarpeta));
        model.addAttribute("lNroComprobante", aServicios.listarNumeroComprobantesPorIdSacCarpeta(idSacCarpeta));
        getDesplegarListasComunes(model, session);
        
        //return "uap/usic/siga/sac/reportes/porMesGestion/imprimirReporteComprobantes";
        //return "inicioMesGestion";
        return "imprimirReporteComprobanteImp";
    }
    
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        //Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("urlF", "listarMesesComp");
        model.addAttribute("urlM", "inicioModificarComprobante");
        model.addAttribute("urlD", "inicioEliminarComprobante");
        model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
        model.addAttribute("lEstantes", aServicios.listarComprobantesEstantesGestion());
        model.addAttribute("urlReg", "registerVoucherFile");
 
    }

}
