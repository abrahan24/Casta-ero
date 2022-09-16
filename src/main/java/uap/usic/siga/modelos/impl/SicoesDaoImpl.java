package uap.usic.siga.modelos.impl;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.SacCompArchivosAdjuntos;
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.entidades.ScsBoletasRespaldatorias;
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.ScsFormularios;
import uap.usic.siga.entidades.ScsModalidades;
import uap.usic.siga.entidades.ScsProyectos;
import uap.usic.siga.entidades.ScsPrsContratados;
import uap.usic.siga.entidades.ScsTiposContratos;
import uap.usic.siga.entidades.ScsTiposModalidades;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.SicoesDao;
 
/**
 * Rectorado - USIC
 * Fecha: 2019-12-27
 * @author Freddy Morales
 */
@Repository("sicoesDao")
public class SicoesDaoImpl implements SicoesDao {
    
    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
    @Override
    public List<ScsBoletasRespaldatorias> listarScsBoletasRespaldatoriasJPQL() {
          String sql = "SELECT sbr "
                + " FROM ScsBoletasRespaldatorias sbr "
                + " WHERE sbr.idEstado = 'A' "
                + " ORDER BY sbr.scsBoletaRespaldatoria";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<ScsFormularios> listarScsFormulariosJPQL() {
         String sql = "SELECT sfr "
                + " FROM ScsFormularios sfr "
                + " WHERE sfr.idEstado = 'A' "
                + " ORDER BY sfr.scsFormulario";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<ScsProyectos> listarScsProyectosJPQL() {
         String sql = "SELECT scp "
                + " FROM ScsProyectos scp "
                + " WHERE scp.idEstado = 'A' "
                + " ORDER BY scp.scsProyecto";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<ScsModalidades> listarScsModalidadesJPQL() {
        String sql = "SELECT scm "
                + " FROM ScsModalidades scm "
                + " WHERE scm.idEstado = 'A' "
                + " ORDER BY scm.scsModalidad ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<ScsTiposModalidades> listarScsTiposModalidadesJPQL() {
         String sql = "SELECT stm "
                + " FROM ScsTiposModalidades stm "
                + " WHERE stm.idEstado = 'A' "
                + " ORDER BY stm.scsTipoModalidad ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<ScsTiposContratos> listarScsTiposContratosJPQL() {
         String sql = "SELECT stc "
                + " FROM ScsTiposContratos stc "
                + " WHERE stc.idEstado = 'A' "
                + " ORDER BY stc.scsTipoContrato ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public ScsArchivosAdjuntos registrarScsArchivosAdjuntosSET(ScsArchivosAdjuntos scsArchivosAdjuntos) {
        em.persist(scsArchivosAdjuntos);
        return scsArchivosAdjuntos;
    }

    @Override
    public ScsContrataciones registrarScsContratacionesSET(ScsContrataciones scsContrataciones) {
        em.persist(scsContrataciones);
        return scsContrataciones;
    }

    @Override
    public List<ScsContrataciones> listarScsContratacionesJPQL() {
        String sql = "SELECT scs "
                + " FROM ScsContrataciones scs "
                + " WHERE scs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public ScsArchivosAdjuntos buscarScsArchivosAdjuntosPorIdScsArchivoAdjuntoGET(Long idScsArchivoAdjunto) {
        String sql = " SELECT saa "
                + " FROM ScsArchivosAdjuntos saa "
                + " WHERE saa.idScsArchivoAdjunto =:idScsArchivoAdjunto "
                + " AND saa.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idScsArchivoAdjunto", idScsArchivoAdjunto);
        try {
            return (ScsArchivosAdjuntos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ScsContrataciones buscarScsContratacionesPorIdScsContratacionGET(Long idScsContratacion) {
        String sql = " SELECT scs "
                + " FROM ScsContrataciones scs "
                + " WHERE scs.idScsContratacion =:idScsContratacion "
                + " AND scs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idScsContratacion", idScsContratacion);
        try {
            return (ScsContrataciones) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void modificarScsContratacionesSET(ScsContrataciones scsContrataciones) {
        em.merge(scsContrataciones);
    }

    @Override
    public void modificarScsArchivosAdjuntosSET(ScsArchivosAdjuntos scsArchivosAdjuntos) {
        em.merge(scsArchivosAdjuntos);
    }

    @Override
    public Long extraerMaximoIdScsContratacionGET() {
         String sql = " SELECT MAX(scs.idScsContratacion) "
                + " FROM ScsContrataciones scs "
                + " WHERE scs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        try {
            return (Long) q.getSingleResult()+1;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ScsProyectos registrarScsProyectosSET(ScsProyectos scsProyectos) {
        em.persist(scsProyectos);
        return scsProyectos;
    }

    @Override
    public void actualizarScsProyectosSET(ScsProyectos scsProyectos) {
        em.merge(scsProyectos);
    }

    @Override
    public ScsProyectos buscarScsProyectosPorIdScsProyectoGET(Long idScsProyecto) {
         String sql = " SELECT scp "
                + " FROM ScsProyectos scp "
                + " WHERE scp.idScsProyecto =:idScsProyecto "
                + " AND scp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idScsProyecto", idScsProyecto);
        try {
            return (ScsProyectos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ScsContrataciones> listarContratacionesPorGestionIdScsModalidadJQPL(Long idScsModalidad, Integer gestion) {
        String sql = "SELECT scs "
                + " FROM ScsContrataciones scs LEFT JOIN scs.scsModalidades scm"
                + " WHERE scs.gestion =:gestion "
                + " AND scm.idScsModalidad =:idScsModalidad"
                + " AND scs.idEstado = 'A' "
                + " AND scm.idEstado = 'A' ";
        //     + " ORDER BY sac.sacNumeroComprobante ";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        q.setParameter("idScsModalidad", idScsModalidad);
        return q.getResultList();
    }

    @Override
    public ScsModalidades buscarScsModalidadesPorIdScsModalidadGET(Long idScsModalidad) {
        String sql = " SELECT scm "
                + " FROM ScsModalidades scm "
                + " WHERE scm.idScsModalidad =:idScsModalidad "
                + " AND scm.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idScsModalidad", idScsModalidad);
        try {
            return (ScsModalidades) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ScsPrsContratados registrarScsPrsContratadosSET(ScsPrsContratados scsPrsContratados) {
        em.persist(scsPrsContratados);
        return scsPrsContratados;
    }

    @Override
    public List<ScsPrsContratados> listarScsPrsContratadosesJQPL() {
       String sql = "SELECT spr "
                + " FROM ScsPrsContratados spr "
                + " WHERE spr.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public ScsPrsContratados buscarScsPrsContratadosPorIdScsPrsContratadosGET(Long idScsPrsContratado) {
        String sql = " SELECT spr "
                + " FROM ScsPrsContratados spr "
                + " WHERE spr.idScsPrsContratado =:idScsPrsContratado "
                + " AND spr.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idScsPrsContratado", idScsPrsContratado);
        try {
            return (ScsPrsContratados) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void actualizarScsPrsContratadosSET(ScsPrsContratados scsPrsContratados) {
        em.merge(scsPrsContratados);
    }
    
}
