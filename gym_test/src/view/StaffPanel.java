package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Staff;
import service.StaffService;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class StaffPanel extends JPanel {

	private JTable table;
	/**
	 * Create the panel.
	 */
	public StaffPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnAdd = new JButton("Add");
	    btnAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog addDialog = new AddStaffDialog();
				
				addDialog.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}
				});
				addDialog.setVisible(true);
				
			}
	    	
	    });
		buttonPanel.add(btnAdd);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String staff = JOptionPane.showInputDialog("Enter the Staff ID: ");
	            int staff_id = Integer.parseInt(staff);
				JDialog editDialog = new EditStaffDialog(staff_id);
				
				editDialog.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}
				});
				editDialog.setVisible(true);
				
			}
			
		});
		buttonPanel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String staff = JOptionPane.showInputDialog("Enter the Staff ID: ");
				int staff_id = Integer.parseInt(staff);
				JDialog deleteDialog = new DeleteStaffDialog(staff_id);
				
				deleteDialog.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}
				});
				deleteDialog.setVisible(true);
			}
			
		});
		buttonPanel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		DefaultTableModel model = dataModel();
		table = new JTable();
		table.setModel(model);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

	}
	
	private DefaultTableModel dataModel(){
		Object[] colNames = {"Staff ID", "First Name", "Last Name", "Email","Mobile","Position","Salary","Last Update"};
		DefaultTableModel dtm = new DefaultTableModel(null, colNames){
			@Override
			 public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};
		List<Staff> stafList = new ArrayList<Staff>();
		StaffService stafServ = new StaffService();
		try{
			stafList = stafServ.getAll();
			for(Staff staff : stafList){
				dtm.addRow(new Object[]{
						staff.getStaffId(),staff.getFirstName(),staff.getLastName(),
						staff.getEmail(),staff.getMobilePhone(),staff.getPosition(),
						staff.getSalary(),staff.getLastUpdate()
				});
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		dtm.fireTableDataChanged();
		return dtm;
	}

}
