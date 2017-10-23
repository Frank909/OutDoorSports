package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Iscrizione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.StatoIscrizioneTO;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.GenericEnum;

import java.util.ArrayList;
import java.util.List;


/** 
 * Data Access Object per tutte le operazioni CRUD per Iscrizione.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class IscrizioneDAO extends GenericDAO<IscrizioneTO> implements Iscrizione_DAO{

	/**
	 * Il costruttore inizializza l'entità Iscrizione da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	private TOFactory TOFactory = null;
	
	public IscrizioneDAO() {
		if(TOFactory == null)
			TOFactory = FactoryProducerTO.getFactory(FactoryEnum.GenericTOFactory);
		
		this.setCurrentClass(TOFactory.getGenericTO(GenericEnum.Iscrizione));
	}
	
	/**
	 * @return la lista di tutte le iscrizioni
	 * @throws DatabaseException
	 */
	@Override
	public List<IscrizioneTO> getAll() throws DatabaseException{
		List<IscrizioneTO> response = super.getAll();
		return response;
	}
	
	/**
	 * @param iscrizione
	 * @return l'iscrizione cambiando lo stato in annullata
	 * @throws DatabaseException
	 */
	@Override
	public IscrizioneTO annullaIscrizione(IscrizioneTO iscrizione) throws DatabaseException {
		super.update(iscrizione);
		return iscrizione;
	}
	
	/**
	 * @param iscrizione
	 * @return vero se esiste l'iscrizione, falso altrimenti
	 * @throws DatabaseException
	 */
	@Override
	public boolean esisteIscrizione(IscrizioneTO iscrizione) throws DatabaseException {
		IscrizioneTO newIscrizione = this.findOne(iscrizione.getIdIscrizione());
		return newIscrizione != null;
	}
	
	/**
	 * 
	 * @param partecipante
	 * @return la lista delle iscrizioni attive di un partecipante
	 * @throws DatabaseException
	 */
	@Override
	public List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO partecipante) throws DatabaseException {
		List<PartecipanteTO> param = new ArrayList<PartecipanteTO>();
		param.add(partecipante);
		List<IscrizioneTO> response = super.executeParamQuery("BOOOOOOOOh", param);
		return response;
	}
	
	/**
	 * @return lo stato terminato di un iscrizione
	 * @throws DatabaseException
	 */
	private StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException {
		StatoIscrizioneTO statoIscrizione = (StatoIscrizioneTO) super.executeQuery("BOOOOOOH");
		return statoIscrizione;
	}
	
	/**
	 * Modifica lo stato delle Iscrizioni di una determinata Escursione 
	 * in termininato
	 * 
	 * @param escursione
	 * @throws DatabaseException
	 */
	@Override
	public void terminaIscrizioni(EscursioneTO escursione) throws DatabaseException {
		List<IscrizioneTO> response = getIscrizioniAttiveEscursione(escursione);
		for(IscrizioneTO iscrizione: response){
			iscrizione.setStatoIscrizione(getStatoIscrizioneTerminato());
			this.update(iscrizione);
		}
	}
	
	/**
	 * @param escursione
	 * @return la lista delle Iscrizioni attive per una determitata Escursione
	 * @throws DatabaseException
	 */
	@Override
	public List<IscrizioneTO> getIscrizioniAttiveEscursione(EscursioneTO escursione) throws DatabaseException {
		List<EscursioneTO> param = new ArrayList<EscursioneTO>();
		param.add(escursione);
		List<IscrizioneTO> response = super.executeParamQuery("boooooooooooooooooh", param);
		return response;
	}

	/**
	 * @param partecipante
	 * @return tutte le iscrizioni di un partecipante
	 * @throws DatabaseException
	 */
	@Override
	public List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO partecipante) throws DatabaseException {
		List<PartecipanteTO> param = new ArrayList<PartecipanteTO>();
		param.add(partecipante);
		List<IscrizioneTO> res = super.executeParamQuery("booooooooh", param);
		return res;
	}
}
