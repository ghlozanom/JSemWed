/*
 * Created on 25-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingenierï¿½a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;

/**
 * Administra la visualización del modelo en los diferentes formatos disponibles.
 * Entre estos formatos estan RDF/XML, N3 y NTriples.
 * 
 * @author Gabriel
 *
 */
public class ManejadorVistas implements ItemListener{

    private ResourceBundle labels;
    private JMenu vistasMenu;
    /**
     * Archivo que contiene las vistas implementadas del modelo
     */
    private ResourceBundle vistas = 
        ResourceBundle.getBundle("co/edu/unal/jsemwed/ui/swing/manejadores/vistas/vistas");
    private IControlador controlador;
    private ManejadorAyuda manejadorAyuda;

    /**
     * @param labels
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;
        vistasMenu.setText( labels.getString( "vistasMenu") );
        
    }

    /**
     * Crea el menu Vistas de la interfazde usuario
     * 
     * @return menu Vistas.
     */
    public JMenu getMenuVistas() {
        
        vistasMenu = new JMenu( );
        vistasMenu.setEnabled( false );
        
        Enumeration visitasEnum = vistas.getKeys();
        while( visitasEnum.hasMoreElements() ){
            
            JCheckBox cb = new JCheckBox( vistas.getString((String) visitasEnum.nextElement()));
            cb.addItemListener( this );
            
            vistasMenu.add( cb );
        }
        
        return vistasMenu;
    }
    

    /**
     * @param controlador El controlador a configurar.
     */
    public void setControlador(IControlador controlador) {
        this.controlador = controlador;
    }

    /**
     * @param b
     */
    public void setVistasEnable(boolean b) {
        
        vistasMenu.setEnabled( b );
        
    }

    /**
     * Crea un componente con la vista del modelo en un formato seleccionado
     * para visualizarlo en la interfaz de usuario.
     * 
     * @param modelo el modelo serializado
     * @param formato formato del modelo serializado
     * @param manejadorVentana componente que permite visualizar el componente.
     * @return componente con la vista del modelo
     */
    public Component getVistaModelo(String modelo, String formato, ActionListener al) {
        
        JPanel modeloPanel = new JPanel( new BorderLayout() );
        JTextArea modeloTextArea = new JTextArea( modelo );
        JScrollPane sp = new JScrollPane( modeloTextArea );
        modeloPanel.setName( formato );
        
        JPanel botonesPanel = new JPanel();
        JButton cerrarButton = new JButton( labels.getString("cerrar") );
        JButton ayudaButton = manejadorAyuda.getBotonAyuda("semantic_web_representacion_de_modelos_rdf_xml_htm");
        botonesPanel.add( cerrarButton );
        cerrarButton.setName( formato );
        cerrarButton.addActionListener( al );
        botonesPanel.add( ayudaButton );
        
        modeloPanel.add( botonesPanel, BorderLayout.NORTH );
        modeloPanel.add( sp, BorderLayout.CENTER );
        
        return modeloPanel;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent arg0) {
        
        String formato = ((JCheckBox)arg0.getSource()).getText();
        controlador.adicionarVista( formato );
        
    }

    /**
     * @param manejadorAyuda
     */
    public void setManejadorAyuda(ManejadorAyuda manejadorAyuda) {
        
        this.manejadorAyuda = manejadorAyuda;
        
    }

}
