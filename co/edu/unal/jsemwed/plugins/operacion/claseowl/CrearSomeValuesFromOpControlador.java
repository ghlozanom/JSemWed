/*
 * Created on 30-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.claseowl;

import java.util.Vector;

import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;

import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.plugins.operacion.OperacionControlador;

/**
 * @author Hernando
 *
 */
public class CrearSomeValuesFromOpControlador extends OperacionControlador
        implements IOperacionControlador {

    private Vector vector;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
     */
    public void ejecutar() {
       
        String[] statement1 = (String[])statement.get(0);
        String[] statement2 = new String[3];
        statement2[0] = statement1[0];
        statement2[1] = RDF.type.getURI();
        statement2[2] = OWL.Restriction.getURI();
        
        vector = new Vector();
        vector.add( statement2 );
        
        statement2 = new String[3];
        statement2[0] = statement1[0];
        statement2[1] = OWL.onProperty.getURI();
        statement2[2] = statement1[1];
        
        vector.add( statement2 );
        
        statement2 = new String[3];
        statement2[0] = statement1[0];
        statement2[1] = OWL.someValuesFrom.getURI();
        statement2[2] = statement1[2]; 
            
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
