package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Activities;
import entities.Classes;
import entities.Customer;
import service.*;

public class MembersPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1434290046822234840L;
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public MembersPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 3, 0, 5));
		
	    JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog addMemDialog = new AddMemberDialog();
				
				//Window listener allows to refresh jtable
				addMemDialog.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}
				});
				addMemDialog.setVisible(true);
				
			}
			
		});
		panel.add(addButton);
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String member = JOptionPane.showInputDialog("Enter the Member Card ID: ");
				if(member.isEmpty()){
					JOptionPane.showMessageDialog(null, "Enter Member ID!");
				}else{
	            int member_id = Integer.parseInt(member);
				JDialog editMemDialog = new EditMemberDialog(member_id);
				
				//Window listener allows to refresh jtable
				editMemDialog.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}
				});
				editMemDialog.setVisible(true);
				}
			}
			
		});
		panel.add(updateButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String member = JOptionPane.showInputDialog("Enter the Member Card ID: ");
				if(member.isEmpty()){
					JOptionPane.showMessageDialog(null, "Enter Member ID!");
				}else{
	            int member_id = Integer.parseInt(member);
	            JDialog deleteMemDialog = new DeleteMemberDialog(member_id);
	            
	            deleteMemDialog.addWindowListener(new WindowAdapter(){
	            	@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}
	            });
	            deleteMemDialog.setVisible(true);
				}
			}
			
		});
		panel.add(deleteButton);
		
		JButton enterGymButton = new JButton("Enter Gym");
		enterGymButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String member = JOptionPane.showInputDialog("Enter the Member Card ID: ");
				if(member.isEmpty()){
					JOptionPane.showMessageDialog(null, "Enter Member ID!");
				}else{
	            int member_id = Integer.parseInt(member);
				enterGym(member_id);
			}
			}
		});
		panel.add(enterGymButton);
		
		JButton showActivitiesButton = new JButton("Show activities");
		showActivitiesButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String member = JOptionPane.showInputDialog("Enter the Member Card ID: ");
				if(member.isEmpty()){
					JOptionPane.showMessageDialog(null, "Enter Member ID!");
				}else{
	            int member_id = Integer.parseInt(member);
	            JDialog showActDialog = new ShowActivitiesDialog(member_id);
	            showActDialog.setVisible(true);
				}
			}
			
		});
		panel.add(showActivitiesButton);
		
		JButton showPaymentsButton = new JButton("Show Payments");
		showPaymentsButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String member = JOptionPane.showInputDialog("Enter the Member Card ID: ");
				if(member.isEmpty()){
					JOptionPane.showMessageDialog(null, "Enter Member ID!");
				}else{
	            int member_id = Integer.parseInt(member);
	            JDialog deleteMemDialog = new ShowPaymentsDialog(member_id);
	            deleteMemDialog.setVisible(true);
				}
			}
			
		});
		panel.add(showPaymentsButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.CENTER);
		
		DefaultTableModel model = dataModel();
		table = new JTable();
		table.setModel(model);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

		
	}
	private DefaultTableModel dataModel(){
		Object[] colNames = {"Member Card ID","First Name","Last Name", "Email","Mobile","Pay Update","Join Date"};
		DefaultTableModel dtm = new DefaultTableModel(null,colNames){
			 @Override
			 public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};
		CustomerService cusService = new CustomerService();
		List<Customer> cusList = new ArrayList<Customer>();
		cusList = cusService.getAll();
		for(Customer customer : cusList){
			dtm.addRow(new Object[]{customer.getCustomerId(),customer.getFirstName(),customer.getLastName(),customer.getEmail(),
					customer.getMobilePhone(),customer.getPayOptUpdate(),customer.getJoinDate()});
		}
	    
		dtm.fireTableDataChanged();
		return dtm;
	}
	
    private void enterGym(int customer_id){
    	Activities activities = new Activities();
    	ActivitiesService actServ = new ActivitiesService();
    	Customer customer = new Customer();
    	CustomerService custServ = new CustomerService();
    	Classes classes = new Classes();
    	ClassesService clasServ = new ClassesService();
    	Date date = new Date();
    	try{
    		customer = custServ.findByid(customer_id);
    		classes = clasServ.findByid(5);
    		activities.setCustomer(customer);
    		activities.setClasses(classes);
    		activities.setActivityDate(date);
    		actServ.save(activities);
    		
    		JOptionPane.showMessageDialog(this, "Member succesfully entered GYM!");
    	}catch(Exception e){
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(this, "Can't enter GYM!");
    	}
    }
	
}
