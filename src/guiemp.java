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
public class guiemp {

	private JFrame frmEmployee;

	/**
	 * Launch the application.
	 */
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiemp window = new guiemp();
					window.frmEmployee.setVisible(true);
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
	private JTable table;
	/**
	 * Create the application.
	 */
	public guiemp() {
		initialize();
		myCon = Drive.dbConnector();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployee = new JFrame();
		frmEmployee.setTitle("Employee");
		frmEmployee.setBounds(100, 100, 840, 549);
		frmEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEmployee.getContentPane().setLayout(null);
	
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into mydb.employee (ID, Name, Salary) VALUES (?,?,?)";
					PreparedStatement ps = myCon.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(textField.getText()));
					if(!(textField_1.getText().isEmpty())){
						ps.setString(2, textField_1.getText());
					}else{
							ps.setNull(2, Types.VARCHAR);}
					if(!(textField_2.getText().isEmpty())){
						ps.setInt(3, Integer.parseInt(textField_2.getText()));
					}else{
							ps.setNull(3, Types.INTEGER);
					}
					ps.execute();
					
					JOptionPane.showMessageDialog(null, "Added Successfully!");
					
					ps.close();
					} catch (Exception es){
						JOptionPane.showMessageDialog(null, es.toString());
					}
			}
		});
		btnNewButton.setBounds(35, 378, 89, 23);
		frmEmployee.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "delete from mydb.employee where ID='"+textField.getText()+"'";
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
		frmEmployee.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update"); 
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "Update mydb.employee set ID='"+textField.getText()+"', Name='"+textField_1.getText()+"',Salary='"+textField_2.getText()+"' where ID='"+textField.getText()+"'";
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
		frmEmployee.getContentPane().add(btnNewButton_2);
		
		
		
		JButton btnNewButton_3 = new JButton("Load");
		btnNewButton_3.setBounds(461, 378, 89, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "SELECT * FROM mydb.employee";
				PreparedStatement ps = myCon.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				} catch (Exception es){
					JOptionPane.showMessageDialog(null, es.toString());
				}
			}
		});
		frmEmployee.getContentPane().add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(712, 120, 86, 20);
		frmEmployee.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(712, 177, 86, 20);
		frmEmployee.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(712, 240, 86, 20);
		frmEmployee.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(672, 123, 46, 14);
		frmEmployee.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(672, 180, 46, 14);
		frmEmployee.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Salary");
		lblNewLabel_2.setBounds(672, 243, 46, 14);
		frmEmployee.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 119, 505, 206);
		frmEmployee.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
	}
}
