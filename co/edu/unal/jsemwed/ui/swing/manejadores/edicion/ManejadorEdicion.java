/*
 * Created on 18-ene-2005
 *
 * Gabriel H. Lozano M.
 * IngenierÃ­a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.edicion;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import co.edu.unal.jsemwed.comun.IControlador;

/**
 * Maneja la funcionalidad de edicion de la interfaz de usuario de 
 * la aplicacion.
 * 
 * @author Gabriel
 *
 */
public class ManejadorEdicion implements ActionListener{
    
    private ResourceBundle labels;
    
    private JMenuItem deshacer;
    
    private JMenuItem rehacer;
    
    private JButton undoBoton;
    
    private JButton redoBoton;
    
    private IControlador iControlador;
    
    private Clipboard clipboard;
    
    private JFrame ventanaPrincipal;
    
    private JMenu edicionMenu;

    /**
     * Configura los labels para internacionalización.
     * 
     * @param labels El labels a configurar.
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;
        edicionMenu.setText( labels.getString("Edicion") );
        deshacer.setText( labels.getString("Deshacer") );
        rehacer.setText( labels.getString("Rehacer") );
        
        undoBoton.setToolTipText( labels.getString("Deshacer"));
        
        redoBoton.setToolTipText( labels.getString("Rehacer"));
    }

    /**
     * Obtinene una instancia del menu Edicion
     * 
     * @return
     */
    public JMenu getMenu() {
        
        edicionMenu = new JMenu( );
        
        deshacer = new JMenuItem( new ImageIcon("images/Undo16.gif"));
        deshacer.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Z,
                ActionEvent.CTRL_MASK));
        deshacer.addActionListener( this );
        deshacer.setEnabled( false );
        
        rehacer = new JMenuItem( new ImageIcon("images/Redo16.gif"));
        rehacer.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Y,
                ActionEvent.CTRL_MASK));
        rehacer.addActionListener( this );
        rehacer.setEnabled( false );
        
        edicionMenu.add( deshacer );
        edicionMenu.add( rehacer );
        
        
        
        return edicionMenu;
        
    }

    /**
     * Obtiene una instancia del boton deshacer
     * 
     * @return boton deshacer de la aplicación
     */
    public JButton getUndoBoton() {
        
        undoBoton = new JButton( new ImageIcon("images/Undo16.gif"));
        undoBoton.setVerticalTextPosition(JButton.BOTTOM);
        undoBoton.setHorizontalTextPosition(JButton.CENTER);
        undoBoton.setEnabled( false );
        undoBoton.addActionListener( this );
        
        return undoBoton;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        Object obj = arg0.getSource();
        
        if( obj.equals(undoBoton) || obj.equals(deshacer) )
            iControlador.undo();
        
        if( obj.equals( redoBoton) || obj.equals( rehacer) )
        	iControlador.redo();
        
    }

    /**
     * Obtiene una instancia del boton rehacer de la aplicación
     * 
     * @return Boton rehacer de la aplicación.
     */
    public JButton getRedoBoton() {
        
        redoBoton = new JButton( new ImageIcon("images/Redo16.gif"));
        redoBoton.setVerticalTextPosition(JButton.BOTTOM);
        redoBoton.setHorizontalTextPosition(JButton.CENTER);
        redoBoton.setEnabled( false );
        redoBoton.addActionListener( this );
        
        return redoBoton;
    }

    /**
     * @param b
     */
    public void setDeshacer(boolean b) {
        
        deshacer.setEnabled( b );
        undoBoton.setEnabled( b );
        
    }

    /**
     * @param b
     */
    public void setRehacer(boolean b) {

        rehacer.setEnabled( b );
        redoBoton.setEnabled( b );
        
    }

    /**
     * @param controlador El iControlador a configurar.
     */
    public void setIControlador(IControlador controlador) {
        iControlador = controlador;
    }
    /**
     * @param ventanaPrincipal El ventanaPrincipal a configurar.
     */
    public void setVentanaPrincipal(JFrame ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        
        clipboard = ventanaPrincipal.getToolkit().getSystemClipboard();
        
        StringSelection ss = new StringSelection("prueba");
        
    }
}
