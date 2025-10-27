package src;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class Payment extends JFrame {
    private final JTextField cardNumberField;
    private final JTextField expiryField;
    private final JTextField cvvField;
    private final int amount;
    private final String turfName;

    public Payment(String turfName, int amount) {
        this.turfName = turfName;
        this.amount = amount;
        
        setTitle("Payment - " + turfName);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel amountLabel = new JLabel("Amount to Pay: ₹" + NumberFormat.getInstance().format(amount));
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel formPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        cardNumberField = new JTextField();
        expiryField = new JTextField();
        cvvField = new JTextField();

        formPanel.add(new JLabel("Card Number:"));
        formPanel.add(cardNumberField);
        formPanel.add(new JLabel("Expiry (MM/YY):"));
        formPanel.add(expiryField);
        formPanel.add(new JLabel("CVV:"));
        formPanel.add(cvvField);

        JButton payButton = new JButton("Pay Now");
        payButton.setBackground(new Color(0, 123, 181));
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);
        payButton.addActionListener(e -> processPayment());

        mainPanel.add(amountLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(payButton);

        add(mainPanel);
        setVisible(true);
    }

    private void processPayment() {
        if (validateFields()) {
            JOptionPane.showMessageDialog(
                this,
                "Payment successful!\nBooking confirmed for " + turfName,
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
        }
    }

    private boolean validateFields() {
        if (cardNumberField.getText().trim().length() != 16) {
            showError("Please enter a valid 16-digit card number");
            return false;
        }
        if (!expiryField.getText().trim().matches("\\d{2}/\\d{2}")) {
            showError("Please enter expiry in MM/YY format");
            return false;
        }
        if (cvvField.getText().trim().length() != 3) {
            showError("Please enter a valid 3-digit CVV");
            return false;
        }
        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
