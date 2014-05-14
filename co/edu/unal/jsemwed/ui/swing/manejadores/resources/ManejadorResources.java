/*
 * Created on 14-ene-2005
 *
 * Gabriel H. Lozano M.
 * Ingenierï¿½a de Sistemas.
 * Universidad Nacional de Colombia.
 */
package co.edu.unal.jsemwed.ui.swing.manejadores.resources;

import java.awt.Component;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import co.edu.unal.jsemwed.comun.IControlador;
import co.edu.unal.jsemwed.ui.swing.manejadores.ayuda.ManejadorAyuda;
import co.edu.unal.jsemwed.ui.swing.manejadores.resources.dialogs.IngresarStatementDialog;
import co.edu.unal.jsemwed.ui.swing.manejadores.resources.elementos.ElementoPanel;
import co.edu.unal.jsemwed.ui.swing.manejadores.ventana.ManejadorVentana;

/**
 * Administra el panel de elementos de la aplicacion
 * 
 * @author Gabriel
 *
 */
public class ManejadorResources {

    ResourceBundle labels;
    
    private JTabbedPane elementosPanel = null;
    private JButton crearResourceButton;
    private JButton editarResourceButton;
	private JButton borrarResourceButton;
    private IControlador iControlador;
    
    private JMenu resourcesMenu;
    private JMenuItem ingresarResourceItem;
    private JMenuItem ingresarStatementItem;
    private JMenuItem ingresarStatementPopupItem = new JMenuItem();
    
    private JFrame ventanaPrincipal;
    private IngresarStatementDialog ingresarStatementDialog;
    private ManejadorAyuda manejadorAyuda;
    private ManejadorVentana manejadorVentana;
    private JScrollPane resourcesTreeSP = null;
    private JTree recursosTree = null;
    private TitledBorder border;
    private JPopupMenu popupMenu;
    private String selectedResource = null;
    
    private JPanel[] panelesDeElementos;
    
    private Vector resources;
    private Vector[] elementos; //Vector que se actualiza cada vez que se pintan los resources
    private DefaultMutableTreeNode root = null;

//	private Object[] res;
	private MouseListener ml;
	private int nivelNodoSelecto;
	private String urlNodoSelecto;



	/**
	 * 
	 */
	private void ingresarStatement() {
	
	    ingresarStatementDialog = new IngresarStatementDialog( labels, 
	            manejadorAyuda, resources  );
	    
	    String[] statement = ingresarStatementDialog.getStatement( ventanaPrincipal, 
	            urlNodoSelecto );
	    
	    if( statement != null ){
	        
	        iControlador.ingresarStatement(  statement[0], statement[1], statement[2] );
	        
	    }else
	    	System.out.println("no ingreso statement");
	
	}



	/**
	 * Crea el panel con los componentes del modelo.
	 * 
	 * @param elementos
	 * @return panel de elementos
	 */
	public Component getElementosPanel( String[] elementos) {
	
		//JPanel elementosFinalPanel = new JPanel();
	    elementosPanel = new JTabbedPane();
	    panelesDeElementos = new JPanel[ elementos.length ];
	    //elementosPanel.setPreferredSize( new Dimension(300,300) );
	    
	    border = BorderFactory.createTitledBorder("test" );
	    
	    //elementosPanel.setLayout( new BorderLayout() );
	    
	    for( int i = 0; i < elementos.length; i++ ){
	    	
	    	JPanel elementoPanel = new ElementoPanel( elementos[i],
	    			labels, manejadorAyuda, ventanaPrincipal, iControlador );
	        
	        JMenuItem resMenuItem = new JMenuItem( elementos[i], 
	        		new ImageIcon("images/Add16.gif"));
	        resourcesMenu.add(  resMenuItem );
	        resMenuItem.setName( elementos[i] );
	        //resMenuItem.addActionListener( this );    
	        	    	
	        elementosPanel.addTab( labels.getString(elementos[i]), elementoPanel );
	        elementoPanel.setName( elementos[i] );
	        panelesDeElementos[i] = elementoPanel;
	        //elementosFinalPanel.add()

	    }    
	    
        elementosPanel.setBorder( 
                BorderFactory.createTitledBorder(labels.getString("elementos")));
	    ingresarStatementPopupItem.setEnabled( false );
//	    elementosFinalPanel.add( elementosPanel );
	    return elementosPanel;
	    
	}

