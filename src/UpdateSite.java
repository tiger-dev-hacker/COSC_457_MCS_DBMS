

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateSite extends JFrame implements ActionListener {
	String user_name = "root"; 
	String passWord = "Keyboard30%$";
	String url = "jdbc:mysql://localhost:3306/mcs";
	
    JTextField site_name, escort_limit, client_id;
    JLabel tempid;
    JButton add,back;
    String number;
   
    public UpdateSite (String number){

        this.number = number;
        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Update Site Details");
        heading.setBounds(320,30,300,50);
        heading.setFont(new Font("serif", Font.BOLD,25));
        add(heading);
      
        JLabel toolID = new JLabel("Site ID");
        toolID.setBounds(50,150,120,30);
        toolID.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(toolID);
        
        tempid = new JLabel("" + this.number);
        tempid.setBounds(200,150,150,30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        tempid.setBackground(new Color(177,252,197));
        add(tempid);

        
        JLabel toolName = new JLabel("Site Name");
        toolName.setBounds(50,200,120,30);
        toolName.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(toolName);
        
        site_name = new JTextField();
        site_name.setBounds(200,200,150,30);
        site_name.setBackground(new Color(177,252,197));
        add(site_name);

        JLabel toolManifest = new JLabel("Escort Limit");
        toolManifest.setBounds(50,250,120,30);
        toolManifest.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(toolManifest);
        
        escort_limit = new JTextField();
        escort_limit.setBounds(200,250,150,30);
        escort_limit.setBackground(new Color(177,252,197));
        add(escort_limit);
        
        

        JLabel ClientIDlabel = new JLabel("Client ID");
        ClientIDlabel.setBounds(50,300,120,30);
        ClientIDlabel.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(ClientIDlabel);
        
        client_id = new JTextField();
        client_id.setBounds(200,300,150,30);
        client_id.setBackground(new Color(177,252,197));
        add(client_id);
        
       
      
//
        try {
        	Connection conn = DriverManager.getConnection(url, user_name, passWord);
   			Statement statement = conn.createStatement();            
   			String query = "select * from site where SiteID = '"+ number +"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
            	site_name.setText(resultSet.getString("SiteName"));
            	escort_limit.setText(resultSet.getString("EscortLimit"));
            	client_id.setText(resultSet.getString("ClientID"));


            }
        }catch (Exception e){
            e.printStackTrace();
        }


        add = new JButton("UPDATE");
        add.setBounds(450,550,150,40);
        add.setBackground(Color.black);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(250,550,150,40);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);


        setSize(900,700);
        setLayout(null);
        setLocation(300,50);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == add){
          String Sname = site_name.getText();
          String SEscortLimit = escort_limit.getText();
	      String ClientID = client_id.getText().trim(); 

         
          try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
  			Statement statement = conn.createStatement();
             String query = "update site set SiteName = '"+ Sname +"', EscortLimit = '"+ SEscortLimit + "', ClientID = '"+ClientID  +"' where SiteID = '"+ number +"'";
              statement.executeUpdate(query);
              JOptionPane.showMessageDialog(null, "Site Details updated successfully");
              setVisible(false);
              new SiteDashboard();
          }catch (Exception E){
              E.printStackTrace();
          }
      }else {
          setVisible(false);
          new ViewSite();
      }
    }

    public static void main(String[] args) {
        new UpdateSite("");
    }
}