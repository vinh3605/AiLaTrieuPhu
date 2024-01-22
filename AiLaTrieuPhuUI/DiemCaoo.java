package AiLaTrieuPhuUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.x.protobuf.MysqlxCrud.Column;
public class DiemCaoo extends JFrame {
	JFrame f;
	private DefaultTableModel model;
	   
	 public DiemCaoo() {
		f = new JFrame();
		
        model = new DefaultTableModel();
        model.addColumn("Xếp Hạng");
        model.addColumn("Name");
        model.addColumn("Điểm");
        
        
        JTable jt = new JTable(model);
	
		
		
	 	JScrollPane sp = new JScrollPane(jt);
	
		
	
		
		
		this.add(sp);
		this.setSize(300,400);    
		this.setVisible(true);  		
		HienThiDiem();
	
		}
	 private void HienThiDiem() {
		    // Connect to your database, modify the connection URL, username, and password accordingly
		    String jdbcURL = "jdbc:mysql://localhost:3306/ailatrieuphu";
		    String username = "root";
		    String password = "1234";

		    try (
		        Connection connection = DriverManager.getConnection(jdbcURL, username, password);
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery("SELECT UserName, Score FROM user ORDER BY Score DESC")
		    ) {
		        // Clear existing rows in the table model
		        model.setRowCount(0);

		        // Add retrieved data to the table model
		        int stt = 1; // Số thứ tự
		        // Thêm dữ liệu đã lấy được vào bảng mô hình
		        while (resultSet.next()) {
		            String name = resultSet.getString("UserName");
		            int diem = resultSet.getInt("Score");
		            model.addRow(new Object[]{stt++, name, diem});
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	 
	 public void ShowWindow() {
		 this.setSize(300,400);
		 this.setLocationRelativeTo(null);
		 this.setSize(300,400);
		 this.setVisible(true);
	
}
	
	
	 
	 
	 }

