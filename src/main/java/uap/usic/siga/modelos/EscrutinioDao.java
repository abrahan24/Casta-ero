package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.dto.EscActasResponse;
import uap.usic.siga.entidades.EscDetalles;
import uap.usic.siga.entidades.EscElecciones;
import uap.usic.siga.entidades.EscFrentes;
import uap.usic.siga.entidades.EscMesasHabilitadas;
import uap.usic.siga.entidades.EscrutinioActas;

/**
 * Rectorado - USIC Fecha: 2021-07-11
 *
 * @author Freddy Morales
 */
public interface EscrutinioDao {

	public List<EscElecciones> listarEleccionesPorGestionPeriodoJPQL(Integer gestion, Integer periodo);
	
	public List<EscMesasHabilitadas> listarMesasHabilitadasPorIdEleccionIdFacultadIdEstamentoJPQL(Long idEleccion, Long idFacultad, Long idEstamento);
	
	public List<EscFrentes> listarFrentesPorIdFacultadIdEleccionJPQL(Long idFacultad, Long idEstamento, Long idEleccion);
	
	public EscrutinioActas registrarEscrutinioActasSET(EscrutinioActas escrutinioActas);
	
	public EscDetalles registrarEscrutinioDetallesSET(EscDetalles escDetalles);
	
	public EscFrentes buscarFrentePorIdFrenteGET(Long idFrente);
	
	public EscMesasHabilitadas registrarEscrutinioMesasHabilitadasSET(EscMesasHabilitadas escMesasHabilitadas);
	
	public EscFrentes registrarFrentesEleccionSET(EscFrentes escFrentes);
	
    public List<EscMesasHabilitadas> listarMesasSufragioHabilitadasJPQL(Integer gestion, Integer periodo);
	
	public List<EscrutinioActas> listarEscrutinioAcatasPorGestionPeriodoJPQL(Integer gestion, Integer periodo);
	
	public List<EscActasResponse> listarEscrutinioActasResponseGestionPeriodoJPQL(Integer gestion, Integer periodo);
	
	public List<EscActasResponse> listarSumatoriaActasPorIdFacultadIdEstamentoIdEleccionGestionJPQL(Long idFacultad, Long idEleccion, Long idEstamento, Integer gestion);
	
	public List<EscActasResponse> listarSumatoriaActasPorMesasIdFacultadIdEstamentoIdEleccionGestionJPQL(Long idFacultad, Long idEleccion, Long idEstamento, Integer gestion);
	
	public List<EscFrentes> listarEscFrentesPorGestionPeriodoJPQL(Integer gestion, Integer periodo);
	
	public EscElecciones buscarEleccionPorIdEleccionGET(Long idEleccion);
	
	

}
