/*
 * Created on 01-may-2005
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
public class IngresarIntersectionOfOpUI extends OperacionUI implements
        IOperacionUI, ActionListener {

    private JButton aceptarButton;
    private JButton cancelarButton;
    private JComboBox superClaseCB;
    private Vector statements;
    private JDialog iscDialog;
    private static int CLASES = 1;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public Vector getStatement() {
        
        iscDialog = new JDialog( ventanaPrincipal, 
                labels.getString("IngresarIntersectionOf"), true );
        
        JPanel superClasePanel = new JPanel( new GridLayout(2,1) );
        JPanel labelPanel = new JPanel();
        ((FlowLayout)labelPanel.getLayout()).setAlignment(FlowLayout.CENTER );
        JLabel superClaseLabel = new JLabel( labels.getString("IngresarIntersectionOf"));
        labelPanel.add( superClaseLabel );
        ((GridLayout)superClasePanel.getLayout()).setVgap( 10 );
        Vector superClaseVector = new Vector();
        Iterator it = this.allElements[CLASES].iterator();
        while( it.hasNext() ){
            
            superClaseVector.add( ((IElemento)it.next()).getURI());
            
        }
        superClaseCB = new JComboBox( superClaseVector);
        superClasePanel.add( labelPanel );
        superClasePanel.add( superClaseCB );
        
        JPanel botonesPanel = new JPanel();
        aceptarButton = new JButton( labels.getString("Aceptar") );
        aceptarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString( "Cancelar") );
        cancelarButton.addActionListener( this );
        JButton ayudaButton = manejadorAyuda.getBotonAyuda("semantic_web_modelos_rdfs_clases_htm");
        botonesPanel.add( aceptarButton );
        botonesPanel.add( cancelarButton );
        botonesPanel.add( ayudaButton );
        
        
        iscDialog.getContentPane().setLayout( new BorderLayout() );
        ((BorderLayout)iscDialog.getContentPane().getLayout()).setVgap( 20 );
        iscDialog.getContentPane().add( superClasePanel, BorderLayout.CENTER );
        iscDialog.getContentPane().add( botonesPanel, BorderLayout.SOUTH );
        iscDialog.setSize( 300, 150 );
        iscDialog.setLocationRelativeTo( ventanaPrincipal );
        iscDialog.setVisible( true );
        
        
        return statements;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals( aceptarButton ) ){
            
            String[] statement = new String[2];
            statement[0] = uriNodoSelecto;
            statement[1] = superClaseCB.getSelectedItem().toString();
            statements = new Vector();
            statements.add( statement );            
            
        }
        
        iscDialog.setVisible( false );
        
    }

}
