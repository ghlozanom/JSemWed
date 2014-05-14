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
public class ElimineQNameComando implements IComando {

	private String prefix;
	private IControladorComandos controlador;
	private String URI;
	private IComando sig;

	/**
	 * @param string
	 * @param uri
	 * @param impl
	 */
	public ElimineQNameComando(String string, String uri, IControladorComandos impl) {
		
		prefix = string;
		controlador = impl;
		URI = uri;
	}
	

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
	 */
	public void ejecutar() {
		
		controlador.elimineQNameComando( prefix );

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
		String[] qName = new String[2];
		qName[0] = prefix;
		qName[1] = URI;
		controlador.adicionarQNameComando( qName );

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
	 */
	public IComando getSiguiente() {
		return sig;
	}

}
