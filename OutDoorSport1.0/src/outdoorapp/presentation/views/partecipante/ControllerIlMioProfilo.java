package outdoorapp.presentation.views.partecipante;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Classe controller che gestisce il profilo del partecipante.
 * 
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerIlMioProfilo extends GenericController{

	@FXML private StackPane ilMioProfiloPartecipante;
	@FXML private Button mBtnAggiornaSRC;
	@FXML private Button mBtnModificaDatiAnagrafici;
	@FXML private Label mNomeCognomeProfilo;
	@FXML private Label mCittaProfilo;
	@FXML private Label mIndirizzoProfilo;
	@FXML private Label mCFProfilo;
	@FXML private Label mDataNascitaProfilo;
	@FXML private Label mEmailProfilo;
	@FXML private Label mDataSRCProfilo;
	@FXML private Label mTessSanProfilo;
	@FXML private Label mUsernameProfilo;
	
	private PartecipanteTO partecipante = null;
	/**
	 * Costruttore che avvalora il partecipante in memoria
	 */
	public ControllerIlMioProfilo() {
		if(partecipante == null){
			TOFactory TOFact = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			partecipante = (PartecipanteTO) TOFact.getUtenteTO(UtenteEnum.Partecipante);
		}
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue){
					partecipante = (PartecipanteTO) SessionCache.getCurrentData(partecipante.getClass().getSimpleName());
					mUsernameProfilo.setText(partecipante.getUsername());
					mNomeCognomeProfilo.setText(partecipante.getNome() + " " + partecipante.getCognome());
					mCittaProfilo.setText("Residente a " + partecipante.getCitta());
					mIndirizzoProfilo.setText("in " + partecipante.getIndirizzo());
					mCFProfilo.setText("CF: " + partecipante.getCodiceFiscale());
					mDataNascitaProfilo.setText("Nato il: " + partecipante.getDataNascita());
					mEmailProfilo.setText("Email: " + partecipante.getEmail());
					mDataSRCProfilo.setText("Data Certificato: " + partecipante.getDataCertificatoSrc());
					mTessSanProfilo.setText("N� Tes. San. : " + partecipante.getTesseraSanitaria());
				}
			}
		};
		
		ilMioProfiloPartecipante.visibleProperty().addListener(visibilityListener);
		
	}
	
	/**
	 * Metodo associato all'evento della visualizzazione della schermata dell'aggiornamento
	 * del certificato SRC
	 */
	@FXML
	protected void aggiornaSRC(){
		this.sendRequest(new Request(ViewCache.getNestedAnchorPane() ,VIEW_AGGIORNA_SRC_PARTECIPANTE));
	}
	
	/**
	 * Metodo associato all'evento della visualizzazione della schermata della modifica delle informazioni
	 * anagrafiche del partecipante
	 */
	@FXML
	protected void modificaDatiAnagrafici(){
		this.sendRequest(new Request(ViewCache.getNestedAnchorPane() ,VIEW_MODIFICA_DATI_PARTECIPANTE));
	}
}
