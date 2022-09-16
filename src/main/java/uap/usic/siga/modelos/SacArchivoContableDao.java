package uap.usic.siga.modelos;

import java.util.List;
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

/**
 *
 * @author Yessenia Velasco
 */
public interface SacArchivoContableDao {

    public List<SacTiposCarpetas> listarTposCarpetasJPQL();

    public void registrarCarpetasSET(SacCarpetas sacCarpetas);

    public List<SacEstantes> listarEstantesJPQL();

    public List<SisMeses> ListarMesesJPQL();

    public List<SacCarpetas> listarCarpetasJPQL();

    public SacCarpetas buscarCarpetasPorIdSacCarpetaGET(Long idSacCarpeta);

    public void modificarSacCarpetasSET(SacCarpetas sacCarpetas);

    public void eliminarSacCarpetasSET(SacCarpetas sacCarpetas);

    public void registrarEstantesSET(SacEstantes sacEstantes);

    public void modificarEstantesSET(SacEstantes sacEstantes);

    public void eliminarEstantesSET(SacEstantes sacEstantes);

    public void registrarSacTiposCarpetasSET(SacTiposCarpetas sacTiposCarpetas);

    public SacEstantes buscarSacEstantesPorIdSacEstanteGET(Long idSacEstante);

    public List<SacTiposPagos> listarTiposPagosJQPL();

    public List<SacTiposComprobantes> listarTiposComprobantesJQPL();

    public void registrarSacComprobanteSET(SacComprobantes sacComprobantes);

    public void registrarSisArchivoAdjuntoSET(SisArchivosAdjuntos sisArchivosAdjuntos);

    public SacComprobantes registrarSacComprobanteDosSET(SacComprobantes sacComprobantes);

    public SisArchivosAdjuntos registrarSisArchivoAdjuntoDosSET(SisArchivosAdjuntos sisArchivosAdjuntos);

    public void registrarRazonSocialSET(SacRazonSocial sacRazonSocial);

    public List<SacCarpetas> listarCarpetasPorIdSacEstanteJPQL(Long idSacEstante);

    public void registrarSacTiposComprobantesSET(SacTiposComprobantes sacTiposComprobantes);

    public void registrarSacTiposPagosSET(SacTiposPagos sacTiposPagos);

    public Personas registrarPersonasSacComprobantesSET(Personas personas);

    public List<SacComprobantes> listarSacComprobantesJPQL(Integer gestion);

    public SacComprobantes buscarSacComprobantesPorIdSacComprobantesGET(Long idSacComprobante);

    public List<SacRazonSocial> listarPersonasRazonSocialPorIdSacComprobanteJPQL(Long idSacComprobante);

    public SisArchivosAdjuntos actualizarSisArchivosAdjuntosSET(SisArchivosAdjuntos sisArchivosAdjuntos);

    public SacComprobantes actualizarSacComprobanteDosSET(SacComprobantes sacComprobantes);

    public void adjuntarArchivoComprobanteSET(SacCompArchivosAdjuntos sacCompArchivosAdjuntos);

    public SacCarpetas buscarCarpetasPorIdSacComprobanteGET(Long idSacComprobante);

    public SacEstantes buscarEstantesPorIdSacCarpetaGET(Long idSacCarpeta);

    public SacCompArchivosAdjuntos buscarSacComprobantesArchivoAdjuntoPorIdSacComprobanteGET(Long idSacComprobante);

    public SacCompNroCheque registrarSacCompNroChequeSET(SacCompNroCheque sacCompNroCheque);

    public SacCompNroComprobante registrarSacCompNroComprobantesSET(SacCompNroComprobante sacNroComprobante);

    public List<SacCompNroCheque> listarNumeroChequesPorIdSacComprobanteJPQL(Long idSacComprobante);

    public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacComprobanteJPQL(Long idSacComprobante);

    public SacCompNroCheque buscarNroChequePorIdSacNroChequeGET(Long idSacNroCheque);

    public SacCompNroComprobante buscarNroComprobantePorIdNroComprobanteGET(Long idSacCompNroComprobante);

