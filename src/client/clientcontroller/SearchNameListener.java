package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import client.clientview.MyFrame;

public class SearchNameListener extends ListenerController {

	SearchNameListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String toolName = (String) JOptionPane.showInputDialog(null, "Please enter the tool name:", "Search for Tool", JOptionPane.OK_CANCEL_OPTION, view.getSearchIcon(), null, "");
		
		if(toolName != null) {
			String response = clientController.searchInventory(toolName);
			
		    JOptionPane.showMessageDialog(null, response, "Search for Tool", JOptionPane.INFORMATION_MESSAGE, view.getSearchIcon());
			
		}
		
	}
}
