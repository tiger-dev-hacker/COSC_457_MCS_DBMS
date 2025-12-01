import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ClientDashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton addEmployeeButton;
	private JButton viewEmployeeButton;
	private JButton findClientButton;
	private JButton deleteEmployeeButton;
	private JButton backToDashboardbtn;

	public ClientDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 650);
		setResizable(false);
		setTitle("Client Management Dashboard");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(15, 23, 42)); // Dark slate background matching HREmployee
		setContentPane(contentPane);

		// Define custom colors matching HREmployee style
		Color darkBlue = new Color(30, 58, 138);
		Color addColor = new Color(34, 197, 94); // Green for Add
		Color viewColor = new Color(59, 130, 246); // Blue for View
		Color deleteColor = new Color(239, 68, 68); // Red for Delete
		Color findColor = new Color(251, 146, 60); // Orange for Find
		Color backColor = new Color(37, 99, 235); // Medium Blue for Back
		Color lightText = new Color(255, 255, 255);

		// Title Label with styled background matching HREmployee
		JLabel lblTitle = new JLabel("Client Management Dashboard");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 38));
		lblTitle.setBounds(180, 25, 650, 60);
		lblTitle.setForeground(lightText);
		lblTitle.setBackground(darkBlue);
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblTitle);

		// Subtitle
		JLabel subtitleLabel = new JLabel("Manage your clients efficiently");
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subtitleLabel.setBounds(180, 95, 650, 30);
		subtitleLabel.setForeground(new Color(203, 213, 225));
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(subtitleLabel);

		// Add Client Button
		addEmployeeButton = new JButton("Add Client");
		addEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		addEmployeeButton.setBounds(150, 170, 320, 90);
		styleButton(addEmployeeButton, addColor, lightText, 20);
		contentPane.add(addEmployeeButton);
		addEmployeeButton.addActionListener(e -> handleAddEmployee());

		// View/Update Client Button
		viewEmployeeButton = new JButton("View/Update Client");
		viewEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		viewEmployeeButton.setBounds(540, 170, 320, 90);
		styleButton(viewEmployeeButton, viewColor, lightText, 20);
		contentPane.add(viewEmployeeButton);
		viewEmployeeButton.addActionListener(e -> handleViewEmployee());

		// Delete Client Button
		deleteEmployeeButton = new JButton("Delete Client");
		deleteEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		deleteEmployeeButton.setBounds(150, 300, 320, 90);
		styleButton(deleteEmployeeButton, deleteColor, lightText, 20);
		contentPane.add(deleteEmployeeButton);
		deleteEmployeeButton.addActionListener(e -> handleDeleteEmployee());

		// Find Client Button
		findClientButton = new JButton("Find Client");
		findClientButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		findClientButton.setBounds(540, 300, 320, 90);
		styleButton(findClientButton, findColor, lightText, 20);
		contentPane.add(findClientButton);
		findClientButton.addActionListener(e -> handleFindClient());

		// Back Button
		backToDashboardbtn = new JButton("Back to Client/Site Dashboard");
		backToDashboardbtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		backToDashboardbtn.setBounds(250, 450, 500, 80);
		styleButton(backToDashboardbtn, backColor, lightText, 20);
		contentPane.add(backToDashboardbtn);
		backToDashboardbtn.addActionListener(e -> handleBackButton());

		setVisible(true);
	}

	// Helper method to style buttons with rounded corners - matching HREmployee
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

		// Add hover effect matching HREmployee
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

	private void handleAddEmployee() {
		new AddClients();
		setVisible(false);
	}

	private void handleViewEmployee() {
		new ViewClient();
		setVisible(false);
	}

	private void handleFindClient() {
		new FindClient();
		setVisible(false);
	}

	private void handleDeleteEmployee() {
		new DeleteClient();
		setVisible(false);
	}

	private void handleBackButton() {
		new ClientSiteDashboard();
		setVisible(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ClientDashboard());
	}
}