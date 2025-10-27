package src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleTurfOwnerDashboard extends JFrame {
    private List<Turf> turfList;
    private JPanel mainPanel;

    public SimpleTurfOwnerDashboard() {
        setTitle("Turf Owner Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize turf list
        turfList = new ArrayList<>();
        addSampleTurfs();

        // Create and add main panel
        mainPanel = createMainPanel();
        add(mainPanel);
        
        setVisible(true);
    }

    private void addSampleTurfs() {
        turfList.add(new Turf("Ground Zero", "City Park", 1500));
        turfList.add(new Turf("Sports Arena", "Main Road", 2000));
        turfList.add(new Turf("Green Field", "Riverside", 1800));
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header
        JLabel headerLabel = new JLabel("Turf Management System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(headerLabel, BorderLayout.NORTH);

        // Turf List Panel
        JPanel turfListPanel = new JPanel();
        turfListPanel.setLayout(new BoxLayout(turfListPanel, BoxLayout.Y_AXIS));
        
        for (Turf turf : turfList) {
            turfListPanel.add(createTurfPanel(turf));
            turfListPanel.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(turfListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add New Turf");
        addButton.addActionListener(e -> showAddTurfDialog());
        buttonPanel.add(addButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTurfPanel(Turf turf) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setMaximumSize(new Dimension(550, 80));

        // Turf Info
        JLabel infoLabel = new JLabel(String.format("<html><b>%s</b><br>Location: %s<br>Rate: ₹%.2f/hour</html>", 
            turf.name, turf.location, turf.rate));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(infoLabel, BorderLayout.CENTER);

        // Action Button
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> showEditTurfDialog(turf));
        panel.add(editButton, BorderLayout.EAST);

        return panel;
    }

    private void showAddTurfDialog() {
        JTextField nameField = new JTextField(20);
        JTextField locationField = new JTextField(20);
        JTextField rateField = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Turf Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("Hourly Rate:"));
        panel.add(rateField);

        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Add New Turf", JOptionPane.OK_CANCEL_OPTION);
            
        if (result == JOptionPane.OK_OPTION) {
            try {
                double rate = Double.parseDouble(rateField.getText());
                turfList.add(new Turf(nameField.getText(), 
                    locationField.getText(), rate));
                refreshPanel();
                 } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a valid rate", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }    

    private void showEditTurfDialog(Turf turf) {
        JTextField nameField = new JTextField(turf.name, 20);
        JTextField locationField = new JTextField(turf.location, 20);
        JTextField rateField = new JTextField(String.valueOf(turf.rate), 20);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Turf Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("Hourly Rate:"));
        panel.add(rateField);

        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Edit Turf", JOptionPane.OK_CANCEL_OPTION);
            
        if (result == JOptionPane.OK_OPTION) {
            try {
                turf.name = nameField.getText();
                turf.location = locationField.getText();
                turf.rate = Double.parseDouble(rateField.getText());
                refreshPanel();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a valid rate", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshPanel() {
        remove(mainPanel);
        mainPanel = createMainPanel();
        add(mainPanel);
        revalidate();
        repaint();
    }

    private static class Turf {
        String name;
        String location;
        double rate;

        Turf(String name, String location, double rate) {
            this.name = name;
            this.location = location;
            this.rate = rate;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleTurfOwnerDashboard::new);
    }
}
  
