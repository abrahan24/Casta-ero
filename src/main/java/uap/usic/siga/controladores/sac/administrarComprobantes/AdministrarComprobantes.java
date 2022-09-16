package uap.usic.siga.controladores.sac.administrarComprobantes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.multipart.MultipartFile;
import uap.usic.siga.dto.PersonasResponse;
import uap.usic.siga.entidades.ArchivoComprobante;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SacCarpetas;
import uap.usic.siga.entidades.SacCompArchivosAdjuntos;
import uap.usic.siga.entidades.SacCompNroCheque;
import uap.usic.siga.entidades.SacCompNroComprobante;
import uap.usic.siga.entidades.SacComprobantes;
import uap.usic.siga.entidades.SacEstantes;
import uap.usic.siga.entidades.SacRazonSocial;
import uap.usic.siga.entidades.SacTiposComprobantes;
import uap.usic.siga.entidades.SacTiposPagos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SacArchivoContableServicios;
import uap.usic.siga.servicios.UsuariosServicios;
import uap.usic.siga.utilidades.AdjuntarArchivo;

/**
 * Rectorado - USIC Fecha: 2019-08-22
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/comprobante")
public class AdministrarComprobantes {

    @Autowired
    private CajitaServicios cServicio;

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private SacArchivoContableServicios aServicios;

    @GetMapping("/inicioComprobantes")
    public String formInicioComprobantes(@ModelAttribute("archivoComprobante") ArchivoComprobante archivoComprobante, HttpSession session, Model model) throws IOException {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
       // model.addAttribute("lr", pServicio.buscarPersonaRazonSocialPorIdPersonaResponse(12l));
        return "inicioComprobantes";
    }

    @PostMapping(value = "/fornNuevoComprobante")
    public String formNuevoComprobante(@ModelAttribute("sacComprobantes") SacComprobantes sacComprobantes,
            @ModelAttribute("personas") Personas personas,
            HttpSession session, Model model, BindingResult result) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model, session);
        model.addAttribute("urlReg", "registerVoucherFile");
        return "inicioComprobantes";
    }

    @PostMapping(value = "/registerVoucherFile")
    public String registrarComprobanteFile(@Valid SacComprobantes sacComprobantes, BindingResult result, HttpSession session, Model model, HttpServletRequest request,
            @RequestParam("nroCheque2") Integer nroCheque2[],
            @RequestParam("nroComprobante2") Integer nroComprobante2[],
            @RequestParam("idPersona2") Long idPersona2[]) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "inicioComprobantes";
        }

        MultipartFile multipartFile = sacComprobantes.getFile();
        SacCompArchivosAdjuntos sacCompArchivosAdjuntos = new SacCompArchivosAdjuntos();
        Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        SacCarpetas sacCarpetas = aServicios.buscarCarpetasPorIdSacCarpeta(sacComprobantes.getSacCarpetas().getIdSacCarpeta());
        model.addAttribute("cap", sacCarpetas);

        SacComprobantes sComprobantes = aServicios.registrarSacComprobanteDos(sacComprobantes);

        String rutaArchivo = adjuntarArchivo.crearSacDirectorio(sacCarpetas.getSacEstantes().getSacNombreEstante() + "/" + sacCarpetas.getSacCodigoCarpeta());
        model.addAttribute("di", rutaArchivo);
        Integer ad = adjuntarArchivo.adjuntarArchivo(sacComprobantes, rutaArchivo);

        sacCompArchivosAdjuntos.setNombreArchivo(sacComprobantes.getNombreArchivo());
        sacCompArchivosAdjuntos.setTipoArchivo(multipartFile.getContentType());
        sacCompArchivosAdjuntos.setUsuarios(usuarios);
        sacCompArchivosAdjuntos.setSacComprobantes(sComprobantes);
        sacCompArchivosAdjuntos.setRutaArchivo(sacCarpetas.getSacEstantes().getSacNombreEstante() + "/" + sacCarpetas.getSacCodigoCarpeta() + "/");

        aServicios.adjuntarArchivoComprobante(sacCompArchivosAdjuntos);

        for (Long idPersona : idPersona2) {
            SacRazonSocial sacRazonSocial = new SacRazonSocial();
            Personas personas = pServicio.buscarPersonasComprobantesPorIdPersona(idPersona);
            sacRazonSocial.setPersonas(personas);
            sacRazonSocial.setSacComprobantes(sacComprobantes);
            aServicios.registrarRazonSocial(sacRazonSocial);
            getDesplegarListasComunes(model, session);

        }

        for (Integer nroCheque : nroCheque2) {
            SacCompNroCheque sacNroCheque = new SacCompNroCheque();
            sacNroCheque.setSacNroCheque(nroCheque);
            sacNroCheque.setSacComprobantes(sacComprobantes);
            aServicios.registrarSacCompNroCheque(sacNroCheque);
            getDesplegarListasComunes(model, session);

        }

        for (Integer nroComprobante : nroComprobante2) {
            SacCompNroComprobante sacNroComprobante = new SacCompNroComprobante();
            sacNroComprobante.setSacNroComprobante(nroComprobante);
            sacNroComprobante.setSacComprobantes(sacComprobantes);
            aServicios.registrarSacCompNroComprobantes(sacNroComprobante);
            getDesplegarListasComunes(model, session);

        }

        /*
        InputStream is = (new URL("E:/Upload/") {}).openStream();
        byte[] pdfBytes = IOUtils.toByteArray(is);
        model.addAttribute("pdf",new String(Base64.getEncoder().encode(pdfBytes)));
         */
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        model.addAttribute("saludo", idPersona2);
        return "inicioComprobantes";
    }

    @RequestMapping(value = "/lCarpetas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<SacCarpetas> getListarCarpetasPorIdSacEstantes(@PathVariable("id") long id) {
        return aServicios.listarCarpetasPorIdSacEstante(id);

    }

    @PostMapping(value = "/guardarSacTiposComprobantes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<SacTiposComprobantes> getListarSacTiposComprobanteses(@ModelAttribute @Valid SacTiposComprobantes sacTiposComprobantes, BindingResult result) {
        aServicios.registrarSacTiposComprobantes(sacTiposComprobantes);
        return aServicios.listarTiposComprobantes();
    }

    @PostMapping(value = "/guardarSacTiposPagos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<SacTiposPagos> getListarSacTiposPagos(@ModelAttribute @Valid SacTiposPagos sacTiposPagos, BindingResult result) {
        aServicios.registrarSacTiposPagos(sacTiposPagos);
        return aServicios.listarTiposPagos();
    }

    @RequestMapping(value = "/findPeople/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Personas getBuscarPersona(@PathVariable("id") long id) {
        return pServicio.buscarPersonasComprobantesPorIdPersona(id);

    }

    @PostMapping(value = "/guardarPersonas", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<Personas> getListarPersonas(@ModelAttribute @Valid Personas personas, BindingResult result) {
        personas.setIdEstado("C");
        personas.setTelefono("123456");
        personas.setTipoSanguineo("Ninguno");
        personas.setDireccion("Ninguno");
        personas.setEmail("ninguno@gmail.com");
        aServicios.registrarPersonasSacComprobantes(personas);
        // pServicio.buscarPersonasComprobantesPorIdPersona(personas.getIdPersona());
        // List<Personas> lPersonas = pServicio.listarPersonasComprobantes();
        //  return pServicio.listarPersonasComprobantes();
        // PersonasResponse pResponse = new PersonasResponse(lPersonas, personas);
        //return pResponse;
        return pServicio.listarPersonasComprobantes();
    }

    @PostMapping("/inicioModificarComprobante")
    public String inicioModificarGastos(@ModelAttribute("sacComprobantes") SacComprobantes sacComprobantes,
            @ModelAttribute("sacCompNroComprobante") SacCompNroComprobante sacNroComprobante,
            @ModelAttribute("sacCompNroCheque") SacCompNroCheque sacCompNroCheque,
            @ModelAttribute("personas") Personas personas,
            @RequestParam("idSacComprobante") Long idSacComprobante, Model model, HttpSession session) throws IOException {

        AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
        SacComprobantes sComprobantes = aServicios.buscarSacComprobantesPorIdSacComprobantes(idSacComprobante);
        SacCompArchivosAdjuntos sacCompArchivosAdjuntos = aServicios.buscarSacComprobantesArchivoAdjuntoPorIdSacComprobante(idSacComprobante);

        //  String archivoAdjunto = adjuntarArchivo.leerComprobante(sacCompArchivosAdjuntos.getRutaArchivo() + sacCompArchivosAdjuntos.getNombreArchivo());
        // model.addAttribute("mult",mf);
        //model.addAttribute("ruta",archivoAdjunto);
        model.addAttribute("arch", sacCompArchivosAdjuntos);
        // model.addAttribute("pdf", archivoAdjunto);
        model.addAttribute("sacComprobantes", aServicios.buscarSacComprobantesPorIdSacComprobantes(idSacComprobante));
        model.addAttribute("lRazonS", aServicios.listarRazonSocialPersonasPorIdSacComprobante(idSacComprobante));
        model.addAttribute("lNroCheques", aServicios.listarNumeroChequesPorIdSacComprobante(idSacComprobante));
        model.addAttribute("lNroComprobantes", aServicios.listarNumeroComprobantesPorIdSacComprobante(idSacComprobante));
        model.addAttribute("urlUp", "updateVoucherFile");
        model.addAttribute("operation", "edit");
        model.addAttribute("nroCompMod", "editNroCompMod");
        model.addAttribute("iModNroComp", "inicioModificarNroComprobantes");
        getDesplegarListasComunes(model, session);
        return "inicioComprobantes";

    }

    @PostMapping(value = "/updateVoucherFile")
    public String modificarComprobanteFile(@Valid SacComprobantes sacComprobantes, BindingResult result, HttpSession session, Model model, HttpServletRequest request,
            @RequestParam("cantBenificiarios") Integer cantBenificiarios) throws IOException {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "edit");
            return "inicioComprobantes";
        }

        /*  
        SisArchivosAdjuntos sisArchivosAdjuntos = new SisArchivosAdjuntos();
            Usuarios usuarios = uServicio.buscarUsuariosPorIdUsuario((Long) session.getAttribute("currentUserId"));
     
        if(archivoComprobante.getArchivoAdjunto().getFile() == null){
             MultipartFile multipartFile = archivoComprobante.getArchivoAdjunto().getFile();          
        
             sisArchivosAdjuntos.setNombreArchivo(archivoComprobante.getArchivoAdjunto().getFileName());
            sisArchivosAdjuntos.setDescripcion(archivoComprobante.getArchivoAdjunto().getDescription());
            sisArchivosAdjuntos.setContenido(multipartFile.getBytes());
            sisArchivosAdjuntos.setTipoArchivo(multipartFile.getContentType());
            sisArchivosAdjuntos.setUsuarios(usuarios);
            aServicios.actualizarSisArchivosAdjuntos(sisArchivosAdjuntos);
        }
       
        archivoComprobante.getSacComprobantes().setSisArchivosAdjuntos(sisArchivosAdjuntos);
        SacComprobantes sacComprobantes = aServicios.actualizarSacComprobanteDos(archivoComprobante.getSacComprobantes());
        
        String idPersona2 = null;
        if(cantBenificiarios > 0){
            for(int i=1; i<=cantBenificiarios; i++){
                String idPersonat = "idPersona2"+i;
                idPersona2 = request.getParameter(idPersonat);
                SacRazonSocial sacRazonSocial = new SacRazonSocial();
                Personas personas = pServicio.buscarPersonasPorIdPersona(Long.parseLong(idPersona2));
                sacRazonSocial.setPersonas(personas);
                sacRazonSocial.setSacComprobantes(sacComprobantes);
                aServicios.registrarRazonSocial(sacRazonSocial);

            }
        }
         */
        aServicios.actualizarSacComprobanteDos(sacComprobantes);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "inicioComprobantes";
    }

    @PostMapping("/inicioModificarNroComprobantes")
    public String inicioModificarNroComprobante(@ModelAttribute("sacCompNroComprobante") SacCompNroComprobante sacCompNroComprobante,
            @RequestParam("idSacCompNroComprobante") Long idSacCompNroComprobante, Model model, HttpSession session) throws IOException {

        model.addAttribute("sacCompNroComprobante", aServicios.buscarNroComprobantePorIdNroComprobante(idSacCompNroComprobante));
        model.addAttribute("operation", "edit");

        getDesplegarListasComunes(model, session);
        return "inicioComprobantes";
    }

    @PostMapping(value = "/actualizarNroComprobantes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    SacCompNroComprobante getListarNumeroComprobantes(@ModelAttribute @Valid SacCompNroComprobante sacCompNroComprobante, BindingResult result) {
        return aServicios.modificarNumerosComprobantes(sacCompNroComprobante);
    }

    @PostMapping(value = "/actualizarNroCheques", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    SacCompNroCheque getModificarNumeroCheque(@ModelAttribute @Valid SacCompNroCheque sacCompNroCheque, BindingResult result) {
        return aServicios.modificarNumeroCheques(sacCompNroCheque);
    }

    @PostMapping(value = "/actualizarDatosPersonasRazonSocial", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Personas getModificaractualizarDatosPersonasRazonSocial(@ModelAttribute @Valid Personas personas, BindingResult result) {
        personas.setTelefono("123456");
        personas.setTipoSanguineo("Ninguno");
        personas.setDireccion("Ninguno");
        personas.setEmail("ninguno@gmail.com");
//        personas.getPrsCiExpedidos().getIdPrsCiExpedido();
//        personas.getPrsPaises().getIdPais();
//        personas.getPrsTiposSexos().getPrsTipoSexo();
        return aServicios.modificarPersonasRazonSocial(personas);
    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {

        //Personas bPersona = pServicio.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        model.addAttribute("urlN", "fornNuevoComprobante");
        model.addAttribute("urlM", "inicioModificarComprobante");
        model.addAttribute("urlD", "inicioEliminarComprobante");
        model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
        model.addAttribute("lTiposComprobantes", aServicios.listarTiposComprobantes());
        model.addAttribute("lEstantes", aServicios.listarEstantes());
        model.addAttribute("lTiposPagos", aServicios.listarTiposPagos());
        model.addAttribute("lPersonas", pServicio.listarPersonasComprobantes());
        model.addAttribute("lCarpetas", aServicios.listarCarpetas());
        model.addAttribute("urlReg", "registerVoucherFile");
        model.addAttribute("lPaises", pServicio.listarPaises());
        model.addAttribute("lExpedidos", pServicio.listarCedulaIdentidadexpedidos());
        model.addAttribute("lPrsTiposSexos", pServicio.listarTiposSexos());
        // model.addAttribute("lComprobantes", aServicios.listarSacComprobantes((Integer) session.getAttribute("gestion")));
        model.addAttribute("lComprobantes", aServicios.listarSacComprobantesGeneral());
        model.addAttribute("lRazonSocial", aServicios.listarSacRazonSocialGeneral());
        model.addAttribute("lNroCheque", aServicios.listarNumerosCheques());
        model.addAttribute("lArchivos", aServicios.listarArchivosAjuntosGeneral());
        model.addAttribute("lGradosAcademicos", pServicio.listarGradosAcademicos());
        model.addAttribute("lSacNumComp", aServicios.listarNumerosComprobantes());

    }

    @RequestMapping(value = "/openFile/{id}", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody
    Resource abrirArchivoMedianteResourse(HttpServletResponse response, @PathVariable("id") long idSacComprobante) throws FileNotFoundException {

        SacCompArchivosAdjuntos aAdjunto = aServicios.buscarSacComprobantesArchivoAdjuntoPorIdSacComprobante(idSacComprobante);
        File file = new File("C:/SacComprobantes/" + aAdjunto.getRutaArchivo() + aAdjunto.getNombreArchivo());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
    }

    @RequestMapping(value = "/agregarNroComp/{nro}/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SacCompNroComprobante getSavePeople(@PathVariable("nro") Integer nro, @PathVariable("id") long id) {
        SacCompNroComprobante nroComp = new SacCompNroComprobante();
        SacComprobantes sacComp = aServicios.buscarSacComprobantesPorIdSacComprobantes(id);
        nroComp.setSacNroComprobante(nro);
        nroComp.setSacComprobantes(sacComp);
        return aServicios.registrarSacCompNroComprobantes(nroComp);

    }

    @RequestMapping(value = "/agregarNroChequeEdit/{nro}/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SacCompNroCheque getGuardarNroCheque(@PathVariable("nro") Integer nro, @PathVariable("id") long id) {
        SacCompNroCheque nroCh = new SacCompNroCheque();
        SacComprobantes sacComp = aServicios.buscarSacComprobantesPorIdSacComprobantes(id);
        nroCh.setSacNroCheque(nro);
        nroCh.setSacComprobantes(sacComp);
        return aServicios.registrarSacCompNroCheque(nroCh);
    }

    @RequestMapping(value = "/buscarPersonasPorIdPersona/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Personas getBuscarPersonaPorIdPersona(@PathVariable("id") long id, Model model) {
        return pServicio.buscarPersonasComprobantesPorIdPersona(id);
    }

    @RequestMapping(value = "/deletNroVoucher/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SacCompNroComprobante eliminarNroComprobante(@PathVariable("id") long id, Model model) {
        SacCompNroComprobante nroComp = aServicios.buscarNroComprobantePorIdNroComprobante(id);
        nroComp.setIdEstado("X");
        return aServicios.modificarNumerosComprobantes(nroComp);
    }

    @RequestMapping(value = "/deletNroChk/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SacCompNroCheque eliminarNroCheck(@PathVariable("id") long id, Model model) {
        //SacCompNroComprobante nroComp = aServicios.buscarNroComprobantePorIdNroComprobante(id);
        //nroComp.setIdEstado("X");
        return aServicios.buscarNroChequePorIdSacNroCheque(id);
    }

    @RequestMapping(value = "/deletNroChecke/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SacCompNroCheque getBuscarPersona(@PathVariable("id") Long id, Model model) {
        SacCompNroCheque nroCheque = aServicios.buscarNumeroChequePorIdSacNroCheque(id);
        nroCheque.setIdEstado("X");
        return aServicios.modificarNumeroCheques(nroCheque);
    }

    @RequestMapping(value = "/agregarRazonSocialEdit/{idPersona}/{idSacComp}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PersonasResponse guardarRazonSocial(@PathVariable("idPersona") Long idPersona, @PathVariable("idSacComp") long idSacComp) {
        Personas personas = pServicio.buscarPersonasComprobantesPorIdPersona(idPersona);
        SacComprobantes sacComp = aServicios.buscarSacComprobantesPorIdSacComprobantes(idSacComp);
        SacRazonSocial razonSocial = new SacRazonSocial();
        razonSocial.setPersonas(personas);
        razonSocial.setSacComprobantes(sacComp);
        SacRazonSocial rSocial = aServicios.registrarPersonaRazonSocial(razonSocial);
        return pServicio.buscarPersonaRazonSocialPorIdSacRazonSocialResponse(rSocial.getIdSacRazonSocial());
    }

    @RequestMapping(value = "/buscarPersonaResponse/{idPersona}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PersonasResponse buscarPersonaRazonSocialResponse(@PathVariable("idPersona") Long idPersona) {
        return pServicio.buscarPersonaRazonSocialPorIdPersonaResponse(idPersona);
    }

    @RequestMapping(value = "/deletRazonSocial/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SacRazonSocial eliminarRazonSocial(@PathVariable("id") Long id) {
        SacRazonSocial sacRazonSocial = aServicios.buscarRazonSocialPorIdSacRazonSocial(id);
        sacRazonSocial.setIdEstado("X");
        return aServicios.actualizarRazonSocial(sacRazonSocial);
    }

    @PostMapping("/inicioEliminarComprobante")
    public String inicioEliminarComprobante(@ModelAttribute("sacComprobantes") SacComprobantes sacComprobantes,
            @RequestParam("idSacComprobante") Long idSacComprobante, Model model, HttpSession session) throws IOException {

        SacCompArchivosAdjuntos sacCompArchivosAdjuntos = aServicios.buscarSacComprobantesArchivoAdjuntoPorIdSacComprobante(idSacComprobante);
        model.addAttribute("arch", sacCompArchivosAdjuntos);
        model.addAttribute("sacComprobantes", aServicios.buscarSacComprobantesPorIdSacComprobantes(idSacComprobante));
        model.addAttribute("lRazonS", aServicios.listarRazonSocialPersonasPorIdSacComprobante(idSacComprobante));
        model.addAttribute("lNroCheques", aServicios.listarNumeroChequesPorIdSacComprobante(idSacComprobante));
        model.addAttribute("lNroComprobantes", aServicios.listarNumeroComprobantesPorIdSacComprobante(idSacComprobante));
        model.addAttribute("urlDel", "confirmEliminarComprobante");
        model.addAttribute("operation", "deletVoucher");
        model.addAttribute("nroCompMod", "editNroCompMod");
        model.addAttribute("iModNroComp", "inicioModificarNroComprobantes");
        getDesplegarListasComunes(model, session);
        return "inicioComprobantes";

    }

    @PostMapping("/confirmEliminarComprobante")
    public String ConfirmarEliminarComprobante(@RequestParam("idSacComprobante") Long idSacComprobante, Model model, HttpSession session) throws IOException {

        List<SacCompNroCheque> lNroCheque = aServicios.listarNumeroChequesPorIdSacComprobante(idSacComprobante);
        for (SacCompNroCheque nroCheque : lNroCheque) {
            nroCheque.setIdEstado("X");
            aServicios.modificarNumeroCheques(nroCheque);
        }

        List<SacCompNroComprobante> lNroComp = aServicios.listarNumeroComprobantesPorIdSacComprobante(idSacComprobante);
        for (SacCompNroComprobante nroComp : lNroComp) {
            nroComp.setIdEstado("X");
            aServicios.modificarNumerosComprobantes(nroComp);
        }

        List<SacRazonSocial> lRazonSocial = aServicios.listarRazonSocialPersonasPorIdSacComprobante(idSacComprobante);
        for (SacRazonSocial rSocial : lRazonSocial) {
            rSocial.setIdEstado("X");
            aServicios.actualizarRazonSocial(rSocial);

        }

        SacCompArchivosAdjuntos sacCompArchivosAdjuntos = aServicios.buscarSacComprobantesArchivoAdjuntoPorIdSacComprobante(idSacComprobante);
        if (sacCompArchivosAdjuntos != null) {
            sacCompArchivosAdjuntos.setIdEstado("X");
            aServicios.modificarSacArchivoAdjuntoComprobanteSET(sacCompArchivosAdjuntos);
        }

        SacComprobantes sacComprobantes1 = aServicios.buscarSacComprobantesPorIdSacComprobantes(idSacComprobante);
        sacComprobantes1.setIdEstado("X");
        aServicios.actualizarSacComprobanteDos(sacComprobantes1);

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "inicioComprobantes";

    }

}
