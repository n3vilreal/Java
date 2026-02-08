import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class ViewDatabaseScreen extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    
    public ViewDatabaseScreen(MainApp app) {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(30, 32, 32));
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        
        JLabel title = new JLabel("All Users");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        
        String[] columns = {"ID", "Name", "Contact", "Address"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(700, 400));
        c.gridy = 1;
        add(scroll, c);
        
        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(150, 35));
        c.gridy = 2;
        add(backBtn, c);
        
        backBtn.addActionListener(e -> app.showScreen("dashboard"));
    }
    
    public void loadData() {
        tableModel.setRowCount(0);
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("address")
                };
                tableModel.addRow(row);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
