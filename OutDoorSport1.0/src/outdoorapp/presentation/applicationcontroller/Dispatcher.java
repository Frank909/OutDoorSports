package outdoorapp.presentation.applicationcontroller;

import javafx.scene.layout.AnchorPane;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;

/**
 * La classe Dispatcher si occupa di gestire le richieste di visualizzazione delle in ingresso
 * e mostrarle, avendo accesso alla cache delle View (classe ViewCache)
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
class Dispatcher {

	private static ViewCache vc = null;
	private static Dispatcher dispatcher = new Dispatcher();;
	
	/**
	 * Costruttore privato
	 */
	private Dispatcher(){
	}
	
	/**
	 * 
	 * @return restituisce l'istanza del Dispatcher
	 */
	public static Dispatcher getInstance(){
		if(vc == null){
			try {
				vc = ViewCache.getInstance();
				vc.initialize();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dispatcher;
	}
	
	/**
	 * Metodo privato di dispatcher che consente di settare a video una schermata avendo accesso
	 * alla cache delle view.
	 * @param request: richiesta in ingresso
	 */
	private void setView(String request){
		vc.setView(request);
	}
	
	/**
	 * Metodo privato di dispatcher che consente di settare a video una schermata figlia avendo accesso
	 * alla cache delle view.
	 * @param request: richiesta in ingresso
	 */
	private void setNestedView(String request, AnchorPane pane){
		vc.setNestedView(request, pane);
	}

	/**
	 * Esegue l'operazione di visualizzazione della schermata a seconda
	 * della richiesta in ingresso.
	 * @param request: richiesta della vista in ingresso
	 * @return null
	 */
	Response dispatch(Request request){
		if(request.getView() == null){
			setView(request.toString());
		}else if(request.getView() instanceof AnchorPane){
			setNestedView(request.toString(), (AnchorPane)request.getView());
		}
		return null;
	}
}
