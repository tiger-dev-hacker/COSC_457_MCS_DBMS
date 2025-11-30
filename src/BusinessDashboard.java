
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

public class BusinessDashboard extends JFrame {

	BusinessDashboard() {
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
		
		JButton btnClientSites; 
		btnClientSites = new JButton("Client/Site"); 
		btnClientSites.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnClientSites.setBounds(200, 400, 150, 90); 
		image.add(btnClientSites); 
		
		btnClientSites.addActionListener(e -> handleClientSiteClick()); 
		
		JButton btnToolsMachinery; 
		btnToolsMachinery = new JButton("Tools/Machinery"); 
		btnToolsMachinery.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnToolsMachinery.setBounds(400, 400, 200, 90); 
		image.add(btnToolsMachinery); 
		
		btnToolsMachinery.addActionListener(e -> handleToolsClick()); 
		
		JButton btnContractJob; 
		btnContractJob = new JButton("Contract/Job"); 
		btnContractJob.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnContractJob.setBounds(650, 400, 200, 90); 
		
		image.add(btnContractJob); 
		btnContractJob.addActionListener(e -> handleContractJobClick()); 

		
		JButton btnBackButton; 
		btnBackButton = new JButton("Back to Main Dashboard");
		btnBackButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
		btnBackButton.setBounds(900, 400, 300, 90); 
		image.add(btnBackButton); 
		btnBackButton.addActionListener(e -> handleBackButton()); 

		add(image);

		
	}
	
	// Method to handle HR/Employees button click
	private void handleClientSiteClick() {
		new ClientSiteDashboard(); // Call external class from same package
		setVisible(false); // Hide current frame (optional)
		// Or use: dispose(); to close the frame completely
	}
	
	private void handleContractJobClick() {
		new ContractJobDashboard(); // Call external class from same package
		setVisible(false); // Hide current frame (optional)
		// Or use: dispose(); to close the frame completely
	}
	
	private void handleToolsClick() {
		new ToolDashboard(); 
		setVisible(false);
	}

	private void handleBackButton() {
		new MainDashboard(); 
		setVisible(false); 
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new BusinessDashboard(); 
	}

}
