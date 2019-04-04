package client.clientcontroller;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import client.clientview.MyFrame;

/**
 * This class is the ActionListener for the List Suppliers button in a Tool Shop application.
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 */
public class ListSuppliersListener extends ListenerController {

	/**
	 * Constructs an ListSuppliersListener object and assigns the specified frame and client controller. 
	 * Adds the listener to the frame.
	 * @param view is the specified frame
	 * @param clientController is the specified client controller
	 */
	ListSuppliersListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	/**
	 * Displays a list of Suppliers. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = clientController.listSuppliers();
		
		JTextArea display = new JTextArea(15, 35);
        display.setEditable(false); 
        display.setText(s);
        Font font = new Font("Ariel", Font.PLAIN, 14);
        display.setFont(font);
        
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());
        
        inventoryPanel.add("Center", scroll);		
        
        String[] options = {"Return to MENU"};
    	
	    JOptionPane.showOptionDialog(null, inventoryPanel, 
	               "Supplier List", JOptionPane.OK_OPTION, 1, view.getListIcon(), options, options[0] );
		
	}
}
