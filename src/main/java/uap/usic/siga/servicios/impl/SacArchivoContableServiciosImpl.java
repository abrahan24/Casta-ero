package uap.usic.siga.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import uap.usic.siga.modelos.CajitaDao;
import uap.usic.siga.modelos.SacArchivoContableDao;
import uap.usic.siga.servicios.SacArchivoContableServicios;

/**
 *
 * @author Yessenia
 */
@Service("archivoContableServicio")
@Transactional
public class SacArchivoContableServiciosImpl implements SacArchivoContableServicios {

    @Autowired
    private SacArchivoContableDao dao;

    @Override
    public List<SacTiposCarpetas> listarTposCarpetas() {
        return dao.listarTposCarpetasJPQL();
    }

    @Override
    public void registrarCarpetas(SacCarpetas sacCarpetas) {
        dao.registrarCarpetasSET(sacCarpetas);
    }

    @Override
    public List<SacEstantes> listarEstantes() {
        return dao.listarEstantesJPQL();
    }

    @Override
    public List<SisMeses> ListarMeses() {
        return dao.ListarMesesJPQL();
    }

    @Override
    public List<SacCarpetas> listarCarpetas() {
        return dao.listarCarpetasJPQL();
    }

    @Override
    public SacCarpetas buscarCarpetasPorIdSacCarpeta(Long idSacCarpeta) {
        return dao.buscarCarpetasPorIdSacCarpetaGET(idSacCarpeta);
    }

    @Override
    public void modificarSacCarpetas(SacCarpetas sacCarpetas) {
        dao.modificarSacCarpetasSET(sacCarpetas);
    }

    @Override
    public void eliminarSacCarpetas(SacCarpetas sacCarpetas) {
        dao.eliminarSacCarpetasSET(sacCarpetas);
    }

    @Override
    public void registrarEstantes(SacEstantes sacEstantes) {
        dao.registrarEstantesSET(sacEstantes);
    }

    @Override
    public void modificarEstantes(SacEstantes sacEstantes) {
        dao.modificarEstantesSET(sacEstantes);
    }

    @Override
    public void eliminarEstantes(SacEstantes sacEstantes) {
        dao.eliminarEstantesSET(sacEstantes);
    }

    @Override
    public void registrarSacTiposCarpetas(SacTiposCarpetas sacTiposCarpetas) {
        dao.registrarSacTiposCarpetasSET(sacTiposCarpetas);
    }

    @Override
    public SacEstantes buscarSacEstantesPorIdSacEstante(Long idSacEstante) {
        return dao.buscarSacEstantesPorIdSacEstanteGET(idSacEstante);
    }

    @Override
    public List<SacTiposPagos> listarTiposPagos() {
        return dao.listarTiposPagosJQPL();
    }

    @Override
    public List<SacTiposComprobantes> listarTiposComprobantes() {
        return dao.listarTiposComprobantesJQPL();
    }

    @Override
    public void registrarSacComprobante(SacComprobantes sacComprobantes) {
        dao.registrarSacComprobanteSET(sacComprobantes);
    }

    @Override
    public void registrarSisArchivoAdjunto(SisArchivosAdjuntos sisArchivosAdjuntos) {
        dao.registrarSisArchivoAdjuntoSET(sisArchivosAdjuntos);
    }

    @Override
    public SacComprobantes registrarSacComprobanteDos(SacComprobantes sacComprobantes) {
        return dao.registrarSacComprobanteDosSET(sacComprobantes);
    }

    @Override
    public SisArchivosAdjuntos registrarSisArchivoAdjuntoDos(SisArchivosAdjuntos sisArchivosAdjuntos) {
        return dao.registrarSisArchivoAdjuntoDosSET(sisArchivosAdjuntos);
    }

    @Override
    public void registrarRazonSocial(SacRazonSocial sacRazonSocial) {
        dao.registrarRazonSocialSET(sacRazonSocial);
    }

    @Override
    public List<SacCarpetas> listarCarpetasPorIdSacEstante(Long idSacEstante) {
        return dao.listarCarpetasPorIdSacEstanteJPQL(idSacEstante);
    }

    @Override
    public void registrarSacTiposComprobantes(SacTiposComprobantes sacTiposComprobantes) {
        dao.registrarSacTiposComprobantesSET(sacTiposComprobantes);
    }

    @Override
    public void registrarSacTiposPagos(SacTiposPagos sacTiposPagos) {
        dao.registrarSacTiposPagosSET(sacTiposPagos);
    }

