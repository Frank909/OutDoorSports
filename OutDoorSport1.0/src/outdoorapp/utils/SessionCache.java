package outdoorapp.utils;

import java.util.HashMap;

import outdoorapp.presentation.reqresp.Request;

public abstract class SessionCache{

	private static HashMap<String, Object> currentData = new HashMap<>();

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
		if(currentData.containsKey(key))
			return true;
		else
			return false;
	}
}
