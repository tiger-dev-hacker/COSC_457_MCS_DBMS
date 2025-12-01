import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TimeSheet extends JFrame {
    private JPanel contentPane;
    private JTextField weekStartField;
    private JTextField weekEndField;
    private JTextField mondayField;
    private JTextField tuesdayField;
    private JTextField wednesdayField;
    private JTextField thursdayField;
    private JTextField fridayField;
    private JTextField totalHoursField;
    private JButton submitButton;
    private JButton clearButton;

    public TimeSheet() {
        setTitle("Employee Timesheet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 600, 610);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setBackground(new Color(240, 242, 245));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title
        JLabel titleLabel = new JLabel("Weekly Timesheet");
        titleLabel.setBounds(150, 10, 300, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(30, 58, 138));
        contentPane.add(titleLabel);

        // Timesheet Panel
        JPanel timesheetPanel = new JPanel();
        timesheetPanel.setBounds(30, 60, 520, 380);
        timesheetPanel.setBackground(Color.WHITE);
        timesheetPanel.setLayout(null);
        timesheetPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(30, 58, 138), 2),
            "Enter Timesheet Information",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), new Color(30, 58, 138)));
        contentPane.add(timesheetPanel);

        // Week Start Date
        JLabel weekStartLabel = new JLabel("Week Start Date:");
        weekStartLabel.setBounds(30, 30, 150, 25);
        weekStartLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(weekStartLabel);

        weekStartField = new JTextField();
        weekStartField.setBounds(200, 30, 280, 30);
        weekStartField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        weekStartField.setToolTipText("Format: MM/DD/YYYY");
        timesheetPanel.add(weekStartField);

        // Week End Date
        JLabel weekEndLabel = new JLabel("Week End Date:");
        weekEndLabel.setBounds(30, 70, 150, 25);
        weekEndLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(weekEndLabel);

        weekEndField = new JTextField();
        weekEndField.setBounds(200, 70, 280, 30);
        weekEndField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        weekEndField.setToolTipText("Format: MM/DD/YYYY");
        timesheetPanel.add(weekEndField);

        // Separator
        JSeparator separator = new JSeparator();
        separator.setBounds(30, 115, 460, 2);
        timesheetPanel.add(separator);

        // Monday
        JLabel mondayLabel = new JLabel("Monday Hours:");
        mondayLabel.setBounds(30, 130, 150, 25);
        mondayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(mondayLabel);

        mondayField = new JTextField();
        mondayField.setBounds(200, 130, 280, 30);
        mondayField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(mondayField);

        // Tuesday
        JLabel tuesdayLabel = new JLabel("Tuesday Hours:");
        tuesdayLabel.setBounds(30, 170, 150, 25);
        tuesdayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(tuesdayLabel);

        tuesdayField = new JTextField();
        tuesdayField.setBounds(200, 170, 280, 30);
        tuesdayField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(tuesdayField);

        // Wednesday
        JLabel wednesdayLabel = new JLabel("Wednesday Hours:");
        wednesdayLabel.setBounds(30, 210, 150, 25);
        wednesdayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(wednesdayLabel);

        wednesdayField = new JTextField();
        wednesdayField.setBounds(200, 210, 280, 30);
        wednesdayField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(wednesdayField);

        // Thursday
        JLabel thursdayLabel = new JLabel("Thursday Hours:");
        thursdayLabel.setBounds(30, 250, 150, 25);
        thursdayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(thursdayLabel);

        thursdayField = new JTextField();
        thursdayField.setBounds(200, 250, 280, 30);
        thursdayField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(thursdayField);

        // Friday
        JLabel fridayLabel = new JLabel("Friday Hours:");
        fridayLabel.setBounds(30, 290, 150, 25);
        fridayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(fridayLabel);

        fridayField = new JTextField();
        fridayField.setBounds(200, 290, 280, 30);
        fridayField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timesheetPanel.add(fridayField);

        // Total Hours (auto-calculated)
        JLabel totalLabel = new JLabel("Total Hours:");
        totalLabel.setBounds(30, 330, 150, 25);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        timesheetPanel.add(totalLabel);

        totalHoursField = new JTextField();
        totalHoursField.setBounds(200, 330, 280, 30);
        totalHoursField.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalHoursField.setEditable(false);
        totalHoursField.setBackground(new Color(230, 230, 230));
        timesheetPanel.add(totalHoursField);

        // Buttons
        submitButton = new JButton("Submit Timesheet");
        submitButton.setBounds(80, 460, 200, 40);
        styleButton(submitButton, new Color(34, 197, 94), Color.WHITE);
        contentPane.add(submitButton);
        submitButton.addActionListener(e -> submitTimesheet());

        clearButton = new JButton("Clear All");
        clearButton.setBounds(300, 460, 200, 40);
        styleButton(clearButton, new Color(239, 68, 68), Color.WHITE);
        contentPane.add(clearButton);
        clearButton.addActionListener(e -> clearFields());

        // Back Button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBounds(180, 510, 220, 40);
        styleButton(backButton, new Color(30, 58, 138), Color.WHITE);
        contentPane.add(backButton);
        backButton.addActionListener(e -> {
            new EmployeeDashboard();
            setVisible(false);
        });

        // Add listeners to auto-calculate total
        addCalculationListeners();

        setVisible(true);
    }

    private void addCalculationListeners() {
        // Add listeners to automatically calculate total when hours are entered
        mondayField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calculateTotal();
            }
        });
        tuesdayField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calculateTotal();
            }
        });
        wednesdayField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calculateTotal();
            }
        });
        thursdayField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calculateTotal();
            }
        });
        fridayField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        try {
            int monday = mondayField.getText().trim().isEmpty() ? 0 : Integer.parseInt(mondayField.getText().trim());
            int tuesday = tuesdayField.getText().trim().isEmpty() ? 0 : Integer.parseInt(tuesdayField.getText().trim());
            int wednesday = wednesdayField.getText().trim().isEmpty() ? 0 : Integer.parseInt(wednesdayField.getText().trim());
            int thursday = thursdayField.getText().trim().isEmpty() ? 0 : Integer.parseInt(thursdayField.getText().trim());
            int friday = fridayField.getText().trim().isEmpty() ? 0 : Integer.parseInt(fridayField.getText().trim());

            int total = monday + tuesday + wednesday + thursday + friday;
            totalHoursField.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            totalHoursField.setText("");
        }
    }

    private void submitTimesheet() {
        // Validate all fields
        if (!validateFields()) {
            return;
        }

        // If validation passes, show success message
        JOptionPane.showMessageDialog(this,
            "Timesheet submitted successfully!\n\n" +
            "Week: " + weekStartField.getText() + " to " + weekEndField.getText() + "\n" +
            "Total Hours: " + totalHoursField.getText(),
            "Success",
            JOptionPane.INFORMATION_MESSAGE);

        // Optionally clear fields after successful submission
        clearFields();
    }

    private boolean validateFields() {
        // Check if any field is empty
        if (weekStartField.getText().trim().isEmpty()) {
            showError("Week Start Date cannot be empty!");
            weekStartField.requestFocus();
            return false;
        }

        if (weekEndField.getText().trim().isEmpty()) {
            showError("Week End Date cannot be empty!");
            weekEndField.requestFocus();
            return false;
        }

        if (mondayField.getText().trim().isEmpty()) {
            showError("Monday Hours cannot be empty!");
            mondayField.requestFocus();
            return false;
        }

        if (tuesdayField.getText().trim().isEmpty()) {
            showError("Tuesday Hours cannot be empty!");
            tuesdayField.requestFocus();
            return false;
        }

        if (wednesdayField.getText().trim().isEmpty()) {
            showError("Wednesday Hours cannot be empty!");
            wednesdayField.requestFocus();
            return false;
        }

        if (thursdayField.getText().trim().isEmpty()) {
            showError("Thursday Hours cannot be empty!");
            thursdayField.requestFocus();
            return false;
        }

        if (fridayField.getText().trim().isEmpty()) {
            showError("Friday Hours cannot be empty!");
            fridayField.requestFocus();
            return false;
        }

        // Validate date format for Week Start Date
        if (!isValidDate(weekStartField.getText().trim())) {
            showError("Week Start Date must be in MM/DD/YYYY format!");
            weekStartField.requestFocus();
            return false;
        }

        // Validate date format for Week End Date
        if (!isValidDate(weekEndField.getText().trim())) {
            showError("Week End Date must be in MM/DD/YYYY format!");
            weekEndField.requestFocus();
            return false;
        }

        // Validate that hours fields are integers
        if (!isValidInteger(mondayField.getText().trim())) {
            showError("Monday Hours must be a valid integer!");
            mondayField.requestFocus();
            return false;
        }

        if (!isValidInteger(tuesdayField.getText().trim())) {
            showError("Tuesday Hours must be a valid integer!");
            tuesdayField.requestFocus();
            return false;
        }

        if (!isValidInteger(wednesdayField.getText().trim())) {
            showError("Wednesday Hours must be a valid integer!");
            wednesdayField.requestFocus();
            return false;
        }

        if (!isValidInteger(thursdayField.getText().trim())) {
            showError("Thursday Hours must be a valid integer!");
            thursdayField.requestFocus();
            return false;
        }

        if (!isValidInteger(fridayField.getText().trim())) {
            showError("Friday Hours must be a valid integer!");
            fridayField.requestFocus();
            return false;
        }

        // Validate that hours are non-negative
        if (Integer.parseInt(mondayField.getText().trim()) < 0 ||
            Integer.parseInt(tuesdayField.getText().trim()) < 0 ||
            Integer.parseInt(wednesdayField.getText().trim()) < 0 ||
            Integer.parseInt(thursdayField.getText().trim()) < 0 ||
            Integer.parseInt(fridayField.getText().trim()) < 0) {
            showError("Hours cannot be negative!");
            return false;
        }

        // Validate that hours are reasonable (0-24 per day)
        if (Integer.parseInt(mondayField.getText().trim()) > 24 ||
            Integer.parseInt(tuesdayField.getText().trim()) > 24 ||
            Integer.parseInt(wednesdayField.getText().trim()) > 24 ||
            Integer.parseInt(thursdayField.getText().trim()) > 24 ||
            Integer.parseInt(fridayField.getText().trim()) > 24) {
            showError("Daily hours cannot exceed 24!");
            return false;
        }

        return true;
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Validation Error",
            JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        weekStartField.setText("");
        weekEndField.setText("");
        mondayField.setText("");
        tuesdayField.setText("");
        wednesdayField.setText("");
        thursdayField.setText("");
        fridayField.setText("");
        totalHoursField.setText("");
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
        SwingUtilities.invokeLater(() -> new TimeSheet());
    }
}