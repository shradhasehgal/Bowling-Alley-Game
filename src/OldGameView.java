/* AddPartyView.java
 *
 *  Version:
 * 		 $Id$
 *
 *  Revisions:
 * 		$Log: AddPartyView.java,v $
 * 		Revision 1.7  2003/02/20 02:05:53  ???
 * 		Fixed addPatron so that duplicates won't be created.
 *
 * 		Revision 1.6  2003/02/09 20:52:46  ???
 * 		Added comments.
 *
 * 		Revision 1.5  2003/02/02 17:42:09  ???
 * 		Made updates to migrate to observer model.
 *
 * 		Revision 1.4  2003/02/02 16:29:52  ???
 * 		Added ControlDeskEvent and ControlDeskObserver. Updated Queue to allow access to Vector so that contents could be viewed without destroying. Implemented observer model for most of ControlDesk.
 *
 *
 */

/**
 * Class for GUI components need to add a party
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Constructor for GUI used to Add Parties to the waiting party queue.
 *
 */

public class OldGameView implements ActionListener, ListSelectionListener {


    private JFrame win, select;
    private JList allBowlers, allGames;
    private Vector bowlerdb, gamedb;
    private ControlDeskView controlDesk;

    private String selectedNick, selectedGame;

    public OldGameView(ControlDeskView controlDesk) {

        this.controlDesk = controlDesk;

        win = Views.InitializeWindow("Choose Patron");

        JPanel colPanel = new JPanel();
        colPanel.setLayout(new GridLayout(1, 3));

        // Bowler Database
        JPanel bowlerPanel = new JPanel();
        bowlerPanel.setLayout(new FlowLayout());
        bowlerPanel.setBorder(new TitledBorder("Bowler Database"));

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
        bowlerPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        allBowlers.addListSelectionListener(this);
        bowlerPanel.add(bowlerPane);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        Views.Button("Search", buttonPanel).addActionListener(this);

        // Clean up main panel
        colPanel.add(bowlerPanel);
        colPanel.add(buttonPanel);

        win.getContentPane().add("Center", colPanel);
        Views.CenterWindow(win);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Search")) {
            if (selectedNick != null) {
                select = Views.InitializeWindow("Choose game");

                JPanel colPanel = new JPanel();
                colPanel.setLayout(new GridLayout(1, 8));

                // Bowler Database
                JPanel bowlerPanel = new JPanel();
                bowlerPanel.setLayout(new GridLayout(1, 4));
                bowlerPanel.setBorder(new TitledBorder("Game Database"));

                try {
                    gamedb = new Vector(getGames());
                } catch (Exception q) {
                    gamedb = new Vector();
                }
                allGames = new JList(gamedb);
                allGames.setVisibleRowCount(8);
                allGames.setFixedCellWidth(400);
                JScrollPane bowlerPane = new JScrollPane(allGames);
                bowlerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                allGames.addListSelectionListener(this);
                bowlerPanel.add(bowlerPane);

                // Button Panel
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new GridLayout(1, 4));

                Views.Button("Select", buttonPanel).addActionListener(this);
                // Clean up main panel
                colPanel.add(bowlerPanel);
                colPanel.add(buttonPanel);

                select.getContentPane().add("Center", colPanel);
                Views.CenterWindow(select);
            }
        }

        else if(e.getActionCommand().equals("Select")){
            win.setVisible(false);
            select.setVisible(false);
        }

    }

    /**
     * Handler for List actions
     * @param e the ListActionEvent that triggered the handler
     */

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(allBowlers)) {
            selectedNick = ((String) ((JList) e.getSource()).getSelectedValue());
        }

        else if (e.getSource().equals(allGames)) {
            selectedGame = ((String) ((JList) e.getSource()).getSelectedValue());
        }

    }

    public Vector getGames() throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("./games.txt"));
        String data;
        Vector myVec = new Vector();
        while ((data = in.readLine()) != null) {
            String storedata = data;
            String[] players = data.split(" ");

            for(int i=1; i < players.length; i++)
            {
                if(players[i].equals(selectedNick)) {
                    myVec.add(data);
                }
            }

            for(int i=0; i < players.length; i++) {
                in.readLine();
                in.readLine();
            }
        }

        return myVec;
    }


}