package outdoorapp.integration.dao.interfaces;

import java.util.List;

import outdoorapp.exceptions.DatabaseException;
import outdoorapp.to.Optional;
import outdoorapp.to.TipoOptional;

public interface Optional_DAO extends GEN_DAO<Optional> {
	Optional disattivaOptional(Optional optional) throws DatabaseException;
	List<Optional> getOptionalAttiviByTipo(TipoOptional tipoOptional) throws DatabaseException;
	List<Optional> getOptionalByTipo(TipoOptional tipoOptional) throws DatabaseException ;
	
}
