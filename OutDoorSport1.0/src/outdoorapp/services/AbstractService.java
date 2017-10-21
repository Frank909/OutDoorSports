package outdoorapp.services;


/**
 * Classe astratta rappresenta di service generico che fornisce metodi per controllare
 * che le informazioni non d'interesse non siano disponibili.
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public abstract class AbstractService implements Service{

	private static boolean flag = false;
	
	/**
	 * Metodo che consente di controllare se la richiesta è partita dal controller esatto.
	 * @return restituisce vero se la richiesta è partita dal controller esatto, falso altrimenti.
	 */
	protected boolean isTunneling(){
		return flag;
	}
	
	/**
	 * Setta se l'inizio di passaggio di richieste iniziano dal controller esatto.
	 * @param flag
	 */
	protected void setTunneled(boolean flag){
		AbstractService.flag = flag;
	}

}
