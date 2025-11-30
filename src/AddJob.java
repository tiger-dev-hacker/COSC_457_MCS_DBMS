

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.font.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddJob extends JFrame {
	private static final long serialVersionUID = 1L; 
	private JPanel contentPane; 
	private JTextField job_name; 
	private JTextField job_length; 
	private JTextField contract_id; 

	private JButton btnNewButton; 
	private JButton btnBackButton; 

	public AddJob() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597); 
		setResizable(false); 
		
		contentPane = new JPanel(); 
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane); 
		
		contentPane.setLayout(null);
		
		JLabel lblNewUserRegister = new JLabel("New Job Register"); 
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblNewUserRegister.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewUserRegister);
		
		
		JLabel lblName = new JLabel("Job Name"); 
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(58, 152, 99, 43);
		contentPane.add(lblName); 
		
		JLabel lblNewLabel = new JLabel("Job Length"); 
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 243, 150, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblContractID = new JLabel("Contract ID"); 
		lblContractID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContractID.setBounds(58, 300, 150, 43);
		contentPane.add(lblContractID); 
		


		job_name = new JTextField(); 
		job_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
		job_name.setBounds(214, 151, 228, 50); 
		contentPane.add(job_name);
		job_name.setColumns(10);
		
		
		job_length = new JTextField(); 
		job_length.setFont(new Font("Tahoma", Font.PLAIN, 32));
		job_length.setBounds(214, 235, 228, 50); 
		contentPane.add(job_length);
		job_length.setColumns(10);
		
		contract_id = new JTextField(); 
		contract_id.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contract_id.setBounds(214, 300, 228, 50); 
		contentPane.add(contract_id);
		contract_id.setColumns(10);
		

		
		
		btnNewButton = new JButton("Register"); 
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) 
		    {
		        String jobName = job_name.getText().trim(); 
		        String jobLength = job_length.getText().trim(); 
		        String contractID = contract_id.getText().trim();
		        
		        try
		        {
		            String user_name = "root"; 
		            String passWord = "Keyboard30%$";
		            String url = "jdbc:mysql://localhost:3306/mcs"; 
		            Connection conn = DriverManager.getConnection(url, user_name, passWord); 
		            
		            // Specify columns (exclude EmployeeID - it auto-generates)
		            String query = "INSERT INTO Job (JobName, JobLength, ContractID ) " +
		                          "VALUES (?, ?, ?)";
		            
		            PreparedStatement pst = conn.prepareStatement(query);
		            pst.setString(1, jobName);
		            pst.setString(2, jobLength);
		            pst.setString(3, contractID);

		            
		            int x = pst.executeUpdate(); 
		            
		            if (x > 0) {
		                JOptionPane.showMessageDialog(btnNewButton, 
		                    "Job " + jobName + "\nis successfully added"); 
		                
		                // Clear fields after successful registration
		                job_name.setText("");
		                job_length.setText("");
		                contract_id.setText(""); 
		            }
		            
		            conn.close(); 
		        }
		        catch(Exception exception)
		        {
		            exception.printStackTrace();
		            JOptionPane.showMessageDialog(btnNewButton, 
		                "Error: " + exception.getMessage()); 
		        }
		    }
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnNewButton.setBounds(200, 447, 250, 74); 
		contentPane.add(btnNewButton); 
		
		btnBackButton = new JButton("Back to Job Dashboard"); 
		btnBackButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnBackButton.setBounds(500, 447, 350, 74); 
		contentPane.add(btnBackButton); 
		btnBackButton.addActionListener(e -> handleBackButton()); 
		setVisible(true); 
	}
	
	private void handleBackButton()
	{
		new JobDashboard(); 
		setVisible(false); 
	}
}