	/**
	 * @return Returns the manejadorVentana.
	 */
	public ManejadorVentana getManejadorVentana() {
	    return manejadorVentana;
	}
    
    /**
     * Configura los labels para soportar internacionalización.
     * 
     * @param labels
     */
    public void setLabels(ResourceBundle labels) {
        
        this.labels = labels;
        
        if( elementosPanel != null )
        {
	        elementosPanel.setBorder( 
	                BorderFactory.createTitledBorder(labels.getString("ResourcesMenu")));
	        crearResourceButton.setToolTipText( labels.getString("ingresarResource"));
        }
        
        resourcesMenu.setText( labels.getString("ResourcesMenu") );
        
        ingresarResourceItem.setText( labels.getString("ingresarResource") );
        
        ingresarStatementItem.setText( labels.getString("ingresarStatement"));
        
        ingresarStatementPopupItem.setText( labels.getString("ingresarStatement") );
        
        //if ( res != null )
        	//pinteResources( res );
        
    }
    
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    /**Posiblemente para borrar
    public void actionPerformed(ActionEvent arg0) {
        
        Object obj = arg0.getSource();
        if( obj.equals( crearResourceButton) || obj.equals(ingresarResourceItem) );
            //ingresarElemento( crearResourceButton.getName() );
        else if ( obj.equals( ingresarStatementItem)  || obj.equals( ingresarStatementPopupItem ) )
        	ingresarStatement();
        
        
    }**/
    /**
     * Pinta los resources ingresados en la app
     * 
     * @param res arreglo de vectores con los valores ingresados
     * 		  para los diferentes recursos.
     */
    public void pinteResources( Vector[] res ) {
        
        this.elementos = res;
        
        
        for (int i = 0; i < res.length; i++) {
        	
        	ElementoPanel elementoPanel = (ElementoPanel) panelesDeElementos[i];
        	elementoPanel.setAllElements( res );
        	DefaultMutableTreeNode root = new
	    		DefaultMutableTreeNode( elementoPanel.getName() );
        	elementoPanel.pinteResources( root, res[i] );
        	//processHierarchy( root, res[i]  );
        	//elementoPanel.pinteResources( root );
        	
		}    
        
        //recursosTree = new JTree(root);
        //resourcesTreeSP = new JScrollPane( recursosTree );
        
        /**JPanel panel = null;
        for (int i = 0; i < panelesDeElementos.length; i++) {
			if( panelesDeElementos[i].getName().equals("Resource") ){
				panel = panelesDeElementos[i];
			}
		}
        
        ElementoPanel elementoPanel = (ElementoPanel) panel;
        elementoPanel.pinteResources( root );**/
        
        
        //elementosPanel.add( resourcesTreeSP, BorderLayout.CENTER );
        
    }

	/**
     * @param manejadorAyuda El manejadorAyuda a configurar.
     */
    public void setManejadorAyuda(ManejadorAyuda manejadorAyuda) {
        this.manejadorAyuda = manejadorAyuda;
    }
    /**
     * @param controlador
     */
    public void setIControlador(IControlador controlador) {
        
        this.iControlador = controlador;
        
    }
    /**
     * Crea el menu resources de la app.
     * 
     * @return menu Resources de la app.
     */
    public JMenu getResourcesMenu() {
        
        resourcesMenu = new JMenu(  );
        
        ingresarResourceItem = new JMenuItem( new ImageIcon("images/Add16.gif"));
        //ingresarResourceItem.addActionListener( this );
        
        if ( ingresarStatementItem == null ){
        	
        	ingresarStatementItem = new JMenuItem(  );
        	//ingresarStatementItem.addActionListener( this );
        	ingresarStatementItem.setEnabled( false );
        	
        }
        
        //resourcesMenu.add( ingresarResourceItem );
        //resourcesMenu.addSeparator();
        //resourcesMenu.add( ingresarStatementItem );
        
        return resourcesMenu;
    }

