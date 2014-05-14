/*
 * Created on 17-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.resources.elementos.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;


/**
 * Dialog para ingresar un nuevo elemento al modelo.
 * 
 * @author Gabriel
 *
 */
public class IngresarResourceDialog implements ActionListener{

    private JTextField uriField;
    private JButton ingresarButton;
    private JButton cancelarButton;
    private JButton helpButton;
    private String resourceURI;
    private JDialog resourceDialog;
    private ResourceBundle labels;
    private ManejadorAyuda manejadorAyuda;
    private String elemento;
    
    /**
     * @param labels
     * @param manejadorAyuda
     * @param string
     */
    public IngresarResourceDialog(ResourceBundle labels, ManejadorAyuda manejadorAyuda, String string) {
        
        this.labels = labels;
        this.manejadorAyuda = manejadorAyuda;
        elemento = string;
        
    }

	/**
	 * Obtiene el URI del nuevo Resource.
	 * 
     * @return URI del nuevo recurso.
     */
    public String getResourceURI( JFrame frame ) {
        
        resourceDialog = new JDialog( frame, labels.getString("ingresarResourceLabel"), true ); 
        resourceDialog.setSize(300, 150 );
        resourceDialog.setResizable( false );
        resourceDialog.setLocationRelativeTo( frame );
        manejadorAyuda.setHelpKey( resourceDialog, "modelo_ingresar_resources_htm");
        
        uriField = new JTextField(20);
        uriField.addActionListener( this );
        ingresarButton = new JButton(labels.getString("Aceptar"));
        ingresarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString("Cancelar") );
        cancelarButton.addActionListener( this );
        helpButton = manejadorAyuda.getBotonAyuda( "semantic_web_conceptos_b_sicos_en_que_consiste_semantic_web_htm" );
        
        JPanel panel = new JPanel( new BorderLayout() );
        JPanel URIPanel = new JPanel();
        JLabel uriLabel = new JLabel(labels.getString("ingresarURI"));
        URIPanel.add(uriLabel);
        URIPanel.add( uriField );
        panel.add( URIPanel, BorderLayout.CENTER );
        JPanel botonesPanel = new JPanel();
        botonesPanel.add( ingresarButton );
        botonesPanel.add( cancelarButton );
        botonesPanel.add( helpButton );
        
        panel.add( botonesPanel, BorderLayout.SOUTH );
        
        resourceDialog.getContentPane().add( panel );
        
        resourceDialog.setVisible( true );
        resourceDialog = null;
        
        return resourceURI;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if ( arg0.getSource().equals(ingresarButton) || arg0.getSource().equals(uriField)){
            
            resourceURI = uriField.getText();
            resourceDialog.setVisible( false );
        }
        else if( arg0.getSource().equals(cancelarButton))
            resourceURI = null;
        	resourceDialog.setVisible( false );
        
    }
}
