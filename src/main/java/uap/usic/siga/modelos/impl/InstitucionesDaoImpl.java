package uap.usic.siga.modelos.impl;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import uap.usic.siga.entidades.Facultades;
import uap.usic.siga.entidades.FclCarreras;
import uap.usic.siga.entidades.FclEstamentos;
import uap.usic.siga.entidades.InsDireccionesFuncionales;
import uap.usic.siga.entidades.InsSede;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.InsUbicacionesOrganicas;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.Institucion;
import uap.usic.siga.entidades.Instituciones;
import uap.usic.siga.modelos.InstitucionesDao;

/**
 *
 * @author Yessenia
 */
@Repository("institucionesDao")
public class InstitucionesDaoImpl implements InstitucionesDao {

	@PersistenceContext
	private javax.persistence.EntityManager em;

	@Override
	public List<InsSedes> listarInsSedesJQPL() {
		String sql = "SELECT isd FROM InsSedes isd " + " WHERE isd.idEstado = 'A' " + " ORDER BY isd.sede ";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<Instituciones> listarInstitucionesGET() {
		String sql = "SELECT i FROM Instituciones i";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public void registrarInsSedesSET(InsSedes inssedes) {
		em.persist(inssedes);
	}

	@Override
	public List<InsUbicacionesOrganicas> listarInsUbicacionesOrganicasIdSedeGET(Long idSede) {
		String sql = "SELECT u FROM InsUbicacionesOrganicas u " + " LEFT JOIN u.sedes s " + " WHERE s.idSede=:idSede "
				+ " AND s.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idSede", idSede);
		return q.getResultList();
	}

	@Override
	public List<String> listarUbicacionesGET(Long id) {
		String sql = "SELECT u.ubicacionOrganica " + " FROM InsUbicacionesOrganicas u " + " LEFT JOIN u.sedes s "
				+ " WHERE s.idSede =:id " + " AND s.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("id", id);
		return q.getResultList();
	}

	@Override
	public InsSedes buscarInsSedesGET(Long idSede) {
		String sql = "SELECT ins FROM InsSedes ins WHERE ins.idSede =:idSede";
		Query q = em.createQuery(sql);
		q.setParameter("idSede", idSede);

		try {
			return (InsSedes) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Institucion> listarInstitucionGET() {
		String sql = "SELECT i FROM Institucion i";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<InsSedes> listarSedesPorIdInstitucionJPQL(Long idInstitucion) {
		String sql = "SELECT s FROM InsSedes s LEFT JOIN s.instituciones i "
				+ " WHERE i.idInstitucion =:idInstitucion ";
		Query q = em.createQuery(sql);
		q.setParameter("idInstitucion", idInstitucion);
		return q.getResultList();
	}

	@Override
	public InsSedes buscarInsSedesPorSedeGET(String sede) {
		String sql = "SELECT s FROM InsSedes s WHERE s.sede =:sede Order by s.sede";
		Query q = em.createQuery(sql);
		q.setParameter("sede", sede);

		try {
			return (InsSedes) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void guardarInsSedesSET(InsSedes insSedes) {
		em.persist(insSedes);
	}

	@Override
	public InsUnidadesFuncionales buscarInsUnidadesFuncionalesPorUnidadGET(String unidadFuncional) {
		String sql = "SELECT u FROM InsUnidadesFuncionales u WHERE u.unidadFuncional =:unidadFuncional";
		Query q = em.createQuery(sql);
		q.setParameter("unidadFuncional", unidadFuncional);

		try {
			return (InsUnidadesFuncionales) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void guardarInsUnidadesFuncionalesSET(InsUnidadesFuncionales insUnidadesFuncionales) {
		em.persist(insUnidadesFuncionales);
	}

	@Override
	public void guardarInstitucionesSET(Instituciones instituciones) {
		em.persist(instituciones);
	}

	@Override
	public List<InsUnidadesFuncionales> listarUnidadesFuncionalesJPQL() {
		String sql = "SELECT unf " + " FROM InsUnidadesFuncionales unf " + " WHERE unf.idEstado = 'A' "
				+ " ORDER BY unf.unidadFuncional";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<InsDireccionesFuncionales> listarDireccionesFuncionalesPorIdSedeJPQL(Long idSede) {
		String sql = "SELECT dfn FROM InsDireccionesFuncionales dfn " + " LEFT JOIN dfn.insSedes sds "
				+ " WHERE sds.idSede=:idSede " + " AND sds.idEstado = 'A' " + " AND dfn.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idSede", idSede);
		return q.getResultList();
	}

	@Override
	public List<InsUnidadesFuncionales> listarUnidadesFuncionalesPorIdDireccionFuncionalJPQL(
			Long idDireccionFuncional) {
		String sql = " SELECT ufn FROM InsUnidadesFuncionales ufn " + " LEFT JOIN ufn.insDireccionesFuncionales dfn "
				+ " WHERE dfn.idDireccionFuncional =:idDireccionFuncional " + " AND ufn.idEstado = 'A' "
				+ " AND dfn.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idDireccionFuncional", idDireccionFuncional);
		return q.getResultList();
	}

	@Override
	public InsDireccionesFuncionales buscarDireccionFuncionalPorIdDireccionaFuncionalGET(Long idDireccionFuncional) {
		String sql = "SELECT dnf FROM InsDireccionesFuncionales dnf"
				+ " WHERE dnf.idDireccionFuncional =:idDireccionFuncional " + " AND dnf.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idDireccionFuncional", idDireccionFuncional);

		try {
			return (InsDireccionesFuncionales) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public InsUnidadesFuncionales buscarInsUnidadesFuncionalesPorIdUnidadFuncionalGET(Long idUnidadFuncional) {
		String sql = "SELECT unf FROM InsUnidadesFuncionales unf" + " WHERE unf.idUnidadFuncional =:idUnidadFuncional "
				+ " AND unf.idEstado = 'A' " + " ORDER BY unf.unidadFuncional ";
		Query q = em.createQuery(sql);
		q.setParameter("idUnidadFuncional", idUnidadFuncional);

		try {
			return (InsUnidadesFuncionales) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void registrarInsDireccionesFuncionalesSET(InsDireccionesFuncionales insDireccionesFuncionales) {
		em.persist(insDireccionesFuncionales);
	}

	@Override
	public List<InsUnidadesFuncionales> listarUnidadesFuncionalesPorIdSedeJPQL(Long idSede) {
		String sql = " SELECT ufn FROM InsUnidadesFuncionales ufn " + " LEFT JOIN ufn.insDireccionesFuncionales dfn "
				+ " LEFT JOIN dfn.insSedes ins " + " WHERE ins.idSede =:idSede " + " AND ufn.idEstado = 'A' "
				+ " AND dfn.idEstado = 'A' " + " AND ins.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idSede", idSede);
		return q.getResultList();
	}

	@Override
	public List<Facultades> listarFacultadesPorIdUniversidadJPQL(Long idUniversidad) {
		String sql = "SELECT fcl FROM Facultades fcl LEFT JOIN fcl.universidades unv " + " WHERE fcl.idEstado = 'A' "
				+ " AND unv.idUniversidad =:idUniversidad" + " ORDER BY fcl.facultad";
		Query q = em.createQuery(sql);
		q.setParameter("idUniversidad", idUniversidad);
		return q.getResultList();
	}

	@Override
	public List<FclCarreras> listarCarrerasPorIdFacultadJPQL(Long idFacultad) {
		String sql = "SELECT fcr FROM FclCarreras fcr LEFT JOIN fcr.facultades fcl " + " WHERE fcr.idEstado = 'A' "
				+ " AND fcl.idFacultad =:idFacultad" + " ORDER BY fcr.carrera";
		Query q = em.createQuery(sql);
		q.setParameter("idFacultad", idFacultad);
		return q.getResultList();

	}

	@Override
	public List<FclEstamentos> listarEstamentosJPQL() {
		String sql = "SELECT fle FROM FclEstamentos fle " + " WHERE fle.idEstado = 'A' " + " ORDER BY fle.estamento";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public Facultades buscarFacultadPorIdFacultadGET(Long idFacultad) {
		String sql = "SELECT fcl FROM Facultades fcl WHERE fcl.idFacultad =:idFacultad ";
		Query q = em.createQuery(sql);
		q.setParameter("idFacultad", idFacultad);
		try {
			return (Facultades) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public FclEstamentos buscarEstamentoPorIdEstamentoGET(Long idEstamento) {
		String sql = "SELECT est FROM FclEstamentos est WHERE est.idEstamento =:idEstamento ";
		Query q = em.createQuery(sql);
		q.setParameter("idEstamento", idEstamento);
		try {
			return (FclEstamentos) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public InsDireccionesFuncionales getDireccionFuncional(String direccion) {
		String sql = "SELECT est FROM direccionFuncional est "
				+ "WHERE est.direccionFuncional =:direccionFuncional ";
		Query q = em.createQuery(sql);
		q.setParameter("direccionFuncional", direccion);
		try {
			return (InsDireccionesFuncionales) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
