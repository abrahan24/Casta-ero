package uap.usic.siga.servicios.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.dto.CjaDireccionesFuncionalesResponse;
import uap.usic.siga.dto.CjaGastosClasificacionesResponse;
import uap.usic.siga.dto.CjaGastosEjecutadosResponse;
import uap.usic.siga.dto.CjaIngresosResponse;
import uap.usic.siga.dto.CjaTiposClasificacionesResponse;
import uap.usic.siga.dto.CjaUnidadesFuncionalesResponse;
import uap.usic.siga.entidades.CjaGastosClasificaciones;
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.CjaProveedores;
import uap.usic.siga.entidades.CjaTiposClasificaciones;
import uap.usic.siga.entidades.CjaTiposGastos;
import uap.usic.siga.entidades.CjaTiposIngresos;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.modelos.CajitaDao;
import uap.usic.siga.servicios.CajitaServicios;

/**
 * Rectorado - USIC Fecha: 2019-03-25
 *
 * @author Freddy Morales
 */
@Service("cajitaServicio")
@Transactional
public class CajitaServiciosImpl implements CajitaServicios {

    @Autowired
    private CajitaDao dao;

    @Override
    public List<CjaTiposGastos> listarTiposGastos() {
        return dao.listarTiposGastosGET();
    }

    @Override
    public List<CjaTiposIngresos> listarTiposIngresos() {
        return dao.listarTiposIngresosGET();
    }

    @Override
    public void registrarIngresosFondos(CjaIngresos cajaIngresos) {
        dao.registrarIngresosFondosSET(cajaIngresos);
    }

    @Override
    public List<CjaIngresos> listarIngresosPorIdPersona(Long idPersona) {
        return dao.listarIngresosPorIdPersonaJPQL(idPersona);
    }

    @Override
    public CjaIngresos buscarCjaIngresosFondosPorIdPersonaGestionPeriodoIdEstado(Long idPersona, Integer gestion, Integer periodo, String idEstado) {
        return dao.buscarCjaIngresosFondosPorIdPersonaGestionPeriodoIdEstadoGET(idPersona, gestion, periodo, idEstado);
    }

    @Override
    public List<CjaGastosEjecutados> listarCjaGastosEjecutadosPorIdCjaIngresoIdUsuario(Long idCjaIngreso, Long idUsuario) {
        return dao.listarCjaGastosEjecutadosPorIdCjaIngresoIdUsuarioJPQL(idCjaIngreso, idUsuario);
    }

