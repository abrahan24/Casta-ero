package uap.usic.siga.controladores.administrarInstituciones;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uap.usic.siga.entidades.AuthorityType;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
 * Rectorado - USIC
 * Fecha: 2019-04+11
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/inst")
public class AdministrarInstituciones {
    
    @Autowired
    private InstitucionesServicios iServicio;
   
    @GetMapping("/inicioSedes")
    public String formSedes(@ModelAttribute("pnlPersonalAdministrativos") PnlPersonalAdministrativos pnlPersonalAdministrativos, Model model){
         model.addAttribute("lInstituciones", iServicio.listarInstituciones());
         model.addAttribute("url", "registrarSede");
         return "inicioSede";   
    }
    
    @PostMapping("/registrarSede")
    public String registrarSede(@Valid @ModelAttribute("insSedes") InsSedes insSedes, BindingResult result, Model model) {
        if (result.hasErrors()) {
             return "inicioSede";
        }
        
        InsSedes bSede = iServicio.buscarInsSedesPorSede(insSedes.getSede());
        if (bSede != null){
             return "inicioSede";
        }
        
        iServicio.guardarInsSedes(insSedes);
        return "inicioSede";
    }

}
