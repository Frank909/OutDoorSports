package outdoorapp.presentation.views;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;

/**
 * Classe astratta ControllerRegistrazione che conterrà i metodi in comune per gli eventi di registrazione 
 * di un generico utente.
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public abstract class ControllerRegistrazione implements Actions, DateFieldCheck{

	/**
	 * Metodo che inizializza tutti i campi della finestra
	 */
	@FXML protected abstract void initialize();
	
	/**
	 * Evento associato all'invio dei dati della registrazione
	 */
	@FXML protected abstract void registra();

	/**
	 * Funzione che restituisce la stringa degli errori rispetto alle informazioni inserite in maniera non corretta 
	 * per registrare il manager di sistema nella configurazione iniziale
	 * 
	 * @param utente: manager di sistema
	 * @return result: stringa errori
	 */
	protected String checkErrors(Utente utente){
		String result = "";

		checkDatePicker(utente);

		int i = 0;

		for (Field f : utente.getClass().getSuperclass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if ((f.get(utente) == null || f.get(utente).equals(""))) {
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
}

/**
 * Interfaccia che permette di far comunicare le classi per applicare controlli sui datepicker in fase di registrazione
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
interface DateFieldCheck{
	
	/**
	 * Metodo dell'interfaccia DateFieldCheck che permette di controllare i datepicker delle schermate
	 * di inserimento dati.
	 * @param utente: Utente in fase di registrazione su cui applicare i controlli
	 */
	void checkDatePicker(Utente utente);
}
