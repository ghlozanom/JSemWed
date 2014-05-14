/*
 * Created on 26-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.impl;

import co.edu.unal.jsemwed.comun.IUI;
import co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.IVista;
import co.edu.unal.jsemwed.swEngine.ISWEngine;

/**
 * @author Gabriel
 *
 */
public class N3 implements IVista {
    
	static String formato = "N3";	//Representa el tipo de vista
	private IUI ui;
	private ISWEngine semWebEngine;
	IVista vista = null;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.IVista#setVista(co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.IVista)
     */
    public void setVista(IVista temp) {
        
        vista = temp;

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.IVista#getVista()
     */
    public IVista getVista() {
        
        return vista;
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.IVista#pinteModelo()
     */
    public void pinteModelo() {
        
		String modeloFormato = this.semWebEngine.getModelo( formato );
		ui.pinteModelo( modeloFormato, formato );

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.IVista#setSWEngine(co.edu.unal.jsemwed.swEngine.ISWEngine)
     */
    public void setSWEngine(ISWEngine swEngine) {
        
        this.semWebEngine = swEngine;

    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.controlador.CasosDeUso.vistas.IVista#setUI(co.edu.unal.jsemwed.comun.IUI)
     */
    public void setUI(IUI ui) {
        
        this.ui = ui;

    }

}
