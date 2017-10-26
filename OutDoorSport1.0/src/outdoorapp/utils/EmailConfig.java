package outdoorapp.utils;

import javax.mail.*;  
import javax.mail.internet.*;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;

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
	 * Metodo che viene utilizzato per mandare l'email, notificando l'avvenuto invio, in caso contrario mostra un
	 * messaggio di errore.
	 * @param email: Email in ingresso da mandare
	 */
	public void sendEmail(EmailTO email) {
		ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
		emailExecutor.execute(new Runnable(){

			@Override
			public void run() {
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
					
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Alert alert = new Alert(AlertType.INFORMATION, "Email di servizio inviata con successo!", ButtonType.OK);
							alert.setTitle("OutDoorSport 1.0");
							alert.setHeaderText("Email Service");
							alert.showAndWait();
						}
					});
					
				} catch (MessagingException e) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Alert alert = new Alert(AlertType.ERROR, "Invio dell'email di servizio fallita.", ButtonType.OK);
							alert.setTitle("OutDoorSport 1.0");
							alert.setHeaderText("Email Service");
							alert.showAndWait();
						}
					});
				}
				
			}});
		
		
		
	}
}
