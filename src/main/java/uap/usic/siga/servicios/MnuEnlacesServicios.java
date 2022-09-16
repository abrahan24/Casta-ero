package uap.usic.siga.servicios;

import java.util.List;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuTiposEnlaces;

/**
 *
 * @author Yessenia
 */
public interface MnuEnlacesServicios {

    public List<MnuTiposEnlaces> listarMnuTiposEnlaces();

    public List<MnuEnlaces> listarMnuEnlaces();
    
    public void registrarMnuTiposEnlaces(MnuTiposEnlaces mnuTiposEnlaces);
    
    public void registrarMnuEnlaces(MnuEnlaces mnuEnlaces);
    
    public void modificarMnuEnlaces(MnuEnlaces mnuEnlaces);
    
    public void EliminarMnuEnlaces(MnuEnlaces mnuEnlaces);

    public MnuEnlaces buscarMnuEnlacesPorIdMnuEnlace(Long idMnuEnlace);

}
