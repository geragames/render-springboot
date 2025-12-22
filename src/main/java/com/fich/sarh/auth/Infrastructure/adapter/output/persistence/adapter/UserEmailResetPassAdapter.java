package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.auth.Application.ports.output.persistence.UserSendEmailResetPasswordSpiPort;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.email.MailProperties;
import com.fich.sarh.common.PersistenceAdapter;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

@PersistenceAdapter
public class UserEmailResetPassAdapter implements UserSendEmailResetPasswordSpiPort {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

    public UserEmailResetPassAdapter(JavaMailSender mailSender, MailProperties mailProperties) {
        this.mailSender = mailSender;
        this.mailProperties = mailProperties;
    }


   @Async
    @Override
    public void sendEmailResetPassword(String newPassword, String toEmail)  {

        MimeMessage message = mailSender.createMimeMessage();
         try {
             MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
             helper.setFrom(mailProperties.getFrom());
             helper.setTo(toEmail);
             helper.setSubject("Reestablecimiento de Contraseña");
             helper.setText(buildMessage(newPassword));
             mailSender.send(message);


         }catch (Exception e){
             throw new IllegalStateException("Error enviando correo de reseteo ", e);
         }

    }

    private String buildMessage(String newPassword) {
        return """
               Su contraseña ha sido restablecida correctamente.

               Contraseña provisoria: %s

               Por favor, cambie su contraseña al iniciar sesión.
               """.formatted(newPassword);
    }
}
