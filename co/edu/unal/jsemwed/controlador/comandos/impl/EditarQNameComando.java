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
public class EditarQNameComando implements IComando {

	private String prefix;
	private String nuevoURI;
	private IControladorComandos controlador;
	private String antiguoURI;
	private IComando sig;

	/**
	 * @param prefix
	 * @param nuevoURI
	 * @param nuevoURI2
	 * @param impl
	 */
	public EditarQNameComando(String prefix, String antiguoURI, String nuevoURI, IControladorComandos impl) {
		
		this.prefix = prefix;
		this.antiguoURI = antiguoURI;
		this.nuevoURI = nuevoURI;
		controlador = impl;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
	 */
	public void ejecutar() {
		
		controlador.cambieQName( prefix, antiguoURI, nuevoURI );

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
		
		controlador.cambieQName( prefix, nuevoURI, antiguoURI );

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
	 */
	public IComando getSiguiente() {
		
		return sig;
		
	}

}
