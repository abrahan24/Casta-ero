package uap.usic.siga.modelos.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
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
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.CajitaDao;

/**
 * Rectorado - USIC Fecha: 2019-03-25
 *
 * @author Freddy Morales
 */
@Repository("cajitaDao")
public class CajitaDaoImpl implements CajitaDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
    @Override
    public List<CjaTiposGastos> listarTiposGastosGET() {
        String sql = "Select tg from CjaTiposGastos tg "
                + " Where tg.idEstado = 'A' Order by tg.nroTipoGasto";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<CjaTiposIngresos> listarTiposIngresosGET() {
        String sql = "Select ti from CjaTiposIngresos ti "
                + " Where ti.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarIngresosFondosSET(CjaIngresos cajaIngresos) {
        em.persist(cajaIngresos);
    }

    @Override
    public List<CjaIngresos> listarIngresosPorIdPersonaJPQL(Long idPersona) {
        String sql = "SELECT cif "
                + "FROM CjaIngresos cif LEFT JOIN cif.personas p "
                + " LEFT JOIN cif.cjaTiposIngresos cti "
                + " WHERE p.idPersona = :idPersona"
                + " AND cif.idEstado != 'X' Order by cif.fecIngreso ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        return q.getResultList();
    }

    @Override
    public CjaIngresos buscarCjaIngresosFondosPorIdPersonaGestionPeriodoIdEstadoGET(Long idPersona, Integer gestion, Integer periodo, String idEstado) {
        String sql = "SELECT cif "
                + " FROM CjaIngresos cif LEFT JOIN cif.personas p"
                + " WHERE  p.idPersona = :idPersona "
                + " AND cif.gestion =:gestion "
                + " AND cif.periodo =:periodo "
                + " AND cif.idEstado =:idEstado ";

        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("gestion", gestion);
        q.setParameter("periodo", periodo);
        q.setParameter("idEstado", idEstado);

        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CjaGastosEjecutados> listarCjaGastosEjecutadosPorIdCjaIngresoIdUsuarioJPQL(Long idCjaIngreso, Long idUsuario) {
        String sql = " SELECT cge "
                + " FROM CjaGastosEjecutados cge LEFT JOIN cge.personas p  "
                + " LEFT JOIN cge.cjaIngresos cif "
                + " LEFT JOIN cge.cjaTiposGastos ctg "
                + " LEFT JOIN cge.cjaProveedores cp "
                + " LEFT JOIN cge.usuarios u "
                + " WHERE cif.idCjaIngreso = :idCjaIngreso "
                + " AND u.idUsuario = :idUsuario "
                + " AND cge.idEstado = 'A' "
                + " AND u.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();

    }

    @Override
    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado) {
        String sql = "SELECT cge "
                + "FROM CjaGastosEjecutados cge LEFT JOIN cge.personas p  "
                + " LEFT JOIN cge.cjaIngresos cif "
                + " LEFT JOIN cge.cjaTiposGastos ctg "
                + " LEFT JOIN cge.cjaProveedores cp "
                + " WHERE cge.idCjaGastoEjecutado = :idCjaGastoEjecutado "
                + " AND cge.idEstado = 'A'  ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaGastoEjecutado", idCjaGastoEjecutado);

        try {
            return (CjaGastosEjecutados) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void actualizarEstadoCajaIngresosSET(CjaIngresos cajaIngresos) {
        em.merge(cajaIngresos);
    }

    @Override
    public CjaTiposGastos buscarCajaTiposGastosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado) {
        String sql = "SELECT ctg.tipoGasto,ctg.idCjaTipoGasto "
                + "FROM CjaGastosEjecutados cge LEFT JOIN cge.cjaTiposGastos ctg  "
                + " WHERE cge.idCjaGastoEjecutado = :idCjaGastoEjecutado "
                + " AND cge.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaGastoEjecutado", idCjaGastoEjecutado);

        try {
            return (CjaTiposGastos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CjaIngresos buscarCajaIngresosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado) {
        String sql = "SELECT cti.idCjaIngreso,cti.fecIngreso "
                + "FROM CjaGastosEjecutados cge LEFT JOIN cge.cjaIngresos cti  "
                + " WHERE cge.idCjaGastoEjecutado = :idCjaGastoEjecutado "
                + " AND cge.idEstado = 'A'  ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaGastoEjecutado", idCjaGastoEjecutado);

        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CjaGastosEjecutadosResponse buscarCajasGastosEjecutadosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado) {
        String sql = "SELECT new uap.usic.siga.dto.CjaGastosEjecutadosResponse(cge) "
                + "FROM CjaGastosEjecutados cge LEFT JOIN cge.personas p  "
                + " LEFT JOIN cge.cjaIngresos ci "
                + " LEFT JOIN cge.cjaTiposGastos ctg "
                + " WHERE cge.idCjaGastoEjecutado = :idCjaGastoEjecutado "
                + " AND cge.idEstado = 'A'  "
                + " AND ctg.idEstado = 'A' "
                + " AND p.idEstado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaGastoEjecutado", idCjaGastoEjecutado);

        try {
            return (CjaGastosEjecutadosResponse) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CjaGastosEjecutados> listarGastoEjecutadosPorIdUsuarioFecIngresoFecPagoJPQL(Long idUsuario, Long idCjaIngreso, Date fecIngreso, Date fecGasto) {
        String sql = " SELECT cge "
                + " FROM CjaGastosEjecutados cge LEFT JOIN cge.usuarios u  "
                + " LEFT JOIN cge.cjaIngresos cif "
                + " LEFT JOIN cge.cjaTiposGastos ctg "
                + " LEFT JOIN cge.cjaProveedores cp "
                + " WHERE u.idUsuario = :idUsuario "
                + " AND cif.idCjaIngreso = :idCjaIngreso "
                + " AND cge.fecGasto BETWEEN :fecIngreso AND :fecGasto "
                + " AND cge.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        q.setParameter("fecIngreso", fecIngreso);
        q.setParameter("fecGasto", fecGasto);
        return q.getResultList();
    }

    @Override
    public CjaIngresos buscarCjaIngresosFondosPorIdPersonaIdEstadoGET(Long idPersona, String idEstado) {
        String sql = "SELECT cif "
                + " FROM CjaIngresos cif LEFT JOIN cif.personas p"
                + " WHERE  p.idPersona = :idPersona "
                + " AND cif.idEstado =:idEstado ";

        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);

        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public CjaIngresos buscarCjaIngresoPorIdCjaIngresoGET(Long idCjaIngreso) {
        String sql = "SELECT cif "
                + " FROM CjaIngresos cif "
                + " WHERE  cif.idCjaIngreso = :idCjaIngreso "
                + " AND cif.idEstado !=  'X' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);

        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void eliminarIngresosFondosSET(CjaIngresos cjaIngresos) {
        em.merge(cjaIngresos);
    }

    @Override
    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdPersonaGET(Long idPersona) {
        String sql = "SELECT pa "
                + " FROM PnlPersonalAdministrativos pa LEFT JOIN pa.personas p"
                + " LEFT JOIN pa.pnlCargos pc "
                + " LEFT JOIN pa.pnlTiposAdministrativos ta "
                + " LEFT JOIN pa.insUnidadesFuncionales uf "
                + " WHERE  p.idPersona = :idPersona "
                + " AND pa.idEstado = 'A' ";

        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);

        try {
            return (PnlPersonalAdministrativos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CjaIngresos buscarCjaIngresoPorIdCjaIngresoIdEstadoIGET(Long idCjaIngreso, Long idPersona) {
        String sql = "SELECT cif "
                + " FROM CjaIngresos cif LEFT JOIN cif.personas p"
                + " WHERE  cif.idCjaIngreso = :idCjaIngreso"
                + " AND p.idPersona = :idPersona"
                + " AND cif.idEstado =  'I' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        q.setParameter("idPersona", idPersona);

        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CjaProveedores> listarCajitaProveedoresJPQL() {
        String sql = "SELECT p FROM CjaProveedores p "
                + " WHERE p.idEstado = 'A' ORDER BY p.proveedor";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public CjaIngresos buscarCajitaSaldoIngresosPorIdPersonaGET(String idEstado, Integer gestion, Integer periodo, Long idPersona) {
        String sql = "SELECT ing FROM CjaIngresos ing "
                + " LEFT JOIN ing.personas prs"
                + " WHERE prs.idPersona =:idPersona "
                + " AND ing.idEstado =:idEstado "
                + " AND ing.gestion =:gestion "
                + " AND ing.periodo =:periodo "
                + " AND prs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);
        q.setParameter("gestion", gestion);
        q.setParameter("periodo", periodo);

        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CjaIngresosResponse buscarSaldoIngresosGET(String idEstado, Integer gestion, Integer periodo, Long idPersona) {
        String sql = "SELECT new uap.usic.siga.dto.CjaIngresosResponse(ing) FROM CjaIngresos ing "
                + " LEFT JOIN ing.personas prs"
                + " WHERE prs.idPersona =:idPersona "
                + " AND ing.idEstado =:idEstado "
                + " AND ing.gestion =:gestion "
                + " AND ing.periodo =:periodo "
                + " AND prs.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);
        q.setParameter("gestion", gestion);
        q.setParameter("periodo", periodo);

        try {
            return (CjaIngresosResponse) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insertarCjaGastosEjecutadosSET(CjaGastosEjecutados cjaGastosEjecutados) {
        em.persist(cjaGastosEjecutados);
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosEjecutadosGestionPeriodoIdUsuarioJPQL(Integer periodo, Integer gestion, Long idUsuario) {
        String sql = "SELECT gts FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaProveedores prv "
                + " LEFT JOIN gts.usuarios u "
                + " WHERE u.idUsuario =:idUsuario "
                + " AND gts.periodo =:periodo "
                + " AND gts.gestion =:gestion "
                + " AND u.idEstado = 'A' "
                + " AND gts.idEstado = 'A' "
                + " ORDER BY gts.fecGasto";
        Query q = em.createQuery(sql);
        q.setParameter("periodo", periodo);
        q.setParameter("gestion", gestion);
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }

    @Override
    public CjaGastosEjecutados buscarCjaGastoEjecutadosPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado) {
        String sql = "SELECT gts FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaProveedores prv "
                + " LEFT JOIN gts.personas prs "
                + " WHERE gts.idCjaGastoEjecutado =:idCjaGastoEjecutado "
                + " AND prs.idEstado = 'A' "
                + " AND gts.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaGastoEjecutado", idCjaGastoEjecutado);

        try {
            return (CjaGastosEjecutados) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void modificarCjaGastosEjecutadosSET(CjaGastosEjecutados cjaGastosEjecutados) {
        em.merge(cjaGastosEjecutados);
    }

    @Override
    public void modificarCajaIngresosSaldoSET(CjaIngresos cjaIngresos) {
        em.merge(cjaIngresos);
    }

    @Override
    public CjaIngresos buscarCjaIngresosPorIdCjaIngresoGET(Long idCjaIngreso) {
        String sql = "SELECT ing FROM CjaIngresos ing "
                + " WHERE ing.idCjaIngreso =:idCjaIngreso "
                + " AND ing.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void registrarTiposIngresosSET(CjaTiposIngresos cjaTiposIngresos) {
        em.persist(cjaTiposIngresos);
    }

    @Override
    public CjaIngresos buscarCajitIngresosPorIdPersonaTipoIngresoGET(String idEstado, Integer gestion, Integer periodo, Long idPersona, Long idCjaTipoIngreso) {
        String sql = "SELECT ing FROM CjaIngresos ing "
                + " LEFT JOIN ing.personas prs "
                + " LEFT JOIN ing.cjaTiposIngresos cti "
                + " WHERE prs.idPersona =:idPersona "
                + " AND cti.idCjaTipoIngreso =:idCjaTipoIngreso"
                + " AND ing.idEstado =:idEstado "
                + " AND ing.gestion =:gestion "
                + " AND ing.periodo =:periodo "
                + " AND prs.idEstado = 'A' "
                + " AND cti.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);
        q.setParameter("gestion", gestion);
        q.setParameter("periodo", periodo);
        q.setParameter("idCjaTipoIngreso", idCjaTipoIngreso);

        try {
            return (CjaIngresos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long buscarCjaGastosEjecutadosPorIdCjaIngresoIdEstadoGET(Long idCjaIngreso, String idEstado) {
        String sql = "SELECT DISTINCT cif.idCjaIngreso "
                + "FROM CjaGastosEjecutados cge "
                + " LEFT JOIN cge.cjaIngresos cif "
                + " WHERE cif.idCjaIngreso = :idCjaIngreso "
                + " AND cge.idEstado = :idEstado"
                + " AND cge.idEstado = 'A'  ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        q.setParameter("idEstado", idEstado);

        try {
            return (Long) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void registarCjaProveedoresSET(CjaProveedores cjaProveedores) {
        em.persist(cjaProveedores);
    }

    @Override
    public List<CjaTiposClasificaciones> listarCjaTiposClasificacionesJPQL() {
        String sql = "Select tc "
                + " FROM CjaTiposClasificaciones tc "
                + " WHERE tc.idEstado = 'A' "
                + " ORDER BY tc.idCjaTipoClasificacion";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public void registrarTiposGastosSET(CjaTiposGastos cjaTiposGastos) {
        em.persist(cjaTiposGastos);
    }

    @Override
    public List<CjaGastosClasificaciones> listarCjaGastosClasificacionesJPQL() {
        String sql = "SELECT cgc "
                + " FROM CjaGastosClasificaciones cgc "
                + " WHERE cgc.idEstado = 'A' Order by cgc.nroGastoClasificacion";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionResponseIdUsuarioIdCjaIngresoJPQL(Long idUsuario, Long idCjaIngreso, Date fecIngreso, Date fecGasto) {
        String sql = "SELECT new uap.usic.siga.dto.CjaTiposClasificacionesResponse(ctc.idCjaTipoClasificacion, ctc.nroTipoClasificacion,ctc.tipoClasificacion,SUM(gts.totalG))"
                + " FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaIngresos cin "
                + " LEFT JOIN gts.cjaTiposGastos ctg "
                + " LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " LEFT JOIN gts.usuarios u "
                + " WHERE u.idUsuario =:idUsuario "
                + " AND cin.idCjaIngreso =:idCjaIngreso "
                + " AND gts.fecGasto BETWEEN :fecIngreso AND :fecGasto "
                + " AND u.idEstado = 'A' "
                + " AND gts.idEstado = 'A' "
                + " GROUP BY ctc.idCjaTipoClasificacion";
        Query q = em.createQuery(sql);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        q.setParameter("fecIngreso", fecIngreso);
        q.setParameter("fecGasto", fecGasto);
        return q.getResultList();
    }

    @Override
    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionResponseIdUsuarioIdCajaIngresoJPQL(Long idUsuario, Long idCjaIngreso) {
        String sql = "SELECT new uap.usic.siga.dto.CjaGastosClasificacionesResponse(cgc.idCjaGastoClasificacion, ctc.idCjaTipoClasificacion,cgc.nroGastoClasificacion,cgc.gastoClasificacion,SUM(gts.totalG))"
                + " FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaIngresos cin "
                + " LEFT JOIN gts.cjaTiposGastos ctg "
                + " LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " LEFT JOIN gts.usuarios u "
                + " WHERE u.idUsuario =:idUsuario "
                + " AND cin.idCjaIngreso =:idCjaIngreso "
                + " AND u.idEstado = 'A' "
                + " AND gts.idEstado = 'A' "
                + " GROUP BY cgc.idCjaGastoClasificacion";
        Query q = em.createQuery(sql);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        return q.getResultList();
    }

    @Override
    public List<CjaGastosEjecutados> listarGastosPorIdPersonaGestionPeriodoIdUsuarioJPQL(Long idPersona, Integer gestion, Integer periodo, Long idUsuario, Long idCjaIngreso) {
        String sql = " SELECT cge "
                + " FROM CjaGastosEjecutados cge LEFT JOIN cge.personas p  "
                + " LEFT JOIN cge.cjaIngresos cif "
                + " LEFT JOIN cge.cjaTiposGastos ctg "
                + " LEFT JOIN cge.cjaProveedores cp "
                + " LEFT JOIN cge.usuarios u "
                + " WHERE p.idPersona = :idPersona "
                + " AND cge.gestion = :gestion "
                + " AND cge.periodo = :periodo "
                + " AND u.idUsuario = :idUsuario "
                + " AND cif.idCjaIngreso = :idCjaIngreso "
                + " AND cge.idEstado = 'A' "
                + " AND u.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("gestion", gestion);
        q.setParameter("periodo", periodo);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        return q.getResultList();
    }

    @Override
    public List<CjaIngresos> listarCjaIngresosPorIdUnidadFuncionalGestionJPQL(Long idUnidadFuncional, Integer gestion) {
        String sql = " SELECT DISTINCT cif "
                + " FROM CjaGastosEjecutados cge LEFT JOIN cge.cjaIngresos cif "
                + " LEFT JOIN cif.cjaTiposIngresos cti "
                + " LEFT JOIN cge.pnlPersonalAdministrativos pa "
                + " LEFT JOIN pa.insUnidadesFuncionales uf"
                + " WHERE uf.idUnidadFuncional = :idUnidadFuncional "
                + " AND cif.gestion = :gestion "
                + " AND cge.idEstado = 'A' "
                + " AND cif.idEstado != 'X' "
                + " AND uf.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idUnidadFuncional", idUnidadFuncional);
        q.setParameter("gestion", gestion);
        return q.getResultList();
    }

    @Override
    public List<CjaGastosEjecutados> listarGastoEjecutadosPorIdCajaIngresoFecIngresoFecPagoJPQL(Long idCjaIngreso, Date fecIngreso, Date fecGasto) {
        String sql = " SELECT cge "
                + " FROM CjaGastosEjecutados cge LEFT JOIN cge.usuarios u  "
                + " LEFT JOIN cge.cjaIngresos cif "
                + " LEFT JOIN cge.cjaTiposGastos ctg "
                + " LEFT JOIN cge.cjaProveedores cp "
                + " WHERE cif.idCjaIngreso = :idCjaIngreso "
                + " AND cge.fecGasto BETWEEN :fecIngreso AND :fecGasto "
                + " AND cge.idEstado = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        q.setParameter("fecIngreso", fecIngreso);
        q.setParameter("fecGasto", fecGasto);
        return q.getResultList();
    }

    @Override
    public List<CjaUnidadesFuncionalesResponse> listarTotalesGastosPOrUnidadesFuncionalesJPQL(Long idDireccionFuncional, Integer gestion) {
        String sql = "SELECT new uap.usic.siga.dto.CjaUnidadesFuncionalesResponse(unf.idUnidadFuncional, SUM(gts.totalG), cng.montoMaximo, cng.saldo,unf.unidadFuncional, unf.sigla) "
                + " FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaIngresos cng "
                + " LEFT JOIN gts.pnlPersonalAdministrativos pnl "
                + " LEFT JOIN pnl.insUnidadesFuncionales unf "
                + " LEFT JOIN unf.insDireccionesFuncionales idf "
                + " WHERE idf.idDireccionFuncional =:idDireccionFuncional "
                + " AND gts.gestion =:gestion "
                + " AND gts.idEstado = 'A' "
                + " AND pnl.idEstado = 'A' "
                + " AND unf.idEstado = 'A' "
                + " GROUP BY unf.idUnidadFuncional";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        q.setParameter("idDireccionFuncional", idDireccionFuncional);
        return q.getResultList();
    }

    @Override
    public List<CjaDireccionesFuncionalesResponse> listarTotalesGastosPorDireccionesFuncionalesJPQL(Long idSede, Integer gestion) {
        String sql = "SELECT new uap.usic.siga.dto.CjaDireccionesFuncionalesResponse(idf.idDireccionFuncional,unf.idUnidadFuncional, SUM(gts.totalG), cng.montoMaximo, cng.saldo, idf.direccionFuncional, idf.sigla) "
                + " FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaIngresos cng "
                + " LEFT JOIN gts.pnlPersonalAdministrativos pnl "
                + " LEFT JOIN pnl.insUnidadesFuncionales unf "
                + " LEFT JOIN unf.insDireccionesFuncionales idf "
                + " LEFT JOIN idf.insSedes ins"
                + " WHERE ins.idSede =:idSede "
                + " AND gts.gestion =:gestion "
                + " AND gts.idEstado = 'A' "
                + " AND pnl.idEstado = 'A' "
                + " AND unf.idEstado = 'A' "
                + " AND idf.idEstado = 'A' "
                + " AND ins.idEstado = 'A' "
                + " AND cng.idEstado = 'A' "
                + " GROUP BY unf.idUnidadFuncional";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        q.setParameter("idSede", idSede);
        return q.getResultList();
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTiposClasificacionResponsePorIdCjaIngresoJPQL(Long idCjaIngreso) {
        String sql = "SELECT new uap.usic.siga.dto.CjaTiposClasificacionesResponse(ctc.idCjaTipoClasificacion, ctc.nroTipoClasificacion,ctc.tipoClasificacion,SUM(gts.totalG))"
                + " FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaIngresos cin "
                + " LEFT JOIN gts.cjaTiposGastos ctg "
                + " LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " LEFT JOIN gts.usuarios u "
                + " WHERE cin.idCjaIngreso =:idCjaIngreso "
                + " AND u.idEstado = 'A' "
                + " AND gts.idEstado = 'A' "
                + " GROUP BY ctc.idCjaTipoClasificacion";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        return q.getResultList();
    }

    @Override
    public List<CjaGastosClasificacionesResponse> listarGastosClasificacionResponsePorIdCajaIngresoJPQL(Long idCjaIngreso) {
        String sql = "SELECT new uap.usic.siga.dto.CjaGastosClasificacionesResponse(cgc.idCjaGastoClasificacion, ctc.idCjaTipoClasificacion,cgc.nroGastoClasificacion,cgc.gastoClasificacion,SUM(gts.totalG))"
                + " FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaIngresos cin "
                + " LEFT JOIN gts.cjaTiposGastos ctg "
                + " LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " LEFT JOIN gts.usuarios u "
                + " WHERE cin.idCjaIngreso =:idCjaIngreso "
                + " AND u.idEstado = 'A' "
                + " AND gts.idEstado = 'A' "
                + " GROUP BY cgc.idCjaGastoClasificacion";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaIngreso", idCjaIngreso);
        return q.getResultList();
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorTipoClasificacionUnidadFuncionalJPQL(Long idUnidadFuncional, Integer gestion) {
        String sql = "SELECT new uap.usic.siga.dto.CjaTiposClasificacionesResponse(ctc.idCjaTipoClasificacion, SUM(gts.totalG), cng.montoMaximo, cng.saldo, ctc.nroTipoClasificacion, ctc.tipoClasificacion) "
                + " FROM CjaGastosEjecutados gts LEFT JOIN gts.cjaIngresos cng "
                + " LEFT JOIN gts.pnlPersonalAdministrativos pnl LEFT JOIN pnl.insUnidadesFuncionales unf "
                + " LEFT JOIN unf.insDireccionesFuncionales idf LEFT JOIN idf.insSedes ins "
                + " LEFT JOIN gts.cjaTiposGastos ctg LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " WHERE unf.idUnidadFuncional =:idUnidadFuncional "
                + " AND gts.gestion =:gestion "
                + " AND gts.idEstado = 'A' "
                + " AND pnl.idEstado = 'A' "
                + " AND unf.idEstado = 'A' "
                + " AND idf.idEstado = 'A' "
                + " AND ins.idEstado = 'A' "
                + " AND cng.idEstado = 'A' "
                + " AND ctg.idEstado = 'A' "
                + " AND cgc.idEstado = 'A' "
                + " AND ctc.idEstado = 'A' "
                + " GROUP BY ctc.idCjaTipoClasificacion";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        q.setParameter("idUnidadFuncional", idUnidadFuncional);
        return q.getResultList();
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosPorPartidasDireccionesFuncionalesJPQL(Long idDireccionFuncional, Integer gestion) {
        String sql = "SELECT new uap.usic.siga.dto.CjaTiposClasificacionesResponse(ctc.idCjaTipoClasificacion,unf.idUnidadFuncional, SUM(gts.totalG), cng.montoMaximo, cng.saldo, ctc.nroTipoClasificacion, ctc.tipoClasificacion) "
                + " FROM CjaGastosEjecutados gts LEFT JOIN gts.cjaIngresos cng "
                + " LEFT JOIN gts.pnlPersonalAdministrativos pnl LEFT JOIN pnl.insUnidadesFuncionales unf "
                + " LEFT JOIN unf.insDireccionesFuncionales idf LEFT JOIN idf.insSedes ins "
                + " LEFT JOIN gts.cjaTiposGastos ctg LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " WHERE idf.idDireccionFuncional =:idDireccionFuncional "
                + " AND gts.gestion =:gestion "
                + " AND gts.idEstado = 'A' "
                + " AND pnl.idEstado = 'A' "
                + " AND unf.idEstado = 'A' "
                + " AND idf.idEstado = 'A' "
                + " AND ins.idEstado = 'A' "
                + " AND cng.idEstado = 'A' "
                + " AND ctg.idEstado = 'A' "
                + " AND cgc.idEstado = 'A' "
                + " AND ctc.idEstado = 'A' "
                + " GROUP BY ctc.idCjaTipoClasificacion";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        q.setParameter("idDireccionFuncional", idDireccionFuncional);
        return q.getResultList();
    }

    @Override
    public List<CjaGastosClasificaciones> listarCjaGastosClasificacionesPorIdCjaTipoClasificacionJPQL(Long idCjaTipoClasificacion) {
        String sql = "SELECT cgc FROM CjaGastosClasificaciones cgc "
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " WHERE ctc.idCjaTipoClasificacion =:idCjaTipoClasificacion "
                + " AND cgc.idEstado = 'A' "
                + " AND ctc.idEstado = 'A' "
                + " ORDER BY cgc.gastoClasificacion";
        Query q = em.createQuery(sql);
        q.setParameter("idCjaTipoClasificacion", idCjaTipoClasificacion);
        return q.getResultList();
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosGeneralesPorPartidaSedeJPQL(Long idSede, Integer gestion) {
        String sql = "SELECT new uap.usic.siga.dto.CjaTiposClasificacionesResponse(ctc.idCjaTipoClasificacion, idf.idDireccionFuncional, unf.idUnidadFuncional, SUM(gts.totalG), cng.montoMaximo, cng.saldo, ctc.nroTipoClasificacion, ctc.tipoClasificacion) "
                + " FROM CjaGastosEjecutados gts LEFT JOIN gts.cjaIngresos cng "
                + " LEFT JOIN gts.pnlPersonalAdministrativos pnl LEFT JOIN pnl.insUnidadesFuncionales unf "
                + " LEFT JOIN unf.insDireccionesFuncionales idf LEFT JOIN idf.insSedes ins "
                + " LEFT JOIN gts.cjaTiposGastos ctg LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " WHERE unf.idUnidadFuncional =:idSede "
                + " AND gts.gestion =:gestion "
                + " AND gts.idEstado = 'A' "
                + " AND pnl.idEstado = 'A' "
                + " AND unf.idEstado = 'A' "
                + " AND idf.idEstado = 'A' "
                + " AND ins.idEstado = 'A' "
                + " AND cng.idEstado = 'A' "
                + " AND ctg.idEstado = 'A' "
                + " AND cgc.idEstado = 'A' "
                + " AND ctc.idEstado = 'A' "
                + " GROUP BY ctc.idCjaTipoClasificacion "
                + " ORDER BY ctc.idCjaTipoClasificacion ";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        q.setParameter("idSede", idSede);
        return q.getResultList();

    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarTotalesGastosGeneralesPorPartidaSedeTotalesJPQL(Long idSede, Integer gestion) {
        String sql = "SELECT new uap.usic.siga.dto.CjaTiposClasificacionesResponse(ctc.idCjaTipoClasificacion, idf.idDireccionFuncional, unf.idUnidadFuncional, SUM(gts.totalG), cng.montoMaximo, cng.saldo, ctc.nroTipoClasificacion, ctc.tipoClasificacion) "
                + " FROM CjaGastosEjecutados gts LEFT JOIN gts.cjaIngresos cng "
                + " LEFT JOIN gts.pnlPersonalAdministrativos pnl LEFT JOIN pnl.insUnidadesFuncionales unf "
                + " LEFT JOIN unf.insDireccionesFuncionales idf LEFT JOIN idf.insSedes ins "
                + " LEFT JOIN gts.cjaTiposGastos ctg LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " WHERE ins.idSede =:idSede "
                + " AND gts.gestion =:gestion "
                + " AND gts.idEstado = 'A' "
                + " AND pnl.idEstado = 'A' "
                + " AND unf.idEstado = 'A' "
                + " AND idf.idEstado = 'A' "
                + " AND ins.idEstado = 'A' "
                + " AND cng.idEstado = 'A' "
                + " AND ctg.idEstado = 'A' "
                + " AND cgc.idEstado = 'A' "
                + " AND ctc.idEstado = 'A' "
                + " GROUP BY ctc.idCjaTipoClasificacion "
                + " ORDER BY ctc.idCjaTipoClasificacion ";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        q.setParameter("idSede", idSede);
        return q.getResultList();
    }

    @Override
    public List<CjaTiposClasificacionesResponse> listarMOntoMaximoPorIdSedeJPQL(Long idSede, Integer gestion) {
        String sql = "SELECT new uap.usic.siga.dto.CjaTiposClasificacionesResponse(ctc.idCjaTipoClasificacion, idf.idDireccionFuncional, unf.idUnidadFuncional, SUM(gts.totalG), cng.montoMaximo, cng.saldo, ctc.nroTipoClasificacion, ctc.tipoClasificacion) "
                + " FROM CjaGastosEjecutados gts LEFT JOIN gts.cjaIngresos cng "
                + " LEFT JOIN gts.pnlPersonalAdministrativos pnl LEFT JOIN pnl.insUnidadesFuncionales unf "
                + " LEFT JOIN unf.insDireccionesFuncionales idf LEFT JOIN idf.insSedes ins "
                + " LEFT JOIN gts.cjaTiposGastos ctg LEFT JOIN ctg.cjaGastosClasificaciones cgc"
                + " LEFT JOIN cgc.cjaTiposClasificaciones ctc "
                + " WHERE ins.idSede =:idSede "
                + " AND gts.gestion =:gestion "
                + " AND gts.idEstado = 'A' "
                + " AND pnl.idEstado = 'A' "
                + " AND unf.idEstado = 'A' "
                + " AND idf.idEstado = 'A' "
                + " AND ins.idEstado = 'A' "
                + " AND cng.idEstado = 'A' "
                + " AND ctg.idEstado = 'A' "
                + " AND cgc.idEstado = 'A' "
                + " AND ctc.idEstado = 'A' "
                + " GROUP BY unf.idUnidadFuncional";
        Query q = em.createQuery(sql);
        q.setParameter("gestion", gestion);
        q.setParameter("idSede", idSede);
        return q.getResultList();
    }

    @Override
    public CjaIngresos buscarCjaIngresosFondosRetornaMaximoIdCajaIngresoIdPersonaGET(Long idPersona, String idEstado) {
        String sql = "SELECT MAX(cif.idCjaIngreso) "
                + " FROM CjaIngresos cif LEFT JOIN cif.personas prs"
                + " WHERE  prs.idPersona =:idPersona "
                + " AND prs.idEstado = 'A' "
                + " AND (cif.idEstado =:idEstado)";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        q.setParameter("idEstado", idEstado);
        Long idCjaIngreso = (Long) q.getSingleResult();

        String sql2 = "SELECT ing FROM CjaIngresos ing "
                + " WHERE ing.idCjaIngreso =:idCjaIngreso ";
        Query q2 = em.createQuery(sql2);
        q2.setParameter("idCjaIngreso", idCjaIngreso);
        try {
            return (CjaIngresos) q2.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<CjaGastosEjecutados> listarGastosEjecutadosGestionPeriodoIdUsuarioIdCjaIngresoJPQL(Integer periodo, Integer gestion, Long idUsuario) {
        String sql = "SELECT gts FROM CjaGastosEjecutados gts "
                + " LEFT JOIN gts.cjaProveedores prv LEFT JOIN gts.cjaIngresos cin"
                + " LEFT JOIN gts.usuarios u "
                + " WHERE u.idUsuario =:idUsuario "
                + " AND cin.caja = 0 "
                + " AND gts.periodo =:periodo "
                + " AND gts.gestion =:gestion "
                + " AND u.idEstado = 'A' "
                + " AND gts.idEstado = 'A' "
                + " ORDER BY gts.fecGasto";
        Query q = em.createQuery(sql);
        q.setParameter("periodo", periodo);
        q.setParameter("gestion", gestion);
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }
}
