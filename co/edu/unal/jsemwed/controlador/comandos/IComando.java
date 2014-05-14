/*
 * Created on 27-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.comandos;

/**
 * @author Gabriel
 *
 */
public interface IComando {

	/**
	 * Ejecuta la acci�n que este comando encapsula
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
	 * Deshace la ultima acci�n realizada
	 * @throws Exception
	 */
	void undo() throws Exception;

	/**
	 * Obtiene el siguiente comando en la cola
	 * 
	 * @return siguiente comando en la cola de ejecuci�n
	 */
	IComando getSiguiente();

}
