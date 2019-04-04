package client.clientview;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import client.clientcontroller.LoginListener;

/**
 * This class provides data fields and methods to create the login GUI for a Tool Shop Application.
 * 
 * @author Wenjia Yang
 * @version 1.0
 * @since April 4, 2019
 *
 */
public class LoginFrame  extends JFrame{
	
	/**
	 * Button to login
	 */
	private JButton login;

	/**
	 * Login icon
	 */
	private ImageIcon loginIcon;
	
	/**
	 * Contructs a LoginFrame object and creates the Icon and adds the login button panel.
	 * @param s
	 */
	public LoginFrame(String s) {
		super(s);
		setLayout(new BorderLayout());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tool Shop Application");
		createIcons();
		addLoginPanel();	    
	}
	
	/**
	 * Adds the ActionListener to the login button. 
	 * @param l is the LoginListener
	 */
	public void addListener(LoginListener l) {
		login.addActionListener(l);
	}
	
	/**
	 * Returns the loginIcon.
	 * @return the loginIcon
	 */
	public ImageIcon getLoginIcon() {
		return loginIcon;
	}

	/**
	 * Creates the icons.
	 */
	private void createIcons() {
		loginIcon = new ImageIcon("loginIcon.png");
	}
	
	/**
	 * Creates and adds the login button to the frame.
	 */
	private void addLoginPanel() {
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(169, 231, 252));
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		login = new JButton("Login", loginIcon);
		
		login.setAlignmentX(CENTER_ALIGNMENT);
		
		loginPanel.add(Box.createVerticalGlue());
		loginPanel.add(login);
		loginPanel.add(Box.createVerticalGlue());
		getContentPane().add("Center", loginPanel);
	}
	
	
	

	
}

