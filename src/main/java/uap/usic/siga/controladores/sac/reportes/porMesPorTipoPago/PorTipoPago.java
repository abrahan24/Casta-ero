package uap.usic.siga.controladores.sac.reportes.porMesPorTipoPago;

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

/**
 * Rectorado - USIC
 * Fecha: 2019-10-21
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/porTipoPago")
public class PorTipoPago {
    
    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private SacArchivoContableServicios aServicios;

    @GetMapping("/inicioPorTipoPago")
    public String formInicioReportePrTipoPago(HttpSession session, Model model) throws IOException {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioPorTipoPago";
    }

    @PostMapping("/listarMesesTipoPago")
    public String listarMesesComprobantes(@RequestParam("gestion") Integer gestion, Model model, HttpSession session) throws IOException {

        model.addAttribute("lMeses", aServicios.listarMesesComprobantesPorGestion(gestion));
        model.addAttribute("urlC", "listarTiposPagos");
        model.addAttribute("urlV", "inicioPorTipoPago");
        model.addAttribute("operation", "month");
        model.addAttribute("gestion", gestion);
        getDesplegarListasComunes(model, session);
        return "inicioPorTipoPago";
    }
 
    @PostMapping("/listarTiposPagos")
    public String listarTiposPagos(@RequestParam("idMes") Long idMes,
          @RequestParam("gestion") Integer gestion, Model model, HttpSession session) throws IOException {

        model.addAttribute("lTiposPagos", aServicios.listarTiposPagos());
        model.addAttribute("urlComp", "listarComprobantesPorTipoPago");
        model.addAttribute("urlMo", "listarMesesTipoPago");
        model.addAttribute("idMes", idMes);
        model.addAttribute("gestion", gestion);
        model.addAttribute("operation", "tipoPago");
        getDesplegarListasComunes(model, session);
        return "inicioPorTipoPago";
    }
 
    @PostMapping("/listarComprobantesPorTipoPago")
    public String listarComprobantesReportes(@RequestParam("idSacTipoPago") Long idSacTipoPago,
        @RequestParam("idMes") Long idMes, 
        @RequestParam("gestion") Integer gestion, Model model, HttpSession session) throws IOException {

        model.addAttribute("lSacComprobantes", aServicios.listarSacComprobantesPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("bSisMeses" ,aServicios.buscarSisMesesPorIdMes(idMes));
        model.addAttribute("bSacTiposPagos" ,aServicios.buscarSacTiposPagosPorIdSacTipoPago(idSacTipoPago));
        model.addAttribute("urlPrint", "imprimirComprobantesPorTipoPago");
        model.addAttribute("urlTp", "listarTiposPagos");
        model.addAttribute("operation", "compr");
        model.addAttribute("lRazonSocial", aServicios.listarSacRazonSocialPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("lNroCheque", aServicios.listarNumerosChequesPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("lArchivos", aServicios.listarArchivosAjuntosPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("lNroComprobante", aServicios.listarNumeroComprobantesPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("idMes", idMes);
        model.addAttribute("idSacTipoPago", idSacTipoPago);
        model.addAttribute("gestion", gestion);
        model.addAttribute("idSacTipoPago", idSacTipoPago);
        getDesplegarListasComunes(model, session);
        return "inicioPorTipoPago";
    }
    
    @PostMapping("/imprimirComprobantesPorTipoPago")
    public String imprimirComprobantesReportes(@RequestParam("idSacTipoPago") Long idSacTipoPago,
        @RequestParam("idMes") Long idMes, 
        @RequestParam("gestion") Integer gestion, Model model, HttpSession session) throws IOException {

        model.addAttribute("lSacComprobantes", aServicios.listarSacComprobantesPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("bSisMeses" ,aServicios.buscarSisMesesPorIdMes(idMes));
        model.addAttribute("bSacTiposPagos" ,aServicios.buscarSacTiposPagosPorIdSacTipoPago(idSacTipoPago));
        model.addAttribute("urlPrint", "imprimirReporteComprobante");
        model.addAttribute("operation", "compr");
        model.addAttribute("lRazonSocial", aServicios.listarSacRazonSocialPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("lNroCheque", aServicios.listarNumerosChequesPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("lArchivos", aServicios.listarArchivosAjuntosPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("lNroComprobante", aServicios.listarNumeroComprobantesPorIdSacTipoPagoIdMesIdSacEstante(idSacTipoPago, idMes, gestion));
        model.addAttribute("idMes", idMes);
        model.addAttribute("idSacTipoPago", idSacTipoPago);
        model.addAttribute("gestion", gestion);
        model.addAttribute("idSacTipoPago", idSacTipoPago);
        getDesplegarListasComunes(model, session);
        //return "uap/usic/siga/sac/reportes/porTipoPago/imprimirReporteComprobantes";
        //return "inicioPorTipoPago";
        return "imprimirComprobantesPorTipoPagoImp";
    }
   
    public void getDesplegarListasComunes(Model model, HttpSession session) {

        //Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("urlF", "listarMesesTipoPago");
        model.addAttribute("urlM", "inicioModificarComprobante");
        model.addAttribute("urlD", "inicioEliminarComprobante");
        model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
        model.addAttribute("lEstantes", aServicios.listarComprobantesEstantesGestion());
        model.addAttribute("urlReg", "registerVoucherFile");
 
    }

    
}
