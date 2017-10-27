package outdoorapp.utils;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class SessionCache {
	public static HashMap<String, Pane> mapViews = new HashMap<>();
	public static Queue<Stage> stageQueue = new ArrayDeque<>();
	public static AnchorPane nestedAnchorPane;
	public static HashMap<String, Object> currentData = new HashMap<>(); 
	
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
	
	/**
	 * Restituisce il dato della sessione corrente in base 
	 * alla chiave 
	 * 
	 * @param key: chiave del valore
	 * @return il dato rispetto alla chiave
	 */
	public static Object getCurrentData(String key){
		return currentData.get(key);
	}

}
