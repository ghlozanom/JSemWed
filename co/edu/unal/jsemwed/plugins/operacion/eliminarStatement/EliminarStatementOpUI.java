/*
 * Created on Feb 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.plugins.operacion.eliminarStatement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EliminarStatementOpUI 
extends OperacionUI
implements IOperacionUI,
ActionListener, ItemListener{

	
	private JDialog statementDialog;
	private JFrame ventanaPrincipal;
	private ResourceBundle labels;
	private String uriNodoSelecto;
	private String[] statement = new String[3];
	private Vector elementos;
	private IElemento elemento;
	private JComboBox predicateComboBox;
	Vector valoresPropiedad = null;
	Vector propiedades = null;
	private JComboBox objectComboBox;
	private JButton ingresarButton;
	private JButton cancelarButton;
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setFrame(javax.swing.JFrame)
	 */
	public void setFrame(JFrame ventanaPrincipal) {
		
		this.ventanaPrincipal = ventanaPrincipal;

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
	 */
	public Vector getStatement() {
		
	       statementDialog = new JDialog(  ventanaPrincipal, labels.getString("ingresarStatement"), true ); 
	        statementDialog.setSize(300, 150 );
	        statementDialog.setResizable( false );
	        statementDialog.setLocationRelativeTo( ventanaPrincipal );
	        //manejadorAyuda.setHelpKey( statementDialog, "acerca_de_jsemwed_htm");
	        
	        JLabel subjectLabel = new JLabel( labels.getString( "sujeto" ) );
	        JLabel subject = new JLabel( uriNodoSelecto );
	        statement[0] = uriNodoSelecto;
	        Iterator elementosIt = elementos.iterator();
	    
	        while( elementosIt.hasNext() ){
	        	
	        	elemento = (IElemento) elementosIt.next();
	        	if( elemento.getURI().equals(uriNodoSelecto) )
	        		break;
	        	
	        }
	        
	        //Si debe preseleccionar un subject
	        
	        
	        JLabel predicateLabel = new JLabel( labels.getString( "predicado") );
	        propiedades = elemento.getPropiedades();
	        Iterator propiedadesIt = propiedades.iterator();
	        String[] uriPropiedades = new String[propiedades.size() ];
	        int i = 0;
	        while( propiedadesIt.hasNext() ){
	        	
	        	Object[] propiedadNueva = (Object[]) propiedadesIt.next();
	        	uriPropiedades[i] = (String) propiedadNueva[0];
	        	if( i == 0 )
	        		valoresPropiedad = (Vector) propiedadNueva[1];
	        	i++;
	        	
	        	
	        }
	        predicateComboBox = new JComboBox( uriPropiedades );
	        predicateComboBox.addItemListener( this );
	        predicateComboBox.setSelectedIndex(0);
	        statement[1] = predicateComboBox.getSelectedItem().toString();
	        
	        JLabel objectLabel = new JLabel( labels.getString( "objeto" ) );
	        objectComboBox = new JComboBox( valoresPropiedad );
	        objectComboBox.addItemListener( this );
	        statement[2] = objectComboBox.getSelectedItem().toString();
	        
	        JPanel statementPanel = new JPanel(  new GridLayout(3,2) );
	        statementPanel.add( subjectLabel );
	        statementPanel.add( subject );
	        statementPanel.add( predicateLabel );
	        statementPanel.add( predicateComboBox );
	        statementPanel.add( objectLabel );
	        statementPanel.add( objectComboBox );
	        
	        ingresarButton = new JButton(labels.getString("Aceptar"));
	        ingresarButton.addActionListener( this );
	        cancelarButton = new JButton( labels.getString("Cancelar") );
	        cancelarButton.addActionListener( this );
	        //helpButton = manejadorAyuda.getBotonAyuda( "modelo_ingresar_statement_htm" );
	        
	        JPanel panel = new JPanel( new BorderLayout() );

	        panel.add( statementPanel, BorderLayout.CENTER );
	        JPanel botonesPanel = new JPanel();
	        botonesPanel.add( ingresarButton );
	        botonesPanel.add( cancelarButton );
	        //botonesPanel.add( helpButton );
	        	        
	        panel.add( botonesPanel, BorderLayout.SOUTH );
	        
	        //JLabel label = new JLabel( new ImageIcon("images/comp.jpg"));
	        //panel.add( label, BorderLayout.WEST );
	    	        
	        statementDialog.getContentPane().add( panel );
	        
	        statementDialog.setVisible( true );
	        statementDialog = null;
	        
	        if( statement == null ) return null;
	        
	        Vector vector = new Vector(1);
	        vector.add( statement );
	        return vector;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setNodoSelecto(java.lang.String)
	 */
	public void setNodoSelecto(String urlNodoSelecto) {
		
		this.uriNodoSelecto = urlNodoSelecto;

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setElementos(java.util.Vector)
	 */
	public void setElementos(Vector vector) {
		
		this.elementos = vector;

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setLabels(java.util.ResourceBundle)
	 */
	public void setLabels(ResourceBundle labels) {
		
		this.labels = labels;

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {

		if( arg0.getSource().equals( cancelarButton) )
			statement = null;

		statementDialog.setVisible( false );
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent arg0) {
		
		if( arg0.getSource().equals(predicateComboBox) ){
			
			String prop = arg0.getItem().toString();
			
	        Iterator propiedadesIt = propiedades.iterator();
	        String[] uriPropiedades = new String[propiedades.size() ];
	        int i = 0;
	        while( propiedadesIt.hasNext() ){
	        	
	        	Object[] propiedadNueva = (Object[]) propiedadesIt.next();
	        	if( propiedadNueva[0].toString().equals(prop) ){
	        		objectComboBox.setModel( 
	        				new DefaultComboBoxModel((Vector) propiedadNueva[1]) );
	        		statement[1] = propiedadNueva[0].toString();
	        		break;
	        	}
	        	if( i == 0 )
	        		valoresPropiedad = (Vector) propiedadNueva[1];
	        	i++;
	        	
	        	
	        }
	        statement[2] = objectComboBox.getSelectedItem().toString();
		}//fin if
		
		if( arg0.getSource().equals( objectComboBox) ){
			
			statement[2] = objectComboBox.getSelectedItem().toString();
		}
		
	}

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        // TODO Auto-generated method stub
        
    }

}
