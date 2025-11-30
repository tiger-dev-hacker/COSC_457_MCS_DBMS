

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateJob extends JFrame implements ActionListener {
	String user_name = "root"; 
	String passWord = "Keyboard30%$";
	String url = "jdbc:mysql://localhost:3306/mcs";
	
    JTextField job_name, job_length, contract_id;
    JLabel tempid;
    JButton add,back;
    String number;
   
    public UpdateJob (String number){

        this.number = number;
        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Update Job Details");
        heading.setBounds(320,30,300,50);
        heading.setFont(new Font("serif", Font.BOLD,25));
        add(heading);
      
        JLabel JobID = new JLabel("Job ID");
        JobID.setBounds(50,150,120,30);
        JobID.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(JobID);
        
        tempid = new JLabel("" + this.number);
        tempid.setBounds(200,150,150,30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        tempid.setBackground(new Color(177,252,197));
        add(tempid);

        
        JLabel jobName = new JLabel("Job Name");
        jobName.setBounds(50,200,120,30);
        jobName.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(jobName);
        
        job_name = new JTextField();
        job_name.setBounds(200,200,150,30);
        job_name.setBackground(new Color(177,252,197));
        add(job_name);

        JLabel toolManifest = new JLabel("Job Length (In weeks)");
        toolManifest.setBounds(50,250,250,30);
        toolManifest.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(toolManifest);
        
        job_length = new JTextField();
        job_length.setBounds(300,250,150,30);
        job_length.setBackground(new Color(177,252,197));
        add(job_length);
        
        JLabel lblContractID = new JLabel("Contract ID ");
        lblContractID.setBounds(50,300,120,30);
        lblContractID.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(lblContractID);
        
        contract_id = new JTextField();
        contract_id.setBounds(200,300,150,30);
        contract_id.setBackground(new Color(177,252,197));
        add(contract_id);
        
        
        
       
      
//
        try {
        	Connection conn = DriverManager.getConnection(url, user_name, passWord);
   			Statement statement = conn.createStatement();            
   			String query = "select * from job where JobID = '"+ number +"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
            	job_name.setText(resultSet.getString("JobName"));
            	job_length.setText(resultSet.getString("JobLength"));
            	contract_id.setText(resultSet.getString("ContractID"));


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
          String jName = job_name.getText();
          String jLength = job_length.getText();
          String contractID = contract_id.getText(); 
          try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
  			Statement statement = conn.createStatement();
             String query = "update job set JobName = '"+ jName +"', JobLength = '"+ jLength + "', ContractID = '"  + contractID +"'  where JobID = '"+ number +"'";
              statement.executeUpdate(query);
              JOptionPane.showMessageDialog(null, "Job Details updated successfully");
              setVisible(false);
              new JobDashboard();
          }catch (Exception E){
              E.printStackTrace();
          }
      }else {
          setVisible(false);
          new ViewJob();
      }
    }

    public static void main(String[] args) {
        new UpdateJob("");
    }
}