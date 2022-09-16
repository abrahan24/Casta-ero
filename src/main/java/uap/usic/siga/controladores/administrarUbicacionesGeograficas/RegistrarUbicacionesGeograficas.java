package uap.usic.siga.controladores.administrarUbicacionesGeograficas;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uap.usic.siga.entidades.InsSedes;

import uap.usic.siga.servicios.InstitucionesServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/inst")
public class RegistrarUbicacionesGeograficas {

    @Autowired
    private InstitucionesServicios iServicio;

    @GetMapping(value = "/listarUbicacionesGeograficas")
    public String listarPersonas(@ModelAttribute("insedes") InsSedes insedes, Model model) {
        model.addAttribute("regUG","registrarInsSedes");
        model.addAttribute("lInstituciones", iServicio.listarInstituciones());
        return "listarUbicaciones";
    }

    @PostMapping(value = "/registrarInsSedes")
    public String registrarPersonas(@Valid @ModelAttribute("inssedes") InsSedes inssedes, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "listarUbicaciones";
//        } 
        iServicio.registrarInsSedes(inssedes);
        return "registrarUbiG";
//        }
    }
}
