package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import entities.*;
import service.*;

import net.miginfocom.swing.MigLayout;

public class EditMemberDialog extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField mailField;
	private JTextField mobileField;
	private JTextField streetField;
	private JTextField numField;
	private JTextField apmtField;
	private JTextField cityField;
	private JFormattedTextField postField;
	private JSpinner spinner;
	private JButton okButton;
	private JButton cancelButton; 
    private Customer customer;
    private Address address;
    private CustomerService custServ;
    private AddressService addrServ;
    private int payOptId;
    private int address_id;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public EditMemberDialog(int member_id) {
		setSize(600, 300);
		setLocation(200, 100);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
	    customer = new Customer();
	    custServ = new CustomerService();
		address = new Address();
		addrServ = new AddressService();
		customer = custServ.findByid(member_id);
		address_id = customer.getAddress().getAddressId();
		address = addrServ.findByid(address_id);
		{
			JLabel lblPersonal = new JLabel("PERSONAL:");
			contentPanel.add(lblPersonal, "cell 0 0");
		}
		{
			JLabel fNameLabel = new JLabel("First Name:");
			contentPanel.add(fNameLabel, "cell 0 1,alignx trailing");
		}
		{
			fNameField = new JTextField();
			fNameField.setText(customer.getFirstName());
			contentPanel.add(fNameField, "flowx,cell 1 1,alignx left");
			fNameField.setColumns(10);
		}
		{
			JLabel mailLabel = new JLabel("Email:");
			contentPanel.add(mailLabel, "cell 0 2,alignx trailing");
		}
		{
			mailField = new JTextField();
			mailField.setText(customer.getEmail());
			contentPanel.add(mailField, "flowx,cell 1 2,alignx left");
			mailField.setColumns(20);
		}
		{
			JLabel mobileLabel = new JLabel("Mobile:");
			contentPanel.add(mobileLabel, "cell 1 2");
		}
		{
			mobileField = new JTextField();
			mobileField.setText(customer.getMobilePhone());
			contentPanel.add(mobileField, "cell 1 2");
			mobileField.setColumns(17);
		}
		{
			JLabel lNameLabel = new JLabel("Last Name:");
			contentPanel.add(lNameLabel, "cell 1 1");
		}
		{
			lNameField = new JTextField();
			lNameField.setText(customer.getLastName());
			contentPanel.add(lNameField, "cell 1 1");
			lNameField.setColumns(25);
		}
		{
			JLabel addressLabel = new JLabel("ADDRESS:");
			contentPanel.add(addressLabel, "cell 0 3");
		}
		{
			JLabel streetLabel = new JLabel("Street:");
			contentPanel.add(streetLabel, "cell 0 4,alignx trailing");
		}
		{
			streetField = new JTextField();
			streetField.setText(address.getStreet());
			contentPanel.add(streetField, "flowx,cell 1 4,alignx left");
			streetField.setColumns(20);
		}
		{
			JLabel numLabel = new JLabel("Number:");
			contentPanel.add(numLabel, "cell 1 4");
		}
		{
			numField = new JTextField();
			numField.setText(address.getNumber());
			contentPanel.add(numField, "cell 1 4");
			numField.setColumns(4);
		}
		{
			JLabel apmtLabel = new JLabel("Apartment:");
			contentPanel.add(apmtLabel, "cell 1 4");
		}
		{
			apmtField = new JTextField();
			apmtField.setText(address.getApartment());
			contentPanel.add(apmtField, "cell 1 4");
			apmtField.setColumns(4);
		}
		{
			JLabel postLabel = new JLabel("Post code:");
			contentPanel.add(postLabel, "cell 0 5,alignx trailing");
		}
		{
			postField = new JFormattedTextField();
			postField.setText(address.getPostCode());
			postField.setColumns(8);
			contentPanel.add(postField, "flowx,cell 1 5,alignx left");
		}
		{
			JLabel cityLabel = new JLabel("City:");
			contentPanel.add(cityLabel, "cell 1 5");
		}
		{
			cityField = new JTextField();
			cityField.setText(address.getCity());
			contentPanel.add(cityField, "cell 1 5");
			cityField.setColumns(20);
		}
		{
			JLabel payOptLabel = new JLabel("Set Payment Option:");
			contentPanel.add(payOptLabel, "cell 0 6");
		}
		{
			payOptId = customer.getPaymentOption().getId();
			SpinnerModel sm = new SpinnerNumberModel(payOptId, 1, 4, 1);
			spinner = new JSpinner(sm);
			
			contentPanel.add(spinner, "flowx,cell 1 6");
		}
		{
			JLabel lblNewLabel = new JLabel("1 - Trial  2 - Full  3 - One time  4 - Student");
			contentPanel.add(lblNewLabel, "cell 1 6");
		}
	
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(isFilled()){
							JOptionPane.showMessageDialog(null, "Fill all text fields!");
						}else{
						saveData();
						dispose();
					}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
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
	public void saveData(){
		try{
	
		PaymentOptionService payOptService = new PaymentOptionService();
	    int id = (int) spinner.getValue();
	    
	    Date date = new Date();
		address.setStreet(streetField.getText());
		address.setNumber(numField.getText());
		address.setApartment(apmtField.getText());
		address.setPostCode(postField.getText());
		address.setCity(cityField.getText());
		customer.setFirstName(fNameField.getText());
		customer.setLastName(lNameField.getText());
		customer.setEmail(mailField.getText());
		customer.setMobilePhone(mobileField.getText());
		customer.setAddress(address);
		
		if(payOptId != id){
		customer.setPaymentOption(payOptService.findByid(id));
	    customer.setPayOptUpdate(date);
		}
		
		addrServ.update(address);
		custServ.update(customer);
		JOptionPane.showMessageDialog(this, "Member succesfully updated!");
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: Can't update member!");
		}
	}
	private boolean isFilled(){
   	 if(fNameField.getText().isEmpty() || lNameField.getText().isEmpty() ||
   			 mailField.getText().isEmpty() || mobileField.getText().isEmpty() ||
   			 streetField.getText().isEmpty() || numField.getText().isEmpty() ||
   			 apmtField.getText().isEmpty() || cityField.getText().isEmpty() ||
   			 postField.getText().isEmpty()){
   		 return false;
   	 }else{
   		 return true;
   	 }
    }

	

}
