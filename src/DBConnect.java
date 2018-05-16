public class DBConnect {

	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e){
			System.out.println("Error: " + e); 
		}
		
	}
}
