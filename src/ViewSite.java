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

public class ViewSite extends JFrame implements ActionListener {
    String user_name = "root";
    String passWord = "Keyboard30%$";
    String url = "jdbc:mysql://localhost:3306/mcs";

    JTable table;
    Choice choiceEMP;
    JButton searchbtn, view_all, update, back;

    public ViewSite() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("View Site Information");

        // Background
        getContentPane().setBackground(new Color(15, 23, 42));

        // Colors consistent with ViewClient
        Color darkBlue = new Color(30, 58, 138);
        Color searchColor = new Color(251, 146, 60); // Orange
        Color viewAllColor = new Color(59, 130, 246); // Blue
        Color updateColor = new Color(34, 197, 94); // Green
        Color backColor = new Color(100, 116, 139); // Gray
        Color lightText = new Color(255, 255, 255);
        Color labelBg = new Color(51, 65, 85);

        // Title Label
        JLabel titleLabel = new JLabel("Site Information Viewer");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setBounds(250, 15, 450, 50);
        titleLabel.setForeground(lightText);
        titleLabel.setBackground(darkBlue);
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);

        // Search label
        JLabel searchLabel = new JLabel("Search by Site ID");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        searchLabel.setBounds(50, 90, 180, 30);
        searchLabel.setForeground(lightText);
        searchLabel.setBackground(labelBg);
        searchLabel.setOpaque(true);
        searchLabel.setHorizontalAlignment(JLabel.CENTER);
        searchLabel.setBorder(BorderFactory.createLineBorder(labelBg, 2, true));
        add(searchLabel);

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

            ResultSet rs = statement.executeQuery("SELECT * FROM site");
            while (rs.next()) {
                choiceEMP.add(rs.getString("SiteID"));
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

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(30, 58, 138));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 200, 930, 420);
        sp.setBorder(BorderFactory.createLineBorder(new Color(59, 130, 246), 2));
        add(sp);

        // Buttons
        searchbtn = new JButton("Search");
        searchbtn.setBounds(50, 145, 150, 45);
        searchbtn.addActionListener(this);
        styleButton(searchbtn, searchColor, lightText, 15);
        add(searchbtn);

        view_all = new JButton("View All");
        view_all.setBounds(230, 145, 150, 45);
        view_all.addActionListener(this);
        styleButton(view_all, viewAllColor, lightText, 15);
        add(view_all);

        update = new JButton("Update");
        update.setBounds(410, 145, 150, 45);
        update.addActionListener(this);
        styleButton(update, updateColor, lightText, 15);
        add(update);

        back = new JButton("Back");
        back.setBounds(590, 145, 150, 45);
        back.addActionListener(this);
        styleButton(back, backColor, lightText, 15);
        add(back);

        // Frame defaults
        setSize(1000, 700);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor, int radius) {
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
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Hover/Pressed Animation
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
            String query = "SELECT * FROM site WHERE SiteID = '" + choiceEMP.getSelectedItem() + "'";
            loadTable(query);
        } else if (e.getSource() == view_all) {
            loadTable("SELECT * FROM site");
        } else if (e.getSource() == update) {
            new UpdateSite(choiceEMP.getSelectedItem());
            setVisible(false);
        } else {
            new SiteDashboard();
            setVisible(false);
        }
    }

    private void loadTable(String query) {
        try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            table.setModel(DatabaseUtils.buildTableModel(rs));

            // Center align all cells
            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(JLabel.CENTER);

            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(center);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ViewSite();
    }
}
