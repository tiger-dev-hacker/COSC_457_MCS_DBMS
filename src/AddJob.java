import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddJob extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField job_name;
    private JTextField job_length;
    private JTextField contract_id;

    private JButton btnRegister;
    private JButton btnBack;

    public AddJob() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 650);
        setResizable(false);
        setTitle("Add New Job");

        // Panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(15, 23, 42)); // dark background
        setContentPane(contentPane);

        // Colors
        Color darkBlue = new Color(30, 58, 138);
        Color registerColor = new Color(34, 197, 94);
        Color backColor = new Color(37, 99, 235);
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85);
        Color fieldBg = new Color(248, 250, 252);
        Color fieldText = new Color(30, 30, 30);

        // Title
        JLabel lblTitle = new JLabel("New Job Registration");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitle.setBounds(250, 25, 500, 60);
        lblTitle.setForeground(lightText);
        lblTitle.setBackground(darkBlue);
        lblTitle.setOpaque(true);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblTitle);

        // Job Name
        JLabel lblJobName = new JLabel("Job Name");
        lblJobName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJobName.setBounds(100, 150, 200, 35);
        styleLabel(lblJobName, labelBg, lightText);
        contentPane.add(lblJobName);

        job_name = new JTextField();
        job_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        job_name.setBounds(320, 145, 500, 45);
        styleTextField(job_name, fieldBg, fieldText);
        contentPane.add(job_name);

        // Job Length
        JLabel lblJobLength = new JLabel("Job Length");
        lblJobLength.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJobLength.setBounds(100, 240, 200, 35);
        styleLabel(lblJobLength, labelBg, lightText);
        contentPane.add(lblJobLength);

        job_length = new JTextField();
        job_length.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        job_length.setBounds(320, 235, 500, 45);
        styleTextField(job_length, fieldBg, fieldText);
        contentPane.add(job_length);

        // Contract ID
        JLabel lblContractID = new JLabel("Contract ID");
        lblContractID.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblContractID.setBounds(100, 330, 200, 35);
        styleLabel(lblContractID, labelBg, lightText);
        contentPane.add(lblContractID);

        contract_id = new JTextField();
        contract_id.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        contract_id.setBounds(320, 325, 500, 45);
        styleTextField(contract_id, fieldBg, fieldText);
        contentPane.add(contract_id);

        // Register Button
        btnRegister = new JButton("Register Job");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnRegister.setBounds(200, 450, 280, 80);
        styleButton(btnRegister, registerColor, lightText, 20);
        contentPane.add(btnRegister);

        btnRegister.addActionListener(e -> handleRegister());

        // Back Button
        btnBack = new JButton("Back to Job Dashboard");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnBack.setBounds(530, 450, 350, 80);
        styleButton(btnBack, backColor, lightText, 20);
        contentPane.add(btnBack);
        btnBack.addActionListener(e -> handleBackButton());

        setVisible(true);
    }

    private void handleRegister() {
        String jobName = job_name.getText().trim();
        String jobLength = job_length.getText().trim();
        String contractID = contract_id.getText().trim();

        if (jobName.isEmpty() || jobLength.isEmpty() || contractID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        try {
            String user_name = "root";
            String passWord = "Keyboard30%$";
            String url = "jdbc:mysql://localhost:3306/mcs";
            Connection conn = DriverManager.getConnection(url, user_name, passWord);

            String query = "INSERT INTO Job (JobName, JobLength, ContractID) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, jobName);
            pst.setString(2, jobLength);
            pst.setString(3, contractID);

            int x = pst.executeUpdate();
            if (x > 0) {
                JOptionPane.showMessageDialog(this, "Job " + jobName + " has been successfully added.");
                job_name.setText("");
                job_length.setText("");
                contract_id.setText("");
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void handleBackButton() {
        new JobDashboard();
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
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(button.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = bgColor;

            public void mouseEntered(java.awt.event.MouseEvent evt) { button.setBackground(brightenColor(original, 25)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { button.setBackground(original); }
            public void mousePressed(java.awt.event.MouseEvent evt) { button.setBackground(darkenColor(original, 20)); }
            public void mouseReleased(java.awt.event.MouseEvent evt) { button.setBackground(brightenColor(original, 25)); }
        });
    }

    private Color brightenColor(Color color, int amount) {
        return new Color(Math.min(255, color.getRed() + amount),
                         Math.min(255, color.getGreen() + amount),
                         Math.min(255, color.getBlue() + amount));
    }

    private Color darkenColor(Color color, int amount) {
        return new Color(Math.max(0, color.getRed() - amount),
                         Math.max(0, color.getGreen() - amount),
                         Math.max(0, color.getBlue() - amount));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddJob());
    }
}
