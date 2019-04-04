package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.clientview.MyFrame;

/**
 * This class is the ActionListener for the Add New Tool button in a Tool Shop application.
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 */
public class AddToolListener extends ListenerController {

	/**
	 * Constructs an AddToolListener object and assigns the specified frame and client controller. 
	 * Adds the listener to the frame.
	* @param view is the specified frame
	 * @param clientController is the specified client controller
	 */
	public AddToolListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	/**
	 * User is prompted to enter new tool information and a new tool is created.
	 */
	public void actionPerformed(ActionEvent e) {
		JTextField idField = new JTextField(5);
	    JTextField nameField = new JTextField(5);
	    JTextField quantityField = new JTextField(5);
	    JTextField priceField = new JTextField(5);
	    JTextField supplierIDField = new JTextField(5);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
	    panel.add( new JLabel("Tool ID:"));
	    panel.add( idField);
	    panel.add(new JLabel("Tool Name:"));
	    panel.add(nameField);
	    panel.add( new JLabel("Tool Quantity:"));
	    panel.add( quantityField);
	    panel.add(new JLabel("Tool Price:"));
	    panel.add(priceField);
	    panel.add( new JLabel("Supplier ID:"));
	    panel.add( supplierIDField);
	    
	    int result = JOptionPane.showOptionDialog(null, panel, 
	               "Add New Tool", JOptionPane.OK_CANCEL_OPTION, 1, view.getAddToolIcon(), null, "");
	    
	    try {	
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	int toolID = Integer.parseInt(idField.getText());
		    	String name = nameField.getText();
		    	int quantity = Integer.parseInt(quantityField.getText());
		    	double price = Double.parseDouble(priceField.getText());
		    	int supplierID = Integer.parseInt(supplierIDField.getText());
		    	
	    		String response = clientController.addNewToolToInventory(toolID, name, quantity, price, supplierID);
	    		JOptionPane.showMessageDialog(null, response, "Add New Tool", JOptionPane.INFORMATION_MESSAGE, view.getAddToolIcon());
		    }
	    }
	    catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Invalid Entries.", "Add New Tool", JOptionPane.ERROR_MESSAGE);
		}		
	}	
}
