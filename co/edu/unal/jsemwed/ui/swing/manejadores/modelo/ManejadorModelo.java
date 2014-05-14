/*
 * Created on 19-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingenierï¿½a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.modelo;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;
import co.edu.unal.jsemwed.ui.swing.manejadores.modelo.dialogs.CrearModeloDialog;
import co.edu.unal.jsemwed.ui.swing.manejadores.modelo.qnames.ManejadorQNames;

/**
 * Clase encargada de administrar los modelos de la aplicacion.
 * 
 * @author Gabriel
 *
 */
public class ManejadorModelo implements ActionListener{
    
    private ResourceBundle labels;
    private ResourceBundle modelos = ResourceBundle.getBundle("co/edu/unal/jsemwed/plugins/" +
    		"modelos/modelos");    
    private JMenuItem crearModeloItem;    
    private JMenuItem abrirModeloItem;    
    private JMenuItem guardarModeloItem;    
    private JMenuItem salirItem;    
    private IControlador iControlador;    
    private JButton nuevoBoton;    
    private JButton abrirBoton;    
    private JButton guardarModeloBoton;    
    private JMenu modeloMenu;    
    private Component ventanaPrincipal;    
    private File archivo = null;    
    private ManejadorQNames manejadorQNames = new ManejadorQNames();
    private ManejadorAyuda manejadorAyuda;

    /**
     * Configura los labels para internacionalizacion de la app.
     * 
     * @param labels El labels a configurar.
     */
    public void setLabels(ResourceBundle labels) {
        this.labels = labels;
        modeloMenu.setText(labels.getString("Modelo"));
        crearModeloItem.setText(labels.getString("crearModeloMenuItem"));
        abrirModeloItem.setText( labels.getString("AbrirModelo") );
        guardarModeloItem.setText( labels.getString("GuardarModelo") );
        salirItem.setText( labels.getString("salirMenuItem") );
        
        nuevoBoton.setToolTipText( labels.getString("crearModeloMenuItem"));
        abrirBoton.setToolTipText( labels.getString("AbrirModelo") );
        guardarModeloBoton.setToolTipText( labels.getString("GuardarModelo"));
        
        if( manejadorQNames != null )
            manejadorQNames.setLabels( labels );
    }

