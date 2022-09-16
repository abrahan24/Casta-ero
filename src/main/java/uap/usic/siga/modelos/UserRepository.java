package uap.usic.siga.modelos;


import java.util.List;
import uap.usic.siga.entidades.Usuarios;

public interface UserRepository extends CrudRepository<Usuarios, Long> {
    Usuarios getUserByUsername(String username);

    List<Usuarios> getAllAdmins();
}
