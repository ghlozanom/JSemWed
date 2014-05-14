/*
 * Created on 17-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.impl;

import java.util.Vector;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.comun.IUI;
import co.edu.unal.jsemwed.controlador.IControladorComandos;
import co.edu.unal.jsemwed.controlador.CasosDeUso.ModeloHandler;
import co.edu.unal.jsemwed.controlador.CasosDeUso.StatementHandler;
import co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.VistasHandler;
import co.edu.unal.jsemwed.controlador.comandos.IComando;
import co.edu.unal.jsemwed.controlador.comandos.impl.BorrarElementoComando;
import co.edu.unal.jsemwed.controlador.comandos.impl.EditarQNameComando;
import co.edu.unal.jsemwed.controlador.comandos.impl.ElimineQNameComando;
import co.edu.unal.jsemwed.controlador.comandos.impl.IngresarQNameComando;
import co.edu.unal.jsemwed.controlador.comandos.impl.IngresarResourceComando;
import co.edu.unal.jsemwed.controlador.comandos.impl.IngresarStatementComando;
import co.edu.unal.jsemwed.swEngine.ISWEngine;

/**
 * Esta clase intercepta las solicitudes del usuario y maneja
 * el historial de comandos para permitir acciones como deshacer
 * y rehacer.
 * 
 * @author Gabriel
 *
 */
public class IControladorImpl implements IControlador, IControladorComandos {

	private boolean modeloGuardado = true;	
	private ModeloHandler modeloHandler;
	private StatementHandler stmtHandler;
	//private IControlador casosDeUso;
	private IUI ui;
	private ISWEngine swEngine;
	private IComando undo = null;
	private IComando redo = null;

	private VistasHandler vistasHandler;

