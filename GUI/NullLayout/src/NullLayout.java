import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NullLayout{
    public static void main() {
        JFrame frame = new JFrame("Switch Color");
        JButton buttonWhite = new JButton("White");
        JButton buttonBlack = new JButton("Black");
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonWhite.setBounds(150,120, 100, 30);
        buttonBlack.setBounds(150,150,100,30);
        buttonBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"Color set to Black");
                frame.getContentPane().setBackground(Color.black);
            }
        });
        buttonWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"Color set to White");
                frame.getContentPane().setBackground(Color.white);
            }
        });
        frame.add(buttonWhite);
        frame.add(buttonBlack);
        frame.setVisible(true);
    }
}
