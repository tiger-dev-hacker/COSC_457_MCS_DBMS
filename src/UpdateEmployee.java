import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateEmployee extends JFrame implements ActionListener {


    JTextField tfName, tmName, tlName, tGender, tPhoneNumber, tSalary;
    JLabel tempid;
    JButton add, back;
    String number;

    public UpdateEmployee(String number) {
        this.number = number;
        
        // Set dark background
        getContentPane().setBackground(new Color(15, 23, 42)); // Dark slate

        // Define custom colors
        Color darkNavy = new Color(30, 58, 138);
        Color royalBlue = new Color(37, 99, 235);
        Color emeraldGreen = new Color(16, 185, 129);
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85); // Slate gray for labels
        Color fieldBg = new Color(248, 250, 252); // Light gray for fields
        Color fieldText = new Color(30, 30, 30);

        // Title Label
        JLabel heading = new JLabel("Update Employee Details");
        heading.setBounds(250, 25, 500, 60);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 36));
        heading.setForeground(lightText);
        heading.setBackground(darkNavy);
        heading.setOpaque(true);
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading);

        // Employee ID Label
        JLabel empID = new JLabel("Employee ID");
        empID.setBounds(50, 120, 140, 35);
        empID.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(empID, labelBg, lightText);
        add(empID);

        // Employee ID Value (read-only)
        tempid = new JLabel(this.number);
        tempid.setBounds(210, 120, 200, 35);
        tempid.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tempid.setForeground(emeraldGreen);
        tempid.setBackground(new Color(30, 41, 59));
        tempid.setOpaque(true);
        tempid.setHorizontalAlignment(JLabel.CENTER);
        tempid.setBorder(new javax.swing.border.LineBorder(emeraldGreen, 2, true));
        add(tempid);

        // First Name Label
        JLabel fname = new JLabel("First Name");
        fname.setBounds(50, 200, 140, 35);
        fname.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(fname, labelBg, lightText);
        add(fname);

        tfName = new JTextField();
        tfName.setBounds(210, 200, 200, 40);
        tfName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(tfName, fieldBg, fieldText);
        add(tfName);

        // Middle Name Label
        JLabel mname = new JLabel("Middle Name");
        mname.setBounds(50, 280, 140, 35);
        mname.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(mname, labelBg, lightText);
        add(mname);

        tmName = new JTextField();
        tmName.setBounds(210, 280, 200, 40);
        tmName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(tmName, fieldBg, fieldText);
        add(tmName);

        // Salary Label
        JLabel salary = new JLabel("Salary");
        salary.setBounds(50, 360, 140, 35);
        salary.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(salary, labelBg, lightText);
        add(salary);

        tSalary = new JTextField();
        tSalary.setBounds(210, 360, 200, 40);
        tSalary.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(tSalary, fieldBg, fieldText);
        add(tSalary);

        // Last Name Label
        JLabel lname = new JLabel("Last Name");
        lname.setBounds(480, 120, 140, 35);
        lname.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(lname, labelBg, lightText);
        add(lname);

        tlName = new JTextField();
        tlName.setBounds(640, 120, 200, 40);
        tlName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(tlName, fieldBg, fieldText);
        add(tlName);

        // Gender Label
        JLabel gender = new JLabel("Gender");
        gender.setBounds(480, 200, 140, 35);
        gender.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(gender, labelBg, lightText);
        add(gender);

        tGender = new JTextField();
        tGender.setBounds(640, 200, 200, 40);
        tGender.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(tGender, fieldBg, fieldText);
        add(tGender);

        // Phone Number Label
        JLabel phone_number = new JLabel("Phone Number");
        phone_number.setBounds(480, 280, 140, 35);
        phone_number.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(phone_number, labelBg, lightText);
        add(phone_number);

        tPhoneNumber = new JTextField();
        tPhoneNumber.setBounds(640, 280, 200, 40);
        tPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        styleTextField(tPhoneNumber, fieldBg, fieldText);
        add(tPhoneNumber);

        // Load existing employee data
        try {
			Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            String query = "select * from employee where EmployeeID = '" + number + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tfName.setText(resultSet.getString("Fname"));
                tmName.setText(resultSet.getString("Mname"));
                tlName.setText(resultSet.getString("Lname"));
                tGender.setText(resultSet.getString("gender"));
                tPhoneNumber.setText(resultSet.getString("phone_number"));
                tSalary.setText(resultSet.getString("salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update Button
        add = new JButton("UPDATE");
        add.setBounds(280, 450, 180, 60);
        add.setFont(new Font("Segoe UI", Font.BOLD, 20));
        styleButton(add, emeraldGreen, lightText, 20);
        add.addActionListener(this);
        add(add);

        // Back Button
        back = new JButton("BACK");
        back.setBounds(500, 450, 180, 60);
        back.setFont(new Font("Segoe UI", Font.BOLD, 20));
        styleButton(back, royalBlue, lightText, 20);
        back.addActionListener(this);
        add(back);

        setSize(900, 600);
        setLayout(null);
        setLocation(300, 100);
        setVisible(true);
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
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
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
        if (e.getSource() == add) {
            String fname = tfName.getText();
            String mname = tmName.getText();
            String lname = tlName.getText();
            String gender = tGender.getText();
            String phone_number = tPhoneNumber.getText();
            String salary = tSalary.getText();

            // Validate salary
            if (salary.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Salary cannot be empty");
                return;
            }

            try {
                Double.parseDouble(salary);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid salary amount");
                return;
            }

            try {
				Connection conn = DatabaseConnection.getConnection();
                Statement statement = conn.createStatement();
                String query = "update employee set Fname = '" + fname + "', Mname = '" + mname + "', Lname = '" + lname + "', gender = '" + gender + "', phone_number ='" + phone_number + "', salary = '" + salary + "' where EmployeeID = '" + number + "'";
                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new EmployeeDashboard();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
            new ViewEmployee();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}