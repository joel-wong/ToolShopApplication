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
}
