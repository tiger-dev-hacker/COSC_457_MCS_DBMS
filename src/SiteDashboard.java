import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder; 

public class SiteDashboard extends JFrame{
	// TODO Auto-generated method stub
			private JPanel windowpane;
			private JButton addEmployeeButton; 
			private JButton viewEmployeeButton; 
			private JButton findSiteButton; 
			private JButton deleteEmployeeButton; 
			private JButton backToDashboardbtn; 

			
			public SiteDashboard()
			{
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(450, 190, 1014, 597); 
				setResizable(false); 
				
				windowpane = new JPanel(); 
				
				windowpane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
				setContentPane(windowpane); 
				
				windowpane.setLayout(null);
				
				
				addEmployeeButton = new JButton("Add Site");
				addEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				addEmployeeButton.setBounds(200, 100, 250, 74); 
				windowpane.add(addEmployeeButton); 
				addEmployeeButton.addActionListener(e -> handleAddEmployee()); 
				
				viewEmployeeButton = new JButton("View/Update Site");
				viewEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				viewEmployeeButton.setBounds(500, 100, 350, 74); 
				windowpane.add(viewEmployeeButton); 
				viewEmployeeButton.addActionListener(e -> handleViewEmployee()); 


				findSiteButton = new JButton("Find Site");
				findSiteButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				findSiteButton.setBounds(300, 300, 250, 74); 
				windowpane.add(findSiteButton); 
				findSiteButton.addActionListener(e -> handleFindSite()); 

				
				deleteEmployeeButton = new JButton("Delete Site");
				deleteEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				deleteEmployeeButton.setBounds(600, 300, 250, 74); 
				windowpane.add(deleteEmployeeButton); 
				deleteEmployeeButton.addActionListener(e -> handleDeleteEmployee());
				
				backToDashboardbtn = new JButton("Back to Client/Site Dashboard");
				backToDashboardbtn.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				backToDashboardbtn.setBounds(300, 450, 300, 74); 
				windowpane.add(backToDashboardbtn); 
				backToDashboardbtn.addActionListener(e -> handleBackButton()); 
				setVisible(true); 
			}
			
				
				private void handleAddEmployee() {
					new AddSite(); 
					setVisible(false); 
				}
				
				private void handleViewEmployee() {
					new ViewSite(); 
					setVisible(false); 
				}
				
				private void handleFindSite()
				{
					new FindSite(); 
					setVisible(false); 
				}
								
				private void handleDeleteEmployee() {
					new DeleteSite(); 
					setVisible(false); 
				}
				
				private void handleBackButton() {
					new ClientSiteDashboard(); 
					setVisible(false); 
				}
		}

