package outdoorapp.presentation.views.partecipante;

import java.time.LocalDate;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert.AlertType;
import outdoorapp.presentation.applicationcontroller.ViewCache;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.to.FactoryProducerTO;
import outdoorapp.to.enums.FactoryEnum;
import outdoorapp.to.enums.UtenteEnum;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.to.interfaces.TOFactory;
import outdoorapp.utils.SessionCache;

/**
 * Classe controller che gestisce la schermata dell'aggiornamento del
 * certificato SRC.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ControllerAggiornamentoSRC extends GenericController{

	@FXML private StackPane stpAggiornaSRC;
	@FXML private Button btnUpdateSRC;
	@FXML private Button btnBack;
	@FXML private Button btnSfoglia;
	@FXML private Label lblSrcError, lblSrc;
	@FXML private DatePicker dateSRC;

	private TOFactory toFactory = null;
	private PartecipanteTO partecipante = null;

	/**
	 * Costruttore che inizializza l'oggetto del partecipante in memoria
	 */
	public ControllerAggiornamentoSRC() {
		if(partecipante == null){
			toFactory = FactoryProducerTO.getFactory(FactoryEnum.UtenteTOFactory);
			partecipante = (PartecipanteTO) toFactory.getUtenteTO(UtenteEnum.Partecipante);
		}
	}

	/**
	 * Metodo associato all'evento del click del bottone Sfoglia della View
	 */
	@FXML 
	protected void btnCaricaClicked(){
		Response response = this.sendRequest(new Request(partecipante, OUTDOORSPORT_SAVE_SRC_CERTIFICATE));
		if(response.toString().equals(RESP_OK))
			lblSrc.setText(((PartecipanteTO)response.getData()).getCertificatoSrc());
	}

	@Override
	protected void initialize() {
		ChangeListener<Boolean> visibilityListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(newValue)
					lblSrcError.setText("");
			}
		};

		stpAggiornaSRC.visibleProperty().addListener(visibilityListener);


	}

	/**
	 * Metodo associato all'evento del click del bottone Indietro della View
	 */
	@FXML
	protected void btnBackClicked(){
		this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_IL_MIO_PROFILO));
	} 
	
	/**
	 * Metodo associato all'evento del click del bottone Aggiorna Certificato della View
	 */
	@FXML
	protected void btnUpdateClicked() {
		Alert alert = new Alert(AlertType.INFORMATION, "Sei sicuro di modificare il certificato SRC?", ButtonType.OK, ButtonType.CANCEL);
		alert.setTitle("OutDoorSport1.0");
		Optional<ButtonType> res = alert.showAndWait();

		if(res.get() == ButtonType.OK){
			if(dateSRC.getValue() == null || lblSrc.getText().equals("...")){
				partecipante.setDataCertificatoSrc("");
				lblSrcError.setText("Immettere i dati.");
			}else{
				if((dateSRC.getValue().getYear() >= LocalDate.now().getYear() - 1)){
					if(SessionCache.existsData(partecipante.getClass().getSimpleName()))
						partecipante = (PartecipanteTO) SessionCache.getCurrentData(partecipante.getClass().getSimpleName());
					partecipante.setDataCertificatoSrc(dateSRC.getValue().toString());
					partecipante.setCertificatoSrc(lblSrc.getText());
					Response resp = this.sendRequest(new Request(partecipante, OUTDOORSPORT_MODIFY_PARTECIPANTE));
					if(resp.toString().equals(RESP_OK))
						this.sendRequest(new Request(ViewCache.getNestedAnchorPane(), VIEW_IL_MIO_PROFILO));
				}else{
					lblSrcError.setText("Data di rilascio obsoleta.");
				}
			}
		}
	}
}
