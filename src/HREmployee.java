

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

public class HREmployee extends JFrame {
	private static final long serialVersionUID = 1L; 
	private JPanel contentPane; 
	private JTextField ssn; 
	private JTextField firstname; 
	private JTextField middlename; 
	private JTextField lastname; 
	private JTextField gender; 
	private JTextField mob; 
	private JButton btnNewButton; 
	private JButton btnBackButton; 

	public HREmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597); 
		setResizable(false); 
		
		contentPane = new JPanel(); 
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane); 
		
		contentPane.setLayout(null);
		
		JLabel lblNewUserRegister = new JLabel("New User Register"); 
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblNewUserRegister.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewUserRegister);
		
		
		JLabel lblName = new JLabel("SSN"); 
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(58, 152, 99, 43);
		contentPane.add(lblName); 
		
		JLabel lblNewLabel = new JLabel("First name"); 
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 243, 110, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmailAddress = new JLabel("Middle name"); 
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailAddress.setBounds(58, 324, 124, 36);
		contentPane.add(lblEmailAddress);
		
		ssn = new JTextField(); 
		ssn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		ssn.setBounds(214, 151, 228, 50); 
		contentPane.add(ssn);
		ssn.setColumns(10);
		
		
		firstname = new JTextField(); 
		firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
		firstname.setBounds(214, 235, 228, 50); 
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		
		middlename = new JTextField(); 
		middlename.setFont(new Font("Tahoma", Font.PLAIN, 32));
		middlename.setBounds(214, 320, 228, 50);
		contentPane.add(middlename); 
		middlename.setColumns(10);
		
		lastname = new JTextField(); 
		lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lastname.setBounds(707, 151, 228, 50); 
		contentPane.add(lastname); 
		lastname.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name"); 
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName.setBounds(542, 159, 99, 29);
		contentPane.add(lblLastName);
		

		
		JLabel lblMobileNumber = new JLabel("Phone number"); 
		lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMobileNumber.setBounds(542, 245, 139, 26);
		contentPane.add(lblMobileNumber);
		
		mob = new JTextField(); 
		mob.setFont(new Font("Tahoma", Font.PLAIN, 32));
		mob.setBounds(707, 235, 228, 50);
		contentPane.add(mob);
		mob.setColumns(10);

		
		JLabel lblGender = new JLabel("Gender"); 
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGender.setBounds(542, 329, 99, 24);
		contentPane.add(lblGender);
		
		gender = new JTextField(); 
		gender.setFont(new Font("Tahoma", Font.PLAIN, 32));
		gender.setBounds(707, 320, 228, 50); 
		contentPane.add(gender); 
		gender.setColumns(10);
		

		
		btnNewButton = new JButton("Register"); 
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) 
		    {
		        String Ssn = ssn.getText().trim(); 
		        String firstName = firstname.getText().trim(); 
		        String middleName = middlename.getText().trim(); 
		        String lastName = lastname.getText().trim();
		        String Gender = gender.getText().trim();
		        String mobileNumber = mob.getText().trim(); 
		        
		        // Validate mobile number
		        if (!mobileNumber.matches("\\d{10}"))
		        {
		            JOptionPane.showMessageDialog(btnNewButton, "Enter a valid 10-digit mobile number"); 
		            return; 
		        }
		        
		        try
		        {
		            String user_name = "root"; 
		            String passWord = "Keyboard30%$";
		            String url = "jdbc:mysql://localhost:3306/mcs"; 
		            Connection conn = DriverManager.getConnection(url, user_name, passWord); 
		            
		            // Specify columns (exclude EmployeeID - it auto-generates)
		            String query = "INSERT INTO Employee (SSN, gender, Fname, Mname, Lname, phone_number) " +
		                          "VALUES (?, ?, ?, ?, ?, ?)";
		            
		            PreparedStatement pst = conn.prepareStatement(query);
		            pst.setString(1, Ssn);
		            pst.setString(2, Gender);
		            pst.setString(3, firstName);
		            pst.setString(4, middleName);
		            pst.setString(5, lastName);
		            pst.setString(6, mobileNumber);
		            
		            int x = pst.executeUpdate(); 
		            
		            if (x > 0) {
		                JOptionPane.showMessageDialog(btnNewButton, 
		                    "Welcome, " + firstName + "\nYour account is successfully created"); 
		                
		                // Clear fields after successful registration
		                ssn.setText("");
		                firstname.setText("");
		                middlename.setText("");
		                lastname.setText("");
		                gender.setText("");
		                mob.setText("");
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
		
		btnBackButton = new JButton("Back to Employee Dashboard"); 
		btnBackButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnBackButton.setBounds(500, 447, 350, 74); 
		contentPane.add(btnBackButton); 
		btnBackButton.addActionListener(e -> handleBackButton()); 
		setVisible(true); 
	}
	
	private void handleBackButton()
	{
		new EmployeeDashboard(); 
		setVisible(false); 
	}
}
