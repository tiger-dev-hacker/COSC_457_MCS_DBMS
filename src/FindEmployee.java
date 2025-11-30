import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class FindEmployee extends JFrame {
    private JPanel contentPane;
    private JTextField ssnField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField genderField;
    private JTextField jobIdField;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;
    String user_name = "root";
    String passWord = "Keyboard30%$";
    String url = "jdbc:mysql://localhost:3306/mcs";
    
    public FindEmployee() {
        setTitle("Find Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 1000, 600);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(240, 242, 245));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title
        JLabel titleLabel = new JLabel("Find Employee");
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
        JLabel ssnLabel = new JLabel("SSN:");
        ssnLabel.setBounds(30, 30, 80, 25);
        ssnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(ssnLabel);

        ssnField = new JTextField();
        ssnField.setBounds(120, 30, 150, 30);
        ssnField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(ssnField);
        
        JLabel firstnameLabel = new JLabel("First Name:");
        firstnameLabel.setBounds(290, 30, 90, 25);
        firstnameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(firstnameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(390, 30, 150, 30);
        firstNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(firstNameField);

        JLabel middlenameLabel = new JLabel("Middle Name:");
        middlenameLabel.setBounds(560, 30, 100, 25);
        middlenameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(middlenameLabel);

        middleNameField = new JTextField();
        middleNameField.setBounds(670, 30, 150, 30);
        middleNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(middleNameField);

        // Row 2: Last Name, Phone Number
        JLabel lastnameLabel = new JLabel("Last Name:");
        lastnameLabel.setBounds(30, 70, 90, 25);
        lastnameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(lastnameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(120, 70, 150, 30);
        lastNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(lastNameField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(290, 70, 110, 25);
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(410, 70, 150, 30);
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(phoneField);

        // Row 3: Gender, Job ID
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 110, 90, 25);
        genderLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(genderLabel);

        genderField = new JTextField();
        genderField.setBounds(120, 110, 150, 30);
        genderField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(genderField);

        JLabel jobIdLabel = new JLabel("Job ID");
        jobIdLabel.setBounds(290, 110, 90, 25);
        jobIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(jobIdLabel);

        jobIdField = new JTextField();
        jobIdField.setBounds(370, 110, 150, 30);
        jobIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(jobIdField);

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
        String[] columnNames = {"SSN", "First Name", "Middle Name", "Last Name", "Phone Number", "Gender", "Job ID"};
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
        backButton = new JButton("Back to Employee Dashboard");
        backButton.setBounds(340, 510, 280, 40);
        styleButton(backButton, new Color(30, 58, 138), Color.WHITE);
        contentPane.add(backButton);
        backButton.addActionListener(e -> {
            new EmployeeDashboard();
            setVisible(false);
        });

        setVisible(true);
    }

    private void performSearch() {
        // Clear previous results
        tableModel.setRowCount(0);

        // Build dynamic SQL query based on filled fields
        StringBuilder query = new StringBuilder("SELECT * FROM employee WHERE 1=1");
        List<String> parameters = new ArrayList<>();

        String ssn = ssnField.getText().trim();
        if (!ssn.isEmpty()) {
            query.append(" AND SSN LIKE ?");
            parameters.add("%" + ssn + "%");
        }

        String firstName = firstNameField.getText().trim();
        if (!firstName.isEmpty()) {
            query.append(" AND Fname LIKE ?");
            parameters.add("%" + firstName + "%");
        }

        String middleName = middleNameField.getText().trim();
        if (!middleName.isEmpty()) {
            query.append(" AND Mname LIKE ?");
            parameters.add("%" + middleName + "%");
        }

        String lastName = lastNameField.getText().trim();
        if (!lastName.isEmpty()) {
            query.append(" AND Lname LIKE ?");
            parameters.add("%" + lastName + "%");
        }

        String phone = phoneField.getText().trim();
        if (!phone.isEmpty()) {
            query.append(" AND phone_number LIKE ?");
            parameters.add("%" + phone + "%");
        }

        String gender = genderField.getText().trim();
        if (!gender.isEmpty()) {
            query.append(" AND gender LIKE ?");
            parameters.add("%" + gender + "%");
        }

        String jobId = jobIdField.getText().trim();
        if (!jobId.isEmpty()) {
            query.append(" AND job_id LIKE ?");
            parameters.add("%" + jobId + "%");
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
                    rs.getString("SSN"),
                    rs.getString("Fname"),
                    rs.getString("Mname"),
                    rs.getString("Lname"),
                    rs.getString("phone_number"),
                    rs.getString("gender"),
                };
                tableModel.addRow(row);
                count++;
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(this,
                    "No employees found matching the criteria.",
                    "No Results",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    count + " employee(s) found.",
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
        ssnField.setText("");
        firstNameField.setText("");
        middleNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        genderField.setText("");
        jobIdField.setText("");
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