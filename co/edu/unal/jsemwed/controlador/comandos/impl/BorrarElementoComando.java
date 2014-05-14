/*
 * Created on Feb 25, 2005
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
public class BorrarElementoComando implements IComando {

	String tipoElemento;
	String urlNodo;
	private IControladorComandos controlador;
	private IComando sigComando;
	
	/**
	 * @param tipoElemento
	 * @param urlNodoSelecto
	 * @param impl
	 */
	public BorrarElementoComando(String tipoElemento, String urlNodoSelecto, IControladorComandos impl) {
		
		this.tipoElemento = tipoElemento;
		this.urlNodo = urlNodoSelecto;
		this.controlador = impl;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
	 */
	public void ejecutar() {
		
		controlador.eliminarResourceComando( tipoElemento, urlNodo );

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
	public void undo() throws Exception {
		
		controlador.ingresarResourceComando( tipoElemento, this.urlNodo );

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
	 */
	public IComando getSiguiente() {
		
		return sigComando;
	}

}
