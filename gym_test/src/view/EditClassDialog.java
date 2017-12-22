package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import entities.Staff;
import service.ClassesService;
import service.StaffService;

public class EditClassDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField cnameField;
    private Classes classes;
    private ClassesService clasServ;

	/**
	 * Create the dialog.
	 */
	public EditClassDialog(int class_id) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
	    classes = new Classes();
	    clasServ = new ClassesService();
		classes = clasServ.findByid(class_id);
	
		{
			JLabel lblSetClassName = new JLabel("Set Class Name:");
			GridBagConstraints gbc_lblSetClassName = new GridBagConstraints();
			gbc_lblSetClassName.insets = new Insets(0, 0, 5, 5);
			gbc_lblSetClassName.anchor = GridBagConstraints.EAST;
			gbc_lblSetClassName.gridx = 0;
			gbc_lblSetClassName.gridy = 1;
			contentPanel.add(lblSetClassName, gbc_lblSetClassName);
		}
		{
			cnameField = new JTextField(classes.getName());
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.BOTH;
			gbc_textField.anchor = GridBagConstraints.WEST;
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(cnameField, gbc_textField);
			cnameField.setColumns(20);
		}
		{
			JLabel lblSetTrainer = new JLabel("Set Trainer:");
			GridBagConstraints gbc_lblSetTrainer = new GridBagConstraints();
			gbc_lblSetTrainer.insets = new Insets(0, 0, 5, 5);
			gbc_lblSetTrainer.gridx = 0;
			gbc_lblSetTrainer.gridy = 2;
			contentPanel.add(lblSetTrainer, gbc_lblSetTrainer);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 2;
			
			DefaultTableModel model = dataModel();
			table = new JTable();
			table.setModel(model);
			table.setBackground(Color.WHITE);
			
			//Creating two indexes to get direct row selection(for selected classes) by setRowSelectionInterval method
			int index0 = classes.getStaff().getStaffId() - 1;
			int index1 = index0;
			table.setRowSelectionInterval(index0, index1);
			scrollPane.setViewportView(table);
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
					public void actionPerformed(ActionEvent e) {
						if(isFilled()){
							saveData();
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "You must fill class name and chose trainer!");
						     }
						 }
						});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private DefaultTableModel dataModel(){
		Object[] colNames = {"Staff ID", "First Name", "Last name","Position"};
		DefaultTableModel dtm = new DefaultTableModel(null, colNames){
			@Override
			 public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};
		StaffService stafServ = new StaffService();
		try{
			List<Staff> staffList = new ArrayList<Staff>();
			staffList = stafServ.getAll();
			for(Staff staff : staffList){
				dtm.addRow(new Object[]{
					staff.getStaffId(),staff.getFirstName(),
					staff.getLastName(),staff.getPosition()
				});
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
		Staff staff = new Staff();
		StaffService stafServ = new StaffService();
		
		try{
		int row = table.getSelectedRow();
		String value = table.getValueAt(row, 0).toString();
		int staff_id = Integer.parseInt(value);
		staff = stafServ.findByid(staff_id);
		
		classes.setName(cnameField.getText());
		classes.setStaff(staff);
		clasServ.update(classes);
		JOptionPane.showMessageDialog(this, "Class successfully updated!");
	}catch(Exception e){
		e.printStackTrace();
		JOptionPane.showMessageDialog(this, "Can't update class!");
	}
	}
	//This method checks if all necessary fields are filled 
		private boolean isFilled(){
			if(cnameField.getText().isEmpty() || table.getSelectedRow()<0){
				return false;
			}else{
				return true;
			}
		}

}
