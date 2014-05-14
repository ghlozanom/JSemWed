/*
 * Created on 04-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.CasosDeUso.vistas;

import java.util.ResourceBundle;

import co.edu.unal.jsemwed.comun.IUI;
import co.edu.unal.jsemwed.swEngine.ISWEngine;


/**
 * Clase encargada de manejar las vistas en el controlador.
 * 
 * @author Gabriel
 *
 */
public class VistasHandler {

	IVista vista = null;
	/**Archivo que contiene la información de las clases que 
	 * implementan los diversos formatos
	 * 
	 */
	ResourceBundle resource = ResourceBundle.getBundle("co/edu/unal/jsemwed/controlador/CasosDeUso/vistas/vistas");
	
	private IUI ui;
	private ISWEngine swEngine;
    
    /**
     * @param swEngine El swEngine a configurar.
     */
    public void setSwEngine(ISWEngine swEngine) {
        this.swEngine = swEngine;
    }
    /**
     * @param ui El ui a configurar.
     */
    public void setUi(IUI ui) {
        this.ui = ui;
    }

	/**
	 * Adiciona una nueva vista al controlador
	 * 
	 * @param formato formato de la vista.
	 */
	public void adicionarVista(String formato) {
		
		try {
			//Crea la nueva vista a partir de un archivo con un formato determinado.
			//TODO Revisar las excepciones que bota aqui la aplicación.
			IVista nuevaVista = (IVista) Class.forName(resource.getString(formato)).newInstance();
			nuevaVista.setSWEngine(swEngine);
			nuevaVista.setUI( ui );
			
			//Adiciona la vista a las vistas actuales.
			IVista temp = vista;
			vista = nuevaVista;
			nuevaVista.setVista( temp );
			
			vista.pinteModelo();
			
			
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
