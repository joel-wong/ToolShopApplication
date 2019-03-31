package client.clientcontroller;

import java.awt.event.ActionEvent;

import client.clientview.MyFrame;

public class SearchNameListener extends ListenerController {

	public SearchNameListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
