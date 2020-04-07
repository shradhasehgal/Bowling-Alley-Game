import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ControlDeskViewButton<T extends ActionListener> {
    private JPanel panel;
    private JButton button;
    public ControlDeskViewButton (String ButtonName , T cdr){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        button = new JButton(ButtonName);
        button.addActionListener(cdr);
        panel.add(button);
    }

    public JPanel getPanel(){
        return panel;
    }
    public JButton getButton() {return button;}
}