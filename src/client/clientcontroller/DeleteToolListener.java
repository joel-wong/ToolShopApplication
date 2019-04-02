package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import client.clientview.MyFrame;

public class DeleteToolListener extends ListenerController {

	public DeleteToolListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String toolID = (String) JOptionPane.showInputDialog(null, "Please enter the tool ID:", "Delete Tool", JOptionPane.OK_CANCEL_OPTION, view.getDeleteIcon(), null, "");
		try {	
			if(toolID != null) {
				String response = clientController.deleteTool(Integer.parseInt(toolID));
				
			    JOptionPane.showMessageDialog(null, response, "Delete Tool", JOptionPane.INFORMATION_MESSAGE, view.getDeleteIcon());
				
			}
		}
		catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Did not enter a valid tool ID number.", "Delete Tool", JOptionPane.ERROR_MESSAGE);
		}
				
	}
}

