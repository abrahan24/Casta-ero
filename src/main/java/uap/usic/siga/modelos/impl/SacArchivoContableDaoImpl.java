package uap.usic.siga.modelos.impl;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.dto.SacComprobantesResponse;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.SacCarpetas;
import uap.usic.siga.entidades.SacCompArchivosAdjuntos;
import uap.usic.siga.entidades.SacCompNroCheque;
import uap.usic.siga.entidades.SacCompNroComprobante;
import uap.usic.siga.entidades.SacComprobantes;
import uap.usic.siga.entidades.SacDevolucionesComprobantes;
import uap.usic.siga.entidades.SacDevolucionesComprobantesDetalles;
import uap.usic.siga.entidades.SacEstadosComprobantes;
import uap.usic.siga.entidades.SacEstantes;
import uap.usic.siga.entidades.SacPrestamosComprobantes;
import uap.usic.siga.entidades.SacPrestamosComprobantesDetalles;
import uap.usic.siga.entidades.SacRazonSocial;
import uap.usic.siga.entidades.SacTiposCarpetas;
import uap.usic.siga.entidades.SacTiposComprobantes;
import uap.usic.siga.entidades.SacTiposPagos;
import uap.usic.siga.entidades.SisArchivosAdjuntos;
import uap.usic.siga.entidades.SisMeses;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.SacArchivoContableDao;

/**
 *
 * @author Yessenia Velasco
 */
