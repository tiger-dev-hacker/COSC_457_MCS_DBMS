

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddClients extends JFrame {
	private static final long serialVersionUID = 1L; 
	private JPanel contentPane; 
	private JTextField client_name; 
	private JTextField client_bcel; 

	private JButton btnNewButton; 
	private JButton btnBackButton; 

	public AddClients() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597); 
		setResizable(false); 
		
		contentPane = new JPanel(); 
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane); 
		
		contentPane.setLayout(null);
		
		JLabel lblNewUserRegister = new JLabel("New Client Register"); 
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblNewUserRegister.setBounds(362, 52, 350, 50);
		contentPane.add(lblNewUserRegister);
		
		
		JLabel lblName = new JLabel("Client Name"); 
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(58, 152, 150, 43);
		contentPane.add(lblName); 
		
		JLabel lblNewLabel = new JLabel("Background Check Expiry Limit"); 
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 243, 400, 29);
		contentPane.add(lblNewLabel);
		

		client_name = new JTextField(); 
		client_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
		client_name.setBounds(214, 151, 228, 50); 
		contentPane.add(client_name);
		client_name.setColumns(10);
		
		
		client_bcel = new JTextField(); 
		client_bcel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		client_bcel.setBounds(400, 235, 228, 50); 
		contentPane.add(client_bcel);
		client_bcel.setColumns(10);
		
		
		
		btnNewButton = new JButton("Register"); 
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) 
		    {
		        String clientName = client_name.getText().trim(); 
		        String clientBCEL = client_bcel.getText().trim(); 
		       
		        
		        try
		        {
		            String user_name = "root"; 
		            String passWord = "Keyboard30%$";
		            String url = "jdbc:mysql://localhost:3306/mcs"; 
		            Connection conn = DriverManager.getConnection(url, user_name, passWord); 
		            
		            // Specify columns (exclude EmployeeID - it auto-generates)
		            String query = "INSERT INTO Client (ClientName, BackgroundCheckExpiryLimit ) " +
		                          "VALUES (?, ?)";
		            
		            PreparedStatement pst = conn.prepareStatement(query);
		            pst.setString(1, clientName);
		            pst.setString(2, clientBCEL);
		          
		            
		            int x = pst.executeUpdate(); 
		            
		            if (x > 0) {
		                JOptionPane.showMessageDialog(btnNewButton, 
		                    "Client " + clientName + "\nis successfully added"); 
		                
		                // Clear fields after successful registration
		                client_name.setText("");
		                client_bcel.setText("");
		              
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
		
		btnBackButton = new JButton("Back to Client Dashboard"); 
		btnBackButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnBackButton.setBounds(500, 447, 350, 74); 
		contentPane.add(btnBackButton); 
		btnBackButton.addActionListener(e -> handleBackButton()); 
		setVisible(true); 
	}
	
	private void handleBackButton()
	{
		new ClientDashboard(); 
		setVisible(false); 
	}
}
