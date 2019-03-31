package client.clientcontroller;

import java.awt.event.ActionEvent;

import client.clientview.MyFrame;

public class CheckQuantityListener extends ListenerController {

	public CheckQuantityListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
