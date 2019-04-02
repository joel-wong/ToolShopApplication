package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.clientview.MyFrame;

public class AddSupplierListener extends ListenerController {

	public AddSupplierListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField idField = new JTextField(5);
	    JTextField nameField = new JTextField(5);
	    JTextField addressField = new JTextField(5);
	    JTextField contactField = new JTextField(5);
	    
	   
	    
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
	    panel.add( new JLabel("Supplier ID:"));
	    panel.add( idField);
	    panel.add(new JLabel("Company Name:"));
	    panel.add(nameField);
	    panel.add( new JLabel("Address:"));
	    panel.add(addressField);
	    panel.add(new JLabel("Sales Contact:"));
	    panel.add(contactField);
	  
	    
	    int result = JOptionPane.showOptionDialog(null, panel, 
	               "Add New Supplier", JOptionPane.OK_CANCEL_OPTION, 1, view.getAddSupplierIcon(), null, "");
	    
	    try {	
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	int supplierID = Integer.parseInt(idField.getText());
		    	String name = nameField.getText();
		    	String address = addressField.getText();
		    	String contact = contactField.getText();
		    	
	
	    		String response = clientController.addNewSupplier(supplierID, name, address, contact);
	    		JOptionPane.showMessageDialog(null, response, "Add New Supplier", JOptionPane.INFORMATION_MESSAGE, view.getAddSupplierIcon());
		    }
	    }
	    catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Invalid Entries.", "Add New Supplier", JOptionPane.ERROR_MESSAGE);
		}
				
	}
		
	
}
