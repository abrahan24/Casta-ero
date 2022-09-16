package uap.usic.siga.servicios;

import java.util.List;

import uap.usic.siga.dto.EscActasResponse;
import uap.usic.siga.entidades.EscDetalles;
import uap.usic.siga.entidades.EscElecciones;
import uap.usic.siga.entidades.EscFrentes;
import uap.usic.siga.entidades.EscMesasHabilitadas;
import uap.usic.siga.entidades.EscrutinioActas;

public interface EscrutinioServicios {

	public List<EscElecciones> listarEleccionesPorGestionPeriodoL(Integer gestion, Integer periodo);
	
	public List<EscMesasHabilitadas> listarMesasHabilitadasPorIdEleccionIdFacultadIdEstamento(Long idEleccion, Long idFacultad, Long idEstamento);
	
	public List<EscFrentes> listarFrentesPorIdFacultadIdEleccion(Long idFacultad, Long idEstamento, Long idEleccion);

    public EscrutinioActas registrarEscrutinioActas(EscrutinioActas escrutinioActas);
	
	public EscDetalles registrarEscrutinioDetalles(EscDetalles escDetalles);
	
	public EscFrentes buscarFrentePorIdFrente(Long idFrente);
	
	public EscMesasHabilitadas registrarEscrutinioMesasHabilitadas(EscMesasHabilitadas escMesasHabilitadas);
	
	public EscFrentes registrarFrentesEleccion(EscFrentes escFrentes);
	
	public List<EscMesasHabilitadas> listarMesasSufragioHabilitadas(Integer gestion, Integer periodo);
	
	public List<EscrutinioActas> listarEscrutinioAcatasPorGestionPeriodo(Integer gestion, Integer periodo);
	
	public List<EscActasResponse> listarEscrutinioActasResponseGestionPeriodo(Integer gestion, Integer periodo);

	public List<EscActasResponse> listarSumatoriaActasPorIdFacultadIdEstamentoIdEleccionGestion(Long idFacultad, Long idEleccion, Long idEstamento, Integer gestion);

	public List<EscActasResponse> listarSumatoriaActasPorMesasIdFacultadIdEstamentoIdEleccionGestion(Long idFacultad, Long idEleccion, Long idEstamento, Integer gestion);

	public List<EscFrentes> listarEscFrentesPorGestionPeriodo(Integer gestion, Integer periodo);
	
	public EscElecciones buscarEleccionPorIdEleccion(Long idEleccion);
	
}
