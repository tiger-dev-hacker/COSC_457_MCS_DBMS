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

public class FindClient extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField clientNameField;
    private JTextField bcelField;
    private JTextField contractField;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;
    

    public FindClient() {
        setTitle("Find Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 80, 1100, 750);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(15, 23, 42)); // Dark slate background
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Define custom colors
        Color darkBlue = new Color(30, 58, 138);
        Color searchColor = new Color(16, 185, 129); // Emerald green
        Color clearColor = new Color(59, 130, 246); // Blue
        Color backColor = new Color(100, 116, 139); // Gray
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85);
        Color fieldBg = new Color(248, 250, 252);
        Color fieldText = new Color(30, 30, 30);

        // Title Label
        JLabel titleLabel = new JLabel("Find Client");
        titleLabel.setBounds(350, 10, 400, 60);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 38));
        titleLabel.setForeground(lightText);
        titleLabel.setBackground(darkBlue);
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(titleLabel);

        // Search Criteria Panel
        JPanel criteriaPanel = new JPanel();
        criteriaPanel.setBounds(30, 90, 1020, 240);
        criteriaPanel.setBackground(new Color(30, 41, 59));
        criteriaPanel.setLayout(null);
        criteriaPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(59, 130, 246), 3, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        contentPane.add(criteriaPanel);

        // Panel title
        JLabel panelTitle = new JLabel("Search Criteria");
        panelTitle.setBounds(15, 5, 200, 30);
        panelTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelTitle.setForeground(lightText);
        criteriaPanel.add(panelTitle);

        JLabel helpText = new JLabel("(Leave fields empty to ignore in search)");
        helpText.setBounds(200, 5, 300, 30);
        helpText.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        helpText.setForeground(new Color(148, 163, 184));
        criteriaPanel.add(helpText);

        // Client Name Label
        JLabel clientNameLabel = new JLabel("Client Name");
        clientNameLabel.setBounds(50, 50, 200, 35);
        clientNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        styleLabel(clientNameLabel, labelBg, lightText);
        criteriaPanel.add(clientNameLabel);

        // Client Name Field
        clientNameField = new JTextField();
        clientNameField.setBounds(270, 50, 300, 40);
        clientNameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(clientNameField, fieldBg, fieldText);
        criteriaPanel.add(clientNameField);

        // Background Check Label
        JLabel bcelLabel = new JLabel("Background Check Limit");
        bcelLabel.setBounds(600, 50, 200, 35);
        bcelLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        styleLabel(bcelLabel, labelBg, lightText);
        criteriaPanel.add(bcelLabel);

        // Background Check Field
        bcelField = new JTextField();
        bcelField.setBounds(820, 50, 150, 40);
        bcelField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(bcelField, fieldBg, fieldText);
        criteriaPanel.add(bcelField);

        // Contract ID Label
        JLabel contractLabel = new JLabel("Contract ID");
        contractLabel.setBounds(50, 110, 200, 35);
        contractLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        styleLabel(contractLabel, labelBg, lightText);
        criteriaPanel.add(contractLabel);

        // Contract ID Field
        contractField = new JTextField();
        contractField.setBounds(270, 110, 300, 40);
        contractField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(contractField, fieldBg, fieldText);
        criteriaPanel.add(contractField);

        // Buttons
        searchButton = new JButton("Search");
        searchButton.setBounds(300, 175, 180, 50);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleButton(searchButton, searchColor, lightText, 15);
        criteriaPanel.add(searchButton);
        searchButton.addActionListener(e -> performSearch());

        clearButton = new JButton("Clear");
        clearButton.setBounds(520, 175, 180, 50);
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleButton(clearButton, clearColor, lightText, 15);
        criteriaPanel.add(clearButton);
        clearButton.addActionListener(e -> clearFields());

        // Results Table
        String[] columnNames = {"Client ID", "Client Name", "Background Check Expiry Limit"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        resultsTable = new JTable(tableModel);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultsTable.setRowHeight(35);
        resultsTable.setGridColor(new Color(203, 213, 225));
        resultsTable.setBackground(Color.WHITE);
        resultsTable.setForeground(new Color(30, 30, 30));
        resultsTable.setSelectionBackground(new Color(59, 130, 246));
        resultsTable.setSelectionForeground(Color.WHITE);

        // Style table header
        JTableHeader header = resultsTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(darkBlue);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new java.awt.Dimension(header.getWidth(), 45));

        // Center align all cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < resultsTable.getColumnCount(); i++) {
            resultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBounds(30, 350, 1020, 280);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(59, 130, 246), 2));
        contentPane.add(scrollPane);

        // Results label
        JLabel resultsLabel = new JLabel("Search Results");
        resultsLabel.setBounds(30, 345, 200, 30);
        resultsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultsLabel.setForeground(lightText);
        contentPane.add(resultsLabel);

        // Back Button
        backButton = new JButton("Back to Client Dashboard");
        backButton.setBounds(380, 650, 320, 50);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleButton(backButton, backColor, lightText, 15);
        contentPane.add(backButton);
        backButton.addActionListener(e -> {
            new ClientDashboard();
            setVisible(false);
        });

        setVisible(true);
    }

    private void performSearch() {
        // Clear previous results
        tableModel.setRowCount(0);

        // Build dynamic SQL query based on filled fields
        StringBuilder query = new StringBuilder("SELECT * FROM client WHERE 1=1");
        List<String> parameters = new ArrayList<>();

        String tool_name = clientNameField.getText().trim();
        if (!tool_name.isEmpty()) {
            query.append(" AND ClientName LIKE ?");
            parameters.add("%" + tool_name + "%");
        }

        String tool_manifest = bcelField.getText().trim();
        if (!tool_manifest.isEmpty()) {
            query.append(" AND BackgroundCheckExpiryLimit LIKE ?");
            parameters.add("%" + tool_manifest + "%");
        }

        String contract_id = contractField.getText().trim();
        if (!contract_id.isEmpty()) {
            if (!tool_name.isEmpty() || !tool_manifest.isEmpty()) {
                query.append("");
            } else {
                query = new StringBuilder("SELECT * FROM client WHERE ClientID = (SELECT ClientID FROM contract WHERE ContractID = '" + contract_id + "')");
            }
        }

        // Execute query
        try {
        	Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query.toString());

            // Set parameters
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setString(i + 1, parameters.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            int count = 0;

            while (rs.next()) {
                Object[] row = {
                    rs.getString("ClientID"),
                    rs.getString("ClientName"),
                    rs.getString("BackgroundCheckExpiryLimit"),
                };
                tableModel.addRow(row);
                count++;
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(this,
                    "No clients found matching the criteria.",
                    "No Results",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    count + " client(s) found.",
                    "Search Complete",
                    JOptionPane.INFORMATION_MESSAGE);
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error searching clients: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        clientNameField.setText("");
        bcelField.setText("");
        contractField.setText("");
        tableModel.setRowCount(0);
    }

    // Helper method to style labels
    private void styleLabel(JLabel label, Color bgColor, Color fgColor) {
        label.setBackground(bgColor);
        label.setForeground(fgColor);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));
    }

    // Helper method to style text fields
    private void styleTextField(JTextField field, Color bgColor, Color fgColor) {
        field.setBackground(bgColor);
        field.setForeground(fgColor);
        field.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
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

                if (button.getModel().isPressed()) {
                    g2.setColor(darkenColor(button.getBackground(), 40));
                } else if (button.getModel().isRollover()) {
                    g2.setColor(brightenColor(button.getBackground(), 30));
                } else {
                    g2.setColor(button.getBackground());
                }

                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), cornerRadius, cornerRadius);
                g2.dispose();
                super.paint(g, c);
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.repaint();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.repaint();
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.repaint();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FindClient());
    }
}