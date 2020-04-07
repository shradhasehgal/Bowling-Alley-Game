/* AddPartyView.java
 *
 *  Version
 *  $Id$
 * 
 *  Revisions:
 * 		$Log: NewPatronView.java,v $
 * 		Revision 1.3  2003/02/02 16:29:52  ???
 * 		Added ControlDeskEvent and ControlDeskObserver. Updated Queue to allow access to Vector so that contents could be viewed without destroying. Implemented observer model for most of ControlDesk.
 * 		
 * 
 */

/**
 * Class for GUI components need to add a patron
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

public class NewPatronView implements ActionListener {

	private JFrame win;
	private JLabel nickLabel, fullLabel, emailLabel;
	private JTextField nickField, fullField, emailField;
	private String nick, full, email;
	private boolean done;
	private AddPartyView addParty;

	public NewPatronView(AddPartyView v) {

		addParty=v;	
		done = false;

		win = Views.InitializeWindow("Add Patron");

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new BorderLayout());

		// Patron Panel
		JPanel patronPanel = new JPanel();
		patronPanel.setLayout(new GridLayout(3, 1));
		patronPanel.setBorder(new TitledBorder("Your Info"));

		JPanel nickPanel = new JPanel();
		nickPanel.setLayout(new FlowLayout());
		nickLabel = new JLabel("Nick Name");
		nickField = new JTextField("", 15);
		nickPanel.add(nickLabel);
		nickPanel.add(nickField);

		JPanel fullPanel = new JPanel();
		fullPanel.setLayout(new FlowLayout());
		fullLabel = new JLabel("Full Name");
		fullField = new JTextField("", 15);
		fullPanel.add(fullLabel);
		fullPanel.add(fullField);

		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new FlowLayout());
		emailLabel = new JLabel("E-Mail");
		emailField = new JTextField("", 15);
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);

		patronPanel.add(nickPanel);
		patronPanel.add(fullPanel);
		patronPanel.add(emailPanel);

		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1));

		Insets buttonMargin = new Insets(4, 4, 4, 4);

		Views.Button("Add Patron",buttonPanel).addActionListener(this);
		Views.Button("Abort", buttonPanel).addActionListener(this);
		// Clean up main panel
		colPanel.add(patronPanel, "Center");
		colPanel.add(buttonPanel, "East");

		win.getContentPane().add("Center", colPanel);

		win = Views.CenterWindow(win);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Abort")) {
			done = true;
			win.hide();
		}

		if (e.getActionCommand().equals("Add Patron")) {
			nick = nickField.getText();
			full = fullField.getText();
			email = emailField.getText();
			done = true;
			addParty.updateNewPatron( this );
			win.hide();
		}

	}

	public boolean done() {
		return done;
	}

	public String getNick() {
		return nick;
	}

	public String getFull() {
		return full;
	}

	public String getEmail() {
		return email;
	}

}
