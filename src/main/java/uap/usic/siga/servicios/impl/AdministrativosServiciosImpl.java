package uap.usic.siga.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.entidades.PnlItems;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PnlTiposAdministrativos;
import uap.usic.siga.modelos.AdministrativosDao;
import uap.usic.siga.servicios.AdministrativosServicios;

/**
 * Rectorado - USIC
 * Fecha: 2019-04-16
 * @author Freddy Morales
 */
@Service("administrativosServicio")
@Transactional
public class AdministrativosServiciosImpl implements AdministrativosServicios {
    
    @Autowired
    private AdministrativosDao dao; 

    @Override
    public List<PnlCargos> listarPnlCargos() {
        return dao.listarPnlCargosGET();
    }

    @Override
    public List<PnlItems> listarPnlItems() {
        return dao.listarPnlItemsGET();
    }

    @Override
    public List<PnlTiposAdministrativos> listarPnlTiposAdministrativos() {
        return dao.listarPnlTiposAdministrativosGET();
    }

    @Override
    public void guardarPersonalAdministrativo(PnlPersonalAdministrativos pnlPersonalAdministrativo) {
        dao.guardarPersonalAdministrativoSET(pnlPersonalAdministrativo);
    }

    @Override
    public PnlCargos buscarCargosPorIdPersonaGET(Long idPersona) {
        return dao.buscarCargosPorIdPersonaGET(idPersona);
    }

    @Override
    public List<PnlPersonalAdministrativos> listarPersonaslAdministrativos() {
     return dao.listarPersonaslAdministrativosJPQL();
    }

    @Override
    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdPnlPersonalAdministrativo(Long idPnlPersonalAdministrativo) {
       return dao.buscarPersonalAdministrativoPorIdPnlPersonalAdministrativoGET(idPnlPersonalAdministrativo);
    }

    @Override
    public void modificarDatosPersonalAdministrativos(PnlPersonalAdministrativos pnlPersonalAdministrativo) {
      dao.modificarDatosPersonalAdministrativosSET(pnlPersonalAdministrativo);
    }

    @Override
    public void eliminarRegistroPersonalAdministrativos(PnlPersonalAdministrativos pnlPersonalAdministrativo) {
     dao.eliminarRegistroPersonalAdministrativosSET(pnlPersonalAdministrativo);
    }

    @Override
    public void actualizarPnlCargos(PnlCargos pnlCargos) {
     dao.actualizarPnlCargosSET(pnlCargos);
    }

    @Override
    public PnlPersonalAdministrativos buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodo(Long idPersona, Integer gestion, Integer periodo) {
     return dao.buscarPersonalAdministrativoPorIdpersonaIdPnlCargoGestionPeriodoGET(idPersona, gestion, periodo);
    }

    @Override
    public void guardarPnlCargos(PnlCargos pnlCargos) {
        dao.guardarPnlCargosSET(pnlCargos);
    }

    @Override
    public void guardarPnlTiposAdministrativos(PnlTiposAdministrativos pnlTiposAdministrativos) {
        dao.guardarPnlTiposAdministrativosSET(pnlTiposAdministrativos);
    }

    @Override
    public void guardarPnlItems(PnlItems pnlItems) {
        dao.guardarPnlItemsSET(pnlItems);
    }

	@Override
	public PnlTiposAdministrativos getTipoAdministrativo(String tipo) {
		// TODO Auto-generated method stub
		return dao.getTipoAdministrativo(tipo);
	}

	@Override
	public PnlItems getItem(String item) {
		// TODO Auto-generated method stub
		return dao.getItem(item);
	}

    
}
