package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import client.clientview.MyFrame;

/**
 * This class is the ActionListener for the Search for Tool by ID button in a Tool Shop application.
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 */
public class SearchIDListener extends ListenerController {

	/**
	 * Constructs an SearchIDListener object and assigns the specified frame and client controller. 
	 * Adds the listener to the frame.
	 * @param view is the specified frame
	 * @param clientController is the specified client controller
	 */
	public SearchIDListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	/**
	 * User is prompted to enter a Tool ID number and the results of the search is displayed.
	 */
	public void actionPerformed(ActionEvent e) {
		String toolID = (String) JOptionPane.showInputDialog(null, "Please enter the tool ID:", "Search for Tool", JOptionPane.OK_CANCEL_OPTION, view.getSearchIcon(), null, "");
		try {	
			if(toolID != null) {
				String response = clientController.searchInventory(Integer.parseInt(toolID));
				
			    JOptionPane.showMessageDialog(null, response, "Search for Tool", JOptionPane.INFORMATION_MESSAGE, view.getSearchIcon());
				
			}
		}
		catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Did not enter a valid tool ID number.", "Search for Tool", JOptionPane.ERROR_MESSAGE);
		}
				
	}
}
