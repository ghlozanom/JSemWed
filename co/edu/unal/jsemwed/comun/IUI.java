/*
 * Created on 17-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.comun;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Vector;

/**
 * @author Gabriel
 *
 */
public interface IUI {

	/**
	 * @param nombreModelo
	 */
	void setNombreModelo(String nombreModelo);

	/**
	 * @return 0 no guardar modelo y continuar, 1 guardar modelo, 2 cancelar
	 */
	int guardarModelo();

	/**
	 * @param vectors
	 */
	void pinteResources(Vector[] vectors);

	/**
	 * Habilita la opcion deshacer en la interfaz de usuario.
	 * @param b
	 */
	void setDeshacer(boolean b);

	/**
	 * Indica a la interfaz de usuario que un modelo fue creado para
	 * que pueda hacer cosas como ingresar resources o statements.
	 * 
	 * @param b
	 */
	void modeloCreado();

	/**
	 * Deshabilita la opci�n de deshacer en la interfaz de usuario
	 * 
	 */
	void deshabilitarUndo();

	/**
	 * 
	 */
	void habilitarRedo();

	/**
	 * 
	 */
	void deshabilitarRedo();

	/**
	 * 
	 */
	void habilitarUndo();

	/**
	 * Define el estado de la opci�n de rehacer en la interfaz de usuario.
	 * 
	 * @param b
	 */
	void setRehacer(boolean b);

	/**
	 * Define el estado de la opci�n de guardar modelo de la interfaz de usuario.
	 * @param b
	 */
	void guardarModeloHabilitado(boolean b);

	/**
	 * Pinta una vista del modelo de acuerdo al formato
	 * 
	 * @param modelo
	 */
	void pinteModelo(String modelo, String formato);

    /**
     * @param controlador
     */
    void setControlador(IControlador controlador);

    /**
     * Obtiene el OutputStream que contiene el modelo a cargar
     * 
     * @return
     */
    OutputStream getOutputStream();

    /**
     * Indica a la UI que el modelo ha sido guardado para que actualice 
     * su estado.
     * 
     */
    void modeloGuardado();

    /**
     * @return
     */
    InputStream getInputStream();

    /**
     * Indica a la UI que nuevos resources han sido ingresados a la aplicacion
     * y que debe actualizarse.
     * 
     * @param resources
     */
    void cargeResources(Vector resources);

    /**
     * @param string
     */
    void mensajeError(String string);

    /**
     * Indica a la UI que el modelo ha sido eliminado y que debe actualizar 
     * el estado de la interfaz
     * 
     */
    void modeloEliminado();

	/**
	 * Adiciona los elementos que el modelo puede manejar
	 * 
	 * @param elementos
	 */
	void adicioneElementos(String[] elementos);
	
	/**
	 * Adiciona las busquedas posibles a este modelo
	 * 
	 * @author Gabriel
	 */
	void adicioneBusquedas( String[] busquedas );

    /**
     * @param resultado
     */
    void pinteResultadoBusqueda(Vector resultado);

    /**
     * Adiciona nuevos QNames en la interfaz de usuario
     * 
     * @param qnames
     */
    void adicionePrefixes(Map qnames);

    /**
     * Cierra la interfaz de usuario
     */
    void cerrarUI();

    /**
     * avisa a la ui que un modelo se ha abierto
     */
    void modeloAbierto();

}
