import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class UpdateContract extends JFrame implements ActionListener {
	String user_name = "root"; 
	String passWord = "Keyboard30%$";
	String url = "jdbc:mysql://localhost:3306/mcs";
	
    JTextField tfName, tmName, tlName, tGender, tPhoneNumber, signature_Date;
    JLabel tempid;
    JButton add, back;
    String number;
   
    public UpdateContract(String number){

        this.number = number;
        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Update Contract Detail");
        heading.setBounds(320,30,300,50);
        heading.setFont(new Font("serif", Font.BOLD,25));
        add(heading);
      
        JLabel empID = new JLabel("Contract ID");
        empID.setBounds(50,150,120,30);
        empID.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(empID);
        
        tempid = new JLabel("" + this.number);
        tempid.setBounds(200,150,150,30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        tempid.setBackground(new Color(177,252,197));
        add(tempid);

        
        JLabel fname = new JLabel("Start Date");
        fname.setBounds(50,200,120,30);
        fname.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(fname);
        
        tfName = new JTextField();
        tfName.setBounds(200,200,150,30);
        tfName.setBackground(new Color(177,252,197));
        add(tfName);

        JLabel mname = new JLabel("End Date");
        mname.setBounds(50,250,120,30);
        mname.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(mname);
        
        tmName = new JTextField();
        tmName.setBounds(200,250,150,30);
        tmName.setBackground(new Color(177,252,197));
        add(tmName);
        
        JLabel lname = new JLabel("Budget");
        lname.setBounds(400,150,120,30);
        lname.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(lname);
        
        tlName = new JTextField();
        tlName.setBounds(600,150,150,30);
        tlName.setBackground(new Color(177,252,197));
        add(tlName);
        
        
        JLabel gender = new JLabel("Federal Wage");
        gender.setBounds(400,200,150,30);
        gender.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(gender);
        
        tGender = new JTextField();
        tGender.setBounds(600,200,150,30);
        tGender.setBackground(new Color(177,252,197));
        add(tGender);
        
        JLabel phone_number = new JLabel("Client ID");
        phone_number.setBounds(400,250,150,30);
        phone_number.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(phone_number);
        
        tPhoneNumber = new JTextField();
        tPhoneNumber.setBounds(600,250,150,30);
        tPhoneNumber.setBackground(new Color(177,252,197));
        add(tPhoneNumber);
      
        JLabel signature_date = new JLabel("Signature Date");
        signature_date.setBounds(50,300,150,30);
        signature_date.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(signature_date);
        
        signature_Date = new JTextField();
        signature_Date.setBounds(200,300,150,30);
        signature_Date.setBackground(new Color(177,252,197));
        add(signature_Date);
        
        JLabel formatHint = new JLabel("(Use MM/DD/YYYY format for dates)");
        formatHint.setBounds(200,340,300,20);
        formatHint.setFont(new Font("SAN_SERIF", Font.ITALIC,12));
        formatHint.setForeground(Color.DARK_GRAY);
        add(formatHint);

        try {
        	Connection conn = DriverManager.getConnection(url, user_name, passWord);
   			Statement statement = conn.createStatement();            
   			String query = "select * from contract where ContractID = '"+number+"'";
            ResultSet resultSet = statement.executeQuery(query);
            
            SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat displayFormat = new SimpleDateFormat("MM/dd/yyyy");
            
            while (resultSet.next()){
                // Convert dates from MySQL format to user-friendly format for display
                try {
                    String startDateStr = resultSet.getString("StartDate");
                    if (startDateStr != null && !startDateStr.isEmpty()) {
                        tfName.setText(displayFormat.format(mysqlFormat.parse(startDateStr)));
                    }
                    
                    String endDateStr = resultSet.getString("EndDate");
                    if (endDateStr != null && !endDateStr.isEmpty()) {
                        tmName.setText(displayFormat.format(mysqlFormat.parse(endDateStr)));
                    }
                    
                    String sigDateStr = resultSet.getString("SignatureDate");
                    if (sigDateStr != null && !sigDateStr.isEmpty()) {
                        signature_Date.setText(displayFormat.format(mysqlFormat.parse(sigDateStr)));
                    }
                } catch (ParseException pe) {
                    // If date parsing fails, just show the original format
                    tfName.setText(resultSet.getString("StartDate"));
                    tmName.setText(resultSet.getString("EndDate"));
                    signature_Date.setText(resultSet.getString("SignatureDate"));
                }
                
                tlName.setText(resultSet.getString("Budget"));
                tGender.setText(resultSet.getString("FederalWageScale"));
                tPhoneNumber.setText(resultSet.getString("ClientID"));
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
          String startDateInput = tfName.getText().trim();
          String endDateInput = tmName.getText().trim();
          String budget = tlName.getText().trim(); 
          String federalWageScale = tGender.getText().trim(); 
          String clientID = tPhoneNumber.getText().trim(); 
          String signatureDateInput = signature_Date.getText().trim(); 

          try {
              // Date format converters
              SimpleDateFormat userFormat = new SimpleDateFormat("MM/dd/yyyy");
              SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
              userFormat.setLenient(false); // Strict date validation
              
              // Convert dates to MySQL format
              String startDate = mysqlFormat.format(userFormat.parse(startDateInput));
              String endDate = mysqlFormat.format(userFormat.parse(endDateInput));
              String signatureDate = mysqlFormat.format(userFormat.parse(signatureDateInput));
              
              Connection conn = DriverManager.getConnection(url, user_name, passWord);
              
              // Use PreparedStatement to prevent SQL injection
              String query = "UPDATE contract SET StartDate = ?, EndDate = ?, Budget = ?, " +
                            "FederalWageScale = ?, ClientID = ?, SignatureDate = ? " +
                            "WHERE ContractID = ?";
              
              PreparedStatement pst = conn.prepareStatement(query);
              pst.setString(1, startDate);
              pst.setString(2, endDate);
              pst.setString(3, budget);
              pst.setString(4, federalWageScale);
              pst.setString(5, clientID);
              pst.setString(6, signatureDate);
              pst.setString(7, number);
              
              pst.executeUpdate();
              
              JOptionPane.showMessageDialog(null, "Contract Details updated successfully");
              setVisible(false);
              new ContractDashboard();
              
              conn.close();
              
          } catch(ParseException pe) {
              JOptionPane.showMessageDialog(null, 
                  "Invalid date format. Please use MM/DD/YYYY\n" +
                  "Example: 12/05/2026", 
                  "Date Format Error", 
                  JOptionPane.ERROR_MESSAGE);
          } catch (Exception E){
              E.printStackTrace();
              JOptionPane.showMessageDialog(null, 
                  "Error: " + E.getMessage(),
                  "Database Error",
                  JOptionPane.ERROR_MESSAGE);
          }
      } else {
          setVisible(false);
          new ViewContract();
      }
    }

    public static void main(String[] args) {
        new UpdateContract("");
    }
}