import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ContractDashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel windowpane;
	private JButton addEmployeeButton;
	private JButton viewEmployeeButton;
	private JButton findContractButton;
	private JButton deleteEmployeeButton;
	private JButton backToDashboardbtn;

	public ContractDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 650);
		setResizable(false);
		setTitle("Contract Management Dashboard");

		windowpane = new JPanel();
		windowpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		windowpane.setLayout(null);
		windowpane.setBackground(new Color(15, 23, 42)); // Dark slate background
		setContentPane(windowpane);

		// Define custom colors
		Color darkBlue = new Color(30, 58, 138);
		Color addColor = new Color(59, 130, 246); // Bright blue for Add
		Color viewColor = new Color(16, 185, 129); // Emerald green for View
		Color deleteColor = new Color(239, 68, 68); // Red for Delete
		Color findColor = new Color(251, 146, 60); // Orange for Find
		Color backColor = new Color(37, 99, 235); // Medium blue for Back
		Color lightText = new Color(255, 255, 255);

		// Title Label with styled background
		JLabel lblTitle = new JLabel("Contract Management Dashboard");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 38));
		lblTitle.setBounds(150, 25, 700, 60);
		lblTitle.setForeground(lightText);
		lblTitle.setBackground(darkBlue);
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		windowpane.add(lblTitle);

		// Subtitle
		JLabel subtitleLabel = new JLabel("Manage agreements and contract documents");
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subtitleLabel.setBounds(150, 95, 700, 30);
		subtitleLabel.setForeground(new Color(203, 213, 225));
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		windowpane.add(subtitleLabel);

		// Add Contract Button
		addEmployeeButton = new JButton("Add Contract");
		addEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		addEmployeeButton.setBounds(150, 170, 320, 90);
		styleButton(addEmployeeButton, addColor, lightText, 20);
		windowpane.add(addEmployeeButton);
		addEmployeeButton.addActionListener(e -> handleAddEmployee());

		// View/Update Contract Button
		viewEmployeeButton = new JButton("View/Update Contract");
		viewEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		viewEmployeeButton.setBounds(540, 170, 320, 90);
		styleButton(viewEmployeeButton, viewColor, lightText, 20);
		windowpane.add(viewEmployeeButton);
		viewEmployeeButton.addActionListener(e -> handleViewEmployee());

		// Delete Contract Button
		deleteEmployeeButton = new JButton("Delete Contract");
		deleteEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		deleteEmployeeButton.setBounds(150, 300, 320, 90);
		styleButton(deleteEmployeeButton, deleteColor, lightText, 20);
		windowpane.add(deleteEmployeeButton);
		deleteEmployeeButton.addActionListener(e -> handleDeleteEmployee());

		// Find Contract Button
		findContractButton = new JButton("Find Contract");
		findContractButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
		findContractButton.setBounds(540, 300, 320, 90);
		styleButton(findContractButton, findColor, lightText, 20);
		windowpane.add(findContractButton);
		findContractButton.addActionListener(e -> handlefindContracts());

		// Back Button
		backToDashboardbtn = new JButton("Back to Contract/Job Dashboard");
		backToDashboardbtn.setFont(new Font("Segoe UI", Font.BOLD, 19));
		backToDashboardbtn.setBounds(220, 450, 550, 80);
		styleButton(backToDashboardbtn, backColor, lightText, 20);
		windowpane.add(backToDashboardbtn);
		backToDashboardbtn.addActionListener(e -> handleBackButton());

		setVisible(true);
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

				// Draw button background with current state
				if (button.getModel().isPressed()) {
					g2.setColor(darkenColor(button.getBackground(), 40));
				} else if (button.getModel().isRollover()) {
					g2.setColor(brightenColor(button.getBackground(), 30));
				} else {
					g2.setColor(button.getBackground());
				}

				g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), cornerRadius, cornerRadius);
				g2.dispose();
				super.paint(g, c);
			}
		});

		// Add hover effect
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			Color originalColor = bgColor;

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.repaint();
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.repaint();
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				button.repaint();
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				button.repaint();
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
		new AddContract();
		setVisible(false);
	}

	private void handleViewEmployee() {
		new ViewContract();
		setVisible(false);
	}

	private void handleDeleteEmployee() {
		new DeleteContract();
		setVisible(false);
	}

	private void handlefindContracts() {
		new FindContract();
		setVisible(false);
	}

	private void handleBackButton() {
		new ContractJobDashboard();
		setVisible(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ContractDashboard());
	}
}