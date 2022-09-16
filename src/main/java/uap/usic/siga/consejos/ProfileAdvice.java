package uap.usic.siga.consejos;

import uap.usic.siga.email.EmailService;
import uap.usic.siga.servicios.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfileAdvice {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

//    @After("execution(* uap.usic.siga.modelos.ProfileRepositoryImpl.update(..))")
 /*   public void UpdateProfileEmail(JoinPoint jp) {
        Profile profile = (Profile) jp.getArgs()[0];
        Usuarios user = userService.getUserById(profile.getId());
        emailService.sendEmail(user.getEmail(), "Profile info", "Update Profile Sucessfully " +          "Your username is: " + user.getUsuario() + ". Your profile info is " + profile.toString());
    }
    */
}
