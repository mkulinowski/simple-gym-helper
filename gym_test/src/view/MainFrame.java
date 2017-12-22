package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class MainFrame{

	private JFrame frame;
	private JButton exitButton;
	private JButton membersButton;
	private JButton classesButton;
	private JButton payOptButton;
	private JButton crewButton;
	private Image membersImage;
	private JMenuItem mntmExit;
	private JLabel picPanel;
	private JPanel panel;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gym Helper");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[grow]"));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(null);
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		frame.getContentPane().add(toolBar, "cell 0 0");
		
		membersButton = new JButton();
		membersButton.setBorder(new LineBorder(Color.WHITE));
		membersButton.setBackground(null);
		toolBar.add(membersButton);
		try {
			membersImage = ImageIO.read(getClass().getResource("/res/fit_mem.png"));
			membersButton.setIcon(new ImageIcon(membersImage));
			membersButton.setFocusPainted(false);
			membersButton.setContentAreaFilled(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		membersButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				
					membersButton.setBorder(new LineBorder(Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
					membersButton.setBorder(new LineBorder(Color.WHITE));
			}
		});
		membersButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addMembersPanel();
				
			}
			
		});
		
		JSeparator separator_2 = new JSeparator();
		toolBar.add(separator_2);
				
		
		classesButton = new JButton();
		classesButton.setBorder(new LineBorder(Color.WHITE));;
		classesButton.setBackground(null);
		toolBar.add(classesButton);
		try {
			Image classesImage = ImageIO.read(getClass().getResource("/res/fit_classes.jpg"));
			classesButton.setIcon(new ImageIcon(classesImage));
			classesButton.setFocusPainted(false);
			classesButton.setContentAreaFilled(false);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		classesButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				
					classesButton.setBorder(new LineBorder(Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
					classesButton.setBorder(new LineBorder(Color.WHITE));
			}
		});
		classesButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addClassesPanel();
				
			}
			
		});
		
		JSeparator separator_3 = new JSeparator();
		toolBar.add(separator_3);
		
	    payOptButton = new JButton();
		payOptButton.setBorder(new LineBorder(Color.WHITE));;
		payOptButton.setBackground(null);
		toolBar.add(payOptButton);
		try {
			Image payOptImage = ImageIO.read(getClass().getResource("/res/fit_pay_opt.png"));
			payOptButton.setIcon(new ImageIcon(payOptImage));
			payOptButton.setFocusPainted(false);
			payOptButton.setContentAreaFilled(false);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		payOptButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				
					payOptButton.setBorder(new LineBorder(Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
					payOptButton.setBorder(new LineBorder(Color.WHITE));
			}
		});
		payOptButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addPayOptPanel();
				
			}
			
		});
		
		JSeparator separator_4 = new JSeparator();
		toolBar.add(separator_4);
		
		crewButton = new JButton();
		crewButton.setBorder(new LineBorder(Color.WHITE));;
		crewButton.setBackground(null);
		toolBar.add(crewButton);
		try {
			Image crewImage = ImageIO.read(getClass().getResource("/res/fit_cre.png"));
			crewButton.setIcon(new ImageIcon(crewImage));
			crewButton.setFocusPainted(false);
			crewButton.setContentAreaFilled(false);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crewButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				
					crewButton.setBorder(new LineBorder(Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
					crewButton.setBorder(new LineBorder(Color.WHITE));
			}
		});
		crewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addStaffPanel();
				
			}
			
		});
		
		JSeparator separator_5 = new JSeparator();
		toolBar.add(separator_5);
		
		exitButton = new JButton();
		exitButton.setBorder(new LineBorder(Color.WHITE));;
		exitButton.setBackground(null);
		toolBar.add(exitButton);
		try {
			Image exitImage = ImageIO.read(getClass().getResource("/res/fit_exit.png"));
			exitButton.setIcon(new ImageIcon(exitImage));
			exitButton.setFocusPainted(false);
			exitButton.setContentAreaFilled(false);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				
				exitButton.setBorder(new LineBorder(Color.BLACK));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
					exitButton.setBorder(new LineBorder(Color.WHITE));
			}
		});
		exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				
			}
			
		});
		
		
	    try {
			Image panelImage = ImageIO.read(getClass().getResource("/res/fit_han.jpg"));
			picPanel = new JLabel(new ImageIcon(panelImage));
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
		
			panel.add(picPanel);
			frame.getContentPane().add(panel, "cell 1 0,grow");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnApp = new JMenu("App");
		menuBar.add(mnApp);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
			
		});
		mnApp.add(mntmExit);
	}

	private void addMembersPanel(){
		
		panel.removeAll();
		JPanel membersPanel = new MembersPanel();
		panel.add(membersPanel);
		panel.validate();
		panel.repaint();
	}
	private void addClassesPanel(){
		panel.removeAll();
		JPanel classesPanel = new ClassesPanel();
		panel.add(classesPanel);
		panel.validate();
		panel.repaint();
	}
	private void addPayOptPanel(){
		panel.removeAll();
		JPanel payOptPanel = new PaymentOptionPanel();
		panel.add(payOptPanel);
		panel.validate();
		panel.repaint();
	}
	private void addStaffPanel(){
		panel.removeAll();
		JPanel staffPanel = new StaffPanel();
		panel.add(staffPanel);
		panel.validate();
		panel.repaint();
	}
	
	

	

}
