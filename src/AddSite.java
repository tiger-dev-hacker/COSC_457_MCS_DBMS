import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class AddSite extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField site_name;
    private JTextField escort_limit;
    private JTextField client_id;

    private JButton btnNewButton;
    private JButton btnBackButton;

    public AddSite() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 650);
        setResizable(false);
        setTitle("Add New Site");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(15, 23, 42)); // Dark slate
        setContentPane(contentPane);

        // Define custom colors
        Color darkBlue = new Color(30, 58, 138);
        Color registerColor = new Color(34, 197, 94); 
        Color backColor = new Color(37, 99, 235);
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85);
        Color fieldBg = new Color(248, 250, 252);
        Color fieldText = new Color(30, 30, 30);

        // Title Label
        JLabel lblNewUserRegister = new JLabel("New Site Registration");
        lblNewUserRegister.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblNewUserRegister.setBounds(250, 25, 500, 60);
        lblNewUserRegister.setForeground(lightText);
        lblNewUserRegister.setBackground(darkBlue);
        lblNewUserRegister.setOpaque(true);
        lblNewUserRegister.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblNewUserRegister);

        // Labels + Fields
        JLabel lblName = new JLabel("Site Name");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblName.setBounds(100, 150, 200, 35);
        styleLabel(lblName, labelBg, lightText);
        contentPane.add(lblName);

        site_name = new JTextField();
        site_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        site_name.setBounds(320, 145, 500, 45);
        styleTextField(site_name, fieldBg, fieldText);
        contentPane.add(site_name);

        JLabel lblEscort = new JLabel("Escort Limit");
        lblEscort.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblEscort.setBounds(100, 240, 200, 35);
        styleLabel(lblEscort, labelBg, lightText);
        contentPane.add(lblEscort);

        escort_limit = new JTextField();
        escort_limit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        escort_limit.setBounds(320, 235, 500, 45);
        styleTextField(escort_limit, fieldBg, fieldText);
        contentPane.add(escort_limit);

        JLabel lblClient = new JLabel("Client ID");
        lblClient.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblClient.setBounds(100, 330, 200, 35);
        styleLabel(lblClient, labelBg, lightText);
        contentPane.add(lblClient);

        client_id = new JTextField();
        client_id.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        client_id.setBounds(320, 325, 500, 45);
        styleTextField(client_id, fieldBg, fieldText);
        contentPane.add(client_id);

        // Register Button
        btnNewButton = new JButton("Register Site");
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnNewButton.setBounds(200, 450, 280, 80);
        styleButton(btnNewButton, registerColor, lightText, 20);
        contentPane.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String siteName = site_name.getText().trim();
                String escortLimit = escort_limit.getText().trim();
                String clientID = client_id.getText().trim();

                if (siteName.isEmpty()) {
                    JOptionPane.showMessageDialog(btnNewButton, "Site name cannot be empty");
                    return;
                }

                if (escortLimit.isEmpty()) {
                    JOptionPane.showMessageDialog(btnNewButton, "Escort limit cannot be empty");
                    return;
                }

                if (clientID.isEmpty()) {
                    JOptionPane.showMessageDialog(btnNewButton, "Client ID cannot be empty");
                    return;
                }

                try {
                    Integer.parseInt(escortLimit);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(btnNewButton, "Escort limit must be a number");
                    return;
                }

                try {
                  
					Connection conn = DatabaseConnection.getConnection();

                    String query = "INSERT INTO Site (SiteName, EscortLimit, ClientID) VALUES (?, ?, ?)";

                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, siteName);
                    pst.setString(2, escortLimit);
                    pst.setString(3, clientID);

                    int x = pst.executeUpdate();

                    if (x > 0) {
                        JOptionPane.showMessageDialog(btnNewButton,
                                "Site " + siteName + "\nhas been successfully added");

                        site_name.setText("");
                        escort_limit.setText("");
                        client_id.setText("");
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(btnNewButton,
                            "Error: " + exception.getMessage());
                }
            }
        });

        // Back Button
        btnBackButton = new JButton("Back to Site Dashboard");
        btnBackButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnBackButton.setBounds(530, 450, 350, 80);
        styleButton(btnBackButton, backColor, lightText, 20);
        contentPane.add(btnBackButton);

        btnBackButton.addActionListener(e -> handleBackButton());

        setVisible(true);
    }

    private void handleBackButton() {
        new SiteDashboard();
        setVisible(false);
    }

    // Styling helpers
    private void styleLabel(JLabel label, Color bgColor, Color fgColor) {
        label.setBackground(bgColor);
        label.setForeground(fgColor);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));
    }

    private void styleTextField(JTextField field, Color bgColor, Color fgColor) {
        field.setBackground(bgColor);
        field.setForeground(fgColor);
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor, int radius) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(button.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = bgColor;

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(brightenColor(original, 25));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(original);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(darkenColor(original, 20));
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(brightenColor(original, 25));
            }
        });
    }

    private Color brightenColor(Color color, int amount) {
        return new Color(
                Math.min(255, color.getRed() + amount),
                Math.min(255, color.getGreen() + amount),
                Math.min(255, color.getBlue() + amount)
        );
    }

    private Color darkenColor(Color color, int amount) {
        return new Color(
                Math.max(0, color.getRed() - amount),
                Math.max(0, color.getGreen() - amount),
                Math.max(0, color.getBlue() - amount)
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddSite());
    }
}
