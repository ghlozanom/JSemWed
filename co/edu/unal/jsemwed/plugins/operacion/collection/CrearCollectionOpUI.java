/*
 * Created on 06-may-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.collection;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author Gabriel
 *
 */
public class CrearCollectionOpUI
extends OperacionUI
implements IOperacionUI, ActionListener, ListSelectionListener {

    private JDialog crearCollectionDialog;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JTable tablaLista;
    private Vector statements;
    private JComboBox predicateCB;
    private DefaultTableModel tm;
    private ListSelectionModel lsl;
    private JButton arribaButton;
    private JButton abajoButton;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.plugins.operacion.OperacionUI#getStatement()
     */
    public Vector getStatement() {
        
		crearCollectionDialog = new JDialog( this.ventanaPrincipal,
				labels.getString("agregarElementoLista"), true ); 
		JPanel dialogPanel = new JPanel( new BorderLayout() );
		
		JPanel predicatePanel = new JPanel();
		JLabel predicateLabel = new JLabel( labels.getString("predicado"));
		Vector nombreElementos = new Vector();
		Iterator it = elementos.iterator();
		while( it.hasNext() ){
		    nombreElementos.add( (String)(((IElemento) it.next()).getURI()));
		}
		predicateCB = new JComboBox(  nombreElementos );
		predicatePanel.add( predicateLabel );
		predicatePanel.add( predicateCB );
		
		JPanel botonesPanel = new JPanel();
		aceptarButton = new JButton(labels.getString("Aceptar"));
		aceptarButton.setEnabled( false );
		aceptarButton.addActionListener( this );
		cancelarButton = new JButton( labels.getString("Cancelar"));
		cancelarButton.addActionListener( this );
		JButton ayudaButton = manejadorAyuda.getBotonAyuda("semantic_web_modelos_rdf_rdf_collections_htm");
		botonesPanel.add( aceptarButton );
		botonesPanel.add( cancelarButton );
		botonesPanel.add( ayudaButton );
		
		JPanel listaPanel = new JPanel( new BorderLayout() );
		
		JPanel botonesListaPanel = new JPanel (  );
		agregarButton = new JButton( labels.getString("Agregar"));
		agregarButton.addActionListener( this );
		eliminarButton = new JButton( labels.getString("Eliminar"));
		eliminarButton.setEnabled( false );
		eliminarButton.addActionListener( this );
		arribaButton = new JButton( new ImageIcon("images/Up16.gif"));
		arribaButton.addActionListener( this );
		arribaButton.setEnabled( false );
		abajoButton = new JButton( new ImageIcon("images/Down16.gif"));
		abajoButton.addActionListener( this );
		abajoButton.setEnabled( false );
		botonesListaPanel.add( agregarButton );
		botonesListaPanel.add( eliminarButton );
		botonesListaPanel.add( arribaButton );
		botonesListaPanel.add( abajoButton );
		
		tablaLista = new JTable();
		lsl = tablaLista.getSelectionModel();
		lsl.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		lsl.addListSelectionListener( this );
		tm = (DefaultTableModel) tablaLista.getModel();
		tm.addColumn( "Blank Node" );
		tm.addColumn( "Object");
		
		listaPanel.setBorder( BorderFactory.createTitledBorder("Datos") );
		listaPanel.add( botonesListaPanel, BorderLayout.NORTH );
		listaPanel.add( new JScrollPane(tablaLista), BorderLayout.CENTER);
		
		dialogPanel.add( predicatePanel,BorderLayout.NORTH );
		dialogPanel.add( listaPanel, BorderLayout.CENTER );
		dialogPanel.add( botonesPanel, BorderLayout.SOUTH );
		
		crearCollectionDialog.getContentPane().add( dialogPanel );
		crearCollectionDialog.setSize(600,300);
		crearCollectionDialog.setResizable( false );
		crearCollectionDialog.setLocationRelativeTo(this.ventanaPrincipal);
		crearCollectionDialog.setVisible( true );
		
        return statements;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals(aceptarButton) )
        {
            statements = new Vector();
            
            String[] statement = new String[3];
            statement[0] = uriNodoSelecto;
            statement[1] = predicateCB.getSelectedItem().toString();
            statement [2] = (String)tm.getValueAt( 0,0 );
            statements.add( statement );
            
            int rows = tm.getRowCount();
            for( int i = 0; i < (rows - 1); i++ ){
                
                statement = new String[3];
                statement[0] = (String)tm.getValueAt(i,0);
                statement[1] = "http://www.w3.org/1999/02/22-rdf-syntax-ns#first";
                statement[2] = (String)tm.getValueAt(i,1);
                statements.add( statement );
                
                statement = new String[3];
                statement[0] = (String)tm.getValueAt(i,0);
                statement[1] = "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest";
                statement[2] = (String)tm.getValueAt((i + 1),0);
                statements.add( statement );
                
            }
            crearCollectionDialog.setVisible( false );
        }else if( arg0.getSource().equals(agregarButton) )
        {
            AgregarElementoListaDialog aeld = 
                new AgregarElementoListaDialog(crearCollectionDialog,elementos,labels);
            String[] datos = aeld.getDatos();
            if( datos != null )
                agregarTabla( datos );
        }else if( arg0.getSource().equals(cancelarButton) ){
            
            statements = null;
            crearCollectionDialog.setVisible( false );
        }else if( arg0.getSource().equals( eliminarButton ) ){
            
            if( tablaLista.getSelectedRow() == (tablaLista.getRowCount() -1 ) && 
                    (aceptarButton.isEnabled( )) ){
                aceptarButton.setEnabled( false );
                agregarButton.setEnabled( true );
            }
            tm.removeRow( tablaLista.getSelectedRow() );
            if( tm.getRowCount() == 0 )
                eliminarButton.setEnabled( false );
            
        }else if( arg0.getSource().equals( arribaButton) ){
            
            tm.moveRow( tablaLista.getSelectedRow(),tablaLista.getSelectedRow(),
                    tablaLista.getSelectedRow() - 1 );
            
        }else if( arg0.getSource().equals( abajoButton) ){
            
            tm.moveRow( tablaLista.getSelectedRow(),tablaLista.getSelectedRow(),
                    tablaLista.getSelectedRow() + 1 );
        }
        
    }

    /**
     * @param datos
     */
    private void agregarTabla(String[] datos) {
        
        if( datos.length == 1 ){
            
            aceptarButton.setEnabled( true );
            agregarButton.setEnabled( false );
            
        }
        tm.addRow( datos );
        
    }

    /* (non-Javadoc)
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent arg0) {
        
        if( arg0.getValueIsAdjusting() )
        {
            eliminarButton.setEnabled( true );
            arribaButton.setEnabled( true );
            abajoButton.setEnabled( true );
            int filaSel = tablaLista.getSelectedRow();
            if( filaSel == 0 || (filaSel == (tablaLista.getRowCount() -1)
                    && aceptarButton.isEnabled()) )
                arribaButton.setEnabled( false );
            if( filaSel == (tablaLista.getRowCount() - 2) || filaSel == (tablaLista.getRowCount() -1))
                abajoButton.setEnabled( false );            
        }
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        // TODO Auto-generated method stub
        
    }


}
