package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import java.awt.Color;
import entities.*;
import service.*;

public class AddMemberDialog extends JDialog{

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddMemberDialog dialog = new AddMemberDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddMemberDialog() {
		
		setSize(600, 300);
		setLocation(200, 100);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
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
			contentPanel.add(fNameField, "flowx,cell 1 1,alignx left");
			fNameField.setColumns(10);
		}
		{
			JLabel mailLabel = new JLabel("Email:");
			contentPanel.add(mailLabel, "cell 0 2,alignx trailing");
		}
		{
			mailField = new JTextField();
			contentPanel.add(mailField, "flowx,cell 1 2,alignx left");
			mailField.setColumns(20);
		}
		{
			JLabel mobileLabel = new JLabel("Mobile:");
			contentPanel.add(mobileLabel, "cell 1 2");
		}
		{
			mobileField = new JTextField();
			contentPanel.add(mobileField, "cell 1 2");
			mobileField.setColumns(17);
		}
		{
			JLabel lNameLabel = new JLabel("Last Name:");
			contentPanel.add(lNameLabel, "cell 1 1");
		}
		{
			lNameField = new JTextField();
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
			contentPanel.add(streetField, "flowx,cell 1 4,alignx left");
			streetField.setColumns(20);
		}
		{
			JLabel numLabel = new JLabel("Number:");
			contentPanel.add(numLabel, "cell 1 4");
		}
		{
			numField = new JTextField();
			contentPanel.add(numField, "cell 1 4");
			numField.setColumns(4);
		}
		{
			JLabel apmtLabel = new JLabel("Apartment:");
			contentPanel.add(apmtLabel, "cell 1 4");
		}
		{
			apmtField = new JTextField();
			contentPanel.add(apmtField, "cell 1 4");
			apmtField.setColumns(4);
		}
		{
			JLabel postLabel = new JLabel("Post code:");
			contentPanel.add(postLabel, "cell 0 5,alignx trailing");
		}
		{
			postField = new JFormattedTextField();
			postField.setColumns(8);
			contentPanel.add(postField, "flowx,cell 1 5,alignx left");
		}
		{
			JLabel cityLabel = new JLabel("City:");
			contentPanel.add(cityLabel, "cell 1 5");
		}
		{
			cityField = new JTextField();
			contentPanel.add(cityField, "cell 1 5");
			cityField.setColumns(20);
		}
		{
			JLabel payOptLabel = new JLabel("Set Payment Option:");
			contentPanel.add(payOptLabel, "cell 0 6");
		}
		{
			SpinnerModel sm = new SpinnerNumberModel(1, 1, 4, 1);
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
			Customer customer = new Customer();
			Address address = new Address();
			CustomerService cusServ = new CustomerService();
			AddressService addServ = new AddressService();
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
			customer.setPaymentOption(payOptService.findByid(id));
			customer.setJoinDate((date));
			customer.setPayOptUpdate(date);
			
			addServ.save(address);
			cusServ.save(customer);
			JOptionPane.showMessageDialog(this, "Member succesfully added!");
			
			
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: Can't add member!");
			
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
