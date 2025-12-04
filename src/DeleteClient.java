import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteClient extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
   
    Choice choiceEMPID;
    JButton delete, back;
    JLabel textName, textPhone;

    public DeleteClient() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Delete Client");

        // Main content pane with dark background
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(15, 23, 42)); // Dark slate background
        setContentPane(contentPane);

        // Define custom colors
        Color darkBlue = new Color(30, 58, 138);
        Color deleteColor = new Color(239, 68, 68); // Red for Delete
        Color backColor = new Color(100, 116, 139); // Gray for Back
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85); // Slate gray for labels
        Color fieldBg = new Color(248, 250, 252); // Light gray for display fields
        Color warningBg = new Color(254, 226, 226); // Light red background

        // Title Label
        JLabel titleLabel = new JLabel("Delete Client");
        titleLabel.setBounds(300, 20, 400, 60);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 38));
        titleLabel.setForeground(lightText);
        titleLabel.setBackground(darkBlue);
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(titleLabel);

        // Warning Panel
        JPanel warningPanel = new JPanel();
        warningPanel.setLayout(null);
        warningPanel.setBounds(100, 100, 800, 80);
        warningPanel.setBackground(warningBg);
        warningPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(239, 68, 68), 3, true),
            new EmptyBorder(10, 10, 10, 10)
        ));
        contentPane.add(warningPanel);

        JLabel warningIcon = new JLabel("⚠️");
        warningIcon.setBounds(20, 15, 50, 50);
        warningIcon.setFont(new Font("Segoe UI", Font.BOLD, 36));
        warningPanel.add(warningIcon);

        JLabel warningText = new JLabel("Warning: This action cannot be undone!");
        warningText.setBounds(80, 10, 700, 30);
        warningText.setFont(new Font("Segoe UI", Font.BOLD, 20));
        warningText.setForeground(new Color(127, 29, 29));
        warningPanel.add(warningText);

        JLabel warningSubtext = new JLabel("Please verify the client information before deleting.");
        warningSubtext.setBounds(80, 40, 700, 25);
        warningSubtext.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        warningSubtext.setForeground(new Color(153, 27, 27));
        warningPanel.add(warningSubtext);

        // Client ID Label
        JLabel label = new JLabel("Select Client ID");
        label.setBounds(150, 220, 200, 35);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(label, labelBg, lightText);
        contentPane.add(label);

        // Client ID Choice Dropdown
        choiceEMPID = new Choice();
        choiceEMPID.setBounds(370, 220, 380, 40);
        choiceEMPID.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        choiceEMPID.setBackground(fieldBg);
        choiceEMPID.setForeground(new Color(30, 30, 30));
        contentPane.add(choiceEMPID);

    	Connection conn = DatabaseConnection.getConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from client");
            while (resultSet.next()) {
                choiceEMPID.add(resultSet.getString("ClientID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading clients: " + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // Client Name Label
        JLabel labelName = new JLabel("Client Name");
        labelName.setBounds(150, 290, 200, 35);
        labelName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(labelName, labelBg, lightText);
        contentPane.add(labelName);

        // Client Name Display
        textName = new JLabel();
        textName.setBounds(370, 285, 380, 45);
        textName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        textName.setForeground(new Color(30, 30, 30));
        textName.setBackground(fieldBg);
        textName.setOpaque(true);
        textName.setHorizontalAlignment(JLabel.CENTER);
        textName.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new Color(203, 213, 225), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(textName);

        // Background Check Label (split into two lines)
        JLabel labelPhone1 = new JLabel("Background Check");
        labelPhone1.setBounds(150, 360, 200, 35);
        labelPhone1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(labelPhone1, labelBg, lightText);
        contentPane.add(labelPhone1);

        JLabel labelPhone2 = new JLabel("Expiry Limit");
        labelPhone2.setBounds(150, 390, 200, 35);
        labelPhone2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(labelPhone2, labelBg, lightText);
        contentPane.add(labelPhone2);

        // Background Check Display
        textPhone = new JLabel();
        textPhone.setBounds(370, 365, 380, 45);
        textPhone.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        textPhone.setForeground(new Color(30, 30, 30));
        textPhone.setBackground(fieldBg);
        textPhone.setOpaque(true);
        textPhone.setHorizontalAlignment(JLabel.CENTER);
        textPhone.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new Color(203, 213, 225), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(textPhone);

    	conn = DatabaseConnection.getConnection();
        // Load initial data
        try {

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from client where ClientID = '" + choiceEMPID.getSelectedItem() + "'");
            while (resultSet.next()) {
                textName.setText(resultSet.getString("ClientName"));
                textPhone.setText(resultSet.getString("BackgroundCheckExpiryLimit"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update display when selection changes
        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                	Connection conn = DatabaseConnection.getConnection();
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from client where ClientID = '" + choiceEMPID.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        textName.setText(resultSet.getString("ClientName"));
                        textPhone.setText(resultSet.getString("BackgroundCheckExpiryLimit"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        // Delete Button
        delete = new JButton("DELETE CLIENT");
        delete.setBounds(270, 480, 220, 70);
        delete.setFont(new Font("Segoe UI", Font.BOLD, 20));
        delete.addActionListener(this);
        styleButton(delete, deleteColor, lightText, 20);
        contentPane.add(delete);

        // Back Button
        back = new JButton("BACK");
        back.setBounds(530, 480, 220, 70);
        back.setFont(new Font("Segoe UI", Font.BOLD, 20));
        back.addActionListener(this);
        styleButton(back, backColor, lightText, 20);
        contentPane.add(back);

        setSize(1000, 620);
        setLocation(250, 100);
        setResizable(false);
        setVisible(true);
    }

    // Helper method to style labels
    private void styleLabel(JLabel label, Color bgColor, Color fgColor) {
        label.setBackground(bgColor);
        label.setForeground(fgColor);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));
    }

    // Helper method to style buttons with rounded corners
    private void styleButton(JButton button, Color bgColor, Color fgColor, int cornerRadius) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(button.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), cornerRadius, cornerRadius);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color originalColor = bgColor;

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(brightenColor(originalColor, 30));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(darkenColor(originalColor, 20));
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(brightenColor(originalColor, 30));
            }
        });
    }

    // Helper method to brighten colors
    private Color brightenColor(Color color, int amount) {
        int r = Math.min(255, color.getRed() + amount);
        int g = Math.min(255, color.getGreen() + amount);
        int b = Math.min(255, color.getBlue() + amount);
        return new Color(r, g, b);
    }

    // Helper method to darken colors
    private Color darkenColor(Color color, int amount) {
        int r = Math.max(0, color.getRed() - amount);
        int g = Math.max(0, color.getGreen() - amount);
        int b = Math.max(0, color.getBlue() - amount);
        return new Color(r, g, b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            // Confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete client:\n" + textName.getText() + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                	Connection conn = DatabaseConnection.getConnection();
                    Statement statement = conn.createStatement();
                    String query = "delete from client where ClientID = '" + choiceEMPID.getSelectedItem() + "'";
                    int rowsDeleted = statement.executeUpdate(query);

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Client deleted successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Client ID not found!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    setVisible(false);
                    new ClientDashboard();

                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting client: " + E.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            new ClientDashboard();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeleteClient());
    }
}