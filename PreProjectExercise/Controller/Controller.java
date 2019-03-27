package Controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Model.*;
import View.MyFrame;

/**
 * This class provides data fields and methods to create the controller for an application
 * to maintain student records. 
 * 
 * @author Wendy
 * @version 1.0
 * @since March 26, 2019
 *
 */
public class Controller {
	/**
	 * The GUI components for the application
	 */
	private MyFrame view;
	/**
	 * The data structure that holds the student records
	 */
	private BinSearchTree tree;
	
	/**
	 * Constructs a Controller object and assigns the specified frame and tree. Adds the ActionListener objects to the frame.
	 * @param v is the frame
	 * @param t is the tree
	 */
	public Controller(MyFrame v, BinSearchTree t) {
		view = v;
		tree = t;
		view.addListeners(new InsertListener(), new CreateTreeListener(), new BrowseListener(), new FindListener());
	}
	
	/**
	 * This class is the ActionListener for the Browse button in the application.
	 * @author Wendy
	 * @version 1.0
	 * @since March 26, 2019
	 *
	 */
	public class BrowseListener implements ActionListener {
	
		/**
		 * Displays the student records in the middle panel of the frame with a scroll bar.
		 */
		public void actionPerformed(ActionEvent e) {
				//System.out.println("Browse");
							
				String s = tree.print_tree(tree.getRoot());
				view.displayRecords(s);
				
		}
		
	}
	
	/**
	 * This class is the ActionListener for the Find button in the application.
	 * @author Wendy
	 * @version 1.0
	 * @since March 26, 2019
	 *
	 */
	public class FindListener implements ActionListener {
		
		/**
		 * Prompts the user to enter the student ID and if the student exists in the tree
		 * the information will be displayed. Otherwise gives a gessage that the record was
		 * not found.
		 */
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Find");
			
			String studentID = JOptionPane.showInputDialog("Please enter the student's id:");
			
			if(studentID != null) {
				Node node = tree.find(tree.getRoot(), studentID);
				if(node == null) {
					JOptionPane.showMessageDialog(null, "Record not found", "Error Message", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, node.toString());
				}
			}
		}
		
	}
	
	/**
	 * This class is the ActionListener for the Create Tree from File button in the application.
	 * @author Wendy
	 * @version 1.0
	 * @since March 26, 2019
	 *
	 */
	public class CreateTreeListener implements ActionListener {
		
		/**
		 * Asks user to enter the name of an input file and creates the tree using the data in the file.
		 * If the file is not found, the user is continually asked to enter an input file name until a valid name is provided.
		 */
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Create");
			tree.destroy();
			while(true) {
				try {
					String fileName = JOptionPane.showInputDialog("Enter the file name:");
					if(fileName == null) {
						break;
					}
					Scanner sc = new Scanner(new File(fileName)); 
					String  id,faculty, major, year;
					
					 while(sc.hasNext()) {
						 id = sc.next();
						 faculty = sc.next(); 
						 major = sc.next();
						 year = sc.next(); 
						 
						 tree.insert(id, faculty, major, year);
					 }
					 sc.close();
					 break;
				 }
				catch(FileNotFoundException fe) {
					JOptionPane.showMessageDialog(null, "File not found. Please try again.", "Error Message", JOptionPane.PLAIN_MESSAGE);
				}
			}

		}
		
	}
	/**
	 * This class is the ActionListener for the Insert button in the application.
	 * @author Wendy
	 * @version 1.0
	 * @since March 26, 2019
	 *
	 */
	public class InsertListener implements ActionListener {
		
		/**
		 * User is prompted to enter the information (id, faculty, major, year) and a new record is
		 * created and inserted into the tree. If some entries are missing the record is not created.
		 */
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Insert");
			
			JTextField idField = new JTextField(5);
		    JTextField facField = new JTextField(5);
		    JTextField majField = new JTextField(5);
		    JTextField yearField = new JTextField(5);
	
		    JPanel myPanel = new JPanel();
		    myPanel.setLayout(new BorderLayout());
		    
		    JPanel panel1 = new JPanel();
		    panel1.setLayout(new FlowLayout());
		    myPanel.add("North", panel1);
		    
		    JPanel panel2 = new JPanel();
		    panel2.setLayout(new FlowLayout());
		    myPanel.add("South", panel2);
		    
		    panel1.add( new JLabel("Enter the Student ID"));
		    panel1.add( idField);
		    panel1.add(new JLabel("Enter the Faculty"));
		    panel1.add(facField);
		    
		    panel2.add(new JLabel("Enter Student's Major"));
		    panel2.add(majField);
		    panel2.add(new JLabel("Enter year"));
		    panel2.add(yearField);
		    
		    String[] options = { "Insert", "Return to Main Window"};
	
		    int result = JOptionPane.showOptionDialog(null, myPanel, 
		               "Insert a New Node", JOptionPane.OK_CANCEL_OPTION, 1, null, options, options[1] );
		    
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	String studentID = idField.getText();
		    	String faculty = facField.getText();
		    	
		    	String major = majField.getText();
		    	String year = yearField.getText();
		    	
		    	if(studentID.equals("") || faculty.equals("") || major.equals("") || year.equals("")) {
					JOptionPane.showMessageDialog(null, "Missing Entries. Unable to Insert.", "Error Message", JOptionPane.PLAIN_MESSAGE);
		    	}
		    	else {
		    		tree.insert(studentID, faculty, major, year); 
		    	}
		    }
		}
	}
}
