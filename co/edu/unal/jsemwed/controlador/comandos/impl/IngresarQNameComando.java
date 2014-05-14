/*
 * Created on Mar 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.controlador.comandos.impl;

import co.edu.unal.jsemwed.controlador.IControladorComandos;
import co.edu.unal.jsemwed.controlador.comandos.IComando;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IngresarQNameComando implements IComando {

	private String[] qName;
	private IControladorComandos controlador;
	private IComando sig;

	/**
	 * @param name
	 * @param impl
	 */
	public IngresarQNameComando(String[] name, IControladorComandos impl) {
		
		qName = name;
		controlador = impl;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
	 */
	public void ejecutar() {
		
		controlador.adicionarQNameComando( qName );

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#setSiguiente(co.edu.unal.jsemwed.controlador.comandos.IComando)
	 */
	public void setSiguiente(IComando temp) {
		
		sig = temp;

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
	 */
	public void undo() {
		
		controlador.elimineQNameComando( qName[0] );

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
	 */
	public IComando getSiguiente() {
		
		return sig;
		
	}

}
