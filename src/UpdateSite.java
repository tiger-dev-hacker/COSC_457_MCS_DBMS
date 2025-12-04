import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateSite extends JFrame implements ActionListener {
   

    JTextField site_name, escort_limit, client_id;
    JLabel tempid;
    JButton add, back;
    String number;

    public UpdateSite(String number) {
        this.number = number;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Update Site Details");

        getContentPane().setBackground(new Color(15, 23, 42)); // Dark slate

        // Custom Design Colors
        Color darkBlue = new Color(30, 58, 138);
        Color updateColor = new Color(34, 197, 94);   // Green
        Color backColor = new Color(100, 116, 139);   // Gray
        Color lightText = Color.WHITE;
        Color labelBg = new Color(51, 65, 85);        // Slate Gray
        Color fieldBg = new Color(248, 250, 252);     // Light gray
        Color fieldText = new Color(30, 30, 30);
        Color idBg = new Color(59, 130, 246);         // Blue highlight for ID

        // Title Label
        JLabel heading = new JLabel("Update Site Details");
        heading.setBounds(250, 25, 500, 60);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 38));
        heading.setForeground(lightText);
        heading.setBackground(darkBlue);
        heading.setOpaque(true);
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading);

        // --- Site ID Label ---
        JLabel siteID = new JLabel("Site ID");
        siteID.setBounds(100, 130, 200, 35);
        siteID.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(siteID, labelBg, lightText);
        add(siteID);

        // Site ID Value field
        tempid = new JLabel(number);
        tempid.setBounds(320, 125, 500, 45);
        tempid.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tempid.setForeground(lightText);
        tempid.setBackground(idBg);
        tempid.setOpaque(true);
        tempid.setHorizontalAlignment(JLabel.CENTER);
        tempid.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(idBg, 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(tempid);

        // --- Site Name ---
        JLabel siteName = new JLabel("Site Name");
        siteName.setBounds(100, 210, 200, 35);
        siteName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(siteName, labelBg, lightText);
        add(siteName);

        site_name = new JTextField();
        site_name.setBounds(320, 205, 500, 45);
        site_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(site_name, fieldBg, fieldText);
        add(site_name);

        // --- Escort Limit ---
        JLabel escortLabel = new JLabel("Escort Limit");
        escortLabel.setBounds(100, 290, 200, 35);
        escortLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(escortLabel, labelBg, lightText);
        add(escortLabel);

        escort_limit = new JTextField();
        escort_limit.setBounds(320, 285, 500, 45);
        escort_limit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(escort_limit, fieldBg, fieldText);
        add(escort_limit);

        // --- Client ID ---
        JLabel clientLabel = new JLabel("Client ID");
        clientLabel.setBounds(100, 370, 200, 35);
        clientLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(clientLabel, labelBg, lightText);
        add(clientLabel);

        client_id = new JTextField();
        client_id.setBounds(320, 365, 500, 45);
        client_id.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(client_id, fieldBg, fieldText);
        add(client_id);

        // Load data
        try {
			Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from site where SiteID = '" + number + "'");
            if (resultSet.next()) {
                site_name.setText(resultSet.getString("SiteName"));
                escort_limit.setText(resultSet.getString("EscortLimit"));
                client_id.setText(resultSet.getString("ClientID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update Button
        add = new JButton("UPDATE SITE");
        add.setBounds(300, 470, 250, 80);
        add.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add.addActionListener(this);
        styleButton(add, updateColor, lightText, 20);
        add(add);

        // Back Button
        back = new JButton("BACK");
        back.setBounds(600, 470, 250, 80);
        back.setFont(new Font("Segoe UI", Font.BOLD, 22));
        back.addActionListener(this);
        styleButton(back, backColor, lightText, 20);
        add(back);

        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(100, 570, 720, 60);
        infoPanel.setBackground(new Color(30, 41, 59));
        infoPanel.setBorder(BorderFactory.createLineBorder(new Color(71, 85, 105), 2, true));

        JLabel infoLabel = new JLabel("💡 Tip: Ensure all details are valid before updating.");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        infoLabel.setForeground(new Color(226, 232, 240));
        infoLabel.setBounds(20, 15, 680, 30);
        infoPanel.add(infoLabel);

        add(infoPanel);

        setSize(1000, 700);
        setLayout(null);
        setLocation(250, 50);
        setVisible(true);
    }

    // Label Styling
    private void styleLabel(JLabel label, Color bg, Color fg) {
        label.setBackground(bg);
        label.setForeground(fg);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new javax.swing.border.LineBorder(bg, 2, true));
    }

    // TextField Styling
    private void styleTextField(JTextField field, Color bg, Color fg) {
        field.setBackground(bg);
        field.setForeground(fg);
        field.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    // Button Styling
    private void styleButton(JButton button, Color bgColor, Color fgColor, int radius) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false);

        button.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(button.getBackground());
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = bgColor;

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(brighten(original, 30));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(original);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(darken(original, 20));
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(brighten(original, 30));
            }
        });
    }

    private Color brighten(Color color, int amt) {
        return new Color(
                Math.min(255, color.getRed() + amt),
                Math.min(255, color.getGreen() + amt),
                Math.min(255, color.getBlue() + amt)
        );
    }

    private Color darken(Color color, int amt) {
        return new Color(
                Math.max(0, color.getRed() - amt),
                Math.max(0, color.getGreen() - amt),
                Math.max(0, color.getBlue() - amt)
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String Sname = site_name.getText().trim();
            String SEscortLimit = escort_limit.getText().trim();
            String ClientID = client_id.getText().trim();

            try {
				Connection conn = DatabaseConnection.getConnection();
                Statement statement = conn.createStatement();
                statement.executeUpdate(
                        "UPDATE site SET SiteName='" + Sname + "', EscortLimit='" +
                        SEscortLimit + "', ClientID='" + ClientID +
                        "' WHERE SiteID='" + number + "'"
                );
                JOptionPane.showMessageDialog(null, "Site details updated successfully!");
                setVisible(false);
                new ViewSite();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            setVisible(false);
            new ViewSite();
        }
    }

    public static void main(String[] args) {
        new UpdateSite("");
    }
}
