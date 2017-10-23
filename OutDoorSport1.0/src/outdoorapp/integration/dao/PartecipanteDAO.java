package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Partecipante_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;
import outdoorapp.to.interfaces.strings.UtenteEnum;

import java.util.ArrayList;
import java.util.List;

/** 
 * Data Access Object per tutte le operazioni CRUD per Partecipante.
 * Sono presenti i metodi di lettura e modifica.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class PartecipanteDAO extends UtenteDAO<PartecipanteTO> implements Partecipante_DAO{

	/**
	 * Il costruttore inizializza l'entità Partecipante da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public PartecipanteDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
		this.setCurrentClass(tofact.getUtenteTO(UtenteEnum.Partecipante));
	}
	
	/**
	 * @param codiceFiscale
	 * @return il Partecipante con un determinato codice fiscale 
	 * @throws DatabaseException
	 */
	@Override
	public PartecipanteTO readByCodiceFiscale(String codiceFiscale) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(codiceFiscale);
		List<PartecipanteTO> list = super.executeParamQuery("booooooooooooooooooh", param);
		PartecipanteTO response = (PartecipanteTO)list.get(0);
		return response;
	}
	
	/**
	 * @param username
	 * @return il Partecipante con un determinato username
	 * @throws DatabaseException
	 */
	@Override
	public PartecipanteTO readByUsername(String username) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(username);
		List<PartecipanteTO> list = super.executeParamQuery("boooooooooooooh", param);
		PartecipanteTO response = (PartecipanteTO)list.get(0);
		return response;
	}
	
	/**
	 * @return la lista di tutti i partecipanti
	 * @throws DatabaseException
	 */
	@Override
	public List<PartecipanteTO> getAllPartecipante() throws DatabaseException {
		return super.executeQuery("boooooooooooooooooooh");
	}
}
