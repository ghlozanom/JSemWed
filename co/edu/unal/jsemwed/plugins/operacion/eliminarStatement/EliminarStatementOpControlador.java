/*
 * Created on Feb 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.plugins.operacion.eliminarStatement;

import java.util.Vector;

import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.controlador.CasosDeUso.ModeloHandler;
import co.edu.unal.jsemwed.controlador.comandos.IComando;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EliminarStatementOpControlador implements IOperacionControlador {

	private String[] statement;
	private ModeloHandler modeloHandler;
	private IComando sigComando;
	
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionControlador#setStatement(java.lang.String[])
	 */
	public void setStatement(Vector statement) {
		
		this.statement = (String[]) statement.get(0);

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionControlador#setModeloHandler(co.edu.unal.jsemwed.controlador.CasosDeUso.ModeloHandler)
	 */
	public void setModeloHandler(ModeloHandler handler) {
		
		this.modeloHandler = handler;

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
	 */
	public void ejecutar() {
		
		modeloHandler.eliminarStatement( statement );
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#setSiguiente(co.edu.unal.jsemwed.controlador.comandos.IComando)
	 */
	public void setSiguiente(IComando temp) {
		
		sigComando = temp;

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
	 */
	public void undo() {
		
		modeloHandler.ingresarStatement( statement );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
	 */
	public IComando getSiguiente() {
		// TODO Auto-generated method stub
		return sigComando;
	}

}
