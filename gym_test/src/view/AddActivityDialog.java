package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Activities;
import entities.Classes;
import entities.Customer;
import entities.Schedule;
import net.miginfocom.swing.MigLayout;
import service.ActivitiesService;
import service.ClassesService;
import service.CustomerService;
import service.ScheduleService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddActivityDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;


	/**
	 *Dialog reads and display data of chosen activity and save activity for given customer id
	 */
	public AddActivityDialog(String class_date) {
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][][grow]", "[][][][]"));
		Schedule schedule = new Schedule();
		ScheduleService schedServ = new ScheduleService();
		schedule = schedServ.findByDate(class_date);
		int class_id = schedule.getClasses().getClassId();
		Classes classes = new Classes();
		ClassesService clasServ = new ClassesService();
		classes = clasServ.findByid(class_id);
		{
			JLabel cDateLabel = new JLabel("Class date:");
			contentPanel.add(cDateLabel, "cell 2 1,alignx right");
		}
		{
			JLabel cDateTextLabel = new JLabel(class_date);
			contentPanel.add(cDateTextLabel, "cell 3 1");
		}
		{
			JLabel cNameLabel = new JLabel("Class name:");
			contentPanel.add(cNameLabel, "cell 2 2,alignx right");
		}
		{
			JLabel cNameTextLabel = new JLabel(classes.getName());
			contentPanel.add(cNameTextLabel, "cell 3 2");
		}
		{
			JLabel memCardLabel = new JLabel("Enter Member Card ID:");
			contentPanel.add(memCardLabel, "cell 2 3,alignx trailing");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 3 3,alignx left");
			textField.setColumns(20);
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
							saveData(class_date);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Enter member card ID!");
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						
					}
					
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void saveData(String class_date){
		//Creating objects
		Schedule schedule = new Schedule();
		ScheduleService schedServ = new ScheduleService();
		Activities activity = new Activities();
		ActivitiesService actServ = new ActivitiesService();
		Customer customer = new Customer();
		CustomerService custServ = new CustomerService();
		Classes classes = new Classes();
		ClassesService clasServ = new ClassesService();
		
		schedule = schedServ.findByDate(class_date);
		int classes_id = schedule.getClasses().getClassId();
		classes = clasServ.findByid(classes_id);
		String cust_id = textField.getText();
		int customer_id = Integer.parseInt(cust_id);
		customer = custServ.findByid(customer_id);
		try{
			activity.setActivityDate(schedule.getClassDate());
			activity.setClasses(classes);
			activity.setCustomer(customer);
			actServ.save(activity);
			JOptionPane.showMessageDialog(this, "Activity successfully added!");
			
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Can't add member!");
		}
	}
	private boolean isFilled(){
		if(textField.getText().isEmpty()){
			return false;
		}else{
		return true;
		}
	}
}
