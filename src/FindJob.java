
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class FindJob extends JFrame {
    private JPanel contentPane;
    private JTextField jobNameField;
    private JTextField jobLengthField;
    private JTextField contractIDField;
    private JTextField EmployeeIDField;

    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;
    String user_name = "root";
    String passWord = "Keyboard30%$";
    String url = "jdbc:mysql://localhost:3306/mcs";
    
    public FindJob() {
        setTitle("Find Job");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 1000, 600);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(240, 242, 245));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title
        JLabel titleLabel = new JLabel("Find Jobs");
        titleLabel.setBounds(380, 10, 300, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(30, 58, 138));
        contentPane.add(titleLabel);
        JPanel criteriaPanel = new JPanel();
        criteriaPanel.setBounds(30, 60, 920, 180);
        criteriaPanel.setBackground(Color.WHITE);
        criteriaPanel.setLayout(null);
        criteriaPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(30, 58, 138), 2),
                "Search Criteria (Leave empty to ignore)",
                0, 0, new Font("Segoe UI", Font.BOLD, 14), new Color(30, 58, 138)));
        contentPane.add(criteriaPanel);


     // Row 1
        JLabel jobNameLabel = new JLabel("Job Name");
        jobNameLabel.setBounds(30, 30, 120, 25);
        jobNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(jobNameLabel);

        jobNameField = new JTextField();
        jobNameField.setBounds(150, 30, 180, 30);
        jobNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(jobNameField);

        JLabel jobLengthLabel = new JLabel("Job Length (weeks)");
        jobLengthLabel.setBounds(360, 30, 150, 25);
        jobLengthLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(jobLengthLabel);

        jobLengthField = new JTextField();
        jobLengthField.setBounds(520, 30, 180, 30);
        jobLengthField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(jobLengthField);

        // Row 2
        JLabel employeeIDLabel = new JLabel("Employee ID");
        employeeIDLabel.setBounds(30, 70, 120, 25);
        employeeIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(employeeIDLabel);

        EmployeeIDField = new JTextField();
        EmployeeIDField.setBounds(150, 70, 180, 30);
        EmployeeIDField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(EmployeeIDField);

        JLabel contractIDLabel = new JLabel("Contract ID");
        contractIDLabel.setBounds(360, 70, 120, 25);
        contractIDLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(contractIDLabel);

        contractIDField = new JTextField();
        contractIDField.setBounds(520, 70, 180, 30);
        contractIDField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        criteriaPanel.add(contractIDField);


        // Buttons
        searchButton = new JButton("Search");
        searchButton.setBounds(720, 30, 150, 35);
        styleButton(searchButton, new Color(20, 184, 166), Color.WHITE);
        criteriaPanel.add(searchButton);
        searchButton.addActionListener(e -> performSearch());

        clearButton = new JButton("Clear");
        clearButton.setBounds(720, 75, 150, 35);
        styleButton(clearButton, new Color(59, 130, 246), Color.WHITE);
        criteriaPanel.add(clearButton);
        clearButton.addActionListener(e -> clearFields());

        // Results Table
        String[] columnNames = {"Job Name", "Job Length (In Weeks)", "ContractID"};
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
        backButton = new JButton("Back to Job Dashboard");
        backButton.setBounds(340, 510, 280, 40);
        styleButton(backButton, new Color(30, 58, 138), Color.WHITE);
        contentPane.add(backButton);
        backButton.addActionListener(e -> {
            new JobDashboard();
            setVisible(false);
        });

        setVisible(true);
    }

    private void performSearch() {
        // Clear previous results
        tableModel.setRowCount(0);

        // Build dynamic SQL query based on filled fields
        StringBuilder query = new StringBuilder("SELECT * FROM job WHERE 1=1");
        List<String> parameters = new ArrayList<>();

        String job_name = jobNameField.getText().trim();
        if (!job_name.isEmpty()) {
            query.append(" AND JobName LIKE ?");
            parameters.add("%" + job_name + "%");
        }

        String job_length = jobLengthField.getText().trim();
        if (!job_length.isEmpty()) {
            query.append(" AND JobLength LIKE ?");
            parameters.add("%" + job_length + "%");
        }
        
        String contract_id = contractIDField.getText().trim();
        if (!contract_id.isEmpty()) {
            query.append(" AND ContractID LIKE ?");
            parameters.add("%" + contract_id + "%");
        }
        
        String employee_id = EmployeeIDField.getText().trim(); 
        if (!employee_id.isEmpty()) {
        	if (!job_name.isEmpty() || !job_length.isEmpty() || !contract_id.isEmpty())
        	{
        		query.append(""); 
        	}
        	else
        	{
        		query = new StringBuilder ("SELECT DISTINCT job.* FROM job JOIN works_on ON job.JobID = works_on.JobID WHERE EmployeeID = '" + employee_id + "'");

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
                    rs.getString("JobName"),
                    rs.getString("JobLength"),
                    rs.getString("ContractID")

                };
                tableModel.addRow(row);
                count++;
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(this,
                    "No jobs found matching the criteria.",
                    "No Results",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    count + " job(s) found.",
                    "Search Complete",
                    JOptionPane.INFORMATION_MESSAGE);
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error searching jobs: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void clearFields() {
    	jobNameField.setText("");
    	jobLengthField.setText("");
    	contractIDField.setText("");

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