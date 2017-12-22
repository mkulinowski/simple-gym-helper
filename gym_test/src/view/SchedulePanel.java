package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import entities.Classes;
import entities.Schedule;
import entities.Staff;
import service.ClassesService;
import service.ScheduleService;
import service.StaffService;

public class SchedulePanel extends JPanel {

	
	private JTable table;
	/**
	 * Create the panel.
	 */
	public SchedulePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog addSchedDialog = new AddScheduleDialog();
				
				//Window listener allows to refresh jtable
				addSchedDialog.addWindowListener(new WindowAdapter(){


					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}

				});
				addSchedDialog.setVisible(true);
			}
			
		});
		buttonPanel.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String class_date = table.getValueAt(row, 2).toString();
				JDialog deleteSchedDialog = new DeleteScheduleDialog(class_date);
				
				//Window listener allows to refresh jtable
				deleteSchedDialog.addWindowListener(new WindowAdapter(){


					@Override
					public void windowClosed(WindowEvent arg0) {
						DefaultTableModel newModel = dataModel();
						table.setModel(newModel);
						
					}

				});
				deleteSchedDialog.setVisible(true);
			}
			
		});
				
		buttonPanel.add(btnDelete);
		
		JButton btnAddAcitivity = new JButton("Add Acitivity");
		btnAddAcitivity.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0){
					JOptionPane.showMessageDialog(null, "Select activity!");
				}else{
				int row = table.getSelectedRow();
				String class_date = table.getValueAt(row, 2).toString();
				JDialog addActDialog = new AddActivityDialog(class_date);
				addActDialog.setVisible(true);
				}
			}
			
		});
		buttonPanel.add(btnAddAcitivity);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		DefaultTableModel model = dataModel();
		table = new JTable();
		table.setModel(model);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

	}
	
	//Method which return a default table model with data from DB 
	private DefaultTableModel dataModel(){
		Object[] colNames = {"Class ID", "Class Name","Date", "Trainer First Name","Trainer Last Name"};
		
		//Anonymous class - makes our table cells not editable
		DefaultTableModel dtm = new DefaultTableModel(null,colNames){
			@Override
			 public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};
		
		//Preparing data, loading it from DB and adding to model
		ScheduleService schServ = new ScheduleService();
		Classes classes = new Classes();
		ClassesService clasServ = new ClassesService();
		Staff staff = new Staff();
		StaffService stafServ = new StaffService();
		try{
			List<Schedule> schedList = new ArrayList<Schedule>();
			schedList = schServ.getAll();
			for(Schedule schedule : schedList){
				int class_id = schedule.getClasses().getClassId();
				classes = clasServ.findByid(class_id);
				int staff_id = classes.getStaff().getStaffId();
				staff = stafServ.findByid(staff_id);
				dtm.addRow(new Object[]{
						classes.getClassId(), classes.getName(),schedule.getClassDate(),
						staff.getFirstName(),staff.getLastName()
				});
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dtm;
		
		
	}

}
