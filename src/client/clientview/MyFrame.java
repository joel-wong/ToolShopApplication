package client.clientview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.clientcontroller.CheckQuantityListener;
import client.clientcontroller.DecreaseQuantityListener;
import client.clientcontroller.ListToolsListener;
import client.clientcontroller.SearchIDListener;
import client.clientcontroller.SearchNameListener;

public class MyFrame extends JFrame{
	private JButton listTools;
	private JButton searchName;
	private JButton searchID;
	private JButton checkQuantity;
	private JButton decreaseQuantity;
	
	private ImageIcon listIcon;
	private ImageIcon searchIcon;
	private ImageIcon checkIcon;
	private ImageIcon decreaseIcon;
	
	
	public MyFrame(String s) {
		super(s);
		setLayout(new BorderLayout());
		setSize(500,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Window");
		createIcons();
		addButtonPanel();
		addTitlePanel();
		
	    
	}
	
	public void addListener(ListToolsListener l) {
		listTools.addActionListener(l);		
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
	
	public ImageIcon getListIcon() {
		return listIcon;
	}
	
	public void createIcons() {
		listIcon = new ImageIcon("listIcon.png");
		searchIcon = new ImageIcon("searchIcon.png");
		checkIcon = new ImageIcon("checkIcon.png");
		decreaseIcon = new ImageIcon("decreaseIcon.png");
	}
	
	
	public void addButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		Font f = new Font("Arial",Font.PLAIN,20);
		
		listTools = new JButton("List all Tools", listIcon);
		listTools.setAlignmentX(CENTER_ALIGNMENT);
		listTools.setFont(f);
		searchName = new JButton("Search for Tool by Name",searchIcon);
		searchName.setAlignmentX(CENTER_ALIGNMENT);
		searchName.setFont(f);
		searchID = new JButton("Search for Tool by ID",searchIcon);
		searchID.setAlignmentX(CENTER_ALIGNMENT);
		searchID.setFont(f);
		checkQuantity = new JButton("Check Tool Quantity",checkIcon);
		checkQuantity.setAlignmentX(CENTER_ALIGNMENT);
		checkQuantity.setFont(f);
		decreaseQuantity = new JButton("Decrease Tool Quantity", decreaseIcon);
		decreaseQuantity.setAlignmentX(CENTER_ALIGNMENT);
		decreaseQuantity.setFont(f);
		
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(listTools);
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(searchName);
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(searchID);
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(checkQuantity);
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(decreaseQuantity);
		buttonPanel.add(Box.createVerticalGlue());
		
		getContentPane().add("Center", buttonPanel);
	}
	
	
	public void addTitlePanel() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		Font f = new Font("Arial",Font.BOLD,30);
		JLabel title = new JLabel("MENU");
		title.setFont(f);
		titlePanel.add(title);
		getContentPane().add("North", titlePanel);
	}
	

	
}
