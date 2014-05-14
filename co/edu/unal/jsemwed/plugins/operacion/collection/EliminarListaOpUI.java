/*
 * Created on 08-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.collection;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author Gabriel
 *
 */
public class EliminarListaOpUI extends OperacionUI implements IOperacionUI {

    Vector statements;
    
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public Vector getStatement() {
        
    	String[] opciones = {getLabels().getString("si"), getLabels().getString("no")};
    	
        int seleccion = JOptionPane.showOptionDialog(
                getVentanaPrincipal(),
                getLabels().getString("EstaSeguro") + " ?",
				getLabels().getString("eliminarLista"),
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				opciones,
				getLabels().getString( "no" ));
        
        //Si escogio que no
        if( seleccion == 1 )
        	return null;
        String bnLista = null;
        Iterator it = this.elementos.iterator();
        boolean hallado = false;
        statements = new Vector();
        while( it.hasNext() ){
            
            IElemento elemento = (IElemento) it.next();
            Vector propiedades = elemento.getPropiedades();
            Iterator it2 = propiedades.iterator();
            
            while( it2.hasNext() ){
                
                Object[] valorPropiedades = (Object[]) it2.next();
                Iterator it3 = ((Vector)valorPropiedades[1]).iterator();
                while( it3.hasNext() ){
                    
                    String valor = (String) it3.next();
                    if( valor.startsWith("_:") ){
                        
                        Iterator it4 = elementos.iterator();
                        while( it4.hasNext() ){//Buscar los que tengan lista
                            
                            IElemento elemento2 = (IElemento) it4.next();
                            if( elemento2.getURI().equals( valor ) ){
                                
                                Vector propiedades2 = elemento2.getPropiedades();
                                Iterator it5 = propiedades2.iterator();
                                while( it5.hasNext() ){
                                    
                                    Object[] valorPropiedad2 = (Object[]) it5.next();
                                    String propiedad = (String) valorPropiedad2[0];
                                    if( propiedad.equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#first")){
                                        String[] statement1 = new String[3];
                                        statement1[0] = elemento.getURI();
                                        statement1[1] = (String) valorPropiedades[0];
                                        statement1[2] = valor;
                                        statements.add( statement1);
                                        bnLista = valor;
                                        hallado = true;
                                        break;
                                    }
                                    
                                }

                            }// fin de if busqueda elementos
                            if( hallado )
                                break;
                        }
                        
                    }
                    if( hallado )
                        break;
                }
                if( hallado )
                    break;
                
            }
            if( hallado )
                break;
        }
        boolean noFinalizado = true; 
        do{
            Iterator it2 = elementos.iterator();
            while( it2.hasNext() && noFinalizado ){
                IElemento elemento = ( IElemento ) it2.next();
                Iterator propIt = (( Vector )elemento.getPropiedades()).iterator();
                while( propIt.hasNext() && noFinalizado ){
                    Object[] propiedad = (Object[]) propIt.next();
                    if( ((String)propiedad[0]).equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#first") ){
                        String[] valores = new String[3];
                        valores[0] = elemento.getURI();
                        valores[1] = "http://www.w3.org/1999/02/22-rdf-syntax-ns#first";
                        valores[2] = (String) ((Vector)propiedad[1]).get(0);
                        statements.add( valores );
                        
                    }else if(((String)propiedad[0]).equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#rest")){
                        
                        String[] valores = new String[3];
                        valores[0] = elemento.getURI();
                        valores[1] = "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest";
                        valores[2] = (String) ((Vector)propiedad[1]).get(0);
                        statements.add( valores );
                    }
                    
                    if( ((String) ((Vector)propiedad[1]).get(0)).equals("RDF:nill") )
                        noFinalizado = false; 
                }
            }
        }while( noFinalizado );
        
        return statements;
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        // TODO Auto-generated method stub
        
    }

}
