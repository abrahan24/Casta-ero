package uap.usic.siga.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.modelos.CargosDao;
import uap.usic.siga.servicios.CargosServicios;

/**
 *
 * @author Yessenia
 */
@Service("cargosServicio")
@Transactional
public class CargosServiciosImpl implements CargosServicios {

    @Autowired
    private CargosDao dao;

    @Override
    public void registrarPnlCargos(PnlCargos cargos) {
        dao.registrarPnlCargosSET(cargos);
    }

    @Override
    public PnlCargos buscarPnlCargosPorIdPnlCargo(Long idPnlCargos) {
        return dao.buscarPnlCargosPorIdPnlCargoGET(idPnlCargos);
    }

    @Override
    public PnlCargos buscarPnlCargosPorCargo(String cargo) {
      return dao.buscarPnlCargosPorCargoGET(cargo);
    }

}
