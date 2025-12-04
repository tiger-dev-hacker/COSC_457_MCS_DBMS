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

public class AddTools extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField tool_name;
    private JTextField tool_manifest;

    private JButton btnRegister;
    private JButton btnBackButton;

    public AddTools() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 650);
        setResizable(false);
        setTitle("Add New Tool");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(15, 23, 42));  // Dark dashboard theme
        setContentPane(contentPane);

        // Theme colors
        Color darkBlue = new Color(30, 58, 138);
        Color registerColor = new Color(34, 197, 94);
        Color backColor = new Color(37, 99, 235);
        Color lightText = Color.WHITE;
        Color labelBg = new Color(51, 65, 85);
        Color fieldBg = new Color(248, 250, 252);
        Color fieldText = new Color(30, 30, 30);

        // Title
        JLabel lblTitle = new JLabel("New Tool Registration");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitle.setBounds(250, 25, 500, 60);
        lblTitle.setForeground(lightText);
        lblTitle.setBackground(darkBlue);
        lblTitle.setOpaque(true);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblTitle);

        // Labels
        JLabel lblToolName = new JLabel("Tool Name");
        lblToolName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblToolName.setBounds(100, 150, 200, 35);
        styleLabel(lblToolName, labelBg, lightText);
        contentPane.add(lblToolName);

        tool_name = new JTextField();
        tool_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        tool_name.setBounds(320, 145, 500, 45);
        styleTextField(tool_name, fieldBg, fieldText);
        contentPane.add(tool_name);

        JLabel lblToolManifest = new JLabel("Tool Manifest");
        lblToolManifest.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblToolManifest.setBounds(100, 240, 200, 35);
        styleLabel(lblToolManifest, labelBg, lightText);
        contentPane.add(lblToolManifest);

        tool_manifest = new JTextField();
        tool_manifest.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        tool_manifest.setBounds(320, 235, 500, 45);
        styleTextField(tool_manifest, fieldBg, fieldText);
        contentPane.add(tool_manifest);

        // Register Button
        btnRegister = new JButton("Register Tool");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnRegister.setBounds(200, 450, 280, 80);
        styleButton(btnRegister, registerColor, lightText, 20);
        contentPane.add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String toolName = tool_name.getText().trim();
                String toolManifest = tool_manifest.getText().trim();

                if (toolName.isEmpty()) {
                    JOptionPane.showMessageDialog(btnRegister, "Tool name cannot be empty");
                    return;
                }

                if (toolManifest.isEmpty()) {
                    JOptionPane.showMessageDialog(btnRegister, "Tool manifest cannot be empty");
                    return;
                }

                try {
					Connection conn = DatabaseConnection.getConnection();

                    String query = "INSERT INTO Tool (ToolName, ToolManifest) VALUES (?, ?)";

                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, toolName);
                    pst.setString(2, toolManifest);

                    int x = pst.executeUpdate();

                    if (x > 0) {
                        JOptionPane.showMessageDialog(btnRegister,
                                "Tool " + toolName + "\nhas been successfully added");

                        tool_name.setText("");
                        tool_manifest.setText("");
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(btnRegister,
                            "Error: " + exception.getMessage());
                }
            }
        });

        // Back button
        btnBackButton = new JButton("Back to Tool Dashboard");
        btnBackButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnBackButton.setBounds(530, 450, 350, 80);
        styleButton(btnBackButton, backColor, lightText, 20);
        contentPane.add(btnBackButton);

        btnBackButton.addActionListener(e -> {
            new ToolDashboard();
            setVisible(false);
        });

        setVisible(true);
    }

    // Label styling
    private void styleLabel(JLabel label, Color bgColor, Color fgColor) {
        label.setBackground(bgColor);
        label.setForeground(fgColor);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));
    }

    // Text field styling
    private void styleTextField(JTextField field, Color bgColor, Color fgColor) {
        field.setBackground(bgColor);
        field.setForeground(fgColor);
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }

    // Button styling (rounded with hover effects)
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
        SwingUtilities.invokeLater(() -> new AddTools());
    }
}
