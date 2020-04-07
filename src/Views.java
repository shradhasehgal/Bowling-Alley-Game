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

    public static JFrame CenterWindow(JFrame win){

        win.pack();
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.setVisible(true);
        return win;
    }

}