    @Override
    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado) {
        return dao.buscarCjaGastosEjecutadosPorIdCjaGastoEjecutadoGET(idCjaGastoEjecutado);
    }

    @Override
    public void actualizarEstadoCajaIngresos(CjaIngresos cajaIngresos) {
        dao.actualizarEstadoCajaIngresosSET(cajaIngresos);
    }

    @Override
    public CjaTiposGastos buscarCajaTiposGastosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado) {
        return dao.buscarCajaTiposGastosPorIdCjaGastoEjecutadoGET(idCjaGastoEjecutado);
    }

    @Override
    public CjaIngresos buscarCajaIngresosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado) {
        return dao.buscarCajaIngresosPorIdCjaGastoEjecutadoGET(idCjaGastoEjecutado);
    }

    @Override
    public CjaGastosEjecutadosResponse buscarCajasGastosEjecutadosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado) {
        return dao.buscarCajasGastosEjecutadosPorIdCjaGastoEjecutadoGET(idCjaGastoEjecutado);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastoEjecutadosPorIdUsuarioFecIngresoFecPago(Long idUsuario, Long idCjaIngreso, Date fecIngreso, Date fecGasto) {
        return dao.listarGastoEjecutadosPorIdUsuarioFecIngresoFecPagoJPQL(idUsuario, idCjaIngreso, fecIngreso, fecGasto);
    }

    @Override
    public CjaIngresos buscarCjaIngresosFondosPorIdPersonaIdEstado(Long idPersona, String idEstado) {
        return dao.buscarCjaIngresosFondosPorIdPersonaIdEstadoGET(idPersona, idEstado);
    }

    @Override
    public CjaIngresos buscarCjaIngresoPorIdCjaIngreso(Long idCjaIngreso) {
        return dao.buscarCjaIngresoPorIdCjaIngresoGET(idCjaIngreso);
    }

    @Override
    public void eliminarIngresosFondos(CjaIngresos cjaIngresos) {
        dao.eliminarIngresosFondosSET(cjaIngresos);
    }

    @Override
    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdPersona(Long idPersona) {
        return dao.buscarPersonalAdministrativoPorIdPersonaGET(idPersona);
    }

    @Override
    public CjaIngresos buscarCjaIngresoPorIdCjaIngresoIdEstadoI(Long idCjaIngreso, Long idPersona) {
        return dao.buscarCjaIngresoPorIdCjaIngresoIdEstadoIGET(idCjaIngreso, idPersona);
    }

    @Override
    public List<CjaProveedores> listarCajitaProveedores() {
        return dao.listarCajitaProveedoresJPQL();
    }

    @Override
    public CjaIngresos buscarCajitaSaldoIngresosPorIdPersona(String idEstado, Integer gestion, Integer periodo, Long idPersona) {
        return dao.buscarCajitaSaldoIngresosPorIdPersonaGET(idEstado, gestion, periodo, idPersona);
    }

    @Override
    public CjaIngresosResponse buscarSaldoIngresos(String idEstado, Integer gestion, Integer periodo, Long idPersona) {
        return dao.buscarSaldoIngresosGET(idEstado, gestion, periodo, idPersona);
    }

    @Override
    public void insertarCjaGastosEjecutados(CjaGastosEjecutados cjaGastosEjecutados) {
        dao.insertarCjaGastosEjecutadosSET(cjaGastosEjecutados);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosEjecutadosGestionPeriodoIdUsuario(Integer periodo, Integer gestion, Long idUsuario) {
        return dao.listarGastosEjecutadosGestionPeriodoIdUsuarioJPQL(periodo, gestion, idUsuario);
    }

    @Override
    public CjaGastosEjecutados buscarCjaGastoEjecutadosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado) {
        return dao.buscarCjaGastoEjecutadosPorIdCjaGastoEjecutadoGET(idCjaGastoEjecutado);
    }

    @Override
    public void modificarCjaGastosEjecutados(CjaGastosEjecutados cjaGastosEjecutados) {
        dao.modificarCjaGastosEjecutadosSET(cjaGastosEjecutados);
    }

    @Override
    public void modificarCajaIngresosSaldo(CjaIngresos cjaIngresos) {
        dao.modificarCajaIngresosSaldoSET(cjaIngresos);
    }

    @Override
    public CjaIngresos buscarCjaIngresosPorIdCjaIngreso(Long idCjaIngreso) {
        return dao.buscarCjaIngresosPorIdCjaIngresoGET(idCjaIngreso);
    }

    @Override
    public void registrarTiposIngresos(CjaTiposIngresos cjaTiposIngresos) {
        dao.registrarTiposIngresosSET(cjaTiposIngresos);
    }

    @Override
    public CjaIngresos buscarCajitIngresosPorIdPersonaTipoIngreso(String idEstado, Integer gestion, Integer periodo, Long idPersona, Long idCjaTipoIngreso) {
        return dao.buscarCajitIngresosPorIdPersonaTipoIngresoGET(idEstado, gestion, periodo, idPersona, idCjaTipoIngreso);
    }

    @Override
    public Long buscarCjaGastosEjecutadosPorIdCjaIngresoIdEstado(Long idCjaIngreso, String idEstado) {
        return dao.buscarCjaGastosEjecutadosPorIdCjaIngresoIdEstadoGET(idCjaIngreso, idEstado);
    }

    @Override
    public void registarCjaProveedores(CjaProveedores cjaProveedores) {
        dao.registarCjaProveedoresSET(cjaProveedores);
    }

    @Override
    public List<CjaTiposClasificaciones> listarCjaTiposClasificaciones() {
        return dao.listarCjaTiposClasificacionesJPQL();
    }

    @Override
    public void registrarTiposGastos(CjaTiposGastos cjaTiposGastos) {
        dao.registrarTiposGastosSET(cjaTiposGastos);
    }

    @Override
    public List<CjaGastosClasificaciones> listarCjaGastosClasificaciones() {
        return dao.listarCjaGastosClasificacionesJPQL();
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionResponseIdUsuarioIdCjaIngreso(Long idUsuario, Long idCjaIngreso,Date fecIngreso,Date fecGasto) {
        return dao.listarTiposClasificacionResponseIdUsuarioIdCjaIngresoJPQL(idUsuario, idCjaIngreso,fecIngreso,fecGasto);
    }

    @Override
    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionResponseIdUsuarioIdCajaIngreso(Long idUsuario, Long idCjaIngreso) {
        return dao.listarGastosClasificacionResponseIdUsuarioIdCajaIngresoJPQL(idUsuario, idCjaIngreso);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosPorIdPersonaGestionPeriodoIdUsuario(Long idPersona, Integer gestion, Integer periodo, Long idUsuario,Long idCjaIngreso) {
        return dao.listarGastosPorIdPersonaGestionPeriodoIdUsuarioJPQL(idPersona, gestion, periodo, idUsuario, idCjaIngreso);
    }

    @Override
    public List<CjaIngresos> listarCjaIngresosPorIdUnidadFuncionalGestion(Long idUnidadFuncional, Integer gestion) {
        return dao.listarCjaIngresosPorIdUnidadFuncionalGestionJPQL(idUnidadFuncional, gestion);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastoEjecutadosPorIdCajaIngresoFecIngresoFecPago(Long idCjaIngreso, Date fecIngreso, Date fecGasto) {
        return dao.listarGastoEjecutadosPorIdCajaIngresoFecIngresoFecPagoJPQL(idCjaIngreso, fecIngreso, fecGasto);
    }

    @Override
    public List<CjaUnidadesFuncionalesResponse> listarTotalesGastosPOrUnidadesFuncionales(Long idDireccionFuncional, Integer gestion) {
        return dao.listarTotalesGastosPOrUnidadesFuncionalesJPQL(idDireccionFuncional, gestion);
    }

    @Override
    public List<CjaDireccionesFuncionalesResponse> listarTotalesGastosPorDireccionesFuncionales(Long idSede, Integer gestion) {
        return dao.listarTotalesGastosPorDireccionesFuncionalesJPQL(idSede, gestion);
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionResponsePorIdCjaIngreso(Long idCjaIngreso) {
        return dao.listarTiposClasificacionResponsePorIdCjaIngresoJPQL(idCjaIngreso);
    }

    @Override
    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionResponsePorIdCajaIngreso(Long idCjaIngreso) {
        return dao.listarGastosClasificacionResponsePorIdCajaIngresoJPQL(idCjaIngreso);
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorTipoClasificacionUnidadFuncional(Long idUnidadFuncional, Integer gestion) {
        return dao.listarTotalesGastosPorTipoClasificacionUnidadFuncionalJPQL(idUnidadFuncional, gestion);
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorPartidasDireccionesFuncionales(Long idDireccionFuncional, Integer gestion) {
        return dao.listarTotalesGastosPorPartidasDireccionesFuncionalesJPQL(idDireccionFuncional, gestion);
    }

    @Override
    public List<CjaGastosClasificaciones> listarCjaGastosClasificacionesPorIdCjaTipoClasificacion(Long idCjaTipoClasificacion) {
        return dao.listarCjaGastosClasificacionesPorIdCjaTipoClasificacionJPQL(idCjaTipoClasificacion);
    }
     @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosGeneralesPorPartidaSedeJPQL(Long idSede, Integer gestion) {
        return dao.listarTotalesGastosGeneralesPorPartidaSedeJPQL(idSede, gestion);
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosGeneralesPorPartidaSedeTotales(Long idSede, Integer gestion) {
        return dao.listarTotalesGastosGeneralesPorPartidaSedeTotalesJPQL(idSede, gestion);
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarMOntoMaximoPorIdSede(Long idSede, Integer gestion) {
        return dao.listarMOntoMaximoPorIdSedeJPQL(idSede, gestion);
    }
  @Override
    public CjaIngresos buscarCjaIngresosFondosRetornaMaximoIdCajaIngresoIdPersona(Long idPersona, String idEstado) {
        return dao.buscarCjaIngresosFondosRetornaMaximoIdCajaIngresoIdPersonaGET(idPersona, idEstado);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosEjecutadosGestionPeriodoIdUsuarioIdCjaIngreso(Integer periodo, Integer gestion, Long idUsuario) {
        return dao.listarGastosEjecutadosGestionPeriodoIdUsuarioIdCjaIngresoJPQL(periodo, gestion, idUsuario);
    }
}
