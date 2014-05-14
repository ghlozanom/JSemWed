/*
 * Created on 08-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.eliminarContainer;

import java.util.Iterator;
import java.util.Vector;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.controlador.CasosDeUso.ModeloHandler;
import co.edu.unal.jsemwed.controlador.comandos.IComando;

/**
 * @author Gabriel
 *
 */
public class EliminarContainerOpControlador implements IOperacionControlador{

    private String[] statement;
    private ModeloHandler modeloHandler;
    private IComando sigComando;
    Vector statements;
    private int numStatements;
    
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
        
        //Eliminar los elementos del container y guardarlo en un vector de statements
        Vector[] elementosArray = modeloHandler.getElementos();
        Vector resources = elementosArray[0];
        Iterator it = resources.iterator();
        IElemento containerEl = null;
        while( it.hasNext() ){
            
            containerEl = (IElemento)it.next();
            if( containerEl.getURI().equals(statement[0]))
                break;
            
        }
        if( containerEl != null ){
            numStatements = 0;
            Vector props = containerEl.getPropiedades();
            Iterator propsit = props.iterator();
            statements = new Vector();
            while( propsit.hasNext() ){
                
                Object[] prop = (Object[]) propsit.next();
                if( ((String)prop[0]).startsWith("http://www.w3.org/1999" +
                		"/02/22-rdf-syntax-ns#_")){
                    Vector propVector = (Vector)prop[1];
                    
                    statements.add(new String[]{statement[0],
                            (String) prop[0], (String)propVector.get(0)} );
                    numStatements++;
                    
                    
                }
                
            }
            numStatements++;
            statements.add( statement );
            modeloHandler.eliminarOperacion( statement[0], "AgregarElementoContainer");
            modeloHandler.eliminarOperacion( statement[0], "EliminarContainer");
            modeloHandler.adicionarOperacion( statement[0], "CrearContainer" );
            modeloHandler.eliminarStatements( statements );
        }
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#setSiguiente(co.edu.unal.jsemwed.controlador.comandos.IComando)
     */
    public void setSiguiente(IComando temp) {

        this.sigComando = temp;
       
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
     */
    public void undo() {
        
        Vector nuevosStatements = new Vector( numStatements );
        Iterator it = statements.iterator();

        int i = 0;
        while( i < numStatements ){
            
            i++;
            nuevosStatements.add( statements.get(numStatements-i));
            
        }
        modeloHandler.adicionarOperacion( statement[0], "AgregarElementoContainer");
        modeloHandler.adicionarOperacion( statement[0], "EliminarContainer");
        modeloHandler.eliminarOperacion( statement[0], "CrearContainer" );
        modeloHandler.adicionarStatements( nuevosStatements );
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#getSiguiente()
     */
    public IComando getSiguiente() {

        return this.sigComando;
        
    }

    
    
}
