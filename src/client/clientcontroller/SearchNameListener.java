package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import client.clientview.MyFrame;

/**
 * This class is the ActionListener for the Search for Tool by Name button in a Tool Shop application.
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 */
public class SearchNameListener extends ListenerController {

	/**
	 * Constructs an SearchNameListener object and assigns the specified frame and client controller. 
	 * Adds the listener to the frame.
	 * @param view is the specified frame
	 * @param clientController is the specified client controller
	 */
	SearchNameListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	/**
	 * User is prompted to enter a Tool Name and the results of the search is displayed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String toolName = (String) JOptionPane.showInputDialog(null, "Please enter the tool name:", "Search for Tool", JOptionPane.OK_CANCEL_OPTION, view.getSearchIcon(), null, "");
		
		if(toolName != null) {
			String response = clientController.searchInventory(toolName);
			
		    JOptionPane.showMessageDialog(null, response, "Search for Tool", JOptionPane.INFORMATION_MESSAGE, view.getSearchIcon());
			
		}
		
	}
}
