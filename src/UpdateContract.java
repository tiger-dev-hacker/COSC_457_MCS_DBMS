import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateContract extends JFrame implements ActionListener {


    JTextField startDateField, endDateField, budgetField, federalWageField, clientIDField, signatureDateField;
    JLabel contractIDLabel;
    JButton updateBtn, backBtn;
    String contractNumber;

    public UpdateContract(String number) {
        this.contractNumber = number;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Update Contract Details");
        getContentPane().setBackground(new Color(15, 23, 42)); // Dark background

        // Colors
        Color darkBlue = new Color(30, 58, 138);
        Color updateColor = new Color(34, 197, 94);   // Green
        Color backColor = new Color(100, 116, 139);   // Gray
        Color lightText = Color.WHITE;
        Color labelBg = new Color(51, 65, 85);        // Slate Gray
        Color fieldBg = new Color(248, 250, 252);     // Light gray
        Color fieldText = new Color(30, 30, 30);
        Color idBg = new Color(59, 130, 246);         // Blue highlight for ID

        // Title
        JLabel heading = new JLabel("Update Contract Details");
        heading.setBounds(200, 20, 600, 60);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 38));
        heading.setForeground(lightText);
        heading.setBackground(darkBlue);
        heading.setOpaque(true);
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading);

        // --- Contract ID ---
        JLabel contractIDLbl = new JLabel("Contract ID");
        contractIDLbl.setBounds(80, 120, 200, 35);
        contractIDLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(contractIDLbl, labelBg, lightText);
        add(contractIDLbl);

        contractIDLabel = new JLabel(contractNumber);
        contractIDLabel.setBounds(300, 115, 500, 45);
        contractIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        contractIDLabel.setForeground(lightText);
        contractIDLabel.setBackground(idBg);
        contractIDLabel.setOpaque(true);
        contractIDLabel.setHorizontalAlignment(JLabel.CENTER);
        contractIDLabel.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(idBg, 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(contractIDLabel);

        // --- Start Date ---
        JLabel startDateLbl = new JLabel("Start Date");
        startDateLbl.setBounds(80, 200, 200, 35);
        startDateLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(startDateLbl, labelBg, lightText);
        add(startDateLbl);

        startDateField = new JTextField();
        startDateField.setBounds(300, 195, 500, 45);
        startDateField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(startDateField, fieldBg, fieldText);
        add(startDateField);

        // --- End Date ---
        JLabel endDateLbl = new JLabel("End Date");
        endDateLbl.setBounds(80, 280, 200, 35);
        endDateLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(endDateLbl, labelBg, lightText);
        add(endDateLbl);

        endDateField = new JTextField();
        endDateField.setBounds(300, 275, 500, 45);
        endDateField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(endDateField, fieldBg, fieldText);
        add(endDateField);

        // --- Budget ---
        JLabel budgetLbl = new JLabel("Budget");
        budgetLbl.setBounds(80, 360, 200, 35);
        budgetLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(budgetLbl, labelBg, lightText);
        add(budgetLbl);

        budgetField = new JTextField();
        budgetField.setBounds(300, 355, 500, 45);
        budgetField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(budgetField, fieldBg, fieldText);
        add(budgetField);

        // --- Federal Wage ---
        JLabel federalWageLbl = new JLabel("Federal Wage");
        federalWageLbl.setBounds(80, 440, 200, 35);
        federalWageLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(federalWageLbl, labelBg, lightText);
        add(federalWageLbl);

        federalWageField = new JTextField();
        federalWageField.setBounds(300, 435, 500, 45);
        federalWageField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(federalWageField, fieldBg, fieldText);
        add(federalWageField);

        // --- Client ID ---
        JLabel clientIDLbl = new JLabel("Client ID");
        clientIDLbl.setBounds(80, 520, 200, 35);
        clientIDLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(clientIDLbl, labelBg, lightText);
        add(clientIDLbl);

        clientIDField = new JTextField();
        clientIDField.setBounds(300, 515, 500, 45);
        clientIDField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(clientIDField, fieldBg, fieldText);
        add(clientIDField);

        JLabel signatureDateLbl = new JLabel("Signature Date");
        signatureDateLbl.setBounds(80, 600, 200, 35);
        signatureDateLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        styleLabel(signatureDateLbl, labelBg, lightText);
        add(signatureDateLbl);

        signatureDateField = new JTextField();
        signatureDateField.setBounds(300, 595, 250, 45);  // Reduced width to make space for new field
        signatureDateField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(signatureDateField, fieldBg, fieldText);
        add(signatureDateField);

        // --- New Text Field Next to Signature Date ---
        JTextField additionalField = new JTextField();
        additionalField.setBounds(570, 595, 230, 45);  // Positioned to the right of signatureDateField
        additionalField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        styleTextField(additionalField, fieldBg, fieldText);
        add(additionalField);

        // Load existing contract data
        loadContractData();

        // Buttons
        updateBtn = new JButton("UPDATE CONTRACT");
        updateBtn.setBounds(300, 660, 250, 60);
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 22));
        updateBtn.addActionListener(this);
        styleButton(updateBtn, updateColor, lightText, 20);
        add(updateBtn);

        backBtn = new JButton("BACK");
        backBtn.setBounds(600, 660, 250, 60);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 22));
        backBtn.addActionListener(this);
        styleButton(backBtn, backColor, lightText, 20);
        add(backBtn);

        setSize(1000, 800);
        setLayout(null);
        setLocation(200, 50);
        setVisible(true);
    }

    private void loadContractData() {
        try {
			Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM contract WHERE ContractID='" + contractNumber + "'");

            SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat displayFormat = new SimpleDateFormat("MM/dd/yyyy");

            if (rs.next()) {
                budgetField.setText(rs.getString("Budget"));
                federalWageField.setText(rs.getString("FederalWageScale"));
                clientIDField.setText(rs.getString("ClientID"));

                // Convert dates to MM/dd/yyyy
                startDateField.setText(formatDate(rs.getString("StartDate"), mysqlFormat, displayFormat));
                endDateField.setText(formatDate(rs.getString("EndDate"), mysqlFormat, displayFormat));
                signatureDateField.setText(formatDate(rs.getString("SignatureDate"), mysqlFormat, displayFormat));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading contract data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String formatDate(String dateStr, SimpleDateFormat mysqlFormat, SimpleDateFormat displayFormat) {
        if (dateStr == null || dateStr.isEmpty()) return "";
        try {
            return displayFormat.format(mysqlFormat.parse(dateStr));
        } catch (ParseException e) {
            return dateStr; // fallback
        }
    }

    // --- Styling Helpers ---
    private void styleLabel(JLabel label, Color bg, Color fg) {
        label.setBackground(bg);
        label.setForeground(fg);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new javax.swing.border.LineBorder(bg, 2, true));
    }

    private void styleTextField(JTextField field, Color bg, Color fg) {
        field.setBackground(bg);
        field.setForeground(fg);
        field.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor, int radius) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false);

        button.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(button.getBackground());
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color original = bgColor;

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(brighten(original, 30));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(original);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(darken(original, 20));
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(brighten(original, 30));
            }
        });
    }

    private Color brighten(Color color, int amt) {
        return new Color(
                Math.min(255, color.getRed() + amt),
                Math.min(255, color.getGreen() + amt),
                Math.min(255, color.getBlue() + amt)
        );
    }

    private Color darken(Color color, int amt) {
        return new Color(
                Math.max(0, color.getRed() - amt),
                Math.max(0, color.getGreen() - amt),
                Math.max(0, color.getBlue() - amt)
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateBtn) {
            try {
                SimpleDateFormat userFormat = new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
                userFormat.setLenient(false);

                String startDate = mysqlFormat.format(userFormat.parse(startDateField.getText().trim()));
                String endDate = mysqlFormat.format(userFormat.parse(endDateField.getText().trim()));
                String signatureDate = mysqlFormat.format(userFormat.parse(signatureDateField.getText().trim()));
                String budget = budgetField.getText().trim();
                String federalWage = federalWageField.getText().trim();
                String clientID = clientIDField.getText().trim();

				Connection conn = DatabaseConnection.getConnection();
                String query = "UPDATE contract SET StartDate=?, EndDate=?, Budget=?, FederalWageScale=?, ClientID=?, SignatureDate=? WHERE ContractID=?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, startDate);
                pst.setString(2, endDate);
                pst.setString(3, budget);
                pst.setString(4, federalWage);
                pst.setString(5, clientID);
                pst.setString(6, signatureDate);
                pst.setString(7, contractNumber);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Contract updated successfully!");
                setVisible(false);
                new ViewContract();

            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Use MM/DD/YYYY.", "Date Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backBtn) {
            setVisible(false);
            new ViewContract();
        }
    }

    public static void main(String[] args) {
        new UpdateContract("");
    }
}
