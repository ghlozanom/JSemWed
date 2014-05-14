/*
 * Created on Feb 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.resources.elementos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.comun.IElemento;
import co.edu.unal.jsemwed.comun.IOperacionUI;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;
import co.edu.unal.jsemwed.ui.swing.manejadores.resources.elementos.dialogs.EdicionDialog;
import co.edu.unal.jsemwed.ui.swing.manejadores.resources.elementos.dialogs.IngresarResourceDialog;

/**
 * Administra cada uno de los elementos que contiene el modelo.
 * 
 * @author ghlozanom
 * 
 */
public class ElementoPanel extends JPanel implements ActionListener,
TreeSelectionListener {

	private JButton crearResourceButton;
	private JButton editarResourceButton;
	private JButton borrarResourceButton;

	private JScrollPane elementosTreeSP;
	private JTree elementosTree;
	private ResourceBundle labels;
	private ManejadorAyuda manejadorAyuda;
	private JFrame ventanaPrincipal;
	private IControlador iControlador;
	private String urlNodoSelecto;
	private String nombrePanel;
	private int nivelNodoSelecto;
	private MouseAdapter ml;
	private JPopupMenu popupMenu;
	private Vector elementos;
	/**
	 * Archivo de operaciones asociadas a los elementos.
	 */
	private ResourceBundle operaciones = 
		ResourceBundle.getBundle("co/edu/unal/jsemwed/plugins/operacion/operaciones");
    private Vector[] allElements;
	
	/**
	 * Creacion de el panel de un elemento
	 * 
	 * @param string nombre del elemento sobre el que se construye el panel.
	 * @param labels labels para internacionalización.
	 * @param manejadorAyuda componente para creación del archivo de ayuda.
	 * @param frame ventana principal.
	 * @param iControlador componente para interactuar con el controlador de la app.
	 */
	public ElementoPanel(String string, ResourceBundle labels,
			ManejadorAyuda manejadorAyuda, JFrame frame, IControlador iControlador  ) {
		
		this.labels = labels;
		this.manejadorAyuda = manejadorAyuda;
		this.ventanaPrincipal = frame;
		this.iControlador = iControlador;
		this.nombrePanel = string;
		
    	setLayout( new BorderLayout() );
    	setPreferredSize( new Dimension(300, 400) );
    	
        JPanel botonesPanel = new JPanel();
        
        crearResourceButton = new JButton(new ImageIcon("images/Add16.gif"));
        crearResourceButton.setName( string );
        crearResourceButton.setToolTipText( labels.getString("ingresarResource"));
        crearResourceButton.addActionListener( this );
        
        editarResourceButton = new JButton(new ImageIcon("images/Edit16.gif"));
        editarResourceButton.setName( string );
        editarResourceButton.addActionListener( this );
        
        borrarResourceButton = new JButton(new ImageIcon("images/Delete16.gif"));
        borrarResourceButton.setName( string );
        borrarResourceButton.setEnabled( false );
        borrarResourceButton.addActionListener( this );
        
        JButton helpButton = manejadorAyuda.getBotonAyuda( "semantic_web_conceptos_b_sicos_en_que_consiste_semantic_web_htm" );
        
        botonesPanel.add( crearResourceButton );
        botonesPanel.add( editarResourceButton );
        botonesPanel.add( borrarResourceButton );
        botonesPanel.add( helpButton );
        
        ml = new MouseAdapter(){
        	
            public void mousePressed(MouseEvent e) {
                int selRow = elementosTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = elementosTree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                	if( e.isPopupTrigger() || 
                	        e.getButton() == MouseEvent.BUTTON3 ){
                	    
                	    elementosTree.setSelectionRow( selRow );
                	    if( nivelNodoSelecto == 1 ){
                	    	
                	    	crearPopupMenu();
                	        popupMenu.show( e.getComponent(), e.getX(), e.getY() );
                	    }
                		
                		
                	}/**
                    if(e.getClickCount() == 1) {
                        mySingleClick(selRow, selPath);
                    }
                    else if(e.getClickCount() == 2) {
                        myDoubleClick(selRow, selPath);
                    }**/
                }
            }

        };
        
        add( botonesPanel, BorderLayout.NORTH );
		
	}

	/**
	 * Crea el menu asociado con los elementos para las operaciones.
	 *
	 */
	private void crearPopupMenu() {
		
		popupMenu = new JPopupMenu();
		Iterator it = elementos.iterator();
		IElemento elemento = null;
		while( it.hasNext() )
		{
			elemento = (IElemento) it.next();
			if( elemento.getURI().equals( urlNodoSelecto) )
				break;
			elemento = null;
			
		}
		if( elemento != null ){
			Vector ops = elemento.getOperaciones();
			if( ops == null )
			    return;
			it = ops.iterator();
			while( it.hasNext() ){
				JMenuItem mi = new JMenuItem( it.next().toString() );
				mi.setName( "operacion" );
				mi.addActionListener( this );
				popupMenu.add( mi );
			}
		}
			
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		if( arg0.getSource().equals(crearResourceButton) )
			ingresarElemento( ((Component) arg0.getSource()).getName() );
		if( arg0.getSource().equals( borrarResourceButton) )
			borrarElemento();
		if( arg0.getSource().equals( editarResourceButton))
			editarElemento();
		if( arg0.getSource() instanceof JMenuItem ){
			
		    //Maneja la implementación de interfaz de usuario de las operaciones
			try {
				String nombreOperacion = 
					operaciones.getString(((JMenuItem)arg0.getSource()).getText());
				IOperacionUI op = (IOperacionUI) 
					Class.forName( nombreOperacion + "OpUI" ).newInstance();
				op.setFrame( ventanaPrincipal );
				op.setNodoSelecto( urlNodoSelecto );
				op.setElementos( this.elementos );
				op.setAllElements( allElements );
				op.setLabels( labels );
				op.setManejadorAyuda( manejadorAyuda );
				Vector statements = op.getStatement();

				if( statements != null ){
					iControlador.doOperacion( nombreOperacion, statements );
					//System.out.println( statement[0] + " " + statement[1] + " "
						//	+ statement[2] );
				}
				
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
			
		
	}

	/**
	 * Permite editar el URI de un elemento.
	 * 
	 */
	private void editarElemento() {
		
		EdicionDialog edDialog = new EdicionDialog(  ventanaPrincipal,
				labels, manejadorAyuda );
		String uri = edDialog.getNuevoURI( urlNodoSelecto );
		System.out.println( uri );
		System.out.println( urlNodoSelecto );
		iControlador.modificarElemento( urlNodoSelecto, uri );
		
	}

	/**
	 * Borra un elemento del modelo.
	 * Este metodo hace el llamado para borrar el elemento al controlador.
	 */
	private void borrarElemento() {
		
		iControlador.borrarElemento( nombrePanel, urlNodoSelecto );
		
	}

	/**
	 * Ingresa un nuevo elemento al modelo
	 * 
	 * @param string nombre del elemento sobre el que se ingresa un nuevo individuo
     * 
     */
    private void ingresarElemento(String string) {
        
        IngresarResourceDialog ingresarResourceDialog = new IngresarResourceDialog( labels, 
                manejadorAyuda, string );
        
        String URI = ingresarResourceDialog.getResourceURI( ventanaPrincipal );
        
        if( URI != null ){
            
            iControlador.ingresarResource( string, URI );
            //Revisar que el motor lo haya hecho bien
            //Resource resource = new Resource( URI );
            //resources.add( resource );
            //pinteResources();
            //manejadorVentana.actualizaCambios();
            //Revisar la creacion de los menu a utilizar
            //ingresarStatementItem.setEnabled( true );
            //ingresarStatementPopupItem.setEnabled( true );

        }
        
    }
	/**
	 * Pinta los nuevos resources del elemento asociado a este panel.
	 * 
	 * @param root
	 */
	public void pinteResources(DefaultMutableTreeNode root, Vector elementos ) {
		
		this.elementos = elementos;
		processHierarchy( root, elementos  );
		
		if( elementosTreeSP != null ){
            
            elementosTreeSP.remove( elementosTree );
            remove( elementosTreeSP );
            
        }
        elementosTree = new JTree( root );
        elementosTree.addTreeSelectionListener( this );
        elementosTree.addMouseListener( ml );
        elementosTreeSP = new JScrollPane( elementosTree );
        add( elementosTreeSP, BorderLayout.CENTER );
        
		
	}
	
    /**
     * Procesa el arbol de loe elementos de este panel
     * 
     * @param root2 subarbol a procesar de elementos
     * 
     */
    private void processHierarchy(DefaultMutableTreeNode root2,
    		Vector res) {
        
        Iterator it = res.iterator();
        
        while( it.hasNext() ){
            
            IElemento elemento = (IElemento) it.next();
            DefaultMutableTreeNode elementoRoot = new DefaultMutableTreeNode( elemento.getURI()); 
            root2.add(  elementoRoot );
            //Aï¿½adir propiedades de los resources
            Vector operaciones = elemento.getOperaciones();
            
            if( operaciones != null ){
            //Si el elemento tiene operaciones
	            if( operaciones.size() > 0 ){
	            	
	            	JPopupMenu popupMenu = new JPopupMenu();
	            	Iterator opit = operaciones.iterator();
	            	
	            	while( opit.hasNext() ){
	            		
	            		String op = (String) opit.next();
	            		JMenuItem mi = new JMenuItem( op );
	            		popupMenu.add( mi );
	            		mi.addActionListener( this );
	            		mi.setName( op );
	            		
	            	}       
	            	
	            }
            }
            
            Vector propiedades = elemento.getPropiedades();
            
            if( propiedades != null ){
	            if( propiedades.size() > 0 ){
	            	
	            	Iterator propit = propiedades.iterator();
	            	while( propit.hasNext() ){
	            		
	            		Object[] propiedad = (Object[]) propit.next();
	            		DefaultMutableTreeNode propiedadRoot = 
	            			new DefaultMutableTreeNode( propiedad[0] );
	            		Vector valorPropiedad = (Vector) propiedad[1];
	            		Iterator valores = valorPropiedad.iterator();
	            		while( valores.hasNext() ){
	            			
	            			DefaultMutableTreeNode valorPropiedadRoot = 
	                			new DefaultMutableTreeNode( (String)valores.next() );
	            			propiedadRoot.add( valorPropiedadRoot );
	            			
	            		}
	            		
	            		elementoRoot.add( propiedadRoot );
	            		
	            	}
	            	
	            }
            }
            
            
        }
        
    }
	
    
    /**
     * Captura la seleccion de un nuevo elemento en el panel.
     */
	public void valueChanged(TreeSelectionEvent e) {
		
		borrarResourceButton.setEnabled( true );
		
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) elementosTree
				.getLastSelectedPathComponent();

		if (node == null)
			return;

		Object nodeInfo = node.getUserObject();
		//System.out.println(nodeInfo.toString() );
		urlNodoSelecto = nodeInfo.toString();
		nivelNodoSelecto = node.getLevel();
	}

    /**
     * @param res
     */
    public void setAllElements(Vector[] res) {
        
        allElements = res;
        
    }


}
