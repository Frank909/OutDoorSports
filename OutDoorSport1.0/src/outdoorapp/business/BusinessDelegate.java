package outdoorapp.business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import outdoorapp.business.applicationservice.ApplicationService;
import outdoorapp.business.applicationservice.ApplicationServiceUtente;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.UtenteDAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Utente;

/**
 * Classe che rappresenta il Business Delegate, ovvero un'astrazione client-side 
 * dei servizi di business. Viene creata una astrazione dove si nascondono i dettagli 
 * implementativi dei servizi di business. Utilizzando il Business Delegate è possibile 
 * ridurre l'accoppiamento tra i client e i servizi offerti dal sistema. 
 * L'obiettivo della classe è quindi quello di incapsulare l'accesso ai vari application service,
 * nascondendo i dettagli di implementazione della strategia di lookup (ritrovamento) e di accesso 
 * ad un servizio.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class BusinessDelegate {
	
	public BusinessDelegate() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metodo che esegue la ricerca dell'opportuno application service. Una volta indentificato il 
	 * corretto application service, viene utilizzata una reflection per capire quale operazione deve
	 * essere eseguita, sempre in base alla richiesta. In questo modo l'informazione è ben nascosta.
	 * 
	 * @param richiesta dal quale verrà identificato l'application service opportuno
	 * @return la risposta in base alla richiesta
	 */
	public Response lookup(Request request){
		
		Response response = new Response();
		
		try {
			Class<?> valueObject = Class.forName(ApplicationService.getApplicationService(request.getData().getClass().getSimpleName()));
			Object object = valueObject.newInstance();
			Method m = valueObject.getMethod(request.getRequest(), request.getClass());
			m.setAccessible(true);
			response = (Response) m.invoke(object, request);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
}
