package outdoorapp.to;

import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.interfaces.TOFactory;

/**
 * Classe che permette di recuperare il factory desiderato in base alla richiesta.
 * Viene istanziato il factory più opportuno. 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class FactoryProducerTO{
	
	/**
	 * Il metodo restituisce il concrete factory per la gestione della categoria
	 * desiderata all'interno dell'applicazione, e in base al tipo di richiesta viene 
	 * istanziato un oggetto di tipo UtenteTOFactory, StatoTOFactory, OptionalTOFactory,
	 * TipoTOFactory o GenericTOFactory. Il tutto è nascosto all'utilizzatore che 
	 * non sa come il metodo opererà
	 *
	 * @param choice
	 * @return
	 */
	
	public static TOFactory getFactory(FactoryEnum choice){
		switch (choice) {
		case UtenteTOFactory:
			return new UtenteTOFactory();
		case StatoTOFactory:
			return new StatoTOFactory();
		case OptionalTOFactory:
			return new OptionalTOFactory();
		case TipoTOFactory:
			return new TipoTOFactory();
		case GenericTOFactory:
			return new GenericTOFactory();
		default:
			return null;
		}
	}
}
