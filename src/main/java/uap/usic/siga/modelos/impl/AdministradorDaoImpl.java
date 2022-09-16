package uap.usic.siga.modelos.impl;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.AdministradorDao;

/**
 *
 * @author Usuario
 */
@Repository("administradorDao")
public class AdministradorDaoImpl implements AdministradorDao {

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})

    @Override
    public SisAdministrador buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(Long idMnuTipoFuncion, Long idPersona) {
        String sql = "SELECT  sa "
                + " FROM MnuFunciones mf LEFT JOIN mf.sisAdministrador sa "
                + " LEFT JOIN mf.personas pr LEFT JOIN mf.mnuTiposFunciones mtf"
                + " WHERE  mtf.idMnuTipoFuncion =:idMnuTipoFuncion "
                + " AND pr.idPersona =:idPersona "
                + " AND sa.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("idMnuTipoFuncion", idMnuTipoFuncion);
        q.setParameter("idPersona", idPersona);
        try {
            return (SisAdministrador) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
