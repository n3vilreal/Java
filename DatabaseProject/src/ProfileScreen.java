import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ProfileScreen extends JPanel {
    private JLabel nameLabel;
    private JLabel contactLabel;
    private JLabel addressLabel;
    
    public ProfileScreen(MainApp app) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 500));
        setBackground(new Color(30, 32, 32));
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        
        JLabel title = new JLabel("My Profile");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(title, c);
        
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        
        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setForeground(Color.LIGHT_GRAY);
        c.gridy = 1;
        add(nameLbl, c);
        
        nameLabel = new JLabel("");
        nameLabel.setForeground(Color.WHITE);
        c.gridx = 1;
        add(nameLabel, c);
        
        JLabel contactLbl = new JLabel("Contact:");
        contactLbl.setForeground(Color.LIGHT_GRAY);
        c.gridx = 0;
        c.gridy = 2;
        add(contactLbl, c);
        
        contactLabel = new JLabel("");
        contactLabel.setForeground(Color.WHITE);
        c.gridx = 1;
        add(contactLabel, c);
        
        JLabel addressLbl = new JLabel("Address:");
        addressLbl.setForeground(Color.LIGHT_GRAY);
        c.gridx = 0;
        c.gridy = 3;
        add(addressLbl, c);
        
        addressLabel = new JLabel("");
        addressLabel.setForeground(Color.WHITE);
        c.gridx = 1;
        add(addressLabel, c);
        
        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(150, 35));
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(backBtn, c);
        
        backBtn.addActionListener(e -> app.showScreen("dashboard"));
    }
    
    public void loadProfile(String contact) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE contact = ?")) {
            
            ps.setString(1, contact);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                nameLabel.setText(rs.getString("name"));
                contactLabel.setText(rs.getString("contact"));
                addressLabel.setText(rs.getString("address"));
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
