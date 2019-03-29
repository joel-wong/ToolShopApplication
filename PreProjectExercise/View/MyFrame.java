package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;


import Controller.Controller.*;

/**
 * This class provides data fields and methods to create the GUI for an application
 * to maintain student records. 
 * 
 * @author Wenjia Yang and Joel Wong
 * @version 1.0
 * @since March 26, 2019
 *
 */
public class MyFrame extends JFrame {
	/**
	 * Button to insert a new record.
	 */
	private JButton b1;
	/**
	 * Button to find a record.
	 */
	private JButton b2;
	/**
	 * Button to browse all records.
	 */
	private JButton b3;
	/**
	 * Button to create a tree from file.
	 */
	private JButton b4;
	
	/**
	 * Middle panel of the GUI.
	 */
	private JPanel middlePanel;
	
	/**
	 * Constructs a myFrame object with the specified title of the frame.
	 * @param s is the title
	 */
	public MyFrame(String s) {
		super(s);
		setLayout(new BorderLayout());
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Window");
		addButtonPanel();
		addTitlePanel();
		addMiddlePanel();
		
	}
	
	/**
	 * Returns the middlePanel of the frame.
	 * @return the middle panel
	 */
	public JPanel getMiddlePanel() {
		return middlePanel;
	}
	
	/**
	 * Assigns Action Listener objects to the buttons in the frame.
	 * @param insListener is responsible for the listening to the insert button
	 * @param creListener is responsible for the listening to the create tree from file button
	 * @param broListener is responsible for the listening to the browse button
	 * @param finListener is responsible for the listening to the find button
	 */
	public void addListeners(InsertListener insListener, CreateTreeListener creListener, BrowseListener broListener, FindListener finListener) {

		b1.addActionListener(insListener);
		b2.addActionListener(finListener);
		b3.addActionListener(broListener);
		b4.addActionListener(creListener);
	}
	
	/**
	 * Creates the button components and adds them to the frame.
	 */
	public void addButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		
		b1 = new JButton("Insert");
		b2 = new JButton("Find");
		b3 = new JButton("Browse");
		b4 = new JButton("Create Tree from File");
		
		buttonPanel.add(b1);
		buttonPanel.add(b2);
		buttonPanel.add(b3);
		buttonPanel.add(b4);
		
		getContentPane().add("South", buttonPanel);
	}
	
	/**
	 * Creates the title panel and adds it to the frame.
	 */
	public void addTitlePanel() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel title = new JLabel("An Application to Maintain Student Records");
		titlePanel.add(title);
		getContentPane().add("North", titlePanel);
	}
	
	/**
	 * Creates the middle panel and adds it to the frame.
	 */
	public void addMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setBackground(Color.WHITE);
		getContentPane().add("Center", middlePanel);
	}
	
	/**
	 * Displays the specified string in a text area in the middle panel with a scroll bar.
	 * @param s is the specified string
	 */
	public void displayRecords(String s) {
		JTextArea display = new JTextArea(15, 45);
        display.setEditable(false); 
        display.setText(s);
        Font font = new Font("Courier", Font.PLAIN, 14);
        display.setFont(font);
        
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        middlePanel.removeAll();
        middlePanel.add(scroll);		
		pack();
	}
	
	
	
	
}