    public SacCompNroComprobante modificarNumerosComprobantesSET(SacCompNroComprobante sacCompNroComprobante);

    public SacCompNroCheque modificarNumeroChequesSET(SacCompNroCheque sacCompNroCheque);

    public List<SacComprobantes> listarSacComprobantesGeneralJPQL();

    public Personas modificarPersonasRazonSocialSET(Personas personas);

    public SacCompNroCheque buscarNumeroChequePorIdSacNroChequeGET(Long idSacCompNroCheque);

    public SacRazonSocial registrarPersonaRazonSocialSET(SacRazonSocial sacRazonSocial);

    public SacRazonSocial buscarPersonaRazonSocialPorIdSacRazonSocialGET(Long idSacRazonSocial);

    public List<SacRazonSocial> listarRazonSocialPersonasPorIdSacComprobanteJPQL(Long idSacComprobante);

    public SacRazonSocial buscarRazonSocialPorIdSacRazonSocialGET(Long idSacRazonSocial);

    public SacRazonSocial actualizarRazonSocialSET(SacRazonSocial sacRazonSocial);

    public SacPrestamosComprobantes registrarSacPrestamosComprobantesSET(SacPrestamosComprobantes sacPrestamosComprobantes);

    public SacPrestamosComprobantes buscarSacPrestamosComprobantesPorIdSacPrestamoComprobanteGET(Long idSacPrestamoComprobante);

    public List<SacPrestamosComprobantes> listarSacPrestamosComprobantesJPQL();

    public SacPrestamosComprobantesDetalles registraSacPrestamosComprobantesDetallesSET(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles);

    public List<SacEstadosComprobantes> listarEstadosComprobantesJPQL();

    public List<SacCompNroComprobante> listarNumerosComprobantesJPQL();

    public List<SacCompNroCheque> listarNumerosChequesJPQL();

    public List<SacRazonSocial> listarSacRazonSocialGeneralJPQL();

    // migrar
    public SacCompArchivosAdjuntos modificarSacArchivoAdjuntoComprobanteSET(SacCompArchivosAdjuntos sacCompArchivosAdjuntos);

    public List<SacComprobantes> listarSacPrestamosComprobantesDetallesPorIdSacPrestamoComprobanteJPQL(Long idSacPrestamoComprobante);

    public List<SacCompArchivosAdjuntos> listarArchivosAjuntosGeneralJPQL();

    public SacPrestamosComprobantes modificarPrestamosComprobantesSET(SacPrestamosComprobantes sacPrestamosComprobantes);

    public SacPrestamosComprobantes eliminarSacPrestamosComprobantesSET(SacPrestamosComprobantes sacPrestamosComprobantes);

    public List<SacPrestamosComprobantesDetalles> listarSacPrestamosComprobantesDetallesPorIdSacPrestamoJPQL(Long idSacPrestamoComprobante);

    public SacPrestamosComprobantesDetalles buscarPrestamosDetallesPorIdSacPrestamoDetalleGET(Long idSacPrestamoComprobanteDetalle);

    public SacPrestamosComprobantesDetalles eliminarSacPrestamosComprobantesDetallesSET(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles);

    public List<SacPrestamosComprobantesDetalles> listarPrestamosComprobantesDetallesGeneralJPQL();

    public SacEstadosComprobantes buscarSacEstadoComprobantesPorIdSacEstadoComprobanteJPQL(Long idSacEstadoComprobante);

    //Administrar Devolucion Prestamos
    public SacDevolucionesComprobantes registrarSacDevolucionesComprobantesSET(SacDevolucionesComprobantes sacDevolucionesComprobantes);

    public SacDevolucionesComprobantesDetalles registrarDevolucionesComprobantesDetallesSET(SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles);

    public SacDevolucionesComprobantes modificarDevolucionComprobantesSET(SacDevolucionesComprobantes sacDevolucionesComprobantes);

    public SacDevolucionesComprobantesDetalles modificarDevolucionesComprobantesDetallesSET(SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles);

