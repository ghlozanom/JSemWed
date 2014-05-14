/*
 * Created on 28-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.rdfs.label;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author Hernando
 *
 */
public class IngresarLabelOpUI extends OperacionUI implements IOperacionUI, ActionListener {

    private JDialog ispDialog;
    private JComboBox propiedadesCB;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private Vector statements;
    private JTextField tf;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public Vector getStatement() {

        ispDialog = new JDialog( ventanaPrincipal, 
                labels.getString("IngresarLabel"), true );
        
        JPanel propiedadPanel = new JPanel( new GridLayout(2,1) );
        JPanel labelPanel = new JPanel();
        ((FlowLayout)labelPanel.getLayout()).setAlignment(FlowLayout.CENTER );
        JLabel propiedadLabel = new JLabel( labels.getString("IngresarLabel"));
        labelPanel.add( propiedadLabel );
        ((GridLayout)propiedadPanel.getLayout()).setVgap( 10 );
        tf = new JTextField();
        propiedadPanel.add( labelPanel );
        propiedadPanel.add( tf );
        
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
            
            if( tf.getText().equals("")) return;
            String[] statement = new String[2];
            statement[0] = uriNodoSelecto;
            statement[1] = "\"" + tf.getText() + "\"";
            statements = new Vector();
            statements.add( statement );            
            
        }
        
        ispDialog.setVisible( false );
        
    }

}
