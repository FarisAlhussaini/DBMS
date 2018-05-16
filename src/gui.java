import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

/* Copyright © 2018. All rights reserved.
 * Any copyright violation would result in lawsuit.
 * Authors: Faris Alhussaini.
 */
public class gui {

	private JFrame frmDatabaseManagment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frmDatabaseManagment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection myCon = null;
	
	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
		myCon = Drive.dbConnector();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatabaseManagment = new JFrame();
		frmDatabaseManagment.setTitle("Database Managment");
		frmDatabaseManagment.setBounds(100, 100, 840, 549);
		frmDatabaseManagment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDatabaseManagment.getContentPane().setLayout(null);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Employee
				guiemp emp = new guiemp();
				emp.Emp();
				
			}
		});
		btnEmployee.setBounds(164, 129, 106, 33);
		frmDatabaseManagment.getContentPane().add(btnEmployee);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //Customer 
				guicus c = new guicus();
				c.Cus();
			}
		});
		btnCustomer.setBounds(164, 341, 106, 33);
		frmDatabaseManagment.getContentPane().add(btnCustomer);
		
		JButton btnSupplier = new JButton("Supplier");
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Supplier
				guisup s = new guisup();
				s.Sup();
			}
		});
		btnSupplier.setBounds(563, 129, 106, 33);
		frmDatabaseManagment.getContentPane().add(btnSupplier);
		
		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    //Product
				guipro p = new guipro();
				p.Pro();
			}
		});
		btnProduct.setBounds(563, 346, 106, 33);
		frmDatabaseManagment.getContentPane().add(btnProduct);
		
		JLabel lblNewLabel = new JLabel("Welcome to Database Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(179, 43, 490, 27);
		frmDatabaseManagment.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Thank you for using our Database Management System, have a nice day!");
				System.exit(0);
			}
		});
		btnNewButton.setBounds(363, 457, 106, 33);
		frmDatabaseManagment.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Serve");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiser ser = new guiser();
				ser.Ser();
			}
		});
		btnNewButton_1.setBounds(363, 248, 106, 33);
		frmDatabaseManagment.getContentPane().add(btnNewButton_1);
		
		
	}
	}

