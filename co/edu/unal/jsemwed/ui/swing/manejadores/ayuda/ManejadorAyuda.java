/*
 * Created on 17-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingenierï¿½a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.ayuda;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import co.edu.unal.jsemwed.aplicacion.App;

/**
 * Clase que sirve como intermediaria con el sistema de ayuda
 * 
 * @author Gabriel
 *
 */
public class ManejadorAyuda implements ActionListener{

    //Labels para la interfaz de usuario
    ResourceBundle labels;
    private JMenu ayudaMenu;
    private JMenuItem contenidoItem;
    private HelpSet hs;
    private HelpBroker hb;
    private JButton ayudaButton = null;
    private Vector botonesAyudaVector;
    
    /**
     * Configura el sistema de ayuda de la aplicacion
     *
     */
    public ManejadorAyuda(){
        
        String helpSet = "ayuda/ayuda.hs";
        ClassLoader cl = App.class.getClassLoader();
        
        URL url = HelpSet.findHelpSet( cl, helpSet );
        try {
            hs = new HelpSet( null, url );
        } catch (HelpSetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        hb = hs.createHelpBroker();
        
        botonesAyudaVector = new Vector();
    }
    
    
    /**
     * Configura los labels para internacionalizacion
     * 
     * @param labels
     */
    public void setLabels(ResourceBundle labels) {

        this.labels = labels;
        ayudaMenu.setText( labels.getString("ayuda") );
        contenidoItem.setText( labels.getString("contenido") );
        
        Iterator i = botonesAyudaVector.iterator();
        while( i.hasNext() ){
            
            JComponent c = (JComponent) i.next();
            if ( c != null )
            c.setToolTipText( labels.getString("ayuda") );
            
        }

        
    }

    /**
     * Obtiene un nuevo menu de ayuda
     *
     * @return
     */
    public JMenu getMenuAyuda() {
        
        ayudaMenu = new JMenu( );
        
        contenidoItem = new JMenuItem( new ImageIcon("images/Help16.gif"));
        contenidoItem.addActionListener( new CSH.DisplayHelpFromSource(hb) );
        
        ayudaMenu.add( contenidoItem );
        return ayudaMenu;
    }

    
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        Object obj = arg0.getSource();
        
        //Si se debe crear la ayuda
        Iterator i = botonesAyudaVector.iterator();
        while(  i.hasNext() ){
        	
        	Object obj2 = i.next();
	        if( obj.equals(contenidoItem) || obj.equals(obj2) )
	            new CSH.DisplayHelpFromSource(hb);
        
        }
        
    }


    /**
     * Devuelve botonos configurados para mostras la ayuda que se pide en el parametro
     * topico.
     * 
     * @param topico el topico de ayuda a asociar con el boton
     * @return boton con el topico de ayuda asociado
     */
    public JButton getBotonAyuda( String topico ) {
            
        ayudaButton = new JButton(new ImageIcon("images/Help16.gif"));
        setHelp( ayudaButton, topico );
        ayudaButton.addActionListener( new CSH.DisplayHelpFromSource(hb) );
        
        botonesAyudaVector.add( ayudaButton );
        
        return ayudaButton;
    }


    /**
     * Asocia un container con un tópico de ayuda.
     * 
     * @param container
     * @param string
     */
    public void setHelpKey(Container container, String string) {
        
        hb.enableHelpKey( container, string, hs );
        
    }


    /**
     * Asocia un component con un tópico de ayuda.
     * 
     * @param helpButton
     * @param string
     */
    public void setHelp(Component component, String string) {
        
        hb.enableHelp( component, string, hs );
        
    }


    /**
     * Revisar para eliminar
     * 
     * @return
     */
    public JButton getIngresarResourceHelp() {

        JButton ingresarResourceButton = new JButton("images/Help16.gif");
        setHelp( ingresarResourceButton, "acerca_de_jsemwed_htm");
        return ingresarResourceButton;
        
    }

}
