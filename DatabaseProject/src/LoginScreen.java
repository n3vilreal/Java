import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginScreen extends JPanel {
    private JTextField contactField;
    private JPasswordField passwordField;
    
    public LoginScreen(MainApp app) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 500));
        setBackground(new Color(30, 32, 32));
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        
        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(title, c);
        
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        
        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setForeground(Color.WHITE);
        c.gridy = 1;
        add(contactLabel, c);
        
        contactField = new JTextField(20);
        contactField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 2;
        c.gridwidth = 2;
        add(contactField, c);
        
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        c.gridy = 3;
        c.gridwidth = 1;
        add(passLabel, c);
        
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 4;
        c.gridwidth = 2;
        add(passwordField, c);
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setPreferredSize(new Dimension(150, 35));
        c.gridy = 5;
        add(loginBtn, c);
        
        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(150, 35));
        c.gridy = 6;
        add(backBtn, c);
        
        loginBtn.addActionListener(e -> login(app));
        backBtn.addActionListener(e -> app.showScreen("home"));
    }
    
    private void login(MainApp app) {
        String contact = contactField.getText();
        String password = new String(passwordField.getPassword());
        
        if (contact.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            
            boolean found = false;
            while (rs.next()) {
                if (contact.equals(rs.getString("contact")) && 
                    password.equals(rs.getString("password"))) {
                    found = true;
                    app.setCurrentUser(contact, rs.getString("name"));
                    break;
                }
            }
            
            if (found) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                contactField.setText("");
                passwordField.setText("");
                app.showScreen("dashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
