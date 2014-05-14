/*
 * Created on 08-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingenierï¿½a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.menu;

import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import co.edu.unal.jsemwed.ui.swing.manejadores.IVentana;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;
import co.edu.unal.jsemwed.ui.swing.manejadores.edicion.ManejadorEdicion;
import co.edu.unal.jsemwed.ui.swing.manejadores.menu.lenguajesMenu.LenguajesMenu;
import co.edu.unal.jsemwed.ui.swing.manejadores.menu.lookandfeel.LookAndFeelMenu;
import co.edu.unal.jsemwed.ui.swing.manejadores.modelo.ManejadorModelo;
import co.edu.unal.jsemwed.ui.swing.manejadores.resources.ManejadorResources;
import co.edu.unal.jsemwed.ui.swing.manejadores.ventana.ManejadorVentana;

/**
 * Administra los menu de la aplicacion.
 * 
 * @author Gabriel
 *
 */
public class ManejadorMenu{
    
    //Archivo de labels en co/edu/unal/jsemwed/ui/swing/labels/labels_*.properties
    private ResourceBundle labels;
    
    //Menu de la ventana
    private JMenu configuracionMenu;
    
    //El que contiene todos los menu
    private JMenuBar menuBar;
    
    //Interfaz para interactuar con la ventana
    private IVentana iVentana;
    
    //Menu de lenguajes
    private LenguajesMenu lenguajesMenu;
    
    //Menu lookandfeel
    private LookAndFeelMenu lookAndFeelMenu;
    
    private ManejadorResources manejadorResources;
    
    private ManejadorAyuda manejadorAyuda;
    
    private ManejadorModelo manejadorModelo;
    
    private JMenu resources;
    
    private ManejadorEdicion manejadorEdicion;
    
    private ManejadorVentana manejadorVentana;

    public ManejadorMenu(){
        
        lenguajesMenu = new LenguajesMenu( );
        lookAndFeelMenu = new LookAndFeelMenu();
        
    }
    
    /**
     * Configura los labels del componente para internacionalizacion.
     * 
     * @param labels
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;
        configuracionMenu.setText( labels.getString("configuracionMenu") );
        lookAndFeelMenu.setLabel( labels );
        lenguajesMenu.setLabel( labels );
        manejadorEdicion.setLabels( labels );
        
    }
    /**
     * Crea el menu de la aplicacion.
     * 
     * @return menu dela aplicación.
     */
    public JMenuBar getMenu() {
                
        //Crear Menu de Modelo
        JMenu modeloMenu = manejadorModelo.getModeloMenu( );
        
        //Crear Menu edicion
        JMenu edicionMenu = manejadorEdicion.getMenu( );
        
        //Crear Menu Resources
        resources = manejadorResources.getResourcesMenu( );
        resources.setEnabled( false );
        
        //Crear el nuevo menu
        //TODO arreglar este menu
        configuracionMenu = new JMenu( );
        
        
	        //SubMenu con los lenguajes
	        JMenu lenguajesMenu = this.lenguajesMenu.getLenguajesMenu();
	        configuracionMenu.add(lenguajesMenu );
        
	        //SubMenu con los look and feel
	        JMenu lookAndFeelMenu = this.lookAndFeelMenu.getMenu( );
	        configuracionMenu.add( lookAndFeelMenu );
        
	    JMenu ventanaMenu = manejadorVentana.getMenuVentana();
	        
	    //Crear el menu de ayuda
        JMenu menuAyuda = manejadorAyuda.getMenuAyuda();
	        
	    //Adicionar todos los menu al menuBar
        menuBar = new JMenuBar();
        menuBar.add( modeloMenu );
        menuBar.add( edicionMenu );
        menuBar.add( resources );
        menuBar.add( configuracionMenu );
        menuBar.add( ventanaMenu );
        menuBar.add( menuAyuda );
        
        return menuBar;
    }


    /**
     * Configura el componente que le permite interactuar a esta clase con la ventana
     * de usuario.
     * 
     * @param ventana El iVentana a configurar.
     */
    public void setIVentana(IVentana ventana) {
        
        iVentana = ventana;
        lenguajesMenu.setIVentana( ventana );
        lookAndFeelMenu.setIVentana( ventana );
    }


    /**
     * Indica a la clase que un nuevo modelo fue creado.
     * 
     */
    public void modeloCreado() {
        
        resources.setEnabled( true );
        
    }
    /**
     * @param manejadorAyuda El manejadorAyuda a configurar.
     */
    public void setManejadorAyuda(ManejadorAyuda manejadorAyuda) {
        this.manejadorAyuda = manejadorAyuda;
    }
    /**
     * @param manejadorResources El manejadorResources a configurar.
     */
    public void setManejadorResources(ManejadorResources manejadorResources) {
        this.manejadorResources = manejadorResources;
    }
    /**
     * @param manejadorEdicion El manejadorEdicion a configurar.
     */
    public void setManejadorEdicion(ManejadorEdicion manejadorEdicion) {
        this.manejadorEdicion = manejadorEdicion;
    }
    /**
     * @param manejadorModelo El manejadorModelo a configurar.
     */
    public void setManejadorModelo(ManejadorModelo manejadorModelo) {
        this.manejadorModelo = manejadorModelo;
    }

    /**
     * @param manejadorVentana El manejadorVentana a configurar.
     */
    public void setManejadorVentana(ManejadorVentana manejadorVentana) {
        this.manejadorVentana = manejadorVentana;
    }

    /**
     * Indica al manejador menu que el modelo fue eliminado
     * y que debe actualizar su estado.
     * 
     */
    public void modeloEliminado() {
        
        resources.setEnabled( false );
        
    }
}
