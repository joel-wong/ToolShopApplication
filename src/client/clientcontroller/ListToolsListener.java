package client.clientcontroller;

import java.awt.event.ActionEvent;

import client.clientview.MyFrame;

public class ListToolsListener extends ListenerController {

	public ListToolsListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = clientController.listTools();
		
	}
}
