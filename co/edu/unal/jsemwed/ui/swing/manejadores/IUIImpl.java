/*
 * Created on 07-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores;

import java.awt.Component;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.comun.IUI;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;
import co.edu.unal.jsemwed.ui.swing.manejadores.busquedas.ManejadorBusquedas;
import co.edu.unal.jsemwed.ui.swing.manejadores.edicion.ManejadorEdicion;
import co.edu.unal.jsemwed.ui.swing.manejadores.menu.ManejadorMenu;
import co.edu.unal.jsemwed.ui.swing.manejadores.modelo.ManejadorModelo;
import co.edu.unal.jsemwed.ui.swing.manejadores.resources.ManejadorResources;
import co.edu.unal.jsemwed.ui.swing.manejadores.toolbar.ManejadorToolbar;
import co.edu.unal.jsemwed.ui.swing.manejadores.ventana.ManejadorVentana;
import co.edu.unal.jsemwed.ui.swing.manejadores.vistas.ManejadorVistas;

/**
 * @author Gabriel
 *
 */
public class IUIImpl implements IUI, IVentana{

    //private JFrame ventanaPrincipal;
    
    //Archivo de configuracion de la interfaz
    private ResourceBundle config = ResourceBundle.getBundle("co/edu/unal/jsemwed/ui/swing/manejadores/config");
    
    
    //Archivo con los labels de la aplicacion
    private ResourceBundle labels;
    
    //Manejador del Menu
    private ManejadorMenu manejadorMenu;
    
    private JMenuBar menuBar = null;
    
    private JToolBar toolBar = null;
    
    private ManejadorModelo manejadorModelo;
    
    private ManejadorToolbar manejadorToolbar;
    
    private ManejadorResources manejadorResources;
    
    private ManejadorAyuda manejadorAyuda;
    
    private ManejadorEdicion manejadorEdicion;
    
    //private Container container;
    
    //El controlador que maneja todas las acciones de la aplicacion
    private IControlador iControlador;
    
    //private Component resourcesComponent;
    private Component resourcesComponent;

    private ManejadorVentana manejadorVentana;
    
    private ManejadorVistas manejadorVistas;
    
    private ManejadorBusquedas manejadorBusquedas;
    
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#setNombreModelo(java.lang.String)
     */
    
    public IUIImpl(){
        
        String language = config.getString("lenguaje");
        
        //SetLabels inicia toda la interfaz de usuario
        labels = ResourceBundle.getBundle( "co/edu/unal/jsemwed/ui/swing/labels/labels_" + language );
        
        //ventanaPrincipal = new JFrame();
        //container = ventanaPrincipal.getContentPane();
        
        manejadorMenu = new ManejadorMenu( );
        manejadorMenu.setIVentana( this );      
        manejadorToolbar = new ManejadorToolbar();
        
        manejadorEdicion = new ManejadorEdicion();
        manejadorModelo = new ManejadorModelo();
        manejadorResources = new ManejadorResources();
        manejadorAyuda = new ManejadorAyuda();
        manejadorVentana = new ManejadorVentana();
        manejadorVistas = new ManejadorVistas();
        manejadorBusquedas = new ManejadorBusquedas();
        
        manejadorModelo.setVentanaPrincipal( 
                manejadorVentana.getVentana() );
        manejadorModelo.setManejadorAyuda( manejadorAyuda );
        
        manejadorResources.setVentanaPrincipal( 
                manejadorVentana.getVentana() );
        manejadorResources.setManejadorVentana( manejadorVentana );
        manejadorResources.setManejadorAyuda( manejadorAyuda );
        
        manejadorMenu.setManejadorResources( manejadorResources );
        manejadorMenu.setManejadorEdicion( manejadorEdicion );     
        manejadorMenu.setManejadorAyuda( manejadorAyuda );
        manejadorMenu.setManejadorModelo( manejadorModelo );
        manejadorMenu.setManejadorVentana( manejadorVentana );
        
        manejadorVistas.setManejadorAyuda( manejadorAyuda );
        
        manejadorToolbar.setManejadorAyuda( manejadorAyuda );
        manejadorToolbar.setManejadorEdicion( manejadorEdicion );
        manejadorToolbar.setManejadorModelo( manejadorModelo );
        
        manejadorVentana.setManejadorVistas( manejadorVistas );
        
        manejadorBusquedas.setVentanaPrincipal( 
                manejadorVentana.getVentana() );
        manejadorBusquedas.setManejadorToolbar( manejadorToolbar );
        
        
        manejadorAyuda.setHelpKey( 
                manejadorVentana.getContainer(), "acerca_de_jsemwed_htm" );
        
        iniciar();
        setLabels( labels );
        
        
        /**
        ventanaPrincipal.setSize( 800, 600 );
        ventanaPrincipal.setVisible( true );
        ventanaPrincipal.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        **/
        
    }
   

    
    /**
     * 
     */
    private void iniciar() {
        
        
        menuBar = manejadorMenu.getMenu();
        manejadorVentana.setMenuBar( menuBar );
        //ventanaPrincipal.setJMenuBar( menuBar );
        
        
        //A�ade el toolbar
        toolBar = manejadorToolbar.getToolBar();
        manejadorVentana.setToolBar( toolBar );
        //container.add(toolBar, BorderLayout.NORTH );
        
        //ventanaPrincipal.validate();
        manejadorVentana.actualizaCambios();
        
    }



