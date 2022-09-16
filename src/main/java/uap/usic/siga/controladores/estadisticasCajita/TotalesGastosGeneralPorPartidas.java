package uap.usic.siga.controladores.estadisticasCajita;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uap.usic.siga.dto.CjaTiposClasificacionesResponse;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
 * Rectorado - USIC
 * Fecha: 2019-08-12
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/general")
public class TotalesGastosGeneralPorPartidas {
    
    @Autowired
    private CajitaServicios cServicio;

    @Autowired
    private InstitucionesServicios iServicio;

    @GetMapping("/inicioGastoGeneralPartida")
    public String formTotalesGastosPartida(Model model) {

        model.addAttribute("lSedes", iServicio.listarInsSedes());
        model.addAttribute("operation", "form");
        model.addAttribute("urlI", "lGastosPartidaGeneral");
        return "inicioGastoGeneralesPartidas";
    }
  
    @PostMapping("/lGastosPartidaGeneral")
    public String listarTotalesGastosPartidaGeneral(@RequestParam("gestion") Integer gestion,
        @RequestParam("idSede") Long idSede, Model model) {
        
        List<CjaTiposClasificacionesResponse> lPartidas = new ArrayList<>();
        List<CjaTiposClasificacionesResponse> lP = null;
        List<InsUnidadesFuncionales> lUnidad = iServicio.listarUnidadesFuncionalesPorIdSede(idSede);
        
        for(InsUnidadesFuncionales uni : lUnidad){
            lP = cServicio.listarTotalesGastosGeneralesPorPartidaSedeJPQL(uni.getIdUnidadFuncional(), gestion);
            if(lP.size() > 0){
                for(CjaTiposClasificacionesResponse bre : lP){
                  CjaTiposClasificacionesResponse oresp = new CjaTiposClasificacionesResponse(bre.getIdCjaTipoClasificacion(),bre.getIdDireccionFuncional(), bre.getIdUnidadFuncional(), bre.getTotal(), bre.getMontoMaximo(), bre.getSaldo(), bre.getNroTipoClasificacion(), bre.getTipoClasificacion());
                  lPartidas.add(oresp); //lPartidas.addAll(lP);
                }
            }            
        }
      
        model.addAttribute("bSede", iServicio.buscarInsSedes(idSede));
        model.addAttribute("lDireccion", iServicio.listarDireccionesFuncionalesPorIdSede(idSede));
        model.addAttribute("lUnidades", iServicio.listarUnidadesFuncionalesPorIdSede(idSede));
        model.addAttribute("lClasificacion", cServicio.listarCjaTiposClasificaciones());
        model.addAttribute("lGeneral", lPartidas);//cServicio.listarTotalesGastosGeneralesPorPartidaSedeJPQL(1l, 2019));
        model.addAttribute("lTotalesClasif", cServicio.listarTotalesGastosGeneralesPorPartidaSedeTotales(idSede, gestion));
        model.addAttribute("lMontosMaximo", cServicio.listarMOntoMaximoPorIdSede(idSede, gestion));
        model.addAttribute("gestion", gestion);
        model.addAttribute("idSede", idSede);
        model.addAttribute("urlImp", "imprimirGastosPartidaGeneral");
        model.addAttribute("operation", "list");
        return "inicioGastoGeneralesPartidas";
    }

      @PostMapping("/imprimirGastosPartidaGeneral")
    public String imprimirTotalesGastosPartidaGeneral(@RequestParam("gestion") Integer gestion,
        @RequestParam("idSede") Long idSede, Model model) {
        
        List<CjaTiposClasificacionesResponse> lPartidas = new ArrayList<>();
        List<CjaTiposClasificacionesResponse> lP = null;
        List<InsUnidadesFuncionales> lUnidad = iServicio.listarUnidadesFuncionalesPorIdSede(idSede);
        
        for(InsUnidadesFuncionales uni : lUnidad){
            lP = cServicio.listarTotalesGastosGeneralesPorPartidaSedeJPQL(uni.getIdUnidadFuncional(), gestion);
            if(lP.size() > 0){
                for(CjaTiposClasificacionesResponse bre : lP){
                  CjaTiposClasificacionesResponse oresp = new CjaTiposClasificacionesResponse(bre.getIdCjaTipoClasificacion(),bre.getIdDireccionFuncional(), bre.getIdUnidadFuncional(), bre.getTotal(), bre.getMontoMaximo(), bre.getSaldo(), bre.getNroTipoClasificacion(), bre.getTipoClasificacion());
                  lPartidas.add(oresp); //lPartidas.addAll(lP);
                }
            }            
        }
      
        model.addAttribute("bSede", iServicio.buscarInsSedes(idSede));
        model.addAttribute("lDireccion", iServicio.listarDireccionesFuncionalesPorIdSede(idSede));
        model.addAttribute("lUnidades", iServicio.listarUnidadesFuncionalesPorIdSede(idSede));
        model.addAttribute("lClasificacion", cServicio.listarCjaTiposClasificaciones());
        model.addAttribute("lGeneral", lPartidas);//cServicio.listarTotalesGastosGeneralesPorPartidaSedeJPQL(1l, 2019));
        model.addAttribute("lTotalesClasif", cServicio.listarTotalesGastosGeneralesPorPartidaSedeTotales(idSede, gestion));
        model.addAttribute("lMontosMaximo", cServicio.listarMOntoMaximoPorIdSede(idSede, gestion));
        model.addAttribute("gestion", gestion);
        //return "uap/usic/siga/estadisticasCajita/totalesGastosGeneralPorPartidas/imprimirTotalesGastosGeneralPartidas";
     
        return "imprimirGastosPartidaGeneralImp";
      }
}
