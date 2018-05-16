import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.sql.*;
/* Copyright © 2018. All rights reserved.
 * Any copyright violation would result in lawsuit.
 * Authors: Faris Alhussaini.
 */
public class guiser {

	private JFrame frame;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	/**
	 * Launch the application.
	 */
	public static void Ser() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiser window = new guiser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection myCon = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	public void fillComboBox(){
		try {
			myCon = Drive.dbConnector();
			String query = "select * FROM mydb.employee";
			PreparedStatement ps = myCon.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				comboBox.addItem(rs.getString("ID"));
			}
			ps.close();
			rs.close();
			
			} catch (Exception es){
				JOptionPane.showMessageDialog(null, es.toString());
			}	
	}
	public void fillComboBox_1(){
		try {
			myCon = Drive.dbConnector();
			String query = "SELECT * FROM mydb.customer";
			PreparedStatement ps = myCon.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				comboBox_1.addItem(rs.getString("Phone Number"));
			}
			ps.close();
			rs.close();
			
			} catch (Exception es){
				JOptionPane.showMessageDialog(null, es.toString());
			}	
	}
	/**
	 * Create the application.
	 */
	public guiser() {
		initialize();
		myCon = Drive.dbConnector();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Serve");
		frame.setBounds(100, 100, 840, 549);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		JButton btnNewButton = new JButton("Add");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into mydb.employee_serves_customer (Employee_ID, `Customer_Phone Number`) VALUES (?,?)";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(textField.getText()));
					ps.setInt(2, Integer.parseInt(textField_1.getText()));
					
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Added Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton.setBounds(35, 378, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "delete from mydb.employee_serves_customer where Employee_ID='"+Integer.parseInt(textField.getText())+"'and`Customer_Phone Number`='"+Integer.parseInt(textField_1.getText())+"'";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton_1.setBounds(178, 378, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "Update mydb.employee_serves_customer set Employee_ID='"+Integer.parseInt(textField.getText())+"', `Customer_Phone Number`='"+Integer.parseInt(textField_1.getText())+"'";
				PreparedStatement ps = myCon.prepareStatement(query);
				ps.execute();
				
				JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
				
				ps.close();
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
			}
		});
		btnNewButton_2.setBounds(317, 378, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("Load");
		btnNewButton_3.setBounds(461, 378, 89, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "SELECT * FROM mydb.employee_serves_customer";
				PreparedStatement ps = myCon.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
			}
		});
		frame.getContentPane().add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(712, 120, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(712, 177, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 119, 458, 206);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(624, 123, 78, 14);
		frame.getContentPane().add(lblEmployeeId);
		
		JLabel lblCustomerPhone = new JLabel("Customer Phone#");
		lblCustomerPhone.setBounds(605, 180, 97, 14);
		frame.getContentPane().add(lblCustomerPhone);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * FROM mydb.employee where ID=?";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.setString(1, (String)comboBox.getSelectedItem());
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){
						textField.setText(rs.getString("ID"));
					}
					ps.close();
					rs.close();
					
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		comboBox.setBounds(709, 23, 89, 20);
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * FROM mydb.customer where `Phone Number`=?";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.setString(1, (String)comboBox_1.getSelectedItem());
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){
						textField_1.setText(rs.getString("Phone Number"));
					}
					ps.close();
					rs.close();
					
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		comboBox_1.setBounds(709, 65, 89, 20);
		frame.getContentPane().add(comboBox_1);
		
		fillComboBox();
		fillComboBox_1();
	}
}