    @Override
    public Personas registrarPersonasSacComprobantes(Personas personas) {
        return dao.registrarPersonasSacComprobantesSET(personas);
    }

    @Override
    public List<SacComprobantes> listarSacComprobantes(Integer gestion) {
        return dao.listarSacComprobantesJPQL(gestion);
    }

    @Override
    public SacComprobantes buscarSacComprobantesPorIdSacComprobantes(Long idSacComprobante) {
        return dao.buscarSacComprobantesPorIdSacComprobantesGET(idSacComprobante);
    }

    @Override
    public List<SacRazonSocial> listarPersonasRazonSocialPorIdSacComprobante(Long idSacComprobante) {
        return dao.listarPersonasRazonSocialPorIdSacComprobanteJPQL(idSacComprobante);
    }

    @Override
    public SisArchivosAdjuntos actualizarSisArchivosAdjuntos(SisArchivosAdjuntos sisArchivosAdjuntos) {
        return dao.actualizarSisArchivosAdjuntosSET(sisArchivosAdjuntos);
    }

    @Override
    public SacComprobantes actualizarSacComprobanteDos(SacComprobantes sacComprobantes) {
        return dao.actualizarSacComprobanteDosSET(sacComprobantes);
    }

    @Override
    public void adjuntarArchivoComprobante(SacCompArchivosAdjuntos sacCompArchivosAdjuntos) {
        dao.adjuntarArchivoComprobanteSET(sacCompArchivosAdjuntos);
    }

    @Override
    public SacCarpetas buscarCarpetasPorIdSacComprobante(Long idSacComprobante) {
        return dao.buscarCarpetasPorIdSacComprobanteGET(idSacComprobante);
    }

    @Override
    public SacEstantes buscarEstantesPorIdSacCarpeta(Long idSacCarpeta) {
        return dao.buscarEstantesPorIdSacCarpetaGET(idSacCarpeta);
    }

    @Override
    public SacCompArchivosAdjuntos buscarSacComprobantesArchivoAdjuntoPorIdSacComprobante(Long idSacComprobante) {
        return dao.buscarSacComprobantesArchivoAdjuntoPorIdSacComprobanteGET(idSacComprobante);
    }

    @Override
    public SacCompNroCheque registrarSacCompNroCheque(SacCompNroCheque sacCompNroCheque) {
        return dao.registrarSacCompNroChequeSET(sacCompNroCheque);
    }

    @Override
    public SacCompNroComprobante registrarSacCompNroComprobantes(SacCompNroComprobante sacNroComprobante) {
        return dao.registrarSacCompNroComprobantesSET(sacNroComprobante);
    }

    @Override
    public List<SacCompNroCheque> listarNumeroChequesPorIdSacComprobante(Long idSacComprobante) {
        return dao.listarNumeroChequesPorIdSacComprobanteJPQL(idSacComprobante);
    }

    @Override
    public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacComprobante(Long idSacComprobante) {
        return dao.listarNumeroComprobantesPorIdSacComprobanteJPQL(idSacComprobante);
    }

    @Override
    public SacCompNroCheque buscarNroChequePorIdSacNroCheque(Long idSacNroCheque) {
        return dao.buscarNroChequePorIdSacNroChequeGET(idSacNroCheque);
    }

    @Override
    public SacCompNroComprobante buscarNroComprobantePorIdNroComprobante(Long idSacCompNroComprobante) {
        return dao.buscarNroComprobantePorIdNroComprobanteGET(idSacCompNroComprobante);
    }

    @Override
    public SacCompNroComprobante modificarNumerosComprobantes(SacCompNroComprobante sacCompNroComprobante) {
        return dao.modificarNumerosComprobantesSET(sacCompNroComprobante);
    }

    @Override
    public SacCompNroCheque modificarNumeroCheques(SacCompNroCheque sacCompNroCheque) {
        return dao.modificarNumeroChequesSET(sacCompNroCheque);
    }

    @Override
    public List<SacComprobantes> listarSacComprobantesGeneral() {
        return dao.listarSacComprobantesGeneralJPQL();
    }

    @Override
    public Personas modificarPersonasRazonSocial(Personas personas) {
        return dao.modificarPersonasRazonSocialSET(personas);
    }

    @Override
    public SacCompNroCheque buscarNumeroChequePorIdSacNroCheque(Long idSacCompNroCheque) {
        return dao.buscarNumeroChequePorIdSacNroChequeGET(idSacCompNroCheque);
    }

