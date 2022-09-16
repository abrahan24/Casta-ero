package uap.usic.siga.servicios;

import java.util.List;

import uap.usic.siga.entidades.CngCongresistas;
import uap.usic.siga.entidades.CngTiposCongresistas;
import uap.usic.siga.entidades.CongresoUap;

/**
 *
 * @author Freddy Morales
 */
public interface CongresoServicios {

  public List<CngTiposCongresistas> listarTiposCongresistas();
	  
	  public List<CngCongresistas> listarCongresistasPorGestion(Integer gestion);
	  
	  public List<CongresoUap> listarCongresosGeneral();
	  
	  public CngCongresistas registrarCongresistas(CngCongresistas cngCongresistas);
	  
	  public CngCongresistas buscarCngCongresistasPorIdCngCongresistas(Long idCngCongresista);
	  
	  public CngCongresistas modificarCngCongresistas(CngCongresistas cngCongresistas);
}
