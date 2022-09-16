package uap.usic.siga.servicios;

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
 * @author Yessenia
 */
public interface SacArchivoContableServicios {

    public List<SacTiposCarpetas> listarTposCarpetas();

    public void registrarCarpetas(SacCarpetas sacCarpetas);

    public List<SacEstantes> listarEstantes();

    public List<SisMeses> ListarMeses();

    public List<SacCarpetas> listarCarpetas();

    public SacCarpetas buscarCarpetasPorIdSacCarpeta(Long idSacCarpeta);

    public void modificarSacCarpetas(SacCarpetas sacCarpetas);

    public void eliminarSacCarpetas(SacCarpetas sacCarpetas);

    public void registrarEstantes(SacEstantes sacEstantes);

    public void modificarEstantes(SacEstantes sacEstantes);

    public void eliminarEstantes(SacEstantes sacEstantes);

    public void registrarSacTiposCarpetas(SacTiposCarpetas sacTiposCarpetas);

    public SacEstantes buscarSacEstantesPorIdSacEstante(Long idSacEstante);

    public List<SacTiposPagos> listarTiposPagos();

    public List<SacTiposComprobantes> listarTiposComprobantes();

    public void registrarSacComprobante(SacComprobantes sacComprobantes);

    public void registrarSisArchivoAdjunto(SisArchivosAdjuntos sisArchivosAdjuntos);

    public SacComprobantes registrarSacComprobanteDos(SacComprobantes sacComprobantes);

    public SisArchivosAdjuntos registrarSisArchivoAdjuntoDos(SisArchivosAdjuntos sisArchivosAdjuntos);

    public void registrarRazonSocial(SacRazonSocial sacRazonSocial);

    public List<SacCarpetas> listarCarpetasPorIdSacEstante(Long idSacEstante);

    public void registrarSacTiposComprobantes(SacTiposComprobantes sacTiposComprobantes);

    public void registrarSacTiposPagos(SacTiposPagos sacTiposPagos);

    public Personas registrarPersonasSacComprobantes(Personas personas);

    public List<SacComprobantes> listarSacComprobantes(Integer gestion);

    public SacComprobantes buscarSacComprobantesPorIdSacComprobantes(Long idSacComprobante);

    public List<SacRazonSocial> listarPersonasRazonSocialPorIdSacComprobante(Long idSacComprobante);

    public SisArchivosAdjuntos actualizarSisArchivosAdjuntos(SisArchivosAdjuntos sisArchivosAdjuntos);

    public SacComprobantes actualizarSacComprobanteDos(SacComprobantes sacComprobantes);

    public void adjuntarArchivoComprobante(SacCompArchivosAdjuntos sacCompArchivosAdjuntos);

    public SacCarpetas buscarCarpetasPorIdSacComprobante(Long idSacComprobante);

    public SacEstantes buscarEstantesPorIdSacCarpeta(Long idSacCarpeta);

    public SacCompArchivosAdjuntos buscarSacComprobantesArchivoAdjuntoPorIdSacComprobante(Long idSacComprobante);

    public SacCompNroCheque registrarSacCompNroCheque(SacCompNroCheque sacCompNroCheque);

    public SacCompNroComprobante registrarSacCompNroComprobantes(SacCompNroComprobante sacNroComprobante);

    public List<SacCompNroCheque> listarNumeroChequesPorIdSacComprobante(Long idSacComprobante);

    public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacComprobante(Long idSacComprobante);

    public SacCompNroCheque buscarNroChequePorIdSacNroCheque(Long idSacNroCheque);

    public SacCompNroComprobante buscarNroComprobantePorIdNroComprobante(Long idSacCompNroComprobante);

    public SacCompNroComprobante modificarNumerosComprobantes(SacCompNroComprobante sacCompNroComprobante);

    public SacCompNroCheque modificarNumeroCheques(SacCompNroCheque sacCompNroCheque);

    public List<SacComprobantes> listarSacComprobantesGeneral();

    public Personas modificarPersonasRazonSocial(Personas personas);

    public SacCompNroCheque buscarNumeroChequePorIdSacNroCheque(Long idSacCompNroCheque);

    public SacRazonSocial registrarPersonaRazonSocial(SacRazonSocial sacRazonSocial);

    public SacRazonSocial buscarPersonaRazonSocialPorIdSacRazonSocial(Long idSacRazonSocial);

    public List<SacRazonSocial> listarRazonSocialPersonasPorIdSacComprobante(Long idSacComprobante);

    public SacRazonSocial buscarRazonSocialPorIdSacRazonSocial(Long idSacRazonSocial);

    public SacRazonSocial actualizarRazonSocial(SacRazonSocial sacRazonSocial);

    public SacPrestamosComprobantes registrarSacPrestamosComprobantes(SacPrestamosComprobantes sacPrestamosComprobantes);

    public SacPrestamosComprobantes buscarSacPrestamosComprobantesPorIdSacPrestamoComprobante(Long idSacPrestamoComprobante);

    public List<SacPrestamosComprobantes> listarSacPrestamosComprobantes();

    public SacPrestamosComprobantesDetalles registraSacPrestamosComprobantesDetalles(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles);

    public List<SacEstadosComprobantes> listarEstadosComprobantes();

    public List<SacCompNroComprobante> listarNumerosComprobantes();

    public List<SacCompNroCheque> listarNumerosCheques();

    public List<SacRazonSocial> listarSacRazonSocialGeneral();

    // migrar
    public SacCompArchivosAdjuntos modificarSacArchivoAdjuntoComprobanteSET(SacCompArchivosAdjuntos sacCompArchivosAdjuntos);

