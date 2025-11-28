
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

public class MainDashboard extends JFrame {

	MainDashboard() {
		setSize(1366, 565);
		setLocation(100, 100);
		setLayout(null);

		// Scale the image to fit the frame
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/office.jpg"));
		Image img = i1.getImage();
		Image scaledImg = img.getScaledInstance(1366, 565, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		
		JLabel image = new JLabel(scaledIcon);
		image.setBounds(0, 0, 1366, 565);
		
		JLabel text = new JLabel("Welcome to Management and Construction Services LLC. "); 
		
		text.setBounds(130, 40, 1100, 90);
		text.setForeground(Color.WHITE); 
		text.setFont(new Font("sans serif", Font.BOLD, 40 )); 
		
		// Add custom pink background
		Color customPink = new Color(0xF53DB0); // Light pink
		text.setBackground(customPink);
		text.setOpaque(true); // Important: makes the background visible

		
		image.add(text); 
		setVisible(true); 
		
		JButton btnHREmployees; 
		btnHREmployees = new JButton("HR/Employees"); 
		btnHREmployees.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnHREmployees.setBounds(400, 400, 200, 90); 
		image.add(btnHREmployees); 
		
		btnHREmployees.addActionListener(e -> handleHREmployeesClick()); 
		
		
		JButton btnBusinessClient; 
		btnBusinessClient = new JButton("Business/Clients"); 
		btnBusinessClient.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnBusinessClient.setBounds(800, 400, 200, 90); 
		image.add(btnBusinessClient); 

		add(image);

		
	}
	
	// Method to handle HR/Employees button click
	private void handleHREmployeesClick() {
		new EmployeeDashboard(); // Call external class from same package
		setVisible(false); // Hide current frame (optional)
		// Or use: dispose(); to close the frame completely
	}
	


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MainDashboard(); 
	}

}
