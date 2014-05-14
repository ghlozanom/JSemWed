/*
 * Created on 01-may-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.claseowl;

import java.util.Iterator;
import java.util.Vector;

import com.hp.hpl.jena.vocabulary.OWL;

import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.plugins.operacion.OperacionControlador;

/**
 * @author Hernando
 *
 */
public class IngresarIntersectionOfOpControlador extends OperacionControlador
        implements IOperacionControlador {

    private String[] statement1;
    private Vector vector;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
     */
    public void ejecutar() {
        
        statement1 = new String[3];
        Iterator it = this.statement.iterator();
        String[] statement2 = (String[]) it.next();
        statement1[0] = statement2[0];
        statement1[1] = OWL.intersectionOf.getURI();
        statement1[2] = statement2[1];
        vector = new Vector();
        vector.add( statement1 );
        modeloHandler.adicionarStatements( vector );
        

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
     */
    public void undo() {
        
        modeloHandler.eliminarStatements( vector );

    }

}
