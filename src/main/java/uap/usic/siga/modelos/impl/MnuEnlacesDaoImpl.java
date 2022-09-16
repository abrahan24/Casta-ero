package uap.usic.siga.modelos.impl;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuTiposEnlaces;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.MnuEnlacesDao;

/**
 *
 * @author Yessenia
 */
@Repository("mnuEnlacesDao")
public class MnuEnlacesDaoImpl implements MnuEnlacesDao {

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})

    @Override
    public List<MnuTiposEnlaces> listarMnuTiposEnlacesJPQL() {
        String sql = "SELECT te "
                + "FROM MnuTiposEnlaces te "
                + " WHERE te.idEstado = 'A' ORDER BY te.mnuTipoEnlace";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<MnuEnlaces> listarMnuEnlacesJPQL() {
        String sql = "SELECT e "
                + "FROM MnuEnlaces e LEFT JOIN e.mnuTiposEnlaces te "
                + " WHERE e.idEstado = 'A' ORDER BY e.mnuEnlace";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarMnuTiposEnlacesSET(MnuTiposEnlaces mnuTiposEnlaces) {
        em.persist(mnuTiposEnlaces);
    }

    @Override
    public void registrarMnuEnlacesSET(MnuEnlaces mnuEnlaces) {
        em.persist(mnuEnlaces);
    }

    @Override
    public void modificarMnuEnlacesSET(MnuEnlaces mnuEnlaces) {
        em.merge(mnuEnlaces);
    }

    @Override
    public void EliminarMnuEnlacesSET(MnuEnlaces mnuEnlaces) {
        em.merge(mnuEnlaces);
    }

    @Override
    public MnuEnlaces buscarMnuEnlacesPorIdMnuEnlaceGET(Long idMnuEnlace) {
        String sql = "SELECT e "
                + " FROM MnuEnlaces e LEFT JOIN e.mnuTiposEnlaces te"
                + " WHERE  e.idMnuEnlace = :idMnuEnlace "
                + " AND e.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("idMnuEnlace", idMnuEnlace);
       
        try {
            return (MnuEnlaces) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}


