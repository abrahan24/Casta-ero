package uap.usic.siga.modelos;

import java.util.List;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;

/**
 *
 * @author fmbma
 */
public interface UsuariosDao {

    public List<Roles> listarRolesGET();

    public Usuarios buscarUsuariosPorIdUsuarioGET(Long idUsuario);
    
    public Usuarios buscarPnlPersonalAdmnistrativoPorIdPersonaGET(Long idPersona);
    
    List<Usuarios> listarUsuariosRegistradosJPQL();
    
    public void modificarUsuariosSET(Usuarios usuarios);
    
    public void eliminarUsuariosSET(Usuarios usuarios);
    
    
    
}
