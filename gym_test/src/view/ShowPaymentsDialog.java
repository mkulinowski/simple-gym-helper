package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import entities.*;
import service.*;

public class ShowPaymentsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ShowPaymentsDialog(int member_id) {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JButton btnAdd = new JButton("Add");
				btnAdd.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						addPayment(member_id);
						DefaultTableModel newModel = dataModel(member_id);
						table.setModel(newModel);
					}
					
				});
				panel.add(btnAdd);
			}
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						String pay = JOptionPane.showInputDialog("Enter the Payment ID: ");
			            int pay_id = Integer.parseInt(pay);
						deletePayment(pay_id);
						DefaultTableModel newModel = dataModel(member_id);
						table.setModel(newModel);
					}
					
				});
				panel.add(btnDelete);
			}
		}
		
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			DefaultTableModel model = dataModel(member_id);
			table = new JTable();
			table.setModel(model);
			table.setBackground(Color.WHITE);
			scrollPane.setViewportView(table);
			
		
		
	}
	
	private DefaultTableModel dataModel(int member_id){
		Object[] colNames = {"First Name", "Last Name","Payment id", "Payment Date"};
		DefaultTableModel dtm = new DefaultTableModel(null,colNames){
			 @Override
			 public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};
		List<Payment> payList = new ArrayList<Payment>();
		PaymentService payServ = new PaymentService();
		Customer customer = new Customer();
		CustomerService custServ = new CustomerService();
		
		try{
	    customer = custServ.findByid(member_id);
		payList = payServ.getAll();
		for(Payment payment: payList){
			if(payment.getCustomer().getCustomerId() == member_id){
				dtm.addRow(new Object[]{
						customer.getFirstName(),customer.getLastName(),payment.getPayId(),
						payment.getPayDate()});
			}
		}
         }catch(Exception e){
			e.printStackTrace();
		}
	
		dtm.fireTableDataChanged();
		return dtm;
	}
	
	public void addPayment(int member_id){
		Payment payment = new Payment();
		PaymentService payServ = new PaymentService();
		CustomerService custServ = new CustomerService();
		Date date = new Date();
		payment.setPayDate(date);
		payment.setCustomer(custServ.findByid(member_id));
		payServ.save(payment);
		JOptionPane.showMessageDialog(this, "Payment succesfully added!");
	}
	public void deletePayment(int payId){
		PaymentService payServ = new PaymentService();
		payServ.delete(payId);
		JOptionPane.showMessageDialog(this, "Payment succesfully deleted!");
	}



}
