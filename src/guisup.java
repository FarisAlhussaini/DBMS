import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;
/* Copyright © 2018. All rights reserved.
 * Any copyright violation would result in lawsuit.
 * Authors: Faris Alhussaini.
 */
public class guisup {

	private JFrame frmSupplier;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void Sup() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guisup window = new guisup();
					window.frmSupplier.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void fillComboBox(){
			try {
				myCon = Drive.dbConnector();
				String query = "Select * From mydb.product";
				PreparedStatement ps = myCon.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					comboBox.addItem(rs.getString("Product ID"));
				}
				ps.close();
				rs.close();
				
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
	}
Connection myCon = null;
private JTextField textField;
private JTextField textField_1;
private JTable table;
	/**
	 * Create the application.
	 */
	public guisup() {
		initialize();
		myCon = Drive.dbConnector();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSupplier = new JFrame();
		frmSupplier.setTitle("Supplier");
		frmSupplier.setBounds(100, 100, 840, 549);
		frmSupplier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSupplier.getContentPane().setLayout(null);
	
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "insert into mydb.supplier (`Name`, `Product_Product ID`) VALUES (?,?)";
				PreparedStatement ps = myCon.prepareStatement(query);
				ps.setString(1, textField.getText());
				ps.setInt(2, Integer.parseInt(textField_1.getText()));
				ps.execute();
				
				JOptionPane.showMessageDialog(null, "Added Successfully!");
				
				ps.close();
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
			}
		});
		btnNewButton.setBounds(57, 388, 89, 23);
		frmSupplier.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "delete from mydb.supplier where Name='"+textField.getText()+"'and`Product_Product ID`='"+textField_1.getText()+"'";
					PreparedStatement ps = myCon.prepareStatement(query);
					
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton_1.setBounds(217, 388, 89, 23);
		frmSupplier.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "Update mydb.supplier set Name='"+textField.getText()+"', `Product_Product ID`='"+textField_1.getText()+"' where Name='"+textField.getText()+"'and`Product_Product ID`='"+textField_1.getText()+"'";
				PreparedStatement ps = myCon.prepareStatement(query);
				
				ps.execute();
				
				JOptionPane.showMessageDialog(null, "Both are primary keys, cant update.");
				
				ps.close();
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
			}
		});
		btnNewButton_2.setBounds(368, 388, 89, 23);
		frmSupplier.getContentPane().add(btnNewButton_2);
		
		
		
		JButton btnNewButton_3 = new JButton("Load");
		btnNewButton_3.setBounds(518, 388, 89, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "SELECT * FROM mydb.supplier";
				PreparedStatement ps = myCon.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
			}
		});
		frmSupplier.getContentPane().add(btnNewButton_3);
		
		
		
		JLabel lblNewLabel = new JLabel("Supplier Name");
		lblNewLabel.setBounds(624, 123, 79, 14);
		frmSupplier.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product_ID");
		lblNewLabel_1.setBounds(624, 180, 79, 14);
		frmSupplier.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(713, 120, 86, 20);
		frmSupplier.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(713, 177, 86, 20);
		frmSupplier.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 119, 458, 206);
		frmSupplier.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * FROM mydb.product where `Product ID`=?";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.setString(1, (String)comboBox.getSelectedItem());
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){
						textField_1.setText(rs.getString("Product ID"));
					}
					ps.close();
					rs.close();
					
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		comboBox.setBounds(709, 23, 89, 28);
		frmSupplier.getContentPane().add(comboBox);
		
		
		fillComboBox();
	}
}
