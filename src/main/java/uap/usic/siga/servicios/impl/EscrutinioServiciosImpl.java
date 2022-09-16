package uap.usic.siga.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.dto.EscActasResponse;
import uap.usic.siga.entidades.EscDetalles;
import uap.usic.siga.entidades.EscElecciones;
import uap.usic.siga.entidades.EscFrentes;
import uap.usic.siga.entidades.EscMesasHabilitadas;
import uap.usic.siga.entidades.EscrutinioActas;
import uap.usic.siga.modelos.EscrutinioDao;
import uap.usic.siga.servicios.EscrutinioServicios;

/**
 * Rectorado - USIC Fecha: 2021-07-11
 *
 * @author Freddy Morales
 */
@Service("escrutinioServicios")
@Transactional
public class EscrutinioServiciosImpl implements EscrutinioServicios {

	@Autowired
	private EscrutinioDao dao;

	@Override
	public List<EscElecciones> listarEleccionesPorGestionPeriodoL(Integer gestion, Integer periodo) {
		return dao.listarEleccionesPorGestionPeriodoJPQL(gestion, periodo);
	}

	@Override
	public List<EscMesasHabilitadas> listarMesasHabilitadasPorIdEleccionIdFacultadIdEstamento(Long idEleccion, Long idFacultad, Long idEstamento) {
		return dao.listarMesasHabilitadasPorIdEleccionIdFacultadIdEstamentoJPQL(idEleccion, idFacultad, idEstamento);
	}

	@Override
	public List<EscFrentes> listarFrentesPorIdFacultadIdEleccion(Long idFacultad, Long idEstamento, Long idEleccion) {
		return dao.listarFrentesPorIdFacultadIdEleccionJPQL(idFacultad, idEstamento, idEleccion);
	}

	@Override
	public EscrutinioActas registrarEscrutinioActas(EscrutinioActas escrutinioActas) {
		return dao.registrarEscrutinioActasSET(escrutinioActas);
	}

	@Override
	public EscDetalles registrarEscrutinioDetalles(EscDetalles escDetalles) {
		return dao.registrarEscrutinioDetallesSET(escDetalles);
	}

	@Override
	public EscFrentes buscarFrentePorIdFrente(Long idFrente) {
		return dao.buscarFrentePorIdFrenteGET(idFrente);
	}

	@Override
	public EscMesasHabilitadas registrarEscrutinioMesasHabilitadas(EscMesasHabilitadas escMesasHabilitadas) {
		return dao.registrarEscrutinioMesasHabilitadasSET(escMesasHabilitadas);
	}

	@Override
	public EscFrentes registrarFrentesEleccion(EscFrentes escFrentes) {
		return dao.registrarFrentesEleccionSET(escFrentes);
	}

	@Override
	public List<EscMesasHabilitadas> listarMesasSufragioHabilitadas(Integer gestion, Integer periodo) {
		return dao.listarMesasSufragioHabilitadasJPQL(gestion, periodo);
	}

	@Override
	public List<EscrutinioActas> listarEscrutinioAcatasPorGestionPeriodo(Integer gestion, Integer periodo) {
		return dao.listarEscrutinioAcatasPorGestionPeriodoJPQL(gestion, periodo);
	}

	@Override
	public List<EscActasResponse> listarEscrutinioActasResponseGestionPeriodo(Integer gestion, Integer periodo) {
		return dao.listarEscrutinioActasResponseGestionPeriodoJPQL(gestion, periodo);
	}

	@Override
	public List<EscActasResponse> listarSumatoriaActasPorIdFacultadIdEstamentoIdEleccionGestion(Long idFacultad,
			Long idEleccion, Long idEstamento, Integer gestion) {
	return dao.listarSumatoriaActasPorIdFacultadIdEstamentoIdEleccionGestionJPQL(idFacultad, idEleccion, idEstamento, gestion);
	}

	@Override
	public List<EscActasResponse> listarSumatoriaActasPorMesasIdFacultadIdEstamentoIdEleccionGestion(Long idFacultad,
			Long idEleccion, Long idEstamento, Integer gestion) {
		return dao.listarSumatoriaActasPorMesasIdFacultadIdEstamentoIdEleccionGestionJPQL(idFacultad, idEleccion, idEstamento, gestion);
	}

	@Override
	public List<EscFrentes> listarEscFrentesPorGestionPeriodo(Integer gestion, Integer periodo) {
		return dao.listarEscFrentesPorGestionPeriodoJPQL(gestion, periodo);
	}

	@Override
	public EscElecciones buscarEleccionPorIdEleccion(Long idEleccion) {
		return dao.buscarEleccionPorIdEleccionGET(idEleccion);
	}

	
}
