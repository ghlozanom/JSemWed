/*
 * Created on 09-abr-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.reification;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author Gabriel
 *
 */
public class IngresarReificationOpUI extends OperacionUI implements
        IOperacionUI, ItemListener, ActionListener {

    private JDialog irDialog;
    private JTextField nuevoStmtPredicateTF;
    private JTextField nuevoStmtObjectTF;
    private IElemento elementoSubjt;
    private JComboBox subjectCB;
    private JComboBox objectCB;
    private JComboBox predicateCB;
    private JButton nuevoStmtAgregarButton;
    private DefaultTableModel tm;
    private JButton eliminarButton;
    private JTable tablaStmts;
    private JButton botonesVentanaAceptar;
    private JButton botonesVentanaCancelar;
    private Vector statements;

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#getStatement()
     */
    public Vector getStatement() {
        
        irDialog = new JDialog( ventanaPrincipal, labels.getString("ingresarReification"),
                true );
        irDialog.getContentPane().setLayout( new BorderLayout() );        
        
        JPanel statementPanel = new JPanel( new GridLayout(3,1) );
        JPanel subjectPanel = new JPanel();
        JLabel subjectLabel = new JLabel( labels.getString("sujeto") );
        subjectLabel.setPreferredSize( new Dimension(70,30) );
        Vector elemTemp = new Vector();
        Iterator it = elementos.iterator();
        while( it.hasNext() ){
            
            elemTemp.add( ((IElemento)it.next()).getURI() );
        }
        subjectCB = new JComboBox( elemTemp );
        subjectCB.addItemListener( this );
        elementoSubjt = (IElemento)elementos.get( subjectCB.getSelectedIndex() );
        subjectCB.setPreferredSize( new Dimension(300,30) );
        subjectPanel.add( subjectLabel );
        subjectPanel.add( subjectCB );
        
        JPanel predicatePanel = new JPanel();
        JLabel predicateLabel = new JLabel( labels.getString( "Propiedad") );
        predicateLabel.setPreferredSize( new Dimension(70,30) );
        Vector propiedadesVector = new Vector();
        Iterator it2 = elementoSubjt.getPropiedades().iterator();
        while( it2.hasNext() ){
            
            Object[] propiedad = (Object[]) it2.next();
            propiedadesVector.add( propiedad[0] );
            
        }
        predicateCB = new JComboBox( propiedadesVector );
        predicateCB.addItemListener( this );
        Vector objectsVector = (Vector) ((Object[])elementoSubjt.getPropiedades().get( predicateCB.getSelectedIndex() ))[1];
        predicateCB.setPreferredSize( new Dimension(300,30) );
        predicatePanel.add( predicateLabel );
        predicatePanel.add( predicateCB );
        
        JPanel objectPanel = new JPanel();
        JLabel objectLabel = new JLabel( labels.getString("objeto") );
        objectLabel.setPreferredSize( new Dimension(70,30) );
        objectCB = new JComboBox(  objectsVector );
        objectCB.setPreferredSize( new Dimension(300,30) );
        objectPanel.add( objectLabel );
        objectPanel.add( objectCB );
        
        statementPanel.add( subjectPanel );
        statementPanel.add( predicatePanel );
        statementPanel.add( objectPanel );
        
        
        JPanel statementsPanel = new JPanel( new BorderLayout() );
        GridBagLayout layout = new GridBagLayout();
        JPanel tablaPanel = new JPanel( layout );
        GridBagConstraints constraints = new GridBagConstraints();
        tablaPanel.setPreferredSize( new Dimension(400, 250) );
        tablaStmts = new JTable();
        tablaStmts.setPreferredScrollableViewportSize( new Dimension(400,200) );
        tm = (DefaultTableModel)tablaStmts.getModel();
        tm.addColumn( labels.getString("Propiedad") );
        tm.addColumn( labels.getString("objeto") );
        addComponent( new JScrollPane(tablaStmts),1,1,1,1,constraints,layout,tablaPanel );
        JPanel manejarStmtsPanel = new JPanel( new GridLayout(2,1) );
        eliminarButton = new JButton( labels.getString("Eliminar") );
        eliminarButton.addActionListener( this );
        JPanel eliminarPanel = new JPanel();
        eliminarButton.setPreferredSize( new Dimension (100, 30 ) );
        GridBagLayout eliminarLayout = new GridBagLayout();
        eliminarButton.setLayout( eliminarLayout );
        addComponent( eliminarButton, 1, 1, 1, 1, constraints, eliminarLayout, eliminarPanel );
        manejarStmtsPanel.add( eliminarPanel );
        JPanel nuevoStmtPanel = new JPanel();
        JLabel nuevoStmtPredicateLabel = new JLabel( labels.getString("Propiedad") );
        nuevoStmtPredicateLabel.setPreferredSize( new Dimension( 60,30) );
        nuevoStmtPredicateTF = new JTextField( 15 );
        JLabel nuevoStmtObjectLabel = new JLabel ( labels.getString( "objeto" ) );
        nuevoStmtObjectLabel.setPreferredSize( new Dimension ( 40, 30 ) );
        nuevoStmtObjectTF = new JTextField( 15 );
        nuevoStmtAgregarButton = new JButton( labels.getString("Agregar") );
        nuevoStmtAgregarButton.addActionListener( this );
        nuevoStmtPanel.add( nuevoStmtPredicateLabel );
        nuevoStmtPanel.add( nuevoStmtPredicateTF );
        nuevoStmtPanel.add( nuevoStmtObjectLabel );
        nuevoStmtPanel.add( nuevoStmtObjectTF );
        nuevoStmtPanel.add( nuevoStmtAgregarButton );
        manejarStmtsPanel.add( nuevoStmtPanel );
        
        statementsPanel.add( tablaPanel, BorderLayout.CENTER );
        statementsPanel.add( manejarStmtsPanel, BorderLayout.SOUTH );
        
        JPanel botonesVentanaPanel = new JPanel();
        botonesVentanaAceptar = new JButton( labels.getString("Aceptar"));
        botonesVentanaAceptar.addActionListener( this );
        botonesVentanaCancelar = new JButton( labels.getString("Cancelar") );
        botonesVentanaCancelar.addActionListener( this );
        JButton ayudaButton = manejadorAyuda.getBotonAyuda( "semantic_web_modelos_rdf_rdf_reification_htm");
        botonesVentanaPanel.add( botonesVentanaAceptar );
        botonesVentanaPanel.add( botonesVentanaCancelar );
        botonesVentanaPanel.add( ayudaButton );
        
        irDialog.getContentPane().add( statementPanel, BorderLayout.NORTH );
        irDialog.getContentPane().add( statementsPanel, BorderLayout.CENTER );
        irDialog.getContentPane().add( botonesVentanaPanel, BorderLayout.SOUTH );
        
        irDialog.setSize( 600, 500 );
        irDialog.setLocationRelativeTo( ventanaPrincipal );
        irDialog.setVisible( true );
        
        return statements;
    }
    
    private void addComponent(Component component, int row, int column, int width, int height
            , GridBagConstraints constraints, GridBagLayout layout, Container cont ) {
        
        constraints.gridx = column;
        constraints.gridy = row;
        
        constraints.gridwidth = width;
        constraints.gridheight = height;
        
        layout.setConstraints( component, constraints );
        cont.add( component );   
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent arg0) {
        
        if( arg0.getSource().equals( subjectCB) ){
            
            elementoSubjt = (IElemento)elementos.get( subjectCB.getSelectedIndex() );
            Vector propiedadesVector = new Vector();
            Iterator it2 = elementoSubjt.getPropiedades().iterator();
            while( it2.hasNext() ){
                
                Object[] propiedad = (Object[]) it2.next();
                propiedadesVector.add( propiedad[0] );
                
            }
            predicateCB.setModel(new DefaultComboBoxModel( propiedadesVector));
            if( propiedadesVector.size() == 0 ){
                
                predicateCB.setEnabled( false );
                objectCB.setModel( new DefaultComboBoxModel() );
                objectCB.setEnabled( false );
                
            }else{
                
	            predicateCB.setEnabled( true );
	            objectCB.setEnabled( true );
	            Vector objectsVector = (Vector) ((Object[])elementoSubjt.getPropiedades().get( predicateCB.getSelectedIndex() ))[1];
	            objectCB.setModel( new DefaultComboBoxModel( objectsVector ) );
	            
            }
            
        }//fin if
        else if( arg0.getSource().equals( predicateCB) ){
            
            Vector objectsVector = (Vector) ((Object[])elementoSubjt.getPropiedades().get( predicateCB.getSelectedIndex() ))[1];
            objectCB.setModel( new DefaultComboBoxModel( objectsVector ) );
            
        }
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals( nuevoStmtAgregarButton ) ){
            
            String predicate = nuevoStmtPredicateTF.getText();
            String object = nuevoStmtObjectTF.getText();
            if( !(predicate.equals("") || object.equals("")) ){
                
                String[] datos = {predicate,object};
                tm.addRow( datos );
                eliminarButton.setEnabled( true );
                
            }
            
        }else if( arg0.getSource().equals( eliminarButton ) ){
            /**
            if( tablaStmts.getSelectedRow() == tablaStmts.getRowCount() -1  ){
                aceptarButton.setEnabled( false );
                agregarButton.setEnabled( true );
            }**/
            int row = tablaStmts.getSelectedRow();
            if( row > -1 ){
                
	            tm.removeRow( tablaStmts.getSelectedRow() );
	            if( tm.getRowCount() == 0 )
	                eliminarButton.setEnabled( false );
	            
            }
            
        }else if( arg0.getSource().equals( botonesVentanaCancelar ) ){
            
            irDialog.setVisible( false );
            irDialog = null;
            
        }else if( arg0.getSource().equals( botonesVentanaAceptar ) ){
            
            statements = new Vector();
            
            int rows = tm.getRowCount();
            String[] statement = new String[3];
            statement[0] = subjectCB.getSelectedItem().toString();
            statement[1] = predicateCB.getSelectedItem().toString();
            statement[2] = objectCB.getSelectedItem().toString();
            
            statements.add( statement );
            
            for (int i = 0; i < rows; i++) {
                
                statement = new String[3];
                statement[0] = this.uriNodoSelecto;
                statement[1] = (String) tm.getValueAt( i,0);
                statement[2] = (String) tm.getValueAt( i, 1 );
                statements.add( statement );
            }
            
            irDialog.setVisible( false );
            
        }
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        // TODO Auto-generated method stub
        
    }

}
