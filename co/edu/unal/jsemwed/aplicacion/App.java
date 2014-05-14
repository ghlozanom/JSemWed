/*
 * Created on 17-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.aplicacion;

import java.util.ResourceBundle;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.comun.IUI;
import co.edu.unal.jsemwed.swEngine.ISWEngine;

/**
 * Clase inicial de la aplicación.
 * Instancia las implementaciones de las interfaces de los subsistemas y
 * configura cada uno de ellos.
 * 
 * @author Gabriel
 *
 */
public class App {

	ResourceBundle resource = ResourceBundle.getBundle("config");
	
	public static void main(String[] args) {
		
		App app = new App();
	}
	
	public App(){
		
		try {
			//Para obtener la clase que implementa la interfaz de usuario
			IUI ui = (IUI) Class.forName(resource.getString("ui")).newInstance();
			
			//Para obtener la clase que implementa el engine de Semantic Web
			ISWEngine swEngine = (ISWEngine) Class.forName(resource.getString("swEngine")).newInstance();
			
			//Para obtener la clase que implementa el controlador
			IControlador controlador = (IControlador) Class.forName(resource.getString("controlador")).newInstance();
			
			//Le indica la interfaz de usuario al controlador
			controlador.setUi(ui);
			
			//Le indica el engine de Semantic Web a utilizar
			controlador.setSwEngine( swEngine );
			
			//Le indica a la interfaz de usuario el controlador a ingresar
			//TODO revisar si es controlador lo que debe ver la ui
			ui.setControlador( controlador );
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
