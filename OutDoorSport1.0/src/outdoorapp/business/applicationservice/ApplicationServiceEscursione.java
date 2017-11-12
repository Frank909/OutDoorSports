package outdoorapp.business.applicationservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.DAOFactory;
import outdoorapp.integration.dao.FactoryProducerDAO;
import outdoorapp.integration.dao.enums.DAORequest;
import outdoorapp.integration.dao.enums.GenericDAOEnum;
import outdoorapp.integration.dao.enums.StatoDAOEnum;
import outdoorapp.integration.dao.interfaces.Escursione_DAO;
import outdoorapp.integration.dao.interfaces.Iscrizione_DAO;
import outdoorapp.integration.dao.interfaces.OptionalEscursione_DAO;
import outdoorapp.integration.dao.interfaces.StatoEscursione_DAO;
import outdoorapp.integration.dao.interfaces.StatoOptional_DAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.enums.OptionalEnum;
import outdoorapp.to.interfaces.EmailTO;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.OptionalEscursioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.StatoOptionalTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.UtenteTO;
import outdoorapp.utils.Actions;
import outdoorapp.utils.EmailConfig;
import outdoorapp.utils.SessionCache;

/**
 * Classe che modella e implementa l'Application Service per la gestione di una Escursione.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * una Escursione, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceEscursione implements Actions {

	private DAOFactory genericFactory = null, 
			statoFactory = null, tipoFactory = null;
	
	private Escursione_DAO escursione_dao = null;
	private OptionalEscursione_DAO optional_escursione_dao = null;
	private StatoOptional_DAO stato_optional_dao = null;
	private Iscrizione_DAO iscrizione_dao = null;
	private EscursioneTO escursione = null;
	private TOFactory OptionalFact = null;
	private OptionalEscursioneTO optional_escursione = null;
	private StatoEscursione_DAO statoEscursioneDao = null;
	
	public ApplicationServiceEscursione() {
		genericFactory = FactoryProducerDAO.getFactory(DAORequest.Generic);
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
		escursione_dao =  (Escursione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.Escursione);
		optional_escursione_dao = (OptionalEscursione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.OptionalEscursione);
		stato_optional_dao = (StatoOptional_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Optional);
		statoEscursioneDao = (StatoEscursione_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Escursione);
		OptionalFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
		iscrizione_dao = (Iscrizione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.Iscrizione);
	}
	
	/**
	 * Metodo che restituisce tutte le Escursioni 
	 * presenti nel sistema
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllEscursioni(Request request){
		Response response = new Response();
		
		try {
			List<EscursioneTO> list_escursioni = escursione_dao.getAll();
			response.setData(list_escursioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
	
	/**
	 * Metodo che restituisce tutte le escursione aperte a cui il partecipante non è iscritto
	 * @param request
	 * @return response: risposta in base alla richiesta
	 */
	public Response getAllEscursioniAperte(Request request){
		Response response = new Response();
		try {
			DAOFactory daofact = (DAOFactory) FactoryProducerDAO.getFactory(DAORequest.Generic);
			Escursione_DAO escursione_dao = (Escursione_DAO) daofact.getGenericDAO(GenericDAOEnum.Escursione);
			List<EscursioneTO> list_escursioni = escursione_dao.readEscursioniAperte();
			response.setData(list_escursioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
	
	/**
	 * Metodo che restituisce tutte le escursioni di un determinato 
	 * Manager di Escursione
	 * 
	 * @param request: Richiesta in ingresso
	 * @return response: Risposta rispetto alla richiesta
	 */
	public Response getAllEscursioniFromMDE(Request request){
		Response response = new Response();
		
		try {
			List<EscursioneTO> list_escursioni = escursione_dao.readEscursioniByManagerDiEscursione((ManagerDiEscursioneTO)SessionCache.getCurrentData("ManagerDiEscursione"));
			response.setData(list_escursioni);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			e.printStackTrace();
			response.setResponse(RESP_KO);
		}
		return response;
	}
	
	/**
	 * Metodo che restituisce una risposta in base a una richiesta, e inserisce 
	 * una nuova Escursione nel sistema
	 * 
	 * @param request
	 * @return una risposta in base alla richiesta
	 */
	public Response nuovaEscursione(Request request){
		Response response = new Response();
		
		try {
			if(!escursione_dao.esisteEscursione((EscursioneTO)request.getData())){
				escursione = (EscursioneTO)request.getData();
				escursione.setStatoEscursione(statoEscursioneDao.getStatoEscursioneAperta());
				ManagerDiEscursioneTO mde = (ManagerDiEscursioneTO) SessionCache.getCurrentData("ManagerDiEscursione");
				escursione.setUtente(mde);
				escursione_dao.create(escursione);
				Alert alert = new Alert(AlertType.INFORMATION, "L'Escursione è stata inserita correttamente!", ButtonType.OK);
				alert.setTitle("OutDoorSport1.0");
				alert.showAndWait();
				response.setResponse(RESP_OK);
			}else{
				response.setResponse(RESP_KO);
			}
			response.setData(null);
		} catch (DatabaseException e) {
			Alert alert = new Alert(AlertType.ERROR, "Alcuni campi non sono corretti!", ButtonType.OK);
			alert.setTitle("OutDoorSport1.0");
			alert.showAndWait();
		}
		
		return response;
	}
	
	/**
	 * Metodo che restituisce una risposta in base a una richiesta, e modifica
	 * una escursione presente nel sistema
	 * 
	 * @param request
	 * @return una risposta in base alla richiesta
	 */
	public Response modificaEscursione(Request request){
		Response response = new Response();
		escursione = (EscursioneTO) request.getData();
		EscursioneTO temp = null;
		int id_temp = -1;
		
		try {
			temp = escursione_dao.readById(escursione.getIdEscursione().intValue());
			if(temp.getIdEscursione() != null)
				id_temp = temp.getIdEscursione().intValue();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		try {
			if(!(escursione_dao.esisteEscursione(escursione) || temp == null) || (escursione_dao.esisteEscursione(escursione) && escursione.getIdEscursione() == id_temp)){
				StatoEscursione_DAO statoEscursioneDao = (StatoEscursione_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Escursione);
				escursione.setStatoEscursione(statoEscursioneDao.getStatoEscursioneAperta());
				ManagerDiEscursioneTO mde = (ManagerDiEscursioneTO) SessionCache.getCurrentData("ManagerDiEscursione");
				if(mde != null){
					escursione.setUtente(mde);
				}
				Set<OptionalTO> optionals = new HashSet<>();
				optionals = escursione.getOptionals();
				escursione.setOptionals(null);
				Set<OptionalTO> newOptionals = new HashSet<>();
				StatoOptionalTO stato_attivo = stato_optional_dao.getStatoAttivo();
				StatoOptionalTO stato_disattivo = stato_optional_dao.getStatoDisattivo();
				OptionalEscursioneTO optional_escursione = (OptionalEscursioneTO) OptionalFact.getOptionalTO(OptionalEnum.OptionalEscursione);
				for(OptionalTO op : optionals){
					optional_escursione.setEscursione(escursione);
					if(!op.getNome().contains(stato_attivo.getNome()) && !op.getNome().contains(stato_disattivo.getNome())){
						newOptionals.add(op);
					}else{
						if(op.getNome().contains(stato_attivo.getNome())){
							List<OptionalEscursioneTO> list = optional_escursione_dao.getAssociationID(escursione, op);
							op.setNome(op.getNome().substring(0, op.getNome().indexOf(" | ")));
						}else{
							List<OptionalEscursioneTO> list = optional_escursione_dao.getAssociationID(escursione, op);
							op.setNome(op.getNome().substring(0, op.getNome().indexOf(" | ")));
						}
						newOptionals.add(op);
					}
				}
				escursione.setOptionals(newOptionals);
				escursione_dao.update(escursione);
				
				Alert alert = new Alert(AlertType.INFORMATION, "L'Escursione è stata modificata correttamente!", ButtonType.OK);
				alert.setTitle("OutDoorSport1.0");
				alert.showAndWait();
				response.setResponse(RESP_OK);
				
				TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
				EmailTO email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);

				String mailOggetto = "OutDoorSports | Modifica escursione: " + escursione.getNome();
				String mailMessaggio = "Gentile Partecipante, \n";
				mailMessaggio += "La informiamo che il Manager di Escursione ha apportato le seguenti modifiche:\n";
				mailMessaggio += "Nome: " + escursione.getNome() + "\n";
				mailMessaggio += "Numero minimo di partecipanti: " + escursione.getNumberMin() + "\n";
				mailMessaggio += "Numero massimo di partecipanti: " + escursione.getNumberMax() + "\n";
				mailMessaggio += "Costo Base: " + escursione.getCosto() + "\n";
				mailMessaggio += "Data: " + escursione.getData() + "\n";
				mailMessaggio += "Descrizione " + escursione.getDescrizione() + "\n";
				mailMessaggio += "Tipo Escursione " + escursione.getTipoEscursione().getNome() + "\n";
				Set<OptionalEscursioneTO> set_oe = new HashSet<>();
				set_oe.addAll(optional_escursione_dao.getOptionalsFromEscursione(escursione.getIdEscursione()));
				String string = "";
				for(OptionalEscursioneTO e : set_oe){
					if(e.getStatoOptional() == stato_attivo){
						string += e.getOptional().getNome() + " | ";
					}
				}
				mailMessaggio += "Optional Disponibili: " + string + "\n";
				
				ArrayList<UtenteTO> listaDestinatari = new ArrayList<>();
				
				List<IscrizioneTO> list_iscrizioni = iscrizione_dao.getAllIscrittiFromEscursione(escursione);
				for(IscrizioneTO i : list_iscrizioni){
					i.setOptionals(set_oe);
					listaDestinatari.add(i.getUtente());
				}
				
				email.setOggetto(mailOggetto);
				email.setMessaggio(mailMessaggio);

				email.setListaDestinatari(listaDestinatari);

				EmailConfig emailConfig = new EmailConfig();
				emailConfig.sendEmail(email);
				
				response.setResponse(RESP_OK);
			}else{
				response.setResponse(RESP_KO);
			}
			response.setData(null);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Metodo che restituisce una risposta in base alla richiesta, e
	 * annulla una escursione
	 * 
	 * @param request
	 * @return response
	 */
	public Response annullaEscursione(Request request){
		Response response = new Response();
		EscursioneTO escursione = (EscursioneTO) request.getData();
		try {
			escursione.setStatoEscursione(statoEscursioneDao.getStatoEscursioneAnnullata());
			escursione_dao.update(escursione);
			response.setData(escursione);
			response.setResponse(RESP_OK);
		} catch (DatabaseException e) {
			response.setResponse(RESP_KO);
		}
		
		return response;
	}
	
}
