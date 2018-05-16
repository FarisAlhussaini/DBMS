import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
/* Copyright © 2018. All rights reserved.
 * Any copyright violation would result in lawsuit.
 * Authors: Faris Alhussaini.
 */

import net.proteanit.sql.DbUtils;
public class guipro {

	private JFrame frmProductt;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void Pro() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guipro window = new guipro();
					window.frmProductt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@SuppressWarnings("unchecked")
	public void fillComboBox(){
		try {
			myCon = Drive.dbConnector();
			String query = "Select * From mydb.customer";
			PreparedStatement ps = myCon.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				comboBox.addItem(rs.getString("Phone Number"));
			}
			ps.close();
			
			} catch (Exception es){
				JOptionPane.showMessageDialog(null, es.toString());
			}
}
	Connection myCon = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	/**
	 * Create the application.
	 */
	public guipro() {
		initialize();
		myCon = Drive.dbConnector();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProductt = new JFrame();
		frmProductt.setTitle("Product");
		frmProductt.setBounds(100, 100, 840, 549);
		frmProductt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProductt.getContentPane().setLayout(null);
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into mydb.product (`Product ID`, `Brand`, `Color`, `Size`, `Customer_Phone Number`, `Type`) VALUES (?,?,?,?,?,?)";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(textField.getText()));
					if(!(textField_1.getText().isEmpty())){
						ps.setString(2, textField_1.getText());
					}else{
							ps.setNull(2, Types.VARCHAR);
					}if(!(textField_2.getText().isEmpty())){
						ps.setString(3, textField_2.getText());
					}	else{
							ps.setNull(3, Types.VARCHAR);
					}if(!(textField_3.getText().isEmpty())){
						ps.setFloat(4, Float.parseFloat(textField_3.getText()));
					}else{
							ps.setNull(4, Types.FLOAT);
					}if(!(textField_4.getText().isEmpty())){
						ps.setInt(5, Integer.parseInt(textField_4.getText()));
					}else{
							ps.setNull(5, Types.INTEGER);
					}
					if(!(textField_5.getText().isEmpty())){
						ps.setString(6, textField_5.getText());
					}else{
							ps.setNull(6, Types.VARCHAR);
					}
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Added Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton.setBounds(59, 357, 89, 23);
		frmProductt.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "delete from mydb.product where `Product ID`='"+textField.getText()+"'";
					PreparedStatement ps = myCon.prepareStatement(query);
					
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton_1.setBounds(191, 357, 89, 23);
		frmProductt.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update mydb.product set `Product ID`='"+textField.getText()+"', Brand='"+textField_1.getText()+"', Color='"+textField_2.getText()+"', Size='"+textField_3.getText()+"',`Customer_Phone Number`='"+textField_4.getText()+"',Type='"+textField_5.getText()+"' where `Product ID`='"+textField.getText()+"'";
					PreparedStatement ps = myCon.prepareStatement(query);
					
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton_2.setBounds(331, 357, 89, 23);
		frmProductt.getContentPane().add(btnNewButton_2);
		
		
		
		JButton btnNewButton_3 = new JButton("Load");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "SELECT * FROM mydb.product";
				PreparedStatement ps = myCon.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
			}
		});
		btnNewButton_3.setBounds(480, 357, 89, 23);
		frmProductt.getContentPane().add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(707, 46, 86, 20);
		frmProductt.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(707, 94, 86, 20);
		frmProductt.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(707, 142, 86, 20);
		frmProductt.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(707, 188, 86, 20);
		frmProductt.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(707, 236, 86, 20);
		frmProductt.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(707, 280, 86, 20);
		frmProductt.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setBounds(618, 49, 60, 14);
		frmProductt.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Brand");
		lblNewLabel_1.setBounds(618, 97, 60, 14);
		frmProductt.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Color");
		lblNewLabel_2.setBounds(618, 145, 60, 14);
		frmProductt.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Size");
		lblNewLabel_3.setBounds(617, 191, 61, 14);
		frmProductt.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Customer Phone#");
		lblNewLabel_4.setBounds(592, 239, 105, 14);
		frmProductt.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Type");
		lblNewLabel_5.setBounds(617, 283, 61, 14);
		frmProductt.getContentPane().add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 71, 422, 226);
		frmProductt.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * FROM mydb.customer where `Phone Number`=?";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.setString(1, (String)comboBox.getSelectedItem());
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){
						textField_4.setText(rs.getString("Phone Number"));
					}
					ps.close();
					rs.close();
					
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		comboBox.setBounds(704, 358, 89, 28);
		frmProductt.getContentPane().add(comboBox);
		fillComboBox();
	}
}
