package client.clientcontroller;
import java.awt.event.ActionListener;

import client.clientview.MyFrame;

abstract public class ListenerController implements ActionListener{
	protected MyFrame view;
	protected ClientController clientController;
	
	public ListenerController(MyFrame view, ClientController clientController) {
		this.view = view;
		this.clientController = clientController;
	}

	
	public static void main(String[] args) {
		Client client = new Client("localhost", 9898);
		ClientController clientController = new ClientController(client);
		MyFrame view = new MyFrame("Frame 1");
		
		ListToolsListener listToolsListener = new ListToolsListener(view, clientController);
		SearchNameListener searchNameListener = new SearchNameListener(view, clientController);
		SearchIDListener searchIDListener = new SearchIDListener(view, clientController);
		CheckQuantityListener checkQuantityListener = new CheckQuantityListener(view, clientController);
		DecreaseQuantityListener decreaseQuantityListener = new DecreaseQuantityListener(view, clientController);
		
		view.setVisible(true);
	}
	
	
}
