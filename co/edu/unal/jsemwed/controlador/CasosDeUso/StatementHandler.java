/*
 * Created on 20-dic-2004
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.CasosDeUso;

import co.edu.unal.jsemwed.comun.IUI;
import co.edu.unal.jsemwed.swEngine.ISWEngine;

/**
 * @author Gabriel
 *
 */
public class StatementHandler {

    /**
     * @param swEngine El swEngine a configurar.
     */
    public void setSwEngine(ISWEngine swEngine) {
        this.swEngine = swEngine;
    }
    /**
     * @param ui El ui a configurar.
     */
    public void setUi(IUI ui) {
        this.ui = ui;
    }
	private ISWEngine swEngine;
	private IUI ui;

	
	/**
	 * @param uri
	 */
	public void ingreseResource(String uri) {
		
		//Ingresa el Resource identificado por el URI
		swEngine.ingreseResource( uri );
		
		//Obtiene el nuevo conjunto de Resources para pintarlos
		//Object[] res = swEngine.getResources();
		
		//ui.pinteResources( res );
		
	}


	/**
	 * @param subject
	 * @param predicate
	 * @param object
	 */
	public void ingresarStatement(String subject, String predicate, String object) {
		
		swEngine.ingresarStatement( subject, predicate, object );
		
		//Obtiene el nuevo conjunto de Resources para pintarlos
		Object[] res = swEngine.getResources();
		
		//ui.pinteResources( res );
		
	}

}
