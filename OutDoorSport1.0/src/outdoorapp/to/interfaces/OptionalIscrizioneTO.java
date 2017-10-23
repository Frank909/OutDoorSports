package outdoorapp.to.interfaces;

public interface OptionalIscrizioneTO extends OutDoorSports{
	public Integer getId();
	public IscrizioneTO getIscrizione();
	public void setIscrizione(IscrizioneTO tblIscrizione);
	public OptionalEscursioneTO getOptionalEscursione();
	public void setOptionalEscursione(OptionalEscursioneTO tblOptionalEscursione);
	public void setId(Integer id);
}
