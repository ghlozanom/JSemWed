/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.modelo.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;

/**
 * Clase que implementa la caja de dialogo para crear un nuevo modelo.
 * 
 * @author ghlozanom
 */
public class CrearModeloDialog implements ItemListener, ActionListener {

	private JDialog crearModeloDialog;
	private JComboBox modelosCB;
	private JButton ingresarButton;
	private JButton cancelarButton;
	private String modelo;
	
	
	/**
	 * Enseña la caja de dialogo para crear modelo y obtiene el nombre de este.
	 * 
	 * @param labels internacionalizacion
	 * @param modelos modelos disponibles
	 * @param manejadorAyuda manejador de la ayuda
	 * @param ventanaPrincipal ventana principal
	 * @return nombre del modelo escogido por el usuario.
	 */
	public String getModelo(ResourceBundle labels, ResourceBundle modelos, Component frame, ManejadorAyuda manejadorAyuda) {

        crearModeloDialog = new JDialog( (JFrame) frame, labels.getString("crearModeloLabel"), true ); 
        crearModeloDialog.setSize(300, 150 );
        crearModeloDialog.setResizable( false );
        crearModeloDialog.setLocationRelativeTo( frame );
        //manejadorAyuda.setHelpKey( crearModeloDialog, "modelo_ingresar_resources_htm");
        
        Enumeration modelosEnum = modelos.getKeys();
        Vector modelosVector = new Vector();
        while( modelosEnum.hasMoreElements() ){
        	
        	modelosVector.add( modelosEnum.nextElement().toString() );
        	
        }
        modelosCB = new JComboBox( modelosVector );
        modelosCB.addItemListener( this );
        modelo = modelosCB.getSelectedItem().toString();
        ingresarButton = new JButton(labels.getString("Aceptar"));
        ingresarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString("Cancelar") );
        cancelarButton.addActionListener( this );
        //helpButton = manejadorAyuda.getBotonAyuda( "modelo_ingresar_resources_htm" );
        
        JPanel panel = new JPanel( new BorderLayout() );
        JPanel URIPanel = new JPanel();
        JLabel modelosLabel = new JLabel(labels.getString("crearModelo"));
        URIPanel.add(modelosLabel);
        URIPanel.add( modelosCB );
        panel.add( URIPanel, BorderLayout.CENTER );
        JPanel botonesPanel = new JPanel();
        botonesPanel.add( ingresarButton );
        botonesPanel.add( cancelarButton );
        botonesPanel.add( manejadorAyuda.getBotonAyuda( "semantic_web_conceptos_b_sicos_en_que_consiste_semantic_web_htm") );
        //botonesPanel.add( helpButton );
        
        panel.add( botonesPanel, BorderLayout.SOUTH );
        
        crearModeloDialog.getContentPane().add( panel );
        
        crearModeloDialog.setVisible( true );
        crearModeloDialog = null;
        
        return modelo;
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent arg0) {
		
		if ( arg0.getStateChange() == ItemEvent.SELECTED ){
			
			modelo = modelosCB.getSelectedItem().toString();
			
		}
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		
        if ( arg0.getSource().equals(ingresarButton) ){
            
            
        }
        else if( arg0.getSource().equals(cancelarButton))
            modelo = null;
        
        crearModeloDialog.setVisible( false );
		
	}

}
