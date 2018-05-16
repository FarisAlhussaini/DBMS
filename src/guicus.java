import java.awt.EventQueue;
/* Copyright © 2018. All rights reserved.
 * Any copyright violation would result in lawsuit.
 * Authors: Faris Alhussaini.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;
import java.sql.Types;
public class guicus {

	private JFrame frmCustomer;

	/**
	 * Launch the application.
	 */
	public static void Cus() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guicus window = new guicus();
					window.frmCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection myCon = null;
private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;
private JTextField textField_3;
private JTable table;
	/**
	 * Create the application.
	 */
	public guicus() {
		initialize();
		myCon = Drive.dbConnector();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCustomer = new JFrame();
		frmCustomer.setTitle("Customer");
		frmCustomer.setBounds(100, 100, 840, 549);
		frmCustomer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCustomer.getContentPane().setLayout(null);
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into mydb.customer (`Phone Number`, `Name`, `Age`, `Gender`) VALUES (?,?,?,?)";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(textField.getText()));
					if(!(textField_1.getText().isEmpty())){
						ps.setString(2, textField_1.getText());
					}else{
							ps.setNull(2, Types.VARCHAR);
					}if(!(textField_2.getText().isEmpty())){
						ps.setInt(3, Integer.parseInt(textField_2.getText()));
					}	else{
							ps.setNull(3, Types.INTEGER);
					}if(!(textField_3.getText().isEmpty())){
						ps.setString(4, textField_3.getText());
					}else{
							ps.setNull(4, Types.VARCHAR);
					}
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Added Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton.setBounds(64, 383, 89, 23);
		frmCustomer.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "delete from mydb.customer where `Phone Number`='"+textField.getText()+"'";
					PreparedStatement ps = myCon.prepareStatement(query);
					
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton_1.setBounds(201, 383, 89, 23);
		frmCustomer.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update mydb.customer set `Phone Number`='"+Integer.parseInt(textField.getText())+"', Name='"+textField_1.getText()+"',Age='"+Integer.parseInt(textField_2.getText())+"', Gender='"+textField_3.getText()+"' where `Phone Number`='"+Integer.parseInt(textField.getText())+"'";
					PreparedStatement ps = myCon.prepareStatement(query);
					
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton_2.setBounds(350, 383, 89, 23);
		frmCustomer.getContentPane().add(btnNewButton_2);
		
		
		
		JButton btnNewButton_3 = new JButton("Load");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "SELECT * FROM mydb.customer";
				PreparedStatement ps = myCon.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
			}
		});
		btnNewButton_3.setBounds(496, 383, 89, 23);
		frmCustomer.getContentPane().add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(728, 122, 86, 20);
		frmCustomer.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(728, 179, 86, 20);
		frmCustomer.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(728, 242, 86, 20);
		frmCustomer.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(728, 303, 86, 20);
		frmCustomer.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Phone#");
		lblNewLabel.setBounds(655, 125, 46, 14);
		frmCustomer.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(655, 182, 46, 14);
		frmCustomer.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Age");
		lblNewLabel_2.setBounds(655, 245, 46, 14);
		frmCustomer.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setBounds(655, 306, 46, 14);
		frmCustomer.getContentPane().add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 126, 443, 199);
		frmCustomer.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
