package uap.usic.siga.controladores.poais.reporte;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.servicios.AdministrativosServicios;
import uap.usic.siga.servicios.InstitucionesServicios;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PoaisServicios;

@Controller
@RequestMapping("/reporte-poai")
public class PoaiReporte {

	@Autowired
	public AdministrativosServicios aServicios;

	@Autowired
	public PoaisServicios poaiServicios;

	@Autowired
	private PersonasServicios pServicios;
	
	@Autowired
	private InstitucionesServicios institucionesServicios;
    
	@RequestMapping("/unidad")
    public String listarUnidad(HttpSession session, Model model){

        model.addAttribute("unidades", institucionesServicios.listarUnidadesFuncionales());
        model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");

		getDesplegarListasComunes(model, session);	

        return "listarReporteUnidad"; 
    }

	@RequestMapping("/unidad/{id_unidad}")
    public String listarAdministrativo(HttpSession session, Model model, @PathVariable("id_unidad") Long id_unidad){

		
        model.addAttribute("lAdministrativos", institucionesServicios.buscarInsUnidadesFuncionalesPorIdUnidadFuncional(id_unidad).getAdministrativos());
        model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");

		getDesplegarListasComunes(model, session);	

        return "listarReporteAdministrativo"; 
    }

	@RequestMapping("/personal/{id_personal}")
    public String listarPoai(HttpSession session, Model model, @PathVariable("id_personal") Long id_personal){

		model.addAttribute("persona", aServicios.buscarPersonalAdministrativoPorIdPnlPersonalAdministrativo(id_personal).getPersonas());
        model.addAttribute("lPoais", aServicios.buscarPersonalAdministrativoPorIdPnlPersonalAdministrativo(id_personal).getPoais());
        model.addAttribute("busqueda", "find");
		model.addAttribute("operation", "table");

		getDesplegarListasComunes(model, session);	

        return "poaiFormulario"; 
    }
	
    public void getDesplegarListasComunes(Model model, HttpSession session) {
		Personas personas = pServicios.buscarPersonaPorIdUsuario((Long) session.getAttribute("currentUserId"));
		PnlPersonalAdministrativos pnlPersonalAdministrativos = aServicios
				.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(personas.getIdPersona(), 2021, 1);
		model.addAttribute("lSupervisores", poaiServicios.listarPoaisSupervisores());
		model.addAttribute("lCargos", aServicios.listarPnlCargos());
		model.addAttribute("urlN", "inicioObjetivoNuevo");
		model.addAttribute("urlM", "inicioModificarObjetivo");
		model.addAttribute("urlD", "inicioEliminarObjetivo");
		model.addAttribute("regComprobantes", "formRegistrarCombprobantes");
		model.addAttribute("bPnl", pnlPersonalAdministrativos);
		model.addAttribute("lPersonal", pServicios.listarPersonalAdministrativoPorGestion(2021));
		model.addAttribute("regPoais", "registrarPoaisObjetivo");
		model.addAttribute("urlMod", "lanzarFormModificar");
	}
    
}