	private void adicionarComando( IComando comando ){
		
		//Se debe a�adir el comando a los de deshacer
		IComando temp = undo;
		undo = comando;
		undo.setSiguiente( temp );
		ui.setDeshacer( true );
		
		//el modelo debe guardarse
		modeloGuardado = false;
		ui.guardarModeloHabilitado( true );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#borrarElemento(java.lang.String)
	 */
	public void borrarElemento( String tipoElemento, String urlNodoSelecto) {
		
		IComando comando = new BorrarElementoComando( tipoElemento, urlNodoSelecto, this );
		try {
            comando.ejecutar();
            adicionarComando( comando );
        } catch (Exception e) {

        }
		
		
		
		
	}
	
	public IControladorImpl(){
		
		modeloHandler = new ModeloHandler();
		stmtHandler = new StatementHandler();
		vistasHandler = new VistasHandler();
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.IControlador#crearModelo()
	 */
	public boolean crearModelo() {
		
		if( !modeloGuardado ){
			
			int i = ui.guardarModelo();
			if( i==1 )
				guardarModelo();
			else if ( i == 2 ){
				return false;
			}
		}
		
		modeloHandler.crearModelo();
		
		//Borra el historial de comandos
		undo = null;
		redo = null;
		
		return true;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#crearModelo(java.lang.String)
	 */
	public void crearModelo(String modelo) {
		
		if( !modeloGuardado ){
			
			int i = ui.guardarModelo();
			if( i==1 )
				guardarModelo();
			else if ( i == 2 ){
				//return false;
			}
		}
		
		modeloHandler.crearModelo( modelo );
		
		//Borra el historial de comandos
		undo = null;
		redo = null;
		
		//return true;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#guardarModelo()
	 */
	public void guardarModelo() {
		
		if (modeloHandler.guardarModelo()){
			
			modeloGuardado = true;
			ui.modeloGuardado();
		
			//Borra el historial de comandos
			undo = null;
			redo = null;
			
		}
				
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#ingresarResource(java.lang.String, java.lang.String)
	 */
	public void ingresarResource(String elemento, String uri) {
		
		IComando comando = new IngresarResourceComando( elemento, uri, this );
		try {
            comando.ejecutar();
            adicionarComando( comando );
        } catch (Exception e) {

        }
		
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.IControladorComandos#ingresarResourceComando(java.lang.String)
	 */
	public void ingresarResourceComando( String modelo, String uri) throws Exception {

		modeloHandler.ingreseResource( modelo, uri);
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#undo()
	 */
	public void undo() {
		
		if( undo != null ){
			
			try {
                undo.undo();
    			IComando temp = redo;
    			redo = undo;
    			undo = undo.getSiguiente();
    			redo.setSiguiente( temp );
    			
    			ui.setRehacer( true );
    			
    			if( undo == null )
    				ui.setDeshacer( false );
            } catch (Exception e) {
            }
			

		}
			
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#redo()
	 */
	public void redo() {
		
		try {
            this.redo.ejecutar();
    		IComando temp = undo;
    		undo = redo;
    		redo = redo.getSiguiente();
    		undo.setSiguiente( temp );
    		
    		
    		ui.setDeshacer( true );
    		
    		if( redo == null )
    			ui.setRehacer( false );
        } catch (Exception e) {

        }
		

		
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.IControladorComandos#eliminarResourceComando(java.lang.String)
	 */
	public void eliminarResourceComando( String modelo, String uri) {
		
		modeloHandler.eliminarResource( modelo, uri );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#ingresarStatement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void ingresarStatement(String subject, String predicate, String object) {
		 
		//Se crea un nuevo comando para ejecutar que ingresa el Statement en el modelo.
		IComando comando = new IngresarStatementComando( subject, predicate, object, this );
		
		try {
            //Se ejecuta el comando creado
            comando.ejecutar();
            adicionarComando( comando );
        } catch (Exception e) {

        }
		
		

		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.IControladorComandos#ingresarStatementComando(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void ingresarStatementComando(String subject, String predicate, String object) {
		
		stmtHandler.ingresarStatement( subject, predicate, object );
		
		//TODO Pintar los Statements actualmente vistos en la aplicaci�n.
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#adicionarVista(java.lang.String)
	 */
	public void adicionarVista(String formato) {
		
		vistasHandler.adicionarVista( formato );
		
	}
		

    /**
     * @param swEngine El swEngine a configurar.
     */
    public void setSwEngine(ISWEngine swEngine) {
        
        this.swEngine = swEngine;
        modeloHandler.setSwEngine( swEngine );
        stmtHandler.setSwEngine( swEngine );
        vistasHandler.setSwEngine( swEngine );
        
    }
    /**
     * @param ui El ui a configurar.
     */
    public void setUi(IUI ui) {
        
        this.ui = ui;
        modeloHandler.setUi( ui );
        stmtHandler.setUi( ui );
        vistasHandler.setUi( ui );
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IControlador#abrirModelo()
     */
    public void abrirModelo() {
        
        if (modeloHandler.abrirModelo())
        {
    		modeloGuardado = true;
    		ui.guardarModeloHabilitado(false);
    		
    		//Borra el historial de comandos
    		undo = null;
    		redo = null;
        }
        /**else {
            
            eliminarModelo();
            
        }**/
        
    }

    /**
     * Elimina el modelo sobre el cual se esta trabajando
     * 
     */
    public void eliminarModelo() {
        
        swEngine.elimineModelo();
        
        undo = null;
        redo = null;
        ui.modeloEliminado();
        
    }

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#modificarElemento(java.lang.String, java.lang.String)
	 */
	public void modificarElemento(String uriViejo, String uriNuevo) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#doOperacion(java.lang.String, java.lang.String[])
	 */
	public void doOperacion(String urlNodoSelecto, Vector statement) {
		
		String clase = urlNodoSelecto + "OpControlador";
		try {
			
			IOperacionControlador opControlador = (IOperacionControlador) Class.forName(clase).newInstance();
			opControlador.setStatement( statement );
			opControlador.setModeloHandler(  this.modeloHandler );
			opControlador.ejecutar();
			adicionarComando( opControlador );
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

        }
		
	}

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IControlador#realizarBusqueda(java.lang.String)
     */
    public void realizarBusqueda(String query) {
        
        modeloHandler.realizarBusqueda( query );
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IControlador#ingresarResource(java.lang.String)
     */
    public void ingresarResource(String URI) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IControlador#adicioneQName(java.lang.String)
     */
    public void adicioneQName(String[] name) {
        
		//Se crea un nuevo comando para ejecutar que ingresa el Statement en el modelo.
		IComando comando = new IngresarQNameComando( name, this );
		
		try {
            //Se ejecuta el comando creado
            comando.ejecutar();
    		adicionarComando( comando );
        } catch (Exception e) {

        }
		

        
    }

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.IControladorComandos#adicionarQNameComando(java.lang.String[])
	 */
	public void adicionarQNameComando(String[] name) {
		
		modeloHandler.adicionarQName( name );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.IControladorComandos#elimineQNameComando(java.lang.String[])
	 */
	public void elimineQNameComando(String name) {
		
		modeloHandler.eliminarQName( name );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#elimineQName(java.lang.String)
	 */
	public void elimineQName(String string, String URI ) {
		
		//Se crea un nuevo comando para ejecutar que ingresa el Statement en el modelo.
		IComando comando = new ElimineQNameComando( string, URI, this );
		
		try {
            //Se ejecuta el comando creado
            comando.ejecutar();
    		adicionarComando( comando );
        } catch (Exception e) {

        }
		

		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IControlador#editeQName(java.lang.String)
	 */
	public void editeQName(String prefix, String antiguoURI, String nuevoURI) {
		
		//Se crea un nuevo comando para ejecutar que ingresa el Statement en el modelo.
		IComando comando = new EditarQNameComando( prefix, antiguoURI, nuevoURI, this );
		
		try {
            //Se ejecuta el comando creado
            comando.ejecutar();
    		adicionarComando( comando );
    		
        } catch (Exception e) {
        }
		

		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.IControladorComandos#cambieQName(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void cambieQName(String prefix, String antiguoURI, String nuevoURI) {
		
		modeloHandler.cambieQName( prefix, antiguoURI, nuevoURI );
		
	}

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IControlador#cerrarUI()
     */
    public void cerrarUI() {
        
        if( !modeloGuardado ){
			
			int i = ui.guardarModelo();
			if( i==1 )
				guardarModelo();
			else if ( i == 2 ){
				return;
			}
		}
        ui.cerrarUI();
    }


}
