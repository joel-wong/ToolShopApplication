package client.clientcontroller;

import java.awt.event.ActionEvent;

import client.clientview.MyFrame;

public class SearchIDListener extends ListenerController {

	public SearchIDListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
