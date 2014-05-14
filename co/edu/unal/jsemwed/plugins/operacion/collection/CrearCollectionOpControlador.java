/*
 * Created on 07-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.collection;

import co.edu.unal.jsemwed.comun.IOperacionControlador;
import co.edu.unal.jsemwed.plugins.operacion.OperacionControlador;

/**
 * @author Gabriel
 *
 */
public class CrearCollectionOpControlador
extends OperacionControlador 
implements IOperacionControlador
{

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.plugins.operacion.OperacionControlador#ejecutar()
     */
    public void ejecutar() {
        
        modeloHandler.adicionarStatements( statement );
        String[] statements = (String[]) statement.get(0);
        modeloHandler.eliminarOperacion( statements[0],"EliminarStatement" );
        modeloHandler.adicionarOperacion( statements[0], "AdicionarElementoLista");
        modeloHandler.adicionarOperacion( statements[0], "EliminarElementoLista");
        modeloHandler.adicionarOperacion( statements[0], "EliminarLista");
        
        for( int i = 1; i<statement.size(); i++  ){
            
            if( i % 2 == 0 )
                continue;
            statements = (String[]) statement.get(i);
            modeloHandler.eliminarOperacion( statements[0],"EliminarStatement" );
            
        }
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.plugins.operacion.OperacionControlador#undo()
     */
    public void undo() {
        modeloHandler.eliminarStatements( statement );
        
        String[] statements = (String[]) statement.get(0);
        modeloHandler.eliminarOperacion( statements[0],"IngresarStatement" );
        
        for( int i = 1; i<statement.size(); i++  ){
            
            if( i % 2 == 0 )
                continue;
            statements = (String[]) statement.get(i);
            modeloHandler.eliminarOperacion( statements[0],"IngresarStatement" );
            
        }
        
    }



}
