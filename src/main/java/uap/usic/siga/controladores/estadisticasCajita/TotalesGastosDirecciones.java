package uap.usic.siga.controladores.estadisticasCajita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
 * Rectrado - USIC Fecha: 2019-08-05
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/gDirection")
public class TotalesGastosDirecciones {

    @Autowired
    private CajitaServicios cServicio;

    @Autowired
    private InstitucionesServicios iServicio;

    @GetMapping("/inicioGastoDirection")
    public String formTotalesGastos(Model model) {

        model.addAttribute("lSedes", iServicio.listarInsSedes());
        model.addAttribute("operation", "form");
        model.addAttribute("urlI", "listarGastosDireccion");
        model.addAttribute("urlImp", "imprimirGastosDireccion");
        return "inicioGastosDirection";
    }

    @PostMapping("/listarGastosDireccion")
    public String listarTotalesGastosDireccion(@RequestParam("gestion") Integer gestion,
            @RequestParam("idSede") Long idSede,
            Model model) {

        model.addAttribute("bSede", iServicio.buscarInsSedes(idSede));
        model.addAttribute("lGastoDireccion", cServicio.listarTotalesGastosPorDireccionesFuncionales(idSede, gestion));
        model.addAttribute("lDirecciones", iServicio.listarDireccionesFuncionalesPorIdSede(idSede));
        model.addAttribute("gestion", gestion);
        model.addAttribute("idSede", idSede);
        model.addAttribute("urlImp", "imprimirGastosDireccion");
        model.addAttribute("operation", "list");
        return "inicioGastosDirection";
    }

    @PostMapping("/imprimirGastosDireccion")
    public String imprimirTotalesGastos(@RequestParam("gestion") Integer gestion,
            @RequestParam("idSede") Long idSede, Model model) {

       model.addAttribute("bSede", iServicio.buscarInsSedes(idSede));
       model.addAttribute("lGastoDireccion", cServicio.listarTotalesGastosPorDireccionesFuncionales(idSede, gestion));
       model.addAttribute("gestion", gestion);
       model.addAttribute("idSede", idSede);
       model.addAttribute("lDirecciones", iServicio.listarDireccionesFuncionalesPorIdSede(idSede));
       //return "uap/usic/siga/estadisticasCajita/totalesGastoDireccionesFuncionales/imprimirTotalesDirecciones";
       
       return "imprimirGastosDireccionImp";
     }
}
