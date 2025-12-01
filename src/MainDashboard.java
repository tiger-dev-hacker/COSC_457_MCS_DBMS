import java.awt.*;
import javax.swing.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setSize(1366, 565);
        setLocation(100, 100);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- Background Image ---
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("images/office.jpg"));
        Image bgImg = bgIcon.getImage().getScaledInstance(1366, 565, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImg));
        background.setBounds(0, 0, 1366, 565);
        add(background);

        // --- Title Text ---
        JLabel text = new JLabel("Welcome to Management and Construction Services LLC.", JLabel.CENTER); 
        text.setBounds(100, 30, 1166, 70);
        text.setForeground(Color.WHITE); 
        text.setFont(new Font("Segoe UI", Font.BOLD, 36)); 
        text.setBackground(new Color(245, 61, 176)); // custom pink
        text.setOpaque(true);
        background.add(text);

        // --- Overlay Background Panel (behind logo) ---
        JPanel logoBackground = new JPanel();
        logoBackground.setBounds(525, 150, 300, 300); // moved down to add gap from title
        logoBackground.setBackground(Color.WHITE); // plain white background
        logoBackground.setLayout(null);
        logoBackground.setOpaque(true);
        logoBackground.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 138), 3, true)); // optional border with theme color
        background.add(logoBackground);

        // --- Overlay Logo Image ---
        ImageIcon overlayIcon = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image overlayImg = overlayIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel overlayLabel = new JLabel(new ImageIcon(overlayImg));
        overlayLabel.setBounds(25, 25, 250, 250); // positioned inside the panel
        logoBackground.add(overlayLabel);

        // --- Button Styles ---
        Color btnColor1 = new Color(30, 58, 138); // Blue
        Color btnColor2 = new Color(34, 197, 94); // Green
        Color btnTextColor = Color.WHITE;

        JButton btnHREmployees = new JButton("HR/Employees");
        styleButton(btnHREmployees, btnColor1, btnTextColor);
        btnHREmployees.setBounds(200, 400, 220, 90); // moved left and lower
        btnHREmployees.addActionListener(e -> {
            new EmployeeDashboard();
            setVisible(false);
        });
        background.add(btnHREmployees);

        JButton btnBusinessClient = new JButton("Business/Clients");
        styleButton(btnBusinessClient, btnColor2, btnTextColor);
        btnBusinessClient.setBounds(930, 400, 220, 90); // moved right to increase spacing
        btnBusinessClient.addActionListener(e -> {
            new BusinessDashboard();
            setVisible(false);
        });
        background.add(btnBusinessClient);

        setVisible(true);
    }

    // --- Button Styling with hover effect ---
    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
        });
    }

    public static void main(String[] args) {
        new MainDashboard();
    }
}
