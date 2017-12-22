package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import entities.*;
import service.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ClassesPanel extends JPanel {

	private JTable table;
	/**
	 * Create the panel.
	 */
	public ClassesPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new GridLayout(0, 4, 0, 10));
		
		JButton nclassButton = new JButton("New Class");
		nclassButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog nclassDialog = new AddNewClassDialog();
				
				//Window listener allows to refresh jtable
				nclassDialog.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}
				});
				nclassDialog.setVisible(true);
			}
		}); 
	
		buttonPanel.add(nclassButton);
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String classes = JOptionPane.showInputDialog("Enter the Class ID: ");
		        int class_id = Integer.parseInt(classes);
				JDialog editClassDialog = new EditClassDialog(class_id);
				
				//Window listener allows to refresh jtable
				editClassDialog.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}
				});
				editClassDialog.setVisible(true);
			}
			
		});
	
		buttonPanel.add(editButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String classes = JOptionPane.showInputDialog("Enter the Class ID: ");
		        int class_id = Integer.parseInt(classes);
				JDialog deleteClassDialog = new DeleteClassDialog(class_id);
				deleteClassDialog.setVisible(true);
			}
			
		});
		buttonPanel.add(deleteButton);
		
		JButton scheduleButton = new JButton("Schedule");
		scheduleButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeAll();
				JPanel schedulePanel = new SchedulePanel();
				add(schedulePanel);
				revalidate();
				repaint();
			}
			
		});
		buttonPanel.add(scheduleButton);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		DefaultTableModel model = dataModel();
		table = new JTable();
		table.setModel(model);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
	}
	private DefaultTableModel dataModel(){
		Object[] colNames = {"Class ID", "Class Name", "Trainer First Name","Trainer Last Name"};
		DefaultTableModel dtm = new DefaultTableModel(null,colNames){
			@Override
			 public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};
		
	
		ClassesService clasServ = new ClassesService();
		Staff staff = new Staff();
		StaffService stafServ = new StaffService();
		try{
			List<Classes> clasList = new ArrayList<Classes>();
			clasList = clasServ.getAll();
			for(Classes classes : clasList){
				/**Using if construction because nulls are allowed in database for staff_id
				 *in table classes, but in staff table the columns we need to join to classPanel jtable
				 *are not nullable. In this case we add empty String to firstname and lastname columuns
				 *if Staff field is empty.
				 */
				if(classes.getStaff() == null){
					dtm.addRow(new Object[]{
							classes.getClassId(),classes.getName(),
							"",""});
					}else{
				int staff_id = classes.getStaff().getStaffId();
	            staff = stafServ.findByid(staff_id);
				dtm.addRow(new Object[]{classes.getClassId(),classes.getName(),
						staff.getFirstName(),staff.getLastName()});
			             }
				}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		dtm.fireTableDataChanged();
		return dtm;
	}
	
	
}