    @Override
    public SacRazonSocial registrarPersonaRazonSocial(SacRazonSocial sacRazonSocial) {
        return dao.registrarPersonaRazonSocialSET(sacRazonSocial);
    }

    @Override
    public SacRazonSocial buscarPersonaRazonSocialPorIdSacRazonSocial(Long idSacRazonSocial) {
        return dao.buscarPersonaRazonSocialPorIdSacRazonSocialGET(idSacRazonSocial);
    }

    @Override
    public List<SacRazonSocial> listarRazonSocialPersonasPorIdSacComprobante(Long idSacComprobante) {
        return dao.listarRazonSocialPersonasPorIdSacComprobanteJPQL(idSacComprobante);
    }

    @Override
    public SacRazonSocial buscarRazonSocialPorIdSacRazonSocial(Long idSacRazonSocial) {
        return dao.buscarRazonSocialPorIdSacRazonSocialGET(idSacRazonSocial);
    }

    @Override
    public SacRazonSocial actualizarRazonSocial(SacRazonSocial sacRazonSocial) {
        return dao.actualizarRazonSocialSET(sacRazonSocial);
    }

    @Override
    public SacPrestamosComprobantes registrarSacPrestamosComprobantes(SacPrestamosComprobantes sacPrestamosComprobantes) {
        return dao.registrarSacPrestamosComprobantesSET(sacPrestamosComprobantes);
    }

    @Override
    public SacPrestamosComprobantes buscarSacPrestamosComprobantesPorIdSacPrestamoComprobante(Long idSacPrestamoComprobante) {
        return dao.buscarSacPrestamosComprobantesPorIdSacPrestamoComprobanteGET(idSacPrestamoComprobante);
    }

    @Override
    public List<SacPrestamosComprobantes> listarSacPrestamosComprobantes() {
        return dao.listarSacPrestamosComprobantesJPQL();
    }

    @Override
    public SacPrestamosComprobantesDetalles registraSacPrestamosComprobantesDetalles(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles) {
        return dao.registraSacPrestamosComprobantesDetallesSET(sacPrestamosComprobantesDetalles);
    }

    @Override
    public List<SacEstadosComprobantes> listarEstadosComprobantes() {
        return dao.listarEstadosComprobantesJPQL();
    }

    @Override
    public List<SacCompNroComprobante> listarNumerosComprobantes() {
        return dao.listarNumerosComprobantesJPQL();
    }

    @Override
    public List<SacCompNroCheque> listarNumerosCheques() {
        return dao.listarNumerosChequesJPQL();
    }

    @Override
    public List<SacRazonSocial> listarSacRazonSocialGeneral() {
        return dao.listarSacRazonSocialGeneralJPQL();
    }

    @Override
    public SacCompArchivosAdjuntos modificarSacArchivoAdjuntoComprobanteSET(SacCompArchivosAdjuntos sacCompArchivosAdjuntos) {
        return dao.modificarSacArchivoAdjuntoComprobanteSET(sacCompArchivosAdjuntos);
    }

    @Override
    public List<SacComprobantes> listarSacPrestamosComprobantesDetallesPorIdSacPrestamoComprobante(Long idSacPrestamoComprobante) {
        return dao.listarSacPrestamosComprobantesDetallesPorIdSacPrestamoComprobanteJPQL(idSacPrestamoComprobante);
    }

    @Override
    public List<SacCompArchivosAdjuntos> listarArchivosAjuntosGeneral() {
        return dao.listarArchivosAjuntosGeneralJPQL();
    }

    @Override
    public SacPrestamosComprobantes modificarPrestamosComprobantes(SacPrestamosComprobantes sacPrestamosComprobantes) {
      return dao.modificarPrestamosComprobantesSET(sacPrestamosComprobantes);
    }

    @Override
    public SacPrestamosComprobantes eliminarSacPrestamosComprobantes(SacPrestamosComprobantes sacPrestamosComprobantes) {
       return dao.eliminarSacPrestamosComprobantesSET(sacPrestamosComprobantes);
    }

    @Override
    public List<SacPrestamosComprobantesDetalles> listarSacPrestamosComprobantesDetallesPorIdSacPrestamo(Long idSacPrestamoComprobante) {
     return dao.listarSacPrestamosComprobantesDetallesPorIdSacPrestamoJPQL(idSacPrestamoComprobante);
    }

    @Override
    public SacPrestamosComprobantesDetalles buscarPrestamosDetallesPorIdSacPrestamoDetalle(Long idSacPrestamoComprobanteDetalle) {
     return dao.buscarPrestamosDetallesPorIdSacPrestamoDetalleGET(idSacPrestamoComprobanteDetalle);
    }

