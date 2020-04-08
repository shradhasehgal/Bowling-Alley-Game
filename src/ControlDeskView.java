/* ControlDeskView.java
 *
 *  Version:
 *			$Id$
 * 
 *  Revisions:
 * 		$Log$
 * 
 */

/**
 * Class for representation of the control desk
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class ControlDeskView implements ActionListener {

	private JFrame win;

	/** The maximum  number of members in a party */
	private int maxMembers;
	private ControlDesk controlDesk;

	/**
	 * Displays a GUI representation of the ControlDesk
	 *
	 */

	public ControlDeskView(ControlDesk controlDesk, int maxMembers) {

		this.controlDesk = controlDesk;
		this.maxMembers = maxMembers;

		win = new JFrame("Control Desk");
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new BorderLayout());

		ControlDeskViewParty Party = new ControlDeskViewParty();
		controlDesk.subscribers.subscribe(Party);

		colPanel.add(new ControlDeskViewControl(this).getControlPanel(), "East");
		colPanel.add(new ControlDeskViewLane(controlDesk).getLaneStatusPanel(), "Center");
		colPanel.add(Party.getPartyPanel(), "West");
		win.getContentPane().add("Center", colPanel);
		Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.pack();
		win.setLocation(
				((screenSize.width) / 2) - ((win.getSize().width) / 2),
				((screenSize.height) / 2) - ((win.getSize().height) / 2));
		win.setVisible(true);
		/* Close program when this window closes */
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * Handler for actionEvents
	 *
	 * @param e	the ActionEvent that triggered the handler
	 *
	 */

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add Party")) {
			AddPartyView addPartyWin = new AddPartyView(this, maxMembers);
		}
		else if (e.getActionCommand().equals("Finished")) {
			win.setVisible(false);
			System.exit(0);
		}
	}

	/**
	 * Receive a new party from andPartyView.
	 *
	 * @param addPartyView	the AddPartyView that is providing a new party
	 *
	 */

	public void updateAddParty(AddPartyView addPartyView) {
		controlDesk.addPartyQueue(addPartyView.getParty());
	}

}
