package uap.usic.siga.controladores.administrarIngresosDeFondo;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.CjaTiposIngresos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.MenuesServicios;
import uap.usic.siga.servicios.PersonasServicios;

/**
 *
 * @author Yessenia
 */
@Controller
@RequestMapping("/ingresos")
public class RegistrarIngresos {

    @Autowired
    private CajitaServicios cServicios;

    @Autowired
    private PersonasServicios pServicios;

    @Autowired
    private MenuesServicios mServicio;

    @GetMapping(value = "/formCjaIngresos")
    public String formIngresos(@ModelAttribute("cjaIngresos") CjaIngresos cjaIngresos, Model model, HttpSession session) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");

        return "formIngresos";
    }

    @PostMapping(value = "/inicioFormCjaIngresos")
    public String inicioFormIngresos(@ModelAttribute("cjaIngresos") CjaIngresos cjaIngresos, Model model, HttpSession session) {

        getDesplegarListasComunes(model, session);
        model.addAttribute("operation", "reg");
        model.addAttribute("idMnuTipoF", "reg");
        return "formIngresos";
    }

    @PostMapping(value = "/cjaIngresosFondos")
    public String registrarIngresosFondos(@Valid @ModelAttribute("cjaIngresos") CjaIngresos cjaIngresos, BindingResult result, HttpSession session, Model model, 
                                          @RequestParam("saldoReg") Double saldo)  {
         if (result.hasErrors()) {
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "formIngresos";
        }

        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        CjaIngresos bCajaIngresos = cServicios.buscarCajitIngresosPorIdPersonaTipoIngreso("A", cjaIngresos.getGestion(), cjaIngresos.getPeriodo(), personas.getIdPersona(), cjaIngresos.getCjaTiposIngresos().getIdCjaTipoIngreso());
        if (bCajaIngresos != null) {
        	
        	System.out.println("aquiii 1");
            model.addAttribute("idMnuTipoF", "resp");
            getDesplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "formIngresos";
        }

        bCajaIngresos = cServicios.buscarCjaIngresosFondosPorIdPersonaGestionPeriodoIdEstado(personas.getIdPersona(), cjaIngresos.getGestion(), cjaIngresos.getPeriodo(), "A");
        if (bCajaIngresos == null) {
        	System.out.println("aquiii 2");
        	
            if(cjaIngresos.getCjaTiposIngresos().getIdCjaTipoIngreso() == 2){
            	System.out.println("aquiii 3");
            	
                cjaIngresos.setMonto(cjaIngresos.getMonto()+saldo);
            }
            cjaIngresos.setSaldo(cjaIngresos.getMonto());
            cjaIngresos.setPersonas(personas);
            cServicios.registrarIngresosFondos(cjaIngresos);
            getDesplegarListasComunes(model, session);
            model.addAttribute("busqueda", "find");
        }
         
        if (bCajaIngresos != null) {
        	System.out.println("aquiii 4");
        	
			cjaIngresos.setSaldo(cjaIngresos.getMonto() + bCajaIngresos.getSaldo());
            cjaIngresos.setPorcentajeGasto((cjaIngresos.getMonto() * 10) / 100);
            cjaIngresos.setPersonas(personas);
            cServicios.registrarIngresosFondos(cjaIngresos);
            bCajaIngresos.setIdEstado("I");
            cServicios.actualizarEstadoCajaIngresos(bCajaIngresos);
            getDesplegarListasComunes(model, session);
            model.addAttribute("idMnuTipoF", "reg");
            model.addAttribute("busqueda", "find");

        }
        getDesplegarListasComunes(model, session);
        return "formIngresos";
    }

    @PostMapping(value = "/modificarIngresos")
    public String modificarIngresosFondos(@Valid @ModelAttribute("cjaIngresos") CjaIngresos cjaIngresos, BindingResult result, HttpSession session, Model model,
            @RequestParam("idCjaIngreso") Long idCjaIngreso) {

        model.addAttribute("cjaIngresos", cServicios.buscarCjaIngresoPorIdCjaIngreso(idCjaIngreso));
        getDesplegarListasComunes(model, session);
        model.addAttribute("operation", "modf");
        return "formIngresos";

    }

    @PostMapping(value = "/confirmarModificacionIngresos")
    public String confirmarmModificacionIngresosFondos(@Valid @ModelAttribute("cjaIngresos") CjaIngresos cjaIngresos, BindingResult result, HttpSession session, Model model) {

        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        cjaIngresos.setPersonas(personas);
        cServicios.actualizarEstadoCajaIngresos(cjaIngresos);
        getDesplegarListasComunes(model, session);
        model.addAttribute("busqueda", "find");
        return "formIngresos";
    }

    @PostMapping(value = "/inicioEliminarIngresos")
    public String inicioEliminarIngresosFondos(@ModelAttribute("cjaIngresos") CjaIngresos cjaIngresos, HttpSession session, Model model,
            @RequestParam("idCjaIngreso") Long idCjaIngreso) {

        model.addAttribute("cjaIngresos", cServicios.buscarCjaIngresoPorIdCjaIngreso(idCjaIngreso));
        model.addAttribute("operation", "delet");
        model.addAttribute("alguito", idCjaIngreso);
        getDesplegarListasComunes(model, session);
        Integer banderita = 0;
        CjaIngresos bCjaIngresos = cServicios.buscarCjaIngresoPorIdCjaIngreso(idCjaIngreso);

        Long bIdCjaGastosEjecutados = cServicios.buscarCjaGastosEjecutadosPorIdCjaIngresoIdEstado(idCjaIngreso, "A");
        model.addAttribute("id", bIdCjaGastosEjecutados);

        if (bIdCjaGastosEjecutados != null) {
            banderita = 1;
            model.addAttribute("banderita", banderita);
            model.addAttribute("mensage", "err");
        }
        model.addAttribute("banderita", banderita);
        return "formIngresos";
    }

    @PostMapping(value = "/confirmarEliminarIngresos")
    public String confirmarmEliminarIngresosFondos(@Valid @ModelAttribute("cjaIngresos") CjaIngresos cjaIngresos, BindingResult result, HttpSession session, Model model) {

        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        cjaIngresos.setPersonas(personas);
        cjaIngresos.setIdEstado("X");
        cServicios.actualizarEstadoCajaIngresos(cjaIngresos);
        model.addAttribute("busqueda", "find");
        getDesplegarListasComunes(model, session);
        return "formIngresos";

    }

    public void getDesplegarListasComunes(Model model, HttpSession session) {
        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        String idEstado = "A";
        model.addAttribute("lIngresos", cServicios.listarIngresosPorIdPersona(personas.getIdPersona()));
        model.addAttribute("bCajaIngresosUltimo", cServicios.buscarCjaIngresosFondosRetornaMaximoIdCajaIngresoIdPersona(personas.getIdPersona(),idEstado));
        model.addAttribute("cjaIng", "cjaIngresosFondos");
        model.addAttribute("urlMod", "modificarIngresos");
        model.addAttribute("urlConfirmarMod", "confirmarModificacionIngresos");
        model.addAttribute("urlEliminar", "eliminarIngresosFondos");
        model.addAttribute("urlRIn", "inicioFormCjaIngresos");
        model.addAttribute("urlEI", "inicioEliminarIngresos");
        model.addAttribute("urlCEI", "confirmarEliminarIngresos");
        model.addAttribute("lTiposIngresos", cServicios.listarTiposIngresos());
        model.addAttribute("urlClose", "inicioCerrarCaja");
        model.addAttribute("urlCloseConf", "confirmarCierreCaja");
     }

    @PostMapping(value = "/guardarTipoIngreso", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<CjaTiposIngresos> getListarTiposIngresos(@ModelAttribute @Valid CjaTiposIngresos cjaTiposIngresos, BindingResult result) {
        cServicios.registrarTiposIngresos(cjaTiposIngresos);
        return cServicios.listarTiposIngresos();
    }

    @PostMapping(value = "/inicioCerrarCaja")
    public String inicioCerrarCajaChica(@ModelAttribute("cjaIngresos") CjaIngresos cjaIngresos, HttpSession session, Model model,
            @RequestParam("idCjaIngreso") Long idCjaIngreso) {

        model.addAttribute("cjaIngresos", cServicios.buscarCjaIngresoPorIdCjaIngreso(idCjaIngreso));
        model.addAttribute("operation", "close");
        model.addAttribute("alguito", idCjaIngreso);
        getDesplegarListasComunes(model, session);
         
        return "formIngresos";
    }

     @PostMapping(value = "/confirmarCierreCaja")
    public String confirmarmCierreCajaChica(@RequestParam("idCjaIngreso") Long idCjaIngreso, HttpSession session, Model model) {

        Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
        CjaIngresos cjaIngresos = cServicios.buscarCjaIngresoPorIdCjaIngreso(idCjaIngreso);
        cjaIngresos.setPersonas(personas);
        cjaIngresos.setIdEstado("I");
        cjaIngresos.setCaja(1);
        cServicios.actualizarEstadoCajaIngresos(cjaIngresos);
        model.addAttribute("busqueda", "find");
        getDesplegarListasComunes(model, session);
        return "formIngresos";

    }

}
