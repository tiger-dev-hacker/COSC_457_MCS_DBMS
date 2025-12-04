import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateTools extends JFrame implements ActionListener {



    JTextField tool_name, tool_manifest;
    JLabel tempid;
    JButton updateBtn, backBtn;
    String number;

    public UpdateTools(String number) {
        this.number = number;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Update Tool Details");

        // Background
        getContentPane().setBackground(new Color(15, 23, 42)); // Dark slate

        // Color theme
        Color darkBlue = new Color(30, 58, 138);
        Color updateColor = new Color(34, 197, 94);
        Color backColor = new Color(100, 116, 139);
        Color labelBg = new Color(51, 65, 85);
        Color lightText = Color.WHITE;
        Color fieldBg = new Color(248, 250, 252);
        Color fieldText = new Color(30, 30, 30);
        Color idBg = new Color(59, 130, 246);

        // Title
        JLabel heading = new JLabel("Update Tool Details");
        heading.setBounds(250, 25, 500, 60);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 38));
        heading.setForeground(lightText);
        heading.setBackground(darkBlue);
        heading.setOpaque(true);
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading);

        // Tool ID Label
        JLabel idLabel = new JLabel("Tool ID");
        idLabel.setBounds(100, 130, 200, 40);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(idLabel, labelBg, lightText);
        add(idLabel);

        // Tool ID value
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

        // Tool Name Label
        JLabel toolNameLabel = new JLabel("Tool Name");
        toolNameLabel.setBounds(100, 210, 200, 40);
        toolNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(toolNameLabel, labelBg, lightText);
        add(toolNameLabel);

        // Tool Name field
        tool_name = new JTextField();
        tool_name.setBounds(320, 205, 500, 45);
        tool_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(tool_name, fieldBg, fieldText);
        add(tool_name);

        // Tool Manifest Label
        JLabel manifestLabel = new JLabel("Tool Manifest");
        manifestLabel.setBounds(100, 290, 200, 40);
        manifestLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(manifestLabel, labelBg, lightText);
        add(manifestLabel);

        // Tool Manifest field
        tool_manifest = new JTextField();
        tool_manifest.setBounds(320, 285, 500, 45);
        tool_manifest.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(tool_manifest, fieldBg, fieldText);
        add(tool_manifest);

        // Load DB data
        loadToolData();

        // UPDATE Button
        updateBtn = new JButton("UPDATE TOOL");
        updateBtn.setBounds(300, 450, 250, 80);
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 22));
        updateBtn.addActionListener(this);
        styleButton(updateBtn, updateColor, lightText, 20);
        add(updateBtn);

        // BACK Button
        backBtn = new JButton("BACK");
        backBtn.setBounds(600, 450, 250, 80);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 22));
        backBtn.addActionListener(this);
        styleButton(backBtn, backColor, lightText, 20);
        add(backBtn);

        // Tip Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(100, 560, 720, 60);
        infoPanel.setBackground(new Color(30, 41, 59));
        infoPanel.setBorder(BorderFactory.createLineBorder(new Color(71, 85, 105), 2, true));

        JLabel tipLabel = new JLabel("💡 Tip: Ensure all tool details are correct before updating.");
        tipLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tipLabel.setForeground(new Color(226, 232, 240));
        tipLabel.setBounds(20, 15, 700, 30);
        infoPanel.add(tipLabel);

        add(infoPanel);

        // Window config
        setSize(1000, 700);
        setLayout(null);
        setLocation(250, 50);
        setVisible(true);
    }

    private void loadToolData() {
        try {
			Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tool WHERE ToolID='" + number + "'");

            if (rs.next()) {
                tool_name.setText(rs.getString("ToolName"));
                tool_manifest.setText(rs.getString("ToolManifest"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ---------------- Styling Methods ----------------

    private void styleLabel(JLabel label, Color bg, Color fg) {
        label.setOpaque(true);
        label.setBackground(bg);
        label.setForeground(fg);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new javax.swing.border.LineBorder(bg, 2, true));
    }

    private void styleTextField(JTextField field, Color bg, Color fg) {
        field.setBackground(bg);
        field.setForeground(fg);
        field.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor, int radius) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));

        // Custom painting
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color paintColor = bgColor;
                if (button.getModel().isPressed()) paintColor = darken(bgColor, 25);
                else if (button.getModel().isRollover()) paintColor = brighten(bgColor, 25);

                g2.setColor(paintColor);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);
                g2.dispose();

                super.paint(g, c);
            }
        });
    }

    private Color brighten(Color c, int amt) {
        return new Color(
                Math.min(255, c.getRed() + amt),
                Math.min(255, c.getGreen() + amt),
                Math.min(255, c.getBlue() + amt)
        );
    }

    private Color darken(Color c, int amt) {
        return new Color(
                Math.max(0, c.getRed() - amt),
                Math.max(0, c.getGreen() - amt),
                Math.max(0, c.getBlue() - amt)
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updateBtn) {

            String name = tool_name.getText().trim();
            String manifest = tool_manifest.getText().trim();

            try {
				Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(
                        "UPDATE tool SET ToolName='" + name + "', ToolManifest='" + manifest +
                        "' WHERE ToolID='" + number + "'"
                );

                JOptionPane.showMessageDialog(this, "Tool details updated successfully!");
                setVisible(false);
                new ViewTools();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
            }

        } else {
            setVisible(false);
            new ViewTools();
        }
    }

    public static void main(String[] args) {
        new UpdateTools("");
    }
}
