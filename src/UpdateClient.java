import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateClient extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;


	JTextField client_name, client_bcel;
	JLabel tempid;
	JButton add, back;
	String number;

	public UpdateClient(String number) {
		this.number = number;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Update Client Details");

		getContentPane().setBackground(new Color(15, 23, 42)); // Dark slate background

		// Define custom colors
		Color darkBlue = new Color(30, 58, 138);
		Color updateColor = new Color(34, 197, 94); // Green for Update
		Color backColor = new Color(100, 116, 139); // Gray for Back
		Color lightText = new Color(255, 255, 255);
		Color labelBg = new Color(51, 65, 85); // Slate gray for labels
		Color fieldBg = new Color(248, 250, 252); // Light gray for fields
		Color fieldText = new Color(30, 30, 30);
		Color idBg = new Color(59, 130, 246); // Blue for ID display

		// Title Label
		JLabel heading = new JLabel("Update Client Details");
		heading.setBounds(250, 25, 500, 60);
		heading.setFont(new Font("Segoe UI", Font.BOLD, 38));
		heading.setForeground(lightText);
		heading.setBackground(darkBlue);
		heading.setOpaque(true);
		heading.setHorizontalAlignment(JLabel.CENTER);
		add(heading);

		// Client ID Label
		JLabel client_id = new JLabel("Client ID");
		client_id.setBounds(100, 130, 200, 35);
		client_id.setFont(new Font("Segoe UI", Font.BOLD, 18));
		styleLabel(client_id, labelBg, lightText);
		add(client_id);

		// Client ID Value (non-editable)
		tempid = new JLabel(this.number);
		tempid.setBounds(320, 125, 500, 45);
		tempid.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tempid.setForeground(lightText);
		tempid.setBackground(idBg);
		tempid.setOpaque(true);
		tempid.setHorizontalAlignment(JLabel.CENTER);
		tempid.setBorder(BorderFactory.createCompoundBorder(
			new javax.swing.border.LineBorder(idBg, 2, true),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		add(tempid);

		// Client Name Label
		JLabel clientName = new JLabel("Client Name");
		clientName.setBounds(100, 210, 200, 35);
		clientName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		styleLabel(clientName, labelBg, lightText);
		add(clientName);

		// Client Name TextField
		client_name = new JTextField();
		client_name.setBounds(320, 205, 500, 45);
		client_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		styleTextField(client_name, fieldBg, fieldText);
		add(client_name);

		// Background Check Expiry Limit Label (split into two lines)
		JLabel clientBCEL1 = new JLabel("Background Check");
		clientBCEL1.setBounds(100, 290, 200, 35);
		clientBCEL1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		styleLabel(clientBCEL1, labelBg, lightText);
		add(clientBCEL1);

		JLabel clientBCEL2 = new JLabel("Expiry Limit");
		clientBCEL2.setBounds(100, 320, 200, 35);
		clientBCEL2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		styleLabel(clientBCEL2, labelBg, lightText);
		add(clientBCEL2);

		// Background Check Expiry Limit TextField
		client_bcel = new JTextField();
		client_bcel.setBounds(320, 295, 500, 45);
		client_bcel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		styleTextField(client_bcel, fieldBg, fieldText);
		add(client_bcel);

		// Help text
		JLabel helpText = new JLabel("Enter the number of days for background check validity");
		helpText.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		helpText.setBounds(320, 345, 500, 25);
		helpText.setForeground(new Color(148, 163, 184));
		add(helpText);

		// Load existing data
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement statement = conn.createStatement();
			String query = "select * from client where ClientID = '" + number + "'";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				client_name.setText(resultSet.getString("ClientName"));
				client_bcel.setText(resultSet.getString("BackgroundCheckExpiryLimit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error loading client data: " + e.getMessage(),
				"Database Error", JOptionPane.ERROR_MESSAGE);
		}

		// Update Button
		add = new JButton("UPDATE CLIENT");
		add.setBounds(300, 450, 250, 80);
		add.setFont(new Font("Segoe UI", Font.BOLD, 22));
		add.addActionListener(this);
		styleButton(add, updateColor, lightText, 20);
		add(add);

		// Back Button
		back = new JButton("BACK");
		back.setBounds(600, 450, 250, 80);
		back.setFont(new Font("Segoe UI", Font.BOLD, 22));
		back.addActionListener(this);
		styleButton(back, backColor, lightText, 20);
		add(back);

		// Info panel at bottom
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setBounds(100, 560, 720, 60);
		infoPanel.setBackground(new Color(30, 41, 59));
		infoPanel.setBorder(BorderFactory.createLineBorder(new Color(71, 85, 105), 2, true));

		JLabel infoLabel = new JLabel("💡 Tip: Make sure to verify all changes before updating");
		infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		infoLabel.setForeground(new Color(226, 232, 240));
		infoLabel.setBounds(20, 15, 680, 30);
		infoPanel.add(infoLabel);
		add(infoPanel);

		setSize(1000, 700);
		setLayout(null);
		setLocation(250, 50);
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
		field.setBorder(BorderFactory.createCompoundBorder(
			new javax.swing.border.LineBorder(new Color(59, 130, 246), 2, true),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			String Cname = client_name.getText().trim();
			String Cbcel = client_bcel.getText().trim();

			// Validation
			if (Cname.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Client name cannot be empty",
					"Validation Error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (Cbcel.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Background check expiry limit cannot be empty",
					"Validation Error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			try {
				Integer.parseInt(Cbcel);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Background check expiry limit must be a number",
					"Validation Error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			try {
				Connection conn = DatabaseConnection.getConnection();
				Statement statement = conn.createStatement();
				String query = "update client set ClientName = '" + Cname + 
					"', BackgroundCheckExpiryLimit = '" + Cbcel + 
					"' where ClientID = '" + number + "'";
				statement.executeUpdate(query);
				JOptionPane.showMessageDialog(this, "Client details updated successfully!",
					"Success", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				new ViewClient();
			} catch (Exception E) {
				E.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error updating client: " + E.getMessage(),
					"Database Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			setVisible(false);
			new ViewClient();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new UpdateClient(""));
	}
}