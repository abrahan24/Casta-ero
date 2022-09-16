package uap.usic.siga.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import uap.usic.siga.modelos.PoaisDao;
import uap.usic.siga.servicios.PoaisServicios;

/**
 * Rectorado - USIC Fecha: 2021-11-10
 *
 * @author
 */
@Service("poaisServicios")
@Transactional
public class PoaisServiciosImpl implements PoaisServicios {

	@Autowired
	public PoaisDao dao;

	@Override
	public List<Poais> listarPoaisPorIdPnlPersonalAdministrativo(Long idPnlPersonalAdministrativo, Integer gestion) {
		return dao.listarPoaisPorIdPnlPersonalAdministrativoJPQL(idPnlPersonalAdministrativo, gestion);
	}

	@Override
	public List<PoaisSupervisores> listarPoaisSupervisores() {
		return dao.listarPoaisSupervisoresJPQL();
	}

	@Override
	public Poais registrarPoais(Poais poais) {
		return dao.registrarPoaisSET(poais);
	}

	@Override
	public PoaisIdentificaciones registrarPoaisIdentificaciones(PoaisIdentificaciones poaisIdentificaciones) {
		return dao.registrarPoaisIdentificacionesSET(poaisIdentificaciones);
	}

	@Override
	public PoaisSupervisores registrarPoaisSupervisores(PoaisSupervisores poaisSupervisores) {
		return dao.registrarPoaisSupervisoresSET(poaisSupervisores);
	}

	@Override
	public List<PoaisIdentificacionesResponse> listarSupervisoresPorGestion(Integer gestion) {
		return dao.listarSupervisoresPorGestionJPQL(gestion);
	}

	@Override
	public PoaisResultados getPoaisResultados(Long idResultado) {
		return dao.getPoaisResultadosJPQL(idResultado);
	}
	
	@Override
	public PoaisDetallesActividades getPoaisDetallesActividades(Long idDetalle) {
		return dao.getPoaisDetallesActividadesJPQL(idDetalle);
	}

	// ===================================================================================================
	@Override
	public List<PoaisObjetivos> listarPoaisObjetivosPorIdPersonalAdministrativo(Long idPnlAdministrativo) {
		return dao.listarPoaisObjetivosPorIdPersonalAdministrativoJPQL(idPnlAdministrativo);
	}

	@Override
	public List<PoaisResultados> listarPoaisResultadosPorIdObjetivo(Long idObjetivo) {
		return dao.listarPoaisResultadosPorIdObjetivoJPQL(idObjetivo);
	}

	@Override
	public List<PoaisActividades> listarPoaisActividadesPorIdResultado(Long idResultado) {
		return dao.listarPoaisActividadesPorIdResultadoJPQL(idResultado);
	}

	@Override
	public List<PoaisMeses> listarPoaiMesesPorIdActividad(Long idActividad) {
		return dao.listarPoaiMesesPorIdActividadJPQL(idActividad);
	}

	@Override
	public List<PoaisSemanas> listarPoaisSemanasPorIdActividad(Long idActividad) {
		return dao.listarPoaisSemanasPorIdActividadJPQL(idActividad);
	}

	@Override
	public Poais getPoai(Long id) {
		return dao.getPoaiJPQL(id);
	}

	@Override
	public PoaisObjetivos registrarPoaisObjetivos(PoaisObjetivos poaisObjetivos) {
		return dao.registrarPoaisObjetivosSET(poaisObjetivos);
	}

	@Override
	public PoaisObjetivos buscarPoaisObjetivosPorIdPoai(Long idPoai) {
		return dao.buscarPoaisObjetivosPorIdPoaiJPQL(idPoai);
	}

	@Override
	public PoaisObjetivos getPoaiObjetivos(Long idObjetivo) {
		return dao.getPoaiObjetivosJPQL(idObjetivo);
	}

	@Override
	public PoaisResultados registrarPoaisResultados(PoaisResultados poaisResultados) {
		return dao.registrarPoaisResultadosSET(poaisResultados);
	}

	@Override
	public List<PoaisMeses> listarMeses() {
		return dao.listarMesesJPQL();
	}

	@Override
	public List<PoaisSemanas> listarSemanas() {
		return dao.listarSemanasJPQL();
	}

	@Override
	public PoaisActividades registrarPoaisActividades(PoaisActividades poaisActividades) {
		return dao.registrarPoaisActividadesSET(poaisActividades);
	}

	@Override
	public PoaisDetallesActividades registrarPoaisDetallesActividades(
			PoaisDetallesActividades poaisDetallesActividades) {
		return dao.registrarPoaisDetallesActividadesSET(poaisDetallesActividades);
	}

	@Override
	public PoaisActividades getPoaisActividades(Long idActividad) {
		return dao.getPoaisActividadesJPQL(idActividad);
	}
	

