
import javax.swing.*;     // Swing GUI classes (JFrame, JButton, JTextField, etc.)
import java.awt.*;        // Layout managers like BorderLayout, GridLayout
import java.sql.*;        // JDBC classes for database connection

public class ViewEmployee extends JFrame {

    // Database connection details
    String url = "jdbc:mysql://localhost:3306/jdbc_demo";
    String user = "root";
    String password = "Keyboard30%$";

    // Input fields and output area
    JTextField nameField, majorField;
    JTextArea output;

    public ViewEmployee() {

        // Basic window setup
        setTitle("Student App");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ---------- Top Panel: Input Fields ----------
        JPanel top = new JPanel(new GridLayout(2, 2));  // 2 rows × 2 columns

        top.add(new JLabel("Name:"));
        nameField = new JTextField();
        top.add(nameField);

        top.add(new JLabel("Employee"));
        majorField = new JTextField();
        top.add(majorField);

        add(top, BorderLayout.NORTH);

        // ---------- Center Area: Output ----------
        output = new JTextArea();
        output.setEditable(false);                      // User cannot type here
        add(new JScrollPane(output), BorderLayout.CENTER);

        // ---------- Bottom Panel: Buttons ----------
        JPanel bottom = new JPanel(new GridLayout(1, 3));  // 1 row × 3 columns

        JButton viewBtn = new JButton("View");
        JButton viewAllBtn = new JButton("View All"); 
        JButton backBtn = new JButton("Back to Employee Page"); 
      
        bottom.add(viewBtn);
        bottom.add(viewAllBtn);
        bottom.add(backBtn); 
        add(bottom, BorderLayout.SOUTH);

        // ---------- Button Click Actions ----------
        viewBtn.addActionListener(e -> viewStudents());
        
        backBtn.addActionListener(e -> handleBackButton());
        setVisible(true);   // Show window
    }

    // ---------- Create a Database Connection ----------
    private Connection getConn() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL driver
        return DriverManager.getConnection(url, user, password);
    }

    // ---------- VIEW Students (SELECT) ----------
    private void viewStudents() {
        output.setText("");  // Clear output

        try (Connection conn = getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            // Loop through result rows
            while (rs.next()) {
                output.append(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("major") + "\n"
                );
            }

        } catch (Exception ex) {
            output.setText(ex.getMessage());
        }
    }

   
    private void handleBackButton()
    {
    	new EmployeeDashboard(); 
    	setVisible(false); 
    }
    // ---------- Main Method ----------
    public static void main(String[] args) {
        new ViewEmployee();   // Create and show the app window
    }
}

