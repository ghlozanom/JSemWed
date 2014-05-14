/*
 * Created on Mar 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.modelo.qnames.dialogs;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EliminarQNameDialog {

	private ResourceBundle labels;
	private Component ventanaPrincipal;

	/**
	 * @param labels
	 * @param ventanaPrincipal
	 */
	public EliminarQNameDialog(ResourceBundle labels, Component ventanaPrincipal) {
		
		this.labels = labels;
		this.ventanaPrincipal = ventanaPrincipal;
		
	}

	/**
	 * @return
	 */
	public boolean getDecision() {
		
    	String[] opciones = { labels.getString("si"), labels.getString("no")};
    	
        int seleccion = JOptionPane.showOptionDialog(
                ventanaPrincipal,
                labels.getString("EstaSeguro") + " ?",
				labels.getString("eliminarQName"),
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				opciones,
				labels.getString( "no" ));
		
        if( seleccion == 0 )
        	return true;
		return false;
	}

}
