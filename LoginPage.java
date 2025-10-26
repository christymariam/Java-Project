import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleLoginSignup extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton actionBtn, switchBtn;
    private boolean isLoginMode = true;

    public SimpleLoginSignup() {
        setTitle("Turf Booking - Login / Signup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 280);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel header = new JLabel("Login", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField = new JTextField();

        actionBtn = new JButton("Login");
        switchBtn = new JButton("Switch to Signup");

        // Add components in order
        panel.add(header);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(actionBtn);
        panel.add(switchBtn);

        add(panel);

        // Handle button click
        actionBtn.addActionListener(e -> handleAction(header));
        switchBtn.addActionListener(e -> toggleMode(header));

        setVisible(true);
    }

    private void toggleMode(JLabel header) {
        isLoginMode = !isLoginMode;
        if (isLoginMode) {
            header.setText("Login");
            actionBtn.setText("Login");
            switchBtn.setText("Switch to Signup");
            getContentPane().remove(emailField);
        } else {
            header.setText("Signup");
            actionBtn.setText("Sign Up");
            switchBtn.setText("Switch to Login");
            getContentPane().add(new JLabel("Email:"));
            getContentPane().add(emailField);
        }
        revalidate();
        repaint();
    }

    private void handleAction(JLabel header) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }
        if (isLoginMode) {
            JOptionPane.showMessageDialog(this, "Welcome back, " + username + "!");
        } else {
            String email = emailField.getText();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email is required for signup!");
                return;
            }
            JOptionPane.showMessageDialog(this, "Signup successful for " + username + "!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleLoginSignup::new);
    }
}
