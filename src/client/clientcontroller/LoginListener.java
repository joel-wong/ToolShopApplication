package client.clientcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.clientview.LoginFrame;
import client.clientview.MyFrame;

/**
 * This class is the ActionListener for the login button in a Tool Shop application.
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 */
public class LoginListener implements ActionListener{
	/**
	 * The GUI components for the login frame of the application.
	 */
	private LoginFrame loginView;
	/**
	 * The controller that makes requests to the server.
	 */
	private ClientController clientController;
	
	/**
	 * Constructs an LoginListener object and assigns the specified login frame and client controller. 
	 * Adds the listener to the login frame.
	 * @param loginView is the specified login frame
	 * @param clientController is the specified client controller
	 */
	public LoginListener(LoginFrame loginView, ClientController clientController) {
		this.loginView = loginView;
		this.clientController = clientController;
		loginView.addListener(this);
	}

	@Override
	/**
	 * User is prompted to enter a username and password and if correct the menu frame is created. 
	 */
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
	               "Login", JOptionPane.OK_CANCEL_OPTION, 1, loginView.getLoginIcon(), null, "");
	    
	   	
	    if (result == JOptionPane.OK_OPTION) {
	    	
	    	String user = userField.getText();
	    	String pass = passField.getText();
	    	boolean successfulLogin = clientController.login(user, pass);
    		String response = "";
    		if(successfulLogin) {
    			response = "Successfully logged in.";
    			JOptionPane.showMessageDialog(null, response, "Login", JOptionPane.INFORMATION_MESSAGE, loginView.getLoginIcon());
    			loginView.setVisible(false);
    			
    			MyFrame newView = new MyFrame("Frame 2");
    			
    			newView.setAutomaticLogout(loginView);
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
    	        ListOrdersListener listOrdersListener = new ListOrdersListener(newView, clientController);
    			
    		}
    		else {
    			response = "Wrong Username or Password.";
    			JOptionPane.showMessageDialog(null, response, "Login", JOptionPane.ERROR_MESSAGE);
    		}
	    }
	   
				
	}
		
	
}
