/*
 * Created on Mar 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.modelo.qnames.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EditarQNameDialog implements ActionListener {

	private String[] qname;
	private ResourceBundle labels;
	private Component ventanaPrincipal;
	private JTextField uriTF;
	private JButton aceptarButton;
	private JButton cancelarButton;
	private String uriNueva;
	private JDialog editarDialog;

	/**
	 * @param labels
	 * @param ventanaPrincipal
	 */
	public EditarQNameDialog(ResourceBundle labels, Component ventanaPrincipal) {
		
		this.labels = labels;
		this.ventanaPrincipal = ventanaPrincipal;
	}

	/**
	 * @return
	 */
	public String getNuevoURI() {
		
		editarDialog = new JDialog( (JFrame)ventanaPrincipal,
				labels.getString("EditarURI"), true );
		
		editarDialog.setSize( 350, 100 );
		editarDialog.setResizable( false );
		editarDialog.setLocationRelativeTo( ventanaPrincipal );
		
		JPanel editarPanel = new JPanel( new BorderLayout() );
		
		JPanel uriPanel = new JPanel();
		JLabel uriLabel = new JLabel( labels.getString("IngreseURIpara") + 
				" " + qname[0] );
		uriTF = new JTextField( 15 );
		uriPanel.add( uriLabel );
		uriPanel.add( uriTF );
		
		JPanel botonesPanel = new JPanel();
		aceptarButton = new JButton( labels.getString("Aceptar") );
		cancelarButton = new JButton( labels.getString("Cancelar") );
		aceptarButton.addActionListener( this );
		cancelarButton.addActionListener( this );
		botonesPanel.add( aceptarButton );
		botonesPanel.add( cancelarButton );
		
		editarPanel.add( uriPanel, BorderLayout.CENTER );
		editarPanel.add( botonesPanel, BorderLayout.SOUTH );
		editarDialog.getContentPane().add( editarPanel );
		
		editarDialog.setVisible( true );
		
		return uriNueva;
	}

	/**
	 * @param qname
	 */
	public void setQNameSeleccionado(String[] qname) {
		
		this.qname = qname;
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		if( arg0.getSource().equals(aceptarButton) )
			if( ! uriTF.getText().equals("") ){
				uriNueva = uriTF.getText();
				editarDialog.setVisible( false );
				return;
			}
		uriNueva = null;
		editarDialog.setVisible( false );
		
	}

}
