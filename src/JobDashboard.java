
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder; 

public class JobDashboard extends JFrame{
	// TODO Auto-generated method stub
			private JPanel windowpane;
			private JButton addEmployeeButton; 
			private JButton viewEmployeeButton; 
			private JButton updateEmployeeButton; 
			private JButton deleteEmployeeButton; 
			private JButton backToDashboardbtn; 

			
			public JobDashboard()
			{
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(450, 190, 1014, 597); 
				setResizable(false); 
				
				windowpane = new JPanel(); 
				
				windowpane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
				setContentPane(windowpane); 
				
				windowpane.setLayout(null);
				
				
				addEmployeeButton = new JButton("Add Job");
				addEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				addEmployeeButton.setBounds(200, 100, 250, 74); 
				windowpane.add(addEmployeeButton); 
				addEmployeeButton.addActionListener(e -> handleAddEmployee()); 
				
				viewEmployeeButton = new JButton("View/Update Job");
				viewEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				viewEmployeeButton.setBounds(500, 100, 350, 74); 
				windowpane.add(viewEmployeeButton); 
				viewEmployeeButton.addActionListener(e -> handleViewEmployee()); 

 

				deleteEmployeeButton = new JButton("Delete Job");
				deleteEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				deleteEmployeeButton.setBounds(300, 300, 250, 74); 
				windowpane.add(deleteEmployeeButton); 
				deleteEmployeeButton.addActionListener(e -> handleDeleteEmployee()); 

				backToDashboardbtn = new JButton("Back to Client/Job Dashboard");
				backToDashboardbtn.setFont(new Font("Tahoma", Font.PLAIN, 22)); 
				backToDashboardbtn.setBounds(300, 450, 300, 74); 
				windowpane.add(backToDashboardbtn); 
				backToDashboardbtn.addActionListener(e -> handleBackButton()); 
				setVisible(true);
			}
				
				private void handleAddEmployee() {
					new AddJob(); 
					setVisible(false); 
				}
				
				private void handleViewEmployee() {
					new ViewJob(); 
					setVisible(false); 
				}
				
								
				private void handleDeleteEmployee() {
					new DeleteJob(); 
					setVisible(false); 
				}
				
				private void handleBackButton() {
					new ContractJobDashboard(); 
					setVisible(false); 
				}
		}

