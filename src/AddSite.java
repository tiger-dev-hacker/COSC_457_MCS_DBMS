


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

public class AddSite extends JFrame {
	private static final long serialVersionUID = 1L; 
	private JPanel contentPane; 
	private JTextField site_name; 
	private JTextField escort_limit; 
	private JTextField client_id; 

	private JButton btnNewButton; 
	private JButton btnBackButton; 

	public AddSite() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597); 
		setResizable(false); 
		
		contentPane = new JPanel(); 
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane); 
		
		contentPane.setLayout(null);
		
		JLabel lblNewUserRegister = new JLabel("New Site Register"); 
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblNewUserRegister.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewUserRegister);
		
		
		JLabel lblName = new JLabel("Site Name"); 
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(58, 152, 99, 43);
		contentPane.add(lblName); 
		
		JLabel lblNewLabel = new JLabel("Escort Limit"); 
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 243, 150, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblLocationLabel = new JLabel("Client ID"); 
		lblLocationLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLocationLabel.setBounds(500, 152, 120, 29);
		contentPane.add(lblLocationLabel);
		

		site_name = new JTextField(); 
		site_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
		site_name.setBounds(214, 151, 228, 50); 
		contentPane.add(site_name);
		site_name.setColumns(10);
		
		
		escort_limit = new JTextField(); 
		escort_limit.setFont(new Font("Tahoma", Font.PLAIN, 32));
		escort_limit.setBounds(214, 235, 228, 50); 
		contentPane.add(escort_limit);
		escort_limit.setColumns(10);
		
		client_id = new JTextField(); 
		client_id.setFont(new Font("Tahoma", Font.PLAIN, 32));
		client_id.setBounds(700, 151, 228, 50); 
		contentPane.add(client_id);
		client_id.setColumns(10);
		
		
		
		btnNewButton = new JButton("Register"); 
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) 
		    {
		        String siteName = site_name.getText().trim(); 
		        String escortLimit = escort_limit.getText().trim(); 
		        String clientID = client_id.getText().trim(); 

		        
		        try
		        {
		            String user_name = "root"; 
		            String passWord = "Keyboard30%$";
		            String url = "jdbc:mysql://localhost:3306/mcs"; 
		            Connection conn = DriverManager.getConnection(url, user_name, passWord); 
		            
		            // Specify columns (exclude EmployeeID - it auto-generates)
		            String query = "INSERT INTO Site (SiteName, EscortLimit, ClientID ) " +
		                          "VALUES (?, ?, ?)";
		            
		            PreparedStatement pst = conn.prepareStatement(query);
		            pst.setString(1, siteName);
		            pst.setString(2, escortLimit);
		            pst.setString(3, clientID);

		            
		            int x = pst.executeUpdate(); 
		            
		            if (x > 0) {
		                JOptionPane.showMessageDialog(btnNewButton, 
		                    "Site " + siteName + "\nis successfully added"); 
		                
		                // Clear fields after successful registration
		                site_name.setText("");
		                escort_limit.setText("");
		                client_id.setText("");

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
		
		btnBackButton = new JButton("Back to Site Dashboard"); 
		btnBackButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnBackButton.setBounds(500, 447, 350, 74); 
		contentPane.add(btnBackButton); 
		btnBackButton.addActionListener(e -> handleBackButton()); 
		setVisible(true); 
	}
	
	private void handleBackButton()
	{
		new SiteDashboard(); 
		setVisible(false); 
	}
}
