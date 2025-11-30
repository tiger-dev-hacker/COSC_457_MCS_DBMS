import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EmployeeDashboard extends JFrame {
	private JPanel windowpane;
	private JButton addEmployeeButton;
	private JButton viewEmployeeButton;
	private JButton deleteEmployeeButton;
	private JButton findEmployeeButton;
	private JButton backToDashboardbtn;
	private JButton assignEmployeeButton;

	public EmployeeDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(false);

		windowpane = new JPanel();
		windowpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		windowpane.setLayout(null);
		setContentPane(windowpane);

		// Add background image
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/employee_background.jpg"));
		Image img = i1.getImage();
		Image scaledImg = img.getScaledInstance(1014, 597, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		JLabel backgroundImage = new JLabel(scaledIcon);
		backgroundImage.setBounds(0, 0, 1014, 597);
		windowpane.add(backgroundImage);

		// Title Label
		JLabel titleLabel = new JLabel("Employee Management");
		titleLabel.setBounds(250, 20, 500, 60);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBackground(new Color(25, 42, 86)); // Dark blue background
		titleLabel.setOpaque(true); // Make background visible
		backgroundImage.add(titleLabel);

		// Define professional colors with varied shades of blue
		Color darkNavy = new Color(30, 58, 138);        // Dark navy blue for Add
		Color royalBlue = new Color(37, 99, 235);       // Royal blue for View/Update
		Color steelBlue = new Color(59, 130, 246);      // Steel blue for Back
		Color crimsonRed = new Color(185, 28, 28);      // Crimson red for Delete
		Color tealBlue = new Color(20, 184, 166);       // Teal blue for Find
		Color lightText = new Color(255, 255, 255);     // White text

		// Add Employee Button
		addEmployeeButton = new JButton("Add Employee");
		addEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		addEmployeeButton.setBounds(200, 120, 250, 74);
		styleButton(addEmployeeButton, darkNavy, lightText, 20);
		backgroundImage.add(addEmployeeButton);
		addEmployeeButton.addActionListener(e -> handleAddEmployee());

		// View/Update Employee Button
		viewEmployeeButton = new JButton("View/Update Employee");
		viewEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		viewEmployeeButton.setBounds(500, 120, 300, 74);
		styleButton(viewEmployeeButton, royalBlue, lightText, 20);
		backgroundImage.add(viewEmployeeButton);
		viewEmployeeButton.addActionListener(e -> handleViewEmployee());

		// Delete Employee Button
		deleteEmployeeButton = new JButton("Delete Employee");
		deleteEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		deleteEmployeeButton.setBounds(200, 280, 280, 74);
		styleButton(deleteEmployeeButton, crimsonRed, lightText, 20);
		backgroundImage.add(deleteEmployeeButton);
		deleteEmployeeButton.addActionListener(e -> handleDeleteEmployee());

		// Find Employee Button
		findEmployeeButton = new JButton("Find Employee");
		findEmployeeButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		findEmployeeButton.setBounds(520, 280, 280, 74);
		styleButton(findEmployeeButton, tealBlue, lightText, 20);
		backgroundImage.add(findEmployeeButton);
		findEmployeeButton.addActionListener(e -> handleFindEmployee());

		// Back to Dashboard Button
		backToDashboardbtn = new JButton("Back to Main Dashboard");
		backToDashboardbtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		backToDashboardbtn.setBounds(300, 420, 330, 64);
		styleButton(backToDashboardbtn, steelBlue, lightText, 20);
		backgroundImage.add(backToDashboardbtn);
		backToDashboardbtn.addActionListener(e -> handleBackButton());

		setVisible(true);
	}

	// Helper method to style buttons consistently with rounded corners
	private void styleButton(JButton button, Color bgColor, Color fgColor, int cornerRadius) {
		button.setBackground(bgColor);
		button.setForeground(fgColor);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setOpaque(true);
		button.setContentAreaFilled(false);
		
		// Add rounded border
		button.setBorder(new javax.swing.border.LineBorder(bgColor, 2, true));
		
		// Custom painting for rounded corners
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
		
		// Add hover effect
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

	private void handleAddEmployee() {
		new HREmployee();
		setVisible(false);
	}

	private void handleViewEmployee() {
		new ViewEmployee();
		setVisible(false);
	}

	private void handleDeleteEmployee() {
		new DeleteEmployee();
		setVisible(false);
	}
//
	private void handleFindEmployee() {
		new FindEmployee();
		setVisible(false);
	}



	private void handleBackButton() {
		new MainDashboard();
		setVisible(false);
	}
}