    @Override
    public SacPrestamosComprobantesDetalles eliminarSacPrestamosComprobantesDetalles(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles) {
     return dao.eliminarSacPrestamosComprobantesDetallesSET(sacPrestamosComprobantesDetalles);
    }

    @Override
    public List<SacPrestamosComprobantesDetalles> listarPrestamosComprobantesDetallesGeneral() {
       return dao.listarPrestamosComprobantesDetallesGeneralJPQL();
    }

    @Override
    public SacEstadosComprobantes buscarSacEstadoComprobantesPorIdSacEstadoComprobante(Long idSacEstadoComprobante) {
     return dao.buscarSacEstadoComprobantesPorIdSacEstadoComprobanteJPQL(idSacEstadoComprobante);
    }

    @Override
    public SacDevolucionesComprobantes registrarSacDevolucionesComprobantes(SacDevolucionesComprobantes sacDevolucionesComprobantes) {
       return dao.registrarSacDevolucionesComprobantesSET(sacDevolucionesComprobantes);
    }

    @Override
    public SacDevolucionesComprobantesDetalles registrarDevolucionesComprobantesDetalles(SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles) {
      return dao.registrarDevolucionesComprobantesDetallesSET(sacDevolucionesComprobantesDetalles);
    }

    @Override
    public SacDevolucionesComprobantes modificarDevolucionComprobantes(SacDevolucionesComprobantes sacDevolucionesComprobantes) {
     return dao.modificarDevolucionComprobantesSET(sacDevolucionesComprobantes);
    }

    @Override
    public SacDevolucionesComprobantesDetalles modificarDevolucionesComprobantesDetalles(SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles) {
    return dao.modificarDevolucionesComprobantesDetallesSET(sacDevolucionesComprobantesDetalles);
    }

    @Override
    public SacDevolucionesComprobantes eliminarSacDevolucionesComprobantes(SacDevolucionesComprobantes sacDevolucionesComprobantes) {
    return dao.eliminarSacDevolucionesComprobantesSET(sacDevolucionesComprobantes);
    }

    @Override
    public SacDevolucionesComprobantes buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobante(Long idSacDevolucionComprobante) {
        return dao.buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobanteGET(idSacDevolucionComprobante);
    }

    @Override
    public SacDevolucionesComprobantesDetalles buscarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobanteDetalle(Long idSacDevolucionComprobanteDetalle) {
      return dao.buscarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobanteDetalleGET(idSacDevolucionComprobanteDetalle);
    }

    @Override
    public List<SacDevolucionesComprobantes> listarSacDevolucionesComprobantes() {
      return dao.listarSacDevolucionesComprobantesJPQL();
    }

    @Override
    public List<SacDevolucionesComprobantesDetalles> listarSacDevolucionesComprobantesDetalles() {
     return dao.listarSacDevolucionesComprobantesDetallesJPQL();
    }

    @Override
    public List<SacComprobantes> listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobante(Long idSacDevolucionComprobante) {
          return dao.listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobanteJPQL(idSacDevolucionComprobante);
    }

    @Override
    public SacPrestamosComprobantesDetalles buscarSacPrstamoDetallesPorIdSacComprobanteyPrestamo(Long idSacComprobante, Boolean prestamo,Long idSacPrestamoComprobante) {
       return dao.buscarSacPrstamoDetallesPorIdSacComprobanteyPrestamoGET(idSacComprobante, prestamo, idSacPrestamoComprobante);
    }

    @Override
    public SacPrestamosComprobantesDetalles actualizarPrestamoComprobanteDetalle(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles) {
       return dao.actualizarPrestamoComprobanteDetalleSET(sacPrestamosComprobantesDetalles);
    }
    
     @Override
    public List<SacEstantes> listarCarpetasPorGestionIdSacEstante(Long idSacEstante, Integer gestion) {
        return dao.listarCarpetasPorGestionIdSacEstanteJPQL(idSacEstante, gestion);
    }

    @Override
    public List<SacComprobantesResponse> listarComprobantesEstantesGestion() {
        return dao.listarComprobantesEstantesGestionJPQL();
    }

    @Override
    public List<SacComprobantesResponse> listarComprobantesMesesPorIdSacEstanteGestion(Long idSacEstante, Integer gestion) {
        return dao.listarComprobantesMesesPorIdSacEstanteGestionJPQL(idSacEstante, gestion);
    }

