package uap.usic.siga.controladores.sicoes.reportes.reportePorModalidad;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uap.usic.siga.servicios.CajitaServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.SicoesServicios;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 * Rectorado - USIC
 * Fecha: 2020-01-18
 * @author Freddy Morales
 */
@Controller
@RequestMapping("/rModalidad")
public class ReportePorModalidades {
    
    @Autowired
    private SicoesServicios scServicios;
    
    @Autowired
    private PersonasServicios pServicios;
    
    @Autowired
    private CajitaServicios cServicios;
    
    @Autowired
    private UsuariosServicios uServicio;
    
    @GetMapping("/inicioRepModalidad")
    public String formInicioReporteModalidades(HttpSession session, Model model) {

        model.addAttribute("lScsModalidades", scServicios.listarScsModalidades());
        model.addAttribute("urlList", "listarContratosModalidad");
        model.addAttribute("operation", "form");
        return "inicioReporteModalidad";
    }

    @PostMapping("/listarContratosModalidad")
    public String listarContratosPorModalidad(@RequestParam("idScsModalidad") Long idScsModalidad,
            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

        model.addAttribute("lContratosModalidades", scServicios.listarContratacionesPorGestionIdScsModalidad(idScsModalidad, gestion));
        model.addAttribute("gestionR", gestion);
        model.addAttribute("idScsModalidad", idScsModalidad);
        model.addAttribute("urlImp", "imprimirReporteContratosModalidad");
        model.addAttribute("operation", "list");
        return "inicioReporteModalidad";
    }

    @PostMapping("/imprimirReporteContratosModalidad")
    public String imprimirReporteContratosPorModalidad(@RequestParam("idScsModalidad") Long idScsModalidad,
            @RequestParam("gestion") Integer gestion,Model model, HttpSession session) throws IOException {

        model.addAttribute("lContratosModalidades", scServicios.listarContratacionesPorGestionIdScsModalidad(idScsModalidad, gestion));
        model.addAttribute("scsModalidades", scServicios.buscarScsModalidadesPorIdScsModalidad(idScsModalidad));
        model.addAttribute("gestionR", gestion);
        model.addAttribute("urlImp", "actualizarContratacion");
        model.addAttribute("operation", "list");
        return "uap/usic/siga/sicoes/reportes/reportePorModalidades/imprimirReporteContratosModalidad";
    }

    
}
