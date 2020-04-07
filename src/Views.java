import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Views {

    public static JButton Button(String ButtonName, JPanel MasterPanel){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton button = new JButton(ButtonName);
        panel.add(button);
        MasterPanel.add(panel);
        return button;
    }

    public static JFrame InitializeWindow(String WindowName){
        JFrame win = new JFrame(WindowName);
        win.getContentPane().setLayout(new BorderLayout());
        ((JPanel) win.getContentPane()).setOpaque(false);
        return win;
    }

    public static void CenterWindow(JFrame win){
        win.pack();
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.setVisible(true);
    }

    public static JTextField MiniPanel(String PanelName, JPanel MasterPanel){
        JPanel MiniPanel = new JPanel();
        MiniPanel.setLayout(new FlowLayout());
        JLabel Label = new JLabel(PanelName);
        JTextField Field = new JTextField("", 15);
        MiniPanel.add(Label);
        MiniPanel.add(Field);
        MasterPanel.add(MiniPanel);
        return Field;
    }

}