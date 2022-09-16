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
 * Rectorado - USIC Fecha: 2019-08-09
 *
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/gClasfDireccion")
public class TotalesGastosClasificacionesDireccion {

    @Autowired
    private CajitaServicios cServicio;

    @Autowired
    private InstitucionesServicios iServicio;

    @GetMapping("/iGastoTipoClasificacionDireccion")
    public String formTotalesGastos(Model model) {

        model.addAttribute("lSedes", iServicio.listarInsSedes());
        model.addAttribute("lTipoGastoClasificacion", cServicio.listarCjaTiposClasificaciones());
        model.addAttribute("lPartidas", cServicio.listarTotalesGastosPorPartidasDireccionesFuncionales(1l, 2019));
        model.addAttribute("operation", "form");
        model.addAttribute("urlI", "lGastosPartidaDireccion");
        return "inicioGastoClasificacionDireccion";
    }

    @PostMapping("/lGastosPartidaDireccion")
    public String listarTotalesGastosPartidaUnidad(@RequestParam("gestion") Integer gestion,
            @RequestParam("idDireccionFuncional") Long idDireccionFuncional, Model model) {

        model.addAttribute("bDireccion", iServicio.buscarDireccionFuncionalPorIdDireccionaFuncional(idDireccionFuncional));
        model.addAttribute("gestion", gestion);
        model.addAttribute("idDireccionFuncional", idDireccionFuncional);
        model.addAttribute("urlImp", "imprimirGastosPartidaDireccion");
        model.addAttribute("operation", "list");
        model.addAttribute("lPartidas", cServicio.listarTotalesGastosPorPartidasDireccionesFuncionales(idDireccionFuncional, gestion));

        return "inicioGastoClasificacionDireccion";
    }

    @RequestMapping(value = "/direccionFuncional/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsDireccionesFuncionales> getListarDireccionesFuncionales(@PathVariable("id") long id) {
        return iServicio.listarDireccionesFuncionalesPorIdSede(id);
    }
    
    @PostMapping("/imprimirGastosPartidaDireccion")
    public String imprimirTotalesGastosPartidaUnidad(@RequestParam("gestion") Integer gestion,
            @RequestParam("idDireccionFuncional") Long idDireccionFuncional, Model model) {

        model.addAttribute("bDireccion", iServicio.buscarDireccionFuncionalPorIdDireccionaFuncional(idDireccionFuncional));
         model.addAttribute("idDireccionFuncional", idDireccionFuncional);
         model.addAttribute("operation", "list");
        model.addAttribute("lPartidas", cServicio.listarTotalesGastosPorPartidasDireccionesFuncionales(idDireccionFuncional, gestion));

        //return "uap/usic/siga/estadisticasCajita/totalesGastosTiposClasificacionesDireccion/imprimirTotalesClasificacionesDireccion";
        return "imprimirGastosPartidaDireccionImp";
    }
}
