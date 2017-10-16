package outdoorapp.utils;

import javax.mail.*;  
import javax.mail.internet.*;

import java.util.Properties;

import javax.activation.*;  
import outdoorapp.to.Email;
import outdoorapp.to.Utente;

/**
 * Classe che permette di configurare l'email
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class EmailConfig {

	Email email;
	
	public EmailConfig() {
		email = new Email();
	}
	
	private Properties createMailProperties(){
		Properties props = new Properties();
		
		props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.port", 587);

		return props;
	}
	
	public void sendEmail(Email email) {
		final String username = "outdoorsportszitoventura@gmail.com";
		final String password = "20172018";

		Properties props = createMailProperties();

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));


			message.setSubject(email.getOggetto());
			message.setText(email.getMessaggio());

			for(Utente utente: email.getListaDestinatari()){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utente.getEmail()));
				Transport.send(message);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return;
	}
}
