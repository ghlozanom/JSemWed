/*
 * Created on 29-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.propiedad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author Hernando
 *
 */
public class IngresarInverseOfOpUI extends OperacionUI implements
        IOperacionUI, ActionListener {

    private JDialog ispDialog;
    private JComboBox propiedadesCB;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private Vector statements;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public Vector getStatement() {

        ispDialog = new JDialog( ventanaPrincipal, 
                labels.getString("IngresarInverseOf"), true );
        
        JPanel propiedadPanel = new JPanel( new GridLayout(2,1) );
        JPanel labelPanel = new JPanel();
        ((FlowLayout)labelPanel.getLayout()).setAlignment(FlowLayout.CENTER );
        JLabel propiedadLabel = new JLabel( labels.getString("IngresarInverseOf"));
        labelPanel.add( propiedadLabel );
        ((GridLayout)propiedadPanel.getLayout()).setVgap( 10 );
        Vector propiedadesVector = new Vector();
        Iterator it = allElements[2].iterator();
        while( it.hasNext() ){
            
            propiedadesVector.add( ((IElemento)it.next()).getURI());
            
        }
        propiedadesCB = new JComboBox( propiedadesVector);
        propiedadPanel.add( labelPanel );
        propiedadPanel.add( propiedadesCB );
        
        JPanel botonesPanel = new JPanel();
        aceptarButton = new JButton( labels.getString("Aceptar") );
        aceptarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString( "Cancelar") );
        cancelarButton.addActionListener( this );
        JButton ayudaButton = manejadorAyuda.getBotonAyuda("semantic_web_modelos_rdfs_propiedades_htm");
        botonesPanel.add( aceptarButton );
        botonesPanel.add( cancelarButton );
        botonesPanel.add( ayudaButton );
        
        
        ispDialog.getContentPane().setLayout( new BorderLayout() );
        ((BorderLayout)ispDialog.getContentPane().getLayout()).setVgap( 20 );
        ispDialog.getContentPane().add( propiedadPanel, BorderLayout.CENTER );
        ispDialog.getContentPane().add( botonesPanel, BorderLayout.SOUTH );
        ispDialog.setSize( 300, 150 );
        ispDialog.setLocationRelativeTo( ventanaPrincipal );
        ispDialog.setVisible( true );
        
        return statements;
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals( aceptarButton ) ){
            
            String[] statement = new String[2];
            statement[0] = uriNodoSelecto;
            statement[1] = propiedadesCB.getSelectedItem().toString();
            statements = new Vector();
            statements.add( statement );            
            
        }
        
        ispDialog.setVisible( false );
        
    }


}
