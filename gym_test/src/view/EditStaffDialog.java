package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Address;
import entities.Staff;
import net.miginfocom.swing.MigLayout;
import service.AddressService;
import service.StaffService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Date;

import javax.swing.BoxLayout;
import java.awt.Color;

public class EditStaffDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField emailField;
	private JTextField mobileField;
	private JTextField positionField;
	private JTextField salaryField;
	private JTextField streetField;
	private JTextField numField;
	private JTextField aptmtField;
	private JTextField postField;
	private JTextField city;
	private Staff staff;
	private StaffService stafServ;
	private Address address;
	private AddressService addServ;



	/**
	 * Create the dialog.
	 */
	public EditStaffDialog(int staff_id) {
		setSize(600, 300);
		setLocation(200, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[][][][][][][]"));
		
		staff = new Staff();
		stafServ = new StaffService();
		address = new Address();
	    addServ = new AddressService();
		staff = stafServ.findByid(staff_id);
		int address_id = staff.getAddress().getAddressId();
		address = addServ.findByid(address_id);
		{
			JLabel lblPersonal = new JLabel("PERSONAL:");
			contentPanel.add(lblPersonal, "cell 0 0");
		}
		{
			JLabel lblFirstName = new JLabel("First name:");
			contentPanel.add(lblFirstName, "cell 1 1,alignx trailing");
		}
		{
			fNameField = new JTextField(staff.getFirstName());
			contentPanel.add(fNameField, "flowx,cell 2 1,alignx left");
			fNameField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name:");
			contentPanel.add(lblLastName, "cell 2 1");
		}
		{
			lNameField = new JTextField(staff.getLastName());
			contentPanel.add(lNameField, "cell 2 1");
			lNameField.setColumns(25);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			contentPanel.add(lblEmail, "cell 1 2,alignx trailing");
		}
		{
			emailField = new JTextField(staff.getEmail());
			contentPanel.add(emailField, "flowx,cell 2 2,alignx left");
			emailField.setColumns(20);
		}
		{
			JLabel lblMobile = new JLabel("Mobile:");
			contentPanel.add(lblMobile, "cell 2 2");
		}
		{
			mobileField = new JTextField(staff.getMobilePhone());
			contentPanel.add(mobileField, "cell 2 2");
			mobileField.setColumns(17);
		}
		{
			JLabel lblPosition = new JLabel("Position:");
			contentPanel.add(lblPosition, "cell 1 3,alignx trailing");
		}
		{
			positionField = new JTextField(staff.getPosition());
			contentPanel.add(positionField, "flowx,cell 2 3,alignx left");
			positionField.setColumns(15);
		}
		{
			JLabel lblSalary = new JLabel("Salary:");
			contentPanel.add(lblSalary, "cell 2 3");
		}
		{
			salaryField = new JTextField(staff.getSalary().toString());
			contentPanel.add(salaryField, "cell 2 3");
			salaryField.setColumns(10);
		}
		{
			JLabel lblAddress = new JLabel("ADDRESS:");
			contentPanel.add(lblAddress, "cell 0 4");
		}
		{
			JLabel lblStreet = new JLabel("Street:");
			contentPanel.add(lblStreet, "cell 1 5,alignx trailing");
		}
		{
			streetField = new JTextField(address.getStreet());
			contentPanel.add(streetField, "flowx,cell 2 5,alignx left");
			streetField.setColumns(20);
		}
		{
			JLabel lblNumber = new JLabel("Number:");
			contentPanel.add(lblNumber, "cell 2 5");
		}
		{
			numField = new JTextField(address.getNumber());
			contentPanel.add(numField, "cell 2 5");
			numField.setColumns(4);
		}
		{
			JLabel lblApartment = new JLabel("Apartment:");
			contentPanel.add(lblApartment, "cell 2 5");
		}
		{
			aptmtField = new JTextField(address.getApartment());
			contentPanel.add(aptmtField, "cell 2 5");
			aptmtField.setColumns(4);
		}
		{
			JLabel lblPostCode = new JLabel("Post code:");
			contentPanel.add(lblPostCode, "cell 1 6,alignx trailing");
		}
		{
			postField = new JTextField(address.getPostCode());
			contentPanel.add(postField, "flowx,cell 2 6,alignx left");
			postField.setColumns(8);
		}
		{
			JLabel lblCity = new JLabel("City:");
			contentPanel.add(lblCity, "cell 2 6");
		}
		{
			city = new JTextField(address.getCity());
			contentPanel.add(city, "cell 2 6");
			city.setColumns(20);
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
						if(isFilled() == true){
							saveData();
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "You must fill text fields with (*)!");
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
	private void saveData(){
		Date date = new Date();
		try{
			address.setStreet(streetField.getText());
			address.setNumber(numField.getText());
			address.setApartment(aptmtField.getText());
			address.setPostCode(postField.getText());
			address.setCity(city.getText());
			staff.setFirstName(fNameField.getText());
			staff.setLastName(lNameField.getText());
			staff.setEmail(emailField.getText());
			staff.setMobilePhone(mobileField.getText());
			staff.setPosition(positionField.getText());
			String salary = salaryField.getText();
			String filtered_salary = salary.replaceAll(",", "");
			BigDecimal bdSalary = new BigDecimal(filtered_salary);
			staff.setSalary(bdSalary);
			staff.setLastUpdate(date);
			staff.setAddress(address);
			
			addServ.update(address);
			stafServ.update(staff);
			JOptionPane.showMessageDialog(this, "Staff updated succesfully!");
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Can't update Staff!");
		}
		
	}
	private boolean isFilled(){
		if(fNameField.getText().isEmpty() || lNameField.getText().isEmpty()
				|| mobileField.getText().isEmpty() || streetField.getText().isEmpty()
				|| numField.getText().isEmpty() || postField.getText().isEmpty()
				|| city.getText().isEmpty()){
			return false;
		}else{
		return true;
		}
	}

}
