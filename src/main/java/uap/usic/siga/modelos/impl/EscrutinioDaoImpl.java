package uap.usic.siga.modelos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.dto.EscActasResponse;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.EscDetalles;
import uap.usic.siga.entidades.EscElecciones;
import uap.usic.siga.entidades.EscFrentes;
import uap.usic.siga.entidades.EscMesasHabilitadas;
import uap.usic.siga.entidades.EscrutinioActas;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.EscrutinioDao;

/**
 * Rectorado - USIC Fecha: 2019-03-25
 *
 * @author Freddy Morales
 */
@Repository("escrutinioDao")
public class EscrutinioDaoImpl implements EscrutinioDao {

	@PersistenceContext
    private EntityManager em;

   @Transactional(rollbackFor = {ServicioException.class})
	@Override
	public List<EscElecciones> listarEleccionesPorGestionPeriodoJPQL(Integer gestion, Integer periodo) {
		 String sql = "SELECT ee FROM EscElecciones ee "
	                + " WHERE ee.idEstado = 'A' "
	                + " AND ee.gestion =:gestion"
	                + " AND ee.periodo =:periodo"
	                + " ORDER BY ee.gestion";
		    Query q = em.createQuery(sql);
		    q.setParameter("gestion", gestion);
		    q.setParameter("periodo", periodo);
	        return q.getResultList();
	}

@Override
public List<EscMesasHabilitadas> listarMesasHabilitadasPorIdEleccionIdFacultadIdEstamentoJPQL(Long idEleccion,	Long idFacultad, Long idEstamento) {
	 String sql = "SELECT emh"
             + " FROM EscMesasHabilitadas emh LEFT JOIN emh.facultades fcl "
             + " LEFT JOIN emh.escElecciones ece "
             + " LEFT JOIN emh.fclEstamentos est"
             + " WHERE fcl.idFacultad =:idFacultad"
             + " AND ece.idEleccion =:idEleccion"
             + " AND est.idEstamento =:idEstamento"
             + " AND emh.idEstado = 'A' "
             + " ORDER BY emh.mesa ";
     Query q = em.createQuery(sql);
     q.setParameter("idFacultad", idFacultad);
     q.setParameter("idEleccion", idEleccion);
     q.setParameter("idEstamento", idEstamento);
     return q.getResultList();
}

@Override
public List<EscFrentes> listarFrentesPorIdFacultadIdEleccionJPQL(Long idFacultad, Long idEstamento, Long idEleccion) {
	 String sql = "SELECT frn"
             + " FROM EscFrentes frn LEFT JOIN frn.facultades fcl "
             + " LEFT JOIN frn.escElecciones ece "
             + " LEFT JOIN frn.fclEstamentos est"
              + " WHERE fcl.idFacultad =:idFacultad"
             + " AND ece.idEleccion =:idEleccion"
             + " AND est.idEstamento =:idEstamento"
             + " AND frn.idEstado = 'A' "
             + " ORDER BY frn.sigla ";
     Query q = em.createQuery(sql);
     q.setParameter("idFacultad", idFacultad);
     q.setParameter("idEleccion", idEleccion);
     q.setParameter("idEstamento", idEstamento);
     return q.getResultList();
}

@Override
public EscrutinioActas registrarEscrutinioActasSET(EscrutinioActas escrutinioActas) {
	em.persist(escrutinioActas);
	return escrutinioActas;
}

@Override
public EscDetalles registrarEscrutinioDetallesSET(EscDetalles escDetalles) {
	em.persist(escDetalles);
	return escDetalles;
}

@Override
public EscFrentes buscarFrentePorIdFrenteGET(Long idFrente) {
	 String sql = "SELECT esf "
             + " FROM EscFrentes esf "
             + " WHERE  esf.idFrente  =:idFrente "
             + " AND esf.idEstado =  'A' ";
     Query q = em.createQuery(sql);
     q.setParameter("idFrente", idFrente);

     try {
         return (EscFrentes) q.getSingleResult();
     } catch (Exception e) {
         return null;
     }
}

@Override
public EscMesasHabilitadas registrarEscrutinioMesasHabilitadasSET(EscMesasHabilitadas escMesasHabilitadas) {
	em.persist(escMesasHabilitadas);
	return escMesasHabilitadas;
}

@Override
public EscFrentes registrarFrentesEleccionSET(EscFrentes escFrentes) {
	em.persist(escFrentes);
	return escFrentes;
}

@Override
public List<EscMesasHabilitadas> listarMesasSufragioHabilitadasJPQL(Integer gestion, Integer periodo) {
	 String sql = "SELECT emh"
             + " FROM EscMesasHabilitadas emh"
              + " WHERE emh.idEstado = 'A' "
              + " AND emh.gestion =:gestion "
              + " AND emh.periodo =:periodo "
              + " ORDER BY emh.gestion ";
     Query q = em.createQuery(sql);
     q.setParameter("gestion",gestion);
     q.setParameter("periodo", periodo);
     return q.getResultList();
}

@Override
public List<EscrutinioActas> listarEscrutinioAcatasPorGestionPeriodoJPQL(Integer gestion, Integer periodo) {
	 String sql = "SELECT esa"
             + " FROM EscrutinioActas esa"
              + " WHERE esa.idEstado = 'A' "
              + " AND esa.gestion =:gestion "
              + " AND esa.periodo =:periodo "
              + " ORDER BY esa.gestion ";
     Query q = em.createQuery(sql);
     q.setParameter("gestion",gestion);
     q.setParameter("periodo", periodo);
     return q.getResultList();
   }

@Override
public List<EscActasResponse> listarEscrutinioActasResponseGestionPeriodoJPQL(Integer gestion, Integer periodo) {
	String sql = "SELECT new uap.usic.siga.dto.EscActasResponse(fcl.facultad, fcl.idFacultad, ele.idEleccion, ele.eleccion, esa.gestion, est.idEstamento, est.estamento) "
            + " FROM EscrutinioActas esa "
            + " LEFT JOIN esa.escMesasHabilitadas emh "
            + " LEFT JOIN emh.facultades fcl "
            + " LEFT JOIN emh.escElecciones ele"
            + " LEFT JOIN emh.fclEstamentos est "
            + " WHERE esa.gestion =:gestion "
            + " AND esa.periodo =:periodo  "
            + " AND esa.idEstado = 'A' "
            + " GROUP BY est.idEstamento, fcl.idFacultad ";
    Query q = em.createQuery(sql);
    q.setParameter("gestion", gestion);
    q.setParameter("periodo", periodo);
    return q.getResultList();
}

@Override
public List<EscActasResponse> listarSumatoriaActasPorIdFacultadIdEstamentoIdEleccionGestionJPQL(Long idFacultad,
		Long idEleccion, Long idEstamento, Integer gestion) {
	String sql = "SELECT new uap.usic.siga.dto.EscActasResponse(fcl.facultad, fcl.idFacultad,  esa.gestion, esf.idFrente, esf.frente, SUM(esa.votosEmitidos), SUM(esa.votosValidos), SUM(esa.votosNulos), SUM(esa.votosBlancos), SUM(esd.votoFrente)) "
            + " FROM EscDetalles esd LEFT JOIN esd.escrutinioActas esa "
            + " LEFT JOIN esa.escMesasHabilitadas emh "
            + " LEFT JOIN emh.facultades fcl "
            + " LEFT JOIN emh.escElecciones ele"
            + " LEFT JOIN emh.fclEstamentos est"
            + " LEFT JOIN esd.escFrentes esf  "
            + " WHERE esa.gestion =:gestion "
            + " AND fcl.idFacultad =:idFacultad "
            + " AND ele.idEleccion =:idEleccion "
            + " AND est.idEstamento =:idEstamento "
            + " AND esa.idEstado = 'A' "
            + " GROUP BY esf.idFrente  ";
    Query q = em.createQuery(sql);
    q.setParameter("gestion", gestion);
    q.setParameter("idFacultad", idFacultad);
    q.setParameter("idEleccion", idEleccion);
    q.setParameter("idEstamento", idEstamento);
    return q.getResultList();
}

@Override
public List<EscActasResponse> listarSumatoriaActasPorMesasIdFacultadIdEstamentoIdEleccionGestionJPQL(Long idFacultad,
		Long idEleccion, Long idEstamento, Integer gestion) {
		String sql = "SELECT new uap.usic.siga.dto.EscActasResponse(fcl.facultad, fcl.idFacultad, esf.idFrente, esf.frente, SUM(esa.votosEmitidos), SUM(esa.votosValidos), SUM(esa.votosNulos), SUM(esa.votosBlancos), SUM(esd.votoFrente), emh.idMesaHabilitada, emh.mesa) "
            + " FROM EscDetalles esd LEFT JOIN esd.escrutinioActas esa  "
            + " LEFT JOIN esa.escMesasHabilitadas emh "
            + " LEFT JOIN emh.facultades fcl "
            + " LEFT JOIN emh.escElecciones ele"
            + " LEFT JOIN emh.fclEstamentos est"
            + " LEFT JOIN esd.escFrentes esf  "
            + " WHERE esa.gestion =:gestion "
            + " AND fcl.idFacultad =:idFacultad "
            + " AND ele.idEleccion =:idEleccion "
            + " AND est.idEstamento =:idEstamento "
            + " AND esa.idEstado = 'A' "
            + " GROUP BY esf.idFrente, emh.idMesaHabilitada  ";
    Query q = em.createQuery(sql);
    q.setParameter("gestion", gestion);
    q.setParameter("idFacultad", idFacultad);
    q.setParameter("idEleccion", idEleccion);
    q.setParameter("idEstamento", idEstamento);
    return q.getResultList();
	
}

@Override
public List<EscFrentes> listarEscFrentesPorGestionPeriodoJPQL(Integer gestion, Integer periodo) {
	 String sql = "SELECT esf FROM EscFrentes esf "
             + " WHERE esf.idEstado = 'A' "
             + " AND esf.gestion =:gestion"
             + " AND esf.periodo =:periodo"
             + " ORDER BY esf.gestion";
	    Query q = em.createQuery(sql);
	    q.setParameter("gestion", gestion);
	    q.setParameter("periodo", periodo);
     return q.getResultList();
}

@Override
public EscElecciones buscarEleccionPorIdEleccionGET(Long idEleccion) {
	 String sql = "SELECT ese "
             + " FROM EscElecciones ese "
             + " WHERE  ese.idEleccion  =:idEleccion "
             + " AND ese.idEstado =  'A' ";
     Query q = em.createQuery(sql);
     q.setParameter("idEleccion", idEleccion);

     try {
         return (EscElecciones) q.getSingleResult();
     } catch (Exception e) {
         return null;
     }
}



   


}
