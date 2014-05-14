/*
 * Created on Mar 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.plugins.operacion.agregarElementoContainer;

import java.awt.BorderLayout;
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

import com.hp.hpl.jena.vocabulary.RDF;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgregarElementoContainerOpUI 
extends OperacionUI
implements IOperacionUI, ActionListener, ItemListener {

	private JFrame ventanaPrincipal;
	private String uriNodoSelecto;
	private Vector elementos;
	private ResourceBundle labels;
	private String[] statement = new String[3];
	private JDialog agregarElementoDialog;
	private JComboBox agregarElementoCB;
	private JButton aceptarButton;
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
		
		statement[0] = uriNodoSelecto;
		int numRecursos = getNumeroRecursos();
		//Hallar el numero de recurso a ingresar
		statement[1] = RDF.getURI() + "_" + ++numRecursos;
		
		
		JPanel elementoPanel = new JPanel();
		JLabel agregarElementoLabel = new JLabel( labels.getString("escogerElemento"));
		agregarElementoCB = new JComboBox( getVectorNombreElementos() );
		agregarElementoCB.addItemListener( this );
		elementoPanel.add( agregarElementoLabel );
		elementoPanel.add( agregarElementoCB );
		statement[2] = agregarElementoCB.getSelectedItem().toString();
		
		JPanel botonesPanel = new JPanel();
		aceptarButton = new JButton( labels.getString("Aceptar"));
		aceptarButton.addActionListener( this );
		cancelarButton = new JButton( labels.getString("Cancelar"));
		cancelarButton.addActionListener( this );
		botonesPanel.add( aceptarButton );
		botonesPanel.add( cancelarButton );
		
		agregarElementoDialog = new JDialog( this.ventanaPrincipal,
				labels.getString("agregarElementoContainer"), true );
		JPanel agregarElementoPanel = new JPanel( new BorderLayout() );
		agregarElementoPanel.add( elementoPanel, BorderLayout.CENTER );
		agregarElementoPanel.add( botonesPanel,BorderLayout.SOUTH );
		
		agregarElementoDialog.setSize( 300, 150 );
		agregarElementoDialog.setLocationRelativeTo( this.ventanaPrincipal );
		agregarElementoDialog.getContentPane().add( agregarElementoPanel );
		agregarElementoDialog.setVisible( true );
		agregarElementoDialog = null;
		
		if( statement == null ) return null;
		
		Vector vector = new Vector(1);
		vector.add(statement);
		
		return vector;
		
	}

	/**
	 * Halla el numero de recursos que se han ingresado en este container
	 * @return
	 */
	private int getNumeroRecursos() {
		
		Iterator elementosIt = elementos.iterator();
		IElemento elemento = null;
		while( elementosIt.hasNext() ){
			
			elemento = (IElemento) elementosIt.next();
			if( elemento.getURI().equals(uriNodoSelecto) )
				break;
		}
		int numRes = 0;
		Iterator propiedadesIt = elemento.getPropiedades().iterator();
		while( propiedadesIt.hasNext() ){
			
			Object[] prop = (Object[]) propiedadesIt.next();
			if( ((String)prop[0]).startsWith("http://www.w3.org/1999/02/22-rdf-syntax-ns#_"))
			{
				String numero = ((String)prop[0]).substring(44);
				int numeroint = Integer.parseInt( numero );
				if( numeroint > numRes )
					numRes = numeroint;
			}
		}
		
		return numRes;
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
	public Vector getVectorNombreElementos( ) {
		
		Vector elementos2 = new Vector();
		Iterator it = this.elementos.iterator();
		while( it.hasNext() ){
			
			elementos2.add( ((IElemento)it.next()).getURI() );
			
		}
		return elementos2;
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
		
		if( arg0.getSource().equals(cancelarButton) )
			statement = null;
		agregarElementoDialog.setVisible( false );
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent arg0) {
		
		statement[2] = agregarElementoCB.getSelectedItem().toString();
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setElementos(java.util.Vector)
	 */
	public void setElementos(Vector vector) {
		
		this.elementos = vector;
		
	}

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        // TODO Auto-generated method stub
        
    }

}
