package client.clientcontroller;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import client.clientview.MyFrame;

public class ListOrdersListener extends ListenerController {

	public ListOrdersListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = clientController.listOrders();
		
		JTextArea display = new JTextArea(25, 45);
        display.setEditable(false); 
        display.setText(s);
        Font font = new Font("Ariel", Font.PLAIN, 14);
        display.setFont(font);
        
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());
        
        inventoryPanel.add("Center", scroll);		
        
        String[] options = {"Return to MENU"};
    	
	    JOptionPane.showOptionDialog(null, inventoryPanel, 
	               "Inventory", JOptionPane.OK_OPTION, 1, view.getListIcon(), options, options[0] );
		
	}
}
