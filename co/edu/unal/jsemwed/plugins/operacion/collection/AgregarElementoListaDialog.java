/*
 * Created on 06-may-2005
 *
 * Gabriel H. Lozano M.
 * Ingeniería de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.plugins.operacion.collection;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import co.edu.unal.jsemwed.comun.IElemento;

/**
 * @author Gabriel
 *
 */
public class AgregarElementoListaDialog implements ActionListener  {

    private Vector elementos;
    private JComboBox bnCB;
    private ResourceBundle labels;
    private JComboBox firstCB;
    private JRadioButton bnButton;
    private JComboBox bnCB2;
    private JRadioButton nillButton;
    private JDialog crearCollectionDialog;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private String blankNodeSelected;
    protected String firstNodeSelected;
    private String[] datos;
    private JDialog aed;

    /**
     * @param crearCollectionDialog
     * @param elementos
     */
    public AgregarElementoListaDialog(JDialog crearCollectionDialog, Vector elementos
            , ResourceBundle labels) {
        
        this.elementos = elementos;
        this.labels = labels;
        this.crearCollectionDialog = crearCollectionDialog;

        
    }


    /**
     * @param bnLabel
     * @param i
     * @param j
     * @param k
     * @param l
     */
    private void addComponent(Component component, int row, int column, int width, int height
            , GridBagConstraints constraints, GridBagLayout layout, Container cont ) {
        
        constraints.gridx = column;
        constraints.gridy = row;
        
        constraints.gridwidth = width;
        constraints.gridheight = height;
        
        layout.setConstraints( component, constraints );
        cont.add( component );   
        
    }

