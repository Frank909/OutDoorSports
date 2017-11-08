package outdoorapp.presentation.views.partecipante;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import outdoorapp.presentation.views.generic.GenericController;
import outdoorapp.presentation.views.models.EscursioneModel;
import outdoorapp.to.interfaces.EscursioneTO;

public class ControllerLeMieEscursioni extends GenericController {

	@FXML private StackPane leMieEscursioniPartecipante;
	@FXML private TextField txtSearchEscursione;
	@FXML private Button btnSearchEscursione;
	@FXML private TableView<EscursioneModel> mTableEscursioni;
	@FXML private TableColumn<EscursioneModel, String> mColumnNameEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnTipoEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnDataEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnNMinEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnNMaxEscursione;
	@FXML private TableColumn<EscursioneModel, String> mColumnCostoEscursione;
	@FXML private Button btnCancelEscursione, btnModificaIscrizione;
	
	private List<EscursioneTO> list_escursioni = new ArrayList<>();
	private EscursioneModel escursione_model = null;
	private EscursioneTO escursione = null;
	
	public ControllerLeMieEscursioni() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	protected void cancellatiEscursione(){
		
	}
	
	@FXML
	protected void modificaIscrizione(){
		
	}
	

}
