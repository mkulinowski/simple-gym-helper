package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Staff;
import service.StaffService;

public class DeleteStaffDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	


	/**
	 * Create the dialog.
	 */
	public DeleteStaffDialog(int staff_id) {
		setBounds(100, 100, 450, 300);
		
		Staff staff = new Staff();
		StaffService stafServ =  new StaffService();
		staff = stafServ.findByid(staff_id);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{102, 229, 0};
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
			JLabel nameLabel = new JLabel("First Name:");
			GridBagConstraints gbc_nameLabel = new GridBagConstraints();
			gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nameLabel.gridx = 0;
			gbc_nameLabel.gridy = 2;
			contentPanel.add(nameLabel, gbc_nameLabel);
		}
		{
			JLabel firstNameLabel = new JLabel("");
			firstNameLabel.setText(staff.getFirstName());
			GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
			gbc_firstNameLabel.insets = new Insets(0, 0, 5, 0);
			gbc_firstNameLabel.gridx = 1;
			gbc_firstNameLabel.gridy = 2;
			contentPanel.add(firstNameLabel, gbc_firstNameLabel);
		}
		{
			JLabel lNameLabel = new JLabel("Last Name:");
			GridBagConstraints gbc_lNameLabel = new GridBagConstraints();
			gbc_lNameLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lNameLabel.gridx = 0;
			gbc_lNameLabel.gridy = 4;
			contentPanel.add(lNameLabel, gbc_lNameLabel);
		}
		{
			JLabel lastNameLabel = new JLabel("");
			lastNameLabel.setText(staff.getLastName());
			GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
			gbc_lastNameLabel.gridx = 1;
			gbc_lastNameLabel.gridy = 4;
			contentPanel.add(lastNameLabel, gbc_lastNameLabel);
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
						stafServ.delete(staff_id);
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
