package uap.usic.siga.servicios;

import uap.usic.siga.entidades.PnlCargos;

/**
 *
 * @author Yessenia
 */
public interface CargosServicios {

    void registrarPnlCargos(PnlCargos cargos);

    public PnlCargos buscarPnlCargosPorIdPnlCargo(Long idPnlCargos);
    
    public PnlCargos buscarPnlCargosPorCargo(String cargo);

}
