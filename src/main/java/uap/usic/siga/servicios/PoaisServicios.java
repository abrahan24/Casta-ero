package uap.usic.siga.servicios;

import java.util.List;

import uap.usic.siga.dto.PoaisIdentificacionesResponse;
import uap.usic.siga.entidades.Poais;
import uap.usic.siga.entidades.PoaisActividades;
import uap.usic.siga.entidades.PoaisDetallesActividades;
import uap.usic.siga.entidades.PoaisIdentificaciones;
import uap.usic.siga.entidades.PoaisMeses;
import uap.usic.siga.entidades.PoaisObjetivos;
import uap.usic.siga.entidades.PoaisRequisitos;
import uap.usic.siga.entidades.PoaisRequisitosCualidades;
import uap.usic.siga.entidades.PoaisRequisitosCumplimientos;
import uap.usic.siga.entidades.PoaisRequisitosExperiencias;
import uap.usic.siga.entidades.PoaisRequisitosFormaciones;
import uap.usic.siga.entidades.PoaisResultados;
import uap.usic.siga.entidades.PoaisSemanas;
import uap.usic.siga.entidades.PoaisSupervisores;
import uap.usic.siga.entidades.PoaisTiposCumplimientos;

/**
 * Rectorado - USIC Fecha: 2021-11-10
 *
 * @author
 */
public interface PoaisServicios {

	public List<Poais> listarPoaisPorIdPnlPersonalAdministrativo(Long idPnlPersonalAdministrativo, Integer gestion);

	public List<PoaisSupervisores> listarPoaisSupervisores();

	public Poais registrarPoais(Poais poais);

	public List<PoaisIdentificaciones> getAllPoaiSupervisor(Long id_persona);

	public PoaisIdentificaciones registrarPoaisIdentificaciones(PoaisIdentificaciones poaisIdentificaciones);

	public PoaisSupervisores registrarPoaisSupervisores(PoaisSupervisores poaisSupervisores);
	
	public PoaisSupervisores buscarSupervisorPersonaCargo(Long id_persona, Long id_cargo);

	public List<PoaisIdentificacionesResponse> listarSupervisoresPorGestion(Integer gestion);
	
	public PoaisIdentificaciones getPoaiIdentificacionIdPoai(Long id);
	
	// =====================================================================================================
	public List<PoaisObjetivos> listarPoaisObjetivosPorIdPersonalAdministrativo(Long idPnlAdministrativo);

	public List<PoaisResultados> listarPoaisResultadosPorIdObjetivo(Long idObjetivo);

	public List<PoaisActividades> listarPoaisActividadesPorIdResultado(Long idResultado);

	public List<PoaisMeses> listarPoaiMesesPorIdActividad(Long idActividad);

	public List<PoaisSemanas> listarPoaisSemanasPorIdActividad(Long idActividad);

	public Poais getPoai(Long id);
	
	public PoaisObjetivos getPoaiObjetivoIdPoai(Long id);

	public PoaisObjetivos registrarPoaisObjetivos(PoaisObjetivos poaisObjetivos);

	public PoaisObjetivos buscarPoaisObjetivosPorIdPoai(Long idPoai);

	public PoaisObjetivos getPoaiObjetivos(Long idObjetivo);

	public PoaisResultados registrarPoaisResultados(PoaisResultados poaisResultados);

	public PoaisResultados getPoaisResultados(Long idResultado);

	public List<PoaisMeses> listarMeses();

	public List<PoaisSemanas> listarSemanas();

	public PoaisActividades registrarPoaisActividades(PoaisActividades poaisActividades);

	public PoaisDetallesActividades registrarPoaisDetallesActividades(
			PoaisDetallesActividades poaisDetallesActividades);

	public PoaisDetallesActividades getPoaisDetallesActividades(Long idDetalle);

	public PoaisActividades getPoaisActividades(Long idActividad);

	public PoaisMeses getPoaisMeses(Long idMes);

	public PoaisSemanas getPoaisSemanas(Long idSemana);
	// =====================================================================================================

	public List<PoaisRequisitos> listarPoaisRequisitosPorIdPoai(Long idPoai);

	public List<PoaisRequisitosCualidades> listarRequisitosCualidadesPorIdRequisito(Long idRequisito);

	public List<PoaisRequisitosExperiencias> listarRequisitosExperienciasPorIdRequisitos(Long idRequisito);

	public List<PoaisRequisitosFormaciones> listRequisitosFormacionesPorIdRequisito(Long idRequisito);

	public Poais buscarPoaisPorIdPnlPersonalAdministrativoGestionIdEstado(Long idPnlPersonalAdministrativo,
			Integer gestion, String idEstado);

	public PoaisRequisitos buscarPoaisRequisitosPorIdPoai(Long idPoai);
	
	public PoaisRequisitos registrarPoaisRequisitos(PoaisRequisitos poaisRequisitos);
	public PoaisRequisitosCualidades registrarPoaisRequisitosCualidades(PoaisRequisitosCualidades poaisRequiCualidades);
	public PoaisRequisitosExperiencias registrarPoaisRequisitosExperiencias(PoaisRequisitosExperiencias poaisRequiExperiencias);
	public PoaisRequisitosFormaciones registrarRequisitoFormaciones(PoaisRequisitosFormaciones poaisRequiFormaciones);
	
	public List<PoaisTiposCumplimientos> listarPoaisTiposCumplimientos();
	public List<PoaisRequisitosCumplimientos> listarPoaisRequisitosCumplimientosPorIdRequisito(Long idRequisito);
	
	public PoaisRequisitosCumplimientos registrarPoaisRequisitosCumplimientos(PoaisRequisitosCumplimientos poaisRequisitosCumplimientos);

	// ==================== Estadisticas Poais Resultados ==================
	
	public List<Poais> listarPoaisPorIdPnlPersonalAdministrativoTodos(Long idPnlPersonalAdministrativo);

}
