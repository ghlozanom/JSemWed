/*
 * Created on 28-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.rdfs.comment;

import java.util.Vector;

import com.hp.hpl.jena.vocabulary.RDFS;

import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.plugins.operacion.OperacionControlador;

/**
 * @author Hernando
 *
 */
public class IngresarCommentOpControlador extends OperacionControlador
        implements IOperacionControlador {

    private Vector vector;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
     */
    public void ejecutar() {
       
        String[] statement1 = (String[])statement.get(0);
        String[] statement2 = new String[3];
        statement2[0] = statement1[0];
        statement2[1] = RDFS.comment.getURI();
        statement2[2] = statement1[1];
        
        vector = new Vector();
        vector.add( statement2 );
        modeloHandler.adicionarStatements( vector );

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
     */
    public void undo() {
        
        modeloHandler.eliminarStatements( vector );

    }

}