    public List<SacComprobantes> listarSacPrestamosComprobantesDetallesPorIdSacPrestamoComprobante(Long idSacPrestamoComprobante);

    public List<SacCompArchivosAdjuntos> listarArchivosAjuntosGeneral();

    public SacPrestamosComprobantes modificarPrestamosComprobantes(SacPrestamosComprobantes sacPrestamosComprobantes);

    public SacPrestamosComprobantes eliminarSacPrestamosComprobantes(SacPrestamosComprobantes sacPrestamosComprobantes);

    public List<SacPrestamosComprobantesDetalles> listarSacPrestamosComprobantesDetallesPorIdSacPrestamo(Long idSacPrestamoComprobante);

    public SacPrestamosComprobantesDetalles buscarPrestamosDetallesPorIdSacPrestamoDetalle(Long idSacPrestamoComprobanteDetalle);

    public SacPrestamosComprobantesDetalles eliminarSacPrestamosComprobantesDetalles(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles);

    public List<SacPrestamosComprobantesDetalles> listarPrestamosComprobantesDetallesGeneral();

    public SacEstadosComprobantes buscarSacEstadoComprobantesPorIdSacEstadoComprobante(Long idSacEstadoComprobante);

    //Administrar Devolucion Prestamos
    public SacDevolucionesComprobantes registrarSacDevolucionesComprobantes(SacDevolucionesComprobantes sacDevolucionesComprobantes);

    public SacDevolucionesComprobantesDetalles registrarDevolucionesComprobantesDetalles(SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles);

    public SacDevolucionesComprobantes modificarDevolucionComprobantes(SacDevolucionesComprobantes sacDevolucionesComprobantes);

    public SacDevolucionesComprobantesDetalles modificarDevolucionesComprobantesDetalles(SacDevolucionesComprobantesDetalles sacDevolucionesComprobantesDetalles);

    public SacDevolucionesComprobantes eliminarSacDevolucionesComprobantes(SacDevolucionesComprobantes sacDevolucionesComprobantes);

    public SacDevolucionesComprobantes buscarSacDevolucionesComprobantesPorIdSacDevolucionComprobante(Long idSacDevolucionComprobante);

    public SacDevolucionesComprobantesDetalles buscarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobanteDetalle(Long idSacDevolucionComprobanteDetalle);

    public List<SacDevolucionesComprobantes> listarSacDevolucionesComprobantes();

    public List<SacDevolucionesComprobantesDetalles> listarSacDevolucionesComprobantesDetalles();

    public List<SacComprobantes> listarSacDevolucionesComprobantesDetallesPorIdSacDevolucionComprobante(Long idSacDevolucionComprobante);

    public SacPrestamosComprobantesDetalles buscarSacPrstamoDetallesPorIdSacComprobanteyPrestamo(Long idSacComprobante, Boolean prestamo,Long idSacPrestamoComprobante);

    public SacPrestamosComprobantesDetalles actualizarPrestamoComprobanteDetalle(SacPrestamosComprobantesDetalles sacPrestamosComprobantesDetalles);

    public List<SacEstantes> listarCarpetasPorGestionIdSacEstante(Long idSacEstante, Integer gestion);
     
     public List<SacComprobantesResponse> listarComprobantesEstantesGestion();
     public List<SacComprobantesResponse> listarComprobantesMesesPorIdSacEstanteGestion(Long idSacEstante, Integer gestion);
     public List<SacComprobantesResponse> listarComprobantesCarpetasPorIdMes(Long idMes, Long idSacEstante);
     public List<SacComprobantes> listarSacComprobantesPorIdSacCarpeta(Long idSacCarpeta);
     public List<SacRazonSocial> listarSacRazonSocialPorIdSacCarpeta(Long idSacCarpeta);
     public List<SacCompNroCheque> listarNumerosChequesPorIdSacCarpeta(Long idSacCarpeta);
     public List<SacCompArchivosAdjuntos> listarArchivosAjuntosPorIdSacCarpeta(Long idSacCarpeta);
     public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacCarpeta(Long idSacCarpeta);
     
     public List<SacComprobantes> listarSacComprobantesPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion);
     public List<SacRazonSocial> listarSacRazonSocialPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion);
     public List<SacCompNroCheque> listarNumerosChequesPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion);
     public List<SacCompArchivosAdjuntos> listarArchivosAjuntosPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion);
     public List<SacCompNroComprobante> listarNumeroComprobantesPorIdSacTipoPagoIdMesIdSacEstante(Long idSacTipoPago, Long idMes, Integer gestion);;
     public List<SisMeses> listarMesesComprobantesPorGestion(Integer gestion);
     public SisMeses buscarSisMesesPorIdMes(Long idMes);
     public SacTiposPagos buscarSacTiposPagosPorIdSacTipoPago(Long idSacTipoPago);
     
    public List<SacDevolucionesComprobantesDetalles> listarSacDevolucionComprobantesDetallesPorIdSacDevolucionComprobante(Long idSacDevolucionComprobante);
   
    // ================ Modulo de Busqueda de Comprobantes  ===============    
    public List<SacComprobantes> listarSacComprobantesPorRazonSocialGestionIdSacTipoComprobante(Long idSacTipoComprobante, String nombres, Integer gestion);
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorRazonSocialGestionIdSacTipoComprobante(Long idSacTipoComprobante, String nombres);
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorSacNroComprobanteIdSacTipoComprobante(Long idSacTipoComprobante, Integer sacNroComprobante);
    public List<SacComprobantesResponse> listarSacComprobantesResponsePorSacNroChequeIdSacTipoComprobante(Long idSacTipoComprobante, Integer sacNroCheque);

}
