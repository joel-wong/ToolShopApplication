package client.clientview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.clientcontroller.AddSupplierListener;
import client.clientcontroller.AddToolListener;
import client.clientcontroller.CheckQuantityListener;
import client.clientcontroller.DecreaseQuantityListener;
import client.clientcontroller.DeleteToolListener;
import client.clientcontroller.InactivityListener;
import client.clientcontroller.IncreaseQuantityListener;
import client.clientcontroller.ListOrdersListener;
import client.clientcontroller.ListSuppliersListener;
import client.clientcontroller.ListToolsListener;
import client.clientcontroller.LoginListener;
import client.clientcontroller.SearchIDListener;
import client.clientcontroller.SearchNameListener;
import client.clientcontroller.SetDateListener;

/**
 * This class provides data fields and methods to create the menu GUI for a Tool Shop Application.
 * 
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 *
 */
public class MyFrame extends JFrame{
	/**
	 * Button to display list of Tools
	 */
	private JButton listTools;
	/**
	 * Button to display list of Suppliers
	 */
	private JButton listSuppliers;
	/**
	 * Button to search for a Tool by name
	 */
	private JButton searchName;
	/**
	 * Button to search for a Tool by ID
	 */
	private JButton searchID;
	/**
	 * Button to check the quantity of a Tool
	 */
	private JButton checkQuantity;
	/**
	 * Button to decrease the quantity of a Tool
	 */
	private JButton decreaseQuantity;
	/**
	 * Button to increase the quantity of a Tool
	 */
	private JButton increaseQuantity;
	/**
	 * Button to delete a Tool
	 */
	private JButton deleteTool;
	/**
	 * Button to add a new Tool
	 */
	private JButton addNewTool;
	/**
	 * Button to add a new Supplier
	 */
	private JButton addNewSupplier;
	/**
	 * Button to set a new Date
	 */
	private JButton setNewDate;
	/**
	 * Button to display list of Orders
	 */
	private JButton listOrders;
	
	/**
	 * List icon
	 */
	private ImageIcon listIcon;
	/**
	 * Search icon
	 */
	private ImageIcon searchIcon;
	/**
	 * Check quantity icon
	 */
	private ImageIcon checkIcon;
	/**
	 * Decrease quantity icon
	 */
	private ImageIcon decreaseIcon;
	/**
	 * Increase quantity icon
	 */
	private ImageIcon increaseIcon;
	/**
	 * Delete tool icon
	 */
	private ImageIcon deleteIcon;
	/**
	 * Add tool ion
	 */
	private ImageIcon addToolIcon;
	/**
	 * Add supplier Icon
	 */
	private ImageIcon addSupplierIcon;
	/**
	 * Set Date Icon
	 */
	private ImageIcon setDateIcon;
	
	/**
	 * Constructs a MyFrame object and creates the Icons, adds the buttons, adds the Title.
	 * @param s is the name of the frame
	 */
	public MyFrame(String s) {
		super(s);
		setLayout(new BorderLayout());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tool Shop Application");
		createIcons();
		addButtonPanel();
		addTitlePanel();
		pack();
	}
	
	/**
	 * Adds the ActionListener to the listTools button. 
	 * @param l is the ListToolsListener
	 */
	public void addListener(ListToolsListener l) {
		listTools.addActionListener(l);		
	}
	
	/**
	 * Adds the ActionListener to the listSuppliers button. 
	 * @param l is the ListSuppliersListener
	 */
	public void addListener(ListSuppliersListener l) {
		listSuppliers.addActionListener(l);		
	}
	
	/**
	 * Adds the ActionListener to the searchName button. 
	 * @param l is the SearchNameListener
	 */
	public void addListener(SearchNameListener l) {
		searchName.addActionListener(l);		
	}
	
	/**
	 * Adds the ActionListener to the searchID button. 
	 * @param l is the SearchIDListener
	 */
	public void addListener(SearchIDListener l) {
		searchID.addActionListener(l);		
	}
	
	/**
	 * Adds the ActionListener to the checkQuantity button. 
	 * @param l is the CheckQuantityListener
	 */
	public void addListener(CheckQuantityListener l) {
		checkQuantity.addActionListener(l);		
	}
	
	/**
	 * Adds the ActionListener to the decreaseQuantity button. 
	 * @param l is the DecreaseQuantityListener
	 */
	public void addListener(DecreaseQuantityListener l) {
		decreaseQuantity.addActionListener(l);
	}
	
	/**
	 * Adds the ActionListener to the increaseQuantity button. 
	 * @param l is the IncreaseQuantityListener
	 */
	public void addListener(IncreaseQuantityListener l) {
		increaseQuantity.addActionListener(l);
	}
	
	/**
	 * Adds the ActionListener to the addNewTool button. 
	 * @param l is the AddToolListener
	 */
	public void addListener(AddToolListener l) {
		addNewTool.addActionListener(l);
	}
	
	/**
	 * Adds the ActionListener to the deleteTool button. 
	 * @param l is the DeleteToolListener
	 */
	public void addListener(DeleteToolListener l) {
		deleteTool.addActionListener(l);
	}
	
	/**
	 * Adds the ActionListener to the addNewSupplier button. 
	 * @param l is the AddSupplierListener
	 */
	public void addListener(AddSupplierListener l) {
		addNewSupplier.addActionListener(l);
	}
	
	/**
	 * Adds the ActionListener to the setNewDate button. 
	 * @param l is the SetDateListener
	 */
	public void addListener(SetDateListener l) {
		setNewDate.addActionListener(l);
	}
	
