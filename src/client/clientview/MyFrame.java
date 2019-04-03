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
import client.clientcontroller.ListSuppliersListener;
import client.clientcontroller.ListToolsListener;
import client.clientcontroller.LoginListener;
import client.clientcontroller.SearchIDListener;
import client.clientcontroller.SearchNameListener;
import client.clientcontroller.SetDateListener;

public class MyFrame extends JFrame{
	private JButton listTools;
	private JButton listSuppliers;
	private JButton searchName;
	private JButton searchID;
	private JButton checkQuantity;
	private JButton decreaseQuantity;
	private JButton increaseQuantity;
	private JButton deleteTool;
	private JButton addNewTool;
	private JButton addNewSupplier;
	private JButton setNewDate;
		
	private ImageIcon listIcon;
	private ImageIcon searchIcon;
	private ImageIcon checkIcon;
	private ImageIcon decreaseIcon;
	private ImageIcon increaseIcon;
	private ImageIcon deleteIcon;
	private ImageIcon addToolIcon;
	private ImageIcon addSupplierIcon;
	private ImageIcon setDateIcon;
	
	
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
	
	public void addListener(ListToolsListener l) {
		listTools.addActionListener(l);		
	}
	
	public void addListener(ListSuppliersListener l) {
		listSuppliers.addActionListener(l);		
	}
	
	public void addListener(SearchNameListener l) {
		searchName.addActionListener(l);		
	}
	
	public void addListener(SearchIDListener l) {
		searchID.addActionListener(l);		
	}
	
	public void addListener(CheckQuantityListener l) {
		checkQuantity.addActionListener(l);		
	}
	
	public void addListener(DecreaseQuantityListener l) {
		decreaseQuantity.addActionListener(l);
	}
	
	public void addListener(IncreaseQuantityListener l) {
		increaseQuantity.addActionListener(l);
	}
	public void addListener(AddToolListener l) {
		addNewTool.addActionListener(l);
	}
	public void addListener(DeleteToolListener l) {
		deleteTool.addActionListener(l);
	}
	public void addListener(AddSupplierListener l) {
		addNewSupplier.addActionListener(l);
	}
	public void addListener(SetDateListener l) {
		setNewDate.addActionListener(l);
	}
	
	
	public ImageIcon getListIcon() {
		return listIcon;
	}
	
	public ImageIcon getSearchIcon() {
		return searchIcon;
	}
	public ImageIcon getCheckIcon() {
		return checkIcon;
	}
	public ImageIcon getDecreaseIcon() {
		return decreaseIcon;
	}
	public ImageIcon getIncreaseIcon() {
		return increaseIcon;
	}
	public ImageIcon getDeleteIcon() {
		return deleteIcon;
	}
	public ImageIcon getAddToolIcon() {
		return addToolIcon;
	}
	public ImageIcon getAddSupplierIcon() {
		return addSupplierIcon;
	}
	public ImageIcon getSetDateIcon() {
		return setDateIcon;
	}
	
	
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
		setNewDate = new JButton("Set New Date", setDateIcon);
		setNewDate.setFont(f);
		
		buttonPanel.add(listTools);
		buttonPanel.add(listSuppliers);
		buttonPanel.add(searchName);
		buttonPanel.add(searchID);
		buttonPanel.add(checkQuantity);
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(decreaseQuantity);
		buttonPanel.add(increaseQuantity);
		buttonPanel.add(addNewTool);
		buttonPanel.add(deleteTool);
		buttonPanel.add(addNewSupplier);
		buttonPanel.add(setNewDate);
		
		
		getContentPane().add("Center", buttonPanel);
	}
	
	
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
