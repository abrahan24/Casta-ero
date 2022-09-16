package uap.usic.siga.controladores;

import uap.usic.siga.entidades.PdfUserDetails;
import uap.usic.siga.entidades.Usuarios;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.servicios.AdministradorServicios;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.PersonasServicios;

@SessionAttributes({"idMnuTipoFuncion","currentUser", "currentUserId", "lTiposEnlaces", "personas", "lEnlaces", "lTiposFunciones", "gestion", "periodo", "nombreSis", "lFunciones"})
@Controller
public class LoginController {

    private static final Logger log = LogManager.getLogger(LoginController.class);

    @Autowired
    private PersonasServicios pS;

    @Autowired
    private AdministrativosServicios aServicios;

    @Autowired
    private MenuesServicios mServicios;

    @Autowired
    private PersonasServicios pServicios;

    @Autowired
    private AdministradorServicios adServicio;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginError(Model model) {
        log.info("Login attempt failed");
        return "login";
    }

    @RequestMapping("/error-forbidden")
    public String errorForbidden() {
        return "error-forbidden";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public String postLogin(Model model, HttpSession session) {
        log.info("postLogin()");

        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        Usuarios loggedInUser = ((PdfUserDetails) authentication.getPrincipal()).getUserDetails();

        Personas bPersona = pServicios.buscarPersonaPorIdUsuario(loggedInUser.getIdUsuario());
        PnlCargos bPnlCargos = aServicios.buscarCargosPorIdPersonaGET(bPersona.getIdPersona());
        model.addAttribute("currentUserId", loggedInUser.getIdUsuario());
        model.addAttribute("currentUser", loggedInUser.getUsuario());
        session.setAttribute("userId", loggedInUser.getIdUsuario());
        session.setAttribute("sNombres", loggedInUser.getPersonas().getNombres());
        session.setAttribute("sPaterno", loggedInUser.getPersonas().getPaterno());
        session.setAttribute("sMaterno", loggedInUser.getPersonas().getMaterno());
        model.addAttribute("personas", pS.buscarPersonaPorIdUsuario(loggedInUser.getIdUsuario()));
        model.addAttribute("userModal", "verd");

        model.addAttribute("lFunciones", mServicios.listarMenusTiposFuncionesPorIdPersona(bPersona.getIdPersona()));
        model.addAttribute("lTiposFunciones", mServicios.listarMenusTiposFuncionesPorIdPersona(bPersona.getIdPersona()));
        //  session.setAttribute("nombreSis", "system.name");
        //session.setAttribute("mFunciones", mServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(1l, 1l));
        session.setAttribute("userModal", "verd");

        return "redirect:/welcome";
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof PdfUserDetails)) {
            throw new IllegalArgumentException("Principal can not be null!");
        }
    }

    @PostMapping(value = "/listarMenues")
    public String seleccionarFuncion(@RequestParam("idMnuTipoFuncion") Long idMnuTipoFuncion, Model model, HttpSession session) {
        log.info("seleccionarFuncion()");
        Personas bPersona = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));

        SisAdministrador bSisAdministrador = adServicio.buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(idMnuTipoFuncion, bPersona.getIdPersona());

        model.addAttribute("lTiposEnlaces", mServicios.listarMenuesTiposEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));
        model.addAttribute("lEnlaces", mServicios.listarMenuesEnlacesPorIdMnuTipoFuncion(idMnuTipoFuncion));

        session.setAttribute("mFunciones", mServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(bPersona.getIdPersona(), idMnuTipoFuncion, bSisAdministrador.getIdSisAdministrador()));
        session.setAttribute("userModal", "falso");
        session.setAttribute("gestion", bSisAdministrador.getGestion());
        session.setAttribute("periodo", bSisAdministrador.getPeriodo());
        session.setAttribute("nombreSiss", bSisAdministrador.getNombreSis());
        session.setAttribute("sisAdministrador", bSisAdministrador);
        session.setAttribute("idMnuTipoFuncion", idMnuTipoFuncion);

        MnuFunciones bFuncionesSis = mServicios.buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(bPersona.getIdPersona(), idMnuTipoFuncion, bSisAdministrador.getIdSisAdministrador());
        model.addAttribute("bFuncionesSis", bFuncionesSis);
        model.addAttribute("SisAdminitrador", bFuncionesSis);
        
        

        return "redirect:/welcome";
    }

}