    @Override
    public List<SacComprobantesResponse> listarComprobantesCarpetasPorIdMes(Long idMes, Long idSacEstante) {
        return dao.listarComprobantesCarpetasPorIdMesJPQL(idMes, idSacEstante);
    }

    @Override
    public List<SacComprobantes> listarSacComprobantesPorIdSacCarpeta(Long idSacCarpeta) {
        return dao.listarSacComprobantesPorIdSacCarpetaJPQL(idSacCarpeta);
    }

    @Override
    public List<SacRazonSocial> listarSacRazonSocialPorIdSacCarpeta(Long idSacCarpeta) {
        return dao.listarSacRazonSocialPorIdSacCarpetaJPQL(idSacCarpeta);
    }

    @Override
    public List<SacCompNroCheque> listarNumerosChequesPorIdSacCarpeta(Long idSacCarpeta) {
        return dao.listarNumerosChequesPorIdSacCarpetaJPQL(idSacCarpeta);
    }

    @Override
    public List<SacCompArchivosAdjuntos> listarArchivosAjuntosPorIdSacCarpeta(Long idSacCarpeta) {
        return dao.listarArchivosAjuntosPorIdSacCarpetaJPQL(idSacCarpeta);
    }

    @Override
    public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacCarpeta(Long idSacCarpeta) {
        return dao.listarNumeroComprobantesPorIdSacCarpetaJPQL(idSacCarpeta);
    }
    
     @Override
    public List<SacComprobantes> listarSacComprobantesPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion) {
        return dao.listarSacComprobantesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(idSacTipoPago, idMes, gestion);
    }

    @Override
    public List<SacRazonSocial> listarSacRazonSocialPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion) {
        return dao.listarSacRazonSocialPorIdSacTipoPagoIdMesIdSacEstanteJPQL(idSacTipoPago, idMes, gestion);
    }

    @Override
    public List<SacCompNroCheque> listarNumerosChequesPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion) {
        return dao.listarNumerosChequesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(idSacTipoPago, idMes, gestion);
    }

    @Override
    public List<SacCompArchivosAdjuntos> listarArchivosAjuntosPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion) {
        return dao.listarArchivosAjuntosPorIdSacTipoPagoIdMesIdSacEstanteJPQL(idSacTipoPago, idMes, gestion);
    }

    @Override
    public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion) {
        return dao.listarNumeroComprobantesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(idSacTipoPago, idMes, gestion);
    }

    @Override
    public List<SisMeses> listarMesesComprobantesPorGestion(Integer gestion) {
        return dao.listarMesesComprobantesPorGestionJPQL(gestion);
    }

    @Override
    public SisMeses buscarSisMesesPorIdMes(Long idMes) {
        return dao.buscarSisMesesPorIdMesGET(idMes);
    }

    @Override
    public SacTiposPagos buscarSacTiposPagosPorIdSacTipoPago(Long idSacTipoPago) {
        return dao.buscarSacTiposPagosPorIdSacTipoPagoGET(idSacTipoPago);
    }

    @Override
    public List<SacDevolucionesComprobantesDetalles> listarSacDevolucionComprobantesDetallesPorIdSacDevolucionComprobante(Long idSacDevolucionComprobante) {
     return dao.listarSacDevolucionComprobantesDetallesPorIdSacDevolucionComprobanteJPQL(idSacDevolucionComprobante);
    }
      @Override
    public List<SacComprobantes> listarSacComprobantesPorRazonSocialGestionIdSacTipoComprobante(Long idSacTipoComprobante, String nombres, Integer gestion) {
        return dao.listarSacComprobantesPorRazonSocialGestionIdSacTipoComprobanteJPQL(idSacTipoComprobante, nombres, gestion);
    }

    @Override
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorRazonSocialGestionIdSacTipoComprobante(Long idSacTipoComprobante, String nombres) {
        return dao.listarSacComprobantesResponsePorRazonSocialGestionIdSacTipoComprobanteJPQL(idSacTipoComprobante, nombres);
    }

    @Override
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorSacNroComprobanteIdSacTipoComprobante(Long idSacTipoComprobante, Integer sacNroComprobante) {
        return dao.listarSacComprobantesResponsePorSacNroComprobanteIdSacTipoComprobanteJPQL(idSacTipoComprobante, sacNroComprobante);
    }

    @Override
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorSacNroChequeIdSacTipoComprobante(Long idSacTipoComprobante, Integer sacNroCheque) {
        return dao.listarSacComprobantesResponsePorSacNroChequeIdSacTipoComprobanteJPQL(idSacTipoComprobante, sacNroCheque);
    }

     
}
