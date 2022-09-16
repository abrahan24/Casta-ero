package uap.usic.siga.controladores.sac.administrarPrestamosyDevoluciones;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SacComprobantes;
import uap.usic.siga.entidades.SacDevolucionesComprobantes;
import uap.usic.siga.entidades.SacEstadosComprobantes;
import uap.usic.siga.entidades.SacPrestamosComprobantes;
import uap.usic.siga.entidades.SacPrestamosComprobantesDetalles;
import uap.usic.siga.entidades.SacRazonSocial;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SacArchivoContableServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 *
 * @author Yessenia Velasco A.
 */
@Controller
@RequestMapping("/prestamos")
public class AdministrarPrestamosComprobantes {

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private SacArchivoContableServicios aServicios;

    @Autowired
    private CajitaServicios cServicios;

    @GetMapping("/inicioPrestamos")
    public String formInicioComprobantes(@ModelAttribute("sacPrestamosComprobantes") SacPrestamosComprobantes sacPrestamosComprobantes,
            @ModelAttribute("personas") Personas personas,
            HttpSession session, Model model) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioPrestamos";
    }

    @PostMapping(value = "/formNuevoPrestamos")
    public String formNuevoComprobante(@ModelAttribute("sacPrestamosComprobantes") SacPrestamosComprobantes sacPrestamosComprobantes,
            @ModelAttribute("personas") Personas personas,
            @ModelAttribute("sacPrestamosComprobantesDetalles") SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles,
            HttpSession session, Model model, BindingResult result) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        return "inicioPrestamos";
    }

    @PostMapping(value = "/registrarPrestamosComprobantes")
    public String registrarComprobantePrestamos(@Valid SacPrestamosComprobantes sacPrestamosComprobantes, BindingResult result, HttpSession session, Model model, HttpServletRequest request,
            @RequestParam("idSacComprobante") Long idSacComprobante[]
    ) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioPrestamos";
        }
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        sacPrestamosComprobantes.setUsuarios(usuarios);
        sacPrestamosComprobantes.setPrestamo(true);
        aServicios.registrarSacPrestamosComprobantes(sacPrestamosComprobantes);

        for (int j = 0; j < idSacComprobante.length; j++) {
            SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles = new SacPrestamosComprobantesDetalles();
            SacComprobantes bSacComprobantes = aServicios.buscarSacComprobantesPorIdSacComprobantes(idSacComprobante[j]);
            sacPrestamosComprobantesDetalles.setSacComprobantes(bSacComprobantes);
            sacPrestamosComprobantesDetalles.setSacPrestamosComprobantes(sacPrestamosComprobantes);
            sacPrestamosComprobantesDetalles.setUsuarios(usuarios);
            sacPrestamosComprobantesDetalles.setPrestamo(true);
            String idSacEstadocComprobante = "idSacEstadoComprobante" + idSacComprobante[j];
            String idSacEstadocComprobanteV = request.getParameter(idSacEstadocComprobante);
            Long idSacEstadocComprobante1 = Long.parseLong(idSacEstadocComprobanteV);

            SacEstadosComprobantes bEstadoComprobantes = aServicios.buscarSacEstadoComprobantesPorIdSacEstadoComprobante(idSacEstadocComprobante1);
            sacPrestamosComprobantesDetalles.setSacEstadosComprobantes(bEstadoComprobantes);
            aServicios.registraSacPrestamosComprobantesDetalles(sacPrestamosComprobantesDetalles);

            getDesplegarListasComunes(model, session);

        }
        SacPrestamosComprobantes bPrestamoComp = aServicios.buscarSacPrestamosComprobantesPorIdSacPrestamoComprobante(sacPrestamosComprobantes.getIdSacPrestamoComprobante());
        model.addAttribute("bPrestamoComp", bPrestamoComp);

        Personas bPersona = pServicio.buscarPersonaPorIdUsuario(bPrestamoComp.getUsuarios().getIdUsuario());
        //Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("bPersona", bPersona);

        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(bPersona.getIdPersona()));
        model.addAttribute("lCompPrestaDetalles", aServicios.listarSacPrestamosComprobantesDetallesPorIdSacPrestamoComprobante(sacPrestamosComprobantes.getIdSacPrestamoComprobante()));

        DateFormat fecFin = new SimpleDateFormat(" dd 'días del mes de' MMMM 'del' yyyy ");
        String fechaPres = fecFin.format(bPrestamoComp.getFecPrestamo());
        model.addAttribute("fechaPrestamo", fechaPres);

        model.addAttribute("vistaPrevia", "vista");
        getDesplegarListasComunes(model, session);
        return "inicioPrestamos";
    }

    @PostMapping(value = "/imprimirSacPrestamoComprobante")
    public String imprimirSacPrestamoComprobante(@Valid SacPrestamosComprobantes sacPrestamosComprobantes, BindingResult result, HttpSession session, Model model, HttpServletRequest request,
            @RequestParam("idSacPrestamoComprobante") Long idSacPrestamoComprobante) throws IOException {

        SacPrestamosComprobantes bPrestamoComp = aServicios.buscarSacPrestamosComprobantesPorIdSacPrestamoComprobante(idSacPrestamoComprobante);
        model.addAttribute("bPrestamoComp", bPrestamoComp);

        Personas bPersona = pServicio.buscarPersonaPorIdUsuario(bPrestamoComp.getUsuarios().getIdUsuario());
        //Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("bPersona", bPersona);

        model.addAttribute("bPersonalAdministrativo", cServicios.buscarPersonalAdministrativoPorIdPersona(bPersona.getIdPersona()));
        model.addAttribute("lCompPrestaDetalles", aServicios.listarSacPrestamosComprobantesDetallesPorIdSacPrestamoComprobante(idSacPrestamoComprobante));

        DateFormat fecFin = new SimpleDateFormat(" dd 'días del mes de' MMMM 'del' yyyy ");
        String fechaPres = fecFin.format(bPrestamoComp.getFecPrestamo());
        model.addAttribute("fechaPrestamo", fechaPres);

        model.addAttribute("vistaPrevia", "vista");
        getDesplegarListasComunes(model, session);
        //return "uap/usic/siga/sac/administrarPrestamos/imprimirPrestamoComprobante";
        return "imprimirSacPrestamoComprobanteImp";
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

    @PostMapping(value = "/formInicioModificarPrestamos")
    public String formInicioModificarPrestamoComprobantes(@ModelAttribute("sacPrestamosComprobantes") SacPrestamosComprobantes sacPrestamosComprobantes,
            @ModelAttribute("personas") Personas personas,
            HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacPrestamoComprobante") Long idSacPrestamoComprobante) throws IOException {

        model.addAttribute("lCompPrestaDetalles", aServicios.listarSacPrestamosComprobantesDetallesPorIdSacPrestamo(idSacPrestamoComprobante));
        model.addAttribute("sacPrestamosComprobantes", aServicios.buscarSacPrestamosComprobantesPorIdSacPrestamoComprobante(idSacPrestamoComprobante));
        model.addAttribute("operationMod", "mod");
        getDesplegarListasComunes(model, session);
        return "inicioPrestamos";
    }

    @PostMapping(value = "/confirmarModificacionPrestamo")
    public String confirmarModificarPrestamoComprobantes(@Valid @ModelAttribute("sacPrestamosComprobantes") SacPrestamosComprobantes sacPrestamosComprobantes,
            HttpSession session, Model model, BindingResult result) throws IOException {

        aServicios.modificarPrestamosComprobantes(sacPrestamosComprobantes);
        model.addAttribute("busqueda", "find");
        getDesplegarListasComunes(model, session);
        return "inicioPrestamos";
    }

    @PostMapping(value = "/formInicioEliminarPrestamos")
    public String formInicioEliminarComprobantes(@ModelAttribute("sacPrestamosComprobantes") SacPrestamosComprobantes sacPrestamosComprobantes,
            HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacPrestamoComprobante") Long idSacPrestamoComprobante) throws IOException {

        model.addAttribute("lCompPrestaDetalles", aServicios.listarSacPrestamosComprobantesDetallesPorIdSacPrestamo(idSacPrestamoComprobante));
        model.addAttribute("sacPrestamosComprobantes", aServicios.buscarSacPrestamosComprobantesPorIdSacPrestamoComprobante(idSacPrestamoComprobante));
        model.addAttribute("operationElimP", "ElimP");
        getDesplegarListasComunes(model, session);
        
        return "inicioPrestamos";
    }

    @PostMapping(value = "/confirmarEliminacionPrestamo")
    public String confirmarEliminacionPrestamoComprobantes(@Valid @ModelAttribute("sacPrestamosComprobantes") SacPrestamosComprobantes sacPrestamosComprobantes, HttpSession session, Model model, BindingResult result,
            @RequestParam("idSacPrestamoComprobante") Long idSacPrestamoComprobante) throws IOException {

        List<SacPrestamosComprobantesDetalles> lPrestCompDet = aServicios.listarSacPrestamosComprobantesDetallesPorIdSacPrestamo(idSacPrestamoComprobante);
        for (SacPrestamosComprobantesDetalles prestCompDet : lPrestCompDet) {
            prestCompDet.setIdEstado("X");
            aServicios.eliminarSacPrestamosComprobantesDetalles(prestCompDet);
            getDesplegarListasComunes(model, session);

        }
        sacPrestamosComprobantes.setIdEstado("X");
        aServicios.modificarPrestamosComprobantes(sacPrestamosComprobantes);
        model.addAttribute("lCompPrestaDetalles", aServicios.listarSacPrestamosComprobantesDetallesPorIdSacPrestamo(idSacPrestamoComprobante));
        model.addAttribute("lPrestamosComprobantes", aServicios.listarSacPrestamosComprobantes());
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioPrestamos";
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {

        model.addAttribute("urlNuevComp", "formNuevoPrestamos");
        model.addAttribute("urlImpPrest", "imprimirSacPrestamoComprobante");
        model.addAttribute("irlInicioModPrest", "formInicioModificarPrestamos");
        model.addAttribute("urlRegPres", "registrarPrestamosComprobantes");
        model.addAttribute("confModiPres", "confirmarModificacionPrestamo");
        model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
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
        model.addAttribute("lPrescDet", aServicios.listarPrestamosComprobantesDetallesGeneral());
        model.addAttribute("urlIniElimPres", "formInicioEliminarPrestamos");
        model.addAttribute("confEliminacionPrest", "confirmarEliminacionPrestamo");

    }

    @RequestMapping(value = "/eliminarPrestamoDetalles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SacPrestamosComprobantesDetalles eliminarComprobantesPrestamoDetalles(@PathVariable("id") Long id) {
        SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles = aServicios.buscarPrestamosDetallesPorIdSacPrestamoDetalle(id);
        sacPrestamosComprobantesDetalles.setIdEstado("X");
        return aServicios.eliminarSacPrestamosComprobantesDetalles(sacPrestamosComprobantesDetalles);
    }

}
