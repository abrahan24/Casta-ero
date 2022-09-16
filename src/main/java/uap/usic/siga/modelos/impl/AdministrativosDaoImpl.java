package uap.usic.siga.modelos.impl;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.PnlItems;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PnlTiposAdministrativos;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.AdministrativosDao;

/**
 * Rectorado - USIC Fecha: 2019-04-16
 *
 * @author Freddy Morales
 */
@Repository("administrativosDao")
public class AdministrativosDaoImpl implements AdministrativosDao {

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
    @Override
    public List<PnlCargos> listarPnlCargosGET() {
        String sql = "SELECT c FROM PnlCargos c "
                + " WHERE c.idEstado = 'A' "
                + " Order by c.cargo";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<PnlItems> listarPnlItemsGET() {
        String sql = "SELECT i FROM PnlItems i "
                + " WHERE i.idEstado = 'A' "
                + " Order by i.item";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<PnlTiposAdministrativos> listarPnlTiposAdministrativosGET() {
        String sql = "SELECT t FROM PnlTiposAdministrativos t "
                + " WHERE t.idEstado = 'A' "
                + " Order by t.tipoAdministrativo";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void guardarPersonalAdministrativoSET(PnlPersonalAdministrativos pnlPersonalAdministrativo) {
        em.persist(pnlPersonalAdministrativo);
    }

    @Override
    public PnlCargos buscarCargosPorIdPersonaGET(Long idPersona) {
        String sql = "SELECT crg "
                + " FROM PnlPersonalAdministrativos adm LEFT JOIN adm.personas p "
                + " LEFT JOIN adm.pnlCargos crg"
                + " WHERE  p.idPersona = :idPersona "
                + " AND p.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);

        try {
            return (PnlCargos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<PnlPersonalAdministrativos> listarPersonaslAdministrativosJPQL() {
        String sql = " SELECT t "
                + " FROM PnlPersonalAdministrativos t LEFT JOIN t.personas p "
                + " LEFT JOIN t.pnlItems i "
                + " LEFT JOIN t.pnlCargos pc "
                + " LEFT JOIN t.pnlTiposAdministrativos pta"
                + " LEFT JOIN t.insUnidadesFuncionales uf "
                + " LEFT JOIN uf.insDireccionesFuncionales df"
                + " LEFT JOIN df.insSedes se"
                + " WHERE t.idEstado = 'A' "
                + " AND p.idEstado = 'A' "
                + " Order by p.paterno";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdPnlPersonalAdministrativoGET(Long idPnlPersonalAdministrativo) {
        String sql = "SELECT pa "
                + " FROM PnlPersonalAdministrativos pa "
                + " WHERE  pa.idPnlPersonalAdministrativo = :idPnlPersonalAdministrativo "
                + " AND pa.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("idPnlPersonalAdministrativo", idPnlPersonalAdministrativo);

        try {
            return (PnlPersonalAdministrativos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void modificarDatosPersonalAdministrativosSET(PnlPersonalAdministrativos pnlPersonalAdministrativo) {
        em.merge(pnlPersonalAdministrativo);
    }

    @Override
    public void eliminarRegistroPersonalAdministrativosSET(PnlPersonalAdministrativos pnlPersonalAdministrativo) {
        em.merge(pnlPersonalAdministrativo);
    }

    @Override
    public void actualizarPnlCargosSET(PnlCargos pnlCargos) {
        em.merge(pnlCargos);
    }

    @Override
    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodoGET(Long idPersona, Integer gestion, Integer periodo) {
        String sql = "SELECT pa "
                + " FROM PnlPersonalAdministrativos pa  LEFT JOIN pa.personas p "
                + " LEFT JOIN pa.pnlCargos pc "
                + " WHERE  p.idPersona = :idPersona "
                + " AND pa.gestion = :gestion "
                + " AND pa.periodo = :periodo "
                + " AND pa.idEstado = 'A' "
                + " AND p.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("gestion", gestion);
        q.setParameter("periodo", periodo);

        try {
            return (PnlPersonalAdministrativos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void guardarPnlCargosSET(PnlCargos pnlCargos) {
        em.persist(pnlCargos);
    }

    @Override
    public void guardarPnlTiposAdministrativosSET(PnlTiposAdministrativos pnlTiposAdministrativos) {
        em.persist(pnlTiposAdministrativos);
    }

    @Override
    public void guardarPnlItemsSET(PnlItems pnlItems) {
        em.persist(pnlItems);
    }

	@Override
	public PnlTiposAdministrativos getTipoAdministrativo(String tipo) {
		String sql = "SELECT pa "
                + " FROM PnlTiposAdministrativos pa "
                + " WHERE  pa.tipoAdministrativo = :tipoAdministrativo "
                + " AND pa.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("tipoAdministrativo", tipo);
        
        try {
            return (PnlTiposAdministrativos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public PnlItems getItem(String item) {
		String sql = "SELECT it "
                + " FROM PnlItems it "
                + " WHERE  it.item = :item "
                + " AND it.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("item", item);
        
        try {
            return (PnlItems) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

}
