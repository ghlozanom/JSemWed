/*
 * Created on 14-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingenier�a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.modelo.qnames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;
import co.edu.unal.jsemwed.ui.swing.manejadores.modelo.qnames.dialogs.AdicionarQNameDialog;
import co.edu.unal.jsemwed.ui.swing.manejadores.modelo.qnames.dialogs.EditarQNameDialog;
import co.edu.unal.jsemwed.ui.swing.manejadores.modelo.qnames.dialogs.EliminarQNameDialog;

/**
 * Clase que administra los QNames de la aplicacion.
 * 
 * @author Gabriel
 *
 */
public class ManejadorQNames implements ActionListener {

    private Map qnames;
    private JButton adicionarQNameButton;
    private JButton editarQNameButton;
    private JButton eliminarQNameButton;
    private ResourceBundle labels;
    private IControlador controlador;
    private Component ventanaPrincipal;
	protected int selectedRow = -1;
	private boolean segundoClic = false;
	private JTable table;
    private ManejadorAyuda manejadorAyuda;

    /**
     * @param qnames
     */
    public void setPrefixes(Map qnames) {
        
        this.qnames = qnames;
        
    }

    /**
     * Obtiene el componente que permite administrar los QNames
     * 
     * @return componente para administrar QNames
     */
    public Component getQNamesComponent() {
        
        JPanel qNamesPanel = new JPanel( new BorderLayout() );
        qNamesPanel.setBorder(
                BorderFactory.createTitledBorder( "QNames" ));
        qNamesPanel.setPreferredSize( new Dimension( 300, 200 ) );
        
        JPanel botonesPanel = new JPanel();
        adicionarQNameButton = new JButton( new ImageIcon( "images/New16.gif") );
        adicionarQNameButton.addActionListener( this );
        editarQNameButton = new JButton( new ImageIcon("images/Edit16.gif"));
        editarQNameButton.addActionListener( this );
        eliminarQNameButton = new JButton( new ImageIcon("images/Delete16.gif"));
        eliminarQNameButton.addActionListener( this );
        JButton ayudaButton = manejadorAyuda.getBotonAyuda("semantic_web_conceptos_b_sicos_vocabularios_htm");
        botonesPanel.add( adicionarQNameButton );
        botonesPanel.add( editarQNameButton );
        botonesPanel.add( eliminarQNameButton );
        botonesPanel.add( ayudaButton );
        
        
        Vector nombreColumnas = new Vector();
        DefaultTableModel model = new DefaultTableModel( );
        model.addColumn( labels.getString("Prefijo") );
        model.addColumn( "URI" );
        Iterator it = qnames.keySet().iterator();
        while( it.hasNext() ){
            Vector fila = new Vector(2);
            String prefijo = (String) it.next();
            fila.add( prefijo );
            fila.add( qnames.get( prefijo ) );
            model.addRow( fila );
        }
        
        table = new JTable( model );
        table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
            	
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                if (lsm.isSelectionEmpty()) {
                    //...//no rows are selected
                } else {
                	if( segundoClic ){
                		selectedRow = lsm.getMinSelectionIndex();
                		System.out.println( table.getValueAt( selectedRow, 0 ));
                		segundoClic = false;
                	}
                	else
                		segundoClic = true;
                    //...//selectedRow is selected
                }
            }
        });

        JScrollPane tsp = new JScrollPane( table );
        qNamesPanel.add( botonesPanel, BorderLayout.NORTH );
        qNamesPanel.add( tsp, BorderLayout.CENTER );
        
        return qNamesPanel;
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        Object obj = arg0.getSource();
        
        if( obj.equals( adicionarQNameButton) ){
            
            AdicionarQNameDialog aqd = new AdicionarQNameDialog(
                    labels, ventanaPrincipal );
            String[] qName = aqd.getQName();
            aqd = null;
            if( qName != null )
            {
            	controlador.adicioneQName( qName );
            	//System.out.println( qName[0] + " " + qName[1] );
            	return;
            }
            System.out.println("retorno null!!");
            
        }else if ( obj.equals( eliminarQNameButton ) && selectedRow != -1){
        	
        	EliminarQNameDialog eqd = new EliminarQNameDialog(
        			labels, ventanaPrincipal );
        	
        	if( eqd.getDecision() )
        		controlador.elimineQName( (String)table.getValueAt( this.selectedRow,0 ),
        				(String)table.getValueAt(this.selectedRow, 1));
        	
        }else if( obj.equals( editarQNameButton ) && selectedRow != -1 ){
        	
        	EditarQNameDialog eqd = new EditarQNameDialog(
        			labels, ventanaPrincipal );
        	String qname[] = new String[2];
        	
        	qname[0] = (String) table.getValueAt( selectedRow, 0 );
        	qname[1] = (String) table.getValueAt( selectedRow, 1 );
        	
        	eqd.setQNameSeleccionado( qname );
        	String nuevoURI = eqd.getNuevoURI();
        	
        	if( nuevoURI != null )
        		controlador.editeQName( qname[0], qname[1], nuevoURI );
        }
            
        
    }

    /**
     * @param labels
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;
        
    }

    /**
     * @param controlador
     */
    public void setIControlador(IControlador controlador) {
        
        this.controlador = controlador;
        
    }

    /**
     * @param component
     */
    public void setVentanaPrincipal(Component component) {
        
        ventanaPrincipal = component;
        
    }

    /**
     * @param ayuda
     */
    public void setManejadorAyuda(ManejadorAyuda ayuda) {
        
        this.manejadorAyuda = ayuda;
        
    }

}
