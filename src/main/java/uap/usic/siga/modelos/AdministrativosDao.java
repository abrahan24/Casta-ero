package uap.usic.siga.modelos;

import java.util.List;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.PnlItems;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PnlTiposAdministrativos;

/**
 * Rectorado - USIC
 * Fecha: 2019-04-16
 * @author Freddy Morales
 */
public interface AdministrativosDao {
    
    public List<PnlCargos> listarPnlCargosGET();
    public List<PnlItems> listarPnlItemsGET();
    public List<PnlTiposAdministrativos> listarPnlTiposAdministrativosGET();

    public void guardarPersonalAdministrativoSET(PnlPersonalAdministrativos pnlPersonalAdministrativo);
    
    public  PnlCargos buscarCargosPorIdPersonaGET(Long idPersona);
    
    public List<PnlPersonalAdministrativos> listarPersonaslAdministrativosJPQL();
    
    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdPnlPersonalAdministrativoGET(Long idPnlPersonalAdministrativo);
    public void modificarDatosPersonalAdministrativosSET(PnlPersonalAdministrativos pnlPersonalAdministrativo);
    public void eliminarRegistroPersonalAdministrativosSET(PnlPersonalAdministrativos pnlPersonalAdministrativo);

    public void actualizarPnlCargosSET(PnlCargos pnlCargos);
    
    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodoGET(Long idPersona,Integer gestion,Integer periodo);
  
    public void guardarPnlCargosSET(PnlCargos pnlCargos);
  
    public void guardarPnlTiposAdministrativosSET(PnlTiposAdministrativos pnlTiposAdministrativos);

    public void guardarPnlItemsSET(PnlItems pnlItems);
    
    public PnlTiposAdministrativos getTipoAdministrativo(String tipo);
    
    public PnlItems getItem(String item);

}
