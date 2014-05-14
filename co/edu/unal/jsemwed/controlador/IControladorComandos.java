/*
 * Created on 27-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador;

/**
 * Interfaz que redefine los metodos de la interfaz comando para implementar
 * la funcionalidad de rehacer y deshacer y que no se suman a la cola de ops.
 * 
 * @author Gabriel
 *
 */
public interface IControladorComandos {

    /**
     * Ingresa un nuevo resource a la app
     * 
     * @param modelo 
     * @param uri
     * @throws Exception
     */
	void ingresarResourceComando( String modelo, String uri ) throws Exception;

	/**
	 * Elimina un resource del modelo.
	 * 
	 * @param uri
	 */
	void eliminarResourceComando(String modelo, String uri);

	/**
	 * Ingresa un Statement sencillo en la aplicacion.
	 * 
	 * @param subject
	 * @param predicate
	 * @param object
	 */
	void ingresarStatementComando(String subject, String predicate, String object);

	/**
	 * Adiciona un QName a la aplicacion
	 * 
	 * @param name
	 */
	void adicionarQNameComando(String[] name);

	/**
	 * Elimina un QName de la aplicacion
	 * 
	 * @param prefix
	 */
	void elimineQNameComando(String prefix);

	/**
	 * Edita un QName ingresado previamente en la app
	 * 
	 * @param prefix
	 * @param nuevoURI
	 * @param antiguoURI
	 */
	void cambieQName(String prefix, String antiguoURI, String nuevoURI);


	
}
