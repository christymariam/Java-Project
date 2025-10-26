import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TurfBooking extends JFrame {
    private JPanel turfPanel;

    public TurfBooking(String category) {
        setTitle("Turf Booking - " + category);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Color theme
        Color primaryColor = new Color(0, 123, 181);
        Color backgroundColor = new Color(245, 245, 245);
        Color cardColor = Color.WHITE;
        Color textColor = new Color(40, 40, 40);

        // Header Label
        JLabel header = new JLabel(category + " Turfs", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(primaryColor);
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        // Content panel
        turfPanel = new JPanel();
        turfPanel.setLayout(new BoxLayout(turfPanel, BoxLayout.Y_AXIS));
        turfPanel.setBackground(backgroundColor);

        displayTurfs(category, textColor, cardColor, primaryColor);

        JScrollPane scrollPane = new JScrollPane(turfPanel);
        scrollPane.setBorder(null);
        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void displayTurfs(String category, Color textColor, Color cardColor, Color buttonColor) {
    List<Turf> turfs = getTurfs(category);
    if (turfs.isEmpty()) {
        JLabel noTurfsLabel = new JLabel("No " + category + " turfs available.");
        noTurfsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turfPanel.add(noTurfsLabel);
        return;
    }

    for (Turf turf : turfs) {
        JPanel card = createTurfCard(turf, textColor, cardColor, buttonColor);
        turfPanel.add(card);
        turfPanel.add(Box.createVerticalStrut(10));
    }
}
    private JPanel createTurfCard(Turf turf, Color textColor, Color cardColor, Color buttonColor) {
    JPanel card = new JPanel(new BorderLayout());
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
    ));
    card.setBackground(cardColor);
    card.setMaximumSize(new Dimension(550, 120));

    JLabel info = new JLabel("<html><b>" + turf.name + "</b><br>" +
            turf.address + "<br>Hours: " + turf.hours +
            "<br>Price: ₹" + turf.price + "/hour</html>");
    info.setForeground(textColor);
    info.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    info.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

    JButton bookBtn = new JButton("Book Now");
    bookBtn.setBackground(buttonColor);
    bookBtn.setForeground(Color.WHITE);
    bookBtn.setFocusPainted(false);
    bookBtn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    bookBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
    bookBtn.addActionListener(e -> handleBooking(turf));

    card.add(info, BorderLayout.CENTER);
    card.add(bookBtn, BorderLayout.EAST);

    return card;
}

    // ...existing code...

    private void handleBooking(Turf turf) {
        int result = JOptionPane.showConfirmDialog(
            this,
            "Confirm booking for " + turf.name + "?\nAmount: ₹" + turf.price,
            "Booking Confirmation",
            JOptionPane.YES_NO_OPTION
        );
        
        if (result == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
                try {
                    new Payment(turf.name, turf.price);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, 
                        "Error processing payment: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    private List<Turf> getTurfs(String category) {
        List<Turf> turfs = new ArrayList<>();
        switch (category) {
            case "Football":
                turfs.add(new Turf("Football Field A", "123 Main Street", "8 AM - 10 PM", 800));
                turfs.add(new Turf("Soccer Dome", "45 River Road", "9 AM - 9 PM", 950));
                turfs.add(new Turf("Kick-Off Grounds", "78 Park Ave", "7 AM - 11 PM", 700));
                break;
            case "Cricket":
                turfs.add(new Turf("Cricket Arena", "90 Sports Complex", "6 AM - 9 PM", 1000));
                turfs.add(new Turf("Cricket Hub", "34 Stadium Road", "7 AM - 10 PM", 1200));
                break;
            case "Basketball":
                turfs.add(new Turf("Basketball Court", "56 Downtown", "8 AM - 8 PM", 600));
                turfs.add(new Turf("Hoops Center", "12 Sports Street", "9 AM - 11 PM", 750));
                break;
        }
        return turfs;
    }

    private static class Turf {
        String name, address, hours;
        int price;

        public Turf(String name, String address, String hours, int price) {
            this.name = name;
            this.address = address;
            this.hours = hours;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TurfBooking("Football"));
    }}
