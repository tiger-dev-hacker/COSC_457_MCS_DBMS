

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateTools extends JFrame implements ActionListener {
	String user_name = "root"; 
	String passWord = "Keyboard30%$";
	String url = "jdbc:mysql://localhost:3306/mcs";
	
    JTextField tool_name, tool_manifest;
    JLabel tempid;
    JButton add,back;
    String number;
   
    public UpdateTools (String number){

        this.number = number;
        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Update Tool Details");
        heading.setBounds(320,30,300,50);
        heading.setFont(new Font("serif", Font.BOLD,25));
        add(heading);
      
        JLabel toolID = new JLabel("Tool ID");
        toolID.setBounds(50,150,120,30);
        toolID.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(toolID);
        
        tempid = new JLabel("" + this.number);
        tempid.setBounds(200,150,150,30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        tempid.setBackground(new Color(177,252,197));
        add(tempid);

        
        JLabel toolName = new JLabel("Tool Name");
        toolName.setBounds(50,200,120,30);
        toolName.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(toolName);
        
        tool_name = new JTextField();
        tool_name.setBounds(200,200,150,30);
        tool_name.setBackground(new Color(177,252,197));
        add(tool_name);

        JLabel toolManifest = new JLabel("Tool Manifest");
        toolManifest.setBounds(50,250,120,30);
        toolManifest.setFont(new Font("SAN_SERIF", Font.BOLD,20));
        add(toolManifest);
        
        tool_manifest = new JTextField();
        tool_manifest.setBounds(200,250,150,30);
        tool_manifest.setBackground(new Color(177,252,197));
        add(tool_manifest);
        
        
        
       
      
//
        try {
        	Connection conn = DriverManager.getConnection(url, user_name, passWord);
   			Statement statement = conn.createStatement();            
   			String query = "select * from tool where ToolID = '"+ number +"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                tool_name.setText(resultSet.getString("ToolName"));
                tool_manifest.setText(resultSet.getString("ToolManifest"));
              

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
          String tname = tool_name.getText();
          String tManifest = tool_manifest.getText();
         
          try {
            Connection conn = DriverManager.getConnection(url, user_name, passWord);
  			Statement statement = conn.createStatement();
             String query = "update tool set ToolName = '"+ tname +"', ToolManifest = '"+ tManifest + "' where ToolID = '"+ number +"'";
              statement.executeUpdate(query);
              JOptionPane.showMessageDialog(null, "Tool Details updated successfully");
              setVisible(false);
              new ToolDashboard();
          }catch (Exception E){
              E.printStackTrace();
          }
      }else {
          setVisible(false);
          new ViewTools();
      }
    }

    public static void main(String[] args) {
        new UpdateTools("");
    }
}