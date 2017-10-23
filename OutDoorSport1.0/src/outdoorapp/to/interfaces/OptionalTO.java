package outdoorapp.to.interfaces;

public interface OptionalTO extends OutDoorSports{
	public void setIdOptional(Integer idOptional);
	public Integer getIdOptional();
	public StatoOptionalTO getStatoOptional();
	public void setStatoOptional(StatoOptionalTO statoOptional);
	public TipoOptionalTO getTipoOptional();
	public void setTipoOptional(TipoOptionalTO tipoOptional);
	public String getNome();
	public void setNome(String nome);
	public String getDescrizione();
	public void setDescrizione(String descrizione);
}
