package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;

public class PaymentOptionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4013892071070216155L;

	/**
	 * Create the panel.
	 */
	public PaymentOptionPanel() {
		setLayout(new MigLayout("", "[][][][][][][][]", "[][][][][][][][]"));
		
		JLabel payOptLabel = new JLabel("Payment Options");
		payOptLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(payOptLabel, "cell 5 2");
		
		JLabel lblTrial = new JLabel("Trial");
		add(lblTrial, "cell 3 4");
		
		JLabel lblPerMonth_3 = new JLabel("49.99 per month");
		add(lblPerMonth_3, "cell 5 4");
		
		JLabel lblMonths = new JLabel("3 months");
		add(lblMonths, "cell 7 4");
		
		JLabel lblNewLabel = new JLabel("Full");
		add(lblNewLabel, "cell 3 5");
		
		JLabel lblPerMonth_2 = new JLabel("79.99 per month");
		add(lblPerMonth_2, "cell 5 5");
		
		JLabel lblMonths_1 = new JLabel("12 months");
		add(lblMonths_1, "cell 7 5");
		
		JLabel lblOneTime = new JLabel("One time");
		add(lblOneTime, "cell 3 6");
		
		JLabel lblPerMonth_1 = new JLabel("14.99 per day");
		add(lblPerMonth_1, "cell 5 6");
		
		JLabel lblDay = new JLabel("1 day");
		add(lblDay, "cell 7 6");
		
		JLabel lblStudent = new JLabel("Student");
		add(lblStudent, "cell 3 7");
		
		JLabel lblPerMonth = new JLabel("59.99 per month");
		add(lblPerMonth, "cell 5 7");
		
		JLabel lblMonths_2 = new JLabel("12 months");
		add(lblMonths_2, "cell 7 7");

	}

}
