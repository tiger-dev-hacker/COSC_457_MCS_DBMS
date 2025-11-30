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
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddContract extends JFrame {
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


	public AddContract() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597); 
		setResizable(false); 
		
		contentPane = new JPanel(); 
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane); 
		
		contentPane.setLayout(null);
		
		JLabel lblNewUserRegister = new JLabel("New Contract Register"); 
		lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblNewUserRegister.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewUserRegister);
		
		
		JLabel lblName = new JLabel("Start Date (MM/DD/YYYY)"); 
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(58, 152, 250, 43);
		contentPane.add(lblName); 
		
		JLabel lblNewLabel = new JLabel("End Date (MM/DD/YYYY)"); 
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 243, 250, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmailAddress = new JLabel("Budget"); 
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailAddress.setBounds(58, 324, 124, 36);
		contentPane.add(lblEmailAddress);
		
		ssn = new JTextField(); 
		ssn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		ssn.setBounds(320, 151, 228, 50); 
		contentPane.add(ssn);
		ssn.setColumns(10);
		
		
		firstname = new JTextField(); 
		firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
		firstname.setBounds(320, 235, 228, 50); 
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
		
		JLabel lblLastName = new JLabel("Federal Wage Scale"); 
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName.setBounds(542, 159, 200, 29);
		contentPane.add(lblLastName);
		

		
		JLabel lblMobileNumber = new JLabel("Client ID"); 
		lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMobileNumber.setBounds(542, 245, 139, 26);
		contentPane.add(lblMobileNumber);
		
		mob = new JTextField(); 
		mob.setFont(new Font("Tahoma", Font.PLAIN, 32));
		mob.setBounds(707, 235, 228, 50);
		contentPane.add(mob);
		mob.setColumns(10);

		
		JLabel lblGender = new JLabel("Signature Date (MM/DD/YYYY)"); 
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGender.setBounds(542, 329, 300, 24);
		contentPane.add(lblGender);
		
		gender = new JTextField(); 
		gender.setFont(new Font("Tahoma", Font.PLAIN, 32));
		gender.setBounds(707, 380, 228, 50); 
		contentPane.add(gender); 
		gender.setColumns(10);
		

		
		btnNewButton = new JButton("Register"); 
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) 
		    {
		        String startDateInput = ssn.getText().trim(); 
		        String endDateInput = firstname.getText().trim(); 
		        String budget = middlename.getText().trim(); 
		        String federalWageScale = lastname.getText().trim();
		        String clientID = mob.getText().trim();
		        String signatureDateInput = gender.getText().trim(); 
		       
		        
		        try
		        {
		            // Date format converters
		            SimpleDateFormat userFormat = new SimpleDateFormat("MM/dd/yyyy");
		            SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		            userFormat.setLenient(false); // Strict date validation
		            
		            // Convert dates to MySQL format
		            String startDate = mysqlFormat.format(userFormat.parse(startDateInput));
		            String endDate = mysqlFormat.format(userFormat.parse(endDateInput));
		            String signatureDate = mysqlFormat.format(userFormat.parse(signatureDateInput));
		            
		            String user_name = "root"; 
		            String passWord = "Keyboard30%$";
		            String url = "jdbc:mysql://localhost:3306/mcs"; 
		            Connection conn = DriverManager.getConnection(url, user_name, passWord); 
		            
		            // Specify columns (exclude EmployeeID - it auto-generates)
		            String query = "INSERT INTO Contract (StartDate, EndDate, Budget, FederalWageScale, ClientID, SignatureDate) " +
		                          "VALUES (?, ?, ?, ?, ?, ?)";
		            
		            PreparedStatement pst = conn.prepareStatement(query);
		            pst.setString(1, startDate);
		            pst.setString(2, endDate);
		            pst.setString(3, budget);
		            pst.setString(4, federalWageScale);
		            pst.setString(5, clientID);
		            pst.setString(6, signatureDate);
		            
		            int x = pst.executeUpdate(); 
		            
		            if (x > 0) {
		                JOptionPane.showMessageDialog(btnNewButton, 
		                    "Contract successfully created"); 
		                
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
		        catch(ParseException pe)
		        {
		            JOptionPane.showMessageDialog(btnNewButton, 
		                "Invalid date format. Please use MM/DD/YYYY\n" +
		                "Example: 12/05/2026", 
		                "Date Format Error", 
		                JOptionPane.ERROR_MESSAGE);
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
		
		btnBackButton = new JButton("Back to Contract Dashboard"); 
		btnBackButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnBackButton.setBounds(500, 447, 350, 74); 
		contentPane.add(btnBackButton); 
		btnBackButton.addActionListener(e -> handleBackButton()); 
		setVisible(true);  
	}
	
	private void handleBackButton()
	{
		new ContractDashboard(); 
		setVisible(false); 
	}
}