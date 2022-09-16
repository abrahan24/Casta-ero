package uap.usic.siga.modelos.impl;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.Menues;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.MnuTiposEnlaces;
import uap.usic.siga.entidades.MnuTiposFunciones;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.entidades.SisNivelesAccesos;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.MenuesDao;

/**
 *
 * @author fmbma
 */
@Repository("menuesDao")
public class MenuesDaoImpl implements MenuesDao {

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
    @Override
    public List<MnuTiposEnlaces> listarMenuesTiposEnlacesJPQL(Long idPnlCargo, Long idPersona) {
        String sql = "SELECT DISTINCT mte FROM Menues mn"
                + " LEFT JOIN mn.mnuEnlaces elc "
                + " LEFT JOIN mn.personas prs "
                + " LEFT JOIN elc.mnuTiposEnlaces mte "
                + " LEFT JOIN mn.pnlCargos crg "
                + " WHERE prs.idPersona =:idPersona "
                + " AND crg.idPnlCargos =:idPnlCargo "
                + " AND prs.idEstado = 'A' "
                + " AND mn.idEstado = 'A' "
                + " ORDER BY elc.mnuEnlace ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idPnlCargo", idPnlCargo);
        return q.getResultList();
    }

    @Override
    public List<MnuEnlaces> listarMenuesEnlacesJPQL(Long idPnlCargo, Long idPersona) {
        String sql = "SELECT elc FROM Menues mn"
                + " LEFT JOIN mn.mnuEnlaces elc "
                + " LEFT JOIN mn.personas prs  "
                + " LEFT JOIN mn.pnlCargos crg "
                + " WHERE prs.idPersona =:idPersona "
                + " AND crg.idPnlCargos =:idPnlCargo "
                + " AND prs.idEstado = 'A' "
                + " AND mn.idEstado = 'A' "
                + " ORDER BY elc.mnuEnlace ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idPnlCargo", idPnlCargo);
        return q.getResultList();
    }

    @Override
    public List<MnuFunciones> listarMenusFuncionesPorIdPersonaIdMnuTipoFuncionJPQL(Long idPersona, Long idMnuTipoFuncion) {
        String sql = "SELECT mn FROM MnuFunciones mn"
                + " LEFT JOIN mn.mnuEnlaces elc "
                + " LEFT JOIN mn.personas prs  "
                + " LEFT JOIN mn.mnuTiposFunciones mtf "
                + " WHERE prs.idPersona =:idPersona "
                + " AND mtf.idMnuTipoFuncion =:idMnuTipoFuncion "
                + " AND prs.idEstado = 'A' "
                + " AND mn.idEstado = 'A' "
                + " ORDER BY elc.mnuEnlace ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idMnuTipoFuncion", idMnuTipoFuncion);
        return q.getResultList();

    }

    @Override
    public List<MnuTiposFunciones> listarMenusTiposFuncionesPorIdPersonaJPQL(Long idPersona) {
        String sql = " SELECT mtf FROM MnuFunciones mf"
                + " LEFT JOIN mf.personas prs  "
                + " LEFT JOIN mf.mnuTiposFunciones mtf "
                + " WHERE prs.idPersona =:idPersona "
                + " AND prs.idEstado = 'A' "
                + " AND mf.idEstado = 'A' "
                + " AND mtf.idEstado = 'A' "
                + " ORDER BY mtf.tipoFuncion ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        return q.getResultList();
    }

    @Override
    public List<MnuEnlaces> listarMenuesEnlacesPorIdMnuTipoFuncionJPQL(Long idMnuTipoFuncion) {
        String sql = "SELECT elc FROM Menues mn"
                + " LEFT JOIN mn.mnuEnlaces elc "
                + " LEFT JOIN mn.mnuTiposFunciones mtf  "
                + " WHERE mtf.idMnuTipoFuncion =:idMnuTipoFuncion "
                + " AND mtf.idEstado = 'A' "
                + " AND mn.idEstado = 'A' "
                + " ORDER BY elc.mnuEnlaceOrden ";
        Query q = em.createQuery(sql);
        q.setParameter("idMnuTipoFuncion", idMnuTipoFuncion);
        return q.getResultList();
    }

    @Override
    public List<MnuTiposEnlaces> listarMenuesTiposEnlacesPorIdMnuTipoFuncionJPQL(Long idMnuTipoFuncion) {
        String sql = "SELECT DISTINCT mte FROM Menues mnu LEFT JOIN mnu.mnuTiposFunciones mtf "
                + " LEFT JOIN mnu.mnuEnlaces mel "
                + " LEFT JOIN mel.mnuTiposEnlaces mte "
                + " WHERE mtf.idMnuTipoFuncion =:idMnuTipoFuncion "
                + " AND mnu.idEstado = 'A' "
                + " AND mel.idEstado = 'A' "
                + " ORDER BY mte.mnuTipoEnlaceOrden ";
        Query q = em.createQuery(sql);
        q.setParameter("idMnuTipoFuncion", idMnuTipoFuncion);
        return q.getResultList();
    }

    @Override
    public MnuFunciones buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncionGET(Long idPersona, Long idMnuTipoFuncion, Long idSisAdministrador) {
        String sql = "SELECT mn FROM MnuFunciones mn"
                + " LEFT JOIN mn.personas prs  "
                + " LEFT JOIN mn.sisAdministrador sa  "
                + " LEFT JOIN mn.mnuTiposFunciones mtf "
                + " WHERE prs.idPersona =:idPersona "
                + " AND mtf.idMnuTipoFuncion =:idMnuTipoFuncion "
                + " AND sa.idSisAdministrador =:idSisAdministrador "
                + " AND prs.idEstado = 'A' "
                + " AND sa.idEstado = 'A' "
                + " AND mn.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idMnuTipoFuncion", idMnuTipoFuncion);
        q.setParameter("idSisAdministrador", idSisAdministrador);

        try {
            return (MnuFunciones) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Menues> listarMenuesJPQL() {
        String sql = " SELECT m FROM Menues m"
                + " LEFT JOIN m.mnuTiposFunciones mtf "
                + " LEFT JOIN m.mnuEnlaces e"
                + " WHERE m.idEstado = 'A' "
                + " AND e.idEstado = 'A' "
                + " AND mtf.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        return q.getResultList();

    }

    @Override
    public List<MnuTiposFunciones> listarMnuTiposFuncionesJPQL() {
        String sql = " SELECT mtf FROM MnuTiposFunciones mtf"
                + " WHERE mtf.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarMenuesSET(Menues menues) {
        em.persist(menues);
    }

    @Override
    public void registrarMnuTiposFuncionesSET(MnuTiposFunciones mnuTiposFunciones) {
        em.persist(mnuTiposFunciones);
    }

    @Override
    public void modificarMenuesSET(Menues menues) {
        em.merge(menues);
    }

    @Override
    public void EliminarMenuesSET(Menues menues) {
        em.merge(menues);
    }

    @Override
    public Menues buscarMenuesPorIdMenuGET(Long idMenu) {
        String sql = "SELECT m "
                + " FROM Menues m LEFT JOIN m.mnuTiposFunciones mtf "
                + " LEFT JOIN m.mnuEnlaces e "
                + " WHERE  m.idMenu = :idMenu "
                + " AND m.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("idMenu", idMenu);

        try {
            return (Menues) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<MnuFunciones> listarMnuFuncionesJPQL() {
        String sql = " SELECT mf FROM MnuFunciones mf"
                + " LEFT JOIN mf.personas p "
                + " LEFT JOIN mf.mnuTiposFunciones mtf "
                + " LEFT JOIN mf.sisAdministrador sa "
                + " LEFT JOIN mf.sisNivelesAccesos sn "
                + " WHERE mf.idEstado = 'A' "
                + " AND mtf.idEstado = 'A' "
                + " AND p.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SisAdministrador> listarSisAdministradorJPQL() {
        String sql = " SELECT sa FROM SisAdministrador sa"
                + " WHERE sa.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SisNivelesAccesos> listarSisNivelesAccesosJPQL() {
        String sql = " SELECT sna FROM SisNivelesAccesos sna"
                + " WHERE sna.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarMnuFuncionesSET(MnuFunciones mnuFunciones) {
        em.persist(mnuFunciones);
    }

    @Override
    public void registrarSisAdministradorSET(SisAdministrador sisAdministrador) {
        em.persist(sisAdministrador);
    }

    @Override
    public void modificarMnuFuncionesSET(MnuFunciones mnuFunciones) {
        em.merge(mnuFunciones);
    }

    @Override
    public void eliminaMnuFuncionesSET(MnuFunciones mnuFunciones) {
        em.merge(mnuFunciones);
    }

    @Override
    public MnuFunciones buscarMnuFuncionesPorIdMnuFuncionGET(Long idMnuFuncion) {
        String sql = " SELECT mf FROM MnuFunciones mf"
                + " LEFT JOIN mf.personas p "
                + " LEFT JOIN mf.mnuTiposFunciones mtf "
                + " LEFT JOIN mf.sisAdministrador sa "
                + " LEFT JOIN mf.sisNivelesAccesos sn "
                + " WHERE mf.idMnuFuncion = :idMnuFuncion"
                + " AND mf.idEstado = 'A' "
                + " AND mtf.idEstado = 'A' "
                + " AND p.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idMnuFuncion", idMnuFuncion);

        try {
            return (MnuFunciones) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void registrarSisNivelesAccesosSET(SisNivelesAccesos sisNivelesAccesos) {
        em.persist(sisNivelesAccesos);
    }

	@Override
	public MnuTiposFunciones getTipoFuncion(Long id) {
		String sql = "SELECT tf FROM MnuTiposFunciones tf"
                + " WHERE tf.idMnuTipoFuncion =:idMnuTipoFuncion "
                + " AND tf.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idMnuTipoFuncion", id);
        
        try {
            return (MnuTiposFunciones) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public SisAdministrador getSisAdmin(Long id) {
		String sql = "SELECT tf FROM SisAdministrador tf"
                + " WHERE tf.idSisAdministrador =:idSisAdministrador "
                + " AND tf.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSisAdministrador", id);
        
        try {
            return (SisAdministrador) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public SisNivelesAccesos getNivelAcceso(Long id) {
		
		System.out.println("id: " + id);
		
		String sql = "SELECT nl "
				+ " FROM SisNivelesAccesos nl"
                + " WHERE nl.idNivelAcceso = " + id;
        Query q = em.createQuery(sql);
        
        try {
            return (SisNivelesAccesos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}
}
