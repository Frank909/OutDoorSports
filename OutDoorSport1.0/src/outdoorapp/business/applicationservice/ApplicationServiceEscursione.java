package outdoorapp.business.applicationservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import outdoorapp.integration.dao.interfaces.OptionalIscrizione_DAO;
import outdoorapp.integration.dao.interfaces.StatoEscursione_DAO;
import outdoorapp.integration.dao.interfaces.StatoIscrizione_DAO;
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
import outdoorapp.to.interfaces.OptionalIscrizioneTO;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoEscursioneTO;
import outdoorapp.to.interfaces.StatoIscrizioneTO;
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
			statoFactory = null;

	private Escursione_DAO escursione_dao = null;
	private OptionalEscursione_DAO optional_escursione_dao = null;
	private StatoOptional_DAO stato_optional_dao = null;
	private Iscrizione_DAO iscrizione_dao = null;
	private EscursioneTO escursione = null;
	private TOFactory OptionalFact = null;
	private StatoOptionalTO stato_disattivo = null;
	private StatoEscursione_DAO statoEscursioneDao = null;
	private OptionalIscrizione_DAO optionalIscrizioneDao = null;
	private StatoOptionalTO stato_attivo = null;
	private List<IscrizioneTO> list_iscrizioni = null;
	private StatoIscrizione_DAO stato_iscrizione_dao = null;
	

	public ApplicationServiceEscursione() {
		genericFactory = FactoryProducerDAO.getFactory(DAORequest.Generic);
		statoFactory = FactoryProducerDAO.getFactory(DAORequest.State);
		escursione_dao =  (Escursione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.Escursione);
		optional_escursione_dao = (OptionalEscursione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.OptionalEscursione);
		stato_optional_dao = (StatoOptional_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Optional);
		statoEscursioneDao = (StatoEscursione_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Escursione);
		OptionalFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
		iscrizione_dao = (Iscrizione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.Iscrizione);
		optionalIscrizioneDao = (OptionalIscrizione_DAO) genericFactory.getGenericDAO(GenericDAOEnum.OptionalIscrizione);
		stato_iscrizione_dao = (StatoIscrizione_DAO) statoFactory.getStatoDAO(StatoDAOEnum.Iscrizione);
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
			List<StatoEscursioneTO> list_stato_escursione = statoEscursioneDao.getAll();
			
			for(EscursioneTO e : list_escursioni){
				LocalDate today = LocalDate.now();
				if(today.toString().equals(e.getData())){
					e.setStatoEscursione(list_stato_escursione.get(3));
					escursione_dao.update(e);
				}else{
					LocalDate eDate = LocalDate.parse(e.getData());
					if(eDate.isBefore(today)){
						e.setStatoEscursione(list_stato_escursione.get(0));
						escursione_dao.update(e);
					}
				}
			}
			
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
				
				if(mde != null)
					escursione.setUtente(mde);
				
				Set<OptionalTO> optionals = new HashSet<>();
				optionals.addAll(escursione.getOptionals());
				Set<OptionalTO> newOptionals = new HashSet<>();
				stato_attivo = stato_optional_dao.getStatoAttivo();
				stato_disattivo = stato_optional_dao.getStatoDisattivo();
				OptionalEscursioneTO optional_escursione = (OptionalEscursioneTO) OptionalFact.getOptionalTO(OptionalEnum.OptionalEscursione);
				
				List<OptionalEscursioneTO> list_opt_e = new ArrayList<>();
				for(OptionalTO op : optionals){
					if(!op.getNome().contains(" " + stato_attivo.getNome()) && !op.getNome().contains(stato_disattivo.getNome())){
						newOptionals.add(op);
					}else{
						List<OptionalEscursioneTO> list = new ArrayList<>();
						list.addAll(optional_escursione_dao.getAssociationID(escursione, op));
						optional_escursione = list.get(0);
						if(op.getNome().contains(" " + stato_attivo.getNome())){
							optional_escursione.setStatoOptional(stato_attivo);
						}else if(op.getNome().contains(stato_disattivo.getNome())){
							optional_escursione.setStatoOptional(stato_disattivo);
						}
						op.setNome(op.getNome().substring(0, op.getNome().indexOf(" | ")));
						
						list_opt_e.add(optional_escursione);
						newOptionals.add(op);
					}
				}
				escursione.setOptionals(newOptionals);
				
				List<IscrizioneTO> list_iscritti = new ArrayList<>();
				list_iscritti.addAll(iscrizione_dao.getAllIscrittiFromEscursione(escursione));
				List<IscrizioneTO> list_iscritti_temp = new ArrayList<>();
				list_iscritti_temp.addAll(iscrizione_dao.getAllIscrittiFromEscursione(escursione));
				
				for(IscrizioneTO i : list_iscritti_temp){
					i.setOptionals(null);
					iscrizione_dao.update(i);
				}
				
				escursione_dao.update(escursione);
				
				List<OptionalEscursioneTO> list_opt_e_2 = new ArrayList<>();
				list_opt_e_2.addAll(optional_escursione_dao.getOptionalsFromEscursione(escursione.getIdEscursione()));
				
				for(OptionalEscursioneTO oe : list_opt_e){
					for(OptionalEscursioneTO oe2 : list_opt_e_2){
						if(oe.getOptional().getNome().equals(oe2.getOptional().getNome())){
							oe2.setStatoOptional(oe.getStatoOptional());
							optional_escursione_dao.update(oe2);
						}
					}
				}
				
				for(IscrizioneTO i : list_iscritti){
					Set<OptionalEscursioneTO> list_oe_from_iscrizione = i.getOptionals();
					for(OptionalEscursioneTO oe : list_oe_from_iscrizione){
						for(OptionalEscursioneTO oe_generic : list_opt_e_2){
							if(oe.getOptional().getNome().equals(oe_generic.getOptional().getNome())){
								oe.setId(oe_generic.getId());
								oe.setEscursione(oe_generic.getEscursione());
								oe.setStatoOptional(oe_generic.getStatoOptional());
								oe.setOptional(oe_generic.getOptional());
							}
						}
						TOFactory toFact = FactoryProducerTO.getFactory(FactoryEnum.OptionalTOFactory);
						OptionalIscrizioneTO optional_iscrizione = (OptionalIscrizioneTO) toFact.getOptionalTO(OptionalEnum.OptionalIscrizione);
						optional_iscrizione.setIdIscrizione(i.getIdIscrizione());
						optional_iscrizione.setIdOptionalEscursione(oe.getId());
						optionalIscrizioneDao.create(optional_iscrizione);
						optional_escursione_dao.update(oe);
					}
				}
				
				Alert alert = new Alert(AlertType.INFORMATION, "L'Escursione è stata modificata correttamente!", ButtonType.OK);
				alert.setTitle("OutDoorSport1.0");
				alert.showAndWait();
				response.setResponse(RESP_OK);

				list_iscrizioni = iscrizione_dao.getAllIscrittiFromEscursione(escursione);
				
				List<IscrizioneTO> list_iscrizioni_ordinata = ordinaIscritti(list_iscrizioni);
				List<StatoIscrizioneTO> list_stato_iscrizione = new ArrayList<>();
				List<IscrizioneTO> list_iscrizioni_da_annullare = new ArrayList<>();
				list_stato_iscrizione.addAll(stato_iscrizione_dao.getAll());
				
				int numero_iscritti = escursione.getIscritti();
				int i = 0;
				int iscritti = numero_iscritti;
				
				for(IscrizioneTO iscritto : list_iscrizioni_ordinata){
					if(i >= (numero_iscritti - 1)){
						iscritto.setStatoIscrizione(list_stato_iscrizione.get(0));
						iscrizione_dao.update(iscritto);
						list_iscrizioni_da_annullare.add(iscritto);
						iscritti--;
					}
					i++;
				}
				escursione.setIscritti(iscritti);
				escursione_dao.update(escursione);
				
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

				try {
					set_oe.addAll(optional_escursione_dao.getOptionalsFromEscursione(escursione.getIdEscursione()));
				} catch (DatabaseException e1) {
					e1.printStackTrace();
				}

				String string = "";
				if(set_oe.isEmpty())
					string = "Nessuno";
				else
					for(OptionalEscursioneTO e : set_oe){
						if(e.getStatoOptional().getIdStatoOptional() == stato_attivo.getIdStatoOptional()){
							string += e.getOptional().getNome() + " | ";
						}
					}

				mailMessaggio += "Optional Disponibili: " + string + "\n";

				ArrayList<UtenteTO> listaDestinatari = new ArrayList<>();

				for(IscrizioneTO in : list_iscrizioni){
					in.setOptionals(set_oe);
					listaDestinatari.add(in.getUtente());
				}

				email.setOggetto(mailOggetto);
				email.setMessaggio(mailMessaggio);

				email.setListaDestinatari(listaDestinatari);

				EmailConfig emailConfig = new EmailConfig();
				emailConfig.sendEmail(email);
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
						EmailTO email = (EmailTO) TOFact.getGenericTO(GenericEnum.Email);

						String mailOggetto = "OutDoorSports | Modifica escursione: " + escursione.getNome();
						String mailMessaggio = "Gentile Partecipante, \n";
						mailMessaggio += "La informiamo che il Manager di Escursione ha modificato il numero degli iscritti,\n";
						mailMessaggio += "e la sua iscrizione verrà annullata, in quanto una delle ultime. \n";
						mailMessaggio += "Ci scusiamo per il disagio. \n";

						ArrayList<UtenteTO> listaDestinatari = new ArrayList<>();
						
						for(IscrizioneTO in : list_iscrizioni_da_annullare){
							listaDestinatari.add(in.getUtente());
						}

						email.setOggetto(mailOggetto);
						email.setMessaggio(mailMessaggio);

						email.setListaDestinatari(listaDestinatari);

						EmailConfig emailConfig = new EmailConfig();
						emailConfig.sendEmail(email);
					}
				}).start();
				
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
	
	/**
	 * Metodo che ordina gli iscritti in base alla data e all'ora
	 * di iscrizione.
	 * 
	 * @param iscritti
	 */
	private List<IscrizioneTO> ordinaIscritti(List<IscrizioneTO> iscritti){
		Collections.sort(iscritti, new Comparator<IscrizioneTO>() {

			@Override
			public int compare(IscrizioneTO o1, IscrizioneTO o2) {
				IscrizioneTO iscrizione1 = (IscrizioneTO)o1;
				IscrizioneTO iscrizione2 = (IscrizioneTO)o2;
				int result = iscrizione1.getData().compareTo(iscrizione2.getData());
				if (result == 0) {
					// Strings are equal, sort by date
					return iscrizione1.getOra().compareTo(iscrizione2.getOra());
				}
				else {
					return result;
				}
			}
		});
		
		return iscritti;
	}
}
