/*
 * Created on 17-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenierï¿½a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.comun;

import java.util.Vector;

import co.edu.unal.jsemwed.swEngine.ISWEngine;

/**
 * Interfaz que define los servicios del subsistema controlador
 * 
 * @author Gabriel
 *
 */
public interface IControlador {
	
	
	/**
	 * Crear un nuevo modelo sobre el cual ingresar Statements.
	 *
	 */
	boolean crearModelo();

	/**
	 * Recibe la solicitud para guardar un modelo.
	 * 
	 */
	void guardarModelo();
	
	/**
	 *Añade un resource en el modelo sobre el cual se esta trabajando 
	 * 
	 * @author Gabriel
	 *
	 */
	void ingresarResource(String URI);
	
	/**
	 * Añade un Statement en el modelo sobre el cual se esta trabajando
	 * en el cual todas las partes, Subject, Predicate y Object son Resources.
	 * 
	 * @author Gabriel
	 *
	 */
	void ingresarStatement( String subject, String Predicate, String Object );
	
	/**
	 * Deshace la ultima accion llevada a cabo
	 * 
	 * @author Gabriel
	 *
	 */
	void undo();
	
	/**
	 * Rehace la ultima acciï¿½n deshecha
	 * 
	 * @author Gabriel
	 *
	 */
	void redo();
	
	/**
	 * Adiciona una vista con el formato indicado por el usuario de la aplicaciï¿½n.
	 * 
	 * @author Gabriel
	 *
	 */
	void adicionarVista( String formato );

    /**
     * Configura el componente UI para interactuar con el usuario.
     * 
     * @param ui le ingresa la interfaz de usuario a utilizar
     */
    void setUi(IUI ui);

    /**
     * Configura el componente swEngine para interactuar con un motor de Semantic Web.
     * 
     * @param swEngine le ingresa el engine de Semantic Web a utilizar
     */
    void setSwEngine(ISWEngine swEngine);

    /**
     * Recibe la solicitud para abrir un nuevo modelo.
     */
    void abrirModelo();

	/**
	 * Recibe la solicitud de crear un nuevo modelo en un formato seleccionado.
	 * 
	 * @param modelo
	 */
	void crearModelo(String modelo);

	/**
	 * Recibe la solicitud para ingresar un nuevo Elemento a la aplicacion
	 * 
	 * @param string tipo del elemento
	 * @param uri uri del elemento
	 */
	void ingresarResource(String string, String uri);

	/**
	 * Recibe la solicitud para eliminar un elemento de la aplicacion
	 * 
	 * @param tipoElemento tipo de elemento a eliminar.
	 * @param urlNodoSelecto URIdel nodo a eliminar.
	 */
	void borrarElemento(String tipoElemento, String urlNodoSelecto );

	/**
	 * Recibe la solicitud para cambiar el URI a un elemento
	 * 
	 * @param uriViejo URI del elemento que se desea modificar
	 * @param uri nuevo URI a ingresar al elemento
	 */
	void modificarElemento(String uriViejo, String uriNuevo);

	/**
	 * Solicitud que recibe el controlador para ejecutar una operacion
	 * 
	 * @param urlNodoSelecto URI del nodo de la operacion
	 * @param statement parametros para ejecutar la operacion.
	 */
	void doOperacion(String urlNodoSelecto, Vector statements);

    /**
     * Solicitud para llevar a cabo una busqueda.
     * 
     * @param query busqueda a realizar.
     */
    void realizarBusqueda(String query);

    /**
     * Solicitud para adicionar un QName al modelo.
     * 
     * @param name nuevo QName a ingresar en la aplicacion.
     */
    void adicioneQName(String[] name);

	/**
	 * Solicitud para eliminar un QName de la aplicacion
	 * 
	 * @param string
	 * @param string2
	 */
	void elimineQName(String string, String string2);

	/**
	 * Solicitud para editar un QName en la aplicacion
	 * 
	 * @param prefix prefijo del QName a editar.
	 * @param antiguoURI URI antiguo asociado al prefijo
	 * @param nuevoURI nuevo URI a asociar con el prefijo.
	 */
	void editeQName(String prefix, String antiguoURI, String nuevoURI);

    /**
     * Solicitud para cerrar la aplicacion
     */
    void cerrarUI();
}
