/*
 * Created on 03-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.comandos.impl;

import co.edu.unal.jsemwed.controlador.IControladorComandos;
import co.edu.unal.jsemwed.controlador.comandos.IComando;

/**
 * @author Gabriel
 *
 */
public class IngresarStatementComando implements IComando {

	private String subject;
	private String predicate;
	private String object;
	private IComando comando = null;
	private IControladorComandos controlador;
	
	/**
	 * @param subject
	 * @param predicate
	 * @param object
	 * @param impl
	 */
	public IngresarStatementComando(String subject, String predicate, String object, IControladorComandos impl) {
		
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
		this.controlador = impl;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
	 */
	public void ejecutar() {
		
		controlador.ingresarStatementComando( subject, predicate, object );
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#setSiguiente(co.edu.unal.jsemwed.controlador.comandos.IComando)
	 */
	public void setSiguiente(IComando temp) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
	 */
	public void undo() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
	 */
	public IComando getSiguiente() {
		// TODO Auto-generated method stub
		return null;
	}

}
