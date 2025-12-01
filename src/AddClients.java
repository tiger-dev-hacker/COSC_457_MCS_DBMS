import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class AddClients extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField client_name;
	private JTextField client_bcel;
	private JButton btnNewButton;
	private JButton btnBackButton;

	public AddClients() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 650);
		setResizable(false);
		setTitle("Add New Client");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(15, 23, 42)); // Dark slate background
		setContentPane(contentPane);

		// Define custom colors
		Color darkBlue = new Color(30, 58, 138);
		Color registerColor = new Color(34, 197, 94); // Green for Register
		Color backColor = new Color(37, 99, 235); // Blue for Back
		Color lightText = new Color(255, 255, 255);
		Color labelBg = new Color(51, 65, 85); // Slate gray for labels
		Color fieldBg = new Color(248, 250, 252); // Light gray for fields
		Color fieldText = new Color(30, 30, 30);

		// Title Label with styled background
		JLabel lblNewUserRegister = new JLabel("New Client Registration");
		lblNewUserRegister.setFont(new Font("Segoe UI", Font.BOLD, 38));
		lblNewUserRegister.setBounds(250, 25, 500, 60);
		lblNewUserRegister.setForeground(lightText);
		lblNewUserRegister.setBackground(darkBlue);
		lblNewUserRegister.setOpaque(true);
		lblNewUserRegister.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblNewUserRegister);

		// Client Name Label
		JLabel lblName = new JLabel("Client Name");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblName.setBounds(100, 150, 200, 35);
		styleLabel(lblName, labelBg, lightText);
		contentPane.add(lblName);

		// Client Name TextField
		client_name = new JTextField();
		client_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		client_name.setBounds(320, 145, 500, 45);
		styleTextField(client_name, fieldBg, fieldText);
		contentPane.add(client_name);
		client_name.setColumns(10);

		// Background Check Expiry Limit Label
		JLabel lblBCEL = new JLabel("Background Check");
		lblBCEL.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblBCEL.setBounds(100, 240, 200, 35);
		styleLabel(lblBCEL, labelBg, lightText);
		contentPane.add(lblBCEL);

		JLabel lblBCEL2 = new JLabel("Expiry Limit");
		lblBCEL2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblBCEL2.setBounds(100, 270, 200, 35);
		styleLabel(lblBCEL2, labelBg, lightText);
		contentPane.add(lblBCEL2);

		// Background Check Expiry Limit TextField
		client_bcel = new JTextField();
		client_bcel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		client_bcel.setBounds(320, 245, 500, 45);
		styleTextField(client_bcel, fieldBg, fieldText);
		contentPane.add(client_bcel);
		client_bcel.setColumns(10);

		// Help text
		JLabel helpText = new JLabel("Enter the number of days for background check validity");
		helpText.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		helpText.setBounds(320, 295, 500, 25);
		helpText.setForeground(new Color(148, 163, 184));
		contentPane.add(helpText);

		// Register Button
		btnNewButton = new JButton("Register Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clientName = client_name.getText().trim();
				String clientBCEL = client_bcel.getText().trim();

				// Validation
				if (clientName.isEmpty()) {
					JOptionPane.showMessageDialog(btnNewButton, "Client name cannot be empty");
					return;
				}

				if (clientBCEL.isEmpty()) {
					JOptionPane.showMessageDialog(btnNewButton, "Background check expiry limit cannot be empty");
					return;
				}

				try {
					// Validate that BCEL is a number
					Integer.parseInt(clientBCEL);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(btnNewButton, "Background check expiry limit must be a number");
					return;
				}

				try {
					String user_name = "root";
					String passWord = "Keyboard30%$";
					String url = "jdbc:mysql://localhost:3306/mcs";
					Connection conn = DriverManager.getConnection(url, user_name, passWord);

					String query = "INSERT INTO Client (ClientName, BackgroundCheckExpiryLimit) " +
							"VALUES (?, ?)";

					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, clientName);
					pst.setString(2, clientBCEL);

					int x = pst.executeUpdate();

					if (x > 0) {
						JOptionPane.showMessageDialog(btnNewButton,
								"Client " + clientName + "\nis successfully added");

						// Clear fields after successful registration
						client_name.setText("");
						client_bcel.setText("");
					}

					conn.close();
				} catch (Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(btnNewButton,
							"Error: " + exception.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		btnNewButton.setBounds(200, 400, 280, 80);
		styleButton(btnNewButton, registerColor, lightText, 20);
		contentPane.add(btnNewButton);

		// Back Button
		btnBackButton = new JButton("Back to Client Dashboard");
		btnBackButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBackButton.setBounds(530, 400, 350, 80);
		styleButton(btnBackButton, backColor, lightText, 20);
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
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
				button.setBackground(brightenColor(originalColor, 30));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(originalColor);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				button.setBackground(darkenColor(originalColor, 20));
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				button.setBackground(brightenColor(originalColor, 30));
			}
		});
	}

	// Helper method to brighten colors
	private Color brightenColor(Color color, int amount) {
		int r = Math.min(255, color.getRed() + amount);
		int g = Math.min(255, color.getGreen() + amount);
		int b = Math.min(255, color.getBlue() + amount);
		return new Color(r, g, b);
	}

	// Helper method to darken colors
	private Color darkenColor(Color color, int amount) {
		int r = Math.max(0, color.getRed() - amount);
		int g = Math.max(0, color.getGreen() - amount);
		int b = Math.max(0, color.getBlue() - amount);
		return new Color(r, g, b);
	}

	private void handleBackButton() {
		new ClientDashboard();
		setVisible(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new AddClients());
	}
}