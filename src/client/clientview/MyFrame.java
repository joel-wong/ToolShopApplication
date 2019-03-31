package client.clientview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
	
	
	public MyFrame(String s) {
		super(s);
		setLayout(new BorderLayout());
		setSize(500,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Window");
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
	
	
	public void addButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		listTools = new JButton("List all Tools");
		listTools.setAlignmentX(CENTER_ALIGNMENT);
		searchName = new JButton("Search for Tool by Name");
		searchName.setAlignmentX(CENTER_ALIGNMENT);
		searchID = new JButton("Search for Tool by ID");
		searchID.setAlignmentX(CENTER_ALIGNMENT);
		checkQuantity = new JButton("Check Tool Quantity");
		checkQuantity.setAlignmentX(CENTER_ALIGNMENT);
		decreaseQuantity = new JButton("Decrease Tool Quantity");
		decreaseQuantity.setAlignmentX(CENTER_ALIGNMENT);
		
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
		JLabel title = new JLabel("MENU");
		titlePanel.add(title);
		getContentPane().add("North", titlePanel);
	}
	

	
}
