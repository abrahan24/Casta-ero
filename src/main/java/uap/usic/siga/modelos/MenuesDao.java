package uap.usic.siga.modelos;

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
public interface MenuesDao {

    public List<MnuTiposEnlaces> listarMenuesTiposEnlacesJPQL(Long idPnlCargo, Long idPersona);

    public List<MnuEnlaces> listarMenuesEnlacesJPQL(Long idPnlCargo, Long idPersona);

    public List<MnuFunciones> listarMenusFuncionesPorIdPersonaIdMnuTipoFuncionJPQL(Long idPersona, Long idMnuTipoFuncion);

    public List<MnuTiposFunciones> listarMenusTiposFuncionesPorIdPersonaJPQL(Long idPersona);

    public List<MnuEnlaces> listarMenuesEnlacesPorIdMnuTipoFuncionJPQL(Long idMnuTipoFuncion);

    public List<MnuTiposEnlaces> listarMenuesTiposEnlacesPorIdMnuTipoFuncionJPQL(Long idMnuTipoFuncion);

    public MnuFunciones buscarMenusFuncionesPorIdPersonaIdMnuTipoFuncionGET(Long idPersona, Long idMnuTipoFuncion,Long idSisAdministrador);

    //Menues
    public List<Menues> listarMenuesJPQL();

    public List<MnuTiposFunciones> listarMnuTiposFuncionesJPQL();

    public void registrarMenuesSET(Menues menues);

    public void registrarMnuTiposFuncionesSET(MnuTiposFunciones mnuTiposFunciones);

    public void modificarMenuesSET(Menues menues);

    public void EliminarMenuesSET(Menues menues);

    public Menues buscarMenuesPorIdMenuGET(Long idMenu);

    //MnuFunciones
    public List<MnuFunciones> listarMnuFuncionesJPQL();

    public List<SisAdministrador> listarSisAdministradorJPQL();

    public List<SisNivelesAccesos> listarSisNivelesAccesosJPQL();

    public void registrarMnuFuncionesSET(MnuFunciones mnuFunciones);

    public void registrarSisAdministradorSET(SisAdministrador sisAdministrador);

    public void modificarMnuFuncionesSET(MnuFunciones mnuFunciones);

    public void eliminaMnuFuncionesSET(MnuFunciones mnuFunciones);

    public MnuFunciones buscarMnuFuncionesPorIdMnuFuncionGET(Long idMnuFuncion);

    public void registrarSisNivelesAccesosSET(SisNivelesAccesos sisNivelesAccesos);

    public MnuTiposFunciones getTipoFuncion(Long id);
    
    public SisAdministrador getSisAdmin(Long id);
    
    public SisNivelesAccesos getNivelAcceso(Long id);
}
