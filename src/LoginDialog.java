import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;

public class LoginDialog extends JDialog {
    private JTextField hostField;
    private JTextField portField;
    private JTextField databaseField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean succeeded = false;
    
    // Color scheme
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color TEXT_COLOR = new Color(44, 62, 80);
    
    public LoginDialog(Frame parent) {
        super(parent, "Database Login", true);
        
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        JLabel headerLabel = new JLabel("Database Connection");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(15, 20, 15, 20));
        headerPanel.add(headerLabel);
        
        // Form panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(8, 5, 8, 5);
        
        // Host
        cs.gridx = 0;
        cs.gridy = 0;
        cs.anchor = GridBagConstraints.WEST;
        JLabel hostLabel = createStyledLabel("Host:");
        panel.add(hostLabel, cs);
        hostField = createStyledTextField("localhost");
        cs.gridx = 1;
        cs.weightx = 1.0;
        panel.add(hostField, cs);
        
        // Port
        cs.gridx = 0;
        cs.gridy = 1;
        cs.weightx = 0;
        JLabel portLabel = createStyledLabel("Port:");
        panel.add(portLabel, cs);
        portField = createStyledTextField("3306");
        cs.gridx = 1;
        cs.weightx = 1.0;
        panel.add(portField, cs);
        
        // Database
        cs.gridx = 0;
        cs.gridy = 2;
        cs.weightx = 0;
        JLabel dbLabel = createStyledLabel("Database:");
        panel.add(dbLabel, cs);
        databaseField = createStyledTextField("myapp_db");
        cs.gridx = 1;
        cs.weightx = 1.0;
        panel.add(databaseField, cs);
        
        // Username
        cs.gridx = 0;
        cs.gridy = 3;
        cs.weightx = 0;
        JLabel userLabel = createStyledLabel("Username:");
        panel.add(userLabel, cs);
        usernameField = createStyledTextField("root");
        cs.gridx = 1;
        cs.weightx = 1.0;
        panel.add(usernameField, cs);
        
        // Password
        cs.gridx = 0;
        cs.gridy = 4;
        cs.weightx = 0;
        JLabel passLabel = createStyledLabel("Password:");
        panel.add(passLabel, cs);
        passwordField = createStyledPasswordField();
        cs.gridx = 1;
        cs.weightx = 1.0;
        panel.add(passwordField, cs);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        JButton loginButton = createStyledButton("Connect", SUCCESS_COLOR);
        loginButton.addActionListener(e -> attemptConnection());
        
        JButton cancelButton = createStyledButton("Cancel", DANGER_COLOR);
        cancelButton.addActionListener(e -> {
            succeeded = false;
            dispose();
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(loginButton);
        
        // Add Enter key support
        passwordField.addActionListener(e -> attemptConnection());
        
        // Layout
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(TEXT_COLOR);
        return label;
    }
    
    private JTextField createStyledTextField(String defaultText) {
        JTextField field = new JTextField(defaultText, 20);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBackground(Color.WHITE);
        field.setForeground(TEXT_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return field;
    }
    
    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBackground(Color.WHITE);
        field.setForeground(TEXT_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return field;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void attemptConnection() {
        String host = hostField.getText();
        String port = portField.getText();
        String database = databaseField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + 
                         "?useSSL=false&serverTimezone=UTC";
            
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Store the connection
            DatabaseConnection.setConnection(conn);
            
            succeeded = true;
            dispose();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Connection failed!\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
    
    public boolean isSucceeded() {
        return succeeded;
    }
}