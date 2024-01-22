package AiLaTrieuPhuUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connection connecting()
	{
		Connection con = null;
		try {
   		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ailatrieuphu","root","1234");
   		    System.out.println("Connected");
   		} catch (SQLException e) {
   			e.printStackTrace();
			System.out.println("SQL Error Code: " + e.getErrorCode());
		}
		return con;
	}
	public static void disconnecting(Connection con) {
		try {
			if (con != null) {
				con.close();
				System.out.println("Close");
			}
		} catch (SQLException e) {
			System.out.println("SQL Error Code: " + e.getErrorCode());
		}
	}
	public static void main(String[] args) {
		Connect.connecting();
	}
	 
}