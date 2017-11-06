package outdoorapp.presentation.views.partecipante;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.to.interfaces.PartecipanteTO;
import outdoorapp.utils.SessionCache;

public class ControllerAggiornamentoSRC extends GenericController{
	
	@FXML private Button btnUpdateSRC;
	@FXML private Button btnSfoglia;
	@FXML private Label lblSrcError, lblSrc;
	@FXML private DatePicker dateSRC;
	
	private PartecipanteTO partecipante = null;
	
	public ControllerAggiornamentoSRC() {
		partecipante = (PartecipanteTO) SessionCache.getCurrentData(partecipante.getClass().getSimpleName());
	}
	
	@FXML 
	protected void btnCaricaClicked(){
		Response response = this.sendRequest(new Request(partecipante, OUTDOORSPORT_SAVE_SRC_CERTIFICATE));
		if(response.toString().equals(RESP_OK))
			lblSrc.setText(((PartecipanteTO)response.getData()).getCertificatoSrc());
	}

	@Override
	protected void initialize() {
		lblSrcError.setText("");		
	}

	@FXML
	protected void btnUpdateClicked() {
		if(dateSRC.getValue() == null){
			partecipante.setDataCertificatoSrc("");
			lblSrcError.setText("Nessuna data immessa.");
		}else{
			if((dateSRC.getValue().getYear() >= LocalDate.now().getYear() - 1)){
				partecipante.setDataCertificatoSrc(dateSRC.getValue().toString());
				Response res = this.sendRequest(new Request(partecipante, OUTDOORSPORT_SAVE_PARTECIPANTE));
				if(res.equals(RESP_OK))
					this.sendRequest(new Request(VIEW_IL_MIO_PROFILO));
			}else{
				lblSrcError.setText("Data di rilascio obsoleta.");
			}
		}
	}

}
