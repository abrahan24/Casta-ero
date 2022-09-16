package uap.usic.siga.servicios;

import java.util.List;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.PnlItems;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PnlTiposAdministrativos;

/**
 * Rectorado - USIC Fecha: 2019-04-16
 *
 * @author Freddy MORALES
 */
public interface AdministrativosServicios {

    public List<PnlCargos> listarPnlCargos();

    public List<PnlItems> listarPnlItems();

    public List<PnlTiposAdministrativos> listarPnlTiposAdministrativos();

    public void guardarPersonalAdministrativo(PnlPersonalAdministrativos pnlPersonalAdministrativo);

    public PnlCargos buscarCargosPorIdPersonaGET(Long idPersona);

    public List<PnlPersonalAdministrativos> listarPersonaslAdministrativos();

    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdPnlPersonalAdministrativo(Long idPnlPersonalAdministrativo);

    public void modificarDatosPersonalAdministrativos(PnlPersonalAdministrativos pnlPersonalAdministrativo);

    public void eliminarRegistroPersonalAdministrativos(PnlPersonalAdministrativos pnlPersonalAdministrativo);

    public void actualizarPnlCargos(PnlCargos pnlCargos);

    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(Long idPersona, Integer gestion, Integer periodo);


    public void guardarPnlCargos(PnlCargos pnlCargos);

    public void guardarPnlTiposAdministrativos(PnlTiposAdministrativos pnlTiposAdministrativos);
    
    public PnlTiposAdministrativos getTipoAdministrativo(String tipo);
    
    public void guardarPnlItems(PnlItems pnlItems);
    
    public PnlItems getItem(String item);


}
