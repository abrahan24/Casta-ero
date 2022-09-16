package uap.usic.siga.controladores.administrarInstituciones;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
 * Rectorado - USIC
 * Fecha: 2019-04-12
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/unidad")
public class AdmUnidadesFuncionales {
    
     @Autowired
    private InstitucionesServicios iServicio;
   
    @GetMapping("/inicioUnidad")
    public String formSedes(@ModelAttribute("insUnidadesFuncionales") InsUnidadesFuncionales insUnidadesFuncionales, Model model){
         Integer iv = 1;
         Long lv = iv.longValue();
         model.addAttribute("lInstituciones", iServicio.listarInstituciones());
         model.addAttribute("lSedes", iServicio.listarSedesPorIdInstitucion(lv));
         model.addAttribute("url", "registrarUnidad");
         return "inicioUnidad";   
    }
    
    @RequestMapping(value = "/lsedes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<InsSedes> getListarInsSedes(@PathVariable("id") long id) {
         return iServicio.listarSedesPorIdInstitucion(id);
    }
    
    @PostMapping("/registrarUnidad")
    public String registrarSede(@Valid @ModelAttribute("insUnidadesFuncionales") InsUnidadesFuncionales insUnidadesFuncionales, BindingResult result, Model model) {
        if (result.hasErrors()) {
             model.addAttribute("lInstituciones", iServicio.listarInstituciones());
             return "inicioUnidad";
        }
        
        InsUnidadesFuncionales bUnidad = iServicio.buscarInsUnidadesFuncionalesPorUnidad(insUnidadesFuncionales.getUnidadFuncional());
        if (bUnidad != null){        
            model.addAttribute("lInstituciones", iServicio.listarInstituciones());
            return "inicioUnidad";
        }
        
        iServicio.guardarInsUnidadesFuncionales(insUnidadesFuncionales);
        return "inicioUnidad";
    }

}