    /**
     * @return
     */
    public String[] getDatos() {
        aed = new JDialog( crearCollectionDialog, "AgregarElementoDialog",
                true);
        
        JPanel aedPanel = new JPanel( new BorderLayout() );
        
        JPanel contenedorPanel = new JPanel( new GridBagLayout() );
        contenedorPanel.setSize( 120, 500 );
        
        JPanel datosPanel = new JPanel( new GridLayout(3,1, 0, 20 ) );
        ButtonGroup buttonGroup = new ButtonGroup();
        
        JPanel restPanel = new JPanel( );
        GridBagLayout restPanelLayout = new GridBagLayout();
        restPanel.setLayout( restPanelLayout );
        GridBagConstraints restPanelConstraints = new GridBagConstraints();
        JPanel restLabelPanel = new JPanel();
        restLabelPanel.setPreferredSize( new Dimension(100, 30) );
        bnButton = new JRadioButton(labels.getString("bn"));
        buttonGroup.add( bnButton );
        bnButton.setSelected( true );
        bnButton.addItemListener( 
                new ItemListener(){

                    public void itemStateChanged(ItemEvent arg0) {
                        
                        if(bnButton.isSelected() ){
                            bnCB2.setEnabled( true );
                            firstCB.setEnabled( true );
                        }else{
                            bnCB2.setEnabled( false );
                            firstCB.setEnabled( false ); 
                        }
                        
                    }
                    
                });
        restLabelPanel.add( bnButton );
        addComponent( restLabelPanel, 0, 0, 1,1, restPanelConstraints, restPanelLayout, restPanel );
        Vector bnVector2 = new Vector();
        Iterator it2 = elementos.iterator();
        while( it2.hasNext() ){
            
            IElemento elemento = (IElemento) it2.next();
            if( elemento.getURI().startsWith("_:") ){
                bnVector2.add( elemento.getURI() );
            }
        }
        bnCB2 = new JComboBox( bnVector2 );
        bnCB2.addItemListener( 
                new ItemListener(){

                    public void itemStateChanged(ItemEvent arg0) {
                        
                        blankNodeSelected = bnCB2.getSelectedItem().toString();
                        
                    }
                    
                });
        
        if( bnCB2.getSelectedItem() != null )
            blankNodeSelected = bnCB2.getSelectedItem().toString();
        bnCB2.setPreferredSize( new Dimension(200,30) );
        addComponent( bnCB2, 0, 1, 1, 1, restPanelConstraints, restPanelLayout, restPanel );
        
        
        JPanel firstPanel = new JPanel( );
        GridBagLayout firstPanelLayout = new GridBagLayout();
        firstPanel.setLayout( firstPanelLayout );
        GridBagConstraints firstPanelConstraints = new GridBagConstraints();
        JPanel firstLabelPanel = new JPanel();
        firstLabelPanel.setPreferredSize( new Dimension(100, 30) );
        JLabel firstLabel = new JLabel( "RDF:First" );
        //firstLabel.setSize( new Dimension( 200, 30) );
        firstLabelPanel.add( firstLabel );
        //firstPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent( firstLabelPanel, 0, 0, 1,1, firstPanelConstraints, firstPanelLayout, firstPanel );
        Vector firstElementosVector = new Vector();
        Iterator it = elementos.iterator();
        while( it.hasNext() ){
        
            IElemento elemento = (IElemento) it.next();
            firstElementosVector.add( elemento.getURI() );
            
        }
        
        firstCB = new JComboBox( firstElementosVector );
        firstCB.addItemListener( 
                new ItemListener(){

                    public void itemStateChanged(ItemEvent arg0) {
                        
                        firstNodeSelected = firstCB.getSelectedItem().toString();
                        
                    }
                    
                });
        firstCB.setPreferredSize( new Dimension (200,30) ); 
        firstNodeSelected = firstCB.getSelectedItem().toString();
        addComponent( firstCB, 0, 1, 1, 1, firstPanelConstraints, firstPanelLayout, firstPanel );   
        
        
        
        JPanel nillButtonPanel = new JPanel( );
        GridBagLayout nillButtonPanelLayout = new GridBagLayout();
        nillButtonPanel.setLayout( nillButtonPanelLayout );
        GridBagConstraints nillButtonPanelConstraints = new GridBagConstraints();
        JPanel nillButtonLabelPanel = new JPanel();
        FlowLayout layout = new FlowLayout();
        nillButtonLabelPanel.setLayout( layout );
        nillButtonLabelPanel.setPreferredSize( new Dimension(300, 30) );
        nillButton = new JRadioButton("RDF:nill");
        buttonGroup.add( nillButton );
        nillButton.setSelected( false );
        nillButtonLabelPanel.add( nillButton );
        layout.setAlignment( FlowLayout.LEFT );
        nillButtonPanelConstraints.anchor = GridBagConstraints.WEST;
        addComponent( nillButtonLabelPanel, 0, 0, 1,1, nillButtonPanelConstraints, nillButtonPanelLayout, nillButtonPanel );

        
        datosPanel.add( restPanel );
        datosPanel.add( firstPanel );
        datosPanel.add( nillButtonPanel );
        
        contenedorPanel.add( datosPanel );       
        
        JPanel botonesPanel = new JPanel();
        aceptarButton = new JButton(labels.getString("Aceptar"));
        aceptarButton.addActionListener( this );
        cancelarButton = new JButton( labels.getString("Cancelar") );
        cancelarButton.addActionListener( this );        
        botonesPanel.add( aceptarButton );
        botonesPanel.add( cancelarButton );
        
        aedPanel.add( contenedorPanel, BorderLayout.CENTER );
        aedPanel.add( botonesPanel, BorderLayout.SOUTH );
        
        aed.getContentPane().add( aedPanel );
        aed.setSize(400,250);
        aed.setResizable( false );
        aed.setLocationRelativeTo( crearCollectionDialog );
        aed.setVisible( true );
        
        return datos;
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        
        if( arg0.getSource().equals( aceptarButton) ){
            if( bnButton.isSelected() ){
	            datos = new String[2];
	            datos[0] = blankNodeSelected;
	            datos[1] = firstNodeSelected;
            }else{
                datos = new String[1];
                datos[0] = "RDF:nill";
            }
            
        }
        
        aed.setVisible( false );
        
        
        
    }

}
