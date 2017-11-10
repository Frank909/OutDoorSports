package outdoorapp.utils;

import java.util.HashMap;

import outdoorapp.presentation.reqresp.Request;


/**
 * Classe astratta che rappresenta il contenitore per i dati di sessione riguardanti l'utente,
 * tenendo traccia delle sue informazioni.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public abstract class SessionCache{

	/**
	 * Istanza hashmap che tiene traccia dei dati attuali.
	 */
	private static HashMap<String, Object> currentData = new HashMap<>();

	/**
	 * Metodo che serve per settare i dati di sessione in base alla chiave in ingresso
	 * @param key: chiave in ingresso.
	 */
	protected void setCurrentData(Request key){
		if(key.getData() != null){
			if(currentData.containsKey(key.getData().getClass().getSimpleName()))
				currentData.replace(key.getData().getClass().getSimpleName(), key.getData());
			else
				currentData.put(key.getData().getClass().getSimpleName(), key.getData());
		}
	}


	/**
	 * Restituisce il dato della sessione corrente in base 
	 * alla chiave 
	 * 
	 * @param key: chiave del valore
	 * @return il dato rispetto alla chiave
	 */
	public static Object getCurrentData(String key){
		return currentData.get(key);
	}
	

	/**
	 * Verifica se seiste o meno un dato con una 
	 * determinata chiave
	 * 
	 * @param key: chiave dell'hashmap
	 * @return vero se esiste, falso altrimenti
	 */
	public static boolean existsData(String key){
		return currentData.containsKey(key);
	}
	
	/**
	 * Metodo che resetta la sessione
	 */
	protected void resetData(){
		currentData.clear();
	}
}
