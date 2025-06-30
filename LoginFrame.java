import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private HashMap<String, String> userCredentials;

    public LoginFrame() {
        // Initialize user credentials (in a real app, this would be from a database)
        userCredentials = new HashMap<>();
        userCredentials.put("admin", "admin123"); // Default admin account

        // Frame setup
        setTitle("Login System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = new JPanel();
        JLabel titleLabel = new JLabel("Login to Your Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        // Style buttons
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        signupButton.setBackground(new Color(72, 209, 204));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);

        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Please enter both username and password",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Login successful!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Here you would typically open the main application window
                    dispose(); // Close login window
                    new WelcomeFrame(username).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Invalid username or password",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close login window
                new SignUpFrame(userCredentials).setVisible(true);
            }
        });

        // Add main panel to frame
        add(mainPanel);
    }

    private boolean authenticate(String username, String password) {
        return userCredentials.containsKey(username) &&
                userCredentials.get(username).equals(password);
    }
}