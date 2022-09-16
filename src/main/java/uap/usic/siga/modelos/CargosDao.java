package uap.usic.siga.modelos;

import uap.usic.siga.entidades.PnlCargos;

/**
 *
 * @author Yessenia
 */
public interface CargosDao {

    void registrarPnlCargosSET(PnlCargos cargos);

    public PnlCargos buscarPnlCargosPorIdPnlCargoGET(Long idPnlCargos);
    
    public PnlCargos buscarPnlCargosPorCargoGET(String cargo);
}
