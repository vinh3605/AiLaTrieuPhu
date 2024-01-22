package AiLaTrieuPhuUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccessData {
	
	public static void update(updateInfo t) {
		try {
			Connection con = Connect.connecting();
			String sql = "update user "
					+ " set score =?"
					+ " where username =?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getScore());
			pst.setString(2, t.getUser());
			System.out.println(pst.executeUpdate());
			Connect.disconnecting(con);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
