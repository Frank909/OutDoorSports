package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.Partecipante_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiEscursioneTO;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;

import java.util.ArrayList;
import java.util.List;

/** 
 * Classe che implementa i Data Access Object per 
 * tutte le operazioni CRUD per Partecipante.
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
	
	@Override
	public PartecipanteTO readByCodiceFiscale(String codiceFiscale) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(codiceFiscale);
		List<PartecipanteTO> list = super.executeParamQuery("booooooooooooooooooh", param);
		PartecipanteTO response = (PartecipanteTO)list.get(0);
		return response;
	}
	
	@Override
	public PartecipanteTO readByUsername(String username) throws DatabaseException {
		List<String> param = new ArrayList<String>();
		param.add(username);
		List<PartecipanteTO> list = super.executeParamQuery("boooooooooooooh", param);
		PartecipanteTO response = (PartecipanteTO)list.get(0);
		return response;
	}
	
	@Override
	public List<PartecipanteTO> getAllPartecipante() throws DatabaseException {
		return super.executeQuery("getAllPartecipanti");
	}
}
