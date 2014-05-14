/*
 * Created on 20-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.CasosDeUso;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import co.edu.unal.jsemwed.comun.IModelo;
import co.edu.unal.jsemwed.comun.IUI;
import co.edu.unal.jsemwed.swEngine.ISWEngine;

/**
 * Clase encargada de los casos de uso relacionados con el modelo
 * 
 * @author Gabriel
 *
 */
public class ModeloHandler {

	private ISWEngine swEngine;
	private IUI ui;
	private OutputStream os = null;
	/**
	 * Archivo con los modelos disponibles
	 */
	private ResourceBundle modelos = 
		ResourceBundle.getBundle("co/edu/unal/jsemwed/plugins/modelos/modelos");
	
	/**
	 * Archivo con los elementos disponibles.
	 */
	private ResourceBundle elementos =
		ResourceBundle.getBundle("co/edu/unal/jsemwed/plugins/elementos/elementos" );
	private IModelo modelo;


    /**
     * @param swEngine El swEngine a configurar.
     */
    public void setSwEngine(ISWEngine swEngine) {
        this.swEngine = swEngine;
    }
    /**
     * @param ui El ui a configurar.
     */
    public void setUi(IUI ui) {
        this.ui = ui;
    }
	/**
	 * 
	 */
	public void crearModelo() {

		swEngine.crearModelo();
		ui.modeloCreado( );
		
	}
    /**
     * Creacion de un modelo a partir de un formato determinado.
     * 
     * @param modelo formato del modelo a crear
     */
    public void crearModelo(String modelo) {
    	
    	try {
    		
    		this.modelo = (IModelo) Class.forName( modelos.getString(modelo)).newInstance();
    		String[] elementos = this.modelo.getElementosPosibles();
    		String[] busquedas = this.modelo.getBusquedas();
    		swEngine.crearModelo( this.modelo.getLenguaje() );
    		Map qnames = swEngine.getPrefixes();
    		ui.modeloCreado( );
    		ui.adicioneElementos( elementos );
    		ui.adicioneBusquedas( busquedas );
    		ui.adicionePrefixes( qnames );
    		
    		
    	} catch (InstantiationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IllegalAccessException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	
    	
    }
	/**
	 * Guarda un modelo sobre el que se este trabajando.
	 * 
	 */
	public boolean guardarModelo() {
		
	    os = ui.getOutputStream();
	    
	    if( os == null )
	        return false;
	    
	    //Guarda el modelo y debe enviar el baseURI
	    String modeloSerializado = swEngine.getModelo( null );
	    this.modelo.setModeloSerializado( modeloSerializado );
	    try {
			ObjectOutputStream oo = 
				new ObjectOutputStream( os );
			oo.writeObject( modelo );
			
			//swEngine.guardarModelo( os );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try {
	        os.flush();
            os.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    //ui.modeloGuardado();
		
	    return true;
	}
    /**
     * Abre un modelo sobre el que se trabajo anteriormente.
     * 
     * @return
     */
    public boolean abrirModelo() {
        
        ObjectInputStream is = null;
		try {
		    InputStream is1 = ui.getInputStream();
		    if( is1 == null ) return false;
			is = new ObjectInputStream( is1 );
			modelo = (IModelo)is.readObject();
			is.close();
			String[] elementos = this.modelo.getElementosPosibles();
			String[] busquedas = this.modelo.getBusquedas();
			ui.modeloAbierto( );
			ui.adicioneElementos( elementos );
			ui.pinteResources( modelo.getElementos() );
			ui.adicioneBusquedas( busquedas );
			
			String modeloSerializado = modelo.getModeloSerializado();
			System.out.println( modeloSerializado );
			swEngine.crearModelo( this.modelo.getLenguaje() );
			swEngine.setModelo( modeloSerializado );
			Map qnames = swEngine.getPrefixes();
			ui.adicionePrefixes( qnames );
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;/**
		if( is == null )
            return false;
        
        if (swEngine.abrirModelo( is ))
        {
	        try {
	            is.close();
	            //Luego de crear el modelo debe cargar los resources en el
	            Vector resources = swEngine.cargeResources();
	            ui.cargeResources( resources );
	            Object[] res = swEngine.getResources();
	            //ui.pinteResources( res );
	            return true;
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
        }
        else{
            ui.mensajeError("Archivo no Valido.\n" +
            		"Intentelo de Nuevo" );
        }
        return false;**/
    }
	/**
	 * Ingresa un nuevo Resource en la aplicacion.
	 * 
	 * @param modelo2 tipo de elemento a ingresar
	 * @param uri uri del elemento a ingresar.
	 * @throws Exception
	 */
	public void ingreseResource(String modelo2, String uri) throws Exception {
		
		try {
			
			String tipo = modelo.ingresarElemento( elementos.getString(modelo2), uri );
			swEngine.ingreseResource( tipo, uri );
			ui.pinteResources( modelo.getElementos() );
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Elimina un resource de la aplicacion.
	 * 
	 * @param tipoElemento tipo del elemento a eliminar
	 * @param URI URI del elemento a eliminar
	 */
	public void eliminarResource(String tipoElemento, String urlNodoSelecto ) {
		
		String tipo = modelo.eliminarElemento( tipoElemento, urlNodoSelecto );
		swEngine.elimineElemento( tipo, urlNodoSelecto );
		ui.pinteResources( modelo.getElementos() );
		
	}
	/**
	 * Ingresa un statement sobre el modelo que se esta trabajando.
	 * 
	 * @param statement statement a ingresar
	 */
	public void ingresarStatement(String[] statement) {
		
		modelo.ingresarStatement( statement );
		swEngine.ingresarStatement( statement[0], statement[1], statement[2] );
		ui.pinteResources( modelo.getElementos() );
		
	}
	/**
	 * Elimina un statement sobre el modelo que se esta trabajando.
	 * 
	 * @param statement statement a eliminar.
	 */
	public void eliminarStatement(String[] statement) {
		
		modelo.eliminarStatement( statement );
		swEngine.elimineStatement( statement );
		ui.pinteResources( modelo.getElementos() );
		
	}
	/**
	 * Adiciona una operacion a un recurso seleccionado.
	 * 
	 * @param uri URI del recurso al que se le adiciona la operacion 
	 * @param op operacion a adicionar al recurso.
	 */
	public void adicionarOperacion(String uri, String op) {
		
		modelo.adicionarOperacion( uri, op );
		
	}
    /**
     * Ejecucion de una busqueda en la aplicacion
     * 
     * @param query
     */
    public void realizarBusqueda(String query) {
        
        Vector resultado = swEngine.realizarBusqueda( query );
        ui.pinteResultadoBusqueda( resultado );
        
    }
	/**
	 * Elimina una operacion sobre un elemento seleccionado.
	 * 
	 * @param string tipo del elemento
	 * @param string2 URI del elemento al que se le elimina la operacion
	 */
	public void eliminarOperacion(String string, String string2) {
		
		modelo.eliminarOperacion( string, string2 );
		
	}	
	
	/**
	 * Este metodo retorna los elementos del modelo
	 * 
	 * @author Gabriel
	 *
	 */
	public Vector[] getElementos(){
	    
	    return modelo.getElementos();
	    
	}
    /**
     * Elimina los statements ingresados en el modelo
     * 
     * @param statements statements a eliminar
     */
    public void eliminarStatements(Vector statements) {
        
        Iterator it = statements.iterator();
        while( it.hasNext() ){
            
            String[] statement = (String[])it.next();
            modelo.eliminarStatement( statement );
            swEngine.elimineStatement( statement );
            
            
        }
        
        ui.pinteResources(modelo.getElementos());
    }
    /**
     * Adiciona los statements indicados en el vector
     * 
     * @param nuevosStatements vector de statements a ingresar
     */
    public void adicionarStatements(Vector nuevosStatements) {
        
        Iterator it = nuevosStatements.iterator();
        
        while(it.hasNext() ){
            
            String[] statement = (String[])it.next();
            modelo.ingresarStatement( statement );
            swEngine.ingresarStatement(statement[0], statement[1],statement[2]);
        }
        ui.pinteResources(modelo.getElementos() );
    }
	/**
	 * Ingresa un nuevo QName en el modelo
	 * 
	 * @param name
	 */
	public void adicionarQName(String[] name) {
		
		swEngine.adicionarQName( name );
		ui.adicionePrefixes( swEngine.getPrefixes() );
		
	}
	/**
	 * Elimina un QName del modelo
	 * 
	 * @param name
	 */
	public void eliminarQName(String name) {
		
		swEngine.eliminarQName( name );
		ui.adicionePrefixes( swEngine.getPrefixes() );
		
	}
	/**
	 * Edita un QName previamente ingresado
	 * 
	 * @param prefix
	 * @param antiguoURI
	 * @param nuevoURI
	 */
	public void cambieQName(String prefix, String antiguoURI, String nuevoURI) {
		
		swEngine.eliminarQName( prefix );
		String qname[] = new String[2];
		qname[0] = prefix;
		qname[1] = nuevoURI;
		adicionarQName( qname );
		
	}
	
}
