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

public class LaneStatusView implements ActionListener, LaneObserver, PinsetterObserver {

	private JPanel jp;

	private JLabel curBowler, pinsDown;
	private JButton viewLane, viewPinSetter, maintenance, pause;

	private PinSetterView psv;
	private LaneView lv;
	private Lane lane;
	int laneNum;

	boolean laneShowing;
	boolean psShowing;

	public LaneStatusView(Lane lane, int laneNum ) {

		this.lane = lane;
		this.laneNum = laneNum;

		laneShowing=false;
		psShowing=false;

		psv = new PinSetterView( laneNum );
		Pinsetter ps = lane.getPinsetter();
		ps.subscribe(psv);

		lv = new LaneView( lane, laneNum );
		lane.subscribe(lv);


		jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JLabel cLabel = new JLabel( "Now Bowling: " );
		curBowler = new JLabel( "(no one)" );
		JLabel pdLabel = new JLabel( "Pins Down: " );
		pinsDown = new JLabel( "0" );

		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		Insets buttonMargin = new Insets(4, 4, 4, 4);

		viewLane = Views.Button("View Lane", buttonPanel);
		viewLane.addActionListener(this);
		viewPinSetter = Views.Button("Pinsetter", buttonPanel);
		viewPinSetter.addActionListener(this);
		maintenance = Views.Button("     ", buttonPanel);
		maintenance.addActionListener(this);
		maintenance.setBackground( Color.GREEN );
		pause = Views.Button("Pause", buttonPanel);
		pause.addActionListener(this);
		viewLane.setEnabled( false );
		viewPinSetter.setEnabled( false );

		jp.add( cLabel );
		jp.add( curBowler );
		jp.add( pdLabel );
		jp.add( pinsDown );
		
		jp.add(buttonPanel);

	}

	public JPanel showLane() {
		return jp;
	}

	public void actionPerformed( ActionEvent e ) {
		if ( lane.isPartyAssigned() ) {
			ButtonCheck(e);
		}

	}

	public void ButtonCheck(ActionEvent  e)
	{
		if (e.getSource().equals(viewPinSetter)) {
			psShowing = !psShowing;
			psv.toggle(psShowing);
		}

		else if (e.getSource().equals(viewLane)) {
			if (!laneShowing) {
				lv.show();
				laneShowing=true;
			}

			else {
				lv.hide();
				laneShowing=false;
			}
		}
		else if (e.getSource().equals(maintenance)) {

			lane.unPauseGame();
			maintenance.setBackground( Color.GREEN );

		}

		else if (e.getSource().equals(pause))
			lane.pauseGame();

	}

	public void receiveLaneEvent(LaneEvent le) {
		curBowler.setText( ( (Bowler)le.getBowler()).getNickName() );
		if ( le.isMechanicalProblem() ) {
			maintenance.setBackground( Color.RED );
		}	
		if (!lane.isPartyAssigned()) {
			viewLane.setEnabled( false );
			viewPinSetter.setEnabled( false );
		} else {
			viewLane.setEnabled( true );
			viewPinSetter.setEnabled( true );
		}
	}

	public void receivePinsetterEvent(PinsetterEvent pe) {
		pinsDown.setText( (Integer.valueOf(pe.totalPinsDown())).toString() );

	}

}
