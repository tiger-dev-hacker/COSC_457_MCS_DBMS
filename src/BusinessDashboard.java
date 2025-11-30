import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

public class BusinessDashboard extends JFrame {

	BusinessDashboard() {
		setSize(1366, 565);
		setLocation(100, 100);
		setLayout(null);

		// Define custom colors
		Color tealBlue = new Color(20, 184, 166);      // Teal for Client/Site
		Color purpleBlue = new Color(139, 92, 246);    // Purple for Tools/Machinery
		Color orangeAmber = new Color(251, 146, 60);   // Orange for Contract/Job
		Color slateBlue = new Color(100, 116, 139);    // Slate for Back
		Color darkTeal = new Color(13, 148, 136);      // Darker teal for title
		Color lightText = new Color(255, 255, 255);

		// Scale the image to fit the frame
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/business_dashboard.jpg"));
		Image img = i1.getImage();
		Image scaledImg = img.getScaledInstance(1366, 565, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel image = new JLabel(scaledIcon);
		image.setBounds(0, 0, 1366, 565);
		add(image);

		// Title with custom styled background
		JLabel text = new JLabel("Business Management Dashboard");
		text.setBounds(280, 40, 800, 90);
		text.setForeground(lightText);
		text.setFont(new Font("Segoe UI", Font.BOLD, 42));
		text.setBackground(darkTeal); // Dark teal background
		text.setOpaque(true);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBorder(BorderFactory.createCompoundBorder(
			new javax.swing.border.LineBorder(new Color(20, 184, 166), 3, true),
			BorderFactory.createEmptyBorder(10, 20, 10, 20)
		));
		image.add(text);

		// Client/Site Button
		JButton btnClientSites = new JButton("Client/Site");
		btnClientSites.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnClientSites.setBounds(200, 400, 180, 90);
		styleButton(btnClientSites, tealBlue, lightText, 20);
		btnClientSites.addActionListener(e -> handleClientSiteClick());
		image.add(btnClientSites);

		// Tools/Machinery Button
		JButton btnToolsMachinery = new JButton("Tools/Machinery");
		btnToolsMachinery.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnToolsMachinery.setBounds(420, 400, 220, 90);
		styleButton(btnToolsMachinery, purpleBlue, lightText, 20);
		btnToolsMachinery.addActionListener(e -> handleToolsClick());
		image.add(btnToolsMachinery);

		// Contract/Job Button
		JButton btnContractJob = new JButton("Contract/Job");
		btnContractJob.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnContractJob.setBounds(680, 400, 220, 90);
		styleButton(btnContractJob, orangeAmber, lightText, 20);
		btnContractJob.addActionListener(e -> handleContractJobClick());
		image.add(btnContractJob);

		// Back Button
		JButton btnBackButton = new JButton("Back to Main Dashboard");
		btnBackButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnBackButton.setBounds(940, 400, 300, 90);
		styleButton(btnBackButton, slateBlue, lightText, 20);
		btnBackButton.addActionListener(e -> handleBackButton());
		image.add(btnBackButton);

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

	private void handleClientSiteClick() {
		new ClientSiteDashboard();
		setVisible(false);
	}

	private void handleContractJobClick() {
		new ContractJobDashboard();
		setVisible(false);
	}

	private void handleToolsClick() {
		new ToolDashboard();
		setVisible(false);
	}

	private void handleBackButton() {
		new MainDashboard();
		setVisible(false);
	}

	public static void main(String[] args) {
		new BusinessDashboard();
	}
}