import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteEmployee extends JFrame implements ActionListener {
    String user_name = "root";
    String passWord = "Keyboard30%$";
    String url = "jdbc:mysql://localhost:3306/mcs";
    Choice choiceEMPID;
    JButton delete, back;

    public DeleteEmployee() {
        // Define custom colors
        Color crimsonRed = new Color(185, 28, 28);
        Color royalBlue = new Color(37, 99, 235);
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85, 220); // Semi-transparent slate gray
        Color valueBg = new Color(248, 250, 252, 240); // Semi-transparent light background
        Color valueText = new Color(30, 30, 30);

        // Background image
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("images/rback.png"));
        Image i22 = i11.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel image = new JLabel(i33);
        image.setBounds(0, 0, 1120, 630);
        add(image);

        // Title Label
        JLabel titleLabel = new JLabel("Delete Employee");
        titleLabel.setBounds(350, 20, 400, 60);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 38));
        titleLabel.setForeground(lightText);
        titleLabel.setBackground(new Color(30, 58, 138));
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        image.add(titleLabel);

        // Employee ID Label
        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 120, 150, 40);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(label, labelBg, lightText);
        image.add(label);

        // Employee ID Choice
        choiceEMPID = new Choice();
        choiceEMPID.setBounds(220, 120, 180, 40);
        choiceEMPID.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        choiceEMPID.setBackground(new Color(248, 250, 252));
        choiceEMPID.setForeground(new Color(30, 30, 30));
        image.add(choiceEMPID);

        try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while (resultSet.next()) {
                choiceEMPID.add(resultSet.getString("EmployeeID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Name Label
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 200, 150, 40);
        labelName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(labelName, labelBg, lightText);
        image.add(labelName);

        // Name Value
        JLabel textName = new JLabel();
        textName.setBounds(220, 200, 250, 40);
        textName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        styleValueLabel(textName, valueBg, valueText);
        image.add(textName);

        // Phone Label
        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50, 280, 150, 40);
        labelPhone.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(labelPhone, labelBg, lightText);
        image.add(labelPhone);

        // Phone Value
        JLabel textPhone = new JLabel();
        textPhone.setBounds(220, 280, 250, 40);
        textPhone.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        styleValueLabel(textPhone, valueBg, valueText);
        image.add(textPhone);

        // Load initial employee data
        try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee where EmployeeID = '" + choiceEMPID.getSelectedItem() + "'");
            while (resultSet.next()) {
                textName.setText(resultSet.getString("Fname"));
                textPhone.setText(resultSet.getString("phone_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update data when selection changes
        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Connection conn = DriverManager.getConnection(url, user_name, passWord);
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from employee where EmployeeID = '" + choiceEMPID.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        textName.setText(resultSet.getString("Fname"));
                        textPhone.setText(resultSet.getString("phone_number"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        // Delete icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(650, 100, 200, 200);
        image.add(img);

        // Delete Button
        delete = new JButton("Delete");
        delete.setBounds(520, 200, 120, 50);
        delete.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleButton(delete, crimsonRed, lightText, 15);
        delete.addActionListener(this);
        image.add(delete);

        // Back Button
        back = new JButton("Back");
        back.setBounds(520, 270, 120, 50);
        back.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleButton(back, royalBlue, lightText, 15);
        back.addActionListener(this);
        image.add(back);

        setSize(1000, 400);
        setLocation(300, 150);
        setLayout(null);
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

    // Helper method to style value labels (for displaying data)
    private void styleValueLabel(JLabel label, Color bgColor, Color fgColor) {
        label.setBackground(bgColor);
        label.setForeground(fgColor);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setBorder(javax.swing.BorderFactory.createCompoundBorder(
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
        if (e.getSource() == delete) {
            try {
                Connection conn = DriverManager.getConnection(url, user_name, passWord);
                Statement statement = conn.createStatement();
                String query = "delete from employee where EmployeeID = '" + choiceEMPID.getSelectedItem() + "' CASCADE";
                int rowsDeleted = statement.executeUpdate(query);

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Employee ID not found!");
                }
                setVisible(false);
                new EmployeeDashboard();

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            new EmployeeDashboard();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new DeleteEmployee();
    }
}