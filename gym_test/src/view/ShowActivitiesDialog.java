package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Activities;
import entities.Classes;
import entities.Customer;
import service.ActivitiesService;
import service.ClassesService;
import service.CustomerService;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowActivitiesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
    private JTable table;

	/**
	 * Create the dialog.
	 */
	public ShowActivitiesDialog(int member_id) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		DefaultTableModel model = dataModel(member_id);
		table = new JTable();
		table.setModel(model);
		table.setBackground(Color.WHITE);
			scrollPane.setViewportView(table);
			
				
	}

	private DefaultTableModel dataModel(int member_id){
		Object[] colNames = {"Activity ID", "First Name","Last Name", "Class Name", "Class Date"};
		DefaultTableModel dtm = new DefaultTableModel(null,colNames){
			 @Override
			 public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};
		List<Activities> actList = new ArrayList<Activities>();
		List<Classes> claList = new ArrayList<Classes>();
		ActivitiesService actServ = new ActivitiesService();
		CustomerService custServ = new CustomerService();
		ClassesService clasServ = new ClassesService();
		Customer customer = new Customer();
		Classes classes = new Classes();
		
	    try{
	    	
	    	actList = actServ.getAll();
	    	customer = custServ.findByid(member_id);
	    	for (Activities activities : actList){
	    		if(activities.getCustomer().getCustomerId() == member_id){
	    			int class_id =activities.getClasses().getClassId();
	    			classes = clasServ.findByid(class_id);
	    			dtm.addRow(new Object[]{
							activities.getActivityId(), customer.getFirstName(),
							customer.getLastName(), classes.getName(),
							activities.getActivityDate()});
	    		}
	    	}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    	JOptionPane.showMessageDialog(this, "Operation failed!");
	    }
		
		dtm.fireTableDataChanged();
		return dtm;
		
	}


	
}
