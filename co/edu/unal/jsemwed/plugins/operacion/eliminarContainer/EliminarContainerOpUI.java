/*
 * Created on 09-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.eliminarContainer;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author Gabriel
 *
 */
public class EliminarContainerOpUI extends OperacionUI {

    private String[] statement = null;
    private final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.plugins.operacion.OperacionUI#getStatement()
     */
    public Vector getStatement() {
        
    	String[] opciones = {getLabels().getString("si"), getLabels().getString("no")};
    	
        int seleccion = JOptionPane.showOptionDialog(
                getVentanaPrincipal(),
                getLabels().getString("EstaSeguro") + " ?",
				getLabels().getString("eliminarContainer"),
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				opciones,
				getLabels().getString( "no" ));
        
        //Si escogio que no
        if( seleccion == 1 )
        	return null;
        //Encontrar la propiedad que tiene el container
        Iterator it = getElementos().iterator();
        IElemento elemento = null;
        
        while( it.hasNext() ){
            
            elemento = (IElemento) it.next();
            if( elemento.getURI().equals(getUriNodoSelecto()))
                break;
            elemento = null;
            
        }
        
        if( elemento != null ) {
            statement = new String[3];
            statement[0] = getUriNodoSelecto();
            Vector propiedades = elemento.getPropiedades();
            Iterator propsIt = propiedades.iterator();
            boolean hallado = false;
            while( propsIt.hasNext()) {
                
                Object[] prop = (Object[]) propsIt.next();
                String nombreProp = (String) prop[0];
                if( nombreProp.equals(RDF + "type" )) {
                	Vector valoresProp = ( Vector) prop[1];
                	Iterator valoresIt = valoresProp.iterator();
                	while( valoresIt.hasNext() )
                	{
                		String valorProp = (String) valoresIt.next();
                		if( valorProp.equals( RDF + "Bag") ||
                				valorProp.equals( RDF + "Seq") ||
								valorProp.equals( RDF + "Alt"))
                		{
                			statement[1] = nombreProp;
                			statement[2] = valorProp;
                			hallado = true;
                			break;
                		}
                	}
                	if( hallado )
                		break;
                }
                    
            }
        }
        
        Vector vector = new Vector(1);
        vector.add( statement );
        return vector;
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        // TODO Auto-generated method stub
        
    }


}
