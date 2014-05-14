/*
 * Created on 16-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.busquedas.busquedaAbierta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import co.edu.unal.jsemwed.comun.IBusqueda;

/**
 * @author Gabriel
 *
 */
public class BusquedaAbierta implements IBusqueda, ActionListener {

    private ResourceBundle labels;
    private Vector[] elementos;
    private JFrame frame;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private String query = null;
    private JTextArea busquedaArea;
    private JDialog dialog;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IBusqueda#getQuery()
     */
    public String getQuery() {
        
        dialog = new JDialog( frame, labels.getString("BusquedaAbierta"),
                true );
        dialog.setSize( 500, 200 );
        dialog.setLocationRelativeTo( frame );
        
        JPanel queryPanel = new JPanel();
        busquedaArea = new JTextArea(5,35);
        busquedaArea.setBorder(
                BorderFactory.createTitledBorder(labels.getString("Busqueda")));

        queryPanel.add(busquedaArea );
        
        JPanel botonesPanel = new JPanel();
        aceptarButton = new JButton( labels.getString("Aceptar") );
        aceptarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString("Cancelar") );
        cancelarButton.addActionListener( this );
        botonesPanel.add( aceptarButton );
        botonesPanel.add( cancelarButton );
        
        JPanel busquedaPanel = new JPanel( new BorderLayout() );
        busquedaPanel.add( queryPanel, BorderLayout.CENTER );
        busquedaPanel.add( botonesPanel, BorderLayout.SOUTH );
        
        dialog.getContentPane().add( busquedaPanel );
        dialog.setVisible( true );
        
        return query;
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IBusqueda#setJFrame(javax.swing.JFrame)
     */
    public void setJFrame(JFrame frame) {
        
        this.frame = frame;

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IBusqueda#setElementos(java.util.Vector[])
     */
    public void setElementos(Vector[] elementos) {
        
        this.elementos = elementos;

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IBusqueda#setLabels(java.util.ResourceBundle)
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;

    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals(aceptarButton) ){
            
            if( !busquedaArea.getText().equals(""))
            {
                query = busquedaArea.getText();
            }else
                query = null;
            
        }
        
        dialog.setVisible( false );
    }

}
