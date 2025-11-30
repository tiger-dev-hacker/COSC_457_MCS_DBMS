import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteContract extends JFrame implements ActionListener {
	String user_name = "root"; 
	String passWord = "Keyboard30%$";
	String url = "jdbc:mysql://localhost:3306/mcs";
    Choice choiceEMPID;
    JButton delete, back;
    public DeleteContract(){

        JLabel label = new JLabel("Contract ID");
        label.setBounds(50,50,100,30);
        label.setFont(new Font("Tahoma", Font.BOLD,15));
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(200,50,150,30);
        add(choiceEMPID);

        try{
        	Connection conn = DriverManager.getConnection(url, user_name, passWord);
   			Statement statement = conn.createStatement();            
            ResultSet resultSet = statement.executeQuery("select * from contract");
            while (resultSet.next()){
                choiceEMPID.add(resultSet.getString("ContractID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel labelName = new JLabel("Start Date");
        labelName.setBounds(50,100,100,30);
        labelName.setFont(new Font("Tahoma", Font.BOLD,15));
        add(labelName);

        JLabel textName = new JLabel();
        textName.setBounds(200,100,100,30);
        add(textName);

        JLabel labelPhone = new JLabel("End Date");
        labelPhone.setBounds(50,150,100,30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD,15));
        add(labelPhone);

        JLabel textPhone = new JLabel();
        textPhone.setBounds(200,150,100,30);
        add(textPhone);

        JLabel labelBudget = new JLabel("Budget");
        labelBudget.setBounds(50,200,100,30);
        labelBudget.setFont(new Font("Tahoma", Font.BOLD,15));
        add(labelBudget);

        JLabel textBudget = new JLabel();
        textBudget.setBounds(200,200,100,30);
        add(textBudget);
        
        JLabel labelClientID = new JLabel("Client ID");
        labelClientID.setBounds(50,250,100,30);
        labelClientID.setFont(new Font("Tahoma", Font.BOLD,15));
        add(labelClientID);

        JLabel textClientID = new JLabel();
        textClientID.setBounds(200,250,100,30);
        add(textClientID);
        
        
        try {
        	Connection conn = DriverManager.getConnection(url, user_name, passWord);
   			Statement statement = conn.createStatement();         
            ResultSet resultSet = statement.executeQuery("select * from contract where ContractID = '"+ choiceEMPID.getSelectedItem() + "'");
            while (resultSet.next()){
                textName.setText(resultSet.getString("StartDate"));
                textPhone.setText(resultSet.getString("EndDate"));
                textBudget.setText(resultSet.getString("Budget")); 
                textClientID.setText(resultSet.getString("ClientID")); 
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                	Connection conn = DriverManager.getConnection(url, user_name, passWord);
           			Statement statement = conn.createStatement();         
                    ResultSet resultSet = statement.executeQuery("select * from contract where ContractID = '"+choiceEMPID.getSelectedItem()+"'");
                    while (resultSet.next()) {
                    	 textName.setText(resultSet.getString("StartDate"));
                         textPhone.setText(resultSet.getString("EndDate"));
                         textBudget.setText(resultSet.getString("Budget")); 
                         textClientID.setText(resultSet.getString("ClientID")); 
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220,300,100,30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(700,80,200,200);
        add(img);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("images/rback.png"));
        Image i22 = i11.getImage().getScaledInstance(1120,630,Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel image = new JLabel(i33);
        image.setBounds(0,0,1120,630);
        add(image);

        setSize(1000,400);
        setLocation(300,150);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==delete){
            try {
            	Connection conn = DriverManager.getConnection(url, user_name, passWord);
       			Statement statement = conn.createStatement();         
                String query = "delete from contract where ContractID = '"+choiceEMPID.getSelectedItem()+"'";
                int rowsDeleted = statement.executeUpdate(query);  // ← Use executeUpdate()

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Contract deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Contract ID not found!");
                }
                setVisible(false);
                new ContractDashboard();

            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
        	new ContractDashboard(); 
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new DeleteContract();
    }
}