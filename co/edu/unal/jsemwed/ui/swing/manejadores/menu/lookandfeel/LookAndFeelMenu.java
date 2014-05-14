/*
 * Created on 10-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.menu.lookandfeel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import co.edu.unal.jsemwed.ui.swing.manejadores.IVentana;

/**
 * Clase encargada de manejar los Look and Feel de la aplicación
 * 
 * @author Gabriel
 *
 */
public class LookAndFeelMenu implements ActionListener{

    private JMenu lookAndFeelMenu;
    /**
     * Archivo de look and feel existentes.
     */
    private ResourceBundle resources =
        ResourceBundle.getBundle("co/edu/unal/jsemwed/ui/swing/manejadores/menu/lookandfeel/lookandfeel");
    
    private IVentana iVentana;
    private ResourceBundle labels;
    
    /**
     * Crea el menu Look and feel de la aplicación.
     * 
     * @param nombreMenu
     * @return Returns the lookAndFeelMenu.
     */
    public JMenu getMenu( ) {
        lookAndFeelMenu = new JMenu( );
        
        Enumeration resourcesEnum = resources.getKeys();
        while(resourcesEnum.hasMoreElements()){
            
            JMenuItem mi = new JMenuItem((String) resourcesEnum.nextElement());
            lookAndFeelMenu.add( mi );
            mi.addActionListener( this );
            
        }
        
        return lookAndFeelMenu;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        iVentana.setLookAndFeel( resources.getString(((JMenuItem)arg0.getSource()).getLabel()));
        
    }

    /**
     * @param ventana
     */
    public void setIVentana(IVentana ventana) {

        iVentana = ventana;
        
    }

    /**
     * Configura los labels para soportar internacionalización.
     * 
     * @param labels
     */
    public void setLabel(ResourceBundle labels) {

        this.labels = labels;
        lookAndFeelMenu.setLabel( labels.getString("Apariencia") );
        
    }
}
