package com.br.stylesync.utils;

import com.br.stylesync.model.Employee;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {
    public static void sendEmail(Employee employee, String activationLink) {
        final String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Ativação de Conta</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h2>Ativação de Conta</h2>\n" +
                "    <p>Olá,</p>\n" +
                "    <p>Clique no link abaixo para ativar sua conta:</p>\n" +
                "    <a href=\"" + activationLink + "\">Ativar Conta</a>\n" +
                "    <p>Se você não solicitou a ativação, ignore este email.</p>\n" +
                "</body>\n" +
                "</html>";
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("oficialstylesync@gmail.com",
                                "stylesync123");
                    }
                });
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("oficialstylesync@gmail.com"));

            Address[] toUser = InternetAddress
                    .parse(employee.getEmail());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Ativação de Conta - Style Sync");
            message.setText(htmlContent);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
