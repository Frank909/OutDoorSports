package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Iscrizione_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.GenericEnum;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.IscrizioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.StatoIscrizioneTO;

import java.util.ArrayList;
import java.util.List;


/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per Iscrizione.
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
	
	@Override
	public IscrizioneTO annullaIscrizione(IscrizioneTO iscrizione) throws DatabaseException {
		super.update(iscrizione);
		return iscrizione;
	}
	
	@Override
	public boolean esisteIscrizione(IscrizioneTO iscrizione) throws DatabaseException {
		IscrizioneTO newIscrizione = this.findOne(iscrizione.getIdIscrizione());
		return newIscrizione != null;
	}
	
	
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
	
	@Override
	public void terminaIscrizioni(EscursioneTO escursione) throws DatabaseException {
		List<IscrizioneTO> response = getIscrizioniAttiveEscursione(escursione);
		for(IscrizioneTO iscrizione: response){
			iscrizione.setStatoIscrizione(getStatoIscrizioneTerminato());
			this.update(iscrizione);
		}
	}

	@Override
	public List<IscrizioneTO> getIscrizioniAttiveEscursione(EscursioneTO escursione) throws DatabaseException {
		List<EscursioneTO> param = new ArrayList<EscursioneTO>();
		param.add(escursione);
		List<IscrizioneTO> response = super.executeParamQuery("boooooooooooooooooh", param);
		return response;
	}


	@Override
	public List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO partecipante) throws DatabaseException {
		List<PartecipanteTO> param = new ArrayList<PartecipanteTO>();
		param.add(partecipante);
		List<IscrizioneTO> res = super.executeParamQuery("booooooooh", param);
		return res;
	}

	@Override
	public List<IscrizioneTO> getAllIscrittiFromEscursione(EscursioneTO escursione) throws DatabaseException {
		List<Integer> param = new ArrayList<>();
		param.add(escursione.getIdEscursione());
		List<IscrizioneTO> res = super.executeParamQuery("getAllIscrittiFromEscursione", param);
		return res;
	}

	@Override
	public IscrizioneTO getIscrizioneFromEscursione(EscursioneTO escursione, PartecipanteTO partecipante) throws DatabaseException {
		List<Integer> param = new ArrayList<>();
		param.add(escursione.getIdEscursione());
		param.add(partecipante.getIdUtente());
		List<IscrizioneTO> res = super.executeParamQuery("getIscrizioneFromEscursione", param);
		if(!res.isEmpty())
			return res.get(0);
		else
			return null;
	}
}