    /**
     * @param component El ventanaPrincipal a configurar.
     */
    public void setVentanaPrincipal(Component component) {
        this.ventanaPrincipal = (JFrame) component;
    }
    /**
     * @param res
     */
/**    public void pinteResources(Object[] res) {

    	this.res = res;
        DefaultMutableTreeNode root = new
        	DefaultMutableTreeNode( labels.getString("Recursos") );
        
        processHierarchy( res, root );        
        
        if( resourcesTreeSP != null ){
            
            resourcesTreeSP.remove( recursosTree );
            elementosPanel.remove( resourcesTreeSP );
            
        }
        
        recursosTree = new JTree(root);
        resourcesTreeSP = new JScrollPane( recursosTree );
        elementosPanel.add( resourcesTreeSP, BorderLayout.CENTER );
        
        if( popupMenu == null ){
        	
	        popupMenu = new JPopupMenu();
	        popupMenu.add(  ingresarStatementPopupItem );
	        ingresarStatementPopupItem.addActionListener( this );
	        
	        ml = new MouseAdapter(){
	        	
	            public void mousePressed(MouseEvent e) {
	                int selRow = recursosTree.getRowForLocation(e.getX(), e.getY());
	                TreePath selPath = recursosTree.getPathForLocation(e.getX(), e.getY());
	                if(selRow != -1) {
	                	if( e.isPopupTrigger() || 
	                	        e.getButton() == MouseEvent.BUTTON3 ){
	                	    
	                	    recursosTree.setSelectionRow( selRow );
	                	    if( nivelNodoSelecto == 1 )
	                	        popupMenu.show( e.getComponent(), e.getX(), e.getY() );
	                		
	                		
	                	}/**
	                    if(e.getClickCount() == 1) {
	                        mySingleClick(selRow, selPath);
	                    }
	                    else if(e.getClickCount() == 2) {
	                        myDoubleClick(selRow, selPath);
	                    }**//**
	                }
	            }
	        };
        }
        recursosTree.addMouseListener( ml );
        recursosTree.addTreeSelectionListener( this );
        
        
        
    }
    **/
    /**
     * @param res
     * @return
     */
 /**   private void processHierarchy(Object[] res,
            DefaultMutableTreeNode root ) {
        
        DefaultMutableTreeNode child = root; /**= new
        	DefaultMutableTreeNode(res[0]);
        root.add( child );**//**
        
        for (int i = 0; i < res.length; i++) {
            
            Object node = res[i];
            
            if( node instanceof Object[] ){
                    
                /**Object[] data = (Object[]) node;
                child = new DefaultMutableTreeNode (data[0]);
                root.add( child );
                
                if( data.length == 2 )
                    processHierarchy( (Object[]) data[1], child );**//**
                Object[] data = (Object[]) node;
                processHierarchy( data, child );
                
            }else{
                
                child = new DefaultMutableTreeNode (node);
                root.add( child );
                
            }
            
            //root.add( child );
            
        }
        
    }**/
	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
/**	public void valueChanged(TreeSelectionEvent e) {
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) recursosTree
				.getLastSelectedPathComponent();

		if (node == null)
			return;

		Object nodeInfo = node.getUserObject();
		urlNodoSelecto = nodeInfo.toString();
		nivelNodoSelecto = node.getLevel();**/
		/**
		if (node.isLeaf()) {
			BookInfo book = (BookInfo) nodeInfo;
			displayURL(book.bookURL);
		} else {
			displayURL(helpURL);
		}**/
		
	//}
    /**
     * Indica a esta clase la creación de un modelo para que se configure.
     * 
     */
    public void modeloCreado() {
        
        resources = new Vector();
        
    }
    /**
     * @param resources2
     */
    public void setResources(Vector resources2) {
        
        this.resources = resources2;
        
    }
	
    /**
     * @param manejadorVentana El manejadorVentana a configurar.
     */
    public void setManejadorVentana(ManejadorVentana manejadorVentana) {
        this.manejadorVentana = manejadorVentana;
    }



    /**
     * @return
     */
    public Vector[] getElementos() {
       
        return this.elementos;
    }
}
