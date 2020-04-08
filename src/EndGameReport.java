/**
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

public class EndGameReport implements ActionListener, ListSelectionListener {

	private JFrame win;
	private JList memberList;
	private Vector retVal;

	private boolean result;

	private String selectedMember;

	public EndGameReport( String partyName, Party party ) {
	
		result = false;
		retVal = new Vector();
		win = new JFrame("End Game Report for " + partyName + "?" );
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new GridLayout( 1, 2 ));

		// Member Panel
		JPanel partyPanel = new JPanel();
		partyPanel.setLayout(new FlowLayout());
		partyPanel.setBorder(new TitledBorder("Party Members"));
		
		Vector myVector = new Vector();
		Iterator iter = (party.getMembers()).iterator();
		while (iter.hasNext()){
			myVector.add( ((Bowler)iter.next()).getNick() );
		}	
		memberList = new JList(myVector);
		memberList.setFixedCellWidth(120);
		memberList.setVisibleRowCount(5);
		memberList.addListSelectionListener(this);
		JScrollPane partyPane = new JScrollPane(memberList);
		partyPanel.add(partyPane);

		partyPanel.add( memberList );

		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));

		Insets buttonMargin = new Insets(4, 4, 4, 4);

		Views.Button("Print Report", buttonPanel).addActionListener(this);
		Views.Button("Finished", buttonPanel).addActionListener(this);

		// Clean up main panel
		colPanel.add(partyPanel);
		colPanel.add(buttonPanel);

		win.getContentPane().add("Center", colPanel);

		win.pack();

		// Center Window on Screen
		Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.setLocation(
			((screenSize.width) / 2) - ((win.getSize().width) / 2),
			((screenSize.height) / 2) - ((win.getSize().height) / 2));
		win.show();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Print Button")){
			//Add selected to the vector.
			retVal.add(selectedMember);
		}
		else if (e.getActionCommand().equals("Finished")){
			win.setVisible(false);
			result = true;
		}

	}

	public void valueChanged(ListSelectionEvent e) {
		selectedMember =
			((String) ((JList) e.getSource()).getSelectedValue());
	}

	public Vector getResult() {
		while (!result) {
			try {
				Thread.sleep(10);
			} catch ( InterruptedException e ) {
				System.err.println( "Interrupted" );
			}
		}
		return retVal;	
	}
	
}

