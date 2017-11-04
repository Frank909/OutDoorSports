package outdoorapp.to;
// Generated 14-set-2017 13.06.34 by Hibernate Tools 5.2.3.Final

import outdoorapp.to.interfaces.OptionalTO;
import outdoorapp.to.interfaces.StatoOptionalTO;
import outdoorapp.to.interfaces.TipoOptionalTO;

/**
 * Classe che implementa lo stato dell'Optional.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

class Optional implements OptionalTO{

	private static final long serialVersionUID = -7068153413865370972L;
	private Integer idOptional;
	private TipoOptionalTO tipoOptional;
	private String nome;
	private String descrizione;

	Optional() {
	}

	@Override
	public Integer getIdOptional() {
		return this.idOptional;
	}

	@Override
	public void setIdOptional(Integer idOptional) {
		this.idOptional = idOptional;
	}

	@Override
	public TipoOptionalTO getTipoOptional() {
		return this.tipoOptional;
	}

	
	public void setTipoOptional(TipoOptionalTO tipoOptional) {
		this.tipoOptional = tipoOptional;
	}

	
	public String getNome() {
		return this.nome;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getDescrizione() {
		return this.descrizione;
	}

	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
