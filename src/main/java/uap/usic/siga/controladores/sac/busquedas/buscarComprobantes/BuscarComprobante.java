package uap.usic.siga.controladores.sac.busquedas.buscarComprobantes;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uap.usic.siga.entidades.SacCompArchivosAdjuntos;
import uap.usic.siga.entidades.SacComprobantes;
import uap.usic.siga.entidades.SacRazonSocial;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SacArchivoContableServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Rectorado - USIC
 * Fecha: 2019-10-24 
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/findVoucher")
public class BuscarComprobante {
    
    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private SacArchivoContableServicios aServicios;

    @GetMapping("/inicioBuscarComprobante")
    public String formInicioBuscarComprobante(HttpSession session, Model model) throws IOException {

        getDesplegarListasComunes(model, session);
        model.addAttribute("operation", "find");
        return "inicioBuscarComprobante";
    }
     
    @PostMapping("/filtrarCompRazonSocial")
    public String buscarComprobantesPorRazonSocial(@RequestParam("idSacTipoComprobante") Long idSacTipoComprobante,
          @RequestParam("nombres") String nombres,
          @RequestParam("sacNroComprobante") Integer sacNroComprobante, 
          @RequestParam("sacNroCheque") Integer sacNroCheque, Model model, HttpSession session) throws IOException {
            
        if(!nombres.equals("")){
            model.addAttribute("lSacComprobantesResponseRs", aServicios.listarSacComprobantesResponsePorRazonSocialGestionIdSacTipoComprobante(idSacTipoComprobante, nombres));
            model.addAttribute("listarRazonSocial", "lRazSoc");
        }
        
        if(sacNroComprobante > 0){
            model.addAttribute("lSacComprobantesResponseNc", aServicios.listarSacComprobantesResponsePorSacNroComprobanteIdSacTipoComprobante(idSacTipoComprobante, sacNroComprobante));
            model.addAttribute("listarNroComprobante", "lNroComp");
        }
        
        if(sacNroCheque > 0){
            model.addAttribute("lSacComprobantesResponseNch", aServicios.listarSacComprobantesResponsePorSacNroChequeIdSacTipoComprobante(idSacTipoComprobante, sacNroCheque));
            model.addAttribute("listarNroCheque", "lNroChe");
        }
        
        model.addAttribute("operation", "find");
        getDesplegarListasComunes(model, session);
        return "inicioBuscarComprobante";
    }
 
    @PostMapping("/buscarSacComprobante")
    public String buscarSacComprobantes(@RequestParam("idSacComprobante") Long idSacComprobante, Model model, HttpSession session) throws IOException {
       
        SacCompArchivosAdjuntos sacCompArchivosAdjuntos = aServicios.buscarSacComprobantesArchivoAdjuntoPorIdSacComprobante(idSacComprobante);
        model.addAttribute("arch", sacCompArchivosAdjuntos);
        model.addAttribute("sacComprobantes", aServicios.buscarSacComprobantesPorIdSacComprobantes(idSacComprobante));
        model.addAttribute("lRazonSocial", aServicios.listarRazonSocialPersonasPorIdSacComprobante(idSacComprobante));
        model.addAttribute("lNroCheques", aServicios.listarNumeroChequesPorIdSacComprobante(idSacComprobante));
        model.addAttribute("lNroComprobantes", aServicios.listarNumeroComprobantesPorIdSacComprobante(idSacComprobante));
        model.addAttribute("urlVol", "inicioBuscarComprobante");
        model.addAttribute("operation", "vista");
        model.addAttribute("nroCompMod", "editNroCompMod");
        model.addAttribute("iModNroComp", "inicioModificarNroComprobantes");
        getDesplegarListasComunes(model, session);
        return "inicioBuscarComprobante";
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("urlB", "filtrarCompRazonSocial");
        model.addAttribute("urlFind", "buscarSacComprobante");
        model.addAttribute("urlD", "buscarSacComprobante");
        model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
        model.addAttribute("lTiposComprobantes", aServicios.listarTiposComprobantes());
        model.addAttribute("urlReg", "registerVoucherFile");
 
    }

}
