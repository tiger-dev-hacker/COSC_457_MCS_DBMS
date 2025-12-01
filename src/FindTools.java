import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class FindTools extends JFrame {
    private JPanel contentPane;
    private JTextField toolNameField;
    private JTextField toolManifestField;
    private JTextField contractIDField;

    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;
    String user_name = "root";
    String passWord = "Keyboard30%$";
    String url = "jdbc:mysql://localhost:3306/mcs";
    
    public FindTools() {
        setTitle("Find Tools");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 1000, 600);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(240, 242, 245));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title
        JLabel titleLabel = new JLabel("Find Tools");
        titleLabel.setBounds(380, 10, 300, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(30, 58, 138));
        contentPane.add(titleLabel);

        // Search Criteria Panel
        JPanel criteriaPanel = new JPanel();
        criteriaPanel.setBounds(30, 60, 920, 180);
        criteriaPanel.setBackground(Color.WHITE);
        criteriaPanel.setLayout(null);
        criteriaPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(30, 58, 138), 2),
            "Search Criteria (Leave empty to ignore)",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), new Color(30, 58, 138)));
        contentPane.add(criteriaPanel);

        // Row 1: SSN, First Name, Middle Name
        JLabel ssnLabel = new JLabel("Tool Name");
        ssnLabel.setBounds(30, 30, 80, 25);
        ssnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(ssnLabel);

        toolNameField = new JTextField();
        toolNameField.setBounds(120, 30, 150, 30);
        toolNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(toolNameField);
        
        JLabel firstnameLabel = new JLabel("Tool Manifest");
        firstnameLabel.setBounds(290, 30, 90, 25);
        firstnameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(firstnameLabel);

        toolManifestField = new JTextField();
        toolManifestField.setBounds(390, 30, 150, 30);
        toolManifestField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(toolManifestField);

        JLabel contractIDLabel = new JLabel("ContractID");
        contractIDLabel.setBounds(290, 60, 90, 25);
        contractIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(contractIDLabel);

        contractIDField = new JTextField();
        contractIDField.setBounds(390, 60, 150, 30);
        contractIDField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(contractIDField);

        

        // Buttons
        searchButton = new JButton("Search");
        searchButton.setBounds(580, 110, 120, 35);
        styleButton(searchButton, new Color(20, 184, 166), Color.WHITE);
        criteriaPanel.add(searchButton);
        searchButton.addActionListener(e -> performSearch());

        clearButton = new JButton("Clear");
        clearButton.setBounds(720, 110, 120, 35);
        styleButton(clearButton, new Color(59, 130, 246), Color.WHITE);
        criteriaPanel.add(clearButton);
        clearButton.addActionListener(e -> clearFields());

        // Results Table
        String[] columnNames = {"Tool Name", "Tool Manifest"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        resultsTable = new JTable(tableModel);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resultsTable.setRowHeight(25);
        resultsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        resultsTable.getTableHeader().setBackground(new Color(30, 58, 138));
        resultsTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBounds(30, 260, 920, 230);
        contentPane.add(scrollPane);

        // Back Button
        backButton = new JButton("Back to Tool Dashboard");
        backButton.setBounds(340, 510, 280, 40);
        styleButton(backButton, new Color(30, 58, 138), Color.WHITE);
        contentPane.add(backButton);
        backButton.addActionListener(e -> {
            new ToolDashboard();
            setVisible(false);
        });

        setVisible(true);
    }

    private void performSearch() {
        // Clear previous results
        tableModel.setRowCount(0);

        // Build dynamic SQL query based on filled fields
        StringBuilder query = new StringBuilder("SELECT * FROM tool WHERE 1=1");
        List<String> parameters = new ArrayList<>();

        String tool_name = toolNameField.getText().trim();
        if (!tool_name.isEmpty()) {
            query.append(" AND ToolName LIKE ?");
            parameters.add("%" + tool_name + "%");
        }

        String tool_manifest = toolManifestField.getText().trim();
        if (!tool_manifest.isEmpty()) {
            query.append(" AND ToolManifest LIKE ?");
            parameters.add("%" + tool_manifest + "%");
        }
        
        String contract_id = contractIDField.getText().trim();

        if (!contract_id.isEmpty()) {
        	if (!tool_name.isEmpty() || !tool_name.isEmpty())
        	{
        		query.append(""); 
        	}
        	else
        	{
        		query = new StringBuilder ("SELECT DISTINCT tool.* FROM tool JOIN used_in ON tool.ToolID = used_in.ToolID WHERE ContractID = '" + contract_id + "'");

        	}
            
        }

        // Execute query
        try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);

            PreparedStatement pstmt = conn.prepareStatement(query.toString());

            // Set parameters
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setString(i + 1, parameters.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            int count = 0;

            while (rs.next()) {
                Object[] row = {
                    rs.getString("ToolName"),
                    rs.getString("ToolManifest"),
                 
                };
                tableModel.addRow(row);
                count++;
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(this,
                    "No tools found matching the criteria.",
                    "No Results",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    count + " tool(s) found.",
                    "Search Complete",
                    JOptionPane.INFORMATION_MESSAGE);
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error searching tools: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void clearFields() {
    	toolNameField.setText("");
        toolManifestField.setText("");
        tableModel.setRowCount(0);
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FindTools());
    }
}