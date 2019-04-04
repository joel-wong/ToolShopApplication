package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import client.clientview.MyFrame;

/**
 * This class is the ActionListener for the Check Tool Quantity button in a Tool Shop application.
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 */
public class CheckQuantityListener extends ListenerController {

	/**
	 * Constructs an CheckQuantityListener object and assigns the specified frame and client controller. 
	 * Adds the listener to the frame.
	 * @param view is the specified frame
	 * @param clientController is the specified client controller
	 */
	CheckQuantityListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	/**
	 * User is prompted to enter a Tool ID number and the quantity of that tool is displayed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String toolID = (String) JOptionPane.showInputDialog(null, "Please enter the tool ID:", "Check Tool Quantity", JOptionPane.OK_CANCEL_OPTION, view.getCheckIcon(), null, "");
		try {	
			if(toolID != null) {
				String response = clientController.checkToolQuantity(Integer.parseInt(toolID));
				
			    JOptionPane.showMessageDialog(null, response, "Check Tool Quantity", JOptionPane.INFORMATION_MESSAGE, view.getCheckIcon());	
			}
		}
		catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Did not enter a valid tool ID number.", "Check Tool Quantity", JOptionPane.ERROR_MESSAGE);
		}
	}
}
