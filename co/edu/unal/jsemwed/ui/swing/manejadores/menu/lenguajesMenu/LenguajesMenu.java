/*
 * Created on 10-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.menu.lenguajesMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import co.edu.unal.jsemwed.ui.swing.manejadores.IVentana;

/**
 * Clase encargada de administrar el menu Lenguajes de la aplicacion.
 * 
 * 
 * @author Gabriel
 *
 */
public class LenguajesMenu implements ActionListener{

    private JMenu lenguajesMenu;
    private IVentana iVentana;
    private ResourceBundle labels;
    
    /**
     * Archivo de lenguajes disponibles
     * 
     */
    private ResourceBundle lenguajes = 
        ResourceBundle.getBundle("co/edu/unal/jsemwed/ui/swing/manejadores/menu/lenguajesMenu/languages");
    /**
     * Crea el menu Lenguajes de la aplicacion.
     * 
     * @param label
     * @return Returns the lenguajesMenu.
     */
    public JMenu getLenguajesMenu( ) {
        
        lenguajesMenu = new JMenu( );
        
        Enumeration lenguajesEnum = lenguajes.getKeys();
        JMenuItem mi = null;
        
        while( lenguajesEnum.hasMoreElements() ){
            
            String key = (String) lenguajesEnum.nextElement();
            
            mi = new JMenuItem( key,
                    new ImageIcon("images/" + lenguajes.getString(key) + ".gif"));
            lenguajesMenu.add( mi );   
            mi.addActionListener(this);
        }
        
        return lenguajesMenu;
    }
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        JMenuItem mi = (JMenuItem) arg0.getSource();
        iVentana.setLenguaje( lenguajes.getString(mi.getLabel()));
        
    }
    /**
     * @param ventana El iVentana a configurar.
     */
    public void setIVentana(IVentana ventana) {
        iVentana = ventana;
    }
    /**
     * @param labels
     */
    public void setLabel(ResourceBundle labels) {

        this.labels = labels;
        lenguajesMenu.setLabel( labels.getString("lenguajesMenu") );
        
    }
}
