/*
 * Created on Feb 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.comun;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;

import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IOperacionUI {

	/**
	 * Configuracion de la ventana principal para crear el componente que obtenga
	 * los parametros de la operacion.
	 * 
	 * @param ventanaPrincipal componente principal de la interfaz de usuario.
	 */
	public void setFrame(JFrame ventanaPrincipal);

	/**
	 * Retorna los statements que implementan la operación
	 * 
	 * @return statements para llevar a cabo la operacion
	 */
	public Vector getStatement();

	/**
	 * Configuracion del nodo que llamo la operacion
	 * 
	 * @param urlNodoSelecto nodo que llamo la operacion
	 */
	public void setNodoSelecto(String urlNodoSelecto);

	/**
	 * Configuracion de los elementos previamente ingresados
	 * del mismo tipo para llevar a cabo
	 * la operacion
	 * 
	 * @param vector elementos ingresados.
	 */
	public void setElementos(Vector vector);

	/**
	 * Configuracion de los elementos previamente ingresados de 
	 * todo tipo para llevar a cabo la operacion.
	 * 
	 * @param vector de elementos ingresados
	 */
	public void setAllElements( Vector[] vector );
	
	/**
	 * Configuracion de labels para soportar i18n.
	 * @param labels
	 */
	public void setLabels(ResourceBundle labels);

    /**
     * Configuracion del componente de ayuda.
     * 
     * @param manejadorAyuda
     */
    public void setManejadorAyuda(ManejadorAyuda manejadorAyuda);

}
