package papermaster;

import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlConnector {

	

	public static Connection doConnect()
	{
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/newspaper","root","");
			System.out.println("connected bdhaiii......");
		}
		catch(Exception exp){
			System.out.println(exp);
			
		}
		return con;
	}
	public static void main(String args[])
	
	{
		doConnect();
		
	}
	
	
	
}


