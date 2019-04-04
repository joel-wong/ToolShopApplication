package client.clientcontroller;
import java.awt.event.ActionListener;

import client.clientview.MyFrame;

/**
 * This abstract class that represents an action listener for a button in the GUI for a Tool Shop Application
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 *
 */
abstract public class ListenerController implements ActionListener{
	/**
	 * The GUI components for the menu frame of the application.
	 */
	protected MyFrame view;
	/**
	 * The controller that makes requests to the server.
	 */
	protected ClientController clientController;
	
	/**
	 * Constructs a ListenerController object and assigns the specified frame and client controller. 
	  * @param view is the specified frame
	 * @param clientController is the specified client controller
	 */
	public ListenerController(MyFrame view, ClientController clientController) {
		this.view = view;
		this.clientController = clientController;
	}
}
