/*
 * Created on Feb 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.resources.elementos.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;

/**
 * Dialog para implementar la edicion de elementos en los paneles.
 * 
 * @author ghlozanom
 */
public class EdicionDialog implements ActionListener {

	JDialog edicionDialog;
	private JFrame frame;
	private ResourceBundle labels;
	private ManejadorAyuda manejadorAyuda;
	private JTextField uriField;
	private JButton ingresarButton;
	private JButton cancelarButton;
	private JButton helpButton;
	private String elementoURI;

	public EdicionDialog( JFrame frame, ResourceBundle labels,
			ManejadorAyuda manejadorAyuda ){
		
		this.frame = frame;
		this.labels = labels;
		this.manejadorAyuda = manejadorAyuda;
	}
	
	/**
	 * Obtiene el nuevo URI del elemento selecto.
	 * 
	 * @param urlNodoSelecto
	 * @return URI nuevo del elemento editado.
	 */
	public String getNuevoURI(String urlNodoSelecto) {
		
        edicionDialog = new JDialog( frame, labels.getString("editarElementoLabel"), true ); 
        edicionDialog.setSize(300, 150 );
        edicionDialog.setResizable( false );
        edicionDialog.setLocationRelativeTo( frame );
        manejadorAyuda.setHelpKey( edicionDialog, "modelo_ingresar_resources_htm");
        
        uriField = new JTextField(20);
        uriField.addActionListener( this );
        ingresarButton = new JButton(labels.getString("Aceptar"));
        ingresarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString("Cancelar") );
        cancelarButton.addActionListener( this );
        helpButton = manejadorAyuda.getBotonAyuda( "modelo_ingresar_resources_htm" );
        
        JPanel panel = new JPanel( new BorderLayout() );
        JPanel URIPanel = new JPanel();
        JLabel uriLabel = new JLabel(labels.getString("editarURI") + urlNodoSelecto);
        URIPanel.add(uriLabel);
        URIPanel.add( uriField );
        panel.add( URIPanel, BorderLayout.CENTER );
        JPanel botonesPanel = new JPanel();
        botonesPanel.add( ingresarButton );
        botonesPanel.add( cancelarButton );
        botonesPanel.add( helpButton );
        
        panel.add( botonesPanel, BorderLayout.SOUTH );
        
        edicionDialog.getContentPane().add( panel );
        
        edicionDialog.setVisible( true );
        edicionDialog = null;
        
        return elementoURI;
        
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		if( arg0.getSource().equals(ingresarButton) )
			elementoURI = uriField.getText();
		else
			elementoURI = null;
			
		edicionDialog.setVisible( false );
		
	}
	
	
	
}
