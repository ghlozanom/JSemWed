/*
 * Created on 28-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.claseowl;

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
public class IngresarClaseEquivalenteOpUI extends OperacionUI implements
        IOperacionUI, ActionListener {

    private JDialog iceDialog;
    private JComboBox clasesCB;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private Vector statements;
    static int POS_CLASES = 1;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public Vector getStatement() {

        iceDialog = new JDialog( ventanaPrincipal, 
                labels.getString("IngresarClaseEquivalente"), true );
        
        JPanel clasePanel = new JPanel( new GridLayout(2,1) );
        JPanel labelPanel = new JPanel();
        ((FlowLayout)labelPanel.getLayout()).setAlignment(FlowLayout.CENTER );
        JLabel claseLabel = new JLabel( labels.getString("IngresarClaseEquivalente"));
        labelPanel.add( claseLabel );
        ((GridLayout)clasePanel.getLayout()).setVgap( 10 );
        Vector clasesVector = new Vector();
        Iterator it = allElements[POS_CLASES].iterator();
        while( it.hasNext() ){
            
            clasesVector.add( ((IElemento)it.next()).getURI());
            
        }
        clasesCB = new JComboBox( clasesVector);
        clasePanel.add( labelPanel );
        clasePanel.add( clasesCB );
        
        JPanel botonesPanel = new JPanel();
        aceptarButton = new JButton( labels.getString("Aceptar") );
        aceptarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString( "Cancelar") );
        cancelarButton.addActionListener( this );
        JButton ayudaButton = manejadorAyuda.getBotonAyuda("semantic_web_modelos_rdfs_propiedades_htm");
        botonesPanel.add( aceptarButton );
        botonesPanel.add( cancelarButton );
        botonesPanel.add( ayudaButton );
        
        
        iceDialog.getContentPane().setLayout( new BorderLayout() );
        ((BorderLayout)iceDialog.getContentPane().getLayout()).setVgap( 20 );
        iceDialog.getContentPane().add( clasePanel, BorderLayout.CENTER );
        iceDialog.getContentPane().add( botonesPanel, BorderLayout.SOUTH );
        iceDialog.setSize( 300, 150 );
        iceDialog.setLocationRelativeTo( ventanaPrincipal );
        iceDialog.setVisible( true );
        
        return statements;
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals( aceptarButton ) ){
            
            String[] statement = new String[2];
            statement[0] = uriNodoSelecto;
            statement[1] = clasesCB.getSelectedItem().toString();
            statements = new Vector();
            statements.add( statement );            
            
        }
        
        iceDialog.setVisible( false );
        
    }

}
