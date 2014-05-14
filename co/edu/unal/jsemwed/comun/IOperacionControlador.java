/*
 * Created on Feb 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.comun;

import java.util.Vector;

import co.edu.unal.jsemwed.controlador.CasosDeUso.ModeloHandler;
import co.edu.unal.jsemwed.controlador.comandos.IComando;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IOperacionControlador extends IComando{

	/**
	 * Configura los valores para llevar a cabo la operacion
	 * 
	 * @param statement parametros de la operacion
	 */
	void setStatement(Vector statement);

	/**
	 * Configura el modeloHandler para interactuar con el modelo sobre el que se trabaja
	 * 
	 * @param handler componente para interactuar con el modelo sobre el que se trabaja
	 */
	void setModeloHandler(ModeloHandler handler);

}
