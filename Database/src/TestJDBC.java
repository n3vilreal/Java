import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "javauser";
            String password = "javapass";

            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
