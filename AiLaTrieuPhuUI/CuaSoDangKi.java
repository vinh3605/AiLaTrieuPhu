package AiLaTrieuPhuUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CuaSoDangKi extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JLabel lbUser = new JLabel("User Name : ");
    JTextField user = new JTextField();
    JButton btStart2 = new JButton("Start");
    JButton btCancel = new JButton("Cancel");
   public  ArrayList<Account> accounts_list = new ArrayList<>();
    StartAiLaTrieuPhu startMenu;
    Statement stmt;
    //StartAiLaTrieuPhu ail
    public CuaSoDangKi(StartAiLaTrieuPhu _startMenu, Statement _stmt) {
        startMenu=_startMenu;
        stmt=_stmt;
        Container con = this.getContentPane();
        
        JPanel pn1 = new JPanel();
        pn1.setLayout(new GridLayout(1, 2));
        pn1.add(lbUser);
        pn1.add(user);

        JPanel pn2 = new JPanel();
        pn2.setLayout(new FlowLayout());
        pn2.add(btStart2);
        pn2.add(btCancel);
//        btStart2.addActionListener(this);
        btCancel.addActionListener(this);
        con.add(pn1);
        con.add(pn2, BorderLayout.SOUTH);
        event();
        
    }
    
    
    private void display(String us) {
    	AiLaTrieuPhuUI h2 =new  AiLaTrieuPhuUI(startMenu, stmt, us);
        
         h2.ShowWindow();
         this.dispose();
    }
    
    public void event() {
    	btStart2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String us = user.getText();
	            try {
					Connection con = Connect.connecting();
					String sql = "Insert into user values (?,?)";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, us);
					pst.setInt(2, 0);
					System.out.println(pst.executeUpdate());
					Connect.disconnecting(con);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	            display(us);
	            
	            Account new_user = new Account(us);
	            accounts_list.add(new_user);
//	            System.out.println("User " + us + " has been added.")
	            //chuyen qua ailatrieuphuU
			}
		});
    }

    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == btStart2) {
//            String us = user.getText();
//            try {
//				Connection con = Connect.connecting();
//				String sql = "Insert into user values (?,?)";
//				PreparedStatement pst = con.prepareStatement(sql);
//				pst.setString(1, us);
//				pst.setInt(2, 0);
//				
//				Connect.disconnecting(con);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//            
//            
//            
//            Account new_user = new Account(us);
//            accounts_list.add(new_user);
////            System.out.println("User " + us + " has been added.");
//            
//            //chuyen qua ailatrieuphuUI
//           AiLaTrieuPhuUI h2 =new  AiLaTrieuPhuUI(startMenu, stmt);
//           setUserToOther(h2, us);
//            h2.ShowWindow();
//            this.dispose();
//            
//            
        if (e.getSource() == btCancel) {
            this.dispose();
        }
    }
    public void ShowWindow() {
		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Sign Up");
}
}

  class Account {
    public String userName;

    public Account(String u) {
        userName = u;
    }

    public String getUserName() {
        return userName;
    }
}
