

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

public class AddTools extends JFrame {
	private static final long serialVersionUID = 1L; 
	private JPanel contentPane; 
	private JTextField tool_name; 
	private JTextField tool_manifest; 

	private JButton btnNewButton; 
	private JButton btnBackButton; 

	public AddTools() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597); 
		setResizable(false); 
		
		contentPane = new JPanel(); 
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane); 
		
		contentPane.setLayout(null);
		
		JLabel lblNewUserRegister = new JLabel("New Tool Register"); 
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblNewUserRegister.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewUserRegister);
		
		
		JLabel lblName = new JLabel("Tool Name"); 
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(58, 152, 99, 43);
		contentPane.add(lblName); 
		
		JLabel lblNewLabel = new JLabel("Tool Manifest"); 
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 243, 150, 29);
		contentPane.add(lblNewLabel);
		

		tool_name = new JTextField(); 
		tool_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
		tool_name.setBounds(214, 151, 228, 50); 
		contentPane.add(tool_name);
		tool_name.setColumns(10);
		
		
		tool_manifest = new JTextField(); 
		tool_manifest.setFont(new Font("Tahoma", Font.PLAIN, 32));
		tool_manifest.setBounds(214, 235, 228, 50); 
		contentPane.add(tool_manifest);
		tool_manifest.setColumns(10);
		
		
		
		btnNewButton = new JButton("Register"); 
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) 
		    {
		        String toolName = tool_name.getText().trim(); 
		        String toolManifest = tool_manifest.getText().trim(); 
		       
		        
		        try
		        {
		            String user_name = "root"; 
		            String passWord = "Keyboard30%$";
		            String url = "jdbc:mysql://localhost:3306/mcs"; 
		            Connection conn = DriverManager.getConnection(url, user_name, passWord); 
		            
		            // Specify columns (exclude EmployeeID - it auto-generates)
		            String query = "INSERT INTO Tool (ToolName, ToolManifest ) " +
		                          "VALUES (?, ?)";
		            
		            PreparedStatement pst = conn.prepareStatement(query);
		            pst.setString(1, toolName);
		            pst.setString(2, toolManifest);
		          
		            
		            int x = pst.executeUpdate(); 
		            
		            if (x > 0) {
		                JOptionPane.showMessageDialog(btnNewButton, 
		                    "Tool " + toolName + "\nis successfully added"); 
		                
		                // Clear fields after successful registration
		                tool_name.setText("");
		                tool_manifest.setText("");
		              
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
		
		btnBackButton = new JButton("Back to Tool Dashboard"); 
		btnBackButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnBackButton.setBounds(500, 447, 350, 74); 
		contentPane.add(btnBackButton); 
		btnBackButton.addActionListener(e -> handleBackButton()); 
		setVisible(true); 
	}
	
	private void handleBackButton()
	{
		new ToolDashboard(); 
		setVisible(false); 
	}
}
