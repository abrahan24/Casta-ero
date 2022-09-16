package uap.usic.siga.servicios;

import java.util.List;
import uap.usic.siga.entidades.Menues;
import uap.usic.siga.entidades.MnuEnlaces;
import uap.usic.siga.entidades.MnuFunciones;
import uap.usic.siga.entidades.MnuTiposEnlaces;
import uap.usic.siga.entidades.MnuTiposFunciones;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.entidades.SisNivelesAccesos;

/**
 *
 * @author fmbma
 */
public interface MenuesServicios {

    public List<MnuTiposEnlaces> listarMenuesTiposEnlacesJPQL(Long idPnlCargo, Long idPersona);

    public List<MnuEnlaces> listarMenuesEnlaces(Long idPnlCargo, Long idPersona);

    public List<MnuFunciones> listarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(Long idPersona, Long idMnuTipoFuncion);

    public List<MnuTiposFunciones> listarMenusTiposFuncionesPorIdPersona(Long idPersona);

    public List<MnuEnlaces> listarMenuesEnlacesPorIdMnuTipoFuncion(Long idMnuTipoFuncion);

    public List<MnuTiposEnlaces> listarMenuesTiposEnlacesPorIdMnuTipoFuncion(Long idMnuTipoFuncion);

    public MnuFunciones buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncion(Long idPersona, Long idMnuTipoFuncion,Long idSisAdministrador);
    
     //Menues
    public List<Menues> listarMenues();

    public List<MnuTiposFunciones> listarMnuTiposFunciones();

    public void registrarMenues(Menues menues);

    public void registrarMnuTiposFunciones(MnuTiposFunciones mnuTiposFunciones);

    public void modificarMenues(Menues menues);

    public void EliminarMenues(Menues menues);

    public Menues buscarMenuesPorIdMenu(Long idMenu);
    
     //MnuFunciones
    public List<MnuFunciones> listarMnuFunciones();
    
    public List<SisAdministrador> listarSisAdministrador();
    
    public List<SisNivelesAccesos>listarSisNivelesAccesos();
          
    public void registrarMnuFunciones(MnuFunciones mnuFunciones);
    
    public void registrarSisAdministrador(SisAdministrador sisAdministrador);
    
    public void modificarMnuFunciones(MnuFunciones mnuFunciones);
    
    public void eliminaMnuFunciones(MnuFunciones mnuFunciones);
    
    public MnuFunciones buscarMnuFuncionesPorIdMnuFuncion(Long idMnuFuncion);
    
    public void registrarSisNivelesAccesos(SisNivelesAccesos sisNivelesAccesos);
    
    public SisAdministrador getSisAdmin(Long id);
    
    public MnuTiposFunciones getTipoFuncion(Long id);
    
    public SisNivelesAccesos getNivelAcceso(Long id);
    
    

}
