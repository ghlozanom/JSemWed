/*
 * Created on 02-mar-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.busquedas.busquedaSencilla;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.unal.jsemwed.comun.IBusqueda;
import co.edu.unal.jsemwed.comun.IElemento;

/**
 * @author Gabriel
 *
 */
public class BusquedaSencilla implements IBusqueda, ActionListener {

    private JFrame frame;
    private Vector elementos;
    private JDialog busquedaDialog;
    private ResourceBundle labels;
    private String query;
    private JTextField subjectTF;
    private JTextField predicateTF;
    private JTextField objectTF;
    private JButton cancelarButton;
    private JButton buscarButton;
    
    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IBusqueda#getQuery()
     */
    public String getQuery() {
        
        busquedaDialog = new JDialog( frame, labels.getString("busqueda"), true );
        busquedaDialog.setSize(700, 300 );
        busquedaDialog.setResizable( false );
        busquedaDialog.setLocationRelativeTo( frame );
        
        JPanel busquedaPanel = new JPanel();
        busquedaPanel.setLayout( new BorderLayout() );
        
        JPanel elementosPanel = new JPanel();
        elementosPanel.setLayout( new GridLayout(3,1) );
        
        JPanel subjectPanel = new JPanel();
        subjectPanel.setBorder( 
                BorderFactory.createTitledBorder(labels.getString("sujeto")) );
        subjectTF = new JTextField( 30 );
        final JComboBox subjectCB = new JComboBox(elementos );
        subjectCB.addItemListener( new ItemListener(){

            public void itemStateChanged(ItemEvent arg0) {
                subjectTF.setText( subjectCB.getSelectedItem().toString() );
                
            }
            
        });
        JButton limpiarSubject = new JButton(labels.getString("limpiar"));
        limpiarSubject.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                subjectTF.setText("");
            }     
        });
        subjectPanel.add( subjectTF );
        subjectPanel.add( subjectCB );
        subjectPanel.add( limpiarSubject );
        
        JPanel predicatePanel = new JPanel();
        predicatePanel.setBorder( 
                BorderFactory.createTitledBorder(labels.getString("predicado")) );
        predicateTF = new JTextField( 30 );
        final JComboBox predicateCB = new JComboBox( elementos );
        predicateCB.addItemListener( new ItemListener(){

            public void itemStateChanged(ItemEvent arg0) {
                predicateTF.setText( predicateCB.getSelectedItem().toString() );
                
            }
            
        });
        JButton limpiarPredicate = new JButton(labels.getString("limpiar"));
        limpiarPredicate.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                predicateTF.setText("");
            }     
        });
        
        predicatePanel.add( predicateTF );
        predicatePanel.add( predicateCB );
        predicatePanel.add( limpiarPredicate );
        
        JPanel objectPanel = new JPanel();
        objectPanel.setBorder( 
                BorderFactory.createTitledBorder(labels.getString("objeto")) );
        objectTF = new JTextField( 30 );
        final JComboBox objectCB = new JComboBox( elementos );
        objectCB.addItemListener( new ItemListener(){

            public void itemStateChanged(ItemEvent arg0) {
                objectTF.setText( objectCB.getSelectedItem().toString() );
                
            }
            
        });
        JButton limpiarObject = new JButton(labels.getString("limpiar"));
        limpiarObject.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                objectTF.setText("");
            }     
        });
        objectPanel.add( objectTF );
        objectPanel.add( objectCB );
        objectPanel.add( limpiarObject );
        
        elementosPanel.add( subjectPanel );
        elementosPanel.add( predicatePanel );
        elementosPanel.add( objectPanel );
        
        JPanel botonesPanel = new JPanel();
        buscarButton = new JButton(labels.getString("buscar"));
        buscarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString("Cancelar"));
        cancelarButton.addActionListener( this );
        botonesPanel.add( buscarButton );
        botonesPanel.add( cancelarButton );
        
        busquedaPanel.add( elementosPanel, BorderLayout.CENTER );
        busquedaPanel.add( botonesPanel, BorderLayout.SOUTH );
        
        busquedaDialog.getContentPane().add( busquedaPanel );
        
        busquedaDialog.setVisible( true );
        busquedaDialog = null;
        
        return query;
        
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IBusqueda#setJFrame(javax.swing.JFrame)
     */
    public void setJFrame(JFrame frame) {
        
        this.frame = frame;
    }

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IBusqueda#setElementos(java.util.Vector)
     */
    public void setElementos(Vector[] elementos) {
        
        Vector elementosVector = elementos[0];
        Iterator it = elementosVector.iterator();
        this.elementos = new Vector();
        
        while( it.hasNext() ){
            
            IElemento elementoAux = (IElemento)it.next();
            this.elementos.add( elementoAux.getURI() );
            
        }
        
    }

    /**
     * @param labels El labels a configurar.
     */
    public void setLabels(ResourceBundle labels) {
        this.labels = labels;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals(buscarButton) ){
            StringBuffer selectQuery = new StringBuffer();
            StringBuffer whereQuery = new StringBuffer();
            selectQuery.append( "SELECT " );
            whereQuery.append( "WHERE (" );
            
            boolean busquedainiciada = false;
            
            if( subjectTF.getText().equals("") ){
                
                selectQuery.append( "?" + labels.getString("sujeto") + " " );
                whereQuery.append(  "?" + labels.getString("sujeto") + " " );
                
            }else{
                
                whereQuery.append( "<"+ subjectTF.getText() + "> " );
                
            }
            if( predicateTF.getText().equals("") ){
                
                selectQuery.append( "?" + labels.getString("predicado") + " " );
                whereQuery.append ( "?" + labels.getString("predicado") + " " );
                
            }else{
                
                whereQuery.append( "<"+ predicateTF.getText() + "> " );
                
            }
            if( objectTF.getText().equals("") ){
                
                selectQuery.append( "?" + labels.getString("objeto") + " " );
                whereQuery.append ( "?" + labels.getString("objeto") + ")" );
                
            }else{
                
                whereQuery.append( "<"+ objectTF.getText() + ">)" );
                
            }
            
            query = selectQuery.toString() + whereQuery.toString();
            //System.out.println(query);
        }else if( arg0.getSource().equals( cancelarButton ) ){
            busquedaDialog.setVisible( false );
        }
        busquedaDialog.setVisible( false );
    }
}
