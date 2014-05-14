/*
 * Created on 21-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.reification;

import java.util.Iterator;
import java.util.Vector;

import com.hp.hpl.jena.vocabulary.RDF;

import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.plugins.operacion.OperacionControlador;

/**
 * @author Gabriel
 *
 */
public class IngresarReificationOpControlador extends OperacionControlador 
implements IOperacionControlador {

    private Vector statements2;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
     */
    public void ejecutar() {
        
        Iterator it = statement.iterator();
        String[] statementOrig = (String[]) it.next();
        String[] primerStatement =  (String[]) it.next();
        
        statements2 = new Vector();
        String[] statement2 = new String[3];
        statement2[0] = primerStatement[0];
        statement2[1] = RDF.type.getURI();
        statement2[2] = RDF.Statement.getURI();
        statements2.add( statement2 );
        
        statement2 = new String[3];
        statement2[0] = primerStatement[0];
        statement2[1] = RDF.subject.getURI();
        statement2[2] = statementOrig[0];
        statements2.add( statement2 );
        
        statement2 = new String[3];
        statement2[0] = primerStatement[0];
        statement2[1] = RDF.predicate.getURI();
        statement2[2] = statementOrig[1];
        statements2.add( statement2 );
        
        statement2 = new String[3];
        statement2[0] = primerStatement[0];
        statement2[1] = RDF.object.getURI();
        statement2[2] = statementOrig[2];
        statements2.add( statement2 );
        
        statements2.add( primerStatement );
        
        while( it.hasNext() ){
            
            statement2 = (String[])it.next();
            statements2.add( statement2 );
            
        }
        
        modeloHandler.adicionarStatements( statements2 );
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
     */
    public void undo() {
        
        modeloHandler.eliminarStatements( statements2 );

    }

}
