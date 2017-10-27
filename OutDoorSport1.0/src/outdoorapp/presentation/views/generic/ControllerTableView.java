package outdoorapp.presentation.views.generic;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * Classe astratta che incorpora tutti i controller che servono 
 * nella realizzazione delle tabelle
 * 
 * @author Andrea Zito
 * @author Francesco Ventura
 *
 */

public abstract class ControllerTableView extends GenericController{

	public ControllerTableView() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo che associa la colonna ai dati che deve visualizzare
	 * 
	 * @param colonna della View
	 * @param nome dell'attributo di riferimento per quei dati
	 * @return col: la colonna con le associazioni
	 */
	protected <S,T> TableColumn<S, T> initColumn(TableColumn<S, T> col, String colName){
		col.setCellValueFactory(new PropertyValueFactory<S, T>(colName));
		return col;
	}
}
