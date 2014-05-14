/*
 * Created on 14-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.modelo.qnames.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Gabriel
 *
 */
public class AdicionarQNameDialog implements ActionListener {

    private ResourceBundle labels;
    private Component ventanaPrincipal;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JDialog dialog;
    private String[] qName;
	private JTextField prefijoField;
	private JTextField uriField;

    /**
     * @param labels
     * @param ventanaPrincipal
     */
    public AdicionarQNameDialog(ResourceBundle labels, Component ventanaPrincipal) {
        
        this.labels = labels;
        this.ventanaPrincipal = ventanaPrincipal;
        
    }

    /**
     * @return
     */
    public String[] getQName() {
        
        dialog = new JDialog( (JFrame)ventanaPrincipal, labels.getString( 
                "ingresarQName"), true );
        dialog.setSize( 350, 100 );
        dialog.setLocationRelativeTo( ventanaPrincipal );
        dialog.setResizable( false );
        
        JPanel qnamesPanel = new JPanel();
        JLabel prefijo = new JLabel( "Prefijo" );
        prefijoField = new JTextField( 7 );
        JLabel uriLabel = new JLabel( "URI" );
        uriField = new JTextField( 15 );
        qnamesPanel.add( prefijo );
        qnamesPanel.add( prefijoField );
        qnamesPanel.add( uriLabel );
        qnamesPanel.add( uriField );
        
        JPanel botonesPanel = new JPanel();
        aceptarButton = new JButton( labels.getString("Aceptar"));
        aceptarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString("Cancelar"));
        cancelarButton.addActionListener( this );
        botonesPanel.add( aceptarButton );
        botonesPanel.add( cancelarButton );
        
        dialog.getContentPane().setLayout( new BorderLayout() );
        dialog.getContentPane().add( qnamesPanel, BorderLayout.CENTER );
        dialog.getContentPane().add( botonesPanel, BorderLayout.SOUTH );
        
        dialog.setVisible( true );
        
        return qName;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals( aceptarButton ) ){
        	
        	if( prefijoField.getText().equals("")  || uriField.getText().equals("") )
        	{
        		JOptionPane.showMessageDialog( dialog, labels.getString("datosIncompletos") );
        		return;
        	}
        	qName = new String[2];
        	qName[0] = prefijoField.getText();
        	qName[1] = uriField.getText();
        }
        
        dialog.setVisible( false );
            
        
    }

}
