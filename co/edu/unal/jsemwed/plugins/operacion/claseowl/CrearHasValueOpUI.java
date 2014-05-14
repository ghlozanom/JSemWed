/*
 * Created on 30-abr-2005
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
public class CrearHasValueOpUI extends OperacionUI implements IOperacionUI, ActionListener {

    private JDialog ispDialog;
    private JComboBox propiedadesCB;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private Vector statements;
    private JComboBox propiedadesCB2;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public Vector getStatement() {

        ispDialog = new JDialog( ventanaPrincipal, 
                labels.getString("CrearHasValue"), true );
        
        JPanel propiedadPanel = new JPanel( new GridLayout(4,1) );
        JPanel labelPanel1 = new JPanel();
        ((FlowLayout)labelPanel1.getLayout()).setAlignment(FlowLayout.CENTER );
        JLabel propiedadLabel = new JLabel( labels.getString("IngresePropiedad"));
        labelPanel1.add( propiedadLabel );
        ((GridLayout)propiedadPanel.getLayout()).setVgap( 10 );
        Vector propiedadesVector = new Vector();
        Iterator it = allElements[2].iterator();
        while( it.hasNext() ){
            
            propiedadesVector.add( ((IElemento)it.next()).getURI());
            
        }
        propiedadesCB = new JComboBox( propiedadesVector);
        propiedadPanel.add( labelPanel1 );
        propiedadPanel.add( propiedadesCB );

        JPanel labelPanel2 = new JPanel();
        ((FlowLayout)labelPanel2.getLayout()).setAlignment(FlowLayout.CENTER );
        JLabel propiedadLabel2 = new JLabel( labels.getString("IngreseIndividuo"));
        labelPanel2.add( propiedadLabel2 );
        Vector propiedadesVector2 = new Vector();
        Iterator it2 = allElements[3].iterator();
        while( it2.hasNext() ){
            
            propiedadesVector2.add( ((IElemento)it2.next()).getURI());
            
        }
        propiedadesCB2 = new JComboBox( propiedadesVector2);
        propiedadPanel.add( labelPanel2 );
        propiedadPanel.add( propiedadesCB2 );
        
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
        ispDialog.setSize( 300, 250 );
        ispDialog.setLocationRelativeTo( ventanaPrincipal );
        ispDialog.setVisible( true );
        
        return statements;
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals( aceptarButton ) ){
            
            String[] statement = new String[3];
            statement[0] = uriNodoSelecto;
            statement[1] = propiedadesCB.getSelectedItem().toString();
            statement[2] = propiedadesCB2.getSelectedItem().toString();
            statements = new Vector();
            statements.add( statement );            
            
        }
        
        ispDialog.setVisible( false );
        
    }

}
