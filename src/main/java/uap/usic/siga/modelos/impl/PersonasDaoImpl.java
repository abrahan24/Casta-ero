package uap.usic.siga.modelos.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.dto.PersonasResponse;
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidades.PrsTiposSexos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.PersonasDao;

/**
 *
 * @author fmbma
 */
@Repository("personasDao")
public class PersonasDaoImpl implements PersonasDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
    @Override
    public List<Personas> listarPersonasGET() {
        String sql = " SELECT p "
                + " FROM Personas p LEFT JOIN p.prsPaises pa "
                + " WHERE p.idEstado = 'A' ORDER BY p.paterno ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<PrsPaises> listarPaisesGET() {
        String sql = "SELECT p FROM PrsPaises p "
                + " WHERE p.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarPersonasSET(Personas personas) {
        em.persist(personas);
    }

    @Override
    public List<Object[]> listarPersonasPorCiJPQL() {
        String sql = "SELECT p.idPersona,p.nombres, p.paterno, p.materno,p.ci,p.fecNacimiento,p.telefono, pa.pais"
                + " FROM Personas p LEFT JOIN p.prsPaises pa "
                + " Order by p.paterno";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public Personas buscarPersonasPorCedulaIdentidadGET(String ci) {
        String sql = " Select p "
                + " from Personas p "
                + " Where p.ci = :ci "
                + " AND p.idEstado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("ci", ci);
        try {
            return (Personas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Personas buscarPersonaPorIdUsuarioGET(Long idUsuario) {
        String sql = " Select p "
                + " from Usuarios u LEFT JOIN u.personas p "
                + " Where u.idUsuario = :idUsuario "
                + " AND p.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idUsuario", idUsuario);
        try {
            return (Personas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void registrarPrsPaisesSET(PrsPaises prsPaises) {
        em.persist(prsPaises);
    }

    @Override
    public List<PrsTiposSexos> listarTiposSexosJPQL() {
        String sql = "SELECT ts FROM PrsTiposSexos ts "
                + " WHERE ts.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarPrsTiposSexosSET(PrsTiposSexos prsTiposSexos) {
        em.persist(prsTiposSexos);
    }

    @Override
    public void modificarPersonasSET(Personas personas) {
        em.merge(personas);
    }

    @Override
    public void eliminarPersonasSET(Personas personas) {
        em.merge(personas);
    }

    @Override
    public Personas buscarPersonasPorIdPersonaGET(Long idPersona) {
        String sql = " SELECT p "
                + " FROM Personas p "
                + " WHERE p.idPersona = :idPersona "
                + " AND p.idEstado != 'X'";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        try {
            return (Personas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PrsCiExpedidos> listarCedulaIdentidadexpedidosJPQL() {
        String sql = "SELECT cix FROM PrsCiExpedidos cix "
                + " WHERE cix.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarPrsCedulaIdentidadExpedidosSET(PrsCiExpedidos prsCiExpedidos) {
        em.persist(prsCiExpedidos);
    }

    @Override
    public List<PersonasResponse> listarPersonasResponsePersonalizadoJPQL() {
        String sql = " SELECT new uap.usic.siga.dto.PersonasResponse(p) "
                + " FROM Personas p LEFT JOIN p.prsPaises pa "
                + " WHERE p.idEstado = 'A' ORDER BY p.paterno ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public PnlPersonalAdministrativos buscarPnlPersonalAdministrativosPorIdPersonaIdEstadoGET(Long idPersona, String idEstado) {
        String sql = " SELECT pa "
                + " FROM PnlPersonalAdministrativos pa LEFT JOIN pa.personas p "
                + " WHERE p.idPersona = :idPersona "
                + " AND p.idEstado =:idEstado"
                + " AND pa.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);
        try {
            return (PnlPersonalAdministrativos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CjaIngresos buscarCjaIngresosPoIdPersonaIdEstadoGET(Long idPersona, String idEstado) {
        String sql = " SELECT ci "
                + " FROM CjaIngresos ci LEFT JOIN ci.personas p "
                + " WHERE p.idPersona = :idPersona "
                + " AND p.idEstado = :idEstado"
                + " AND ci.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);
        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdPersonaIdEstadoGET(Long idPersona, String idEstado) {
        String sql = " SELECT cig "
                + " FROM CjaGastosEjecutados cig LEFT JOIN cig.personas p "
                + " WHERE p.idPersona = :idPersona "
                + " AND p.idEstado = :idEstado"
                + " AND cig.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);
        try {
            return (CjaGastosEjecutados) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuarios buscarUsuariosPorIdPersonaIdEstadoGET(Long idPersona, String idEstado) {
        String sql = " SELECT u "
                + " FROM Usuarios u LEFT JOIN u.personas p "
                + " WHERE p.idPersona = :idPersona "
                + " AND p.idEstado = :idEstado"
                + " AND u.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Personas buscarPersonasPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado) {
        String sql = "SELECT p "
                + "FROM CjaGastosEjecutados cge LEFT JOIN cge.personas p  "
                + " WHERE cge.idCjaGastoEjecutado = :idCjaGastoEjecutado "
                + " AND cge.idEstado = 'A'  "
                + " AND p.idEstado = 'A'  ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaGastoEjecutado", idCjaGastoEjecutado);

        try {
            return (Personas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Personas buscarPersonasPorIdCjaIngresoGET(Long idCjaIngreso) {
        String sql = " SELECT p "
                + " FROM CjaIngresos ci LEFT JOIN ci.personas p "
                + " WHERE ci.idCjaIngreso = :idCjaIngreso "
                + " AND p.idEstado = :idEstado"
                + " AND ci.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        try {
            return (Personas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Personas> listarCjaGastosEjecutadosPersonasPorIdCjaIngresoIdUsuarioJPQL(Long idCjaIngreso, Long idUsuario) {
        String sql = " SELECT DISTINCT p"
                + " FROM CjaGastosEjecutados cge LEFT JOIN cge.personas p  "
                + " LEFT JOIN cge.cjaIngresos cif "
                + " LEFT JOIN cge.cjaTiposGastos ctg "
                + " LEFT JOIN cge.cjaProveedores cp "
                + " LEFT JOIN cge.usuarios u "
                + " WHERE cif.idCjaIngreso = :idCjaIngreso "
                + " AND u.idUsuario = :idUsuario "
                + " AND cge.idEstado = 'A' "
                + " AND u.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }

    @Override
    public List<Personas> listarPersonasComprobantesJPQL() {
        String sql = " SELECT p "
                + " FROM Personas p  "
                + " WHERE p.idEstado != 'X' ORDER BY p.idPersona ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public Personas buscarPersonasComprobantesPorIdPersonaGET(Long idPersona) {
        String sql = " SELECT p "
                + " FROM Personas p "
                + " WHERE p.idPersona = :idPersona "
                + " AND p.idEstado != 'X'";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        try {
            return (Personas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PersonasResponse buscarPersonaRazonSocialPorIdSacRazonSocialResponseGET(Long idSacRazonSocial) {
        String sql = "SELECT new uap.usic.siga.dto.PersonasResponse(srs.idSacRazonSocial, prs.idPersona, prs.nombres, prs.paterno, prs.materno, prs.ci, pci.idPrsCiExpedido, prp.idPais, pts.idPrsTipoSexo,prg.idGradoAcademico) "
                + " FROM SacRazonSocial srs LEFT JOIN srs.personas prs"
                + " LEFT JOIN prs.prsPaises prp LEFT JOIN prs.prsTiposSexos pts "
                + " LEFT JOIN prs.prsCiExpedidos pci LEFT JOIN prs.prsGradosAcademicos prg "
                + " WHERE srs.idSacRazonSocial =:idSacRazonSocial "
                + " AND prs.idEstado != 'X' "
                + " AND pci.idEstado = 'A' "
                + " AND prp.idEstado = 'A' "
                + " AND pts.idEstado = 'A' "
                + " AND srs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacRazonSocial", idSacRazonSocial);
        try {
            return (PersonasResponse) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PersonasResponse buscarPersonaRazonSocialPorIdPersonaResponseGET(Long idPersona) {
         String sql = "SELECT new uap.usic.siga.dto.PersonasResponse(prs.idPersona, prs.nombres, prs.paterno, prs.materno, prs.ci, pci.idPrsCiExpedido, prp.idPais, pts.idPrsTipoSexo, prg.idGradoAcademico) "
                + " FROM Personas prs"
                + " LEFT JOIN prs.prsPaises prp LEFT JOIN prs.prsTiposSexos pts "
                + " LEFT JOIN prs.prsCiExpedidos pci "
                + " LEFT JOIN prs.prsGradosAcademicos prg "
                + " WHERE prs.idPersona =:idPersona "
                + " AND prs.idEstado != 'X' "
                + " AND pci.idEstado = 'A' "
                + " AND prp.idEstado = 'A' "
                + " AND prg.idEstado = 'A' "
                + " AND pts.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        try {
            return (PersonasResponse) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PrsGradosAcademicos> listarGradosAcademicosJPQL() {
        String sql = "SELECT ga "
                + " FROM PrsGradosAcademicos ga "
                + " WHERE ga.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarPrsGradosAcademicosSET(PrsGradosAcademicos prsGradosAcademicos) {
        em.persist(prsGradosAcademicos);
    }

	@Override
	public List<Personas> listarPersonalAdministrativoPorGestionJPQL(Integer gestion) {
		 String sql = " SELECT  prs"
	                + " FROM PnlPersonalAdministrativos pnl LEFT JOIN pnl.personas prs  "
	                + " WHERE pnl.gestion =: gestion  "
	                + " AND pnl.idEstado = 'A'  "
	                + " AND prs.idEstado = 'A'  "
	                + " ORDER BY prs.paterno  ";
	        Query q = em.createQuery(sql);
	        q.setParameter("gestion", gestion);
	         return q.getResultList();
	}

	@Override
	public PrsCiExpedidos getCiExpedido(Long id) {
		String sql = " SELECT exci "
                + " FROM PrsCiExpedidos exci "
                + " WHERE exci.idPrsCiExpedido = :id";
        Query q = em.createQuery(sql);
        q.setParameter("id", id);
        try {
            return (PrsCiExpedidos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public PrsGradosAcademicos getGradoAcademico(Long id) {
		String sql = " SELECT gr "
                + " FROM PrsGradosAcademicos gr "
                + " WHERE gr.idGradoAcademico = :id";
        Query q = em.createQuery(sql);
        q.setParameter("id", id);
        try {
            return (PrsGradosAcademicos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public PrsPaises getPais(Long id) {
		String sql = " SELECT p "
                + " FROM PrsPaises p "
                + " WHERE p.idPais = :id";
        Query q = em.createQuery(sql);
        q.setParameter("id", id);
        try {
            return (PrsPaises) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public PrsTiposSexos getSexo(Long id) {
		String sql = " SELECT sx "
                + " FROM PrsTiposSexos sx "
                + " WHERE sx.idPrsTipoSexo = :id";
        Query q = em.createQuery(sql);
        q.setParameter("id", id);
        try {
            return (PrsTiposSexos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}
}
