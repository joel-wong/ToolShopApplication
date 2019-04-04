package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import client.clientview.MyFrame;

public class CheckQuantityListener extends ListenerController {

	CheckQuantityListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String toolID = (String) JOptionPane.showInputDialog(null, "Please enter the tool ID:", "Check Tool Quantity", JOptionPane.OK_CANCEL_OPTION, view.getCheckIcon(), null, "");
		try {	
			if(toolID != null) {
				String response = clientController.checkToolQuantity(Integer.parseInt(toolID));
				
			    JOptionPane.showMessageDialog(null, response, "Check Tool Quantity", JOptionPane.INFORMATION_MESSAGE, view.getCheckIcon());
				
			}
		}
		catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Did not enter a valid tool ID number.", "Check Tool Quantity", JOptionPane.ERROR_MESSAGE);
		}
	}
}
