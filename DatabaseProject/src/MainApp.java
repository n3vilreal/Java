import javax.swing.*;
import java.awt.*;

public class MainApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Screens
    private HomeScreen homeScreen;
    private LoginScreen loginScreen;
    private SignupScreen signupScreen;
    private DashboardScreen dashboardScreen;
    private ProfileScreen profileScreen;
    private EditScreen editScreen;
    
    // User data
    public String currentUserContact;
    public String currentUserName;
    
    public MainApp() {
        frame = new JFrame("User Management");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(80, 84, 84));
        
        // Creating all screens
        homeScreen = new HomeScreen(this);
        loginScreen = new LoginScreen(this);
        signupScreen = new SignupScreen(this);
        dashboardScreen = new DashboardScreen(this);
        profileScreen = new ProfileScreen(this);
        editScreen = new EditScreen(this);
        
        // Adding screens to layout
        mainPanel.add(wrapPanel(homeScreen), "home");
        mainPanel.add(wrapPanel(loginScreen), "login");
        mainPanel.add(wrapPanel(signupScreen), "signup");
        mainPanel.add(wrapPanel(dashboardScreen), "dashboard");
        mainPanel.add(wrapPanel(profileScreen), "profile");
        mainPanel.add(wrapPanel(editScreen), "edit");
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private JPanel wrapPanel(JPanel panel) {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(80, 84, 84));
        wrapper.add(panel);
        return wrapper;
    }
    
    public void showScreen(String screenName) {
        if (screenName.equals("profile")) {
            profileScreen.loadProfile(currentUserContact);
        } else if (screenName.equals("edit")) {
            editScreen.loadData(currentUserContact);
        }
        cardLayout.show(mainPanel, screenName);
    }
    
    public void setCurrentUser(String contact, String name) {
        this.currentUserContact = contact;
        this.currentUserName = name;
    }
    
    public static void main(String[] args) {
        new MainApp();
    }
}
