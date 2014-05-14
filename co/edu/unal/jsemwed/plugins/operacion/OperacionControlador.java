/*
 * Created on 07-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion;

import java.util.Vector;

import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.controlador.CasosDeUso.ModeloHandler;
import co.edu.unal.jsemwed.controlador.comandos.IComando;

/**
 * @author Gabriel
 *
 */
public abstract class OperacionControlador implements IOperacionControlador {

    protected Vector statement;
    protected ModeloHandler modeloHandler;
    private IComando siguiente;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionControlador#setStatement(java.util.Vector)
     */
    public void setStatement(Vector statement) {
        
        this.statement = statement;

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
    public abstract void ejecutar();

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#setSiguiente(co.edu.unal.jsemwed.controlador.comandos.IComando)
     */
    public void setSiguiente(IComando temp) {
        
        this.siguiente = temp;

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
     */
    public abstract void undo();

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
     */
    public IComando getSiguiente() {
        
        return siguiente;
        
    }

}
