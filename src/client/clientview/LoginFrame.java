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


public class LoginFrame  extends JFrame{
	
	
	private JButton login;

	private ImageIcon loginIcon;
	
	
	public LoginFrame(String s) {
		super(s);
		setLayout(new BorderLayout());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tool Shop Application");
		createIcons();
		addLoginPanel();
		    
	}
	
	
	public void addListener(LoginListener l) {
		login.addActionListener(l);
	}
	
	public ImageIcon getLoginIcon() {
		return loginIcon;
	}
	
	public void createIcons() {
		
		loginIcon = new ImageIcon("loginIcon.png");
	}
	
	
	public void addLoginPanel() {
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

