
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder; 

public class ClientDashboard extends JFrame{
	// TODO Auto-generated method stub
			private JPanel windowpane;
			private JButton addEmployeeButton; 
			private JButton viewEmployeeButton; 
			private JButton findClientButton; 
			private JButton deleteEmployeeButton; 
			private JButton backToDashboardbtn; 

			
			public ClientDashboard()
			{
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(450, 190, 1014, 597); 
				setResizable(false); 
				
				windowpane = new JPanel(); 
				
				windowpane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
				setContentPane(windowpane); 
				
				windowpane.setLayout(null);
				
				
				addEmployeeButton = new JButton("Add Client");
				addEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				addEmployeeButton.setBounds(200, 100, 250, 74); 
				windowpane.add(addEmployeeButton); 
				addEmployeeButton.addActionListener(e -> handleAddEmployee()); 
				
				viewEmployeeButton = new JButton("View/Update Client");
				viewEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				viewEmployeeButton.setBounds(500, 100, 350, 74); 
				windowpane.add(viewEmployeeButton); 
				viewEmployeeButton.addActionListener(e -> handleViewEmployee()); 

 

				deleteEmployeeButton = new JButton("Delete Client");
				deleteEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				deleteEmployeeButton.setBounds(300, 300, 250, 74); 
				windowpane.add(deleteEmployeeButton); 
				deleteEmployeeButton.addActionListener(e -> handleDeleteEmployee()); 
				
				findClientButton = new JButton("Find Client");
				findClientButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				findClientButton.setBounds(600, 300, 250, 74); 
				windowpane.add(findClientButton); 
				findClientButton.addActionListener(e -> handleFindClient()); 
				
				backToDashboardbtn = new JButton("Back to Client/Site Dashboard");
				backToDashboardbtn.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				backToDashboardbtn.setBounds(300, 450, 300, 74); 
				windowpane.add(backToDashboardbtn); 
				backToDashboardbtn.addActionListener(e -> handleBackButton()); 
				setVisible(true);
			}
				
				private void handleAddEmployee() {
					new AddClients(); 
					setVisible(false); 
				}
				
				private void handleViewEmployee() {
					new ViewClient(); 
					setVisible(false); 
				}
				
				private void handleFindClient() {
					new FindClient(); 
					setVisible(false); 
				}
								
				private void handleDeleteEmployee() {
					new DeleteClient(); 
					setVisible(false); 
				}
				
				private void handleBackButton() {
					new ClientSiteDashboard(); 
					setVisible(false); 
				}
		}

