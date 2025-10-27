package src;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton actionBtn, switchBtn;
    private boolean isLoginMode = true;
    private JPanel panel;
    private JLabel header;

    public Login() {
        setTitle("Turf Booking - Login / Signup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 320);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        header = new JLabel("Login", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField = new JTextField();

        actionBtn = new JButton("Login");
        switchBtn = new JButton("Switch to Signup");

        actionBtn.addActionListener(e -> handleAction());
        switchBtn.addActionListener(e -> toggleMode());

        rebuildForm();

        add(panel);
        setVisible(true);
    }

    private void rebuildForm() {
        panel.removeAll();

        panel.add(header);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        if (!isLoginMode) {
            panel.add(new JLabel("Email:"));
            panel.add(emailField);
        }

        panel.add(actionBtn);
        panel.add(switchBtn);

        panel.revalidate();
        panel.repaint();
    }

    private void toggleMode() {
        isLoginMode = !isLoginMode;
        if (isLoginMode) {
            header.setText("Login");
            actionBtn.setText("Login");
            switchBtn.setText("Switch to Signup");
        } else {
            header.setText("Signup");
            actionBtn.setText("Sign Up");
            switchBtn.setText("Switch to Login");
        }
        rebuildForm();
    }

    private void handleAction() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        if (isLoginMode) {
            JOptionPane.showMessageDialog(this, "Welcome back, " + username + "!");
        } else {
            String email = emailField.getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email is required for signup!");
                return;
            }
            JOptionPane.showMessageDialog(this, "Signup successful for " + username + "!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
