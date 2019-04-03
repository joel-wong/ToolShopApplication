package client.clientcontroller;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.clientview.MyFrame;

public class LoginListener extends ListenerController {

	public LoginListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField userField = new JTextField(5);
	    JTextField passField = new JPasswordField(5);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
	    panel.add( new JLabel("Username:"));
	    panel.add(userField);
	    panel.add(new JLabel("Password:"));
	    panel.add(passField);
	    
	    int result = JOptionPane.showOptionDialog(null, panel, 
	               "Login", JOptionPane.OK_CANCEL_OPTION, 1, view.getLoginIcon(), null, "");
	    
	   	
	    if (result == JOptionPane.OK_OPTION) {
	    	
	    	String user = userField.getText();
	    	String pass = passField.getText();
	    	boolean successfulLogin = clientController.login(user, pass);
    		String response = "";
    		if(successfulLogin) {
    			response = "Successfully logged in.";
    			JOptionPane.showMessageDialog(null, response, "Login", JOptionPane.INFORMATION_MESSAGE, view.getLoginIcon());
    			view.setVisible(false);
    			
    			MyFrame newView = new MyFrame("Frame 2");
    			newView.addButtonPanel();
    			newView.addTitlePanel();
    			newView.pack();
    			newView.setVisible(true);
    			ListToolsListener listToolsListener = new ListToolsListener(newView, clientController);
    	        ListSuppliersListener listSuppliersListener = new ListSuppliersListener(newView, clientController);
    	        SearchNameListener searchNameListener = new SearchNameListener(newView, clientController);
    	        SearchIDListener searchIDListener = new SearchIDListener(newView, clientController);
    	        CheckQuantityListener checkQuantityListener = new CheckQuantityListener(newView, clientController);
    	        DecreaseQuantityListener decreaseQuantityListener = new DecreaseQuantityListener(newView, clientController);
    	        IncreaseQuantityListener increaseQuantityListener = new IncreaseQuantityListener(newView, clientController);
    	        AddToolListener addToolListener = new AddToolListener(newView, clientController);
    	        DeleteToolListener deleteToolListener = new DeleteToolListener(newView, clientController);
    	        AddSupplierListener addSupplierListener = new AddSupplierListener(newView, clientController);
    	        SetDateListener setDateListener = new SetDateListener(newView, clientController);
    			
    		}
    		else {
    			response = "Wrong Username or Password.";
    			JOptionPane.showMessageDialog(null, response, "Login", JOptionPane.ERROR_MESSAGE);
    		}
	    }
	   
				
	}
		
	
}