/*
 * Created on 09-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;

import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;

/**
 * @author Gabriel
 *
 */
public abstract class OperacionUI implements IOperacionUI{

    protected JFrame ventanaPrincipal;
    protected String uriNodoSelecto;
    protected Vector elementos;
    protected ResourceBundle labels;
    protected Vector[] allElements;
    protected ManejadorAyuda manejadorAyuda;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setFrame(javax.swing.JFrame)
     */
    public void setFrame(JFrame ventanaPrincipal) {
        
        this.ventanaPrincipal = ventanaPrincipal;
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public abstract Vector getStatement();

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setNodoSelecto(java.lang.String)
     */
    public void setNodoSelecto(String urlNodoSelecto) {
        
        this.uriNodoSelecto = urlNodoSelecto;
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setElementos(java.util.Vector)
     */
    public void setElementos(Vector vector) {
        
        this.elementos = vector;
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setLabels(java.util.ResourceBundle)
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;
        
    }
    
    /**
     * @return Returns the uriNodoSelecto.
     */
    public String getUriNodoSelecto() {
        return uriNodoSelecto;
    }
    /**
     * @param uriNodoSelecto El uriNodoSelecto a configurar.
     */
    public void setUriNodoSelecto(String uriNodoSelecto) {
        this.uriNodoSelecto = uriNodoSelecto;
    }
    /**
     * @return Returns the ventanaPrincipal.
     */
    public JFrame getVentanaPrincipal() {
        return ventanaPrincipal;
    }
    /**
     * @param ventanaPrincipal El ventanaPrincipal a configurar.
     */
    public void setVentanaPrincipal(JFrame ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
    /**
     * @return Returns the elementos.
     */
    public Vector getElementos() {
        return elementos;
    }
    /**
     * @return Returns the labels.
     */
    public ResourceBundle getLabels() {
        return labels;
    }
    
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        
        allElements = vector;
        
    }
    
    /**
     * @param manejadorAyuda
     */
    public void setManejadorAyuda(ManejadorAyuda manejadorAyuda){
        
        this.manejadorAyuda = manejadorAyuda;
        
    }
}
