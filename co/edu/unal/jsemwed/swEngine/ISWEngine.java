/*
 * Created on 17-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenierï¿½a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.swEngine;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Vector;

/**
 * @author Gabriel
 *
 */
public interface ISWEngine {

	/**
	 * Crea un nuevo modelo
	 * 
	 * @deprecated use crearModelo( String lenguaje )
	 * @return
	 */
	String crearModelo();

	/**
	 * Ingresa un nuevo Resource con el URI delparametro
	 * @param uri uri del nuevo resource
	 */
	void ingreseResource(String uri);

	/**
	 * Retorna los elementos ingresados previamente al modelo.
	 * 
	 * @return elementos ingresados al modelo
	 */
	Object[] getResources();

	/**
	 * Ingresa un nuevo statement al modelo.
	 * 
	 * @param subject subject del statement
	 * @param predicate predicate del statement
	 * @param object object del statement
	 */
	void ingresarStatement(String subject, String predicate, String object);

	/**
	 * Retorna el modelo con el formato determinado por el parï¿½metro 
	 * 
	 * @param modelo tipo del nuevo modelo.
	 * @return
	 */
	String getModelo(String modelo);

    /**
     * Guarda el modelo en el Stream del parámetro.
     * 
     * @param os Stream del parámetro.
     */
    void guardarModelo(OutputStream os);

    /**
     * Carga el modelo del Stream is
     * 
     * @param is Stream donde se encuentra el modelo
     */
    boolean abrirModelo(InputStream is);

    /**
     * Obtiene los resources ingresados en el modelo.
     * 
     */
    Vector cargeResources();

    /**
     * Elimina el modelo sobre el que se esta trabajando.
     */
    void elimineModelo();

	/**
	 * Crea un nuevo modelo con el lenguaje seleccionado.
	 * 
	 * @param lenguaje lenguaje para crear el nuevo modelo.
	 */
	void crearModelo(String lenguaje );

	/**
	 * Ingresa un nuevo resource al modelo
	 * 
	 * @param tipo clase del resource que se ingresa (p.ej. Clase o Propiedad)
	 * @param uri URI del nuevo Resource a ingresar
	 * @throws Exception se produce si el URI no esta bien formado
	 */
	void ingreseResource(String tipo, String uri) throws Exception;

	/**
	 * Elimina el resource del modelo sobre el que se trabaja
	 * 
	 * @param tipo Clase del resource a eliminar 
	 * @param urlNodoSelecto2 URI del resource a eliminar
	 */
	void elimineElemento(String tipo, String urlNodoSelecto2);

	/**
	 * Elimina un Statement del modelo sobre el que se trabaja.
	 * 
	 * @param statement statement a eliminar del modelo.
	 */
	void elimineStatement(String[] statement);

	/**
	 * Lee los statements en el modelo serializado
	 * 
	 * @param modeloSerializado
	 */
	void setModelo(String modeloSerializado);

    /**
     * Ejecuta la busqueda descrita por el query
     * 
     * @param query descripción de la busqueda
     * @return vector con el nombre y el valor de las variables en el resultado
     */
    Vector realizarBusqueda(String query);

    /**
     * Entrega los QNames ingresados en el modelo
     * 
     * @return mapa de QNames ingresados en la app.
     */
    Map getPrefixes();

	/**
	 * Ingresa un nuevo QName al modelo
	 * 
	 * @param name nuevo QName a ingresar
	 */
	void adicionarQName(String[] name);

	/**
	 * Elimina un QName previamente ingresado.
	 * 
	 * @param name nombre del prefijo del QName a eliminar
	 */
	void eliminarQName(String name);

}
