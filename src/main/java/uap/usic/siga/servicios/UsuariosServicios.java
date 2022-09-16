package uap.usic.siga.servicios;

import java.util.List;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;

/**
 *
 * @author fmbma
 */
public interface UsuariosServicios {

    public List<Roles> listarRoles();

    public Usuarios buscarUsuariosPorIdUsuario(Long idUsuario);

    public Usuarios buscarPnlPersonalAdmnistrativoPorIdPersona(Long idPersona);

    List<Usuarios> listarUsuariosRegistrados();

    public void modificarUsuarios(Usuarios usuarios);

    public void eliminarUsuarios(Usuarios usuarios);

}
