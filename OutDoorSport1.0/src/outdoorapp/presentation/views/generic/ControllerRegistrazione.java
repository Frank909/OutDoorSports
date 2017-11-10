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
 * nella fase di registrazione
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public abstract class ControllerRegistrazione extends GenericController{

	public ControllerRegistrazione() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metodo astratto associato a tutti i bottoni dei controller che implementeranno una funzione di invio dati.
	 */
	@FXML 
	protected abstract void registra();

	/**
	 * Funzione che restituisce la stringa degli errori rispetto alle informazioni inserite in maniera non corretta 
	 * per registrare il manager di sistema nella configurazione iniziale
	 * 
	 * @param utente: manager di sistema
	 * @return result: stringa errori
	 */
	protected String checkErrors(UtenteTO utente){
		String result = "";

		checkDatePicker(utente);

		int i = 0;

		for (Field f : utente.getClass().getSuperclass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if ((f.get(utente) == null || f.get(utente).equals(""))) {
					if(!(f.getName().equals("ruolo") || f.getName().equals("statoUtente") || f.getName().equals("idUtente"))){
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
		
		for (Field f : utente.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.get(utente) == null || f.get(utente).equals("")) {
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


		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(utente.getEmail());

		if(!matcher.matches()){
			result += "email";
		}

		if(!result.equals(""))
			result += " non corretti!";

		return result;
	}
	
	/**
	 * Metodo che permette di controllare i datepicker delle schermate
	 * di inserimento dati. Il metodo verrà riscritto in base alle esigenze.
	 * @param utente: Utente in fase di registrazione su cui applicare i controlli
	 */
	protected void checkDatePicker(UtenteTO utente){};	
}
