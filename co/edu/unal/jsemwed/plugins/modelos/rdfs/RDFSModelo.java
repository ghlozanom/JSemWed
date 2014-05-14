/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.plugins.modelos.rdfs;

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
public class RDFSModelo implements IModelo {

	static String RESOURCE = "http://www.w3.org/2000/01/rdf-schema#Resource";
	static String CLASE = "http://www.w3.org/2000/01/rdf-schema#Class";
	static String PROPIEDAD = "http://www.w3.org/1999/02/22-rdf-syntax-ns#Property";
	
	String[] nombreElementos = {"Resource", "Clase", "Propiedad" };
	Vector[] elementos = new Vector[3];
	Vector resources = new Vector();
	Vector clases = new Vector();
	Vector propiedades = new Vector();
	String[] busquedas = {"busquedaSencilla"};
	String modeloSerializado = null;
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#getElementosPosibles()
	 */
	public String[] getElementosPosibles() {
		
		return nombreElementos;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#getLenguaje()
	 */
	public String getLenguaje() {

		return "RDFS";
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#ingresarElemento(java.lang.String, java.lang.String)
	 */
	public String ingresarElemento(String modelo2, String uri)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		
		IElemento elemento = (IElemento)Class.forName( modelo2 ).newInstance();
		elemento.setURI( uri );
		String tipoURI = elemento.getTipoURI();
		if( tipoURI.equals(RESOURCE) )
			resources.add( elemento );
		if( tipoURI.equals(CLASE) )
			clases.add( elemento );
		if( tipoURI.equals(PROPIEDAD) )
			propiedades.add( elemento );
		
		elemento.isModificado( true );
		return tipoURI;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#getElementos()
	 */
	public Vector[] getElementos() {
		
		elementos[0] = resources;
		elementos[1] = clases;
		elementos[2] = propiedades;
		
		return elementos;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#eliminarElemento(java.lang.String)
	 */
	public String eliminarElemento(String tipoElemento, String urlNodoSelecto) {

		Vector vectorSel = null;
		for ( int i = 0; i < nombreElementos.length; i++) {
			
			if( nombreElementos[i].equals( tipoElemento ) )
				vectorSel = elementos[i];
		}
		
		if( vectorSel == null )
			return null;
		
		Iterator it = vectorSel.iterator();
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
		vectorSel.remove( i );
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
		
		it = clases.iterator();
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
		
		it = propiedades.iterator();
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
				return;
				
			}
		}
		
		it = clases.iterator();
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
				return;
				
			}
		}
		
		it = propiedades.iterator();
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
				return;
				
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IModelo#adicionarOperacion(java.lang.String, java.lang.String)
	 */
	public void adicionarOperacion(String uri, String op) {
		// TODO Auto-generated method stub
		
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
		
		return modeloSerializado;
		
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
	public void eliminarOperacion(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

}
