/*
 * Created on 27-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.comandos;

/**
 * @author Gabriel
 *
 */
public interface IComando {

	/**
	 * Ejecuta la acción que este comando encapsula
	 * 
	 * @throws Exception
	 */
	void ejecutar() throws Exception;

	/**
	 * Configura el siguiente comando en la cola
	 * 
	 * @param temp
	 */
	void setSiguiente(IComando temp);

	/**
	 * Deshace la ultima acción realizada
	 * @throws Exception
	 */
	void undo() throws Exception;

	/**
	 * Obtiene el siguiente comando en la cola
	 * 
	 * @return siguiente comando en la cola de ejecución
	 */
	IComando getSiguiente();

}
