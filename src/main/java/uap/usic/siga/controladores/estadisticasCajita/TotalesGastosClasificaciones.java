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
import uap.usic.siga.entidades.CjaGastosClasificaciones;
import uap.usic.siga.entidades.InsDireccionesFuncionales;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
 * Rectorado - USIC
 * Fecha: 2019-08-08
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/gClasificacion")
public class TotalesGastosClasificaciones {
    
    @Autowired
    private CajitaServicios cServicio;

    @Autowired
    private InstitucionesServicios iServicio;

    @GetMapping("/inicioGastoClasificacion")
    public String formTotalesGastos(Model model) {

        model.addAttribute("lSedes", iServicio.listarInsSedes());
        model.addAttribute("lTipoGastoClasificacion", cServicio.listarCjaTiposClasificaciones());
        model.addAttribute("operation", "form");
        model.addAttribute("urlI", "lGastosPartidaUnidad");
        return "inicioGastoClasificacion";
    }
     
    @RequestMapping(value = "/lDireccionFuncional/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsDireccionesFuncionales> getListarDireccionesFuncionales(@PathVariable("id") long id) {
        return iServicio.listarDireccionesFuncionalesPorIdSede(id);
    }

    @RequestMapping(value = "/lGastoClasificacion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<CjaGastosClasificaciones> getListarGastosClasificaciones(@PathVariable("id") long id) {
        return cServicio.listarCjaGastosClasificacionesPorIdCjaTipoClasificacion(id);
    }

    @RequestMapping(value = "/lUnidadFuncional/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<InsUnidadesFuncionales> getListarUnidadesFuncionales(@PathVariable("id") long id) {
        return  iServicio.listarUnidadesFuncionalesPorIdDireccionFuncional(id);
    }

    @PostMapping("/lGastosPartidaUnidad")
    public String listarTotalesGastosPartidaUnidad(@RequestParam("gestion") Integer gestion,
        @RequestParam("idUnidadFuncional") Long idUnidadFuncional, Model model) {
        
        model.addAttribute("bUnidad", iServicio.buscarInsUnidadesFuncionalesPorIdUnidadFuncional(idUnidadFuncional));
        model.addAttribute("lPartidasUnidad", cServicio.listarTotalesGastosPorTipoClasificacionUnidadFuncional(idUnidadFuncional, gestion));
        model.addAttribute("gestion", gestion);
        model.addAttribute("idUnidadFuncional", idUnidadFuncional);
        model.addAttribute("urlImp", "imprimirGastosPartidaUnidad");
        model.addAttribute("operation", "list");
        return "inicioGastoClasificacion";
    }

    @PostMapping("/imprimirGastosPartidaUnidad")
    public String imprimirTotalesGastosPartidaUnidad(@RequestParam("gestion") Integer gestion,
        @RequestParam("idUnidadFuncional") Long idUnidadFuncional, Model model) {
        
        model.addAttribute("bUnidad", iServicio.buscarInsUnidadesFuncionalesPorIdUnidadFuncional(idUnidadFuncional));
        model.addAttribute("lPartidasUnidad", cServicio.listarTotalesGastosPorTipoClasificacionUnidadFuncional(idUnidadFuncional, gestion));
        model.addAttribute("gestion", gestion);
        model.addAttribute("idUnidadFuncional", idUnidadFuncional);
        //return "uap/usic/siga/estadisticasCajita/totalesGastosTiposClasificacionesUnidad/imprimirTotalesGastosClasificaciones";
        return "imprimirGastosPartidaUnidadImp";
    }
}
