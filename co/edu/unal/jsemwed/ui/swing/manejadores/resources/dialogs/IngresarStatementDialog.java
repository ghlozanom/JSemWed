/*
 * Creado el  Jan 23, 2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.resources.dialogs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
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
 * @author ghlm81
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IngresarStatementDialog implements ItemListener, ActionListener {

	private ResourceBundle labels;
	private ManejadorAyuda manejadorAyuda;
	private Vector resources;
	private JDialog statementDialog;
	private JComboBox subjectComboBox;
	private JComboBox predicateComboBox;
	private JComboBox objectComboBox;
	private JButton ingresarButton;
	private JButton cancelarButton;
	private JButton helpButton;
	
	private String[] statement = new String[3];
	
	/**
	 * @param labels
	 * @param manejadorAyuda
	 * @param resources
	 */
	public IngresarStatementDialog(ResourceBundle labels, ManejadorAyuda manejadorAyuda, Vector resources) {
		
		this.labels = labels;
		this.manejadorAyuda = manejadorAyuda;
		this.resources = resources;
		
	}

	/**
	 * @param ventanaPrincipal
	 * @param urlNodoSelecto
	 * @return
	 */
	public String[] getStatement(JFrame ventanaPrincipal, String urlNodoSelecto) {
		
	       statementDialog = new JDialog(  ventanaPrincipal, labels.getString("ingresarStatement"), true ); 
	        statementDialog.setSize(300, 150 );
	        statementDialog.setResizable( false );
	        statementDialog.setLocationRelativeTo( ventanaPrincipal );
	        manejadorAyuda.setHelpKey( statementDialog, "acerca_de_jsemwed_htm");
	        
	        JLabel subjectLabel = new JLabel( labels.getString( "sujeto" ) );
	        subjectComboBox = new  JComboBox( resources );
	        subjectComboBox.addItemListener( this );        
	        statement[0] = subjectComboBox.getSelectedItem().toString();
	        //Si debe preseleccionar un subject
	        if( urlNodoSelecto != null ){
	            Iterator i = resources.iterator();
	            int count = 0;
	            while( i.hasNext() ){
	                
	                String resourceURL = (String) i.next();
	                if( resourceURL.equals(urlNodoSelecto) )
	                    subjectComboBox.setSelectedIndex( count );
	                count++;
	            }
	        }
	        
	        JLabel predicateLabel = new JLabel( labels.getString( "predicado") );
	        predicateComboBox = new JComboBox( resources );
	        predicateComboBox.addItemListener( this );
	        statement[1] = predicateComboBox.getSelectedItem().toString();
	        
	        JLabel objectLabel = new JLabel( labels.getString( "objeto" ) );
	        objectComboBox = new JComboBox( resources );
	        objectComboBox.addItemListener( this );
	        statement[2] = objectComboBox.getSelectedItem().toString();
	        
	        JPanel statementPanel = new JPanel(  new GridLayout(3,2) );
	        statementPanel.add( subjectLabel );
	        statementPanel.add( subjectComboBox );
	        statementPanel.add( predicateLabel );
	        statementPanel.add( predicateComboBox );
	        statementPanel.add( objectLabel );
	        statementPanel.add( objectComboBox );
	        
	        ingresarButton = new JButton(labels.getString("Aceptar"));
	        ingresarButton.addActionListener( this );
	        cancelarButton = new JButton( labels.getString("Cancelar") );
	        cancelarButton.addActionListener( this );
	        helpButton = manejadorAyuda.getBotonAyuda( "modelo_ingresar_statement_htm" );
	        
	        JPanel panel = new JPanel( new BorderLayout() );

	        panel.add( statementPanel, BorderLayout.CENTER );
	        JPanel botonesPanel = new JPanel();
	        botonesPanel.add( ingresarButton );
	        botonesPanel.add( cancelarButton );
	        botonesPanel.add( helpButton );
	        	        
	        panel.add( botonesPanel, BorderLayout.SOUTH );
	        
	        //JLabel label = new JLabel( new ImageIcon("images/comp.jpg"));
	        //panel.add( label, BorderLayout.WEST );
	    	        
	        statementDialog.getContentPane().add( panel );
	        
	        statementDialog.setVisible( true );
	        statementDialog = null;
	        
	        return statement;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent arg0) {
		
		Object source = arg0.getSource();
		
		if ( source.equals( subjectComboBox) )
			statement[0] = subjectComboBox.getSelectedItem().toString();
		
		else if( source.equals( predicateComboBox ) )
			statement[1] = predicateComboBox.getSelectedItem().toString();
		
		else if( source.equals( objectComboBox ) )
			statement[2] = objectComboBox.getSelectedItem().toString();
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		if( arg0.getSource().equals( cancelarButton) )
			statement = null;

		statementDialog.setVisible( false );
		
	}

}
