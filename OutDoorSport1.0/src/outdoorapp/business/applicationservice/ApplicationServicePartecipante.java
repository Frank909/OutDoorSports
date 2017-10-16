package outdoorapp.business.applicationservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javafx.stage.FileChooser;
import outdoorapp.exceptions.DatabaseException;
import outdoorapp.integration.dao.PartecipanteDAO;
import outdoorapp.integration.dao.RuoliDAO;
import outdoorapp.integration.dao.StatoUtenteDAO;
import outdoorapp.presentation.reqresp.Request;
import outdoorapp.presentation.reqresp.Response;
import outdoorapp.to.ManagerDiSistema;
import outdoorapp.to.Partecipante;
import outdoorapp.to.Utente;
import outdoorapp.utils.Actions;
import outdoorapp.utils.Forms;
import outdoorapp.utils.Views;

/**
 * Classe che modella e implementa l'Application Service per la gestione di un Partecipante.
 * L'obiettivo è quello di raccogliere tutte le azioni che è possibile effettuare per 
 * un Partecipante, andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * L'application service utilizza i Transfer Object e i Data Access Object per occuparsi
 * della persistenza di tali oggetti, ma anche per il recupero di tali dati dal Database.
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public class ApplicationServicePartecipante implements Views, Actions {

	PartecipanteDAO partecipante_dao;
	
	/**
	 * Costruttore che inizializza il DAO del Partecipante
	 */
	public ApplicationServicePartecipante() {
		partecipante_dao = new PartecipanteDAO();
	}
	
	/**
	 * Metodo che permette la ricerca del file da caricare e ne salva
	 * il path.
	 * 
	 * @param request
	 * @return una response in base alla request
	 */
	public Response caricaCertificatoSRC(Request request){
		Partecipante partecipante = (Partecipante) request.getData();
		Response response = new Response();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Carica Certificato SRC");
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.JPEG"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
            );
		File fileCertificatoSRC = fileChooser.showOpenDialog(Forms.getForm(VIEW_REGISTRAZIONE_PARTECIPANTE));
		if(fileCertificatoSRC != null){
			partecipante.setCertificatoSrc(fileCertificatoSRC.getPath());
			response.setResponse(RESP_OK);
		}else
			response.setResponse(RESP_KO);
		response.setData(partecipante);

		return response;
	}
	

	public Response nuovoPartecipante(Request request){
		Response response = new Response();
		
		try {
			if(!partecipante_dao.esisteEmail((Utente)request.getData()) && !partecipante_dao.esisteUsername((Utente)request.getData())){
				RuoliDAO ruoliDao = new RuoliDAO();
				StatoUtenteDAO statoUtenteDao = new StatoUtenteDAO();
				Partecipante partecipante = (Partecipante)request.getData();
				uploadCertificatoSRC(partecipante);
				partecipante.setRuolo(ruoliDao.getRuoloPartecipante());
				partecipante.setStatoUtente(statoUtenteDao.getStatoDisattivo());
				partecipante_dao.create(partecipante);
				response.setResponse(RESP_OK);
			}else{
				response.setResponse(RESP_KO);
			}
			response.setData(null);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Metodo che crea, se non è stata già creata, la directory principale, e crea la directory
	 * avente il nome dell'username, che conterrà il certificato SRC. Quindi salva il 
	 * certificato SRC.
	 * 
	 * @param username
	 */
	private void uploadCertificatoSRC(Partecipante partecipante){
		File rootDir = new File(ROOT_CERTIFICATE);
		if (!rootDir.exists()) {
		    try{
		        rootDir.mkdir();
		    } 
		    catch(SecurityException se){
		    }        
		}
		
		String path = ROOT_CERTIFICATE + "\\" + partecipante.getUsername();
		File userCertificateDir = new File(path);
		if (!userCertificateDir.exists()) {
		    try{
		    	userCertificateDir.mkdir();
		    } 
		    catch(SecurityException se){
		    }        
		}
		
		File newFile = new File(partecipante.getCertificatoSrc());
		OutputStream out;
		try {
			out = new FileOutputStream(newFile);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
