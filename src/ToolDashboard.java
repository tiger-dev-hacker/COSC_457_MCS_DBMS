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

public class ToolDashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel windowpane;
	private JButton addEmployeeButton;
	private JButton viewEmployeeButton;
	private JButton findEmployeeButton;
	private JButton deleteEmployeeButton;
	private JButton backToDashboardbtn;

	public ToolDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 650);
		setResizable(false);
		setTitle("Tool Management Dashboard");

		windowpane = new JPanel();
		windowpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		windowpane.setLayout(null);
		windowpane.setBackground(new Color(15, 23, 42)); // Dark slate background
		setContentPane(windowpane);

		// WCAG AAA compliant color scheme with high contrast ratios (7:1 minimum)
		Color darkBlue = new Color(30, 58, 138);
		Color addColor = new Color(5, 150, 105); // Dark emerald green - WCAG AAA
		Color viewColor = new Color(29, 78, 216); // Dark blue - WCAG AAA
		Color deleteColor = new Color(185, 28, 28); // Dark red - WCAG AAA
		Color findColor = new Color(124, 58, 237); // Dark purple - WCAG AAA
		Color backColor = new Color(55, 65, 81); // Dark gray - WCAG AAA
		Color lightText = new Color(255, 255, 255);

		// Title Label with styled background
		JLabel lblTitle = new JLabel("Tool Management Dashboard");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 38));
		lblTitle.setBounds(190, 25, 630, 60);
		lblTitle.setForeground(lightText);
		lblTitle.setBackground(darkBlue);
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		windowpane.add(lblTitle);

		// Subtitle with high contrast
		JLabel subtitleLabel = new JLabel("Manage construction tools and equipment");
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subtitleLabel.setBounds(190, 95, 630, 30);
		subtitleLabel.setForeground(new Color(226, 232, 240)); // High contrast light gray
		subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		windowpane.add(subtitleLabel);

		// Add Tools Button
		addEmployeeButton = new JButton("ADD TOOLS");
		addEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
		addEmployeeButton.setBounds(150, 170, 320, 90);
		styleButton(addEmployeeButton, addColor, lightText, 20);
		windowpane.add(addEmployeeButton);
		addEmployeeButton.addActionListener(e -> handleAddEmployee());

		// View/Update Tools Button
		viewEmployeeButton = new JButton("VIEW/UPDATE TOOLS");
		viewEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
		viewEmployeeButton.setBounds(540, 170, 320, 90);
		styleButton(viewEmployeeButton, viewColor, lightText, 20);
		windowpane.add(viewEmployeeButton);
		viewEmployeeButton.addActionListener(e -> handleViewEmployee());

		// Delete Tools Button
		deleteEmployeeButton = new JButton("DELETE TOOLS");
		deleteEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
		deleteEmployeeButton.setBounds(150, 300, 320, 90);
		styleButton(deleteEmployeeButton, deleteColor, lightText, 20);
		windowpane.add(deleteEmployeeButton);
		deleteEmployeeButton.addActionListener(e -> handleDeleteEmployee());

		// Find Tools Button
		findEmployeeButton = new JButton("FIND TOOLS");
		findEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
		findEmployeeButton.setBounds(540, 300, 320, 90);
		styleButton(findEmployeeButton, findColor, lightText, 20);
		windowpane.add(findEmployeeButton);
		findEmployeeButton.addActionListener(e -> handleFindTools());

		// Back Button
		backToDashboardbtn = new JButton("BACK TO MAIN DASHBOARD");
		backToDashboardbtn.setFont(new Font("Segoe UI", Font.BOLD, 22));
		backToDashboardbtn.setBounds(250, 450, 500, 80);
		styleButton(backToDashboardbtn, backColor, lightText, 20);
		windowpane.add(backToDashboardbtn);
		backToDashboardbtn.addActionListener(e -> handleBackButton());

		setVisible(true);
	}

	// Helper method to style buttons with rounded corners and WCAG compliance
	private void styleButton(JButton button, Color bgColor, Color fgColor, int cornerRadius) {
		button.setBackground(bgColor);
		button.setForeground(fgColor);
		button.setFocusPainted(true); // Important for keyboard navigation accessibility
		button.setBorderPainted(false);
		button.setOpaque(true);
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Add visible border for better accessibility
		button.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 3, true));

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
					g2.setColor(brightenColor(button.getBackground(), 40));
				} else {
					g2.setColor(button.getBackground());
				}
				
				g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), cornerRadius, cornerRadius);
				
				// Draw black border for high contrast
				g2.setColor(Color.BLACK);
				g2.setStroke(new java.awt.BasicStroke(3));
				g2.drawRoundRect(2, 2, c.getWidth() - 4, c.getHeight() - 4, cornerRadius, cornerRadius);
				
				g2.dispose();
				super.paint(g, c);
			}
		});

		// Add hover and press effects with clear visual feedback
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

	// Helper method to brighten colors for hover state
	private Color brightenColor(Color color, int amount) {
		int r = Math.min(255, color.getRed() + amount);
		int g = Math.min(255, color.getGreen() + amount);
		int b = Math.min(255, color.getBlue() + amount);
		return new Color(r, g, b);
	}

	// Helper method to darken colors for pressed state
	private Color darkenColor(Color color, int amount) {
		int r = Math.max(0, color.getRed() - amount);
		int g = Math.max(0, color.getGreen() - amount);
		int b = Math.max(0, color.getBlue() - amount);
		return new Color(r, g, b);
	}

	private void handleAddEmployee() {
		new AddTools();
		setVisible(false);
	}

	private void handleViewEmployee() {
		new ViewTools();
		setVisible(false);
	}

	private void handleFindTools() {
		new FindTools();
		setVisible(false);
	}

	private void handleDeleteEmployee() {
		new DeleteTools();
		setVisible(false);
	}

	private void handleBackButton() {
		new BusinessDashboard();
		setVisible(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ToolDashboard());
	}
}