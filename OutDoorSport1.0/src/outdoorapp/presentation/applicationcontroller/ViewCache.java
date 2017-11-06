package outdoorapp.presentation.applicationcontroller;


import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.utils.SessionCache;

/**
 * ViewCache è una classe realizzata allo scopo di pre-caricare le schermate di visualizzazione richieste
 * in una cache temporeanea in modo da non rallentare il normale svolgimento delle operazioni del programma
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ViewCache extends SessionCache{
	
	private static HashMap<String, Pane> mapViews = new HashMap<>();
	private static Queue<Stage> stageQueue = new ArrayDeque<>();
	private static AnchorPane nestedAnchorPane;

	private final String VIEW_LOGIN = "../../../resources/fxml/application/login";
	private final String VIEW_MANAGER_DI_SISTEMA_CONFIG = "../../../resources/fxml/manager_sistema/managerDiSistemaConfig";
	private final String VIEW_DASHBOARD_MANAGER_DI_SISTEMA = "../../../resources/fxml/manager_sistema/dashboardManagerSistema";
	private final String VIEW_DASHBOARD_MANAGER_DI_ESCURSIONE = "../../../resources/fxml/manager_escursione/dashboardManagerEscursione";
	private final String VIEW_DASHBOARD_PARTECIPANTE = "../../../resources/fxml/partecipante/dashboardPartecipante";
	private final String VIEW_PASSWORD_DIMENTICATA = "../../../resources/fxml/application/passwordDimenticata";
	private final String VIEW_REGISTRAZIONE_PARTECIPANTE = "../../../resources/fxml/partecipante/registrazionePartecipante";
	private final String VIEW_GESTIONE_MANAGER_ESCURSIONE = "../../../resources/fxml/manager_sistema/gestioneManagerEscursione";
	private final String VIEW_VISUALIZZA_ESCURSIONI_SISTEMA = "../../../resources/fxml/manager_sistema/visualizzaEscursioniSistema";
	private final String VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE = "../../../resources/fxml/manager_sistema/registrazioneManagerEscursione";
	private final String VIEW_GESTIONE_ESCURSIONI = "../../../resources/fxml/manager_escursione/gestioneEscursioni";
	private final String VIEW_INSERISCI_ESCURSIONE = "../../../resources/fxml/manager_escursione/inserimentoEscursione";
	private final String VIEW_LE_MIE_ESCURSIONI = "../../../resources/fxml/partecipante/leMieEscursioniPartecipante";
	private final String VIEW_VISUALIZZA_ESCURSIONI_APERTE = "../../../resources/fxml/partecipante/visualizzaEscursioniAperte";
	private final String VIEW_IL_MIO_PROFILO = "../../../resources/fxml/partecipante/ilMioProfiloPartecipante";
	private final String VIEW_DETTAGLI_MANAGER_DI_ESCURSIONE = "../../../resources/fxml/manager_sistema/dettagliManagerDiEscursione";
	private final String VIEW_MODIFICA_MANAGER_DI_ESCURSIONE = "../../../resources/fxml/manager_sistema/modificaManagerEscursione";
	private final String VIEW_DETTAGLI_ESCURSIONI = "../../../resources/fxml/manager_sistema/dettagliEscursione";
	private final String VIEW_DETTAGLI_PARTECIPANTE = "../../../resources/fxml/manager_sistema/dettagliPartecipante";
	private final String VIEW_DETTAGLI_ESCURSIONI_FROM_MDE = "../../../resources/fxml/manager_escursione/dettagliEscursione";
	private final String VIEW_MODIFICA_ESCURSIONE = "../../../resources/fxml/manager_escursione/modificaEscursione";
	private final String VIEW_AGGIORNA_SRC_PARTECIPANTE = "../../../resources/fxml/partecipante/aggiornaCertificatoSRC";
	private final String VIEW_MODIFICA_DATI_PARTECIPANTE = "../../../resources/fxml/partecipante/modificaDatiAnagraficiPartecipante";
	private final String VIEW_ISCRITTI_ESCURSIONE = "../../../resources/fxml/manager_escursione/visualizzaPartecipantiIscrittiEscursione";
	
	private static ViewCache viewCache = new ViewCache();

	/**
	 * Costruttore privato - Singleton Class
	 */
	private ViewCache(){}

	/**
	 * 
	 * @return viewCache: restituisce l'istanza ViewCache
	 */
	public static ViewCache getInstance(){
		return viewCache;
	}

	/**
	 * Metodo di creazione cache in memoria di tutte le schermate 
	 * @throws Exception
	 */
	void initialize() throws Exception{
		loadForm("VIEW_LOGIN", VIEW_LOGIN, true);
		loadForm("VIEW_MANAGER_DI_SISTEMA_CONFIG",VIEW_MANAGER_DI_SISTEMA_CONFIG, true);
		loadForm("VIEW_DASHBOARD_MANAGER_DI_SISTEMA",VIEW_DASHBOARD_MANAGER_DI_SISTEMA, true);
		loadForm("VIEW_DASHBOARD_MANAGER_DI_ESCURSIONE",VIEW_DASHBOARD_MANAGER_DI_ESCURSIONE, true);
		loadForm("VIEW_DASHBOARD_PARTECIPANTE",VIEW_DASHBOARD_PARTECIPANTE, true);
		loadForm("VIEW_PASSWORD_DIMENTICATA",VIEW_PASSWORD_DIMENTICATA, true);
		loadForm("VIEW_REGISTRAZIONE_PARTECIPANTE",VIEW_REGISTRAZIONE_PARTECIPANTE, true);
		loadForm("VIEW_GESTIONE_MANAGER_ESCURSIONE",VIEW_GESTIONE_MANAGER_ESCURSIONE, false);
		loadForm("VIEW_VISUALIZZA_ESCURSIONI_SISTEMA",VIEW_VISUALIZZA_ESCURSIONI_SISTEMA, false);
		loadForm("VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE",VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE, false);
		loadForm("VIEW_GESTIONE_ESCURSIONI", VIEW_GESTIONE_ESCURSIONI, false);
		loadForm("VIEW_INSERISCI_ESCURSIONE", VIEW_INSERISCI_ESCURSIONE, false);
		loadForm("VIEW_LE_MIE_ESCURSIONI",VIEW_LE_MIE_ESCURSIONI, false);
		loadForm("VIEW_VISUALIZZA_ESCURSIONI_APERTE", VIEW_VISUALIZZA_ESCURSIONI_APERTE, false);
		loadForm("VIEW_IL_MIO_PROFILO", VIEW_IL_MIO_PROFILO, false);
		loadForm("VIEW_DETTAGLI_MANAGER_DI_ESCURSIONE", VIEW_DETTAGLI_MANAGER_DI_ESCURSIONE, false);
		loadForm("VIEW_MODIFICA_MANAGER_DI_ESCURSIONE", VIEW_MODIFICA_MANAGER_DI_ESCURSIONE, false);
		loadForm("VIEW_DETTAGLI_ESCURSIONI", VIEW_DETTAGLI_ESCURSIONI, false);
		loadForm("VIEW_DETTAGLI_PARTECIPANTE", VIEW_DETTAGLI_PARTECIPANTE, false);
		loadForm("VIEW_DETTAGLI_ESCURSIONI_FROM_MDE", VIEW_DETTAGLI_ESCURSIONI_FROM_MDE, false);
		loadForm("VIEW_MODIFICA_ESCURSIONE", VIEW_MODIFICA_ESCURSIONE, false);
		loadForm("VIEW_AGGIORNA_SRC_PARTECIPANTE", VIEW_AGGIORNA_SRC_PARTECIPANTE, false);
		loadForm("VIEW_MODIFICA_DATI_PARTECIPANTE", VIEW_MODIFICA_DATI_PARTECIPANTE, false);
		loadForm("VIEW_ISCRITTI_ESCURSIONE", VIEW_ISCRITTI_ESCURSIONE, false);
	}
	
	/**
	 * Setta la vista richiesta dall'utente e chiude quella precedente
	 * @param key: chiave della schermata
	 */
	void setView(Request key){	
		if(!stageQueue.isEmpty())
			stageQueue.poll().close();

		this.setCurrentData(key);
		
		Stage newStage = new Stage();
		newStage.setTitle("OutDoorSports 1.0");
		newStage.setResizable(false);
		Scene myScene = new Scene(mapViews.get(key.toString()));
		newStage.setScene(myScene);
		newStage.show();
		stageQueue.add(newStage);
	}

	/**
	 * Metodo che consente di settare la vista di una sotto-schermata richiesta se quest'ultima si trova in una schermata principale
	 * @param key: chiave sotto-finestra richiesta
	 * @param parent: finestra genitore
	 */
	void setNestedView(Request key, AnchorPane parent){
		StackPane panel = (StackPane)mapViews.get(key.toString());
		this.setCurrentData(key);
		
		setNestedAnchorPane(parent);
		AnchorPane.setLeftAnchor(panel, 0.0);
		AnchorPane.setRightAnchor(panel, 0.0);
		AnchorPane.setTopAnchor(panel, 0.0);
		AnchorPane.setBottomAnchor(panel, 0.0);
		if(!parent.getChildren().isEmpty())
			parent.getChildren().get(0).setVisible(false);
		parent.getChildren().clear();
		if(!panel.isVisible())
			panel.setVisible(true);
		parent.getChildren().add(panel);
	}

	/**
	 * Metodo di caricamento nella cache della singola schermata
	 * @param key: chiave schermata
	 * @param resource: percorso risorsa da caricare
	 * @param visibility: imposta quale view deve essere subito visibile e quale no
	 */
	private void loadForm(String key, String resource, boolean visibility){
		try {
			Pane newPane = FXMLLoader.load(getClass().getResource(resource + ".fxml"));
			newPane.setVisible(visibility);
			mapViews.put(key, newPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che setta la view innestata corrente
	 * 
	 * @param anchorPane: la view genitore che deve contenere la nuova view
	 */
	private void setNestedAnchorPane(AnchorPane anchorPane){
		nestedAnchorPane = anchorPane;
	}
	
	/**
	 * 
	 * @return stageQueue.peak(): Restituisce lo stage della schermata corrente
	 */
	public static Stage getCurrentView(){
		return stageQueue.peek();
	}
	
	/**
	 * @return la view innestata corrente
	 */
	public static AnchorPane getNestedAnchorPane(){
		return nestedAnchorPane;
	}
}
