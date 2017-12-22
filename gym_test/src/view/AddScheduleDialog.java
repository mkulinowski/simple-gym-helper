package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Classes;
import entities.Schedule;
import entities.Staff;
import service.ClassesService;
import service.ScheduleService;
import service.StaffService;


public class AddScheduleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField yearField;
	private JTextField monthField;
	private JTextField dayField;
	private JTextField hourField;
	private JTextField minutesField;


	/**
	 * Create the dialog.
	 */
	public AddScheduleDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{74, 332, 0};
		gbl_contentPanel.rowHeights = new int[]{31, 158, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
	
		{
			JLabel cDateLabel = new JLabel("Set Class Date:");
			GridBagConstraints gbc_cDateLabel = new GridBagConstraints();
			gbc_cDateLabel.anchor = GridBagConstraints.EAST;
			gbc_cDateLabel.insets = new Insets(0, 0, 5, 5);
			gbc_cDateLabel.gridx = 0;
			gbc_cDateLabel.gridy = 0;
			contentPanel.add(cDateLabel, gbc_cDateLabel);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			{
				JLabel lblNewLabel = new JLabel("Year-Month-Day");
				panel.add(lblNewLabel);
			}
			{
				yearField = new JTextField();
				panel.add(yearField);
				yearField.setColumns(4);
			}
			{
				monthField = new JTextField();
				panel.add(monthField);
				monthField.setColumns(2);
			}
			{
				dayField = new JTextField();
				panel.add(dayField);
				dayField.setColumns(2);
			}
			{
				JLabel lblHour = new JLabel("Hour");
				panel.add(lblHour);
			}
			{
				hourField = new JTextField();
				panel.add(hourField);
				hourField.setColumns(2);
			}
			{
				JLabel label = new JLabel(":");
				panel.add(label);
			}
			{
				minutesField = new JTextField();
				panel.add(minutesField);
				minutesField.setColumns(2);
			}
		}
		{
			JLabel lblSetTrainer = new JLabel("Set Class:");
			GridBagConstraints gbc_lblSetTrainer = new GridBagConstraints();
			gbc_lblSetTrainer.insets = new Insets(0, 0, 0, 5);
			gbc_lblSetTrainer.gridx = 0;
			gbc_lblSetTrainer.gridy = 1;
			contentPanel.add(lblSetTrainer, gbc_lblSetTrainer);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			DefaultTableModel model = dataModel();
			table = new JTable();
			table.setModel(model);
			table.setBackground(Color.WHITE);
			scrollPane.setViewportView(table);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(isFilled()){
							saveData();
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "You must fill fields and choose class!");
						}
						
					}
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
					
				});
				buttonPane.add(cancelButton);
			}
		}
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
	
	private void saveData(){
		Classes classes = new Classes();
		ClassesService clasServ = new ClassesService();
		Schedule schedule = new Schedule();
		ScheduleService schedServ = new ScheduleService();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		try{
			//Getting Classes object from DB
			int row = table.getSelectedRow();
			String value = table.getValueAt(row, 0).toString();
			int class_id = Integer.parseInt(value);
			classes = clasServ.findByid(class_id);
			
			//Getting date String from textFields and parsing to date format
			String class_date = yearField.getText()+"-"+monthField.getText()+"-"+
		    dayField.getText()+" "+hourField.getText()+":"+minutesField.getText()+
		    ":00.0";
			date = format.parse(class_date);
			
			schedule.setClassDate(date);
			schedule.setClasses(classes);
			
			schedServ.save(schedule);
			JOptionPane.showMessageDialog(this, "Class schedule successfully saved!");
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Can't save schedule!");
		}
	}
	//This method checks if all necessary fields are filled 
		private boolean isFilled(){
			if(yearField.getText().isEmpty() || monthField.getText().isEmpty() ||
				dayField.getText().isEmpty() || hourField.getText().isEmpty()
				|| minutesField.getText().isEmpty()|| table.getSelectedRow()<0){
				return false;
			}else{
				return true;
			}
		}
}

