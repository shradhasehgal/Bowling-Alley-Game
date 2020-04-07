import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ControlDeskViewParty implements ControlDeskObserver{

    private JList partyList;
    private JPanel partyPanel;

    public ControlDeskViewParty() {

        partyPanel = new JPanel();
        partyPanel.setLayout(new FlowLayout());
        partyPanel.setBorder(new TitledBorder("Party Queue"));

        var empty = new Vector();
        empty.add("(Empty)");

        partyList = new JList(empty);
        partyList.setFixedCellWidth(120);
        partyList.setVisibleRowCount(10);
        JScrollPane partyPane;
        partyPane = new JScrollPane(partyList);
        partyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        partyPanel.add(partyPane);

    }

    public void receiveControlDeskEvent(ControlDeskEvent ce) {
        partyList.setListData(((Vector) ce.getPartyQueue()));
    }
    public JPanel getPartyPanel(){
        return partyPanel;
    }
}