	/**
	 * Adds the ActionListener to the listOrders button. 
	 * @param l is the ListOrdersListener
	 */
	public void addListener(ListOrdersListener l) {
		listOrders.addActionListener(l);
	}
	
	/**
	 * Returns the listIcon.
	 * @return the listIcon
	 */
	public ImageIcon getListIcon() {
		return listIcon;
	}
	/**
	 * Returns the searchIcon.
	 * @return the searchIcon
	 */
	public ImageIcon getSearchIcon() {
		return searchIcon;
	}
	/**
	 * Returns the checkIcon.
	 * @return the checkIcon
	 */
	public ImageIcon getCheckIcon() {
		return checkIcon;
	}
	/**
	 * Returns the decreaseIcon.
	 * @return the decreaseIcon
	 */
	public ImageIcon getDecreaseIcon() {
		return decreaseIcon;
	}
	/**
	 * Returns the increaseIcon.
	 * @return the increaseIcon
	 */
	public ImageIcon getIncreaseIcon() {
		return increaseIcon;
	}
	/**
	 * Returns the deleteIcon.
	 * @return the deleteIcon
	 */
	public ImageIcon getDeleteIcon() {
		return deleteIcon;
	}
	/**
	 * Returns the addToolIcon.
	 * @return the addToolIcon
	 */
	public ImageIcon getAddToolIcon() {
		return addToolIcon;
	}
	/**
	 * Returns the addSupplierIcon.
	 * @return the addSupplierIcon
	 */
	public ImageIcon getAddSupplierIcon() {
		return addSupplierIcon;
	}
	/**
	 * Returns the setDateIcon.
	 * @return the setDateIcon
	 */
	public ImageIcon getSetDateIcon() {
		return setDateIcon;
	}

	/**
	 * Creates the icons.
	 */
	private void createIcons() {
		listIcon = new ImageIcon("listIcon.png");
		searchIcon = new ImageIcon("searchIcon.png");
		checkIcon = new ImageIcon("checkIcon.png");
		decreaseIcon = new ImageIcon("decreaseIcon.png");
		increaseIcon = new ImageIcon("increaseIcon.png");
		deleteIcon = new ImageIcon("deleteIcon.png");
		addToolIcon = new ImageIcon("addToolIcon.png");
		addSupplierIcon = new ImageIcon("addSupplierIcon.png");
		setDateIcon = new ImageIcon("setDateIcon.png");
	}


	/**
	 * Creates and adds the buttons to the frame.
	 */
	private void addButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new GridLayout(0,2));
		
		Font f = new Font("Arial",Font.PLAIN,20);
		
		listTools = new JButton("List all Tools", listIcon);
		listTools.setFont(f);
		listSuppliers = new JButton("List all Suppliers", listIcon);
		listSuppliers.setFont(f);
		searchName = new JButton("Search for Tool by Name",searchIcon);
		searchName.setFont(f);
		searchID = new JButton("Search for Tool by ID",searchIcon);
		searchID.setFont(f);
		checkQuantity = new JButton("Check Tool Quantity",checkIcon);
		checkQuantity.setFont(f);
		setNewDate = new JButton("Set New Date", setDateIcon);
		setNewDate.setFont(f);
		decreaseQuantity = new JButton("Decrease Tool Quantity", decreaseIcon);
		decreaseQuantity.setFont(f);
		increaseQuantity = new JButton("Increase Tool Quantity", increaseIcon);
		increaseQuantity.setFont(f);
		addNewTool = new JButton("Add New Tool", addToolIcon);
		addNewTool.setFont(f);
		deleteTool = new JButton("Delete Tool", deleteIcon);
		deleteTool.setFont(f);
		addNewSupplier = new JButton("Add New Supplier", addSupplierIcon);
		addNewSupplier.setFont(f);
		listOrders = new JButton("List Orders", listIcon);
		listOrders.setFont(f);
		
		
		buttonPanel.add(listTools);
		buttonPanel.add(listSuppliers);
		buttonPanel.add(searchName);
		buttonPanel.add(searchID);
		buttonPanel.add(checkQuantity);
		buttonPanel.add(setNewDate);
		buttonPanel.add(decreaseQuantity);
		buttonPanel.add(increaseQuantity);
		buttonPanel.add(addNewTool);
		buttonPanel.add(deleteTool);
		buttonPanel.add(addNewSupplier);
		buttonPanel.add(listOrders);
		
		
		
		getContentPane().add("Center", buttonPanel);
	}

	/**
	 * Creates and adds the title panel to the frame.
	 */
	private void addTitlePanel() {
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBackground(new Color(169, 231, 252));
		Font f = new Font("Arial",Font.BOLD,30);
		JLabel title = new JLabel("MENU");
		title.setFont(f);
		titlePanel.add(title);
		getContentPane().add("North", titlePanel);
	}
	

	/**
	 * Activates the Automatic Logout feature for the frame. 
	 * @param loginFrame is the Login Frame for the shop Application
	 */
	public void setAutomaticLogout(LoginFrame loginFrame) {
		Action logout = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame)e.getSource();
				frame.dispose();	
	    		JOptionPane.showMessageDialog(null, "Session timed out due to inactivity.", "Session Timed Out", JOptionPane.INFORMATION_MESSAGE);
	    		loginFrame.setVisible(true);
			}	
		};
		
		InactivityListener l = new InactivityListener(this, logout, 1);
		l.start();
	}
	
	

	
}
