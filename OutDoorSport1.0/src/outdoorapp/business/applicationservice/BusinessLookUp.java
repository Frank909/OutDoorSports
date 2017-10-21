package outdoorapp.business.applicationservice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.services.AbstractService;
import outdoorapp.services.Service;

class BusinessLookUp{

	private static BusinessLookUp businessLookUp = new BusinessLookUp();

	/**
	 * Costruttore privato BusinessLookUp
	 */
	private BusinessLookUp(){}

	/**
	 * SingleTon Class
	 * @return restituisce l'istanza del BusinessLookUp
	 */
	static BusinessLookUp getInstance(){
		return businessLookUp;
	}

	/**
	 * Metodo che esegue la ricerca dell'opportuno application service. Una volta indentificato il 
	 * corretto application service, viene utilizzata una reflection per capire quale operazione deve
	 * essere eseguita, sempre in base alla richiesta. In questo modo l'informazione è ben nascosta.
	 * 
	 * @param richiesta dal quale verrà identificato l'application service opportuno
	 * @return la risposta in base alla richiesta
	 */
	Response lookup(Request request, Service service){
		if(service instanceof ServiceBusinessLookUp){

			Response response = new Response();

			try {
				Class<?> valueObject = Class.forName(ApplicationService.getApplicationService(request.getData().getClass().getSimpleName()));
				Object object = valueObject.newInstance();
				Method m = valueObject.getMethod(request.toString(), request.getClass());
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
		}else
			return null;
	}

}