	@Override
	public PoaisMeses getPoaisMeses(Long idMes) {
		return dao.getPoaisMesesJPQL(idMes);
	}

	@Override
	public PoaisSemanas getPoaisSemanas(Long idSemana) {
		return dao.getPoaisSemanasJPQL(idSemana);
	}

	// ==============================================================================================

	@Override
	public List<PoaisRequisitos> listarPoaisRequisitosPorIdPoai(Long idPoai) {
		return dao.listarPoaisRequisitosPorIdPoaiJPQL(idPoai);
	}

	@Override
	public List<PoaisRequisitosCualidades> listarRequisitosCualidadesPorIdRequisito(Long idRequisito) {
		return dao.listarRequisitosCualidadesPorIdRequisitoJPQL(idRequisito);
	}

	@Override
	public List<PoaisRequisitosExperiencias> listarRequisitosExperienciasPorIdRequisitos(Long idRequisito) {
		return dao.listarRequisitosExperienciasPorIdRequisitosJPQL(idRequisito);
	}

	@Override
	public List<PoaisRequisitosFormaciones> listRequisitosFormacionesPorIdRequisito(Long idRequisito) {
		return dao.listRequisitosFormacionesPorIdRequisitoJPQL(idRequisito);
	}

	@Override
	public Poais buscarPoaisPorIdPnlPersonalAdministrativoGestionIdEstado(Long idPnlPersonalAdministrativo,
			Integer gestion, String idEstado) {
		return dao.buscarPoaisPorIdPnlPersonalAdministrativoGestionIdEstadoGET(idPnlPersonalAdministrativo, gestion,
				idEstado);
	}

	@Override
	public PoaisRequisitos buscarPoaisRequisitosPorIdPoai(Long idPoai) {
		return dao.buscarPoaisRequisitosPorIdPoaiGET(idPoai);
	}
	
	@Override
	public PoaisRequisitos registrarPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
		return dao.registrarPoaisRequisitosSET(poaisRequisitos);
	}

	@Override
	public PoaisRequisitosCualidades registrarPoaisRequisitosCualidades(PoaisRequisitosCualidades poaisRequiCualidades) {
		return dao.registrarPoaisRequisitosCualidadesSET(poaisRequiCualidades);
	}

	@Override
	public PoaisRequisitosExperiencias registrarPoaisRequisitosExperiencias(PoaisRequisitosExperiencias poaisRequiExperiencias) {
		return dao.registrarPoaisRequisitosExperienciasSET(poaisRequiExperiencias);
	}

	@Override
	public PoaisRequisitosFormaciones registrarRequisitoFormaciones(PoaisRequisitosFormaciones poaisRequiFormaciones) {
		return dao.registrarRequisitoFormacionesSET(poaisRequiFormaciones);
	}

	@Override
	public List<PoaisTiposCumplimientos> listarPoaisTiposCumplimientos() {
		return dao.listarPoaisTiposCumplimientosJPQL();
	}

	@Override
	public List<PoaisRequisitosCumplimientos> listarPoaisRequisitosCumplimientosPorIdRequisito(Long idRequisito) {
		return dao.listarPoaisRequisitosCumplimientosPorIdRequisitoJPQL(idRequisito);
	}

	@Override
	public PoaisRequisitosCumplimientos registrarPoaisRequisitosCumplimientos(PoaisRequisitosCumplimientos poaisRequisitosCumplimientos) {
		return dao.registrarPoaisRequisitosCumplimientosSET(poaisRequisitosCumplimientos);
	}

	@Override
	public List<Poais> listarPoaisPorIdPnlPersonalAdministrativoTodos(Long idPnlPersonalAdministrativo) {
		return dao.listarPoaisPorIdPnlPersonalAdministrativoTodosJPQL(idPnlPersonalAdministrativo);
	}

	@Override
	public PoaisIdentificaciones getPoaiIdentificacionIdPoai(Long id) {
		// TODO Auto-generated method stub
		return dao.getPoaiIdentificacionIdPoai(id);
	}

	@Override
	public PoaisObjetivos getPoaiObjetivoIdPoai(Long id) {
		// TODO Auto-generated method stub
		return dao.getPoaiObjetivoIdPoai(id);
	}

	@Override
	public PoaisSupervisores buscarSupervisorPersonaCargo(Long id_persona, Long id_cargo) {
		// TODO Auto-generated method stub
		return dao.buscarSupervisorPersonaCargo(id_persona, id_cargo);
	}

	@Override
	public List<PoaisIdentificaciones> getAllPoaiSupervisor(Long id_persona) {
		// TODO Auto-generated method stub
		return dao.getAllPoaiSupervisor(id_persona);
	}

	

}
