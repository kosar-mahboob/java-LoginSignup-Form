import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SignUpFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private HashMap<String, String> userCredentials;

    public SignUpFrame(HashMap<String, String> userCredentials) {
        this.userCredentials = userCredentials;

        // Frame setup
        setTitle("Sign Up");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = new JPanel();
        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(confirmPasswordLabel);
        formPanel.add(confirmPasswordField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back to Login");

        // Style buttons
        signupButton.setBackground(new Color(34, 139, 34));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);

        backButton.setBackground(new Color(220, 20, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        buttonPanel.add(signupButton);
        buttonPanel.add(backButton);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(SignUpFrame.this,
                            "Please fill in all fields",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(SignUpFrame.this,
                            "Passwords do not match",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (userCredentials.containsKey(username)) {
                    JOptionPane.showMessageDialog(SignUpFrame.this,
                            "Username already exists",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(SignUpFrame.this,
                            "Password must be at least 6 characters",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Add new user
                userCredentials.put(username, password);
                JOptionPane.showMessageDialog(SignUpFrame.this,
                        "Account created successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Return to login
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close signup window
                new LoginFrame().setVisible(true);
            }
        });

        // Add main panel to frame
        add(mainPanel);
    }
}