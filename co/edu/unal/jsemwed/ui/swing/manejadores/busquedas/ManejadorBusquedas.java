/*
 * Created on 02-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.busquedas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import co.edu.unal.jsemwed.comun.IBusqueda;
import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.ui.swing.manejadores.toolbar.ManejadorToolbar;


/**
 * Clase que administra las búsquedas de la aplicacion
 * 
 * @author Gabriel
 *
 */
public class ManejadorBusquedas implements ItemListener, ActionListener {

    private String[] busquedas;
    private String busquedaSeleccionada;
    private Component ventana;
    private JComboBox busquedasCB;
    private ResourceBundle labels;
    private ResourceBundle busquedasBundle = 
        ResourceBundle.getBundle("co/edu/unal/jsemwed/plugins/busquedas/busquedas");
    private Vector[] elementos;
    private IControlador controlador;
    private JPanel busquedasPanel = null;
    private ManejadorToolbar manejadorToolbar;
    private int numBusqueda = 1; 	//Lleva la cuenta de las busquedas que se han realizados
    
    public ManejadorBusquedas( ){
        
        
    }

    /**
     * Configura las busquedas factibles en el modelo.
     * 
     * @param busquedas2 arreglo con el nombre de las busquedas
     * 
     */
    public void setBusquedas(String[] busquedas2) {
        
        this.busquedas = busquedas2;
        
    }

    /**
     * Configura el componente que muestra las busquedas posibles.
     * 
     * @return Componente gráfico de las busquedas
     */
    public Component getBusquedasComponent() {
        
        //Si este modelo no tiene busquedas definidas
        if( busquedas == null )
            return new JPanel();
        
        busquedasPanel = new JPanel();
        busquedasCB = new JComboBox( busquedas );
        busquedaSeleccionada = busquedasCB.getSelectedItem().toString();
        busquedasCB.addItemListener( this );
        JButton busquedasButton = new JButton(labels.getString( "buscar" ));
        busquedasButton.addActionListener( this );
        busquedasPanel.add( busquedasCB );
        busquedasPanel.add( busquedasButton );
        
        return busquedasPanel;
        
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent arg0) {
       
        busquedaSeleccionada = busquedasCB.getSelectedItem().toString();
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        try {
            IBusqueda busqueda = 
                (IBusqueda)Class.forName( busquedasBundle.getString(busquedaSeleccionada)).newInstance();
            
            busqueda.setJFrame( (JFrame) ventana );
            busqueda.setElementos( elementos );
            busqueda.setLabels( labels );
            String query = busqueda.getQuery();
            if( query != null)
                controlador.realizarBusqueda( query );
            else
                System.out.println("llego aca la busqueda");
            
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    /**
     * Configura la ventana principal
     * 
     * @param ventana
     */
    public void setVentanaPrincipal(Component ventana) {
        
        this.ventana = ventana;
        
    }

    /**
     * @param elementos
     */
    public void setElementos(Vector[] elementos) {
        
        this.elementos = elementos;
        
    }

    
    
    /**
     * @param controlador El controlador a configurar.
     */
    public void setControlador(IControlador controlador) {
        this.controlador = controlador;
    }

    /**
     * @param labels
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;
        
    }

    /**
     * Obtiene un componente con el resultado de una busqueda previamente
     * realizada.
     * 
     * @param resultado
     * @return Componente con el resultado de la busqueda.
     */
    public Component getResultadoComponent(Vector resultado,
            ActionListener al ) {
        
        JPanel resultadoPanel = new JPanel();
        resultadoPanel.setLayout( new BorderLayout() );
        
        JPanel botonesPanel = new JPanel();
        JButton cerrarButton = new JButton(labels.getString("cerrar"));
        cerrarButton.addActionListener( al );
        cerrarButton.setName( "busqueda" + numBusqueda );
        botonesPanel.add( cerrarButton );
        
        
        Iterator it = resultado.iterator();
        Vector variables = (Vector) it.next();
        Vector datos = (Vector) it.next();
  /**      String[] [] data = new String [ resultado.length ] 
                                        [resultado[0].size()];
        for (int i = 0; i < resultado.length; i++) {
            
            Vector variables = resultado[i];
            Iterator it = variables.iterator();
            int j=0;
            while( it.hasNext() ){
                Object[] variable = (Object[]) it.next();
                data[i][j] = variable[1].toString();
                j++;
            }
            
        }
        
        String[] nombreColumnas = new String[ resultado[0].size() ];
        Vector variables = resultado[0];
        Iterator it = variables.iterator();
        int i=0;
        while( it.hasNext() ){
            
            Object[] variable = (Object[]) it.next();
            nombreColumnas[i] = variable[0].toString();
            i++;
        }**/
        JTable tabla = new JTable(datos, variables);
        JScrollPane sp = new JScrollPane( tabla );
        
        resultadoPanel.setName( "busqueda" + numBusqueda );
        numBusqueda++;
        
        resultadoPanel.add( sp, BorderLayout.CENTER );
        resultadoPanel.add( botonesPanel, BorderLayout.NORTH );
        return resultadoPanel;
    }

    /**
     * Quita las busquedas de la pantalla
     * 
     */
    public void removerBusquedas() {
        
        if( busquedasPanel != null )
            manejadorToolbar.removeItem( busquedasPanel );
        
    }

    /**
     * @param manejadorToolbar
     */
    public void setManejadorToolbar(ManejadorToolbar manejadorToolbar) {
        
        this.manejadorToolbar = manejadorToolbar;
        
    }

}
