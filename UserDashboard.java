package src;

import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {
    public UserDashboard() {
        setTitle("User Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to your dashboard!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        add(welcomeLabel);
        setVisible(true);
    }
}
