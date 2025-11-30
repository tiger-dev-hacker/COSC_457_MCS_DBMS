
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AssignTools extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField contractID;
	private JButton btnNewButton;
	private JButton btnBackButton;
    JLabel tempid;
    String number;

	public AssignTools(String number) {
        this.number = number;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(15, 23, 42)); // Dark slate background
		setContentPane(contentPane);

		// Define custom colors
		Color darkBlue = new Color(30, 58, 138);
		Color mediumBlue = new Color(37, 99, 235);
		Color lightText = new Color(255, 255, 255);
		Color labelBg = new Color(51, 65, 85); // Slate gray for labels
		Color fieldBg = new Color(248, 250, 252); // Light gray for fields
		Color fieldText = new Color(30, 30, 30);
        Color emeraldGreen = new Color(16, 185, 129);

		 //Title Label with styled background
		JLabel lblNewUserRegister = new JLabel("Assign Tools");
		lblNewUserRegister.setFont(new Font("Segoe UI", Font.BOLD, 38));
		lblNewUserRegister.setBounds(250, 25, 550, 60);
		lblNewUserRegister.setForeground(lightText);
		lblNewUserRegister.setBackground(darkBlue);
		lblNewUserRegister.setOpaque(true);
		lblNewUserRegister.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblNewUserRegister);

		// Employee ID Label
		JLabel lblNewLabel = new JLabel("Tool ID");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(58, 120, 140, 35);
		styleLabel(lblNewLabel, labelBg, lightText);
		contentPane.add(lblNewLabel);
		
	    // Employee ID Value (read-only)
        tempid = new JLabel(this.number);
        tempid.setBounds(210, 120, 250, 40);  // Changed Y to 120 (same row as label)
        tempid.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tempid.setForeground(emeraldGreen);
        tempid.setBackground(new Color(30, 41, 59));
        tempid.setOpaque(true);
        tempid.setHorizontalAlignment(JLabel.CENTER);
        tempid.setBorder(new javax.swing.border.LineBorder(emeraldGreen, 2, true));
        contentPane.add(tempid);  // FIXED: Changed from add() to contentPane.add()

		// Job ID Label
		JLabel lblName = new JLabel("Contract ID");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblName.setBounds(58, 200, 140, 35);  // Changed Y to 200 (different row)
		styleLabel(lblName, labelBg, lightText);
		contentPane.add(lblName);
		
		contractID = new JTextField();
		contractID.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		contractID.setBounds(210, 200, 250, 40);  // Changed Y to 200 (same row as Job ID label)
		styleTextField(contractID, fieldBg, fieldText);
		contentPane.add(contractID);

		
		// Assign Button
		btnNewButton = new JButton("Assign Tool");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contract_id = contractID.getText().trim();
				
				// Validation
				if (contract_id.isEmpty()) {
					JOptionPane.showMessageDialog(btnNewButton,
						"Please enter a Job ID",
						"Validation Error",
						JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					String user_name = "root";
					String passWord = "Keyboard30%$";
					String url = "jdbc:mysql://localhost:3306/mcs";
					Connection conn = DriverManager.getConnection(url, user_name, passWord);

					String query = "INSERT INTO Used_in (ToolID, ContractID) " +
							"VALUES (?, ?)";

					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, number);
					pst.setString(2, contract_id);
					
					int x = pst.executeUpdate();

					if (x > 0) {
						JOptionPane.showMessageDialog(btnNewButton,
								"Tool " + number + " successfully assigned to Contract ID " + contract_id,
								"Success",
								JOptionPane.INFORMATION_MESSAGE);

						// Clear fields
						contractID.setText("");
					}

					pst.close();
					conn.close();
				} catch (Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(btnNewButton, 
						"Error: " + exception.getMessage(),
						"Database Error",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.setBounds(230, 380, 220, 70);
		styleButton(btnNewButton, new Color(34, 197, 94), lightText, 20); // Green for Register
		contentPane.add(btnNewButton);

		// Back Button
		btnBackButton = new JButton("Back to Dashboard");
		btnBackButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBackButton.setBounds(520, 380, 280, 70);
		styleButton(btnBackButton, mediumBlue, lightText, 20);
		contentPane.add(btnBackButton);
		btnBackButton.addActionListener(e -> handleBackButton());

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

	private void handleBackButton() {
		new ViewTools();
		setVisible(false);
	}
}
