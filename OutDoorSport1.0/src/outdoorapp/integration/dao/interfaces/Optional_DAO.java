package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.TipoOptionalTO;

public interface Optional_DAO extends GEN_DAO<OptionalTO> {
	OptionalTO disattivaOptional(OptionalTO optional) throws DatabaseException;
	List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO tipoOptional) throws DatabaseException;
	List<OptionalTO> getOptionalByTipo(TipoOptionalTO tipoOptional) throws DatabaseException ;
	
}
