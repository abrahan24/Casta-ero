package uap.usic.siga.servicios.impl;

import uap.usic.siga.entidades.PdfUserDetails;
import uap.usic.siga.modelos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.Usuarios;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        log.info("loadUserByUsername() : {}", username);

        return new PdfUserDetails(user);
    }
}
