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

public class HREmployee extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ssn;
	private JTextField firstname;
	private JTextField middlename;
	private JTextField lastname;
	private JTextField gender;
	private JTextField mob;
	private JTextField salary;
	private JButton btnNewButton;
	private JButton btnBackButton;

	public HREmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 650);
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

		// Title Label with styled background
		JLabel lblNewUserRegister = new JLabel("New Employee Registration");
		lblNewUserRegister.setFont(new Font("Segoe UI", Font.BOLD, 38));
		lblNewUserRegister.setBounds(250, 25, 550, 60);
		lblNewUserRegister.setForeground(lightText);
		lblNewUserRegister.setBackground(darkBlue);
		lblNewUserRegister.setOpaque(true);
		lblNewUserRegister.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblNewUserRegister);

		// SSN Label
		JLabel lblName = new JLabel("SSN");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblName.setBounds(58, 120, 120, 35);
		styleLabel(lblName, labelBg, lightText);
		contentPane.add(lblName);

		// First Name Label
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(58, 200, 120, 35);
		styleLabel(lblNewLabel, labelBg, lightText);
		contentPane.add(lblNewLabel);

		// Middle Name Label
		JLabel lblEmailAddress = new JLabel("Middle Name");
		lblEmailAddress.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblEmailAddress.setBounds(58, 280, 140, 35);
		styleLabel(lblEmailAddress, labelBg, lightText);
		contentPane.add(lblEmailAddress);

		// Salary Label
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblSalary.setBounds(58, 360, 120, 35);
		styleLabel(lblSalary, labelBg, lightText);
		contentPane.add(lblSalary);

		// Last Name Label
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblLastName.setBounds(520, 120, 120, 35);
		styleLabel(lblLastName, labelBg, lightText);
		contentPane.add(lblLastName);

		// Phone Number Label
		JLabel lblMobileNumber = new JLabel("Phone Number");
		lblMobileNumber.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblMobileNumber.setBounds(520, 200, 160, 35);
		styleLabel(lblMobileNumber, labelBg, lightText);
		contentPane.add(lblMobileNumber);

		// Gender Label
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblGender.setBounds(520, 280, 120, 35);
		styleLabel(lblGender, labelBg, lightText);
		contentPane.add(lblGender);

		// Text Fields with styling
		ssn = new JTextField();
		ssn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		ssn.setBounds(210, 120, 250, 40);
		styleTextField(ssn, fieldBg, fieldText);
		contentPane.add(ssn);

		firstname = new JTextField();
		firstname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		firstname.setBounds(210, 200, 250, 40);
		styleTextField(firstname, fieldBg, fieldText);
		contentPane.add(firstname);

		middlename = new JTextField();
		middlename.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		middlename.setBounds(210, 280, 250, 40);
		styleTextField(middlename, fieldBg, fieldText);
		contentPane.add(middlename);

		salary = new JTextField();
		salary.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		salary.setBounds(210, 360, 250, 40);
		styleTextField(salary, fieldBg, fieldText);
		contentPane.add(salary);

		lastname = new JTextField();
		lastname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lastname.setBounds(690, 120, 250, 40);
		styleTextField(lastname, fieldBg, fieldText);
		contentPane.add(lastname);

		mob = new JTextField();
		mob.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mob.setBounds(690, 200, 250, 40);
		styleTextField(mob, fieldBg, fieldText);
		contentPane.add(mob);

		gender = new JTextField();
		gender.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		gender.setBounds(690, 280, 250, 40);
		styleTextField(gender, fieldBg, fieldText);
		contentPane.add(gender);

		// Register Button
		btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Ssn = ssn.getText().trim();
				String firstName = firstname.getText().trim();
				String middleName = middlename.getText().trim();
				String lastName = lastname.getText().trim();
				String Gender = gender.getText().trim();
				String mobileNumber = mob.getText().trim();
				String salaryValue = salary.getText().trim();

				// Validate mobile number
				if (!mobileNumber.matches("\\d{10}")) {
					JOptionPane.showMessageDialog(btnNewButton, "Enter a valid 10-digit mobile number");
					return;
				}

				// Validate salary
				if (salaryValue.isEmpty()) {
					JOptionPane.showMessageDialog(btnNewButton, "Salary cannot be empty");
					return;
				}

				try {
					Double.parseDouble(salaryValue);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(btnNewButton, "Enter a valid salary amount");
					return;
				}

				try {
					Connection conn = DatabaseConnection.getConnection();

					String query = "INSERT INTO Employee (SSN, gender, Fname, Mname, Lname, phone_number, salary) " +
							"VALUES (?, ?, ?, ?, ?, ?, ?)";

					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, Ssn);
					pst.setString(2, Gender);
					pst.setString(3, firstName);
					pst.setString(4, middleName);
					pst.setString(5, lastName);
					pst.setString(6, mobileNumber);
					pst.setDouble(7, Double.parseDouble(salaryValue));

					int x = pst.executeUpdate();

					if (x > 0) {
						JOptionPane.showMessageDialog(btnNewButton,
								"Welcome, " + firstName + "\nYour account is successfully created");

						// Clear fields
						ssn.setText("");
						firstname.setText("");
						middlename.setText("");
						lastname.setText("");
						gender.setText("");
						mob.setText("");
						salary.setText("");
					}

				} catch (Exception exception) {
					exception.printStackTrace();
					JOptionPane.showMessageDialog(btnNewButton, "Error: " + exception.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.setBounds(230, 450, 220, 70);
		styleButton(btnNewButton, new Color(34, 197, 94), lightText, 20); // Green for Register
		contentPane.add(btnNewButton);

		// Back Button
		btnBackButton = new JButton("Back to Dashboard");
		btnBackButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBackButton.setBounds(520, 450, 280, 70);
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
		new EmployeeDashboard();
		setVisible(false);
	}
}