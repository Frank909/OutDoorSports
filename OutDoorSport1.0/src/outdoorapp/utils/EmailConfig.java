package outdoorapp.utils;

import javax.mail.*;  
import javax.mail.internet.*;

import java.util.Properties;

import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.GenericEnum;

/**
 * Classe che permette di configurare l'email
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class EmailConfig {

	private EmailTO email = null;

	/**
	 * Costruttore della classe EmailConfig che inizializza il controllo Email
	 */
	public EmailConfig() {
		if(email == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);
		}
	}

	/** 
	 * @return props: Restituisce le proprietà dell'email.
	 */
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

	/**
	 * Metodo che viene utilizzato per mandare l'email.
	 * @param email: Email in ingresso da mandare
	 */
	public void sendEmail(EmailTO email) {
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

			for(UtenteTO utente: email.getListaDestinatari()){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utente.getEmail()));
				Transport.send(message);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return;
	}
}
