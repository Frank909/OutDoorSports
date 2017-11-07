package outdoorapp.business.applicationservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.pagination.SQL2008StandardLimitHandler;

import javafx.scene.control.TextInputDialog;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.interfaces.Iscrizione_DAO;
import outdoorapp.integration.dao.interfaces.Partecipante_DAO;
import outdoorapp.integration.dao.interfaces.StatoIscrizione_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.EmailConfig;
import outdoorapp.utils.SessionCache;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di Iscrizioni.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * una Iscrizione, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceIscrizione implements Views, Actions{

	private DAOFactory genericFactory = null, statoFactory = null;
	private TOFactory TOFact = null;
	
	private Iscrizione_DAO iscrizione_dao = null;
	private IscrizioneTO iscrizione = null;
	private StatoIscrizione_DAO stato_iscrizione_dao = null;
	private EmailTO email = null;
	
	
	public ApplicationServiceIscrizione() {
		if(iscrizione_dao == null){
			genericFactory = FactoryProducerDAO.getFactory(DAORequest.Generic);
			iscrizione_dao = (Iscrizione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.Iscrizione);
		}
		if(stato_iscrizione_dao == null){
			statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
			stato_iscrizione_dao = (StatoIscrizione_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Iscrizione);
		}
		if(email == null){
			TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
			email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);
		}
	}
	
	/**
	 * Metodo che restituisce tutte le iscrizioni
	 * ad una determinata escursione.
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllIscrittiFromEscursione(Request request){
		Response response = new Response();
		EscursioneTO escursione = (EscursioneTO)SessionCache.getCurrentData("Escursione");
		
		try {
			List<IscrizioneTO> list_iscrizioni = iscrizione_dao.getAllIscrittiFromEscursione((EscursioneTO)SessionCache.getCurrentData("Escursione"));
			response.setData(list_iscrizioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
	
	/**
	 * Metodo che annulla l'iscrizione di
	 * un determinato partecipante
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta in base alla richiesta
	 */
	public Response annullaIscrizione(Request request){
		Response response = new Response();
		IscrizioneTO iscrizione = (IscrizioneTO) request.getData();
		try {
			iscrizione.setStatoIscrizione(stato_iscrizione_dao.getStatoDisattivo());
		} catch (DatabaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("OutDoorSports 1.0");
		dialog.setHeaderText("Intendi annullare l'iscrizione del Partecipante?");
		dialog.setContentText("Motivazione:");
		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			try {
				iscrizione = iscrizione_dao.annullaIscrizione(iscrizione);
				
				EmailTO email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);

				String mailOggetto = "OutDoorSports | Iscrizione Annullata";
				String mailMessaggio = "Gentile ";
				mailMessaggio += iscrizione.getUtente().getNome() + " " + iscrizione.getUtente().getCognome() + ", \n";
				mailMessaggio += "La tua iscrizione per l'escursione " + iscrizione.getEscursione().getNome();
				mailMessaggio += " è stata cancellata. \n";
				mailMessaggio += "Motivo: " + result.get() + " \n";
				mailMessaggio += "Ci scusiamo per il disagio";

				email.setOggetto(mailOggetto);
				email.setMessaggio(mailMessaggio);

				ArrayList<UtenteTO> listaDestinatari = new ArrayList<>();
				listaDestinatari.add(iscrizione.getUtente());
				email.setListaDestinatari(listaDestinatari);

				EmailConfig emailConfig = new EmailConfig();
				emailConfig.sendEmail(email);
				
				response.setData(iscrizione);
				response.setResponse(RESP_OK);
			} catch (DatabaseException e) {
				response.setResponse(RESP_KO);
				e.printStackTrace();
			}
		}
		return response;
	}

}
