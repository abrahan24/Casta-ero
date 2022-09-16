package uap.usic.siga.modelos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.dto.PoaisIdentificacionesResponse;
import uap.usic.siga.entidades.CngCongresistas;
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
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.PoaisDao;

/**
 * Rectorado - USIC Fecha: 2021-11-10
 *
 * @author
 */
@Repository("poaisDao")
public class PoaisDaoImpl implements PoaisDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(rollbackFor = { ServicioException.class })
	@Override
	public List<Poais> listarPoaisPorIdPnlPersonalAdministrativoJPQL(Long idPnlPersonalAdministrativo,
			Integer gestion) {
		String sql = "SELECT poa  FROM Poais poa " + "  LEFT JOIN poa.pnlPersonalAdministrativos pnl  "
				+ " WHERE pnl.idPnlPersonalAdministrativo =:idPnlPersonalAdministrativo "
				+ " AND poa.gestion =:gestion " + " AND poa.idEstado = 'A' "
				+ " AND pnl.idEstado = 'A' Order by poa.gestion ";
		Query q = em.createQuery(sql);
		q.setParameter("idPnlPersonalAdministrativo", idPnlPersonalAdministrativo);
		q.setParameter("gestion", gestion);
		return q.getResultList();
	}

	@Override
	public List<PoaisSupervisores> listarPoaisSupervisoresJPQL() {
		String sql = "SELECT psu FROM PoaisSupervisores psu " + " WHERE psu.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public Poais registrarPoaisSET(Poais poais) {
		em.persist(poais);
		return poais;
	}

	@Override
	public PoaisIdentificaciones registrarPoaisIdentificacionesSET(PoaisIdentificaciones poaisIdentificaciones) {
		em.persist(poaisIdentificaciones);
		return poaisIdentificaciones;
	}

	@Override
	public PoaisSupervisores registrarPoaisSupervisoresSET(PoaisSupervisores poaisSupervisores) {
		em.persist(poaisSupervisores);
		return poaisSupervisores;
	}

	@Override
	public List<PoaisIdentificacionesResponse> listarSupervisoresPorGestionJPQL(Integer gestion) {
		String sql = " SELECT DISTINCT new uap.usic.siga.dto.PoaisIdentificacionesResponse(psu.idSupervisor, psu.descripcion, psu.gestion, prs.idPersona, prs.nombres, prs.paterno, prs.materno)"
				+ " FROM PoaisSupervisores psu LEFT JOIN psu.personas prs " + " WHERE psu.gestion =:gestion   "
				+ " AND psu.idEstado = 'A' " + " AND prs.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("gestion", gestion);
		return q.getResultList();
	}

	// =====================================================================================================
	@Override
	public List<PoaisObjetivos> listarPoaisObjetivosPorIdPersonalAdministrativoJPQL(Long idPnlAdministrativo) {
		String sql = "SELECT po FROM PoaisObjetivos po LEFT JOIN po.poais p LEFT JOIN p.pnlPersonalAdministrativos pa "
				+ "WHERE pa.idPnlPersonalAdministrativo = " + idPnlAdministrativo;

		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<PoaisResultados> listarPoaisResultadosPorIdObjetivoJPQL(Long idObjetivo) {
		String sql = "SELECT pr FROM PoaisResultados pr LEFT JOIN pr.poaisObjetivos po " + "WHERE po.idObjetivo = "
				+ idObjetivo;

		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<PoaisActividades> listarPoaisActividadesPorIdResultadoJPQL(Long idResultado) {
		String sql = "SELECT pa FROM PoaisActividades pa LEFT JOIN pa.poaisResultados pr " + "WHERE pr.idResultado = "
				+ idResultado;

		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<PoaisMeses> listarPoaiMesesPorIdActividadJPQL(Long idActividad) {
		String sql = "SELECT pm FROM PoaisMeses pm LEFT JOIN pm.poaisActividades pa " + "WHERE pa.idActividad = "
				+ idActividad;

		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<PoaisSemanas> listarPoaisSemanasPorIdActividadJPQL(Long idActividad) {
		String sql = "SELECT ps FROM PoaisSemanas ps LEFT JOIN ps.poaisActividades pa " + "WHERE pa.idActividad = "
				+ idActividad;

		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public Poais getPoaiJPQL(Long id) {
		String sql = "SELECT p  FROM Poais p   " + " WHERE p.idPoai =:id  " + " AND p.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("id", id);

		try {
			return (Poais) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisObjetivos registrarPoaisObjetivosSET(PoaisObjetivos poaisObjetivos) {
		em.persist(poaisObjetivos);
		return poaisObjetivos;
	}

	@Override
	public PoaisObjetivos buscarPoaisObjetivosPorIdPoaiJPQL(Long idPoai) {
		String sql = "SELECT po  FROM PoaisObjetivos po LEFT JOIN po.poais p  " + " WHERE p.idPoai =:idPoai  "
				+ " AND p.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idPoai", idPoai);

		try {
			return (PoaisObjetivos) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisObjetivos getPoaiObjetivosJPQL(Long idObjetivo) {
		String sql = "SELECT po FROM PoaisObjetivos po " + " WHERE po.idObjetivo = :idObjetivo  ";
		Query q = em.createQuery(sql);
		q.setParameter("idObjetivo", idObjetivo);

		try {
			return (PoaisObjetivos) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisResultados registrarPoaisResultadosSET(PoaisResultados poaisResultados) {
		em.persist(poaisResultados);
		return poaisResultados;
	}

	@Override
	public PoaisResultados getPoaisResultadosJPQL(Long idResultado) {
		String sql = "SELECT pr FROM PoaisResultados pr " + " WHERE pr.idResultado = :idResultado  ";
		Query q = em.createQuery(sql);
		q.setParameter("idResultado", idResultado);

		try {
			return (PoaisResultados) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisActividades registrarPoaisActividadesSET(PoaisActividades poaisActividades) {
		em.persist(poaisActividades);
		return poaisActividades;
	}

	@Override
	public PoaisDetallesActividades registrarPoaisDetallesActividadesSET(
			PoaisDetallesActividades poaisDetallesActividades) {
		em.persist(poaisDetallesActividades);
		return poaisDetallesActividades;
	}

	@Override
	public PoaisDetallesActividades getPoaisDetallesActividadesJPQL(Long idDetalle) {
		String sql = "SELECT pda FROM PoaisDetallesActividades pda " + " WHERE pda.idDetalleActividad = :idDetalle  ";
		Query q = em.createQuery(sql);
		q.setParameter("idDetalle", idDetalle);

		try {
			return (PoaisDetallesActividades) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisActividades getPoaisActividadesJPQL(Long idActividad) {
		String sql = "SELECT pa FROM PoaisActividades pa " + " WHERE pa.idActividad = :idActividad  ";
		Query q = em.createQuery(sql);
		q.setParameter("idActividad", idActividad);

		try {
			return (PoaisActividades) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisMeses getPoaisMesesJPQL(Long idMes) {
		String sql = "SELECT pm FROM PoaisMeses pm " + " WHERE pm.idMes = :idMes  ";
		Query q = em.createQuery(sql);
		q.setParameter("idMes", idMes);

		try {
			return (PoaisMeses) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisSemanas getPoaisSemanasJPQL(Long idSemana) {
		String sql = "SELECT ps FROM PoaisSemanas ps " + " WHERE ps.idSemana = :idSemana  ";
		Query q = em.createQuery(sql);
		q.setParameter("idSemana", idSemana);

		try {
			return (PoaisSemanas) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	// =====================================================================================================

	@Override
	public List<PoaisRequisitos> listarPoaisRequisitosPorIdPoaiJPQL(Long idPoai) {
		String sql = "SELECT prq FROM PoaisRequisitos prq " + "  LEFT JOIN prq.poais poa  "
				+ " WHERE poa.idPoai =:idPoai  " + " AND poa.idEstado = 'A' " + " AND prq.idEstado ='A'  ";
		Query q = em.createQuery(sql);
		q.setParameter("idPoai", idPoai);
		return q.getResultList();
	}

	@Override
	public List<PoaisRequisitosCualidades> listarRequisitosCualidadesPorIdRequisitoJPQL(Long idRequisito) {
		String sql = "SELECT prc FROM PoaisRequisitosCualidades prc " + "  LEFT JOIN prc.poaisRequisitos prq  "
				+ " WHERE prq.idRequisito =:idRequisito  " + " AND prc.idEstado = 'A' " + " AND prq.idEstado ='A'  "
				+ " ORDER BY prc.requisitoCualidad";
		Query q = em.createQuery(sql);
		q.setParameter("idRequisito", idRequisito);
		return q.getResultList();
	}

	@Override
	public List<PoaisRequisitosExperiencias> listarRequisitosExperienciasPorIdRequisitosJPQL(Long idRequisito) {
		String sql = "SELECT pre FROM PoaisRequisitosExperiencias pre LEFT JOIN pre.poaisRequisitos prq  "
				+ " WHERE prq.idRequisito =:idRequisito  " + " AND pre.idEstado = 'A' " + " AND prq.idEstado ='A'  "
				+ " ORDER BY pre.requisitoExperiencia";
		Query q = em.createQuery(sql);
		q.setParameter("idRequisito", idRequisito);
		return q.getResultList();
	}

	@Override
	public List<PoaisRequisitosFormaciones> listRequisitosFormacionesPorIdRequisitoJPQL(Long idRequisito) {
		String sql = "SELECT prf FROM PoaisRequisitosFormaciones prf " + "  LEFT JOIN prf.poaisRequisitos prq  "
				+ " WHERE prq.idRequisito =:idRequisito  " + " AND prf.idEstado = 'A' " + " AND prq.idEstado ='A'  "
				+ " ORDER BY prf.requisitoFormacion";
		Query q = em.createQuery(sql);
		q.setParameter("idRequisito", idRequisito);
		return q.getResultList();
	}

	@Override
	public Poais buscarPoaisPorIdPnlPersonalAdministrativoGestionIdEstadoGET(Long idPnlPersonalAdministrativo,
			Integer gestion, String idEstado) {
		String sql = " SELECT poa " + " FROM Poais poa LEFT JOIN poa.pnlPersonalAdministrativos pnl  "
				+ " WHERE pnl.idPnlPersonalAdministrativo =:idPnlPersonalAdministrativo "
				+ " AND poa.gestion =:gestion  " + " AND poa.idEstado =: idEstado " + " AND pnl.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idPnlPersonalAdministrativo", idPnlPersonalAdministrativo);
		q.setParameter("gestion", gestion);
		q.setParameter("idEstado", idEstado);
		try {
			return (Poais) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public PoaisRequisitos buscarPoaisRequisitosPorIdPoaiGET(Long idPoai) {
		String sql = " SELECT prq  " + " FROM PoaisRequisitos prq LEFT JOIN prq.poais poa  "
				+ " WHERE poa.idPoai =:idPoai  " + " AND poa.idEstado = 'A'  " + " AND prq.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idPoai", idPoai);
		try {
			return (PoaisRequisitos) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<PoaisMeses> listarMesesJPQL() {
		String sql = "SELECT pm FROM PoaisMeses pm";
		Query q = em.createQuery(sql);
		;
		return q.getResultList();
	}

	@Override
	public List<PoaisSemanas> listarSemanasJPQL() {
		String sql = "SELECT ps FROM PoaisSemanas ps";
		Query q = em.createQuery(sql);
		;
		return q.getResultList();
	}

	@Override
	public PoaisRequisitos registrarPoaisRequisitosSET(PoaisRequisitos poaisRequisitos) {
		em.persist(poaisRequisitos);
		return poaisRequisitos;
	}

	@Override
	public PoaisRequisitosCualidades registrarPoaisRequisitosCualidadesSET(
			PoaisRequisitosCualidades poaisRequiCualidades) {
		em.persist(poaisRequiCualidades);
		return poaisRequiCualidades;
	}

	@Override
	public PoaisRequisitosExperiencias registrarPoaisRequisitosExperienciasSET(
			PoaisRequisitosExperiencias poaisRequiExperiencias) {
		em.persist(poaisRequiExperiencias);
		return poaisRequiExperiencias;
	}

	@Override
	public PoaisRequisitosFormaciones registrarRequisitoFormacionesSET(
			PoaisRequisitosFormaciones poaisRequiFormaciones) {
		em.persist(poaisRequiFormaciones);
		return poaisRequiFormaciones;
	}

	@Override
	public List<PoaisRequisitosCumplimientos> listarPoaisRequisitosCumplimientosPorIdRequisitoJPQL(Long idRequisito) {
		String sql = "SELECT prc FROM PoaisRequisitosCumplimientos prc " + "  LEFT JOIN prc.poaisRequisitos prq  "
				+ " WHERE prq.idRequisito =:idRequisito  " + " AND prc.idEstado = 'A' " + " AND prq.idEstado ='A'  "
				+ " ORDER BY prc.requisitoCumplimiento";
		Query q = em.createQuery(sql);
		q.setParameter("idRequisito", idRequisito);
		return q.getResultList();
	}

	@Override
	public List<PoaisTiposCumplimientos> listarPoaisTiposCumplimientosJPQL() {
		String sql = "SELECT ptc FROM PoaisTiposCumplimientos ptc  " + " WHERE ptc.idEstado = 'A' "
				+ " ORDER BY ptc.tipoCumplimiento";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public PoaisRequisitosCumplimientos registrarPoaisRequisitosCumplimientosSET(
			PoaisRequisitosCumplimientos poaisRequisitosCumplimientos) {
		em.persist(poaisRequisitosCumplimientos);
		return poaisRequisitosCumplimientos;
	}

	// ==================== Estadisticas Poais Resultados =====================

	@Override
	public List<Poais> listarPoaisPorIdPnlPersonalAdministrativoTodosJPQL(Long idPnlPersonalAdministrativo) {
		String sql = "SELECT  poa  FROM Poais poa " + "  LEFT JOIN poa.pnlPersonalAdministrativos pnl  "
				+ " WHERE pnl.idPnlPersonalAdministrativo =:idPnlPersonalAdministrativo " + " AND poa.idEstado = 'A' "
				+ " AND pnl.idEstado = 'A' " + " ORDER BY poa.gestion ";
		Query q = em.createQuery(sql);
		q.setParameter("idPnlPersonalAdministrativo", idPnlPersonalAdministrativo);
		return q.getResultList();
	}

	@Override
	public PoaisIdentificaciones getPoaiIdentificacionIdPoai(Long id) {
		String sql = " SELECT pact  " + 
				" FROM PoaisIdentificaciones pact LEFT JOIN pact.poais poa  " + 
				" WHERE poa.idPoai =:idPoai  " + 
				" AND poa.idEstado = 'A'";
		Query q = em.createQuery(sql);
		q.setParameter("idPoai", id);
		try {
			return (PoaisIdentificaciones) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisObjetivos getPoaiObjetivoIdPoai(Long id) {
		String sql = " SELECT pobj  " + 
				" FROM PoaisObjetivos pobj LEFT JOIN pobj.poais poa  " + 
				" WHERE poa.idPoai =:idPoai  " + 
				" AND poa.idEstado = 'A'";
		Query q = em.createQuery(sql);
		q.setParameter("idPoai", id);
		try {
			return (PoaisObjetivos) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PoaisSupervisores buscarSupervisorPersonaCargo(Long id_persona, Long id_cargo) {
		String sql = " SELECT posup  " + 
				" FROM PoaisSupervisores posup LEFT JOIN posup.personas p  LEFT JOIN posup.pnlCargos c" + 
				" WHERE p.idPersona =:idPersona  " + 
				" AND c.idPnlCargos =:idPnlCargos  " + 
				" AND posup.idEstado = 'A'";
		Query q = em.createQuery(sql);
		q.setParameter("idPersona", id_persona);
		q.setParameter("idPnlCargos", id_cargo);
		try {
			return (PoaisSupervisores) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<PoaisIdentificaciones> getAllPoaiSupervisor(Long id_persona) {
		String sql = "SELECT piden FROM PoaisIdentificaciones piden "
				+ "LEFT JOIN piden.poais poa "
				+ "LEFT JOIN piden.poaisSupervisores psup "
				+ "LEFT JOIN psup.personas per "
				+ "WHERE per.idPersona = :idPersona ";
		Query q = em.createQuery(sql);
		q.setParameter("idPersona", id_persona);
		return q.getResultList();
	}

}
