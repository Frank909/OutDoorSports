package outdoorapp.integration.dao;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.interfaces.MDS_DAO;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.ManagerDiSistemaTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.Actions;

import java.util.List;

/** 
 * Classe che implementa i Data Access Object per tutte le operazioni 
 * CRUD per Manager di Sistema.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ManagerDiSistemaDAO extends UtenteDAO<ManagerDiSistemaTO> implements Actions, MDS_DAO{

	/**
	 * Il costruttore inizializza l'entità Manager di Sistema da utilizzare 
	 * in tutte le operazioni del DAO.
	 */
	public ManagerDiSistemaDAO() {
		TOFactory tofact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
		this.setCurrentClass(tofact.getUtenteTO(UtenteEnum.ManagerDiSistema));
	}
	
	@Override
	public boolean verificaManagerDiSistema() throws DatabaseException {
		boolean response;
		List<ManagerDiSistemaTO> list = super.executeQuery("getAllMDS");
		if(list.size() != 0)
			response = true;
		else
			response = false;
		return response;
	}
}
