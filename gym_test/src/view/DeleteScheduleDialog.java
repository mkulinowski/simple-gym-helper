package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Classes;
import entities.Schedule;
import entities.Staff;
import service.ClassesService;
import service.ScheduleService;
import service.StaffService;

public class DeleteScheduleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
   


	/**
	 * Create the dialog.
	 */
	public DeleteScheduleDialog(String class_date) {
		
		
		Schedule schedule = new Schedule();
		ScheduleService schedServ = new ScheduleService();
		Classes classes = new Classes();
		ClassesService clasServ = new ClassesService();
		Staff staff = new Staff();
		StaffService stafServ = new StaffService();
		
		//Getting schedule object to delete
		schedule = schedServ.findByDate(class_date);
        
		//Getting instances of entities which fields will fill information jlabels
		int class_id = schedule.getClasses().getClassId();
		classes = clasServ.findByid(class_id);
		int staff_id = classes.getStaff().getStaffId();
		staff = stafServ.findByid(staff_id);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{102, 229, 50};
		gbl_contentPanel.rowHeights = new int[]{22, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel questLabel = new JLabel("Do you really want to delete:");
			questLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			GridBagConstraints gbc_questLabel = new GridBagConstraints();
			gbc_questLabel.insets = new Insets(0, 0, 5, 0);
			gbc_questLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_questLabel.gridx = 1;
			gbc_questLabel.gridy = 0;
			contentPanel.add(questLabel, gbc_questLabel);
		}
		{
			JLabel nameLabel = new JLabel("Class Name:");
			GridBagConstraints gbc_nameLabel = new GridBagConstraints();
			gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nameLabel.gridx = 0;
			gbc_nameLabel.gridy = 2;
			contentPanel.add(nameLabel, gbc_nameLabel);
		}
		{
			JLabel classNameLabel = new JLabel("");
			classNameLabel.setText(classes.getName());
			GridBagConstraints gbc_classNameLabel = new GridBagConstraints();
			gbc_classNameLabel.insets = new Insets(0, 0, 5, 0);
			gbc_classNameLabel.gridx = 1;
			gbc_classNameLabel.gridy = 2;
			contentPanel.add(classNameLabel, gbc_classNameLabel);
		}
		{
			JLabel trainerLabel = new JLabel("Trainer Name:");
			GridBagConstraints gbc_trainerLabel = new GridBagConstraints();
			gbc_trainerLabel.insets = new Insets(0, 0, 0, 5);
			gbc_trainerLabel.gridx = 0;
			gbc_trainerLabel.gridy = 4;
			contentPanel.add(trainerLabel, gbc_trainerLabel);
		}
		{
			JLabel dateLabel = new JLabel("Date:");
			GridBagConstraints gbc_dateLabel = new GridBagConstraints();
			gbc_dateLabel.insets = new Insets(0, 0, 0, 5);
			gbc_dateLabel.gridx = 0;
			gbc_dateLabel.gridy = 6;
			contentPanel.add(dateLabel, gbc_dateLabel);
		}
		{
			JLabel tNameLabel = new JLabel("");
			tNameLabel.setText(staff.getFirstName() +""+ staff.getLastName());
			GridBagConstraints gbc_tNameLabel = new GridBagConstraints();
			gbc_tNameLabel.gridx = 1;
			gbc_tNameLabel.gridy = 4;
			contentPanel.add(tNameLabel, gbc_tNameLabel);
		}
		{
			JLabel cdateLabel = new JLabel("");
			cdateLabel.setText(schedule.getClassDate().toString());
			GridBagConstraints gbc_cdateLabel = new GridBagConstraints();
			gbc_cdateLabel.gridx = 1;
			gbc_cdateLabel.gridy = 6;
			contentPanel.add(cdateLabel, gbc_cdateLabel);
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
						
						schedServ.delete(class_date);
						dispose();
						
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

	
}
