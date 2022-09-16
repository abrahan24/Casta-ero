package uap.usic.siga.servicios.impl;

import java.util.List;
import uap.usic.siga.modelos.UserRepository;
import uap.usic.siga.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uap.usic.siga.entidades.Usuarios;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Long addUser(Usuarios user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.add(user);
    }

    @Override
    public List<Usuarios> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Usuarios getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public Usuarios getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<Usuarios> getAllAdmins() {
        return userRepository.getAllAdmins();
    }
/*
    @Override
    public void updateUser(Usuarios user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.updateUser(user);

    }
*/
}
