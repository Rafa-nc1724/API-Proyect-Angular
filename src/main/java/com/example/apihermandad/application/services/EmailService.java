package com.example.apihermandad.application.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.apihermandad.application.dto.UsuarioCreateDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendEmailOnUserCreation(UsuarioCreateDto user) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
    
    helper.setTo(user.getEmail());
    helper.setSubject("¡Bienvenido/a a la hermandad!");

    String html = "<!doctype html>" +
        "<html lang=\"es\">" +
        "<body style=\"margin:0; padding:0; background-color:#f6f9fc;\">" +

        "  <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"background-color:#f6f9fc; padding:24px 0;\">"
        +
        "    <tr>" +
        "      <td align=\"center\">" +

        "        <table role=\"presentation\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"width:600px; max-width:600px; background:#ffffff; border:1px solid #e6e9ef; border-radius:12px;\">"
        +
        "          <tr>" +
        "            <td style=\"padding:24px 24px 12px 24px; font-family:Arial, sans-serif; color:#111827;\">" +
        "              <h2 style=\"margin:0; font-size:18px; font-weight:700;\">Tu cuenta ha sido creada</h2>" +
        "            </td>" +
        "          </tr>" +

        "          <tr>" +
        "            <td style=\"padding:0 24px 16px 24px; font-family:Arial, sans-serif; color:#374151; font-size:14px; line-height:20px;\">"
        +
        "              <p style=\"margin:0 0 12px 0;\">Hola " + user.getName() + ",</p>" +
        "              <p style=\"margin:0;\">Esta es tu contraseña temporal. Por favor, cámbiala al iniciar sesión.</p>"
        +
        "            </td>" +
        "          </tr>" +

        "          <tr>" +
        "            <td align=\"center\" style=\"padding:8px 24px 20px 24px;\">" +
        "              <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" style=\"background:#f3f4f6; border:1px dashed #9ca3af; border-radius:10px;\">"
        +
        "                <tr>" +
        "                  <td style=\"padding:14px 18px; font-family:Consolas, Menlo, Monaco, monospace; font-size:22px; letter-spacing:1px; color:#111827;\">"
        +
        "                   " + user.getPassword() +
        "                  </td>" +
        "                </tr>" +
        "              </table>" +
        "            </td>" +
        "          </tr>" +

        "          <tr>" +
        "            <td style=\"padding:0 24px 24px 24px; font-family:Arial, sans-serif; color:#6b7280; font-size:12px; line-height:18px;\">"
        +
        "              <p style=\"margin:0;\">Si tú no has solicitado esta cuenta, puedes ignorar este correo.</p>" +
        "            </td>" +
        "          </tr>" +
        "        </table>" +

        "      </td>" +
        "    </tr>" +
        "  </table>" +
        "</body>" +
        "</html>";
    ;

    helper.setText(html, true);

    mailSender.send(mimeMessage);
  }
}
