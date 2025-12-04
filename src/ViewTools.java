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

public class ViewTools extends JFrame implements ActionListener {

  

    JTable table;
    Choice choiceEMP;
    JButton searchbtn, view_all, update, back, assign_tool;

    public ViewTools() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("View Tool Information");

        // Background
        getContentPane().setBackground(new Color(15, 23, 42));

        // Color palette
        Color darkBlue = new Color(30, 58, 138);
        Color searchColor = new Color(251, 146, 60);     // Orange
        Color viewAllColor = new Color(59, 130, 246);    // Blue
        Color updateColor = new Color(34, 197, 94);      // Green
        Color assignColor = new Color(79, 70, 229);      // Indigo
        Color backColor = new Color(100, 116, 139);      // Gray
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85);

        // Title Label
        JLabel titleLabel = new JLabel("Tool Information Viewer");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setBounds(230, 15, 520, 55);
        titleLabel.setForeground(lightText);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(darkBlue);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);

        // Search label
        JLabel searchLabel = new JLabel("Search by Tool ID");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        searchLabel.setBounds(50, 95, 180, 30);
        searchLabel.setForeground(lightText);
        searchLabel.setBackground(labelBg);
        searchLabel.setOpaque(true);
        searchLabel.setHorizontalAlignment(JLabel.CENTER);
        searchLabel.setBorder(BorderFactory.createLineBorder(labelBg, 2, true));
        add(searchLabel);

        // Choice dropdown
        choiceEMP = new Choice();
        choiceEMP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        choiceEMP.setBounds(250, 95, 200, 30);
        choiceEMP.setBackground(new Color(248, 250, 252));
        choiceEMP.setForeground(new Color(30, 30, 30));
        add(choiceEMP);

		Connection conn = DatabaseConnection.getConnection();

        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM tool");
            while (rs.next()) {
                choiceEMP.add(rs.getString("ToolID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Styled JTable
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setGridColor(new Color(203, 213, 225));
        table.setBackground(Color.WHITE);
        table.setForeground(new Color(30, 30, 30));
        table.setSelectionBackground(new Color(59, 130, 246));
        table.setSelectionForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(30, 58, 138));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 220, 930, 420);
        sp.setBorder(BorderFactory.createLineBorder(new Color(59, 130, 246), 2));
        add(sp);

        // Buttons
        searchbtn = new JButton("Search");
        searchbtn.setBounds(50, 150, 150, 45);
        styleButton(searchbtn, searchColor, lightText, 15);
        searchbtn.addActionListener(this);
        add(searchbtn);

        view_all = new JButton("View All");
        view_all.setBounds(230, 150, 150, 45);
        styleButton(view_all, viewAllColor, lightText, 15);
        view_all.addActionListener(this);
        add(view_all);

        update = new JButton("Update");
        update.setBounds(410, 150, 150, 45);
        styleButton(update, updateColor, lightText, 15);
        update.addActionListener(this);
        add(update);

        assign_tool = new JButton("Assign Tool");
        assign_tool.setBounds(590, 150, 150, 45);
        styleButton(assign_tool, assignColor, lightText, 15);
        assign_tool.addActionListener(this);
        add(assign_tool);

        back = new JButton("Back");
        back.setBounds(770, 150, 150, 45);
        styleButton(back, backColor, lightText, 15);
        back.addActionListener(this);
        add(back);

        // Frame defaults
        setSize(1000, 700);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor, int radius) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(fgColor);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (button.getModel().isPressed()) {
                    g2.setColor(darkenColor(bgColor, 40));
                } else if (button.getModel().isRollover()) {
                    g2.setColor(brightenColor(bgColor, 30));
                } else {
                    g2.setColor(bgColor);
                }

                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    private Color brightenColor(Color c, int amount) {
        return new Color(
                Math.min(255, c.getRed() + amount),
                Math.min(255, c.getGreen() + amount),
                Math.min(255, c.getBlue() + amount)
        );
    }

    private Color darkenColor(Color c, int amount) {
        return new Color(
                Math.max(0, c.getRed() - amount),
                Math.max(0, c.getGreen() - amount),
                Math.max(0, c.getBlue() - amount)
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchbtn) {
            loadTable("SELECT * FROM tool WHERE ToolID = '" + choiceEMP.getSelectedItem() + "'");
        }
        else if (e.getSource() == view_all) {
            loadTable("SELECT * FROM tool");
        }
        else if (e.getSource() == update) {
            new UpdateTools(choiceEMP.getSelectedItem());
            setVisible(false);
        }
        else if (e.getSource() == assign_tool) {
            new AssignTools(choiceEMP.getSelectedItem());
        }
        else {
            new ToolDashboard();
            setVisible(false);
        }
    }

    private void loadTable(String query) {
        try {
			Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            table.setModel(DatabaseUtils.buildTableModel(rs));

            // Center-align all cells
            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);

            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(center);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ViewTools();
    }
}
