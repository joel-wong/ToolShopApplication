package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import client.clientview.MyFrame;

public class SearchIDListener extends ListenerController {

	public SearchIDListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String toolID = (String) JOptionPane.showInputDialog(null, "Please enter the tool ID:", "Search for Tool", JOptionPane.OK_CANCEL_OPTION, view.getSearchIcon(), null, "");
		try {	
			if(toolID != null) {
				String response = clientController.searchInventory(Integer.parseInt(toolID));
				
			    JOptionPane.showMessageDialog(null, response, "Search for Tool", JOptionPane.INFORMATION_MESSAGE, view.getSearchIcon());
				
			}
		}
		catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Did not enter a valid tool ID number.", "Search for Tool", JOptionPane.ERROR_MESSAGE);
		}
				
	}
}
