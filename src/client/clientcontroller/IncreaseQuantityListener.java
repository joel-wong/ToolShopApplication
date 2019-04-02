package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.clientview.MyFrame;

public class IncreaseQuantityListener extends ListenerController {

	public IncreaseQuantityListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField idField = new JTextField(5);
	    JTextField numField = new JTextField(5);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
	    panel.add( new JLabel("Please enter the tool ID:"));
	    panel.add( idField);
	    panel.add(new JLabel("Please enter the amount to add:"));
	    panel.add(numField);
	    
	    int result = JOptionPane.showOptionDialog(null, panel, 
	               "Increase Tool Quantity", JOptionPane.OK_CANCEL_OPTION, 1, view.getIncreaseIcon(), null, "");
	    
	    try {	
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	int toolID = Integer.parseInt(idField.getText());
		    	int amount = Integer.parseInt(numField.getText());
	
	    		String response = clientController.addTools(toolID, amount);
	    		JOptionPane.showMessageDialog(null, response, "Increase Tool Quantity", JOptionPane.INFORMATION_MESSAGE, view.getIncreaseIcon());
		    }
	    }
	    catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Did not enter a valid tool ID number or amount to add", "Increase Tool Quantity", JOptionPane.ERROR_MESSAGE);
		}
				
	}
		
	
}
