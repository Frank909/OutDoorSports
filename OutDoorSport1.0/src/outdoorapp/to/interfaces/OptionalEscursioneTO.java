package outdoorapp.to.interfaces;

import java.util.Set;

public interface OptionalEscursioneTO extends OutDoorSports{
	public void setId(Integer id);
	public Integer getId();
	public EscursioneTO getEscursione();
	public void setEscursione(EscursioneTO tblEscursione);
	public OptionalTO getOptional();
	public void setOptional(OptionalTO tblOptional);
	public Set<OptionalIscrizioneTO> getOptionalIscrizione();
	public void setOptionalIscrizione(Set<OptionalIscrizioneTO> tblOptionalIscriziones);
}
