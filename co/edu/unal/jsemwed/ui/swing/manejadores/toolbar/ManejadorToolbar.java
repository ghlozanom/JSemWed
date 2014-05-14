/*
 * Created on 13-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JToolBar;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;
import co.edu.unal.jsemwed.ui.swing.manejadores.edicion.ManejadorEdicion;
import co.edu.unal.jsemwed.ui.swing.manejadores.modelo.ManejadorModelo;

/**
 * Administra la caja de herramientas de la interfaz de usuario.
 * 
 * @author Gabriel
 *
 */
public class ManejadorToolbar implements ActionListener{

    
    private IControlador iControlador;
    
    private JButton nuevoBoton;
    
    private JButton abrirBoton;
    
    private JButton undoBoton;
    
    private JButton redoBoton;
    
    private ManejadorEdicion manejadorEdicion;
    
    private ManejadorModelo manejadorModelo;
    
    private JToolBar toolBar;
    
    private int posicion = 0; //indica la posicion para ingresar componentes al toolbar
    
    private Vector componentes = new Vector();
    
    
    /**
     * @param manejadorAyuda El manejadorAyuda a configurar.
     */
    public void setManejadorAyuda(ManejadorAyuda manejadorAyuda) {
        this.manejadorAyuda = manejadorAyuda;
    }
    private ManejadorAyuda manejadorAyuda;
    
    /**
     * Crea un nuevo toolbar para la app.
     * 
     * @return toolbar
     */
    public JToolBar getToolBar() {

        toolBar = new JToolBar();
        
        nuevoBoton = manejadorModelo.getNuevoModeloBoton();
        
        abrirBoton = manejadorModelo.getAbrirBoton();
        
        JButton guardarModeloBoton = manejadorModelo.getGuardarModeloBoton();
       
        undoBoton = manejadorEdicion.getUndoBoton();
        
        redoBoton = manejadorEdicion.getRedoBoton();
        
        addToolBarItem( nuevoBoton );
        addToolBarItem( abrirBoton );
        addToolBarItem( guardarModeloBoton );
        toolBar.addSeparator();
        addToolBarItem( undoBoton );
        addToolBarItem( redoBoton );
        toolBar.addSeparator();
        
        toolBar.add( manejadorAyuda.getBotonAyuda("acerca_de_jsemwed_htm"));
        
        return toolBar;
    }
    



    /**
     * @param controlador
     */
    public void setIControlador(IControlador controlador) {
        
        this.iControlador = controlador;
        
    }



    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {

        if( arg0.getSource().equals(nuevoBoton))
            iControlador.crearModelo();
        
    }



    /**
     * @param b
     */
    public void setUndoable(boolean b) {
        
        undoBoton.setEnabled( b );
        
    }



    /**
     * @param b
     */
    public void setRedoable(boolean b) {

        redoBoton.setEnabled( b );
        
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
     * Añade un nuevo item al toolbar
     * 
     * @param comp componente a añadir al toolbar
     */
    public void addToolBarItem(Component comp) {
        
        toolBar.add( comp, posicion );
        posicion++;
        componentes.add( comp );
        
    }

    /**
     * Elimina un item al toolbar.
     * 
     * @param panel componente a eliminar del toolbar
     */
    public void removeItem(Component panel) {
        
        Iterator it = componentes.iterator();
        while( it.hasNext() ){
            
            Component comp = (Component)it.next();
            if( panel.equals(comp))
                toolBar.remove( panel );
            
        }
        
    }
}
