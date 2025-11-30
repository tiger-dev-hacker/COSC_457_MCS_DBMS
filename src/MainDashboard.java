import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

public class MainDashboard extends JFrame {
    public MainDashboard() {
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
        add(image); // Add image to frame FIRST
        
        JLabel text = new JLabel("Welcome to Management and Construction Services LLC. "); 
        text.setBounds(130, 40, 1100, 90);
        text.setForeground(Color.WHITE); 
        text.setFont(new Font("sans serif", Font.BOLD, 40)); 
        
        // Add custom pink background
        Color customPink = new Color(0xF53DB0);
        text.setBackground(customPink);
        text.setOpaque(true);
        
        image.add(text); // Add to image label
        
        JButton btnHREmployees = new JButton("HR/Employees"); 
        btnHREmployees.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
        btnHREmployees.setBounds(400, 400, 200, 90); 
        btnHREmployees.addActionListener(e -> handleHREmployeesClick()); 
        image.add(btnHREmployees); // Add to image label
        
        JButton btnBusinessClient = new JButton("Business/Clients"); 
        btnBusinessClient.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
        btnBusinessClient.setBounds(800, 400, 200, 90); 
        btnBusinessClient.addActionListener(e -> handleBusinessClientClick()); 
        image.add(btnBusinessClient); // Add to image label
        
        setVisible(true); 
    }
    
    private void handleHREmployeesClick() {
        new EmployeeDashboard();
        setVisible(false);
    }
    
    private void handleBusinessClientClick() {
        new BusinessDashboard();
        setVisible(false);
    }
    
    public static void main(String[] args) {
        new MainDashboard(); 
    }
}