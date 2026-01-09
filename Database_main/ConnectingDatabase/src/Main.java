import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/conndb";
        String username="root";
        String password="nebil@123";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established.");
            conn.close();
        }
        catch(Exception e){
            System.out.println("Caught: " + e.getMessage());
        }
    }
}