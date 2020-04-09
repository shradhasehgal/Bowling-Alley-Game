
/**
 * Class for GUI components need to add a party
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;
import java.text.*;

/**
 * Constructor for GUI used to Add Parties to the waiting party queue.
 * 
 */

public class QueryView implements ActionListener, ListSelectionListener {

	private JFrame win;
	private Vector bowlerdb,temp= new Vector();
	private JButton ScoreReport;
	private JList allBowlers,ScoreHistoryList;

	private String selectedNick;
	
	@SuppressWarnings("unchecked")
	public QueryView() {

		win = new JFrame("Queries");
		win.getContentPane().setLayout(new BorderLayout());
		((JPanel) win.getContentPane()).setOpaque(false);

		JPanel colPanel = new JPanel();
		colPanel.setLayout(new GridLayout(1, 3));

		JPanel Stats = new JPanel();
		Stats.setLayout(new GridLayout(3, 1));
		Stats.setBorder(new TitledBorder("Stats"));

		JPanel TopPlayer = new JPanel();
		TopPlayer.setLayout(new FlowLayout());
		TopPlayer.setBorder(new TitledBorder("Top Player"));
		String topplayer = findmax.getDescScores().lastElement();
	    JLabel TopPlayerJLabel = new JLabel(topplayer);
	    TopPlayer.add(TopPlayerJLabel);

		
		
		JPanel LastPlayer = new JPanel();
		LastPlayer.setLayout(new FlowLayout());
		LastPlayer.setBorder(new TitledBorder("Last Player"));
		String lastplayer = findmax.getDescScores().firstElement();
		JLabel LastPlayerJLabel = new JLabel(lastplayer);
	    LastPlayer.add(LastPlayerJLabel);
		
		Stats.add(TopPlayer);
		Stats.add(LastPlayer);

		// Select Bowler to view ScoreReport of each individual player
		// Bowler Database
		JPanel bowlerPanel = new JPanel();
		bowlerPanel.setLayout(new FlowLayout());
		bowlerPanel.setBorder(new TitledBorder("Select Bowler"));

		try {
			bowlerdb = new Vector(BowlerFile.getBowlers());
		} catch (Exception e) {
			System.err.println("File Error");
			bowlerdb = new Vector();
		}
		allBowlers = new JList(bowlerdb);
		allBowlers.setVisibleRowCount(8);
		allBowlers.setFixedCellWidth(120);
		JScrollPane bowlerPane = new JScrollPane(allBowlers);
		bowlerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		allBowlers.addListSelectionListener(this);
		bowlerPanel.add(bowlerPane);

		ScoreReport = new JButton("View SCORE REPORT");
		JPanel viewScoreReport = new JPanel();
		viewScoreReport.setLayout(new FlowLayout());
		ScoreReport.addActionListener(this);
		viewScoreReport.add(ScoreReport);
		bowlerPanel.add(viewScoreReport);

		// Party Queue Panel
		JPanel ScoreResultPanel = new JPanel();
		ScoreResultPanel.setLayout(new GridLayout(1,1));
		ScoreResultPanel.setBorder(new TitledBorder("Score Report"));

		Vector empty = new Vector();
		empty.add("(Empty)");

		ScoreHistoryList = new JList(empty);
		ScoreHistoryList.setFixedCellWidth(120);
		ScoreHistoryList.setVisibleRowCount(10);
		JScrollPane ScoreHistoryPane = new JScrollPane(ScoreHistoryList);
		ScoreHistoryPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		ScoreHistoryPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScoreResultPanel.add(ScoreHistoryPane);	

		colPanel.add(Stats);
		colPanel.add(ScoreResultPanel);
		colPanel.add(bowlerPanel);
		win.getContentPane().add("Center", colPanel);

		win.pack();

		// Center Window on Screen
		Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
		win.setLocation(((screenSize.width) / 2) - ((win.getSize().width) / 2),
				((screenSize.height) / 2) - ((win.getSize().height) / 2));
		win.show();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(ScoreReport)) {
			temp.clear();
			//temp.add(selectedNick);
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader("SCOREHISTORY.DAT"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String data;
			try {
				while ((data = in.readLine()) != null) {
					if (selectedNick.equals(data.split("\t")[0])) {
						System.out.println(data);
						temp.add(data);
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ScoreHistoryList.setListData(temp);
		}
	}

	/**
	 * Handler for List actions
	 * 
	 * @param e the ListActionEvent that triggered the handler
	 */

	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource().equals(allBowlers)) {
			selectedNick =
				((String) ((JList) e.getSource()).getSelectedValue());
		}
	}

}
