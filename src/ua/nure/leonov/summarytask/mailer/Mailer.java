package ua.nure.leonov.summarytask.mailer;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


/**
 * The Class of Mailer that send emails for users
 */
public class Mailer {

    /**
     * The constant logger.
     */
    private static final Logger LOG = Logger.getLogger(Mailer.class);

    /**
     * The constant properties.
     */
    final private Properties properties = new Properties();

    public Mailer(){}

    /**
     * Method that send new password for user by email
     * @param pass the new password
     * @param recipient the email of recipient
     * @return true if sending email is successful
     */
    public boolean sendMail(String pass, String recipient) throws MailerException {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("resources/mail.properties"));

            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("Periodicals"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Password recovery");
            message.setSentDate(new Date());

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Hi, your new password on PeriodicalsService: " + pass);

            multipart.addBodyPart(textBodyPart);  // add the text part
            message.setContent(multipart);


            LOG.info("Sending message with Mailer");

            Transport tr = mailSession.getTransport();
            tr.connect(null, "xmdmvozxqeitippp");
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (MessagingException e) {
            throw new MailerException("Error during sending email" + e, e);
        } catch (IOException e) {
            throw new MailerException("Error during getting mail.properties" + e, e);
        }
        LOG.info("Message was sent!");
        return true;
    }
}
