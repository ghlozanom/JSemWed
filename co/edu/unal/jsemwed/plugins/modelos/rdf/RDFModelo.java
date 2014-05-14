/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.plugins.modelos.rdf;

import java.util.Iterator;
import java.util.Vector;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IModelo;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RDFModelo implements IModelo {

	String[] nombreElementos = {"Resource"};
	String[] busquedas = {"busquedaSencilla", "busquedaAbierta"};
	Vector[] elementos = new Vector[1];
	Vector resources = new Vector();
	String modeloSerializado = null;
	
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#getResources()
	 */
	public String[] getElementosPosibles() {
		
		return nombreElementos;
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#getLenguaj()
	 */
	public String getLenguaje() {
		
		return "RDF";
		
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#ingresarElemento(java.lang.String, java.lang.String)
	 */
	public String ingresarElemento(String modelo2, String uri) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		IElemento elemento = (IElemento)Class.forName( modelo2 ).newInstance();
		elemento.setURI( uri );
		resources.add( elemento );
		elemento.isModificado( true );
		return elemento.getTipoURI();
		//System.out.println(Resource.getURI());
		
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#getElementos()
	 */
	public Vector[] getElementos() {
		// TODO Auto-generated method stub
		elementos[0] = resources;
		return elementos;
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#eliminarElemento(java.lang.String)
	 */
	public String eliminarElemento( String tipoElemento, String urlNodoSelecto) {
		
		if( !(tipoElemento.equals(nombreElementos[0])) )
			return null;
		
		Iterator it = resources.iterator();
		IElemento el = null;
		int i = 0;
		boolean hallado = false;
		while( it.hasNext() ){
			
			el = (IElemento) it.next();
			if( el.getURI().equals(urlNodoSelecto) ){
				
				hallado = true;
				break;
				
			}
			i++;
		}
		if( !hallado )
			return null;
		resources.remove( i );
		return el.getTipoURI();
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#ingresarStatement(java.lang.String[])
	 */
	public void ingresarStatement(String[] statement) {
		
		Iterator it = resources.iterator();
		while ( it.hasNext() )
		{
			IElemento elemento = (IElemento) it.next();
			String nombreElemento = elemento.getURI();
			if( elemento.getURI().equals( statement[0]) ){
				
				String predicate = statement[1];
				String object = statement[2];
				String[] propiedad = new String[2];
				propiedad[0] = predicate;
				//Revisar si la propiedad es un literal
				propiedad[1] = object;
				elemento.addPropiedad( propiedad );
				elemento.addOperacion( "EliminarStatement");
				return;
			}
			
		}
		
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#eliminarStatement(java.lang.String[])
	 */
	public void eliminarStatement(String[] statement) {
		
		Iterator it = resources.iterator();
		while( it.hasNext() )
		{
			IElemento elemento = (IElemento) it.next();
			String nombreElemento = elemento.getURI();
			if( elemento.getURI().equals( statement[0]) ){
				
				String predicate = statement[1];
				String object = statement[2];
				String[] propiedad = new String[2];
				propiedad[0] = predicate;
				propiedad[1] = object;
				elemento.removePropiedad( propiedad );
				if( elemento.getPropiedades().size()==0 )
					elemento.removeOperacion("EliminarStatement");
				
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#adicionarOperacion(java.lang.String, java.lang.String)
	 */
	public void adicionarOperacion(String uri, String op) {
		
		Iterator elemIt = resources.iterator();
		
		while( elemIt.hasNext() ){
			
			IElemento elem = (IElemento)elemIt.next();
			if(elem.getURI().equals(uri)){
				elem.addOperacion( op );
				return;
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#setModeloSerializado(java.lang.String)
	 */
	public void setModeloSerializado(String modeloSerializado) {
		
		this.modeloSerializado = modeloSerializado;
		
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#getModeloSerializado()
	 */
	public String getModeloSerializado() {
		
		return this.modeloSerializado;
		
	}
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IModelo#getBusquedas()
     */
    public String[] getBusquedas() {
        
        return busquedas;
    }
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#eliminarOperacion(java.lang.String, java.lang.String)
	 */
	public void eliminarOperacion(String uri, String op) {
		
		Iterator elemIt = resources.iterator();
		
		while( elemIt.hasNext() ){
			
			IElemento elem = (IElemento)elemIt.next();
			if(elem.getURI().equals(uri)){
				elem.removeOperacion( op );
				return;
			}
		}
	}

}
