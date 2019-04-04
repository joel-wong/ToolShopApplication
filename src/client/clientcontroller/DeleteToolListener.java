package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import client.clientview.MyFrame;

/**
 * This class is the ActionListener for the Delete Tool button in a Tool Shop application.
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 */
public class DeleteToolListener extends ListenerController {

	/**
	 * Constructs an DeleteToolListener object and assigns the specified frame and client controller. 
	 * Adds the listener to the frame.
	 * @param view is the specified frame
	 * @param clientController is the specified client controller
	 */
	DeleteToolListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	/**
	 * User is prompted to enter a Tool ID number and then deletes the specified Tool.
	 */
	public void actionPerformed(ActionEvent e) {
		String toolID = (String) JOptionPane.showInputDialog(null, "Please enter the tool ID:", "Delete Tool", JOptionPane.OK_CANCEL_OPTION, view.getDeleteIcon(), null, "");
		try {	
			if(toolID != null) {
				String response = clientController.deleteTool(Integer.parseInt(toolID));
				
			    JOptionPane.showMessageDialog(null, response, "Delete Tool", JOptionPane.INFORMATION_MESSAGE, view.getDeleteIcon());
				
			}
		}
		catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Did not enter a valid tool ID number.", "Delete Tool", JOptionPane.ERROR_MESSAGE);
		}
				
	}
}

