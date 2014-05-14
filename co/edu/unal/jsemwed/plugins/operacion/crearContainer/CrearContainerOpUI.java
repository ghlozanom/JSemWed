/*
 * Created on Mar 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.plugins.operacion.crearContainer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hp.hpl.jena.vocabulary.RDF;

import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CrearContainerOpUI 
extends OperacionUI
implements IOperacionUI, ItemListener, ActionListener {

	private JFrame ventanaPrincipal;
	private JDialog crearContainerDialog;
	private String urlNodoSelecto;
	private Vector elementos;
	private ResourceBundle labels;
	private JComboBox tipoContainerCB;
	private String[] statement = new String[3]; 
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
		
		statement[0] = urlNodoSelecto;
		statement[1] = RDF.getURI() + "type" ;
		statement[2] = RDF.getURI() + "Bag" ;
		
		crearContainerDialog = new JDialog( ventanaPrincipal, 
				labels.getString( "crearContainer" ), true );
		crearContainerDialog.setSize( 300, 150 );
		
		JPanel tipoContainerPanel = new JPanel(  );
		JLabel tipoContainerLabel = new JLabel( labels.getString("tipoContainer"));
		tipoContainerCB = new JComboBox();
		tipoContainerCB.addItem( "Bag");
		tipoContainerCB.addItem( "Seq");
		tipoContainerCB.addItem( "Alt");
		tipoContainerCB.addItemListener( this );
		tipoContainerPanel.add( tipoContainerLabel );
		tipoContainerPanel.add( tipoContainerCB );
		
		JPanel botonesPanel = new JPanel();
		JButton aceptarButton = new JButton( labels.getString("Aceptar"));
		aceptarButton.addActionListener( this );
		cancelarButton = new JButton( labels.getString("Cancelar"));
		cancelarButton.addActionListener( this );
		JButton ayudaButton = manejadorAyuda.getBotonAyuda("semantic_web_modelos_rdf_rdf_containers_htm");
		botonesPanel.add( aceptarButton );
		botonesPanel.add( cancelarButton );
		botonesPanel.add( ayudaButton );
		
		
        JPanel containerPanel = new JPanel( new BorderLayout() );
        containerPanel.add( tipoContainerPanel, BorderLayout.CENTER );
        containerPanel.add( botonesPanel, BorderLayout.SOUTH );
        crearContainerDialog.getContentPane().add( containerPanel );
        crearContainerDialog.setLocationRelativeTo( ventanaPrincipal );
        crearContainerDialog.setVisible( true );
        crearContainerDialog = null;
        
        if( statement == null ) return null;
        Vector vector = new Vector(1);
        vector.add( statement );
        return vector;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setNodoSelecto(java.lang.String)
	 */
	public void setNodoSelecto(String urlNodoSelecto) {
		
		this.urlNodoSelecto = urlNodoSelecto;

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
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent arg0) {
		
		String tipoContainerSeleccionado = tipoContainerCB.getSelectedItem().toString();
		
		statement[2] = RDF.getURI() + tipoContainerSeleccionado;
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		if( arg0.getSource() == cancelarButton )
			statement = null;
		crearContainerDialog.setVisible( false );
		
	}

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        // TODO Auto-generated method stub
        
    }

}
