package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.clientview.MyFrame;

/**
 * This class is the ActionListener for the Increase Tool Quantity button in a Tool Shop application.
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 */
public class IncreaseQuantityListener extends ListenerController {

	/**
	 * Constructs an IncreaseQuantityListener object and assigns the specified frame and client controller. 
	 * Adds the listener to the frame.
	 * @param view is the specified frame
	 * @param clientController is the specified client controller
	 */
	IncreaseQuantityListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	/**
	 * User is prompted to enter a Tool ID number and the amount to increase the quantity by and the quantity of that tool is increased accordingly.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField idField = new JTextField(5);
	    JTextField numField = new JTextField(5);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
	    panel.add( new JLabel("Please enter the tool ID:"));
	    panel.add( idField);
	    panel.add(new JLabel("Please enter the amount to add:"));
	    panel.add(numField);
	    
	    int result = JOptionPane.showOptionDialog(null, panel, 
	               "Increase Tool Quantity", JOptionPane.OK_CANCEL_OPTION, 1, view.getIncreaseIcon(), null, "");
	    
	    try {	
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	int toolID = Integer.parseInt(idField.getText());
		    	int amount = Integer.parseInt(numField.getText());
	
	    		String response = clientController.addTools(toolID, amount);
	    		JOptionPane.showMessageDialog(null, response, "Increase Tool Quantity", JOptionPane.INFORMATION_MESSAGE, view.getIncreaseIcon());
		    }
	    }
	    catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Did not enter a valid tool ID number or amount to add", "Increase Tool Quantity", JOptionPane.ERROR_MESSAGE);
		}
	}
}
