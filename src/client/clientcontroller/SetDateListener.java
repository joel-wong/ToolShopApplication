package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.clientview.MyFrame;

public class SetDateListener extends ListenerController {

	public SetDateListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField monthField = new JTextField(5);
	    JTextField dayField = new JTextField(5);
	    JTextField yearField = new JTextField(5);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
	    panel.add( new JLabel("Month:"));
	    panel.add( monthField);
	    panel.add(new JLabel("Day:"));
	    panel.add(dayField);
	    panel.add( new JLabel("Year:"));
	    panel.add(yearField);
    
	    int result = JOptionPane.showOptionDialog(null, panel, 
	               "Set New Date", JOptionPane.OK_CANCEL_OPTION, 1, view.getSetDateIcon(), null, "");
	    
	    try {	
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	String month = monthField.getText();
		    	int day = Integer.parseInt(dayField.getText());
		    	int year = Integer.parseInt(yearField.getText());
		    	
	
	    		String response = clientController.setNewDate(month, day, year);
	    		JOptionPane.showMessageDialog(null, response, "Set New Date", JOptionPane.INFORMATION_MESSAGE, view.getSetDateIcon());
		    }
	    }
	    catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null,"Error. Invalid Entries.", "Set New Date", JOptionPane.ERROR_MESSAGE);
		}
				
	}
		
	
}