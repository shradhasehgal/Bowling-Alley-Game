import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ControlDeskViewLane {

    private JPanel laneStatusPanel;

    public ControlDeskViewLane(ControlDesk controlDesk) {
        laneStatusPanel = new JPanel();
        laneStatusPanel.setLayout(new GridLayout(controlDesk.getNumLanes(), 1));
        laneStatusPanel.setBorder(new TitledBorder("Lane Status"));

        var lanes = controlDesk.getLanes();
        var it = lanes.iterator();
        int laneCount = 1;

        while (it.hasNext()) {
            Lane curLane = (Lane) it.next();
            LaneStatusView laneStat = new LaneStatusView(curLane, laneCount);
            curLane.subscribe(laneStat);
            ((Pinsetter) curLane.getPinsetter()).subscribe(laneStat);
            JPanel lanePanel = laneStat.showLane();
            lanePanel.setBorder(new TitledBorder("Lane" + laneCount++));
            laneStatusPanel.add(lanePanel);
        }
    }

    public JPanel getLaneStatusPanel(){
        return laneStatusPanel;
    }

}
