/*
 * Created on 29-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.propiedad;

import java.util.Iterator;
import java.util.Vector;

import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;

import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.plugins.operacion.OperacionControlador;

/**
 * @author Hernando
 *
 */
public class IngresarFunctionalPropertyOpControlador extends
        OperacionControlador implements IOperacionControlador {

    private Vector vector;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#ejecutar()
     */
    public void ejecutar() {
        
        String[] statement = new String[3];
        Iterator it = this.statement.iterator();
        statement[0] = it.next().toString();
        statement[1] = RDF.type.getURI();
        statement[2] = OWL.FunctionalProperty.getURI();
        
        vector = new Vector();
        vector.add( statement );
        modeloHandler.adicionarStatements( vector );

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.comandos.IComando#undo()
     */
    public void undo() {
        
        modeloHandler.eliminarStatements( vector );

    }

}
