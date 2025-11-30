import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class FindContract extends JFrame {
    private JPanel contentPane;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField budgetField;
    private JTextField federalWageScaleField;
    private JTextField ClientID;
    private JTextField signatureDateField;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;
    String user_name = "root";
    String passWord = "Keyboard30%$";
    String url = "jdbc:mysql://localhost:3306/mcs";
    
    public FindContract() {
        setTitle("Find Contract");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 1000, 600);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(240, 242, 245));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title
        JLabel titleLabel = new JLabel("Find Contract");
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
        JLabel ssnLabel = new JLabel("Start Date");
        ssnLabel.setBounds(30, 30, 80, 25);
        ssnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(ssnLabel);

        startDateField = new JTextField();
        startDateField.setBounds(120, 30, 150, 30);
        startDateField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(startDateField);
        
        JLabel firstnameLabel = new JLabel("End Date");
        firstnameLabel.setBounds(290, 30, 90, 25);
        firstnameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(firstnameLabel);

        endDateField = new JTextField();
        endDateField.setBounds(390, 30, 150, 30);
        endDateField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(endDateField);

        JLabel middlenameLabel = new JLabel("Budget");
        middlenameLabel.setBounds(560, 30, 100, 25);
        middlenameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(middlenameLabel);

        budgetField = new JTextField();
        budgetField.setBounds(670, 30, 150, 30);
        budgetField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(budgetField);

        // Row 2: Last Name, Phone Number
        JLabel lastnameLabel = new JLabel("Federal Wage Scale");
        lastnameLabel.setBounds(30, 70, 90, 25);
        lastnameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(lastnameLabel);

        federalWageScaleField = new JTextField();
        federalWageScaleField.setBounds(120, 70, 150, 30);
        federalWageScaleField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(federalWageScaleField);

        JLabel phoneLabel = new JLabel("Client ID");
        phoneLabel.setBounds(290, 70, 110, 25);
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(phoneLabel);

        ClientID = new JTextField();
        ClientID.setBounds(410, 70, 150, 30);
        ClientID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(ClientID);

        // Row 3: Gender, Job ID
        JLabel genderLabel = new JLabel("Signature Date");
        genderLabel.setBounds(30, 110, 90, 25);
        genderLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(genderLabel);

        signatureDateField = new JTextField();
        signatureDateField.setBounds(120, 110, 150, 30);
        signatureDateField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(signatureDateField); 
        

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
        String[] columnNames = {"Start Date", "End Date", "Budget", "Federal Wage Scale", "Client ID", "Signature Date" };
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
        backButton = new JButton("Back to Contract Dashboard");
        backButton.setBounds(340, 510, 280, 40);
        styleButton(backButton, new Color(30, 58, 138), Color.WHITE);
        contentPane.add(backButton);
        backButton.addActionListener(e -> {
            new ContractDashboard();
            setVisible(false);
        });

        setVisible(true);
    }

    private void performSearch() {
        // Clear previous results
        tableModel.setRowCount(0);

        // Build dynamic SQL query based on filled fields
        StringBuilder query = new StringBuilder("SELECT * FROM contract WHERE 1=1");
        List<String> parameters = new ArrayList<>();

        String ssn = startDateField.getText().trim();
        if (!ssn.isEmpty()) {
            query.append(" AND StartDate LIKE ?");
            parameters.add("%" + ssn + "%");
        }

        String firstName = endDateField.getText().trim();
        if (!firstName.isEmpty()) {
            query.append(" AND EndDate LIKE ?");
            parameters.add("%" + firstName + "%");
        }

        String middleName = budgetField.getText().trim();
        if (!middleName.isEmpty()) {
            query.append(" AND budget LIKE ?");
            parameters.add("%" + middleName + "%");
        }

        String lastName = federalWageScaleField.getText().trim();
        if (!lastName.isEmpty()) {
            query.append(" AND FederalWageScale LIKE ?");
            parameters.add("%" + lastName + "%");
        }

        String phone = ClientID.getText().trim();
        if (!phone.isEmpty()) {
            query.append(" AND ClientID LIKE ?");
            parameters.add("%" + phone + "%");
        }

        String gender = signatureDateField.getText().trim();
        if (!gender.isEmpty()) {
            query.append(" AND SignatureDate LIKE ?");
            parameters.add("%" + gender + "%");
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
                    rs.getString("StartDate"),
                    rs.getString("EndDate"),
                    rs.getString("Budget"),
                    rs.getString("FederalWageScale"),
                    rs.getString("ClientID"),
                    rs.getString("SignatureDate"),
                };
                tableModel.addRow(row);
                count++;
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(this,
                    "No contracts found matching the criteria.",
                    "No Results",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    count + " contracts(s) found.",
                    "Search Complete",
                    JOptionPane.INFORMATION_MESSAGE);
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error searching employees: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void clearFields() {
    	startDateField.setText("");
    	endDateField.setText("");
    	budgetField.setText("");
    	federalWageScaleField.setText("");
    	ClientID.setText("");
    	signatureDateField.setText("");
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
        SwingUtilities.invokeLater(() -> new FindEmployee());
    }
}