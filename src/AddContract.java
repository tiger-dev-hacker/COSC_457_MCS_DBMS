import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddContract extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField budgetField;
    private JTextField fwsField;
    private JTextField clientIDField;
    private JTextField signatureDateField;
    private JButton btnRegister;
    private JButton btnBack;

    public AddContract() {
    	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    setBounds(400, 150, 1200, 700); // Wider window for extra space
    	    setResizable(false);

    	    contentPane = new JPanel();
    	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	    contentPane.setLayout(null);
    	    contentPane.setBackground(new Color(15, 23, 42));
    	    setContentPane(contentPane);

    	    // Colors
    	    Color darkBlue = new Color(30, 58, 138);
    	    Color mediumBlue = new Color(37, 99, 235);
    	    Color lightText = new Color(255, 255, 255);
    	    Color labelBg = new Color(51, 65, 85);
    	    Color fieldBg = new Color(248, 250, 252);
    	    Color fieldText = new Color(30, 30, 30);
    	    Color spaceBg = new Color(30, 58, 138, 50); // Semi-transparent for space

    	    // Title
    	    JLabel lblTitle = new JLabel("New Contract Registration");
    	    lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
    	    lblTitle.setBounds(300, 20, 600, 50);
    	    lblTitle.setForeground(lightText);
    	    lblTitle.setBackground(darkBlue);
    	    lblTitle.setOpaque(true);
    	    lblTitle.setHorizontalAlignment(JLabel.CENTER);
    	    contentPane.add(lblTitle);

    	    // *** PERFECT LAYOUT WITH EXTRA SPACE ***
    	    int leftColumnX = 100;           // Left column
    	    int rightColumnX = 580;          // Right column (creates 80px gap)
    	    int spaceStartX = 1020;          // Empty space starts here
    	    int labelHeight = 45;
    	    int fieldHeight = 50;
    	    int fieldGap = 25;
    	    int topY = 110;
    	    int yGap = 75;

    	    // LEFT COLUMN LABEL WIDTHS (shorter labels)
    	    int leftLabelWidth = 140;
    	    
    	    // RIGHT COLUMN LABEL WIDTHS (wider for Federal Wage Scale)
    	    int rightLabelWidth = 200;       // Wide enough for "Federal Wage Scale"

    	    // FIELD WIDTH (consistent)
    	    int fieldWidth = 320;

    	    // LEFT COLUMN
    	    // Start Date
    	    JLabel lblStartDate = new JLabel("Start Date");
    	    styleLabel1(lblStartDate, labelBg, lightText, leftColumnX, topY, leftLabelWidth, labelHeight);
    	    contentPane.add(lblStartDate);
    	    
    	    startDateField = new JTextField();
    	    styleTextField1(startDateField, fieldBg, fieldText, 
    	                   leftColumnX + leftLabelWidth + fieldGap, topY - 2, 
    	                   fieldWidth, fieldHeight);
    	    contentPane.add(startDateField);
    	    
    	    JLabel lblStartFormat = new JLabel("(MM/DD/YYYY)");
    	    lblStartFormat.setBounds(leftColumnX + leftLabelWidth + fieldGap + 10, 
    	                            topY + labelHeight + 5, 120, 20);
    	    lblStartFormat.setForeground(new Color(148, 163, 184));
    	    lblStartFormat.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	    contentPane.add(lblStartFormat);

    	    // End Date
    	    JLabel lblEndDate = new JLabel("End Date");
    	    styleLabel1(lblEndDate, labelBg, lightText, leftColumnX, topY + yGap, leftLabelWidth, labelHeight);
    	    contentPane.add(lblEndDate);
    	    
    	    endDateField = new JTextField();
    	    styleTextField1(endDateField, fieldBg, fieldText, 
    	                   leftColumnX + leftLabelWidth + fieldGap, topY + yGap - 2, 
    	                   fieldWidth, fieldHeight);
    	    contentPane.add(endDateField);

    	    // Budget
    	    JLabel lblBudget = new JLabel("Budget");
    	    styleLabel1(lblBudget, labelBg, lightText, leftColumnX, topY + 2 * yGap, leftLabelWidth, labelHeight);
    	    contentPane.add(lblBudget);
    	    
    	    budgetField = new JTextField();
    	    styleTextField1(budgetField, fieldBg, fieldText, 
    	                   leftColumnX + leftLabelWidth + fieldGap, topY + 2 * yGap - 2, 
    	                   fieldWidth, fieldHeight);
    	    contentPane.add(budgetField);

    	    // RIGHT COLUMN - WIDER LABELS
    	    // Federal Wage Scale (FULL TEXT)
    	    JLabel lblFWS = new JLabel("Federal Wage Scale");
    	    styleLabel1(lblFWS, labelBg, lightText, rightColumnX, topY, rightLabelWidth, labelHeight);
    	    contentPane.add(lblFWS);
    	    
    	    fwsField = new JTextField();
    	    styleTextField1(fwsField, fieldBg, fieldText, 
    	                   rightColumnX + rightLabelWidth + fieldGap, topY - 2, 
    	                   fieldWidth, fieldHeight);
    	    contentPane.add(fwsField);

    	    // Client ID
    	    JLabel lblClientID = new JLabel("Client ID");
    	    styleLabel1(lblClientID, labelBg, lightText, rightColumnX, topY + yGap, rightLabelWidth, labelHeight);
    	    contentPane.add(lblClientID);
    	    
    	    clientIDField = new JTextField();
    	    styleTextField1(clientIDField, fieldBg, fieldText, 
    	                   rightColumnX + rightLabelWidth + fieldGap, topY + yGap - 2, 
    	                   fieldWidth, fieldHeight);
    	    contentPane.add(clientIDField);

    	    // Signature Date
    	    JLabel lblSignatureDate = new JLabel("Signature Date");
    	    styleLabel1(lblSignatureDate, labelBg, lightText, rightColumnX, topY + 2 * yGap, rightLabelWidth, labelHeight);
    	    contentPane.add(lblSignatureDate);
    	    
    	    signatureDateField = new JTextField();
    	    styleTextField1(signatureDateField, fieldBg, fieldText, 
    	                   rightColumnX + rightLabelWidth + fieldGap, topY + 2 * yGap - 2, 
    	                   fieldWidth, fieldHeight);
    	    contentPane.add(signatureDateField);

    	    // *** BACKGROUND SPACE VISUALIZATION ***
    	    // Add subtle background panel for empty space
    	    JPanel spacePanel = new JPanel();
    	    spacePanel.setBounds(spaceStartX - 50, topY - 20, 200, 3 * yGap + 100);
    	    spacePanel.setBackground(spaceBg);
    	    spacePanel.setOpaque(true);
    	    spacePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    	    contentPane.add(spacePanel);

    	    // Add decorative line
    	    JLabel spaceLine = new JLabel("══════════════════════════════════════════");
    	    spaceLine.setBounds(spaceStartX, topY - 10, 150, 30);
    	    spaceLine.setForeground(new Color(148, 163, 184, 100));
    	    spaceLine.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    	    contentPane.add(spaceLine);

    	    // BUTTONS - Centered between columns
    	    int buttonY = topY + 3 * yGap + 30;
    	    int buttonCenterX = (leftColumnX + rightColumnX + rightLabelWidth + fieldWidth + fieldGap) / 2 - 240;
    	    
    	    btnRegister = new JButton("Register Contract");
    	    btnRegister.setBounds(buttonCenterX - 280, buttonY, 260, 60);
    	    styleButton(btnRegister, new Color(34, 197, 94), lightText, 15);
    	    contentPane.add(btnRegister);
    	    btnRegister.addActionListener(e -> registerContract());

    	    btnBack = new JButton("Back to Dashboard");
    	    btnBack.setBounds(buttonCenterX + 20, buttonY, 260, 60);
    	    styleButton(btnBack, mediumBlue, lightText, 15);
    	    contentPane.add(btnBack);
    	    btnBack.addActionListener(e -> handleBackButton());

    	    setVisible(true);
    }
    private void styleLabel1(JLabel label, Color bgColor, Color fgColor, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        label.setBackground(bgColor);
        label.setForeground(fgColor);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(new Color(71, 85, 105), 1, true));
        label.setFont(new Font("Segoe UI", Font.BOLD, 13)); // Slightly smaller for long text
        label.setVerticalAlignment(JLabel.CENTER);
    }
    private void styleTextField1(JTextField field, Color bgColor, Color fgColor, int x, int y, int width, int height) {
        field.setBounds(x, y, width, height);
        field.setBackground(bgColor);
        field.setForeground(fgColor);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(37, 99, 235), 2, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15) // More padding
        ));
    }
    private void registerContract() {
        String startDateInput = startDateField.getText().trim();
        String endDateInput = endDateField.getText().trim();
        String budget = budgetField.getText().trim();
        String federalWageScale = fwsField.getText().trim();
        String clientID = clientIDField.getText().trim();
        String signatureDateInput = signatureDateField.getText().trim();

		Connection conn = DatabaseConnection.getConnection();

        try {
            SimpleDateFormat userFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
            userFormat.setLenient(false);

            String startDate = mysqlFormat.format(userFormat.parse(startDateInput));
            String endDate = mysqlFormat.format(userFormat.parse(endDateInput));
            String signatureDate = mysqlFormat.format(userFormat.parse(signatureDateInput));


            String query = "INSERT INTO Contract (StartDate, EndDate, Budget, FederalWageScale, ClientID, SignatureDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, startDate);
            pst.setString(2, endDate);
            pst.setString(3, budget);
            pst.setString(4, federalWageScale);
            pst.setString(5, clientID);
            pst.setString(6, signatureDate);

            int x = pst.executeUpdate();

            if (x > 0) {
                JOptionPane.showMessageDialog(btnRegister, "Contract successfully created");
                startDateField.setText("");
                endDateField.setText("");
                budgetField.setText("");
                fwsField.setText("");
                clientIDField.setText("");
                signatureDateField.setText("");
            }
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(btnRegister,
                    "Invalid date format. Please use MM/DD/YYYY",
                    "Date Format Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(btnRegister, "Error: " + exception.getMessage());
        }
    }

    private void handleBackButton() {
        new ContractDashboard();
        setVisible(false);
    }

    private void styleLabel(JLabel label, Color bgColor, Color fgColor, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        label.setBackground(bgColor);
        label.setForeground(fgColor);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(bgColor, 2, true));
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
    }

    private void styleTextField(JTextField field, Color bgColor, Color fgColor, int x, int y, int width, int height) {
        field.setBounds(x, y, width, height);
        field.setBackground(bgColor);
        field.setForeground(fgColor);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(37, 99, 235), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor, int cornerRadius) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(false);

        button.setBorder(BorderFactory.createLineBorder(bgColor, 2, true));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(button.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), cornerRadius, cornerRadius);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color originalColor = bgColor;
            public void mouseEntered(java.awt.event.MouseEvent evt) { button.setBackground(bgColor.brighter()); }
            public void mouseExited(java.awt.event.MouseEvent evt) { button.setBackground(originalColor); }
        });
    }

    public static void main(String[] args) {
        new AddContract();
    }
}