    /**
     * Crea el menu Modelo de la aplicacion.
     * Este Menu crear modelos en la aplicacion.
     * 
     * @return Menu modelos.
     */
    public JMenu getModeloMenu() {

        //Crea el menu encargado de manejar el modelo
        modeloMenu = new JMenu( );
        
        crearModeloItem = new JMenuItem( );
        crearModeloItem.setIcon( new ImageIcon("images/New16.gif") );
        crearModeloItem.addActionListener( this );
        //Adicionar acelerador para el menuitem crearModelo
        crearModeloItem.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));   
        
        abrirModeloItem = new JMenuItem( );
        abrirModeloItem.setIcon( new ImageIcon("images/Open16.gif") );
        abrirModeloItem.addActionListener( this );
        abrirModeloItem.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK)); 
        
        guardarModeloItem = new JMenuItem(  );
        guardarModeloItem.setIcon( new ImageIcon("images/Save16.gif") );
        guardarModeloItem.setEnabled( false );
        guardarModeloItem.addActionListener( this );
        guardarModeloItem.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        
        salirItem = new JMenuItem( );
        salirItem.addActionListener(this);
        
        modeloMenu.add( crearModeloItem );
        modeloMenu.addSeparator();
        modeloMenu.add( abrirModeloItem );
        modeloMenu.add( guardarModeloItem );
        modeloMenu.addSeparator();
        modeloMenu.add( salirItem );
        
        
        return modeloMenu;
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        Object obj = arg0.getSource();
        
        if( obj.equals(crearModeloItem) || obj.equals(nuevoBoton) )
        	crearModelo();
            //iControlador.crearModelo();
        
        if( obj.equals(abrirModeloItem) || obj.equals(abrirBoton) ){
            //if( iControlador.crearModelo() )
                iControlador.abrirModelo();
        }
        
        if( obj.equals(guardarModeloBoton) || obj.equals(guardarModeloItem))
            iControlador.guardarModelo();
        
        else if( obj.equals( salirItem))
            System.exit( 0 );
        
    }

    
    
    /**
	 * Lee los modelos de un archivo y pregunta al usuario cual desea crear
	 */
	private void crearModelo() {
		
		CrearModeloDialog cmd = new CrearModeloDialog();
		String modelo = cmd.getModelo( labels, modelos, ventanaPrincipal,
		        manejadorAyuda );
		//System.out.println(modelo);
		if( modelo != null )
			iControlador.crearModelo( modelo );
		//El modelo ha sido creado exitosamente, revisar
		manejadorQNames.setVentanaPrincipal( ventanaPrincipal );
		manejadorQNames.setIControlador( this.iControlador );
	}

	/**
     * @param controlador El iControlador a configurar.
     */
    public void setIControlador(IControlador controlador) {
        iControlador = controlador;
        
    }

    /**
     * Administra la característica guardarModeloHabilitado
     * @param b
     */
    public void guardarModeloHabilitado(boolean b) {
        
        guardarModeloItem.setEnabled( b );
        guardarModeloBoton.setEnabled( b );
        
    }

    /**
     * Crea un boton para crear nuevos modelos.
     * 
     * @return boton nuevo modelo.
     */
    public JButton getNuevoModeloBoton() {
        
        nuevoBoton = new JButton(new ImageIcon("images/New16.gif"));
        nuevoBoton.setVerticalTextPosition(JButton.BOTTOM);
        nuevoBoton.setHorizontalTextPosition(JButton.CENTER);
        nuevoBoton.addActionListener( this );
        
        return nuevoBoton;
        
    }
    
    /**
     * Crear el boton para abrir un modelo desde un archivo.
     * 
     * @return boton abrir modelo.
     */
    public JButton getAbrirBoton() {
        
        abrirBoton = new JButton(new ImageIcon("images/Open16.gif"));
        abrirBoton.setVerticalTextPosition(JButton.BOTTOM);
        abrirBoton.setHorizontalTextPosition(JButton.CENTER);
        abrirBoton.addActionListener( this );
        
        return abrirBoton;
        
    }

    /**
     * Crea el boton que permite guardar un modelo.
     * 
     * @return boton guardar modelo.
     */
    public JButton getGuardarModeloBoton() {

        guardarModeloBoton = new JButton( new ImageIcon("images/Save16.gif") );
        guardarModeloBoton.setVerticalTextPosition(JButton.BOTTOM);
        guardarModeloBoton.setHorizontalTextPosition(JButton.CENTER);
	    guardarModeloBoton.setEnabled( false );
        guardarModeloBoton.addActionListener( this );
        
        return guardarModeloBoton;
        
    }

    /**
     * Obtiene un Stream asociado a un archivo en donde persista el modelo
     * sobre el que se esta trabajando.
     * 
     * @return Stream para guardar el modelo
     */
    public OutputStream getOutputStream() {
        
        if ( archivo == null ){
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileSelectionMode( 
	                JFileChooser.FILES_ONLY );
	        
	        int result = fileChooser.showSaveDialog( this.ventanaPrincipal );
	        
	        if( result == JFileChooser.CANCEL_OPTION )
	            return null;
	        
	         archivo = fileChooser.getSelectedFile();
	         
	         if( archivo.exists() ){
	             
	             int n = JOptionPane.showConfirmDialog( this.ventanaPrincipal,
	             	"Desea sobreescribir el archivo " + archivo.getName() );
	             
	             while( n == JOptionPane.NO_OPTION ){
	                 
	     	        result = fileChooser.showSaveDialog( this.ventanaPrincipal );
	    	        
	    	        if( result == JFileChooser.CANCEL_OPTION )
	    	            return null;
	    	        
	    	         archivo = fileChooser.getSelectedFile();
	    	         
	    	         if( archivo.exists() ){
	    	             
	    	             n = JOptionPane.showConfirmDialog( this.ventanaPrincipal,
	    	             	"Desea sobreescribir el archivo " + archivo.getName() );
	    	         }//fin if
	             }//fin while
	             if ( n == JOptionPane.CANCEL_OPTION ){
	                 archivo = null;
	                 return null;
	             }
	         }//fin if
	         
        }//fin if
        
        if( archivo == null ||
                archivo.getName().equals(""))
            JOptionPane.showMessageDialog( this.ventanaPrincipal,
                    "Nombre de Archivo Invalido", "Nombre de Archivo Invalido",
                    JOptionPane.ERROR_MESSAGE );
        
        else {
            
            try {
                OutputStream os = new FileOutputStream(archivo );
                return os;
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        return null;
    }

    /**
     * Crea un Stream de donde leer un modelo.
     * 
     * @return Stream del archivo con el modelo.
     */
    public InputStream getInputStream() {
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode( 
                JFileChooser.FILES_ONLY );
        
        int result = fileChooser.showOpenDialog( this.ventanaPrincipal );
        
        if( result == JFileChooser.CANCEL_OPTION )
            return null;
        
        archivo = fileChooser.getSelectedFile();
        try {
            return new FileInputStream(archivo);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @param component El ventanaPrincipal a configurar.
     */
    public void setVentanaPrincipal(Component component) {
        
        this.ventanaPrincipal = component;
        
    }

    /**
     * Configura la clase cuando se ha creado un modelo.
     * 
     */
    public void modeloCreado() {
        
        guardarModeloHabilitado( true );
        
        archivo = null;
        
    }

    /**
     * Indica al manejador modelo que el modelo fue eliminado y que
     * debe actualizar su estado.
     * 
     */
    public void modeloEliminado() {
        
        guardarModeloHabilitado( false );
        archivo = null;
        
    }

    /**
     * Pregunta al usuario si desea guardar un modelo
     * @return 0 - No; 1 - Si; 2 - Cancelar.
     */
    public int guardarModelo() {
        
        int n = JOptionPane.showConfirmDialog( this.ventanaPrincipal,
             	"Desea Guardar el Modelo ?" );
        if( n == JOptionPane.NO_OPTION )
            return 0;
        if( n == JOptionPane.OK_OPTION )
            return 1;
        return 2;
    }

	/**
	 * @param elementos
	 */
	public void adicioneElementos(String[] elementos) {
		// TODO Auto-generated method stub
		
	}

    /**
     * @param qnames
     */
    public void setPrefixes(Map qnames) {
        
        manejadorQNames.setPrefixes( qnames );
        
    }

    /**
     * @return
     */
    public Component getQNamesComponent() {
        
        return manejadorQNames.getQNamesComponent();
        
    }

    /**
     * 
     */
    public void modeloAbierto() {
        
        guardarModeloHabilitado( true );
        
    }

    /**
     * @param manejadorAyuda
     */
    public void setManejadorAyuda(ManejadorAyuda manejadorAyuda) {
        
        this.manejadorAyuda = manejadorAyuda;
        manejadorQNames.setManejadorAyuda( this.manejadorAyuda );
        
    }
}
