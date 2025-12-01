import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ClientSiteDashboard extends JFrame {

	public ClientSiteDashboard() {
		setSize(1366, 565);
		setLocation(100, 100);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Scale the image to fit the frame
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/construction-site-client-visit.jpg"));
		Image img = i1.getImage();
		Image scaledImg = img.getScaledInstance(1366, 565, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel image = new JLabel(scaledIcon);
		image.setBounds(0, 0, 1366, 565);

		// Enhanced title with gradient-like effect using layered panels
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setBounds(80, 40, 1200, 100);
		titlePanel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent black
		titlePanel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(245, 61, 176), 3),
			new EmptyBorder(10, 20, 10, 20)
		));

		JLabel text = new JLabel("Dashboard for Clients and Sites");
		text.setBounds(0, 0, 1200, 100);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Arial", Font.BOLD, 46));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		
		titlePanel.add(text);
		image.add(titlePanel);

		// Button styling colors
		Color clientColor = new Color(52, 152, 219); // Modern blue
		Color siteColor = new Color(46, 204, 113); // Modern green
		Color backColor = new Color(231, 76, 60); // Modern red
		
		// Client Button
		JButton btnClient = createStyledButton("Client", clientColor);
		btnClient.setBounds(250, 380, 250, 100);
		btnClient.addActionListener(e -> handleClientClick());
		image.add(btnClient);

		// Site Button
		JButton btnSite = createStyledButton("Site", siteColor);
		btnSite.setBounds(558, 380, 250, 100);
		btnSite.addActionListener(e -> handleSiteClick());
		image.add(btnSite);

		// Back Button
		JButton btnBack = createStyledButton("Back to Business Dashboard", backColor);
		btnBack.setBounds(866, 380, 400, 100);
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.addActionListener(e -> handleBackClick());
		image.add(btnBack);

		add(image);
		setVisible(true);
	}

	// Helper method to create styled buttons
	private JButton createStyledButton(String text, Color bgColor) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 24));
		button.setForeground(Color.WHITE);
		button.setBackground(bgColor);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.WHITE, 2),
			new EmptyBorder(10, 20, 10, 20)
		));
		
		// Add hover effect
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			Color originalColor = bgColor;
			
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(brightenColor(originalColor));
				button.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.YELLOW, 3),
					new EmptyBorder(10, 20, 10, 20)
				));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(originalColor);
				button.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.WHITE, 2),
					new EmptyBorder(10, 20, 10, 20)
				));
			}
		});
		
		return button;
	}

	// Helper method to brighten colors on hover
	private Color brightenColor(Color color) {
		int r = Math.min(255, color.getRed() + 40);
		int g = Math.min(255, color.getGreen() + 40);
		int b = Math.min(255, color.getBlue() + 40);
		return new Color(r, g, b);
	}

	// Method to handle Client button click
	private void handleClientClick() {
		new ClientDashboard();
		setVisible(false);
	}

	private void handleSiteClick() {
		new SiteDashboard();
		setVisible(false);
	}

	private void handleBackClick() {
		new BusinessDashboard();
		setVisible(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ClientSiteDashboard());
	}
}