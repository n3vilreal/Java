import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EditScreen extends JPanel {
    private JTextField nameField;
    private JTextField addressField;
    private JPasswordField passwordField;
    
    public EditScreen(MainApp app) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 500));
        setBackground(new Color(30, 32, 32));
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 10, 8, 10);
        
        JLabel title = new JLabel("Edit Profile");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(title, c);
        
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        c.gridy = 1;
        add(nameLabel, c);
        
        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 2;
        c.gridwidth = 2;
        add(nameField, c);
        
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setForeground(Color.WHITE);
        c.gridy = 3;
        c.gridwidth = 1;
        add(addressLabel, c);
        
        addressField = new JTextField(20);
        addressField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 4;
        c.gridwidth = 2;
        add(addressField, c);
        
        JLabel passLabel = new JLabel("Enter password to confirm:");
        passLabel.setForeground(Color.ORANGE);
        c.gridy = 5;
        c.gridwidth = 2;
        add(passLabel, c);
        
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 6;
        add(passwordField, c);
        
        JButton saveBtn = new JButton("Save Changes");
        saveBtn.setPreferredSize(new Dimension(150, 35));
        c.gridy = 7;
        add(saveBtn, c);
        
        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(150, 35));
        c.gridy = 8;
        add(backBtn, c);
        
        saveBtn.addActionListener(e -> saveChanges(app));
        backBtn.addActionListener(e -> app.showScreen("dashboard"));
    }
    
    public void loadData(String contact) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE contact = ?")) {
            
            ps.setString(1, contact);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                addressField.setText(rs.getString("address"));
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void saveChanges(MainApp app) {
        String name = nameField.getText();
        String address = addressField.getText();
        String password = new String(passwordField.getPassword());
        
        if (name.isEmpty() || address.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check password first
            PreparedStatement checkPs = conn.prepareStatement(
                "SELECT * FROM users WHERE contact = ? AND password = ?");
            checkPs.setString(1, app.currentUserContact);
            checkPs.setString(2, password);
            ResultSet rs = checkPs.executeQuery();
            
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Wrong password");
                return;
            }
            
            // Update data
            String sql = "UPDATE users SET name = ?, address = ? WHERE contact = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, app.currentUserContact);
            
            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Profile updated!");
                passwordField.setText("");
                app.showScreen("profile");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
