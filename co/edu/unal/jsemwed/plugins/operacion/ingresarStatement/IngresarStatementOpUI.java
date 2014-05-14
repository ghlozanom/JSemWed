/*
 * Created on Feb 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.plugins.operacion.ingresarStatement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.plugins.operacion.OperacionUI;

/**
 * @author ghlozanom
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IngresarStatementOpUI 
extends OperacionUI
implements IOperacionUI,
ActionListener, ItemListener {

	private ResourceBundle labels;
	private ResourceBundle typed_literals = 
	    ResourceBundle.getBundle("co/edu/unal/jsemwed/comun/typed_literals/typed_literals");
	private Vector elementos;
	private JDialog statementDialog;
	private JComboBox subjectComboBox;
	private JComboBox predicateComboBox;
	private JComboBox objectComboBox;
	private JButton ingresarButton;
	private JButton cancelarButton;
	private JButton helpButton;
	private JFrame ventanaPrincipal;
	
	private String[] statement = new String[3];
	private Object urlNodoSelecto;
    private JRadioButton olButton;
    private JRadioButton orButton;
    private JTextField literalField;
    private JComboBox tiposCB;
    private Vector vector;
    private Vector[] allElements;
	

	/**
	 * @param ventanaPrincipal
	 * @param urlNodoSelecto
	 * @return
	 */
	public Vector getStatement(  ) {
		
	       statementDialog = new JDialog(  ventanaPrincipal, labels.getString("ingresarStatement"), true ); 
	        statementDialog.setSize(600, 250 );
	        //statementDialog.setResizable( false );
	        statementDialog.setLocationRelativeTo( ventanaPrincipal );
	        //manejadorAyuda.setHelpKey( statementDialog, "acerca_de_jsemwed_htm");
	        
	        JLabel subjectLabel = new JLabel( labels.getString( "sujeto" ) );
	        subjectComboBox = new  JComboBox( elementos );
	        subjectComboBox.addItemListener( this );        
	        statement[0] = subjectComboBox.getSelectedItem().toString();
	        //Si debe preseleccionar un subject
	        if( urlNodoSelecto != null ){
	            Iterator i = elementos.iterator();
	            int count = 0;
	            while( i.hasNext() ){
	                
	                String resourceURL =  i.next().toString();
	                if( resourceURL.equals(urlNodoSelecto) )
	                    subjectComboBox.setSelectedIndex( count );
	                count++;
	            }
	        }
	        
	        JLabel predicateLabel = new JLabel( labels.getString( "predicado") );
	        //se deben sacar los blank nodes del vector de elementos
	        Vector elementosAux = new Vector();
	        Iterator iter = elementos.iterator();
	        while( iter.hasNext() ){
	            String elemento = (String) iter.next();
	            if( ! elemento.startsWith("_") )
	                elementosAux.add( elemento );
	        }
	        for( int i = 1; i< allElements.length; i++ ){
	            
	            Vector vector = allElements[i];
	            iter = vector.iterator();
	            while( iter.hasNext() ){
	                String elemento = ((IElemento) iter.next()).getURI();
	                elementosAux.add( elemento );
	            }
	            
	        }
	        predicateComboBox = new JComboBox( elementosAux );
	        predicateComboBox.addItemListener( this );
	        statement[1] = predicateComboBox.getSelectedItem().toString();
	        
	        JPanel objectPanel = new JPanel( new GridLayout(2,1));
	        JPanel objectResourcePanel = new JPanel( new GridLayout (1,2) );
	        objectResourcePanel.setMaximumSize(new Dimension(400, 50 ) );
	        objectResourcePanel.setBorder(
	                BorderFactory.createTitledBorder( labels.getString("Resource")) );
	        orButton = new JRadioButton( labels.getString("ObjetoResource"),
	                true);
	        JLabel objectLabel = new JLabel( labels.getString( "objeto" ) );
	        objectComboBox = new JComboBox( elementos );
	        objectComboBox.addItemListener( this );
	        statement[2] = objectComboBox.getSelectedItem().toString();
	        objectResourcePanel.add( orButton );
	        JPanel orPanel = new JPanel();
	        orPanel.add( objectLabel );
	        orPanel.add( objectComboBox);
	        objectResourcePanel.add( orPanel );
	        JPanel objectLiteralPanel = new JPanel( new GridLayout(1,3) );
	        objectLiteralPanel.setMaximumSize( new Dimension(400, 50 ));
	        objectLiteralPanel.setBorder(
	                BorderFactory.createTitledBorder( labels.getString("literal")));
	        olButton = new JRadioButton( labels.getString("ObjetoLiteral"),
	                false );
	        JPanel olPanel = new JPanel();
	        JLabel objectLiteralLabel = new JLabel( labels.getString("literal") );
	        literalField = new JTextField( 15 );
	        olPanel.add(objectLiteralLabel);
	        JPanel tipoPanel = new JPanel();
	        tipoPanel.add( new JLabel( labels.getString("Tipo")));
	        Enumeration typed_literals_enum = typed_literals.getKeys();
	        Vector tipos = new Vector();
	        while( typed_literals_enum.hasMoreElements() ){
	            
	            Object ob = typed_literals_enum.nextElement();
	            tipos.add( ob.toString() );
	        }
	        //String[] tipos = {labels.getString("ninguno"),
	          //      "boolean", "long", "char", "float, double" };
	        tiposCB = new JComboBox( tipos );
	        tipoPanel.add( tiposCB );
	        olPanel.add(literalField);
	        objectLiteralPanel.add( olButton );
	        objectLiteralPanel.add( olPanel );
	        objectLiteralPanel.add( tipoPanel );
	        objectPanel.add( objectResourcePanel );
	        objectPanel.add( objectLiteralPanel );
	        ButtonGroup radioGroup = new ButtonGroup();
	        radioGroup.add( orButton );
	        radioGroup.add( olButton );
	        
	        
	        JPanel statementPanel = new JPanel(  );
	        JPanel subjectPanel = new JPanel();
	        subjectPanel.add(subjectLabel);
	        subjectPanel.add(subjectComboBox);
	        statementPanel.add(  subjectPanel );
	        JPanel predicatePanel = new JPanel();
	        predicatePanel.add(predicateLabel);
	        predicatePanel.add(predicateComboBox);
	        statementPanel.add( predicatePanel );
	        statementPanel.add( objectPanel );

	        
	        ingresarButton = new JButton(labels.getString("Aceptar"));
	        ingresarButton.addActionListener( this );
	        cancelarButton = new JButton( labels.getString("Cancelar") );
	        cancelarButton.addActionListener( this );
	        helpButton = manejadorAyuda.getBotonAyuda( "semantic_web_conceptos_b_sicos_en_que_consiste_semantic_web_htm" );
	        
	        JPanel panel = new JPanel( new BorderLayout() );

	        panel.add( statementPanel, BorderLayout.CENTER );
	        JPanel botonesPanel = new JPanel();
	        botonesPanel.add( ingresarButton );
	        botonesPanel.add( cancelarButton );
	        botonesPanel.add( helpButton );
	        	        
	        panel.add( botonesPanel, BorderLayout.SOUTH );
	        
	        //JLabel label = new JLabel( new ImageIcon("images/comp.jpg"));
	        //panel.add( label, BorderLayout.WEST );
	    	        
	        statementDialog.getContentPane().add( panel );
	        
	        statementDialog.setVisible( true );
	        //statementDialog = null;
	        
	        return vector;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent arg0) {
		
		Object source = arg0.getSource();
		
		if ( source.equals( subjectComboBox) )
			statement[0] = subjectComboBox.getSelectedItem().toString();
		
		else if( source.equals( predicateComboBox ) )
			statement[1] = predicateComboBox.getSelectedItem().toString();
		
		else if( source.equals( objectComboBox ) )
			statement[2] = objectComboBox.getSelectedItem().toString();
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		if( arg0.getSource().equals( cancelarButton) )
			statement = null;
		else{
		    if( olButton.isSelected() ){
		        
		        statement[2] = "\"" + literalField.getText() + "\"";
		        //Añadir el typed lyteral
		        String tipo = tiposCB.getSelectedItem().toString();
		        if( ! tipo.equals("none") ){
		            //El objeto tiene un tipo indicado
		            statement[2] = statement[2] + "^^" + tipo;
		            
		        }
		        
		    }
		    vector = new Vector(1);
		    vector.add( statement);
		    
		}

		statementDialog.setVisible( false );
		
	}
	
	
	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setFrame(javax.swing.JFrame)
	 */
	public void setFrame(JFrame ventanaPrincipal) {
		
		this.ventanaPrincipal = ventanaPrincipal;

	}


	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setNodoSelecto(java.lang.String)
	 */
	public void setNodoSelecto(String urlNodoSelecto) {
		
		this.urlNodoSelecto = urlNodoSelecto;

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setElementos(java.util.Vector)
	 */
	public void setElementos(Vector vector) {
		elementos = new Vector();
		Iterator it = vector.iterator();
		while( it.hasNext() ){
			
			elementos.add( ((IElemento)it.next()).getURI() );
			
		}

	}

	/* (non-Javadoc)
	 * @see co.edu.unal.jsemwed.comun.IOperacionUI#setLabels(java.util.ResourceBundle)
	 */
	public void setLabels(ResourceBundle labels) {
		
		this.labels = labels;
		
	}

    /* (non-Javadoc)
     * @see co.edu.unal.jsemwed.comun.IOperacionUI#setAllElements(java.util.Vector)
     */
    public void setAllElements(Vector[] vector) {
        
        allElements = vector;
        
    }

}
