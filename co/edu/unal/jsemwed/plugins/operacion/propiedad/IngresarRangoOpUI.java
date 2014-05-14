/*
 * Created on 22-abr-2005
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
 * @author Gabriel
 *
 */
public class IngresarRangoOpUI extends OperacionUI implements IOperacionUI, ActionListener {

    private JDialog irDialog;
    private JComboBox claseCB;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private Vector statements;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public Vector getStatement() {
        
        irDialog = new JDialog( ventanaPrincipal, 
                labels.getString("IngresarRango"), true );
        
        JPanel rangoPanel = new JPanel( new GridLayout(2,1) );
        JPanel labelPanel = new JPanel();
        ((FlowLayout)labelPanel.getLayout()).setAlignment(FlowLayout.CENTER );
        JLabel superClaseLabel = new JLabel( labels.getString("IngresarRango"));
        labelPanel.add( superClaseLabel );
        ((GridLayout)rangoPanel.getLayout()).setVgap( 10 );
        Vector claseVector = new Vector();
        Iterator it = allElements[1].iterator();
        while( it.hasNext() ){
            
            claseVector.add( ((IElemento)it.next()).getURI());
            
        }
        claseCB = new JComboBox( claseVector);
        rangoPanel.add( labelPanel );
        rangoPanel.add( claseCB );
        
        JPanel botonesPanel = new JPanel();
        aceptarButton = new JButton( labels.getString("Aceptar") );
        aceptarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString( "Cancelar") );
        cancelarButton.addActionListener( this );
        JButton ayudaButton = manejadorAyuda.getBotonAyuda("semantic_web_modelos_rdfs_dominio_htm");
        botonesPanel.add( aceptarButton );
        botonesPanel.add( cancelarButton );
        botonesPanel.add( ayudaButton );
        
        
        irDialog.getContentPane().setLayout( new BorderLayout() );
        ((BorderLayout)irDialog.getContentPane().getLayout()).setVgap( 20 );
        irDialog.getContentPane().add( rangoPanel, BorderLayout.CENTER );
        irDialog.getContentPane().add( botonesPanel, BorderLayout.SOUTH );
        irDialog.setSize( 300, 150 );
        irDialog.setLocationRelativeTo( ventanaPrincipal );
        irDialog.setVisible( true );
        
        return statements;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals( aceptarButton ) ){
            
            String[] statement = new String[2];
            statement[0] = uriNodoSelecto;
            statement[1] = claseCB.getSelectedItem().toString();
            statements = new Vector();
            statements.add( statement );            
            
        }
        
        irDialog.setVisible( false );
        
    }


}
