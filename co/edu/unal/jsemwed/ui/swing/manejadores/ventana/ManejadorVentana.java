/*
 * Created on 25-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.ventana;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.ui.swing.manejadores.vistas.ManejadorVistas;

/**
 * Permite administrar la ventana de interfaz de usuario.
 * 
 * @author Gabriel
 *
 */
public class ManejadorVentana implements ActionListener{

    private JMenu menuVentana;
    private ManejadorVistas manejadorVistas;
    private ResourceBundle labels;
    private JFrame ventanaPrincipal;
    private JTabbedPane modeloPane;
    private Vector vistasModelo;			//Guarda las vistas que se han creado del modelo
    private Component QNamesComp = null;
    private IControlador controlador;
    
    
    public ManejadorVentana(){
        
        ventanaPrincipal = new JFrame( );
        ventanaPrincipal.setSize( 800, 600 );
        ventanaPrincipal.setVisible( true );
        ventanaPrincipal.setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
        ventanaPrincipal.addWindowListener( new WindowAdapter(){
            public void windowClosing( WindowEvent e ){
                
                controlador.cerrarUI();
            }
        });
        
    }
    
    
    /**
     * Obtiene el menu para manejar la ventana
     * 
     * @return menu Ventana
     */
    public JMenu getMenuVentana() {
        
        menuVentana = new JMenu( );
        menuVentana.add( manejadorVistas.getMenuVistas() );
        
        return menuVentana;
    }

    /**
     * @param manejadorVistas El manejadorVistas a configurar.
     */
    public void setManejadorVistas(ManejadorVistas manejadorVistas) {
        this.manejadorVistas = manejadorVistas;
    }

    /**
     * @param labels
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;
        menuVentana.setText( labels.getString("Ventana") );
        
    }


    /**
     * @param menuBar
     */
    public void setMenuBar(JMenuBar menuBar) {
        
        ventanaPrincipal.setJMenuBar( menuBar );
        
    }


    /**
     * @param toolBar
     */
    public void setToolBar(JToolBar toolBar) {
        
        ventanaPrincipal.getContentPane().add(toolBar, BorderLayout.NORTH );
        
    }


    /**
     * Actualiza los cambios que sobre la ventana se hayan llevado a cabo.
     * 
     */
    public void actualizaCambios() {
        
        ventanaPrincipal.validate();
        ventanaPrincipal.repaint();
        
    }


    /**
     * @param string
     */
    public void setTitulo(String string) {
        
        ventanaPrincipal.setTitle( string );
        
    }


    /**
     * Adiciona el panel de elementos a la ventana de la app.
     * 
     * @param resourcesComponent
     */
    public void adicioneResources(Component resourcesComponent) {
        
        ventanaPrincipal.getContentPane().add( resourcesComponent,  BorderLayout.WEST);
        
    }


    /**
     * @return
     */
    public Component getVentana() {
        
        return ventanaPrincipal;
        
    }


    /**
     * @return
     */
    public Container getContainer() {
        
        return ventanaPrincipal.getContentPane();
    }


    /**
     * Remueve un componente de la ventana de la app.
     * 
     * @param resourcesComponent
     */
    public void remove(Component resourcesComponent) {
        
        getContainer().remove( resourcesComponent );
        
    }


    /**
     * Pinta la serialización del modelo en un formato seleccionado.
     * 
     * @param formato formato del modelo a pintar.
     * @param modeloComponent componente a pintar en la ventana de la app
     */
    public void pinteModelo(String formato, Component modeloComponent) {
        
        if( modeloPane == null ){
            
            modeloPane = new JTabbedPane();
            getContainer().add( modeloPane, BorderLayout.CENTER );
            
        }
        modeloPane.addTab( formato, modeloComponent );
        modeloPane.setSelectedComponent( modeloComponent );
        actualizaCambios();
        
        if( vistasModelo == null )
            vistasModelo = new Vector();
        vistasModelo.add( modeloComponent );
        
        
    }


    /**
     * Permite enseñar un mensaje de error en la aplicación.
     * 
     * @param string
     */
    public void mensajeError(String string) {
        
        JOptionPane.showMessageDialog( this.ventanaPrincipal, 
                string, "Error", JOptionPane.ERROR_MESSAGE );
        
    }


    /**
     * Elimina una de las vistas del modelo en formato serializado.
     * 
     */
    public void eliminarVistas() {
        
        if( vistasModelo == null )
            return;
        
        Iterator it = vistasModelo.iterator();
        
        while( it.hasNext() ){
            
            modeloPane.remove( (Component) it.next() );
            
        }
        
        getContainer().remove( this.modeloPane );
        modeloPane = null;
        
        actualizaCambios();
        
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        Component p = (Component) arg0.getSource();
        
        Iterator it = vistasModelo.iterator();
        
        Component vista = null;
        
        while( it.hasNext() ){
            
            vista = (Component) it.next();
            
            if( vista.getName().equals(p.getName() ) ){
                
                break;
                
            }
            
        }
        vistasModelo.remove( vista );
        modeloPane.remove( vista );
        
        if( vistasModelo.size() == 0 ){
            
            getContainer().remove( modeloPane );
            vistasModelo = null;
            modeloPane = null;
            actualizaCambios();
            
        }
        
    }


    /**
     * Adiciona el panel QNames a la ventana de la app.
     * 
     * @param comp
     */
    public void adicioneQNames(Component comp) {
        
       // ventanaPrincipal.getContentPane().add( comp,  BorderLayout.EAST );
        if( QNamesComp != null )
            ventanaPrincipal.getContentPane().remove( QNamesComp );
        
        QNamesComp = comp;
        ventanaPrincipal.getContentPane().add( QNamesComp,  BorderLayout.SOUTH );
        
    }


    /**
     * @param controlador
     */
    public void setControlador(IControlador controlador) {
        
        this.controlador = controlador;
        
    }


    /**
     * Termina la aplicación
     */
    public void cerrarUI() {
        
        System.exit( 0 );
        
    }
}
