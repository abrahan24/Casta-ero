package uap.usic.siga.modelos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.entidades.CjaTiposGastos;
import uap.usic.siga.entidades.CngCongresistas;
import uap.usic.siga.entidades.CngTiposCongresistas;
import uap.usic.siga.entidades.CongresoUap;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.CongresoDao;

/**
 *
 * @author Freddy Morales
 */
@Repository("congresoDao")
public class CongresoDaoImpl implements CongresoDao{
	
    @PersistenceContext
    private EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
	@Override
	public List<CngTiposCongresistas> listarTiposCongresistasJPQL() {
		 String sql = "Select ctc from CngTiposCongresistas ctc "
                + " Where ctc.idEstado = 'A' Order by ctc.tipoCongresista";
        Query q = em.createQuery(sql);
        return q.getResultList();
	}

	@Override
	public List<CngCongresistas> listarCongresistasPorGestionJPQL(Integer gestion) {
		   String sql = "SELECT ccg  FROM CngCongresistas ccg "
	                + " WHERE ccg.gestion =:gestion "
	                + " AND ccg.idEstado = 'A' Order by ccg.gestion ";
	        Query q = em.createQuery(sql);
	        q.setParameter("gestion", gestion);
	        return q.getResultList();
	}

	@Override
	public List<CongresoUap> listarCongresosGeneralJPQL() {
		 String sql = "Select cgu from CongresoUap cgu "
	                + " Where cgu.idEstado = 'A' Order by cgu.congreso";
	        Query q = em.createQuery(sql);
	        return q.getResultList();
	}

	@Override
	public CngCongresistas registrarCongresistasSET(CngCongresistas cngCongresistas) {
		em.persist(cngCongresistas);
		return cngCongresistas ;
	}

	@Override
	public CngCongresistas buscarCngCongresistasPorIdCngCongresistasGET(Long idCngCongresista) {
		 String sql = "SELECT cng  FROM CngCongresistas cng   "
	                + " WHERE cng.idCngCongresista =:idCngCongresista  "
	                + " AND cng.idEstado = 'A' ";
	        Query q = em.createQuery(sql);
	        q.setParameter("idCngCongresista", idCngCongresista);

	        try {
	            return (CngCongresistas) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public CngCongresistas modificarCngCongresistasSET(CngCongresistas cngCongresistas) {
		em.merge(cngCongresistas);
		return cngCongresistas; 
	}

}
