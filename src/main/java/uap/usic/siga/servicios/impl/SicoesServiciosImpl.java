package uap.usic.siga.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.entidades.ScsBoletasRespaldatorias;
import uap.usic.siga.entidades.ScsContrataciones;
import uap.usic.siga.entidades.ScsFormularios;
import uap.usic.siga.entidades.ScsModalidades;
import uap.usic.siga.entidades.ScsProyectos;
import uap.usic.siga.entidades.ScsPrsContratados;
import uap.usic.siga.entidades.ScsTiposContratos;
import uap.usic.siga.entidades.ScsTiposModalidades;
import uap.usic.siga.modelos.SicoesDao;
import uap.usic.siga.servicios.SicoesServicios;

/**
 * Rectorado - USIC 
 * Fecha: 2019-12-27
 * @author Freddy Morales
 */
@Service("sicoesServicios")
@Transactional
public class SicoesServiciosImpl implements SicoesServicios {
    
    @Autowired
    private SicoesDao dao;

    @Override
    public List<ScsBoletasRespaldatorias> listarScsBoletasRespaldatorias() {
        return dao.listarScsBoletasRespaldatoriasJPQL();
    }

    @Override
    public List<ScsFormularios> listarScsFormularios() {
        return dao.listarScsFormulariosJPQL();
    }

    @Override
    public List<ScsProyectos> listarScsProyectos() {
        return dao.listarScsProyectosJPQL();
    }

    @Override
    public List<ScsModalidades> listarScsModalidades() {
        return dao.listarScsModalidadesJPQL();
    }

    @Override
    public List<ScsTiposModalidades> listarScsTiposModalidades() {
        return dao.listarScsTiposModalidadesJPQL();
    }

    @Override
    public List<ScsTiposContratos> listarScsTiposContratos() {
        return dao.listarScsTiposContratosJPQL();
    }

    @Override
    public ScsArchivosAdjuntos registrarScsArchivosAdjuntos(ScsArchivosAdjuntos scsArchivosAdjuntos) {
        return dao.registrarScsArchivosAdjuntosSET(scsArchivosAdjuntos);
    }

    @Override
    public ScsContrataciones registrarScsContrataciones(ScsContrataciones scsContrataciones) {
        return dao.registrarScsContratacionesSET(scsContrataciones);
    }

    @Override
    public List<ScsContrataciones> listarScsContrataciones() {
        return dao.listarScsContratacionesJPQL();
    }

    @Override
    public ScsArchivosAdjuntos buscarScsArchivosAdjuntosPorIdScsArchivoAdjunto(Long idScsArchivoAdjunto) {
        return dao.buscarScsArchivosAdjuntosPorIdScsArchivoAdjuntoGET(idScsArchivoAdjunto);
    }

    @Override
    public ScsContrataciones buscarScsContratacionesPorIdScsContratacion(Long idScsContratacion) {
        return dao.buscarScsContratacionesPorIdScsContratacionGET(idScsContratacion);
    }

    @Override
    public void modificarScsContrataciones(ScsContrataciones scsContrataciones) {
        dao.modificarScsContratacionesSET(scsContrataciones);
    }

    @Override
    public void modificarScsArchivosAdjuntos(ScsArchivosAdjuntos scsArchivosAdjuntos) {
        dao.modificarScsArchivosAdjuntosSET(scsArchivosAdjuntos);
    }

    @Override
    public Long extraerMaximoIdScsContratacion() {
        return dao.extraerMaximoIdScsContratacionGET();
    }

    @Override
    public ScsProyectos registrarScsProyectos(ScsProyectos scsProyectos) {
        return dao.registrarScsProyectosSET(scsProyectos);
    }

    @Override
    public void actualizarScsProyectos(ScsProyectos scsProyectos) {
        dao.actualizarScsProyectosSET(scsProyectos);
    }

    @Override
    public ScsProyectos buscarScsProyectosPorIdScsProyecto(Long idScsProyecto) {
        return dao.buscarScsProyectosPorIdScsProyectoGET(idScsProyecto);
    }

    @Override
    public List<ScsContrataciones> listarContratacionesPorGestionIdScsModalidad(Long idScsModalidad, Integer gestion) {
        return dao.listarContratacionesPorGestionIdScsModalidadJQPL(idScsModalidad, gestion);
    }

    @Override
    public ScsModalidades buscarScsModalidadesPorIdScsModalidad(Long idScsModalidad) {
        return dao.buscarScsModalidadesPorIdScsModalidadGET(idScsModalidad);
    }

    @Override
    public ScsPrsContratados registrarScsPrsContratados(ScsPrsContratados scsPrsContratados) {
        return dao.registrarScsPrsContratadosSET(scsPrsContratados);
    }

    @Override
    public List<ScsPrsContratados> listarScsPrsContratadoses() {
        return dao.listarScsPrsContratadosesJQPL();
    }

    @Override
    public ScsPrsContratados buscarScsPrsContratadosPorIdScsPrsContratados(Long idScsPrsContratado) {
        return dao.buscarScsPrsContratadosPorIdScsPrsContratadosGET(idScsPrsContratado);
    }

    @Override
    public void actualizarScsPrsContratados(ScsPrsContratados scsPrsContratados) {
        dao.actualizarScsPrsContratadosSET(scsPrsContratados);
    }

}
