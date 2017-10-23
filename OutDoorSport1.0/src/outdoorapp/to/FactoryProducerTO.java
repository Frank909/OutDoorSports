package outdoorapp.to;

import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.to.interfaces.strings.FactoryEnum;

public class FactoryProducerTO{
	
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
