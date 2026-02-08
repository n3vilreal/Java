import javax.swing.*;
import java.awt.*;

public class DashboardScreen extends JPanel {
    
    public DashboardScreen(MainApp app) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(400, 500));
        setBackground(new Color(30, 32, 32));
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        
        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        
        JButton profileBtn = new JButton("View Profile");
        profileBtn.setPreferredSize(new Dimension(180, 40));
        c.gridy = 1;
        add(profileBtn, c);
        
        JButton editBtn = new JButton("Edit Profile");
        editBtn.setPreferredSize(new Dimension(180, 40));
        c.gridy = 3;
        add(editBtn, c);
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setPreferredSize(new Dimension(150, 35));
        c.gridy = 4;
        add(logoutBtn, c);
        
        profileBtn.addActionListener(e -> app.showScreen("profile"));
        editBtn.addActionListener(e -> app.showScreen("edit"));
        logoutBtn.addActionListener(e -> app.showScreen("home"));
    }
}
