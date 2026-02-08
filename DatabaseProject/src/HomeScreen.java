import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    
    public HomeScreen(MainApp app) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(400, 500));
        setBackground(new Color(30, 32, 32));
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        
        JLabel title = new JLabel("Welcome");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setPreferredSize(new Dimension(180, 40));
        c.gridy = 1;
        add(loginBtn, c);
        
        JButton signupBtn = new JButton("Signup");
        signupBtn.setPreferredSize(new Dimension(180, 40));
        c.gridy = 2;
        add(signupBtn, c);
        
        loginBtn.addActionListener(e -> app.showScreen("login"));
        signupBtn.addActionListener(e -> app.showScreen("signup"));
    }
}
