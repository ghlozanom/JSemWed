/*
 * Created on 04-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.CasosDeUso.vistas;

import co.edu.unal.jsemwed.comun.IUI;
import co.edu.unal.jsemwed.swEngine.ISWEngine;

/**
 * Interfaz para implementar una nueva vista del modelo.
 * 
 * @author Gabriel
 *
 */
public interface IVista {

	/**
	 * 
	 * @param temp
	 */
	void setVista(IVista temp);
	
	IVista getVista();

	/**
	 * Retorna al modelo en el formato representado por la vista
	 */
	void pinteModelo();

	/**
	 * Configura el Motor de Semantic Web para obtener la vista del modelo.
	 * @param swEngine
	 */
	void setSWEngine(ISWEngine swEngine);
	
	/**
	 * Configura el modulo UI para realizar solicitudes
	 * @param ui
	 */
	void setUI( IUI ui );

}
