/*
 * Created on 20-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.swEngine.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

import co.edu.unal.jsemwed.swEngine.ISWEngine;

import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.arp.URI;
import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdql.Query;
import com.hp.hpl.jena.rdql.QueryEngine;
import com.hp.hpl.jena.rdql.QueryExecution;
import com.hp.hpl.jena.rdql.QueryResults;
import com.hp.hpl.jena.rdql.ResultBinding;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * @author Gabriel
 *
 */
public class JenaISWEngineImpl implements ISWEngine{

	private String nombreModelo = "TEMPORAL";
	private Model modelo;
	private Resource r;
	private Vector resources;
	
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.swEngine.ISWEngine#crearModelo()
	 */
	public String crearModelo() {

		modelo = ModelFactory.createDefaultModel();
		resources = new Vector();
		return nombreModelo;
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.swEngine.ISWEngine#ingreseResource(java.lang.String)
	 */
	public void ingreseResource(String uri) {

		//TODO Revisar que el modelo haya sido creado
		Resource r = modelo.createResource( uri );
		resources.add( r );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.controlador.swEngine.ISWEngine#getResources()
	 */
	public Object[] getResources() {

	    Object[] recursos = new Object[ resources.size() ];
	    
	    for( int i = 0; i < resources.size(); i++ ){
	        
	        r = (Resource) resources.get(i );
	        //recursos[i] = r.getURI();
	        StmtIterator iter = r.listProperties();
	        int numPropiedades = 0;
	        while( iter.hasNext() ){
	            
	            numPropiedades++;
	            iter.next();
	            
	        }
	        
	        if( numPropiedades == 0 )//no tiene propiedades ingresadas
	        {
	            recursos[i] = r.getURI();
	        }else {
	            Object[] propiedades = new Object[ ++numPropiedades ];
	            propiedades[0] = r.getURI();
	            iter = r.listProperties();
	            for (int j = 1; j < numPropiedades; j++) {
	                
	                Statement st = iter.nextStatement();
	                Object[] propiedad = new Object[2];
	                propiedad[0] = st.getPredicate().toString();
	                Object[] objeto = new Object[1];
	                objeto[0] = st.getObject().toString();
	                propiedad[1] = objeto;
	                propiedades[j] = propiedad;
                    
                }
	            recursos[i] = propiedades;
	        }
	    }//fin for
	    return recursos;
	    
	    
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#ingresarStatement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void ingresarStatement(String subject, String predicate, String object) {
		
		Resource resource = modelo.getResource( subject );
		//Resource subject2 = modelo.createResource( new AnonId(object) );
		
		if( object.startsWith("\""))
		{
		    int indice = object.indexOf("^^" );
		    if( indice != -1 ){
		        //existe un typed lyteral
		        String tipo = object.substring( indice + 2, object.length()   );
		        String valor = object.substring( 1, indice - 1 );
		        
		        if( tipo.equals("boolean") ){
		            
		            Boolean valorObj = new Boolean( valor );
		            Literal lit = modelo.createTypedLiteral( valorObj );
		            resource.addProperty( modelo.createProperty( predicate ),
		                    lit );
		            return;
		        }
		        if( tipo.equals("integer") ){
		            Integer valorObj = new Integer(valor);
		            Literal lit = modelo.createTypedLiteral( valorObj );
		            resource.addProperty( modelo.createProperty( predicate ), lit);
		            return;
		        }
		        
		    }else {
			    String object2 = object.substring( 1, object.length() - 1 );
			    resource.addProperty( modelo.createProperty(predicate), object2 );
		    }
		}
		else if( subject.startsWith("_")){//cuando el sujeto es un blank node
		    resource = modelo.createResource( new AnonId(subject.substring(2,subject.length())));
		    Resource objectResource = null;
		    if( object.startsWith("_")){//si el objeto es un blank node
		        objectResource = modelo.createResource( new AnonId(object.substring(2, object.length())) );
		    }else{
		        objectResource = modelo.getResource( object );
		    }
		    resource.addProperty( modelo.createProperty(predicate), objectResource );
		    
		}else {
		    Resource objectResource = null;
		    if( object.startsWith("_")){//el object es un blank node
		        objectResource = modelo.createResource( new AnonId(object.substring(2, object.length())) );
		    }else{
		        objectResource = modelo.getResource( object );
		    }
		    resource.addProperty( modelo.createProperty(predicate),
		            objectResource );
		}
		    
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#getModelo(java.lang.String)
	 */
	public String getModelo(String modelo) {
		
		OutputStream os = new ByteArrayOutputStream();
		this.modelo.write( os, modelo );
		return os.toString();
		
	}

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.swEngine.ISWEngine#guardarModelo(java.io.OutputStream)
     */
    public void guardarModelo(OutputStream os) {
        
        modelo.write( os );
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.swEngine.ISWEngine#abrirModelo(java.io.InputStream)
     */
    public boolean abrirModelo(InputStream is) {
        
        //crearModelo();
        try{
            modelo.read( is, "" );
        }catch ( Exception e ){
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.swEngine.ISWEngine#cargeResources()
     */
    public Vector cargeResources() {
        
        StmtIterator i = modelo.listStatements();
        Vector recursos = new Vector();
        while( i.hasNext() ){
            
            Statement st = i.nextStatement();
            resources.add( st.getSubject() );
            recursos.add( st.getSubject().toString());
        }
        
        return recursos;
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.swEngine.ISWEngine#elimineModelo()
     */
    public void elimineModelo() {
        
        modelo = null;
        resources = null;
        
    }

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#crearModelo(java.lang.String)
	 */
	public void crearModelo(String lenguaje) {
		
		if (lenguaje.equals("RDF") )
		{
			//Crea modelo básico RDF
			modelo = ModelFactory.createDefaultModel();
			resources = new Vector();
			return;

		}
		
		if( lenguaje.equals("RDFS") )
		{
			
			modelo = ModelFactory.createOntologyModel( ProfileRegistry.RDFS_LANG );
			resources = new Vector();
			return;
			
		}
		
		if( lenguaje.equals("OWLFULL"))
		{
			
			modelo = ModelFactory.createOntologyModel( ProfileRegistry.OWL_LANG );
			resources = new Vector();
			return;
			
		}
			
		if( lenguaje.equals("OWLLITE"))
		{
			
			modelo = ModelFactory.createOntologyModel( OntModelSpec.OWL_LITE_MEM_RULES_INF, null );
			resources = new Vector();
			return;
			
		}
		
		if( lenguaje.equals("OWLDL") )
		{
			
			modelo = ModelFactory.createOntologyModel( ProfileRegistry.OWL_DL_LANG);
			resources = new Vector();
			return;
			
		}
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#ingreseResource(java.lang.String, java.lang.String)
	 */
	public void ingreseResource(String tipo, String uri) throws Exception {
	    
	    if( URI.isWellFormedAddress(uri) )
	        throw new Exception();
	    Resource r = null;
	    if( uri.startsWith("_") )// es blank node
	    
	        r = modelo.createResource( new AnonId(uri.substring(2,uri.length())) );
	        
	    else
	        
	        r = modelo.createResource( uri );
		
		r.addProperty( RDF.type, ResourceFactory.createResource(tipo) );

		//modelo.write( System.out );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#elimineElemento(java.lang.String)
	 */
	public void elimineElemento(String tipo, String urlNodoSelecto) {
		
		Resource r = modelo.getResource( urlNodoSelecto );
		r.removeProperties();
		modelo.write( System.out );
		
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#elimineStatement(java.lang.String[])
	 */
	public void elimineStatement(String[] statement) {
	
	    StmtIterator  stmtIterator= null;
	    
	    if( statement[2].startsWith("\"")){
	        
	        String object2 = statement[2].substring( 1, statement[2].length() - 1 );
	        stmtIterator = modelo.listStatements(
	                modelo.getResource( statement[0]),
	                modelo.getProperty( statement[1]),
	                object2 );
	        
	    }else{
	    
			stmtIterator = modelo.listStatements(
					modelo.getResource(statement[0]), modelo.getProperty(statement[1]),
					modelo.getResource(statement[2]));
	    }
		
		modelo.remove( stmtIterator );

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#setModelo(java.lang.String)
	 */
	public void setModelo(String modeloSerializado) {
		
		modelo.read( new ByteArrayInputStream( modeloSerializado.getBytes() ), "" );
		
	}

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.swEngine.ISWEngine#realizarBusqueda(java.lang.String)
     */
    public Vector realizarBusqueda(String query) {
       
        Query query2 = new Query( query );
        query2.setSource( modelo );
        QueryExecution qe = new QueryEngine( query2 );
        
        QueryResults results = qe.exec();
        List variables = query2.getResultVars();
        Iterator varIt = variables.iterator();
        Vector variablesVector = new Vector();
        
        while( varIt.hasNext() ){
            variablesVector.add( varIt.next().toString() );
        }
        ListIterator it = null;
        //Iterator iter = results;
        
        
        
        Vector resultadoVector = new Vector();
        
        int i = 0;
       // for( iter = results; iter.hasNext(); ){
        while( results.hasNext() ) {
            Vector resultado = new Vector();
            ResultBinding res = (ResultBinding)results.next();
            
            variables = query2.getResultVars();
            it = variables.listIterator();
            
            while( it.hasNext() ){
                resultado.add( res.get((String)it.next()));
            }
            resultadoVector.add( resultado );
            
        }
        results.close();
        Vector vectorFinal = new Vector();
        vectorFinal.add( variablesVector );
        vectorFinal.add( resultadoVector );
        
        return vectorFinal;
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.swEngine.ISWEngine#getPrefixes()
     */
    public Map getPrefixes() {
        
        return modelo.getNsPrefixMap();
        
    }

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#adicionarQName(java.lang.String[])
	 */
	public void adicionarQName(String[] name) {
		
		modelo.setNsPrefix( name[0], name[1] );
		
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.swEngine.ISWEngine#eliminarQName(java.lang.String)
	 */
	public void eliminarQName(String name) {
		
		modelo.removeNsPrefix( name );
		
	}

}
