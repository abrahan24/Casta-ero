package uap.usic.siga.modelos;

import java.util.List;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuTiposEnlaces;

/**
 *
 * @author Yessenia
 */
public interface MnuEnlacesDao {

    public List<MnuTiposEnlaces> listarMnuTiposEnlacesJPQL();

    public List<MnuEnlaces> listarMnuEnlacesJPQL();

    public void registrarMnuTiposEnlacesSET(MnuTiposEnlaces mnuTiposEnlaces);
    
    public void registrarMnuEnlacesSET(MnuEnlaces mnuEnlaces);
    
    public void modificarMnuEnlacesSET(MnuEnlaces mnuEnlaces);
    
    public void EliminarMnuEnlacesSET(MnuEnlaces mnuEnlaces);

    public MnuEnlaces buscarMnuEnlacesPorIdMnuEnlaceGET(Long idMnuEnlace);

}
