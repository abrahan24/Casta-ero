package uap.usic.siga.servicios;

import java.util.List;

import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;

public interface UserService {

    Long addUser(Usuarios user);

    List<Usuarios> getAllUsers();

    Usuarios getUserByUsername(String username);

    Usuarios getUserById(Long id);

    List<Usuarios> getAllAdmins();
    
    //public Roles getRol(L)

    //public void updateUser(Usuarios user);
}
