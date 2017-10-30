package outdoorapp.presentation.views.generic;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import outdoorapp.to.interfaces.EscursioneTO;
import outdoorapp.to.interfaces.UtenteTO;

/**
 * 
 * Classe astratta che incorpora tutti i controller che servono 
 * nella fase di inserimento di una escursione
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public abstract class ControllerEscursione extends GenericController{

	public ControllerEscursione() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML 
	protected abstract void registra();
	
	/**
	 * Funzione che restituisce la stringa degli errori rispetto alle informazioni inserite in maniera non corretta 
	 * per inserire una nuova escursione
	 * 
	 * @param escursione: escursione da controllare
	 * @return result: stringa errori
	 */
	protected String checkErrors(EscursioneTO escursione){
		String result = "";

		checkDatePicker(escursione);

		int i = 0;

		for (Field f : escursione.getClass().getSuperclass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if ((f.get(escursione) == null || f.get(escursione).equals(""))) {
					if(!(f.getName().equals("ruolo") || f.getName().equals("statoUtente"))){
						if(!f.getName().equals("email")){
							result += f.getName() + ", ";
							i++;
							if(i == 2){
								result += "\n";
								i = 0;
							}
						}
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		for (Field f : escursione.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.get(escursione) == null || f.get(escursione).equals("")) {
					result += f.getName() + ", ";
					i++;
					if(i == 2){
						result += "\n";
						i = 0;
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		if(!result.equals(""))
			result += " non corretti!";

		return result;
	}
	
	/**
	 * Metodo dell'interfaccia DateFieldCheck che permette di controllare i datepicker delle schermate
	 * di inserimento dati. Il metodo verrà riscritto in base alle esigenze.
	 * @param escursione: Data escursione in fase di inserimento della stessa
	 */
	public void checkDatePicker(EscursioneTO escursione){};
}
