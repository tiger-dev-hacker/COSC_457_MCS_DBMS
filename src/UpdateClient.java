

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateClient extends JFrame implements ActionListener {
	String user_name = "root"; 
	String passWord = "Keyboard30%$";
	String url = "jdbc:mysql://localhost:3306/mcs";
	
    JTextField client_name, client_bcel;
    JLabel tempid;
    JButton add,back;
    String number;
   
    public UpdateClient (String number){

        this.number = number;
        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Update Client Details");
        heading.setBounds(320,30,300,50);
        heading.setFont(new Font("serif", Font.BOLD,25));
        add(heading);
      
        JLabel client_id = new JLabel("Client ID");
        client_id.setBounds(50,150,120,30);
        client_id.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(client_id);
        
        tempid = new JLabel("" + this.number);
        tempid.setBounds(200,150,150,30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        tempid.setBackground(new Color(177,252,197));
        add(tempid);

        
        JLabel clientName = new JLabel("Client Name");
        clientName.setBounds(50,200,120,30);
        clientName.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(clientName);
        
        client_name = new JTextField();
        client_name.setBounds(200,200,150,30);
        client_name.setBackground(new Color(177,252,197));
        add(client_name);

        JLabel clientBCEL = new JLabel("Background Check Expiry Limit");
        clientBCEL.setBounds(50,250,120,30);
        clientBCEL.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(clientBCEL);
        
        client_bcel = new JTextField();
        client_bcel.setBounds(200,250,150,30);
        client_bcel.setBackground(new Color(177,252,197));
        add(client_bcel);
        
        
        
       
      
//
        try {
        	Connection conn = DriverManager.getConnection(url, user_name, passWord);
   			Statement statement = conn.createStatement();            
   			String query = "select * from client where ClientID = '"+ number +"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
            	client_name.setText(resultSet.getString("ClientName"));
            	client_bcel.setText(resultSet.getString("BackgroundCheckExpiryLimit"));
              

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
          String Cname = client_name.getText();
          String Cbcel = client_bcel.getText();
         
          try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
  			Statement statement = conn.createStatement();
             String query = "update client set ClientName = '"+ Cname +"', BackgroundCheckExpiryLimit = '"+ Cbcel + "' where ClientID = '"+ number +"' restrict";
              statement.executeUpdate(query);
              JOptionPane.showMessageDialog(null, "Client Details updated successfully");
              setVisible(false);
              new ViewClient();
          }catch (Exception E){
              E.printStackTrace();
          }
      }else {
          setVisible(false);
          new ViewClient();
      }
    }

    public static void main(String[] args) {
        new UpdateClient("");
    }
}