package uap.usic.siga.servicios;

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
public interface CajitaServicios {

    public List<CjaTiposGastos> listarTiposGastos();

    public List<CjaTiposIngresos> listarTiposIngresos();

    public void registrarIngresosFondos(CjaIngresos cajaIngresos);

    public List<CjaIngresos> listarIngresosPorIdPersona(Long idPersona);

    public CjaIngresos buscarCjaIngresosFondosPorIdPersonaGestionPeriodoIdEstado(Long idPersona, Integer gestion, Integer periodo, String idEstado);

    public List<CjaGastosEjecutados> listarCjaGastosEjecutadosPorIdCjaIngresoIdUsuario(Long idCjaIngreso, Long idUsuario);

    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado);

    public void actualizarEstadoCajaIngresos(CjaIngresos cajaIngresos);

    public CjaTiposGastos buscarCajaTiposGastosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado);

    public CjaIngresos buscarCajaIngresosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado);

    public CjaGastosEjecutadosResponse buscarCajasGastosEjecutadosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado);

    public List<CjaGastosEjecutados> listarGastoEjecutadosPorIdUsuarioFecIngresoFecPago(Long idUsuario, Long idCjaIngreso, Date fecIngreso, Date fecGasto);

    public CjaIngresos buscarCjaIngresosFondosPorIdPersonaIdEstado(Long idPersona, String idEstado);

    public CjaIngresos buscarCjaIngresoPorIdCjaIngreso(Long idCjaIngreso);

    public void eliminarIngresosFondos(CjaIngresos cjaIngresos);

    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdPersona(Long idPersona);

    public CjaIngresos buscarCjaIngresoPorIdCjaIngresoIdEstadoI(Long idCjaIngreso, Long idPersona);

    // Ing. Fredy Metodos
    public List<CjaProveedores> listarCajitaProveedores();

    public CjaIngresos buscarCajitaSaldoIngresosPorIdPersona(String idEstado, Integer gestion, Integer periodo, Long idPersona);

    public CjaIngresosResponse buscarSaldoIngresos(String idEstado, Integer gestion, Integer periodo, Long idPersona);

    public void insertarCjaGastosEjecutados(CjaGastosEjecutados cjaGastosEjecutados);

    public List<CjaGastosEjecutados> listarGastosEjecutadosGestionPeriodoIdUsuario(Integer periodo, Integer gestion, Long idUsuario);

    public CjaGastosEjecutados buscarCjaGastoEjecutadosPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado);

    public void modificarCjaGastosEjecutados(CjaGastosEjecutados cjaGastosEjecutados);

    public void modificarCajaIngresosSaldo(CjaIngresos cjaIngresos);

    public CjaIngresos buscarCjaIngresosPorIdCjaIngreso(Long idCjaIngreso);

    public void registrarTiposIngresos(CjaTiposIngresos cjaTiposIngresos);

    public CjaIngresos buscarCajitIngresosPorIdPersonaTipoIngreso(String idEstado, Integer gestion, Integer periodo, Long idPersona, Long idCjaTipoIngreso);

    public Long buscarCjaGastosEjecutadosPorIdCjaIngresoIdEstado(Long idCjaIngreso, String idEstado);

    public void registarCjaProveedores(CjaProveedores cjaProveedores);

    public List<CjaTiposClasificaciones> listarCjaTiposClasificaciones();

    public void registrarTiposGastos(CjaTiposGastos cjaTiposGastos);

    public List<CjaGastosClasificaciones> listarCjaGastosClasificaciones();

    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionResponseIdUsuarioIdCjaIngreso(Long idUsuario, Long idCjaIngreso,Date fecIngreso,Date fecGasto);

    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionResponseIdUsuarioIdCajaIngreso(Long idUsuario, Long idCjaIngreso);

    public List<CjaGastosEjecutados> listarGastosPorIdPersonaGestionPeriodoIdUsuario(Long idPersona, Integer gestion, Integer periodo, Long idUsuario,Long idCjaIngreso);

    public List<CjaIngresos> listarCjaIngresosPorIdUnidadFuncionalGestion(Long idUnidadFuncional, Integer gestion);

    public List<CjaGastosEjecutados> listarGastoEjecutadosPorIdCajaIngresoFecIngresoFecPago(Long idCjaIngreso, Date fecIngreso, Date fecGasto);

    public List<CjaUnidadesFuncionalesResponse> listarTotalesGastosPOrUnidadesFuncionales(Long idDireccionFuncional, Integer gestion);

    public List<CjaDireccionesFuncionalesResponse> listarTotalesGastosPorDireccionesFuncionales(Long idSede, Integer gestion);

    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionResponsePorIdCjaIngreso(Long idCjaIngreso);

    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionResponsePorIdCajaIngreso(Long idCjaIngreso);

    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorTipoClasificacionUnidadFuncional(Long idUnidadFuncional, Integer gestion);

    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorPartidasDireccionesFuncionales(Long idDireccionFuncional, Integer gestion);

    public List<CjaGastosClasificaciones> listarCjaGastosClasificacionesPorIdCjaTipoClasificacion(Long idCjaTipoClasificacion);

    public List<CjaTiposClasificacionesResponse> listarTotalesGastosGeneralesPorPartidaSedeJPQL(Long idSede, Integer gestion);

    public List<CjaTiposClasificacionesResponse> listarTotalesGastosGeneralesPorPartidaSedeTotales(Long idSede, Integer gestion);

    public List<CjaTiposClasificacionesResponse> listarMOntoMaximoPorIdSede(Long idSede, Integer gestion);
    
  // actualizar
    public CjaIngresos buscarCjaIngresosFondosRetornaMaximoIdCajaIngresoIdPersona(Long idPersona, String idEstado);
    public List<CjaGastosEjecutados> listarGastosEjecutadosGestionPeriodoIdUsuarioIdCjaIngreso(Integer periodo, Integer gestion, Long idUsuario);

}
