package uap.usic.siga.controladores;

import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.entidades.AuthorityType;
import uap.usic.siga.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.UsuariosServicios;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsuariosServicios uServicio;

    @Autowired
    private PersonasServicios pServicio;

    @Autowired
    private InstitucionesServicios iServicio;

    @Autowired
    private AdministrativosServicios aServicio;

    @GetMapping(value = "/signupForm")
    public String inicioUsuarios(@ModelAttribute("usuarios") Usuarios usuarios, Model model) {
        model.addAttribute("busqueda", "find");
        // model.addAttribute("tituloForm", "menu.register");
        getDesplegarListasComunes(model);
        return "addUser";
    }

    @RequestMapping(value = {"/list", ""})
    public String users(Model model) {
        model.addAttribute("userLi-st", userService.getAllUsers());
        return "allUsers";
    }

    @PostMapping(value = "/formNuevoUsuario")
    public String addUser(@ModelAttribute("usuarios") Usuarios usuarios, Model model) {

        model.addAttribute("operation", "reg");
        getDesplegarListasComunes(model);
        return "addUser";
    }

    @PostMapping(value = "/signup")
    public String addUser(@ModelAttribute("usuarios") @Valid Usuarios user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model);
            model.addAttribute("operation", "reg");
            return "addUser";
        }
        Usuarios dbUser = this.userService.getUserByUsername(user.getUsuario());
        if (dbUser != null) {
            getDesplegarListasComunes(model);
            model.addAttribute("operation", "reg");
            return "addUser";
        }

        Usuarios bUsuarios = pServicio.buscarUsuariosPorIdPersonaIdEstado(user.getPersonas().getIdPersona(), "A");
        if (bUsuarios == null) {

            user.setDateCreated(new Date());
            Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_USER));
            user.setRoles(roles);
            userService.addUser(user);
            getDesplegarListasComunes(model);
            model.addAttribute("busqueda", "find");
        } else {
            model.addAttribute("mensage", "err");
            model.addAttribute("operation", "reg");
            getDesplegarListasComunes(model);
            return "addUser";
        }
        return "addUser";

    }

    //Modificar Usuarios
    @PostMapping(value = "/inicioModificarUsuarios")
    public String modificarUsuarios(@ModelAttribute("usuarios") Usuarios usuarios, Model model,
            @RequestParam("idUsuario") Long idUsuario) {

        model.addAttribute("usuarios", uServicio.buscarUsuariosPorIdUsuario(idUsuario));
        model.addAttribute("operation", "mod");
        getDesplegarListasComunes(model);
        return "addUser";
    }

    @PostMapping(value = "/confirmarModificacionUsuarios")
    public String confirmarModificacionUsuarios(@ModelAttribute("usuarios") @Valid Usuarios user, BindingResult result, Model model
    ) {

        if (result.hasErrors()) {
            getDesplegarListasComunes(model);
            model.addAttribute("operation", "mod");
            return "addUser";
        }
        /*
        Usuarios dbUser = this.userService.getUserByUsername(user.getUsuario());
        if (dbUser != null) {
            getDesplegarListasComunes(model);
            model.addAttribute("mensage", "err");
            model.addAttribute("operation", "mod");
            return "addUser";
        }
*/
        //user.setDateCreated(new Date());
        //Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_USER));
        //user.setRoles(roles);
        
         user.setDateCreated(new Date());
       // userService.updateUser(user);
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");

        return "addUser";
        //Fin Modificar usuarios
    }

    //Inicio Eliminar usuarios
    @PostMapping(value = "/inicioEliminarUsuarios")
    public String inicioEliminarUsuarios(@ModelAttribute("usuarios") Usuarios usuarios, Model model,
            @RequestParam("idUsuario") Long idUsuario) {

        model.addAttribute("usuarios", uServicio.buscarUsuariosPorIdUsuario(idUsuario));
        model.addAttribute("operation", "elim");
        getDesplegarListasComunes(model);
        return "addUser";
    }

    @PostMapping(value = "/confirmarEliminacionUsuario")
    public String confirmarEliminacionUsuarios(@ModelAttribute("usuarios") @Valid Usuarios user, BindingResult result, Model model) {

        Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_USER));
        user.setRoles(roles);
         user.setDateCreated(new Date());
        user.setIdEstado("X");
        uServicio.eliminarUsuarios(user);
        getDesplegarListasComunes(model);
        model.addAttribute("busqueda", "find");

        return "addUser";

    }
//------------------------Inicio Usuario Administrador----------------------------

    @RequestMapping(value = "/addAdminForm", method = RequestMethod.GET)
    public String addAdmin(@ModelAttribute("usuarios") Usuarios usuarios, Model model) {
        getDesplegarListasComunes(model);
        model.addAttribute("busquedaAdm", "findAdm");

        model.addAttribute("lPersonas", aServicio.listarPersonaslAdministrativos());

        return "addUser";
    }

    @PostMapping(value = "/formNuevoUsuarioAdm")
    public String addUserAdm(@ModelAttribute("usuarios") Usuarios usuarios, Model model) {

        model.addAttribute("operationAdm", "regAdm");
        getDesplegarListasComunes(model);
        return "addUser";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addAdmin(@Valid @ModelAttribute("usuarios") Usuarios user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("operationAdm", "regAdm");
            getDesplegarListasComunes(model);
            return "addUser";
        }
        Usuarios dbUser = this.userService.getUserByUsername(user.getUsuario());
        if (dbUser != null) {
            model.addAttribute("operationAdm", "regAdm");
            model.addAttribute("mensage", "err");
            getDesplegarListasComunes(model);
            return "addUser";
        }

        user.setDateCreated(new Date());

        Set<Roles> roles = Collections.singleton(new Roles(AuthorityType.ROLE_ADMIN));
        user.setRoles(roles);
        userService.addUser(user);
        model.addAttribute("busquedaAdm", "findAdm");
        getDesplegarListasComunes(model);
        return "addUser";
    }

    @RequestMapping(value = "/adminList", method = RequestMethod.GET)
    public String getAllAdmins(Model model) {
        model.addAttribute("userList", userService.getAllAdmins());
        return "allUsers";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    InsSedes getUserProfile(@PathVariable("id") long id) {
        return iServicio.buscarInsSedes(id);
        //return "Controller";
    }

    public void getDesplegarListasComunes(Model model) {

        model.addAttribute("urlNuevoU", "formNuevoUsuario");
        model.addAttribute("inicioModU", "inicioModificarUsuarios");
        model.addAttribute("modUsuario", "confirmarModificacionUsuarios");
        model.addAttribute("inicElimU", "inicioEliminarUsuarios");
        model.addAttribute("elimUsuario", "confirmarEliminacionUsuario");
        model.addAttribute("url", "signup");
        model.addAttribute("lRoles", uServicio.listarRoles());
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("lPersonas", aServicio.listarPersonaslAdministrativos());
        model.addAttribute("lUsuarios", uServicio.listarUsuariosRegistrados());

        model.addAttribute("urlNuevoUAdm", "formNuevoUsuarioAdm");
        model.addAttribute("registrarUAdm", "addAdmin");
    }
}
