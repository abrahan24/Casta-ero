package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.entidades.CngCongresistas;
import uap.usic.siga.entidades.CngTiposCongresistas;
import uap.usic.siga.entidades.CongresoUap;

/**
 *
 * @author Freddy Morales
 */
public interface CongresoDao {

	  public List<CngTiposCongresistas> listarTiposCongresistasJPQL();
	  
	  public List<CngCongresistas> listarCongresistasPorGestionJPQL(Integer gestion);
	  
      public List<CongresoUap> listarCongresosGeneralJPQL();
  
      public CngCongresistas registrarCongresistasSET(CngCongresistas cngCongresistas);
      
      public CngCongresistas buscarCngCongresistasPorIdCngCongresistasGET(Long idCngCongresista);
 
      public CngCongresistas modificarCngCongresistasSET(CngCongresistas cngCongresistas);
      
}
