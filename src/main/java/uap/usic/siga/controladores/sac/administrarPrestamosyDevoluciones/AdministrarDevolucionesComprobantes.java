package uap.usic.siga.controladores.sac.administrarPrestamosyDevoluciones;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SacComprobantes;
import uap.usic.siga.entidades.SacDevolucionesComprobantes;
import uap.usic.siga.entidades.SacDevolucionesComprobantesDetalles;
import uap.usic.siga.entidades.SacEstadosComprobantes;
import uap.usic.siga.entidades.SacPrestamosComprobantes;
import uap.usic.siga.entidades.SacPrestamosComprobantesDetalles;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SacArchivoContableServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/devoluciones")
public class AdministrarDevolucionesComprobantes {

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private SacArchivoContableServicios aServicios;

    @Autowired
    private CajitaServicios cServicios;

    @GetMapping("/inicioDevoluciones")
    public String formInicioComprobantes(@ModelAttribute("sacDevolucionesComprobantes") SacDevolucionesComprobantes sacDevolucionesComprobantes,
            @ModelAttribute("personas") Personas personas,
            HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioDevoluciones";
    }

    @PostMapping(value = "/formNuevaDevolucion")
    public String formNuevoComprobante(@ModelAttribute("sacDevolucionesComprobantes") SacDevolucionesComprobantes sacDevolucionesComprobantes,
            @ModelAttribute("personas") Personas personas,
            @ModelAttribute("sacDevolucionesComprobantesDetalles") SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles,
            HttpSession session, Model model, BindingResult result) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "inicioDevoluciones";
    }

    @PostMapping(value = "/registrarDevolucionComprobantes")
    public String registrarComprobantePrestamos(@Valid SacDevolucionesComprobantes sacDevolucionesComprobantes, BindingResult result, HttpSession session, Model model, HttpServletRequest request,
            @RequestParam("idSacComprobante") Long idSacComprobante[]
    ) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioDevoluciones";
        }
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        sacDevolucionesComprobantes.setUsuarios(usuarios);
        aServicios.registrarSacDevolucionesComprobantes(sacDevolucionesComprobantes);

        for (int j = 0; j < idSacComprobante.length; j++) {
            SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles = new SacDevolucionesComprobantesDetalles();
            SacComprobantes bSacComprobantes = aServicios.buscarSacComprobantesPorIdSacComprobantes(idSacComprobante[j]);
            sacDevolucionesComprobantesDetalles.setSacComprobantes(bSacComprobantes);
            sacDevolucionesComprobantesDetalles.setSacDevolucionesComprobantes(sacDevolucionesComprobantes);
            sacDevolucionesComprobantesDetalles.setUsuarios(usuarios);

            String idSacEstadocComprobante = "idSacEstadoComprobante" + idSacComprobante[j];
            String idSacEstadocComprobanteV = request.getParameter(idSacEstadocComprobante);
            Long idSacEstadocComprobante1 = Long.parseLong(idSacEstadocComprobanteV);

            String idSacPrestamoComprobante = "idSacPrestamoComprobante" + idSacComprobante[j];
            String idSacPrestamoComprovanteV = request.getParameter(idSacPrestamoComprobante);
            Long idSacPrestamoComprobante1 = Long.parseLong(idSacPrestamoComprovanteV);

            SacEstadosComprobantes bEstadoComprobantes = aServicios.buscarSacEstadoComprobantesPorIdSacEstadoComprobante(idSacEstadocComprobante1);
            sacDevolucionesComprobantesDetalles.setSacEstadosComprobantes(bEstadoComprobantes);

            SacPrestamosComprobantes bSacPresComp = aServicios.buscarSacPrestamosComprobantesPorIdSacPrestamoComprobante(idSacPrestamoComprobante1);
            sacDevolucionesComprobantesDetalles.setSacPrestamosComprobantes(bSacPresComp);

            aServicios.registrarDevolucionesComprobantesDetalles(sacDevolucionesComprobantesDetalles);
            SacPrestamosComprobantesDetalles bSacPrestamoDet = aServicios.buscarSacPrstamoDetallesPorIdSacComprobanteyPrestamo(idSacComprobante[j], true, bSacPresComp.getIdSacPrestamoComprobante());

            java.util.Date fecha = new Date();
            bSacPrestamoDet.setPrestamo(false);
            //    bSacPrestamoDet.setUsuarios(usuarios);
            bSacPrestamoDet.setModificacion(fecha);
            aServicios.actualizarPrestamoComprobanteDetalle(bSacPrestamoDet);
            getDesplegarListasComunes(model, session);
        }

        SacDevolucionesComprobantes bDevolucionComp = aServicios.buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobante(sacDevolucionesComprobantes.getIdSacDevolucionComprobante());;
        model.addAttribute("bDevolucionComp", bDevolucionComp);

        Personas bPersona = pServicio.buscarPersonaPorIdUsuario(bDevolucionComp.getUsuarios().getIdUsuario());
        model.addAttribute("bPersona", bPersona);

        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(bPersona.getIdPersona()));
        model.addAttribute("lDevCompDetalles", aServicios.listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobante(sacDevolucionesComprobantes.getIdSacDevolucionComprobante()));

        DateFormat fecDevolucion = new SimpleDateFormat(" dd 'de' MMMM 'del' yyyy ");
        String fechaDev = fecDevolucion.format(bDevolucionComp.getFecDevolucion());
        model.addAttribute("fechaDevolucion", fechaDev);

        model.addAttribute("vistaPrevia", "vista");
        getDesplegarListasComunes(model, session);
        return "inicioDevoluciones";
    }

    @PostMapping(value = "/imprimirSacDevolucionesComprobante")
    public String imprimirSacPrestamoComprobante(@Valid SacDevolucionesComprobantes sacDevolucionesComprobantes, BindingResult result, HttpSession session, Model model, HttpServletRequest request,
            @RequestParam("idSacDevolucionComprobante") Long idSacDevolucionComprobante) throws IOException {

        SacDevolucionesComprobantes bDevolucionComp = aServicios.buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobante(idSacDevolucionComprobante);;
        model.addAttribute("bDevolucionComp", bDevolucionComp);

        Personas bPersona = pServicio.buscarPersonaPorIdUsuario(bDevolucionComp.getUsuarios().getIdUsuario());
        model.addAttribute("bPersona", bPersona);

        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(bPersona.getIdPersona()));
        model.addAttribute("lDevCompDetalles", aServicios.listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobante(idSacDevolucionComprobante));

        DateFormat fecDevolucion = new SimpleDateFormat(" dd 'de' MMMM 'del' yyyy ");
        String fechaDev = fecDevolucion.format(bDevolucionComp.getFecDevolucion());
        model.addAttribute("fechaDevolucion", fechaDev);

        model.addAttribute("vistaPrevia", "vista");
        getDesplegarListasComunes(model, session);
        //return "uap/usic/siga/sac/administrarDevoluciones/imprimirActaDevolucion";
        return "imprimirSacDevolucionesComprobanteImp";
    }

    @PostMapping(value = "/formInicioModificarDevolucion")
    public String formInicioModificarDevolucionComprobantes(@ModelAttribute("sacDevolucionesComprobantes") SacDevolucionesComprobantes sacDevolucionesComprobantes,
            @ModelAttribute("personas") Personas personas,
            HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacDevolucionComprobante") Long idSacDevolucionComprobante) throws IOException {

        model.addAttribute("lDevCompDetalles", aServicios.listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobante(idSacDevolucionComprobante));
        model.addAttribute("sacDevolucionesComprobantes", aServicios.buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobante(idSacDevolucionComprobante));;
        model.addAttribute("operationMod", "mod");
        getDesplegarListasComunes(model, session);
        return "inicioDevoluciones";
    }

    @PostMapping(value = "/confirmarModificacionDevolucion")
    public String confirmarModificarDevolucionComprobantes(@Valid @ModelAttribute("sacDevolucionesComprobantes") SacDevolucionesComprobantes sacDevolucionesComprobantes,
            HttpSession session, Model model, BindingResult result) throws IOException {

        aServicios.modificarDevolucionComprobantes(sacDevolucionesComprobantes);
        model.addAttribute("busqueda", "find");
        getDesplegarListasComunes(model, session);
        return "inicioDevoluciones";
    }

    @PostMapping(value = "/formInicioEliminarDevolucion")
    public String formInicioEliminarDevolucionComprobantes(@ModelAttribute("sacDevolucionesComprobantes") SacDevolucionesComprobantes sacDevolucionesComprobantes,   HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacDevolucionComprobante") Long idSacDevolucionComprobante) throws IOException {

        model.addAttribute("lDevCompDetalles", aServicios.listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobante(idSacDevolucionComprobante));
        model.addAttribute("sacDevolucionesComprobantes", aServicios.buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobante(idSacDevolucionComprobante));;

        model.addAttribute("operationElim", "Elim");
        getDesplegarListasComunes(model, session);
        return "inicioDevoluciones";
    }

    @PostMapping(value = "/confirmarEliminacionDevolucionComprobantes")
    public String confirmarEliminacionDevolucionComprobantesss(@Valid @ModelAttribute("sacDevolucionesComprobantes") SacDevolucionesComprobantes sacDevolucionesComprobantes,
             HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacDevolucionComprobante") Long idSacDevolucionComprobante) throws IOException {

        List<SacDevolucionesComprobantesDetalles> lDevCompDetalles = aServicios.listarSacDevolucionComprobantesDetallesPorIdSacDevolucionComprobante(idSacDevolucionComprobante);
        for (SacDevolucionesComprobantesDetalles devCompDet : lDevCompDetalles) {
            java.util.Date fechaM = new Date();
            devCompDet.setModificacion(fechaM);
            devCompDet.setIdEstado("X");
            aServicios.modificarDevolucionesComprobantesDetalles(devCompDet);

            SacPrestamosComprobantesDetalles bSacPrestamoDet = aServicios.buscarSacPrstamoDetallesPorIdSacComprobanteyPrestamo(devCompDet.getSacComprobantes().getIdSacComprobante(), false, devCompDet.getSacPrestamosComprobantes().getIdSacPrestamoComprobante());
            java.util.Date fecha = new Date();
            bSacPrestamoDet.setPrestamo(true);
            bSacPrestamoDet.setModificacion(fecha);
            aServicios.actualizarPrestamoComprobanteDetalle(bSacPrestamoDet);
            getDesplegarListasComunes(model, session);
        }
        sacDevolucionesComprobantes.setIdEstado("X");
        aServicios.eliminarSacDevolucionesComprobantes(sacDevolucionesComprobantes);
        getDesplegarListasComunes(model, session);
         model.addAttribute("busqueda", "find");
        return "inicioDevoluciones";
    }

    @PostMapping(value = "/guardarPersonasPrestamos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Personas> getListarPersonas(@ModelAttribute @Valid Personas personas, BindingResult result) {
        personas.setIdEstado("C");
        personas.setTelefono("123456");
        personas.setTipoSanguineo("Ninguno");
        personas.setDireccion("Ninguno");
        personas.setEmail("ninguno@gmail.com");
        aServicios.registrarPersonasSacComprobantes(personas);
        return pServicio.listarPersonasComprobantes();
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("urlNuevDev", "formNuevaDevolucion");
        model.addAttribute("urlImpActDev", "imprimirSacDevolucionesComprobante");
        model.addAttribute("urlIniModDev", "formInicioModificarDevolucion");
        model.addAttribute("urlRegDevComp", "registrarDevolucionComprobantes");
        model.addAttribute("confModiDev", "confirmarModificacionDevolucion");
        model.addAttribute("iniElimDevPres", "formInicioEliminarDevolucion");
        model.addAttribute("confElimDevoPrest", "confirmarEliminacionDevolucionComprobantes");
        model.addAttribute("lPersonas", pServicio.listarPersonasComprobantes());
        model.addAttribute("lComprobantes", aServicios.listarSacComprobantesGeneral());
        model.addAttribute("lPrestamosComprobantes", aServicios.listarSacPrestamosComprobantes());
        model.addAttribute("lEstados", aServicios.listarEstadosComprobantes());
        model.addAttribute("lSacNumComp", aServicios.listarNumerosComprobantes());
        model.addAttribute("lRazonSocialP", aServicios.listarSacRazonSocialGeneral());
        model.addAttribute("lPaises", pServicio.listarPaises());
        model.addAttribute("lExpedidos", pServicio.listarCedulaIdentidadexpedidos());
        model.addAttribute("lPrsTiposSexos", pServicio.listarTiposSexos());
        model.addAttribute("lGradosAcademicos", pServicio.listarGradosAcademicos());
        model.addAttribute("lDevolucionesGeneral", aServicios.listarSacDevolucionesComprobantes());
        model.addAttribute("lPrescDet", aServicios.listarPrestamosComprobantesDetallesGeneral());
       

    }

}