    /**
     * 
     */
    private void setLabels( ResourceBundle labels ) {
        
        this.labels = labels;
        
        //ventanaPrincipal.setTitle(labels.getString("nombreVentana"));
        manejadorVentana.setTitulo(labels.getString("nombreVentana"));
        
        manejadorMenu.setLabels( labels );
        
        manejadorModelo.setLabels( labels );
        
        manejadorEdicion.setLabels( labels );
        
        manejadorResources.setLabels( labels );
        
        manejadorAyuda.setLabels( labels );
        
        manejadorVistas.setLabels( labels );
        
        manejadorVentana.setLabels( labels );
        
        manejadorBusquedas.setLabels( labels );
        

        
        //A�ade el menuBar

        
    }

    
    public void setNombreModelo(String nombreModelo) {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#guardarModelo()
     */
    public int guardarModelo() {
        
        return manejadorModelo.guardarModelo();
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#setDeshacer(boolean)
     */
    public void setDeshacer(boolean b) {
        
        manejadorEdicion.setDeshacer( b );
        
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#deshabilitarUndo()
     */
    public void deshabilitarUndo() {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#habilitarRedo()
     */
    public void habilitarRedo() {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#deshabilitarRedo()
     */
    public void deshabilitarRedo() {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#habilitarUndo()
     */
    public void habilitarUndo() {
        
        //
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#setRehacer(boolean)
     */
    public void setRehacer(boolean b) {
        
        manejadorEdicion.setRehacer( b );
        
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#guardarModeloHabilitado(boolean)
     */
    public void guardarModeloHabilitado(boolean b) {
        
        manejadorModelo.guardarModeloHabilitado( b );
        
    }
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#pinteModelo(java.lang.String, java.lang.String)
     */
    public void pinteModelo(String modelo, String formato) {
        
        Component modeloComponent = manejadorVistas.getVistaModelo( modelo, formato
                ,manejadorVentana );
        manejadorVentana.pinteModelo( formato, modeloComponent );
        System.out.println( modelo );
        
    }



	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IUI#pinteResources(java.lang.String[])
	 */
	public void pinteResources(Vector[] res) {
	
	    manejadorResources.pinteResources( res );
        manejadorBusquedas.setElementos( 
                manejadorResources.getElementos() );
	    //ventanaPrincipal.validate();
	    manejadorVentana.actualizaCambios();
	    
	}
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#setControlador(co.edu.unal.jsemwed.comun.IControlador)
     */
    public void setControlador(IControlador controlador) {
        
        this.iControlador = controlador;
        
        manejadorToolbar.setIControlador( controlador );
        
        manejadorResources.setIControlador( controlador );
        
        manejadorModelo.setIControlador( controlador );
        
        manejadorEdicion.setIControlador( controlador );
        
        manejadorVistas.setControlador( controlador );
        
        manejadorBusquedas.setControlador( controlador );
        
        manejadorVentana.setControlador( controlador );
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.ui.swing.manejadores.IVentana#setLenguaje(java.lang.String)
     */
    public void setLenguaje(String lenguaje) {
        
        labels = ResourceBundle.getBundle( "co/edu/unal/jsemwed/ui/swing/labels/labels_" + lenguaje );
        setLabels( labels );
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.ui.swing.manejadores.IVentana#setLookAndFeel(java.lang.String)
     */
    public void setLookAndFeel(String lookAndFeel) {
        
        try {
            if( lookAndFeel.equals("system") )
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            else
                UIManager.setLookAndFeel(lookAndFeel);
            
            SwingUtilities.updateComponentTreeUI( manejadorVentana.getVentana() );
            
            manejadorVentana.actualizaCambios();
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#getOutputStream()
     */
    public OutputStream getOutputStream() {
        
        return manejadorModelo.getOutputStream();
        
    }



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#getInputStream()
     */
    public InputStream getInputStream() {
        
        return manejadorModelo.getInputStream();
    }



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#cargeResources(java.util.Vector)
     */
    public void cargeResources(Vector resources) {
        
        manejadorResources.setResources( resources );
        
    }



	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IUI#modeloCreado()
	 */
	public void modeloCreado() {
	
	    //TODO actualizar esto
	    
	    modeloAbierto();
	    manejadorModelo.modeloCreado();
	    
	}



	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IUI#modeloGuardado()
	 */
	public void modeloGuardado() {
	    
	    manejadorModelo.guardarModeloHabilitado( false );
	    manejadorEdicion.setDeshacer( false );
	    manejadorEdicion.setRehacer( false );
	    
	}



	/* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#mensajeError(java.lang.String)
     */
    public void mensajeError(String string) {
        
        manejadorVentana.mensajeError( string );
        
    }



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#modeloEliminado()
     */
    public void modeloEliminado() {
        
        manejadorEdicion.setDeshacer( false );
        manejadorEdicion.setRehacer( false );
        
        manejadorToolbar.setRedoable( false );
        manejadorToolbar.setUndoable( false );
        
        manejadorMenu.modeloEliminado();
        
        manejadorModelo.modeloEliminado();
        
       if( resourcesComponent != null )
        	manejadorVentana.remove( resourcesComponent );
        
        manejadorVistas.setVistasEnable( false );
        
        manejadorVentana.actualizaCambios();
    }



	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IUI#adicioneElementos(java.lang.String[])
	 */
	public void adicioneElementos(String[] elementos) {
		
		/**if( resourcesComponent != null )
			manejadorVentana.remove( resourcesComponent );**/
		//resourcesComponent = null;
	    resourcesComponent = manejadorResources.getElementosPanel(elementos);
	    
	    manejadorVentana.adicioneResources( resourcesComponent );	    
	    manejadorVentana.actualizaCambios();
	    
		manejadorModelo.adicioneElementos( elementos );
		
	}



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#adicioneBusquedas(java.lang.String[])
     */
    public void adicioneBusquedas(String[] busquedas) {
        
        manejadorBusquedas.setBusquedas( busquedas );
        Component comp = manejadorBusquedas.getBusquedasComponent();
        manejadorToolbar.addToolBarItem( comp );
        
    }



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#pinteResultadoBusqueda(java.lang.String)
     */
    public void pinteResultadoBusqueda(Vector resultado) {
        
        Component component = manejadorBusquedas.getResultadoComponent( resultado,
                manejadorVentana );
        manejadorVentana.pinteModelo( "busqueda", component );
        
        
    }



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#adicionePrefixes(java.util.Map)
     */
    public void adicionePrefixes(Map qnames) {
        
        manejadorModelo.setPrefixes ( qnames );
        Component comp = manejadorModelo.getQNamesComponent();
        manejadorVentana.adicioneQNames( comp );
        manejadorVentana.actualizaCambios();
        
    }



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#cerrarUI()
     */
    public void cerrarUI() {
        
        manejadorVentana.cerrarUI();
        
    }



    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IUI#modeloAbierto()
     */
    public void modeloAbierto() {
        
	    manejadorEdicion.setDeshacer(false);
	    manejadorEdicion.setRehacer (false);
	    
	    manejadorToolbar.setUndoable( false );
	    manejadorToolbar.setRedoable( false );
	    manejadorBusquedas.removerBusquedas();
	    
	    manejadorMenu.modeloCreado();
	    
	    manejadorModelo.modeloAbierto();
	    
	    if( resourcesComponent != null )
	    	manejadorVentana.remove( resourcesComponent );
	    	
	    manejadorResources.modeloCreado();
	    //resourcesComponent = manejadorResources.getElementosPanel( null );
	    
	    //manejadorVentana.adicioneResources( resourcesComponent );
	    
	    manejadorVistas.setVistasEnable( true );
	    
	    manejadorVentana.eliminarVistas();
	    
	    manejadorVentana.actualizaCambios();
        
    }


    
}

