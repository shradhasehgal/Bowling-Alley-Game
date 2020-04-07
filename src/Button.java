import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Button<T extends ActionListener> {

    private JPanel panel;
    private JButton button;

    public Button(String ButtonName, JPanel MasterPanel, T MasterClass){
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        button = new JButton(ButtonName);
        button.addActionListener(MasterClass);
        panel.add(button);
        MasterPanel.add(panel);
    }

    public JButton getButton() {
        return button;
    }
}