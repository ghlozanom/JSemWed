/*
 * Created on 02-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
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
     * Configuración de la ventana principal para implementar el componente de busquedas
     * 
     * @param frame ventana principal de la aplicación.
     */
    void setJFrame( JFrame frame );

    /**
     * Configuración de los elementos previamente ingresados para ser usados en la
     *  construcción de la busqueda.
     * 
     * @param elementos elementos previamente ingresados en la aplicacion
     */
    void setElementos(Vector[] elementos);

    /**
     * Labels para permitir internacionalización.
     * 
     * @param labels
     */
    void setLabels(ResourceBundle labels);
    
}
