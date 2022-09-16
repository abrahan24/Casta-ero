package uap.usic.siga.controladores.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uap.usic.siga.entidades.InsSede;

import uap.usic.siga.servicios.InstitucionesServicios;

/**
 *
 * @author Personal
 */
@Controller
@RequestMapping("/prueba")
public class Prueba {

    @Autowired
    private InstitucionesServicios iServicio;

    @GetMapping("/listarInstitucion")
    public String listarInstitucion(@ModelAttribute("sedes")InsSede sedes,Model model) {
        model.addAttribute("lInstitucion",iServicio.listarInstitucion());
        return "inicioInstitucion";
    }
}
