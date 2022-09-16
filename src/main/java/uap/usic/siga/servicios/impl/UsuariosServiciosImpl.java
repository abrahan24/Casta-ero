package uap.usic.siga.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.modelos.UsuariosDao;
import uap.usic.siga.servicios.UsuariosServicios;

/**
 *
 * @author fmbma
 */
@Service("usuariosServicios")
@Transactional
public class UsuariosServiciosImpl implements UsuariosServicios {
    
    @Autowired
    private UsuariosDao dao;
    
    @Override
    public List<Roles> listarRoles() {
        return dao.listarRolesGET();
    }
    
    @Override
    public Usuarios buscarUsuariosPorIdUsuario(Long idUsuario) {
        return dao.buscarUsuariosPorIdUsuarioGET(idUsuario);
    }
    
    @Override
    public Usuarios buscarPnlPersonalAdmnistrativoPorIdPersona(Long idPersona) {
        return dao.buscarPnlPersonalAdmnistrativoPorIdPersonaGET(idPersona);
    }
    
    @Override
    public List<Usuarios> listarUsuariosRegistrados() {
        return dao.listarUsuariosRegistradosJPQL();
    }
    
    @Override
    public void modificarUsuarios(Usuarios usuarios) {
        dao.modificarUsuariosSET(usuarios);
    }
    
    @Override
    public void eliminarUsuarios(Usuarios usuarios) {
        dao.eliminarUsuariosSET(usuarios);
    }
    
}
