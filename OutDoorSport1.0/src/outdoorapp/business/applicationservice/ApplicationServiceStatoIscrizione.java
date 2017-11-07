package outdoorapp.business.applicationservice;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di uno StatoIscrizione.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * uno StatoIscrizione, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class ApplicationServiceStatoIscrizione implements Actions, Views{

	public ApplicationServiceStatoIscrizione() {
		// TODO Auto-generated constructor stub
	}
	
	public Response gellAllStatoIscrizione (Request request){
		return null;
		
	}

}
