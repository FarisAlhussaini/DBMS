import java.sql.*;
import javax.swing.*;
	
	public class Drive {
			Connection myCon = null;
			public static Connection dbConnector(){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection myCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?autoReconnect=true&useSSL=false","root","root");
				return myCon;
				
			
				
				
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
			}
		}

	