@Repository("archivoContableDao")
public class SacArchivoContableDaoImpl implements SacArchivoContableDao {

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})

    @Override
    public List<SacTiposCarpetas> listarTposCarpetasJPQL() {
        String sql = "SELECT tc "
                + " FROM SacTiposCarpetas tc "
                + " WHERE tc.idEstado = 'A' Order by tc.sacTipoCarpeta";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarCarpetasSET(SacCarpetas sacCarpetas) {
        em.persist(sacCarpetas);
    }

    @Override
    public List<SacEstantes> listarEstantesJPQL() {
        String sql = "SELECT e "
                + " FROM SacEstantes e "
                + " WHERE e.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SisMeses> ListarMesesJPQL() {
        String sql = "SELECT m "
                + " FROM SisMeses m "
                + " WHERE m.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SacCarpetas> listarCarpetasJPQL() {
        String sql = "SELECT c "
                + " FROM SacCarpetas c LEFT JOIN c.sacTiposCarpetas tc "
                + " LEFT JOIN c.sacEstantes e "
                + " LEFT JOIN c.sisMeses m "
                + " WHERE c.idEstado = 'A' "
                + " AND e.idEstado = 'A' "
                + " AND tc.idEstado = 'A' "
                + " AND m.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public SacCarpetas buscarCarpetasPorIdSacCarpetaGET(Long idSacCarpeta) {
        String sql = " SELECT c "
                + " FROM SacCarpetas c "
                + " WHERE c.idSacCarpeta = :idSacCarpeta "
                + " AND c.idEstado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCarpeta", idSacCarpeta);
        try {
            return (SacCarpetas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void modificarSacCarpetasSET(SacCarpetas sacCarpetas) {
        em.merge(sacCarpetas);
    }

    @Override
    public void eliminarSacCarpetasSET(SacCarpetas sacCarpetas) {
        em.merge(sacCarpetas);
    }

    @Override
    public void registrarEstantesSET(SacEstantes sacEstantes) {
        em.persist(sacEstantes);
    }

    @Override
    public void modificarEstantesSET(SacEstantes sacEstantes) {
        em.merge(sacEstantes);
    }

    @Override
    public void eliminarEstantesSET(SacEstantes sacEstantes) {
        em.merge(sacEstantes);
    }

    @Override
    public void registrarSacTiposCarpetasSET(SacTiposCarpetas sacTiposCarpetas) {
        em.persist(sacTiposCarpetas);
    }

    @Override
    public SacEstantes buscarSacEstantesPorIdSacEstanteGET(Long idSacEstante) {
        String sql = " SELECT e "
                + " FROM SacEstantes e "
                + " WHERE e.idSacEstante = :idSacEstante "
                + " AND e.idEstado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("idSacEstante", idSacEstante);
        try {
            return (SacEstantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SacTiposPagos> listarTiposPagosJQPL() {
        String sql = "SELECT stp "
                + " FROM SacTiposPagos stp "
                + " WHERE stp.idEstado = 'A' "
                + " ORDER BY stp.sacTipoPago ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SacTiposComprobantes> listarTiposComprobantesJQPL() {
        String sql = "SELECT stc "
                + " FROM SacTiposComprobantes stc "
                + " WHERE stc.idEstado = 'A' "
                + " ORDER BY stc.sacTipoComprobante ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarSacComprobanteSET(SacComprobantes sacComprobantes) {
        em.persist(sacComprobantes);
    }

    @Override
    public void registrarSisArchivoAdjuntoSET(SisArchivosAdjuntos sisArchivosAdjuntos) {
        em.persist(sisArchivosAdjuntos);
    }

    @Override
    public SacComprobantes registrarSacComprobanteDosSET(SacComprobantes sacComprobantes) {
        em.persist(sacComprobantes);
        return sacComprobantes;
    }

    @Override
    public SisArchivosAdjuntos registrarSisArchivoAdjuntoDosSET(SisArchivosAdjuntos sisArchivosAdjuntos) {
        em.persist(sisArchivosAdjuntos);
        return sisArchivosAdjuntos;
    }

    @Override
    public void registrarRazonSocialSET(SacRazonSocial sacRazonSocial) {
        em.persist(sacRazonSocial);
    }

    @Override
    public List<SacCarpetas> listarCarpetasPorIdSacEstanteJPQL(Long idSacEstante) {
        String sql = "SELECT c FROM SacCarpetas c "
                + " LEFT JOIN c.sacEstantes e "
                + " WHERE e.idSacEstante=:idSacEstante "
                + " AND c.idEstado = 'A' "
                + " AND e.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacEstante", idSacEstante);
        return q.getResultList();
    }

    @Override
    public void registrarSacTiposComprobantesSET(SacTiposComprobantes sacTiposComprobantes) {
        em.merge(sacTiposComprobantes);
    }

    @Override
    public void registrarSacTiposPagosSET(SacTiposPagos sacTiposPagos) {
        em.merge(sacTiposPagos);
    }

    @Override
    public Personas registrarPersonasSacComprobantesSET(Personas personas) {
        em.persist(personas);
        return personas;

    }

    @Override
    public List<SacComprobantes> listarSacComprobantesJPQL(Integer gestion) {
        String sql = "SELECT sac "
                + " FROM SacComprobantes sac "
                + " WHERE sac.gestion =:gestion "
                + " AND sac.idEstado = 'A' ";;
        //     + " ORDER BY sac.sacNumeroComprobante ";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public SacComprobantes buscarSacComprobantesPorIdSacComprobantesGET(Long idSacComprobante) {
        String sql = "SELECT sac "
                + " FROM SacComprobantes sac "
                + " WHERE sac.idSacComprobante =:idSacComprobante "
                + " AND sac.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacComprobante", idSacComprobante);
        try {
            return (SacComprobantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SacRazonSocial> listarPersonasRazonSocialPorIdSacComprobanteJPQL(Long idSacComprobante) {
        String sql = "SELECT srs "
                + " FROM SacRazonSocial srs "
                + " LEFT JOIN srs.sacComprobantes scp "
                + " LEFT JOIN srs.personas p "
                + " WHERE scp.idSacComprobante =:idSacComprobante "
                + " AND scp.idEstado = 'A' "
                + " AND p.idEstado != 'X' "
                + " AND srs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacComprobante", idSacComprobante);
        return q.getResultList();
    }

    @Override
    public SisArchivosAdjuntos actualizarSisArchivosAdjuntosSET(SisArchivosAdjuntos sisArchivosAdjuntos) {
        em.merge(sisArchivosAdjuntos);
        return sisArchivosAdjuntos;
    }

    @Override
    public SacComprobantes actualizarSacComprobanteDosSET(SacComprobantes sacComprobantes) {
        em.merge(sacComprobantes);
        return sacComprobantes;
    }

    @Override
    public void adjuntarArchivoComprobanteSET(SacCompArchivosAdjuntos sacCompArchivosAdjuntos) {
        em.persist(sacCompArchivosAdjuntos);
    }

    @Override
    public SacCarpetas buscarCarpetasPorIdSacComprobanteGET(Long idSacComprobante) {
        String sql = " SELECT scp "
                + " FROM SacComprobantes sac LEFT JOIN sac.sacCarpetas scp "
                + " WHERE sac.idSacComprobante =:idSacComprobante "
                + " AND sac.idEstado = 'A' "
                + " AND scp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacComprobante", idSacComprobante);
        try {
            return (SacCarpetas) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacEstantes buscarEstantesPorIdSacCarpetaGET(Long idSacCarpeta) {
        String sql = "SELECT est "
                + " FROM SacCarpetas car LEFT JOIN car.sacEstante est"
                + " WHERE car.idSacCarpeta =:idSacCarpeta "
                + " AND car.idEstado = 'A' "
                + " AND est.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCarpeta", idSacCarpeta);
        try {
            return (SacEstantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public SacCompArchivosAdjuntos buscarSacComprobantesArchivoAdjuntoPorIdSacComprobanteGET(Long idSacComprobante) {
        String sql = " SELECT saa "
                + " FROM SacCompArchivosAdjuntos saa LEFT JOIN saa.sacComprobantes sac "
                + " WHERE sac.idSacComprobante =:idSacComprobante "
                + " AND sac.idEstado = 'A' "
                + " AND saa.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacComprobante", idSacComprobante);
        try {
            return (SacCompArchivosAdjuntos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacCompNroCheque registrarSacCompNroChequeSET(SacCompNroCheque sacCompNroCheque) {
        em.persist(sacCompNroCheque);
        return sacCompNroCheque;
    }

    @Override
    public SacCompNroComprobante registrarSacCompNroComprobantesSET(SacCompNroComprobante sacNroComprobante) {
        em.persist(sacNroComprobante);
        return sacNroComprobante;

    }

    @Override
    public List<SacCompNroCheque> listarNumeroChequesPorIdSacComprobanteJPQL(Long idSacComprobante) {
        String sql = "SELECT snCh "
                + " FROM SacCompNroCheque snCh "
                + " LEFT JOIN snCh.sacComprobantes scp "
                + " WHERE scp.idSacComprobante =:idSacComprobante "
                + " AND scp.idEstado = 'A' "
                + " AND snCh.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacComprobante", idSacComprobante);
        return q.getResultList();
    }

    @Override
    public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacComprobanteJPQL(Long idSacComprobante) {
        String sql = "SELECT snComp "
                + " FROM SacCompNroComprobante snComp "
                + " LEFT JOIN snComp.sacComprobantes scp "
                + " WHERE scp.idSacComprobante =:idSacComprobante "
                + " AND scp.idEstado = 'A' "
                + " AND snComp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacComprobante", idSacComprobante);
        return q.getResultList();
    }

    @Override
    public SacCompNroCheque buscarNroChequePorIdSacNroChequeGET(Long idSacNroCheque) {
        String sql = "SELECT nCh "
                + " FROM SacCompNroCheque nCh "
                + " WHERE nCh.idSacNroCheque =:idSacNroCheque "
                + " AND nCh.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacNroCheque", idSacNroCheque);
        try {
            return (SacCompNroCheque) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacCompNroComprobante buscarNroComprobantePorIdNroComprobanteGET(Long idSacCompNroComprobante) {
        String sql = "SELECT nComp "
                + " FROM SacCompNroComprobante nComp "
                + " WHERE nComp.idSacCompNroComprobante =:idSacCompNroComprobante "
                + " AND nComp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCompNroComprobante", idSacCompNroComprobante);
        try {
            return (SacCompNroComprobante) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacCompNroComprobante modificarNumerosComprobantesSET(SacCompNroComprobante sacCompNroComprobante) {
        em.merge(sacCompNroComprobante);
        return sacCompNroComprobante;
    }

    @Override
    public SacCompNroCheque modificarNumeroChequesSET(SacCompNroCheque sacCompNroCheque) {
        em.merge(sacCompNroCheque);
        return sacCompNroCheque;
    }

    @Override
    public List<SacComprobantes> listarSacComprobantesGeneralJPQL() {
        String sql = "SELECT sac "
                + " FROM SacComprobantes sac "
                + " WHERE sac.idEstado = 'A' ";;
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public Personas modificarPersonasRazonSocialSET(Personas personas) {
        em.merge(personas);
        return personas;
    }

    @Override
    public SacCompNroCheque buscarNumeroChequePorIdSacNroChequeGET(Long idSacCompNroCheque) {
        String sql = "SELECT snc "
                + " FROM SacCompNroCheque snc "
                + " WHERE snc.idSacCompNroCheque =:idSacCompNroCheque "
                + " AND snc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCompNroCheque", idSacCompNroCheque);
        try {
            return (SacCompNroCheque) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public SacRazonSocial registrarPersonaRazonSocialSET(SacRazonSocial sacRazonSocial) {
        em.persist(sacRazonSocial);
        return sacRazonSocial;
    }

    @Override
    public SacRazonSocial buscarPersonaRazonSocialPorIdSacRazonSocialGET(Long idSacRazonSocial) {
        String sql = "SELECT srs "
                + " FROM SacRazonSocial srs "
                + " WHERE srs.idSacRazonSocial =:idSacRazonSocial "
                + " AND srs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacRazonSocial", idSacRazonSocial);
        try {
            return (SacRazonSocial) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SacRazonSocial> listarRazonSocialPersonasPorIdSacComprobanteJPQL(Long idSacComprobante) {
        String sql = "SELECT srs "
                + " FROM SacRazonSocial srs "
                + " LEFT JOIN srs.sacComprobantes scp "
                + " LEFT JOIN srs.personas prs "
                + " WHERE scp.idSacComprobante =:idSacComprobante "
                + " AND scp.idEstado = 'A' "
                + " AND prs.idEstado != 'X' "
                + " AND srs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacComprobante", idSacComprobante);
        return q.getResultList();
    }

    @Override
    public SacRazonSocial buscarRazonSocialPorIdSacRazonSocialGET(Long idSacRazonSocial) {
        String sql = "SELECT srs "
                + " FROM SacRazonSocial srs "
                + " WHERE srs.idSacRazonSocial =:idSacRazonSocial "
                + " AND srs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacRazonSocial", idSacRazonSocial);
        try {
            return (SacRazonSocial) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacRazonSocial actualizarRazonSocialSET(SacRazonSocial sacRazonSocial) {
        em.merge(sacRazonSocial);
        return sacRazonSocial;
    }

    @Override
    public SacPrestamosComprobantes registrarSacPrestamosComprobantesSET(SacPrestamosComprobantes sacPrestamosComprobantes) {
        em.persist(sacPrestamosComprobantes);
        return sacPrestamosComprobantes;
    }

    @Override
    public SacPrestamosComprobantes buscarSacPrestamosComprobantesPorIdSacPrestamoComprobanteGET(Long idSacPrestamoComprobante) {
        String sql = "SELECT pc "
                + " FROM SacPrestamosComprobantes pc "
                + " WHERE pc.idSacPrestamoComprobante =:idSacPrestamoComprobante "
                + " AND pc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacPrestamoComprobante", idSacPrestamoComprobante);
        try {
            return (SacPrestamosComprobantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SacPrestamosComprobantes> listarSacPrestamosComprobantesJPQL() {
        String sql = " SELECT pc "
                + " FROM SacPrestamosComprobantes pc  "
                + " WHERE pc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public SacPrestamosComprobantesDetalles registraSacPrestamosComprobantesDetallesSET(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles) {
        em.persist(sacPrestamosComprobantesDetalles);
        return sacPrestamosComprobantesDetalles;
    }

    @Override
    public List<SacEstadosComprobantes> listarEstadosComprobantesJPQL() {
        String sql = " SELECT e "
                + " FROM SacEstadosComprobantes e "
                + " WHERE e.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SacCompNroComprobante> listarNumerosComprobantesJPQL() {
        String sql = " SELECT nc "
                + " FROM SacCompNroComprobante nc LEFT JOIN nc.sacComprobantes sc "
                + " WHERE nc.idEstado != 'X' "
                + " AND sc.idEstado != 'X' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SacCompNroCheque> listarNumerosChequesJPQL() {
        String sql = " SELECT nch "
                + " FROM SacCompNroCheque nch LEFT JOIN nch.sacComprobantes sc "
                + " WHERE nch.idEstado != 'X' "
                + " AND sc.idEstado != 'X' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SacRazonSocial> listarSacRazonSocialGeneralJPQL() {
        String sql = " SELECT srs "
                + " FROM SacRazonSocial srs "
                + " LEFT JOIN srs.sacComprobantes scp "
                + " LEFT JOIN srs.personas p "
                + " WHERE scp.idEstado = 'A' "
                + " AND p.idEstado != 'X' "
                + " AND srs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public SacCompArchivosAdjuntos modificarSacArchivoAdjuntoComprobanteSET(SacCompArchivosAdjuntos sacCompArchivosAdjuntos) {
        em.merge(sacCompArchivosAdjuntos);
        return sacCompArchivosAdjuntos;
    }

    @Override
    public List<SacComprobantes> listarSacPrestamosComprobantesDetallesPorIdSacPrestamoComprobanteJPQL(Long idSacPrestamoComprobante) {
        String sql = "SELECT sc "
                + " FROM SacPrestamosComprobantesDetalles spcd "
                + " LEFT JOIN spcd.sacComprobantes sc "
                + " LEFT JOIN spcd.sacPrestamosComprobantes spc "
                + " WHERE spc.idSacPrestamoComprobante =:idSacPrestamoComprobante "
                + " AND spcd.idEstado != 'X' "
                + " AND sc.idEstado != 'X' "
                + " AND spc.idEstado != 'X' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacPrestamoComprobante", idSacPrestamoComprobante);
        return q.getResultList();
    }

    @Override
    public List<SacCompArchivosAdjuntos> listarArchivosAjuntosGeneralJPQL() {
        String sql = " SELECT saa "
                + " FROM SacCompArchivosAdjuntos saa "
                + " LEFT JOIN saa.sacComprobantes sac "
                + " WHERE sac.idEstado = 'A' "
                + " AND saa.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public SacPrestamosComprobantes modificarPrestamosComprobantesSET(SacPrestamosComprobantes sacPrestamosComprobantes) {
        em.merge(sacPrestamosComprobantes);
        return sacPrestamosComprobantes;
    }

    @Override
    public SacPrestamosComprobantes eliminarSacPrestamosComprobantesSET(SacPrestamosComprobantes sacPrestamosComprobantes) {
        em.merge(sacPrestamosComprobantes);
        return sacPrestamosComprobantes;
    }

    @Override
    public List<SacPrestamosComprobantesDetalles> listarSacPrestamosComprobantesDetallesPorIdSacPrestamoJPQL(Long idSacPrestamoComprobante) {
        String sql = "SELECT spcd "
                + " FROM SacPrestamosComprobantesDetalles spcd "
                + " LEFT JOIN spcd.sacPrestamosComprobantes spc "
                + " LEFT JOIN spcd.sacComprobantes sc "
                + " WHERE spc.idSacPrestamoComprobante =:idSacPrestamoComprobante "
                + " AND spcd.idEstado = 'A' "
                + " AND spc.idEstado = 'A' "
                + " AND sc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacPrestamoComprobante", idSacPrestamoComprobante);
        return q.getResultList();
    }

    @Override
    public SacPrestamosComprobantesDetalles buscarPrestamosDetallesPorIdSacPrestamoDetalleGET(Long idSacPrestamoComprobanteDetalle) {
        String sql = "SELECT spcd "
                + " FROM SacPrestamosComprobantesDetalles spcd "
                + " WHERE spcd.idSacPrestamoComprobanteDetalle =:idSacPrestamoComprobanteDetalle "
                + " AND spcd.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacPrestamoComprobanteDetalle", idSacPrestamoComprobanteDetalle);
        try {
            return (SacPrestamosComprobantesDetalles) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacPrestamosComprobantesDetalles eliminarSacPrestamosComprobantesDetallesSET(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles) {
        em.merge(sacPrestamosComprobantesDetalles);
        return sacPrestamosComprobantesDetalles;
    }

    @Override
    public List<SacPrestamosComprobantesDetalles> listarPrestamosComprobantesDetallesGeneralJPQL() {
        String sql = " SELECT spcd "
                + " FROM SacPrestamosComprobantesDetalles spcd LEFT JOIN spcd.sacPrestamosComprobantes spc "
                + " LEFT JOIN spcd.sacComprobantes sc "
                + " WHERE spcd.idEstado = 'A' "
                + " AND spc.idEstado = 'A' "
                + " AND sc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public SacEstadosComprobantes buscarSacEstadoComprobantesPorIdSacEstadoComprobanteJPQL(Long idSacEstadoComprobante) {
        String sql = "SELECT sec "
                + " FROM SacEstadosComprobantes sec "
                + " WHERE sec.idSacEstadoComprobante =:idSacEstadoComprobante "
                + " AND sec.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacEstadoComprobante", idSacEstadoComprobante);
        try {
            return (SacEstadosComprobantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacDevolucionesComprobantes registrarSacDevolucionesComprobantesSET(SacDevolucionesComprobantes sacDevolucionesComprobantes) {
        em.persist(sacDevolucionesComprobantes);
        return sacDevolucionesComprobantes;
    }

    @Override
    public SacDevolucionesComprobantesDetalles registrarDevolucionesComprobantesDetallesSET(SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles) {
        em.persist(sacDevolucionesComprobantesDetalles);
        return sacDevolucionesComprobantesDetalles;
    }

    @Override
    public SacDevolucionesComprobantes modificarDevolucionComprobantesSET(SacDevolucionesComprobantes sacDevolucionesComprobantes) {
        em.merge(sacDevolucionesComprobantes);
        return sacDevolucionesComprobantes;
    }

    @Override
    public SacDevolucionesComprobantesDetalles modificarDevolucionesComprobantesDetallesSET(SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles) {
        em.merge(sacDevolucionesComprobantesDetalles);
        return sacDevolucionesComprobantesDetalles;
    }

    @Override
    public SacDevolucionesComprobantes eliminarSacDevolucionesComprobantesSET(SacDevolucionesComprobantes sacDevolucionesComprobantes) {
        em.merge(sacDevolucionesComprobantes);
        return sacDevolucionesComprobantes;
    }

    @Override
    public SacDevolucionesComprobantes buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobanteGET(Long idSacDevolucionComprobante) {
        String sql = "SELECT sdc "
                + " FROM SacDevolucionesComprobantes sdc "
                + " WHERE sdc.idSacDevolucionComprobante =:idSacDevolucionComprobante "
                + " AND sdc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacDevolucionComprobante", idSacDevolucionComprobante);
        try {
            return (SacDevolucionesComprobantes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacDevolucionesComprobantesDetalles buscarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobanteDetalleGET(Long idSacDevolucionComprobanteDetalle) {
        String sql = "SELECT sdcd "
                + " FROM SacDevolucionesComprobantesDetalles sdcd "
                + " WHERE sdcd.idSacDevolucionComprobanteDetalle =:idSacDevolucionComprobanteDetalle "
                + " AND sdcd.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacDevolucionComprobanteDetalle", idSacDevolucionComprobanteDetalle);
        try {
            return (SacDevolucionesComprobantesDetalles) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SacDevolucionesComprobantes> listarSacDevolucionesComprobantesJPQL() {
        String sql = " SELECT sdc "
                + " FROM SacDevolucionesComprobantes sdc "
                + " WHERE sdc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SacDevolucionesComprobantesDetalles> listarSacDevolucionesComprobantesDetallesJPQL() {
        String sql = " SELECT sdcd "
                + " FROM SacDevolucionesComprobantesDetalles sdcd "
                + " WHERE sdcd.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SacComprobantes> listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobanteJPQL(Long idSacDevolucionComprobante) {
        String sql = "SELECT sc "
                + " FROM SacDevolucionesComprobantesDetalles sdcd "
                + " LEFT JOIN sdcd.sacComprobantes sc "
                + " LEFT JOIN sdcd.sacDevolucionesComprobantes sdc "
                + " WHERE sdc.idSacDevolucionComprobante =:idSacDevolucionComprobante "
                + " AND sdcd.idEstado = 'A' "
                + " AND sc.idEstado = 'A' "
                + " AND sdc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacDevolucionComprobante", idSacDevolucionComprobante);
        return q.getResultList();
    }

    @Override
    public SacPrestamosComprobantesDetalles buscarSacPrstamoDetallesPorIdSacComprobanteyPrestamoGET(Long idSacComprobante, Boolean prestamo, Long idSacPrestamoComprobante) {
        String sql = "SELECT spcd "
                + " FROM SacPrestamosComprobantesDetalles spcd "
                + " LEFT JOIN  spcd.sacComprobantes sc "
                + " LEFT JOIN  spcd.sacPrestamosComprobantes spc "
                + " WHERE sc.idSacComprobante =:idSacComprobante "
                + " AND spc.idSacPrestamoComprobante =:idSacPrestamoComprobante"
                + " AND spcd.prestamo =:prestamo "
                + " AND spcd.idEstado = 'A' "
                + " AND sc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacComprobante", idSacComprobante);
        q.setParameter("idSacPrestamoComprobante", idSacPrestamoComprobante);
        q.setParameter("prestamo", prestamo);
        try {
            return (SacPrestamosComprobantesDetalles) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacPrestamosComprobantesDetalles actualizarPrestamoComprobanteDetalleSET(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles) {
        em.merge(sacPrestamosComprobantesDetalles);
        return sacPrestamosComprobantesDetalles;
    }

    @Override
    public List<SacEstantes> listarCarpetasPorGestionIdSacEstanteJPQL(Long idSacEstante, Integer gestion) {
        String sql = " SELECT sct "
                + " FROM SacCarpetas sct LEFT JOIN sct.sacEstantes ste "
                + " WHERE ste.idSacEstante =:idSacEstante "
                + " AND sct.gestion =:gestion "
                + " AND ste.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " GROUP BY sct.gestion";
        Query q = em.createQuery(sql);
        q.setParameter("idSacEstante", idSacEstante);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<SacComprobantesResponse> listarComprobantesEstantesGestionJPQL() {
        String sql = " SELECT DISTINCT new uap.usic.siga.dto.SacComprobantesResponse(ste.idSacEstante, sct.gestion, ste.sacNombreEstante)"
                + " FROM SacComprobantes sac LEFT JOIN sac.sacCarpetas sct "
                + " LEFT JOIN sct.sacEstantes ste "
                + " WHERE ste.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND sac.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<SacComprobantesResponse> listarComprobantesMesesPorIdSacEstanteGestionJPQL(Long idSacEstante, Integer gestion) {
        String sql = " SELECT DISTINCT new uap.usic.siga.dto.SacComprobantesResponse(sim.idMes, sim.mes, sct.gestion)"
                + " FROM SacComprobantes sac LEFT JOIN sac.sacCarpetas sct "
                + " LEFT JOIN sct.sacEstantes ste LEFT JOIN sct.sisMeses sim"
                + " WHERE ste.idSacEstante =:idSacEstante "
                + " AND sct.gestion =:gestion "
                + " AND ste.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND sac.idEstado = 'A' "
                + " AND sim.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacEstante", idSacEstante);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<SacComprobantesResponse> listarComprobantesCarpetasPorIdMesJPQL(Long idMes, Long idSacEstante) {
        String sql = " SELECT DISTINCT new uap.usic.siga.dto.SacComprobantesResponse(sct.idSacCarpeta, sct.sacCodigoCarpeta, ste.sacNombreEstante, sct.sacNumeroCarpeta)"
                + " FROM SacComprobantes sac LEFT JOIN sac.sacCarpetas sct "
                + " LEFT JOIN sct.sacEstantes ste LEFT JOIN sct.sisMeses sim"
                + " WHERE sim.idMes =:idMes "
                + " AND ste.idSacEstante =:idSacEstante "
                + " AND ste.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND sac.idEstado = 'A' "
                + " AND sim.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idMes", idMes);
        q.setParameter("idSacEstante", idSacEstante);
        return q.getResultList();
    }

    @Override
    public List<SacComprobantes> listarSacComprobantesPorIdSacCarpetaJPQL(Long idSacCarpeta) {
        String sql = " SELECT sac FROM SacComprobantes sac "
                + " LEFT JOIN sac.sacCarpetas sct "
                + " WHERE sct.idSacCarpeta =:idSacCarpeta "
                + " AND sct.idEstado = 'A' "
                + " AND sac.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCarpeta", idSacCarpeta);
        return q.getResultList();
    }

    @Override
    public List<SacRazonSocial> listarSacRazonSocialPorIdSacCarpetaJPQL(Long idSacCarpeta) {
        String sql = " SELECT srs "
                + " FROM SacRazonSocial srs "
                + " LEFT JOIN srs.sacComprobantes scp "
                + " LEFT JOIN srs.personas p "
                + " LEFT JOIN scp.sacCarpetas sct "
                + " WHERE sct.idSacCarpeta =:idSacCarpeta "
                + " AND scp.idEstado = 'A' "
                + " AND p.idEstado != 'X' "
                + " AND srs.idEstado = 'A' "
                + " AND sct.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCarpeta", idSacCarpeta);
        return q.getResultList();
    }

    @Override
    public List<SacCompNroCheque> listarNumerosChequesPorIdSacCarpetaJPQL(Long idSacCarpeta) {
        String sql = " SELECT nch "
                + " FROM SacCompNroCheque nch "
                + " LEFT JOIN nch.sacComprobantes sc "
                + " LEFT JOIN sc.sacCarpetas sct "
                + " WHERE sct.idSacCarpeta =:idSacCarpeta "
                + " AND nch.idEstado != 'X' "
                + " AND sc.idEstado != 'X' "
                + " AND sct.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCarpeta", idSacCarpeta);
        return q.getResultList();
    }

    @Override
    public List<SacCompArchivosAdjuntos> listarArchivosAjuntosPorIdSacCarpetaJPQL(Long idSacCarpeta) {
        String sql = " SELECT saa "
                + " FROM SacCompArchivosAdjuntos saa "
                + " LEFT JOIN saa.sacComprobantes sac "
                + " LEFT JOIN sac.sacCarpetas sct "
                + " WHERE sct.idSacCarpeta =:idSacCarpeta "
                + " AND sac.idEstado = 'A' "
                + " AND saa.idEstado = 'A' "
                + " AND sct.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCarpeta", idSacCarpeta);
        return q.getResultList();
    }

    @Override
    public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacCarpetaJPQL(Long idSacCarpeta) {
        String sql = "SELECT snComp "
                + " FROM SacCompNroComprobante snComp "
                + " LEFT JOIN snComp.sacComprobantes scp "
                + " LEFT JOIN scp.sacCarpetas sct "
                + " WHERE sct.idSacCarpeta =:idSacCarpeta "
                + " AND scp.idEstado = 'A' "
                + " AND snComp.idEstado = 'A' "
                + " AND sct.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacCarpeta", idSacCarpeta);
        return q.getResultList();
    }

    @Override
    public List<SacComprobantes> listarSacComprobantesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion) {
        String sql = " SELECT sac "
                + " FROM SacComprobantes sac LEFT JOIN sac.sacTiposPagos stp "
                + " LEFT JOIN sac.sacCarpetas sct LEFT JOIN sct.sacEstantes stt "
                + " LEFT JOIN sct.sisMeses sms "
                + " WHERE stp.idSacTipoPago =:idSacTipoPago "
                + " AND sms.idMes =:idMes "
                + " AND sct.gestion =:gestion "
                + " AND sct.idEstado = 'A' "
                + " AND sac.idEstado = 'A' "
                + " AND stt.idEstado = 'A' "
                + " AND sms.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoPago", idSacTipoPago);
        q.setParameter("idMes", idMes);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<SacRazonSocial> listarSacRazonSocialPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion) {
        String sql = " SELECT srs "
                + " FROM SacRazonSocial srs "
                + " LEFT JOIN srs.sacComprobantes scp LEFT JOIN srs.personas p "
                + " LEFT JOIN scp.sacCarpetas sct LEFT JOIN sct.sisMeses sms "
                + " LEFT JOIN scp.sacTiposPagos stp "
                + " WHERE stp.idSacTipoPago =:idSacTipoPago"
                + " AND sct.gestion =:gestion "
                + " AND sms.idMes =:idMes "
                + " AND scp.idEstado = 'A' "
                + " AND p.idEstado != 'X' "
                + " AND srs.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND sms.idEstado = 'A' "
                + " AND stp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoPago", idSacTipoPago);
        q.setParameter("idMes", idMes);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<SacCompNroCheque> listarNumerosChequesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion) {
        String sql = " SELECT nch "
                + " FROM SacCompNroCheque nch "
                + " LEFT JOIN nch.sacComprobantes sc LEFT JOIN sc.sacTiposPagos stp  "
                + " LEFT JOIN sc.sacCarpetas sct LEFT JOIN sct.sisMeses sms "
                + " WHERE stp.idSacTipoPago =:idSacTipoPago"
                + " AND sct.gestion =:gestion "
                + " AND sms.idMes =:idMes "
                + " AND nch.idEstado != 'X' "
                + " AND sc.idEstado != 'X' "
                + " AND sct.idEstado = 'A' "
                + " AND sms.idEstado = 'A' "
                + " AND stp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoPago", idSacTipoPago);
        q.setParameter("idMes", idMes);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<SacCompArchivosAdjuntos> listarArchivosAjuntosPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion) {
        String sql = " SELECT saa "
                + " FROM SacCompArchivosAdjuntos saa "
                + " LEFT JOIN saa.sacComprobantes sac LEFT JOIN sac.sacTiposPagos stp "
                + " LEFT JOIN sac.sacCarpetas sct LEFT JOIN sct.sisMeses sms "
                + " WHERE stp.idSacTipoPago =:idSacTipoPago"
                + " AND sct.gestion =:gestion "
                + " AND sms.idMes =:idMes "
                + " AND sac.idEstado = 'A' "
                + " AND saa.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND sms.idEstado = 'A' "
                + " AND stp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoPago", idSacTipoPago);
        q.setParameter("idMes", idMes);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion) {
        String sql = "SELECT snComp "
                + " FROM SacCompNroComprobante snComp "
                + " LEFT JOIN snComp.sacComprobantes scp LEFT JOIN scp.sacTiposPagos stp "
                + " LEFT JOIN scp.sacCarpetas sct LEFT JOIN sct.sisMeses sms "
                + " WHERE stp.idSacTipoPago =:idSacTipoPago"
                + " AND sct.gestion =:gestion "
                + " AND sms.idMes =:idMes "
                + " AND scp.idEstado = 'A' "
                + " AND snComp.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND sms.idEstado = 'A' "
                + " AND stp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoPago", idSacTipoPago);
        q.setParameter("idMes", idMes);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<SisMeses> listarMesesComprobantesPorGestionJPQL(Integer gestion) {
        String sql = "SELECT DISTINCT sms "
                + " FROM SacComprobantes sac "
                + " LEFT JOIN sac.sacCarpetas sct "
                + " LEFT JOIN sct.sisMeses sms "
                + " WHERE sct.gestion =:gestion"
                + " AND sac.idEstado = 'A' "
                + " AND sms.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public SisMeses buscarSisMesesPorIdMesGET(Long idMes) {
        String sql = " SELECT sms "
                + " FROM SisMeses sms "
                + " WHERE sms.idMes =:idMes "
                + " AND sms.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idMes", idMes);
        try {
            return (SisMeses) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SacTiposPagos buscarSacTiposPagosPorIdSacTipoPagoGET(Long idSacTipoPago) {
        String sql = " SELECT stp "
                + " FROM SacTiposPagos stp "
                + " WHERE stp.idSacTipoPago =:idSacTipoPago "
                + " AND stp.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoPago", idSacTipoPago);
        try {
            return (SacTiposPagos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SacDevolucionesComprobantesDetalles> listarSacDevolucionComprobantesDetallesPorIdSacDevolucionComprobanteJPQL(Long idSacDevolucionComprobante) {
        String sql = "SELECT sdcd "
                + " FROM SacDevolucionesComprobantesDetalles sdcd "
                + " LEFT JOIN sdcd.sacDevolucionesComprobantes sdc "
                + " LEFT JOIN sdcd.sacComprobantes sc "
                + " WHERE sdc.idSacDevolucionComprobante =:idSacDevolucionComprobante "
                + " AND sdcd.idEstado = 'A' "
                + " AND sc.idEstado = 'A' "
                + " AND sdc.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacDevolucionComprobante", idSacDevolucionComprobante);
        return q.getResultList();
    }
 @Override
    public List<SacComprobantes> listarSacComprobantesPorRazonSocialGestionIdSacTipoComprobanteJPQL(Long idSacTipoComprobante, String nombres, Integer gestion) {
          String sql = " SELECT sac "
                + " FROM SacRazonSocial srs LEFT JOIN srs.personas prs "
                + " LEFT JOIN srs.sacComprobantes sac LEFT JOIN sac.sacTiposComprobantes stc"
                + " LEFT JOIN sac.sacCarpetas sct "
                + " WHERE (prs.paterno like :nombres OR prs.materno like :nombres OR prs.nombres like :nombres OR prs.ci like :nombres) "
                + " AND stc.idSacTipoComprobante =:idSacTipoComprobante "
                + " AND sct.gestion =:gestion "
                + " AND sac.idEstado = 'A' "
                + " AND stc.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND srs.idEstado = 'A' "
                + " AND prs.idEstado != 'X' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoComprobante", idSacTipoComprobante);
        q.setParameter("nombres", '%' + nombres + '%');
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorRazonSocialGestionIdSacTipoComprobanteJPQL(Long idSacTipoComprobante, String nombres) {
         String sql = " SELECT new uap.usic.siga.dto.SacComprobantesResponse(sac.idSacComprobante, srs.idSacRazonSocial, prs.idPersona, prs.ci, prs.paterno, prs.materno, prs.nombres, sct.gestion, sms.mes) "
                + " FROM SacRazonSocial srs LEFT JOIN srs.personas prs "
                + " LEFT JOIN srs.sacComprobantes sac LEFT JOIN sac.sacTiposComprobantes stc"
                + " LEFT JOIN sac.sacCarpetas sct LEFT JOIN sct.sisMeses sms "
                + " WHERE (prs.paterno like :nombres OR prs.materno like :nombres OR prs.nombres like :nombres OR prs.ci like :nombres) "
                + " AND stc.idSacTipoComprobante =:idSacTipoComprobante "
                + " AND sac.idEstado = 'A' "
                + " AND stc.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND srs.idEstado = 'A' "
                + " AND prs.idEstado != 'X' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoComprobante", idSacTipoComprobante);
        q.setParameter("nombres", '%' + nombres + '%');
        return q.getResultList();
    }

    @Override
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorSacNroComprobanteIdSacTipoComprobanteJPQL(Long idSacTipoComprobante, Integer sacNroComprobante) {
          String sql = " SELECT new uap.usic.siga.dto.SacComprobantesResponse(sac.idSacComprobante, sct.gestion, sms.mes, snc.sacNroComprobante, snc.idSacCompNroComprobante) "
                + " FROM SacCompNroComprobante snc LEFT JOIN snc.sacComprobantes sac "
                + " LEFT JOIN sac.sacTiposComprobantes stc LEFT JOIN sac.sacCarpetas sct "
                + " LEFT JOIN sct.sisMeses sms "
                + " WHERE snc.sacNroComprobante =:sacNroComprobante "
                + " AND stc.idSacTipoComprobante =:idSacTipoComprobante "
                + " AND sac.idEstado = 'A' "
                + " AND stc.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND snc.idEstado = 'A' "
                + " AND sms.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoComprobante", idSacTipoComprobante);
        q.setParameter("sacNroComprobante", sacNroComprobante);
        return q.getResultList();
    }

    @Override
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorSacNroChequeIdSacTipoComprobanteJPQL(Long idSacTipoComprobante, Integer sacNroCheque) {
         String sql = " SELECT new uap.usic.siga.dto.SacComprobantesResponse(sac.gestion, sac.idSacComprobante, sms.mes, snc.sacNroCheque, snc.idSacCompNroCheque) "
                + " FROM SacCompNroCheque snc LEFT JOIN snc.sacComprobantes sac "
                + " LEFT JOIN sac.sacTiposComprobantes stc LEFT JOIN sac.sacCarpetas sct "
                + " LEFT JOIN sct.sisMeses sms "
                + " WHERE snc.sacNroCheque =:sacNroCheque "
                + " AND stc.idSacTipoComprobante =:idSacTipoComprobante "
                + " AND sac.idEstado = 'A' "
                + " AND stc.idEstado = 'A' "
                + " AND sct.idEstado = 'A' "
                + " AND snc.idEstado = 'A' "
                + " AND sms.idEstado != 'X' ";
        Query q = em.createQuery(sql);
        q.setParameter("idSacTipoComprobante", idSacTipoComprobante);
        q.setParameter("sacNroCheque", sacNroCheque);
        return q.getResultList();
    }
}
