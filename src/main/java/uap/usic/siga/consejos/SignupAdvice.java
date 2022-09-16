package uap.usic.siga.consejos;

import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.email.EmailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SignupAdvice {
    @Autowired
    private EmailService emailService;

    @AfterReturning("execution(* uap.usic.siga.servicios.impl.UserServiceImpl.addUser(..))")
    public void aopEmail(JoinPoint jp) {
        //Usuarios user = (Usuarios) jp.getArgs()[0];
       // emailService.sendEmail(user.getEmail(), "Registration info", "Registered Sucessfully to Programming discussion forum!! " +
       //         "Your username is: " + user.getUsuario() + ". Share your idea in PdF...");
    }
}
