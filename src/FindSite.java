// --- FULL UPDATED FindSite CLASS WITH MODERN UI --- //

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.util.List;

public class FindSite extends JFrame {

    private JPanel contentPane;
    private JTextField siteNameField;
    private JTextField escortLimitField;
    private JTextField clientIDField;

    private JTable resultsTable;
    private DefaultTableModel tableModel;

    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;



    public FindSite() {

        setTitle("Find Sites");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 80, 1100, 750);
        setResizable(false);

        // ----- Background Panel -----
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(15, 23, 42)); // Dark theme
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ----- Color Palette -----
        Color darkBlue = new Color(30, 58, 138);
        Color emerald = new Color(16, 185, 129);
        Color blue = new Color(59, 130, 246);
        Color gray = new Color(100, 116, 139);
        Color slate = new Color(51, 65, 85);
        Color fieldBg = new Color(248, 250, 252);
        Color textLight = Color.WHITE;
        Color textDark = new Color(30, 30, 30);

        // ----- Title Label -----
        JLabel heading = new JLabel("Find Site");
        heading.setBounds(350, 10, 400, 60);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 38));
        heading.setForeground(textLight);
        heading.setBackground(darkBlue);
        heading.setOpaque(true);
        heading.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(heading);

        // ----- Search Criteria Panel -----
        JPanel criteriaPanel = new JPanel();
        criteriaPanel.setBounds(30, 90, 1020, 240);
        criteriaPanel.setBackground(new Color(30, 41, 59));
        criteriaPanel.setLayout(null);
        criteriaPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(blue, 3, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        contentPane.add(criteriaPanel);

        JLabel panelTitle = new JLabel("Search Criteria");
        panelTitle.setBounds(15, 5, 200, 30);
        panelTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelTitle.setForeground(textLight);
        criteriaPanel.add(panelTitle);

        JLabel helpText = new JLabel("(Leave fields empty to ignore in search)");
        helpText.setBounds(200, 5, 300, 30);
        helpText.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        helpText.setForeground(new Color(148, 163, 184));
        criteriaPanel.add(helpText);

        // ----- Labels & Fields -----
        JLabel siteNameLabel = new JLabel("Site Name");
        siteNameLabel.setBounds(50, 60, 180, 35);
        styleLabel(siteNameLabel, slate, textLight);
        criteriaPanel.add(siteNameLabel);

        siteNameField = new JTextField();
        siteNameField.setBounds(240, 60, 300, 40);
        styleTextField(siteNameField, fieldBg, textDark);
        criteriaPanel.add(siteNameField);


        JLabel escortLabel = new JLabel("Escort Limit");
        escortLabel.setBounds(570, 60, 180, 35);
        styleLabel(escortLabel, slate, textLight);
        criteriaPanel.add(escortLabel);

        escortLimitField = new JTextField();
        escortLimitField.setBounds(760, 60, 200, 40);
        styleTextField(escortLimitField, fieldBg, textDark);
        criteriaPanel.add(escortLimitField);


        JLabel clientIDLabel = new JLabel("Client ID");
        clientIDLabel.setBounds(50, 140, 180, 35);
        styleLabel(clientIDLabel, slate, textLight);
        criteriaPanel.add(clientIDLabel);

        clientIDField = new JTextField();
        clientIDField.setBounds(240, 140, 300, 40);
        styleTextField(clientIDField, fieldBg, textDark);
        criteriaPanel.add(clientIDField);

        // ----- Buttons -----
        searchButton = new JButton("SEARCH");
        searchButton.setBounds(600, 135, 170, 55);
        styleButton(searchButton, emerald, textLight, 20);
        criteriaPanel.add(searchButton);
        searchButton.addActionListener(e -> performSearch());

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(780, 135, 170, 55);
        styleButton(clearButton, blue, textLight, 20);
        criteriaPanel.add(clearButton);
        clearButton.addActionListener(e -> clearFields());

        // ----- Results Table -----
        String[] columns = { "Site ID", "Site Name", "Escort Limit", "Client ID" };
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        resultsTable = new JTable(tableModel);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultsTable.setRowHeight(35);
        resultsTable.setBackground(Color.WHITE);
        resultsTable.setForeground(textDark);
        resultsTable.setSelectionBackground(blue);
        resultsTable.setSelectionForeground(Color.WHITE);

        JTableHeader header = resultsTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(darkBlue);
        header.setForeground(Color.WHITE);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns.length; i++) {
            resultsTable.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBounds(30, 350, 1020, 280);
        scrollPane.setBorder(BorderFactory.createLineBorder(blue, 2));
        contentPane.add(scrollPane);

        JLabel resultsLabel = new JLabel("Search Results");
        resultsLabel.setBounds(30, 325, 200, 30);
        resultsLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultsLabel.setForeground(textLight);
        contentPane.add(resultsLabel);

        // ----- Back Button -----
        backButton = new JButton("BACK TO SITE DASHBOARD");
        backButton.setBounds(380, 650, 330, 55);
        styleButton(backButton, gray, textLight, 20);
        contentPane.add(backButton);

        backButton.addActionListener(e -> {
            new SiteDashboard();
            setVisible(false);
        });

        setVisible(true);
    }

   
    private void styleLabel(JLabel label, Color bgColor, Color fgColor) {
        label.setForeground(fgColor);
        label.setBackground(bgColor);
        label.setOpaque(true);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));
    }

    private void styleTextField(JTextField field, Color bgColor, Color fgColor) {
        field.setBackground(bgColor);
        field.setForeground(fgColor);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleButton(JButton button, Color bg, Color fg, int cornerRadius) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBorder(new javax.swing.border.LineBorder(bg, 2, true));
        button.setOpaque(true);

        // Rounded + hover effects
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                if (button.getModel().isPressed()) {
                    g2.setColor(darken(bg, 35));
                } else if (button.getModel().isRollover()) {
                    g2.setColor(brighten(bg, 25));
                } else {
                    g2.setColor(bg);
                }

                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), cornerRadius, cornerRadius);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    private Color brighten(Color c, int amount) {
        return new Color(
                Math.min(255, c.getRed() + amount),
                Math.min(255, c.getGreen() + amount),
                Math.min(255, c.getBlue() + amount)
        );
    }

    private Color darken(Color c, int amount) {
        return new Color(
                Math.max(0, c.getRed() - amount),
                Math.max(0, c.getGreen() - amount),
                Math.max(0, c.getBlue() - amount)
        );
    }

    // ----------------------------------------------------------------------------------------------------
    // 🔎 Search Logic (unchanged)
    // ----------------------------------------------------------------------------------------------------

    private void performSearch() {
        tableModel.setRowCount(0);

        StringBuilder query = new StringBuilder("SELECT * FROM site WHERE 1=1");
        List<String> parameters = new ArrayList<>();

        if (!siteNameField.getText().trim().isEmpty()) {
            query.append(" AND SiteName LIKE ?");
            parameters.add("%" + siteNameField.getText().trim() + "%");
        }

        if (!escortLimitField.getText().trim().isEmpty()) {
            query.append(" AND EscortLimit LIKE ?");
            parameters.add("%" + escortLimitField.getText().trim() + "%");
        }

        if (!clientIDField.getText().trim().isEmpty()) {
            query.append(" AND ClientID LIKE ?");
            parameters.add("%" + clientIDField.getText().trim() + "%");
        }

        try {
			Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query.toString());

            for (int i = 0; i < parameters.size(); i++)
                pstmt.setString(i + 1, parameters.get(i));

            ResultSet rs = pstmt.executeQuery();
            int count = 0;

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("SiteID"),
                        rs.getString("SiteName"),
                        rs.getString("EscortLimit"),
                        rs.getString("ClientID")
                });
                count++;
            }

            JOptionPane.showMessageDialog(this,
                    count == 0 ? "No sites found." : count + " site(s) found.",
                    "Search Complete",
                    JOptionPane.INFORMATION_MESSAGE);

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        siteNameField.setText("");
        escortLimitField.setText("");
        clientIDField.setText("");
        tableModel.setRowCount(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FindSite());
    }
}
