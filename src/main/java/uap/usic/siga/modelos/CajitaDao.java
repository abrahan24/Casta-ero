package uap.usic.siga.modelos;

import java.util.Date;
import java.util.List;
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

/**
 * Rectorado - USIC Fecha: 2019-03-25
 *
 * @author Freddy Morales
 */
public interface CajitaDao {

    public List<CjaTiposGastos> listarTiposGastosGET();

    public List<CjaTiposIngresos> listarTiposIngresosGET();

    public void registrarIngresosFondosSET(CjaIngresos cajaIngresos);

    public List<CjaIngresos> listarIngresosPorIdPersonaJPQL(Long idPersona);

    public CjaIngresos buscarCjaIngresosFondosPorIdPersonaGestionPeriodoIdEstadoGET(Long idPersona, Integer gestion, Integer periodo, String idEstado);

    public List<CjaGastosEjecutados> listarCjaGastosEjecutadosPorIdCjaIngresoIdUsuarioJPQL(Long idCjaIngreso, Long idUsuario);

    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado);

    public void actualizarEstadoCajaIngresosSET(CjaIngresos cajaIngresos);

    public CjaTiposGastos buscarCajaTiposGastosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado);

    public CjaIngresos buscarCajaIngresosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado);

    public CjaGastosEjecutadosResponse buscarCajasGastosEjecutadosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado);

    public List<CjaGastosEjecutados> listarGastoEjecutadosPorIdUsuarioFecIngresoFecPagoJPQL(Long idUsuario, Long idCjaIngreso, Date fecIngreso, Date fecGasto);

    public CjaIngresos buscarCjaIngresosFondosPorIdPersonaIdEstadoGET(Long idPersona, String idEstado);

    public CjaIngresos buscarCjaIngresoPorIdCjaIngresoGET(Long idCjaIngreso);

    public void eliminarIngresosFondosSET(CjaIngresos cjaIngresos);

    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdPersonaGET(Long idPersona);

    public CjaIngresos buscarCjaIngresoPorIdCjaIngresoIdEstadoIGET(Long idCjaIngreso, Long idPersona);

    //Ing Freddy
    public List<CjaProveedores> listarCajitaProveedoresJPQL();

    public CjaIngresos buscarCajitaSaldoIngresosPorIdPersonaGET(String idEstado, Integer gestion, Integer periodo, Long idPersona);

    public CjaIngresosResponse buscarSaldoIngresosGET(String idEstado, Integer gestion, Integer periodo, Long idPersona);

    public void insertarCjaGastosEjecutadosSET(CjaGastosEjecutados cjaGastosEjecutados);

    public List<CjaGastosEjecutados> listarGastosEjecutadosGestionPeriodoIdUsuarioJPQL(Integer periodo, Integer gestion, Long idUsuario);

    public CjaGastosEjecutados buscarCjaGastoEjecutadosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado);

    public void modificarCjaGastosEjecutadosSET(CjaGastosEjecutados cjaGastosEjecutados);

    public void modificarCajaIngresosSaldoSET(CjaIngresos cjaIngresos);

    public CjaIngresos buscarCjaIngresosPorIdCjaIngresoGET(Long idCjaIngreso);

    public void registrarTiposIngresosSET(CjaTiposIngresos cjaTiposIngresos);

    public CjaIngresos buscarCajitIngresosPorIdPersonaTipoIngresoGET(String idEstado, Integer gestion, Integer periodo, Long idPersona, Long idCjaTipoIngreso);

    public Long buscarCjaGastosEjecutadosPorIdCjaIngresoIdEstadoGET(Long idCjaIngreso, String idEstado);

    public void registarCjaProveedoresSET(CjaProveedores cjaProveedores);

    public List<CjaTiposClasificaciones> listarCjaTiposClasificacionesJPQL();

    public void registrarTiposGastosSET(CjaTiposGastos cjaTiposGastos);

    public List<CjaGastosClasificaciones> listarCjaGastosClasificacionesJPQL();

    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionResponseIdUsuarioIdCjaIngresoJPQL(Long idUsuario, Long idCjaIngreso,Date fecIngreso,Date fecGasto);

    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionResponseIdUsuarioIdCajaIngresoJPQL(Long idUsuario, Long idCjaIngreso);

    public List<CjaGastosEjecutados> listarGastosPorIdPersonaGestionPeriodoIdUsuarioJPQL(Long idPersona, Integer gestion, Integer periodo, Long idUsuario,Long idCjaIngreso);

    public List<CjaIngresos> listarCjaIngresosPorIdUnidadFuncionalGestionJPQL(Long idUnidadFuncional, Integer gestion);

    public List<CjaGastosEjecutados> listarGastoEjecutadosPorIdCajaIngresoFecIngresoFecPagoJPQL(Long idCjaIngreso, Date fecIngreso, Date fecGasto);

    public List<CjaUnidadesFuncionalesResponse> listarTotalesGastosPOrUnidadesFuncionalesJPQL(Long idDireccionFuncional, Integer gestion);

    public List<CjaDireccionesFuncionalesResponse> listarTotalesGastosPorDireccionesFuncionalesJPQL(Long idSede, Integer gestion);

    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionResponsePorIdCjaIngresoJPQL(Long idCjaIngreso);

    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionResponsePorIdCajaIngresoJPQL(Long idCjaIngreso);

    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorTipoClasificacionUnidadFuncionalJPQL(Long idUnidadFuncional, Integer gestion);

    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorPartidasDireccionesFuncionalesJPQL(Long idDireccionFuncional, Integer gestion);

    public List<CjaGastosClasificaciones> listarCjaGastosClasificacionesPorIdCjaTipoClasificacionJPQL(Long idCjaTipoClasificacion);

    public List<CjaTiposClasificacionesResponse> listarTotalesGastosGeneralesPorPartidaSedeJPQL(Long idSede, Integer gestion);

    public List<CjaTiposClasificacionesResponse> listarTotalesGastosGeneralesPorPartidaSedeTotalesJPQL(Long idSede, Integer gestion);

    public List<CjaTiposClasificacionesResponse> listarMOntoMaximoPorIdSedeJPQL(Long idSede, Integer gestion);

    // ================== ultimo ajustes
    public CjaIngresos buscarCjaIngresosFondosRetornaMaximoIdCajaIngresoIdPersonaGET(Long idPersona, String idEstado);

    public List<CjaGastosEjecutados> listarGastosEjecutadosGestionPeriodoIdUsuarioIdCjaIngresoJPQL(Integer periodo, Integer gestion, Long idUsuario);

}
