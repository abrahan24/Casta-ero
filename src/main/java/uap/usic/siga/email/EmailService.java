package uap.usic.siga.email;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