    public SacDevolucionesComprobantes eliminarSacDevolucionesComprobantesSET(SacDevolucionesComprobantes sacDevolucionesComprobantes);
    
    public SacDevolucionesComprobantes buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobanteGET(Long idSacDevolucionComprobante);
    
    public SacDevolucionesComprobantesDetalles buscarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobanteDetalleGET(Long idSacDevolucionComprobanteDetalle);
    
    public List<SacDevolucionesComprobantes> listarSacDevolucionesComprobantesJPQL();
    
    public List<SacDevolucionesComprobantesDetalles> listarSacDevolucionesComprobantesDetallesJPQL();
    
    public List<SacComprobantes> listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobanteJPQL(Long idSacDevolucionComprobante);
    
    public SacPrestamosComprobantesDetalles buscarSacPrstamoDetallesPorIdSacComprobanteyPrestamoGET(Long idSacComprobante,  Boolean prestamo,Long idSacPrestamoComprobante);
    
    public SacPrestamosComprobantesDetalles actualizarPrestamoComprobanteDetalleSET(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles);
    
     //========= Migrar 
     public List<SacEstantes> listarCarpetasPorGestionIdSacEstanteJPQL(Long idSacEstante, Integer gestion);
     public List<SacComprobantesResponse> listarComprobantesEstantesGestionJPQL();
     public List<SacComprobantesResponse> listarComprobantesMesesPorIdSacEstanteGestionJPQL(Long idSacEstante, Integer gestion);
     public List<SacComprobantesResponse> listarComprobantesCarpetasPorIdMesJPQL(Long idMes, Long idSacEstante);
     public List<SacComprobantes> listarSacComprobantesPorIdSacCarpetaJPQL(Long idSacCarpeta);
     public List<SacRazonSocial> listarSacRazonSocialPorIdSacCarpetaJPQL(Long idSacCarpeta);
     public List<SacCompNroCheque> listarNumerosChequesPorIdSacCarpetaJPQL(Long idSacCarpeta);
     public List<SacCompArchivosAdjuntos> listarArchivosAjuntosPorIdSacCarpetaJPQL(Long idSacCarpeta);
     public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacCarpetaJPQL(Long idSacCarpeta);
    
     //----------------
      public List<SacComprobantes> listarSacComprobantesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion);
     public List<SacRazonSocial> listarSacRazonSocialPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion);
     public List<SacCompNroCheque> listarNumerosChequesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion);
     public List<SacCompArchivosAdjuntos> listarArchivosAjuntosPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion);
     public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacTipoPagoIdMesIdSacEstanteJPQL(Long idSacTipoPago, Long idMes, Integer gestion);;
     public List<SisMeses> listarMesesComprobantesPorGestionJPQL(Integer gestion);
     public SisMeses buscarSisMesesPorIdMesGET(Long idMes);
     public SacTiposPagos buscarSacTiposPagosPorIdSacTipoPagoGET(Long idSacTipoPago);
   
    public List<SacDevolucionesComprobantesDetalles> listarSacDevolucionComprobantesDetallesPorIdSacDevolucionComprobanteJPQL(Long idSacDevolucionComprobante);

     // ===================== Modulo Busqueda =============
     
     public List<SacComprobantes> listarSacComprobantesPorRazonSocialGestionIdSacTipoComprobanteJPQL(Long idSacTipoComprobante, String nombres, Integer gestion);
     public List<SacComprobantesResponse> listarSacComprobantesResponsePorRazonSocialGestionIdSacTipoComprobanteJPQL(Long idSacTipoComprobante, String nombres);
     public List<SacComprobantesResponse> listarSacComprobantesResponsePorSacNroComprobanteIdSacTipoComprobanteJPQL(Long idSacTipoComprobante, Integer sacNroComprobante);
     public List<SacComprobantesResponse> listarSacComprobantesResponsePorSacNroChequeIdSacTipoComprobanteJPQL(Long idSacTipoComprobante, Integer sacNroCheque);
   
   
}
