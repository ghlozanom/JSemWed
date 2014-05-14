/*
 * Created on 02-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.comun;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;

/**
 * Interfaz para implementar una nueva busqueda en la aplicacion.
 * 
 * @author Gabriel
 *
 */
public interface IBusqueda {

    /**
     * Obtiene la busqueda que se quiere realizar.
     * 
     * @return busqueda a realizar en formato RDQL
     */
    String getQuery();
    
    /**
     * Configuraci�n de la ventana principal para implementar el componente de busquedas
     * 
     * @param frame ventana principal de la aplicaci�n.
     */
    void setJFrame( JFrame frame );

    /**
     * Configuraci�n de los elementos previamente ingresados para ser usados en la
     *  construcci�n de la busqueda.
     * 
     * @param elementos elementos previamente ingresados en la aplicacion
     */
    void setElementos(Vector[] elementos);

    /**
     * Labels para permitir internacionalizaci�n.
     * 
     * @param labels
     */
    void setLabels(ResourceBundle labels);
    
}
