import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SignupScreen extends JPanel {
    private JTextField nameField;
    private JTextField contactField;
    private JTextField addressField;
    private JPasswordField passwordField;
    
    public SignupScreen(MainApp app) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 550));
        setBackground(new Color(30, 32, 32));
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 10, 8, 10);
        
        JLabel title = new JLabel("Signup");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(title, c);
        
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        
        addField("Name:", nameField = new JTextField(20), c, 1);
        addField("Contact:", contactField = new JTextField(20), c, 3);
        addField("Address:", addressField = new JTextField(20), c, 5);
        
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        c.gridy = 7;
        add(passLabel, c);
        
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 8;
        c.gridwidth = 2;
        add(passwordField, c);
        
        JButton signupBtn = new JButton("Signup");
        signupBtn.setPreferredSize(new Dimension(150, 35));
        c.gridy = 9;
        add(signupBtn, c);
        
        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(150, 35));
        c.gridy = 10;
        add(backBtn, c);
        
        signupBtn.addActionListener(e -> signup(app));
        backBtn.addActionListener(e -> app.showScreen("home"));
    }
    
    private void addField(String label, JTextField field, GridBagConstraints c, int row) {
        JLabel lbl = new JLabel(label);
        lbl.setForeground(Color.WHITE);
        c.gridy = row;
        c.gridwidth = 1;
        add(lbl, c);
        
        field.setPreferredSize(new Dimension(200, 30));
        c.gridy = row + 1;
        c.gridwidth = 2;
        add(field, c);
    }
    
    private void signup(MainApp app) {
        String name = nameField.getText();
        String contact = contactField.getText();
        String address = addressField.getText();
        String password = new String(passwordField.getPassword());
        
        if (name.isEmpty() || contact.isEmpty() || address.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (name, password, contact, address) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, contact);
            ps.setString(4, address);
            
            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Signup successful!");
                nameField.setText("");
                contactField.setText("");
                addressField.setText("");
                passwordField.setText("");
                app.showScreen("login");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
