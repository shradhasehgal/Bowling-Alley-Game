import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlDeskViewControl <T extends ActionListener>{

    private JPanel controlPanel;

    public ControlDeskViewControl(T MasterClass){
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1));
        controlPanel.setBorder(new TitledBorder("Controls"));
        Views.Button("Add Party", controlPanel).addActionListener(MasterClass);
        Views.Button("Finished", controlPanel).addActionListener(MasterClass);
    }

    public JPanel getControlPanel(){
        return controlPanel;
    }

}
