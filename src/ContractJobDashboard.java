import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ContractJobDashboard extends JFrame {
	private static final long serialVersionUID = 1L;

	public ContractJobDashboard() {
		setSize(1366, 565);
		setLocation(100, 100);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Contract & Job Services Dashboard");

		// Scale the image to fit the frame
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/contract_job_visit.jpg"));
		Image img = i1.getImage();
		Image scaledImg = img.getScaledInstance(1366, 565, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel image = new JLabel(scaledIcon);
		image.setBounds(0, 0, 1366, 565);

		// Enhanced title panel with better styling
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setBounds(100, 40, 1160, 100);
		titlePanel.setBackground(new Color(0, 0, 0, 160)); // Semi-transparent black
		titlePanel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(59, 130, 246), 4), // Blue border
			new EmptyBorder(10, 20, 10, 20)
		));

		JLabel text = new JLabel("Contract and Job Services");
		text.setBounds(0, 0, 1160, 100);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Segoe UI", Font.BOLD, 48));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);

		titlePanel.add(text);
		image.add(titlePanel);

		// Button colors matching your other dashboards
		Color contractColor = new Color(59, 130, 246); // Blue
		Color jobColor = new Color(16, 185, 129); // Green
		Color backColor = new Color(100, 116, 139); // Gray

		// Contract Button
		JButton btnContract = createStyledButton("Contract", contractColor);
		btnContract.setBounds(200, 350, 280, 100);
		btnContract.addActionListener(e -> handleClientClick());
		image.add(btnContract);

		// Job Button
		JButton btnJob = createStyledButton("Job", jobColor);
		btnJob.setBounds(540, 350, 280, 100);
		btnJob.addActionListener(e -> handleSiteClick());
		image.add(btnJob);

		// Back Button
		JButton btnBack = createStyledButton("Back to Business Dashboard", backColor);
		btnBack.setBounds(880, 350, 400, 100);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBack.addActionListener(e -> handleBackClick());
		image.add(btnBack);

		add(image);
		setVisible(true);
	}

	// Helper method to create styled buttons with rounded corners
	private JButton createStyledButton(String text, Color bgColor) {
		JButton button = new JButton(text);
		button.setFont(new Font("Segoe UI", Font.BOLD, 26));
		button.setForeground(Color.WHITE);
		button.setBackground(bgColor);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setOpaque(true);
		button.setContentAreaFilled(false);

		// Add border for better definition
		button.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.WHITE, 3, true),
			new EmptyBorder(10, 20, 10, 20)
		));

		// Custom UI for rounded corners
		button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
			@Override
			public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
				java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
				g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
						java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

				// Draw button background with state
				if (button.getModel().isPressed()) {
					g2.setColor(darkenColor(button.getBackground(), 40));
				} else if (button.getModel().isRollover()) {
					g2.setColor(brightenColor(button.getBackground(), 40));
				} else {
					g2.setColor(button.getBackground());
				}

				g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 25, 25);

				// Draw white border
				g2.setColor(Color.WHITE);
				g2.setStroke(new java.awt.BasicStroke(3));
				g2.drawRoundRect(2, 2, c.getWidth() - 4, c.getHeight() - 4, 25, 25);

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

		return button;
	}

	// Helper method to brighten colors on hover
	private Color brightenColor(Color color, int amount) {
		int r = Math.min(255, color.getRed() + amount);
		int g = Math.min(255, color.getGreen() + amount);
		int b = Math.min(255, color.getBlue() + amount);
		return new Color(r, g, b);
	}

	// Helper method to darken colors on press
	private Color darkenColor(Color color, int amount) {
		int r = Math.max(0, color.getRed() - amount);
		int g = Math.max(0, color.getGreen() - amount);
		int b = Math.max(0, color.getBlue() - amount);
		return new Color(r, g, b);
	}

	// Method to handle Contract button click
	private void handleClientClick() {
		new ContractDashboard();
		setVisible(false);
	}

	private void handleSiteClick() {
		new JobDashboard();
		setVisible(false);
	}

	private void handleBackClick() {
		new BusinessDashboard();
		setVisible(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ContractJobDashboard());
	}
}