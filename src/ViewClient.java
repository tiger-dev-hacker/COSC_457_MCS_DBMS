import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ViewClient extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	String user_name = "root";
	String passWord = "Keyboard30%$";
	String url = "jdbc:mysql://localhost:3306/mcs";
	JTable table;
	Choice choiceEMP;
	JButton searchbtn, view_all, update, back;

	public ViewClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("View Client Information");

		// Dark slate background
		getContentPane().setBackground(new Color(15, 23, 42));

		// Define custom colors
		Color darkBlue = new Color(30, 58, 138);
		Color searchColor = new Color(251, 146, 60); // Orange
		Color viewAllColor = new Color(59, 130, 246); // Blue
		Color updateColor = new Color(34, 197, 94); // Green
		Color backColor = new Color(100, 116, 139); // Gray
		Color lightText = new Color(255, 255, 255);
		Color labelBg = new Color(51, 65, 85);

		// Title Label
		JLabel titleLabel = new JLabel("Client Information Viewer");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
		titleLabel.setBounds(250, 15, 450, 50);
		titleLabel.setForeground(lightText);
		titleLabel.setBackground(darkBlue);
		titleLabel.setOpaque(true);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);

		// Search Label
		JLabel search = new JLabel("Search by Client ID");
		search.setFont(new Font("Segoe UI", Font.BOLD, 16));
		search.setBounds(50, 90, 180, 30);
		search.setForeground(lightText);
		search.setBackground(labelBg);
		search.setOpaque(true);
		search.setHorizontalAlignment(JLabel.CENTER);
		search.setBorder(BorderFactory.createLineBorder(labelBg, 2, true));
		add(search);

		// Choice dropdown
		choiceEMP = new Choice();
		choiceEMP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		choiceEMP.setBounds(250, 90, 200, 30);
		choiceEMP.setBackground(new Color(248, 250, 252));
		choiceEMP.setForeground(new Color(30, 30, 30));
		add(choiceEMP);

		try {
			Connection conn = DriverManager.getConnection(url, user_name, passWord);
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from client");
			while (resultSet.next()) {
				choiceEMP.add(resultSet.getString("ClientID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Styled table with scroll pane
		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setGridColor(new Color(203, 213, 225));
		table.setBackground(Color.WHITE);
		table.setForeground(new Color(30, 30, 30));
		table.setSelectionBackground(new Color(59, 130, 246));
		table.setSelectionForeground(Color.WHITE);

		// Style table header
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 16));
		header.setBackground(new Color(30, 58, 138));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(header.getWidth(), 40));

		// Center align table cells
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane jp = new JScrollPane(table);
		jp.setBounds(30, 200, 930, 420);
		jp.setBorder(BorderFactory.createLineBorder(new Color(59, 130, 246), 2));
		add(jp);

		// Search Button
		searchbtn = new JButton("Search");
		searchbtn.setBounds(50, 145, 150, 45);
		searchbtn.addActionListener(this);
		styleButton(searchbtn, searchColor, lightText, 15);
		add(searchbtn);

		// View All Button
		view_all = new JButton("View All");
		view_all.setBounds(230, 145, 150, 45);
		view_all.addActionListener(this);
		styleButton(view_all, viewAllColor, lightText, 15);
		add(view_all);

		// Update Button
		update = new JButton("Update");
		update.setBounds(410, 145, 150, 45);
		update.addActionListener(this);
		styleButton(update, updateColor, lightText, 15);
		add(update);

		// Back Button
		back = new JButton("Back");
		back.setBounds(590, 145, 150, 45);
		back.addActionListener(this);
		styleButton(back, backColor, lightText, 15);
		add(back);

		setSize(1000, 700);
		setLayout(null);
		setLocation(250, 100);
		setVisible(true);
	}

	// Helper method to style buttons with rounded corners
	private void styleButton(JButton button, Color bgColor, Color fgColor, int cornerRadius) {
		button.setFont(new Font("Segoe UI", Font.BOLD, 18));
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

		button.addMouseListener(new java.awt.event.MouseAdapter() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchbtn) {
			String query = "select * from client where ClientID = '" + choiceEMP.getSelectedItem() + "'";
			try {
				Connection conn = DriverManager.getConnection(url, user_name, passWord);
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				table.setModel(DatabaseUtils.buildTableModel(resultSet));
				
				// Apply center alignment to all columns
				for (int i = 0; i < table.getColumnCount(); i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(
						new DefaultTableCellRenderer() {{
							setHorizontalAlignment(JLabel.CENTER);
						}}
					);
				}
			} catch (Exception E) {
				E.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error: " + E.getMessage(), 
					"Database Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == view_all) {
			try {
				Connection conn = DriverManager.getConnection(url, user_name, passWord);
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from client");
				table.setModel(DatabaseUtils.buildTableModel(resultSet));
				
				// Apply center alignment to all columns
				for (int i = 0; i < table.getColumnCount(); i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(
						new DefaultTableCellRenderer() {{
							setHorizontalAlignment(JLabel.CENTER);
						}}
					);
				}
			} catch (Exception E) {
				E.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error: " + E.getMessage(), 
					"Database Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == update) {
			new UpdateClient(choiceEMP.getSelectedItem());
			setVisible(false);
		} else {
			setVisible(false);
			new ClientDashboard();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ViewClient());
	}
}