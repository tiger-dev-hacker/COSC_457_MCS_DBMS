import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ViewEmployee extends JFrame implements ActionListener {
    String user_name = "root";
    String passWord = "Keyboard30%$";
    String url = "jdbc:mysql://localhost:3306/mcs";
    JTable table;
    Choice choiceEMP;
    JButton searchbtn, view_all, update, back, assign_job;
    
    public ViewEmployee() {
        // Set dark background
        getContentPane().setBackground(new Color(15, 23, 42)); // Dark slate
        
        // Define custom colors
        Color darkNavy = new Color(30, 58, 138);
        Color royalBlue = new Color(37, 99, 235);
        Color emeraldGreen = new Color(16, 185, 129);
        Color amberOrange = new Color(245, 158, 11);
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85);
        
        // Title Label
        JLabel titleLabel = new JLabel("Employee Records");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setBounds(300, 15, 350, 50);
        titleLabel.setForeground(lightText);
        titleLabel.setBackground(darkNavy);
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);
        
        // Search Label
        JLabel search = new JLabel("Search by Employee ID");
        search.setFont(new Font("Segoe UI", Font.BOLD, 16));
        search.setBounds(20, 85, 200, 30);
        search.setForeground(lightText);
        search.setBackground(labelBg);
        search.setOpaque(true);
        search.setHorizontalAlignment(JLabel.CENTER);
        search.setBorder(new javax.swing.border.LineBorder(labelBg, 2, true));
        add(search);

        // Choice dropdown with styling
        choiceEMP = new Choice();
        choiceEMP.setBounds(230, 85, 150, 30);
        choiceEMP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        choiceEMP.setBackground(new Color(248, 250, 252));
        choiceEMP.setForeground(new Color(30, 30, 30));
        add(choiceEMP);

        try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while (resultSet.next()) {
                choiceEMP.add(resultSet.getString("EmployeeID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Styled Table
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setBackground(new Color(248, 250, 252));
        table.setForeground(new Color(30, 30, 30));
        table.setGridColor(new Color(203, 213, 225));
        table.setSelectionBackground(new Color(59, 130, 246));
        table.setSelectionForeground(Color.WHITE);
        
        // Style table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(51, 65, 85));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Center align cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(20, 180, 860, 450);
        jp.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85), 2));
        add(jp);

        // Search Button
        searchbtn = new JButton("Search");
        searchbtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        searchbtn.setBounds(400, 85, 110, 40);
        styleButton(searchbtn, royalBlue, lightText, 15);
        searchbtn.addActionListener(this);
        add(searchbtn);

        // View All Button
        view_all = new JButton("View All");
        view_all.setFont(new Font("Segoe UI", Font.BOLD, 16));
        view_all.setBounds(530, 85, 110, 40);
        styleButton(view_all, emeraldGreen, lightText, 15);
        view_all.addActionListener(this);
        add(view_all);

        // Update Button
        update = new JButton("Update");
        update.setFont(new Font("Segoe UI", Font.BOLD, 16));
        update.setBounds(660, 85, 110, 40);
        styleButton(update, amberOrange, lightText, 15);
        update.addActionListener(this);
        add(update);

        // Back Button
        back = new JButton("Back");
        back.setFont(new Font("Segoe UI", Font.BOLD, 16));
        back.setBounds(790, 85, 90, 40);
        styleButton(back, new Color(100, 116, 139), lightText, 15); // Gray
        back.addActionListener(this);
        add(back);

        // Assign Job Button
        assign_job = new JButton("Assign Job");
        assign_job.setFont(new Font("Segoe UI", Font.BOLD, 16));
        assign_job.setBounds(400, 130, 110, 40);
        styleButton(assign_job, new Color(100, 116, 139), lightText, 15); // Gray
        assign_job.addActionListener(this);
        add(assign_job);

        
        setSize(920, 700);
        setLayout(null);
        setLocation(300, 100);
        setVisible(true);
    }

    // Helper method to style buttons with rounded corners
    private void styleButton(JButton button, Color bgColor, Color fgColor, int cornerRadius) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(false);

        button.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(button.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), cornerRadius, cornerRadius);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color originalColor = bgColor;

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchbtn) {
            String query = "select * from employee where EmployeeID = '" + choiceEMP.getSelectedItem() + "'";
            try {
                Connection conn = DriverManager.getConnection(url, user_name, passWord);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                table.setModel(DatabaseUtils.buildTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == view_all) {
            try {
                Connection conn = DriverManager.getConnection(url, user_name, passWord);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from employee");
                table.setModel(DatabaseUtils.buildTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == update) {
            new UpdateEmployee(choiceEMP.getSelectedItem());
            setVisible(false);
        } else if (e.getSource() == assign_job) {
            new AssignEmployee(choiceEMP.getSelectedItem());
            setVisible(false); 
        }
        else {
            setVisible(false);
            new EmployeeDashboard();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}