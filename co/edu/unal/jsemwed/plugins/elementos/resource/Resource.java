/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.plugins.elementos.resource;

import java.util.Iterator;
import java.util.Vector;

import co.edu.unal.jsemwed.comun.IElemento;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Resource implements IElemento {

	static String resource = "http://www.w3.org/2000/01/rdf-schema#Resource";
	boolean modificado = true;
	String URI;
	Vector operaciones = new Vector();
	Vector propiedades = new Vector();

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#addPropiedad(java.lang.String[])
	 */
	public void addPropiedad(String[] propiedad) {
		
		Iterator it = propiedades.iterator();
		Vector nuevaPropiedad = null;
		
		while( it.hasNext() ){
			
			Object[] propAux = (Object[]) it.next();
			if( propAux[0].equals(propiedad[0]) )
			{
				nuevaPropiedad = (Vector) propAux[1];
				break;
			}
		}
		
		
		if( nuevaPropiedad == null ){
			
			Object[] propAux = new Object[2];
			propAux[0] = propiedad[0];
			Vector prop = new Vector();
			propAux[1] = prop;
			prop.add( propiedad[1] );
			propiedades.add( propAux );
			
		}
		
		else{
			
			nuevaPropiedad.add( propiedad[1] );
			
		}
			
		
	}
	
	public Resource(){
		
		operaciones.add( "IngresarStatement" );
		operaciones.add( "CrearContainer" );
		operaciones.add( "CrearCollection" );
		operaciones.add( "IngresarReification" );
		operaciones.add("IngresarTipoDeClase");
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#getOperaciones()
	 */
	public Vector getOperaciones() {
		
		return operaciones;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#getPropiedad()
	 */
	public Vector getPropiedades() {
		
		return propiedades;
		
	}
	/**
	 * @return Returns the resource.
	 */
	public String getTipoURI() {
		return resource;
	}
	/**
	 * @param resource The resource to set.
	 */
	public void setResource(String resource) {
		Resource.resource = resource;
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#isModificado(boolean)
	 */
	public void isModificado(boolean b) {
		
		modificado = b;
		
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#setURI(java.lang.String)
	 */
	public void setURI(String URI) {
		
		this.URI = URI;
		
	}
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#getURI()
	 */
	public String getURI() {
		
		return this.URI;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#removePropiedad(java.lang.String[])
	 */
	public void removePropiedad(String[] propiedad) {
		
		Iterator it = propiedades.iterator();
		boolean propiedadBorrada = false;
		int numValoresPropiedad = 0;
		int j = 0;
		
		while( it.hasNext() ){
			
			Object[] propAux = (Object[]) it.next();
			if( propAux[0].equals(propiedad[0]) )
			{
				Vector valores = (Vector) propAux[1];
				Iterator valoresIt = valores.iterator();
				int i = 0;
				while( valoresIt.hasNext() ){
					
					String valor = (String)valoresIt.next();
					if( valor.equals(propiedad[1]) ){
						propiedadBorrada = true;
						break;
					}
					i++;
					
				}
				if( propiedadBorrada ){
					valores.remove( i );
					numValoresPropiedad = valores.size();
					break;
				}
				
					
			}
			j++;
		}
		
		if( (propiedadBorrada) && (numValoresPropiedad == 0) ){
			propiedades.remove(j);
		}
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#addOperacion(java.lang.String)
	 */
	public void addOperacion(String op) {
		
		Iterator opIt = operaciones.iterator();
		while( opIt.hasNext() ){
			if( ((String)opIt.next()).equals(op) )
				return;
		}
		operaciones.add( op  );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IElemento#removeOperacion(java.lang.String)
	 */
	public void removeOperacion(String string) {
		
		Iterator opsIt = operaciones.iterator();
		int i=0;
		boolean hallado = false;
		
		while( opsIt.hasNext() ){
			
			if( ((String) opsIt.next()).equals( string ) ){
				hallado = true;
				break;
			}
			i++;
		}
		if( hallado )
			operaciones.remove( i );
	}
}
