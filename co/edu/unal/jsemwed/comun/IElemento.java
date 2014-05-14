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
 * Interfaz para implementar un nuevo elemento
 * 
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IElemento extends Serializable{

	
    /**
     * Retorna el URI del tipo de elemento asociado a este elemento.
     * 
     * @return URI del tipo de este elemento, por ejemplo owl:Class para
     * 	  	   elementos de tipo owl:Class
     */
	public String getTipoURI();

	/**
	 * Configura si un elemento ha sido modificado.
	 * 
	 * @param b valor para configurar esta propiedad.
	 */
	public void isModificado(boolean b);
	
	/**
	 * Configura el URI de este elemento
	 * 
	 * @param URI URI del elemento
	 */
	void setURI( String URI );
	
	/**
	 * Obtiene el URI del elemento
	 * 
	 * @return URI del elemento
	 */
	String getURI();
	
	/**
	 * Obtiene las operaciones asociadas a este elemento.
	 * 
	 * @return vector de operaciones
	 */
	public Vector getOperaciones();

	/**
	 * Obtiene los statements ingresados sobre este elemento
	 * 
	 * @return vector de statements donde este elemento es el subject de ellos.
	 */
	public Vector getPropiedades();
	
	/**
	 * Permite adicionar propiedades a este elemento.
	 * 
	 * @param propiedad
	 */
	public void addPropiedad(String[] propiedad);

	/**
	 * Permite eliminar propiedades a este elemento
	 * 
	 * @param propiedad
	 */
	public void removePropiedad(String[] propiedad);

	/**
	 * Permite adicionar operaciones al elemento
	 * 
	 * @param op
	 */
	public void addOperacion(String op);

	/**
	 * Permite eliminar operaciones a este elemento
	 * 
	 * @param string
	 */
	public void removeOperacion(String string);
}
