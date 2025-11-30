
import javax.swing.*;
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
    public ViewSite(){
    	
		
        getContentPane().setBackground(new Color(255,131,122));
        JLabel search = new JLabel("Search by Site Id");
        search.setBounds(20,20,150,20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180,20,150,20);
        add(choiceEMP);

        try{
			Connection conn = DriverManager.getConnection(url, user_name, passWord);
			Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from site");
            while (resultSet.next()){
                choiceEMP.add(resultSet.getString("SiteID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();
        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0,100,900,600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20,70,80,20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        view_all = new JButton("View All");
        view_all.setBounds(120,70,80,20);
        view_all.addActionListener(this);
        add(view_all);

        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(this);
        add(back);


	     setSize(900,700);
	     setLayout(null);
	     setLocation(300,100);
	     setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchbtn){
            String query = "select * from site where SiteID = '"+choiceEMP.getSelectedItem()+"'";
            try {
    			Connection conn = DriverManager.getConnection(url, user_name, passWord); 
    			Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                table.setModel(DatabaseUtils.buildTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == view_all) {
            try{
    			Connection conn = DriverManager.getConnection(url, user_name, passWord); 
    			Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("select * from site");
                table.setModel(DatabaseUtils.buildTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == update){
            new UpdateSite(choiceEMP.getSelectedItem());
            setVisible(false);
        } else {
            setVisible(false);
            new SiteDashboard();
        }
    }

    public static void main(String[] args) {
        new ViewSite();
    }
}