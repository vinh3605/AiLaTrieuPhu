package AiLaTrieuPhuUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HienThiUI  {
	
	
	public static void main(String []args) {
		//kết nối sql
		
		String url="jdbc:mysql://localhost:3306/ailatrieuphu";
		String  user="root";
		String  password="1234";
		String driveName="com.mysql.cj.jdbc.Driver";
		Statement stmt = null;
		 

		try {
			Class.forName(driveName);
			try {
				Connection myConnect= DriverManager.getConnection(url, user, password);
				stmt= myConnect.createStatement();
	//			String sql="select * from questions";
	//		ResultSet resultSet= stmt.executeQuery(sql);
	//		int i=1;
	//		while( resultSet.next()) {
	//				System.out.println(resultSet.getString(1));
	//			System.out.println(resultSet.getString(2));
	//			System.out.println(resultSet.getString(3));
	//		System.out.println(resultSet.getString(4));
	//			System.out.println(resultSet.getString(5));			
    //          System.out.println(resultSet.getString(6));
	//				System.out.println(resultSet.getString(7));
	//			i++;
	//	}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		StartAiLaTrieuPhu UI = new StartAiLaTrieuPhu("Ai La Trieu Phu",stmt);
		UI.ShowWindow();
		
//		DiemCaoo dc = new DiemCaoo();
//		dc.ShowWindow();
	}
}
