package outdoorapp.presentation.applicationcontroller;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

import org.jboss.jandex.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import outdoorapp.to.OutDoorSports;

/**
 * ViewCache è una classe realizzata allo scopo di pre-caricare le schermate di visualizzazione richieste
 * in una cache temporeanea in modo da non rallentare il normale svolgimento delle operazioni del programma
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */
public class ViewCache{

	private final String VIEW_LOGIN = "../../resources/fxml/application/login";
	private final String VIEW_MANAGER_DI_SISTEMA_CONFIG = "../../resources/fxml/manager_sistema/managerDiSistemaConfig";
	private final String VIEW_DASHBOARD_MANAGER_DI_SISTEMA = "../../resources/fxml/manager_sistema/dashboardManagerSistema";
	private final String VIEW_DASHBOARD_MANAGER_DI_ESCURSIONE = "../../resources/fxml/manager_escursione/dashboardManagerEscursione";
	private final String VIEW_DASHBOARD_PARTECIPANTE = "../../resources/fxml/partecipante/dashboardPartecipante";
	private final String VIEW_PASSWORD_DIMENTICATA = "../../resources/fxml/application/passwordDimenticata";
	private final String VIEW_REGISTRAZIONE_PARTECIPANTE = "../../resources/fxml/partecipante/registrazionePartecipante";
	private final String VIEW_GESTIONE_MANAGER_ESCURSIONE = "../../resources/fxml/manager_sistema/gestioneManagerEscursione";
	private final String VIEW_VISUALIZZA_ESCURSIONI_SISTEMA = "../../resources/fxml/manager_sistema/visualizzaEscursioniSistema";
	private final String VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE = "../../resources/fxml/manager_sistema/registrazioneManagerEscursione";
	private final String VIEW_GESTIONE_ESCURSIONI = "../../resources/fxml/manager_escursione/gestioneEscursioni";
	private final String VIEW_INSERISCI_ESCURSIONE = "../../resources/fxml/manager_escursione/inserimentoEscursione";
	private final String VIEW_LE_MIE_ESCURSIONI = "../../resources/fxml/partecipante/leMieEscursioniPartecipante";
	private final String VIEW_VISUALIZZA_ESCURSIONI_APERTE = "../../resources/fxml/partecipante/visualizzaEscursioniAperte";
	private final String VIEW_IL_MIO_PROFILO = "../../resources/fxml/partecipante/ilMioProfiloPartecipante";
	
	private static ViewCache viewCache = new ViewCache();
	private static HashMap<String, Pane> mapViews = new HashMap<>();
	private static Queue<Stage> stageQueue = new ArrayDeque<>();
	private static AnchorPane nestedAnchorPane;

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
	public void initialize() throws Exception{
		loadForm("VIEW_LOGIN", VIEW_LOGIN);
		loadForm("VIEW_MANAGER_DI_SISTEMA_CONFIG",VIEW_MANAGER_DI_SISTEMA_CONFIG);
		loadForm("VIEW_DASHBOARD_MANAGER_DI_SISTEMA",VIEW_DASHBOARD_MANAGER_DI_SISTEMA);
		loadForm("VIEW_DASHBOARD_MANAGER_DI_ESCURSIONE",VIEW_DASHBOARD_MANAGER_DI_ESCURSIONE);
		loadForm("VIEW_DASHBOARD_PARTECIPANTE",VIEW_DASHBOARD_PARTECIPANTE);
		loadForm("VIEW_PASSWORD_DIMENTICATA",VIEW_PASSWORD_DIMENTICATA);
		loadForm("VIEW_REGISTRAZIONE_PARTECIPANTE",VIEW_REGISTRAZIONE_PARTECIPANTE);
		loadForm("VIEW_GESTIONE_MANAGER_ESCURSIONE",VIEW_GESTIONE_MANAGER_ESCURSIONE);
		loadForm("VIEW_VISUALIZZA_ESCURSIONI_SISTEMA",VIEW_VISUALIZZA_ESCURSIONI_SISTEMA);
		loadForm("VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE",VIEW_REGISTRAZIONE_MANAGER_ESCURSIONE);
		loadForm("VIEW_GESTIONE_ESCURSIONI", VIEW_GESTIONE_ESCURSIONI);
		loadForm("VIEW_INSERISCI_ESCURSIONE", VIEW_INSERISCI_ESCURSIONE);
		loadForm("VIEW_LE_MIE_ESCURSIONI",VIEW_LE_MIE_ESCURSIONI);
		loadForm("VIEW_VISUALIZZA_ESCURSIONI_APERTE", VIEW_VISUALIZZA_ESCURSIONI_APERTE);
		loadForm("VIEW_IL_MIO_PROFILO", VIEW_IL_MIO_PROFILO);
	}

	/**
	 * 
	 * @return stageQueue.peak(): Restituisce lo stage della schermata corrente
	 */
	public static Stage getCurrentView(){
		return stageQueue.peek();
	}
	
	/**
	 * Setta la vista richiesta dall'utente e chiude quella precedente
	 * @param key: chiave della schermata
	 */
	void setView(String key){	
		if(!stageQueue.isEmpty())
			stageQueue.poll().close();
		Stage newStage = new Stage();
		newStage.setTitle("OutDoorSports 1.0");
		newStage.setResizable(false);
		Scene myScene = new Scene(mapViews.get(key));
		newStage.setScene(myScene);
		newStage.show();
		stageQueue.add(newStage);
	}

	/**
	 * Metodo che consente di settare la vista di una sotto-schermata richiesta se quest'ultima si trova in una schermata principale
	 * @param key: chiave sotto-finestra richiesta
	 * @param parent: finestra genitore
	 */
	void setNestedView(String key, AnchorPane parent){
		StackPane panel = (StackPane)mapViews.get(key);
		setNestedAnchorPane(parent);
		AnchorPane.setLeftAnchor(panel, 0.0);
		AnchorPane.setRightAnchor(panel, 0.0);
		AnchorPane.setTopAnchor(panel, 0.0);
		AnchorPane.setBottomAnchor(panel, 0.0);
		parent.getChildren().clear();
		parent.getChildren().add(panel);
	}

	/**
	 * Metodo di caricamento nella cache della singola schermata
	 * @param key: chiave schermata
	 * @param resource: percorso risorsa da caricare
	 */
	private void loadForm(String key, String resource){
		try {
			Pane newPane = FXMLLoader.load(getClass().getResource(resource + ".fxml"));
			mapViews.put(key, newPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setNestedAnchorPane(AnchorPane anchorPane){
		ViewCache.nestedAnchorPane = anchorPane;
	}
	
	public static AnchorPane getNestedAnchorPane(){
		return nestedAnchorPane;
	}
}
