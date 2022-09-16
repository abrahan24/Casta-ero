package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.dto.PoaisIdentificacionesResponse;
import uap.usic.siga.entidades.PnlCargos;
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
public interface PoaisDao {

	public List<Poais> listarPoaisPorIdPnlPersonalAdministrativoJPQL(Long idPnlPersonalAdministrativo, Integer gestion);
	public List<PoaisIdentificaciones> getAllPoaiSupervisor(Long id_persona);
	
	public List<PoaisSupervisores> listarPoaisSupervisoresJPQL();
	
	public Poais registrarPoaisSET(Poais poais);
	public PoaisIdentificaciones registrarPoaisIdentificacionesSET(PoaisIdentificaciones poaisIdentificaciones);
	public PoaisSupervisores registrarPoaisSupervisoresSET(PoaisSupervisores poaisSupervisores);
	public PoaisSupervisores buscarSupervisorPersonaCargo(Long id_persona, Long id_cargo);
	
	public PoaisIdentificaciones getPoaiIdentificacionIdPoai(Long id);
	
	public List<PoaisIdentificacionesResponse>listarSupervisoresPorGestionJPQL(Integer gestion);
	
	// =====================================================================================================
	public List<PoaisObjetivos> listarPoaisObjetivosPorIdPersonalAdministrativoJPQL(Long idPoai);

	public List<PoaisResultados> listarPoaisResultadosPorIdObjetivoJPQL(Long idObjetivo);

	public List<PoaisActividades> listarPoaisActividadesPorIdResultadoJPQL(Long idResultado);

	public List<PoaisMeses> listarPoaiMesesPorIdActividadJPQL(Long idActividad);

	public List<PoaisSemanas> listarPoaisSemanasPorIdActividadJPQL(Long idActividad);
	
	public Poais getPoaiJPQL(Long id);
	
	public PoaisObjetivos getPoaiObjetivoIdPoai(Long id);
	
	public PoaisObjetivos registrarPoaisObjetivosSET(PoaisObjetivos poaisObjetivos);
	
	public PoaisObjetivos buscarPoaisObjetivosPorIdPoaiJPQL(Long idPoai);
	
	public PoaisObjetivos getPoaiObjetivosJPQL(Long idObjetivo);
	
	public PoaisResultados registrarPoaisResultadosSET(PoaisResultados poaisResultados);
	
	public PoaisResultados getPoaisResultadosJPQL(Long idResultado);
	
	public List<PoaisMeses> listarMesesJPQL();
	
	public List<PoaisSemanas> listarSemanasJPQL();
	
	public PoaisActividades registrarPoaisActividadesSET(PoaisActividades poaisActividades);
	
	public PoaisDetallesActividades registrarPoaisDetallesActividadesSET(PoaisDetallesActividades poaisDetallesActividades);
	
	public PoaisDetallesActividades getPoaisDetallesActividadesJPQL(Long idDetalle);
	
	public PoaisActividades getPoaisActividadesJPQL(Long idActividad);
	
	public PoaisMeses getPoaisMesesJPQL(Long idMes);
	
	public PoaisSemanas getPoaisSemanasJPQL(Long idSemana);
	
	// =====================================================================================================

	
	public List<PoaisRequisitos> listarPoaisRequisitosPorIdPoaiJPQL(Long idPoai);
	public List<PoaisRequisitosCualidades> listarRequisitosCualidadesPorIdRequisitoJPQL(Long idRequisito);
	public List<PoaisRequisitosExperiencias> listarRequisitosExperienciasPorIdRequisitosJPQL(Long idRequisito);
	public List<PoaisRequisitosFormaciones> listRequisitosFormacionesPorIdRequisitoJPQL(Long idRequisito);
	
	public Poais buscarPoaisPorIdPnlPersonalAdministrativoGestionIdEstadoGET(Long idPnlPersonalAdministrativo, Integer gestion, String idEstado);
	public PoaisRequisitos buscarPoaisRequisitosPorIdPoaiGET(Long idPoai);
	
	public PoaisRequisitos registrarPoaisRequisitosSET(PoaisRequisitos poaisRequisitos);
	public PoaisRequisitosCualidades registrarPoaisRequisitosCualidadesSET(PoaisRequisitosCualidades poaisRequiCualidades);
	public PoaisRequisitosExperiencias registrarPoaisRequisitosExperienciasSET(PoaisRequisitosExperiencias poaisRequiExperiencias);
	public PoaisRequisitosFormaciones registrarRequisitoFormacionesSET(PoaisRequisitosFormaciones poaisRequiFormaciones);
	
	public List<PoaisTiposCumplimientos> listarPoaisTiposCumplimientosJPQL();
	public List<PoaisRequisitosCumplimientos> listarPoaisRequisitosCumplimientosPorIdRequisitoJPQL(Long idRequisito);
	
	public PoaisRequisitosCumplimientos registrarPoaisRequisitosCumplimientosSET(PoaisRequisitosCumplimientos poaisRequisitosCumplimientos);

	//================= Estadisticas de Poais Resultados=========================================================================================

	public List<Poais> listarPoaisPorIdPnlPersonalAdministrativoTodosJPQL(Long idPnlPersonalAdministrativo);
	
}
