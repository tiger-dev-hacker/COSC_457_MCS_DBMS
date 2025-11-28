

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateEmployee extends JFrame implements ActionListener {
	String user_name = "root"; 
	String passWord = "Keyboard30%$";
	String url = "jdbc:mysql://localhost:3306/mcs";
	
    JTextField tfName, tmName, tlName, tGender, tPhoneNumber;
    JLabel tempid;
    JButton add,back;
    String number;
   
    public UpdateEmployee(String number){

        this.number = number;
        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320,30,300,50);
        heading.setFont(new Font("serif", Font.BOLD,25));
        add(heading);
      
        JLabel empID = new JLabel("Employee ID");
        empID.setBounds(50,150,120,30);
        empID.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(empID);
        
        tempid = new JLabel("" + this.number);
        tempid.setBounds(200,150,150,30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        tempid.setBackground(new Color(177,252,197));
        add(tempid);

        
        JLabel fname = new JLabel("First Name");
        fname.setBounds(50,200,120,30);
        fname.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(fname);
        
        tfName = new JTextField();
        tfName.setBounds(200,200,150,30);
        tfName.setBackground(new Color(177,252,197));
        add(tfName);

        JLabel mname = new JLabel("Middle Name");
        mname.setBounds(50,250,120,30);
        mname.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(mname);
        
        tmName = new JTextField();
        tmName.setBounds(200,250,150,30);
        tmName.setBackground(new Color(177,252,197));
        add(tmName);
        
        JLabel lname = new JLabel("Last Name");
        lname.setBounds(400,150,120,30);
        lname.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(lname);
        
        tlName = new JTextField();
        tlName.setBounds(600,150,150,30);
        tlName.setBackground(new Color(177,252,197));
        add(tlName);
        
        
        JLabel gender = new JLabel("Gender");
        gender.setBounds(400,200,120,30);
        gender.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(gender);
        
        tGender = new JTextField();
        tGender.setBounds(600 ,200,150,30);
        tGender.setBackground(new Color(177,252,197));
        add(tGender);
        
        JLabel phone_number = new JLabel("Phone Number");
        phone_number.setBounds(400,250,150,30);
        phone_number.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(phone_number);
        
        tPhoneNumber = new JTextField();
        tPhoneNumber.setBounds(600,250,150,30);
        tPhoneNumber.setBackground(new Color(177,252,197));
        add(tPhoneNumber);
      
//
        try {
        	Connection conn = DriverManager.getConnection(url, user_name, passWord);
   			Statement statement = conn.createStatement();            
   			String query = "select * from employee where EmployeeID = '"+number+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                tfName.setText(resultSet.getString("Fname"));
                tmName.setText(resultSet.getString("Mname"));
                tlName.setText(resultSet.getString("Lname"));
                tGender.setText(resultSet.getString("gender"));
                tPhoneNumber.setText(resultSet.getString("phone_number"));

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
          String fname = tfName.getText();
          String mname = tmName.getText();
          String lname = tlName.getText(); 
          String gender = tGender.getText(); 
          String phone_number = tPhoneNumber.getText(); 
          try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
  			Statement statement = conn.createStatement();
             String query = "update employee set Fname = '"+ fname +"', Mname = '"+ mname +"', Lname = '"+ lname + "', gender = '"+ gender +"', phone_number ='" + phone_number +"' where EmployeeID = '"+ number +"'";
              statement.executeUpdate(query);
              JOptionPane.showMessageDialog(null, "Details updated successfully");
              setVisible(false);
              new EmployeeDashboard();
          }catch (Exception E){
              E.printStackTrace();
          }
      }else {
          setVisible(false);
          new ViewEmployee();
      }
    }

    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}