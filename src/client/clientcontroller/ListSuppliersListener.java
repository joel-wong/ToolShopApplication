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

public class ListSuppliersListener extends ListenerController {

	public ListSuppliersListener(MyFrame view, ClientController clientController) {
		super(view, clientController);
		view.addListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = clientController.listSuppliers();
		
		JTextArea display = new JTextArea(15, 35);
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
	               "Supplier List", JOptionPane.OK_OPTION, 1, view.getListIcon(), options, options[0] );
		
	}
}
