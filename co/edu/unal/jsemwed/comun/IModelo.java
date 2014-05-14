/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.comun;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IModelo extends Serializable{

    /**
     * Devuelve las b�squedas que se pueden realizar sobre este modelo.
     * 
     * @return
     */
    String[] getBusquedas();
    
    /**
     * Devuelve el nombre de los elementos asociados a este modelo.
     * @return
     */
	String[] getElementosPosibles();

	/**
	 * Retorna el lenguaje en el cual esta implementado este modelo.
	 * Por ejemplo, RDF
	 * @return
	 */
	String getLenguaje();

	/**
	 * Ingresa un elemento al modelo con el tipo correspondiente
	 * 
	 * @param modelo2
	 * @param uri
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @return URI del resource a ingresar
	 */
	String ingresarElemento(String modelo2, String uri) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	/**
	 * Retorna en cada vector los elementos ingresados de cada tipo que soporte el modelo
	 * @return
	 */
	Vector[] getElementos();

	/**
	 * Elimina un elemento del modelo
	 * 
	 * @param tipoElemento tipo de elemento a eliminar.
	 * @param urlNodoSelecto URI del nodo a eliminar
	 */
	String eliminarElemento(String tipoElemento, String urlNodoSelecto);

	/**
	 * Ingresa un nuevo statement sobre este modelo.
	 * 
	 * @param statement statement a ingresar.
	 */
	void ingresarStatement(String[] statement);

	/**
	 * elimina un statement del modelo.
	 * 
	 * @param statement statement a eliminar del modelo.
	 */
	void eliminarStatement(String[] statement);

	/**
	 * Aciciona una operacion a un elemento que se encuentra en el modelo.
	 * 
	 * @param uri uri del elemento al que se le adiciona la operacion.
	 * @param op nombre de la operacion a adicionar al elemento.
	 */
	void adicionarOperacion(String uri, String op);

	/**
	 * Configura el modelo serializado para guardarlo.
	 * 
	 * @param modeloSerializado
	 */
	void setModeloSerializado(String modeloSerializado);

	/**
	 * Obtiene el modelo serializado.
	 * 
	 * @return modelo serializado.
	 */
	String getModeloSerializado();

	/**
	 * Elimina la operacion de un elemento que se encuentra en este modelo.
	 * 
	 * @param string URI del elemento al que se le eliminara la operacion.
	 * @param string2 nombre de la operacion a eliminar sobre este elemento.
	 */
	void eliminarOperacion(String string, String string2);
	
}
