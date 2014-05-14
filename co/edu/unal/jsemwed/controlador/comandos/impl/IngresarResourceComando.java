/*
 * Created on 27-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.comandos.impl;

import co.edu.unal.jsemwed.controlador.IControladorComandos;
import co.edu.unal.jsemwed.controlador.comandos.IComando;

/**
 * @author Gabriel
 *
 */
public class IngresarResourceComando implements IComando {

	private String uri;
	private IControladorComandos controlador;
	private IComando sigComando;
	private String modelo;
	
	/**
	 * @param uri
	 */
	public IngresarResourceComando( String modelo, String uri, IControladorComandos cont ) {
		
		this.uri = uri;
		controlador = cont;
		this.modelo = modelo;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
	 */
	public void ejecutar() throws Exception {
		
		controlador.ingresarResourceComando( modelo, uri );
	
		
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
		
		controlador.eliminarResourceComando( modelo, uri );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
	 */
	public IComando getSiguiente() {
		return sigComando;
	}

}
