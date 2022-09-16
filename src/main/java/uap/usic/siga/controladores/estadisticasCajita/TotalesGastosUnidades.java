package uap.usic.siga.controladores.estadisticasCajita;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uap.usic.siga.entidades.InsDireccionesFuncionales;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
 * Rectorado - USIC Fecha: 2019-07-30
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/gUnidad")
public class TotalesGastosUnidades {

    @Autowired
    private CajitaServicios cServicio;

    @Autowired
    private InstitucionesServicios iServicio;

    @GetMapping("/inicioGastosUnidad")
    public String formTotalesGastos(Model model) {

        model.addAttribute("lSedes", iServicio.listarInsSedes());
        model.addAttribute("operation", "form");
        model.addAttribute("urlI", "listarGastosUnidad");
        return "inicioGastosUnidad";
    }

    @RequestMapping(value = "/lDirecciones/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsDireccionesFuncionales> getListarDireccionesFuncionales(@PathVariable("id") long id) {
        return iServicio.listarDireccionesFuncionalesPorIdSede(id);
    }

    @PostMapping("/listarGastosUnidad")
    public String listarTotalesGastos(@RequestParam("gestion") Integer gestion,
            @RequestParam("idDireccionFuncional") Long idDireccionFuncional,
            Model model) {

        model.addAttribute("bDireccion", iServicio.buscarDireccionFuncionalPorIdDireccionaFuncional(idDireccionFuncional));
        model.addAttribute("lGastoUnidad", cServicio.listarTotalesGastosPOrUnidadesFuncionales(idDireccionFuncional, gestion));
        model.addAttribute("gestion", gestion);
        model.addAttribute("idDireccionFuncional", idDireccionFuncional);
        model.addAttribute("urlImp", "imprimirGastosUnidad");
        model.addAttribute("operation", "list");
        return "inicioGastosUnidad";
    }

    @PostMapping("/imprimirGastosUnidad")
    public String imprimirTotalesGastos(@RequestParam("gestion") Integer gestion,
            @RequestParam("idDireccionFuncional") Long idDireccionFuncional,
            Model model) {

        model.addAttribute("bDireccion", iServicio.buscarDireccionFuncionalPorIdDireccionaFuncional(idDireccionFuncional));
        model.addAttribute("lGastoUnidad", cServicio.listarTotalesGastosPOrUnidadesFuncionales(idDireccionFuncional, gestion));
        model.addAttribute("gestion", gestion);
        model.addAttribute("idDireccionFuncional", idDireccionFuncional);
        //return "uap/usic/siga/estadisticasCajita/totalesGastoUnidadesFuncionales/imprimirTotalesUnidades";
        
        return "imprimirGastosUnidadImp";
    }

}
