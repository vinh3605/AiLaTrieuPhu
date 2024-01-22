package AiLaTrieuPhuUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.EmptyBorder;
public  class AiLaTrieuPhuUI  {
	StartAiLaTrieuPhu startMenu;
	JButton btquaylai;
	JLayeredPane pane;
	JButton bt1, bt2,bt3,bt4;
	JLabel lbcauhoi , lbsocau;
	boolean status[ ]= new boolean[15];
	String answer;
	Statement stmt;
	int questionNumber=1;
	String user;

//	public String getUser() {
//		return user;
//	}
//
//	public void setUser(String user) {
//		this.user = user;
//	}

	public AiLaTrieuPhuUI(StartAiLaTrieuPhu _startMenu, Statement _stmt, String user) {
		startMenu =_startMenu;
		stmt=_stmt;
		startMenu.hideStartMenu();
		addControls();
		//addEvents();
		this.user=user;
		System.out.println(user);
		
	}

	//them control
	public void addControls() {
		//tao mau cho cac nut va label 
		Color mau = new Color(204, 229,255);
			
		//tạo layer pane 
		pane =  new JLayeredPane();
		
		//set Layout overlay
		pane.setLayout(new OverlayLayout(pane));
		
		JPanel pnbg = new JPanel();
		pnbg.setLayout(new BorderLayout());
		
		//tạo jlabel làm background
		JLabel lblbg= new JLabel();
		
		//lấy ảnh
		Image bgim = Toolkit.getDefaultToolkit().createImage(StartAiLaTrieuPhu.class.getResource("/image/bg3.jpg"));
		
		lblbg.setIcon(new  ImageIcon(bgim) );
		pnbg.add(lblbg, BorderLayout.SOUTH);
		//them background vào layer và đặt thấp nhất
		pane.add(pnbg, JLayeredPane.DEFAULT_LAYER);
		 
		
		// tạo pn main 
		JPanel pnMain = new JPanel(); 
		pnMain.setLayout(new GridLayout(2, 1));
	    //thêm pn main vào layer cao hơn 
	    pane.add(pnMain, JLayeredPane.PALETTE_LAYER);
	    //làm mờ nó
	    pnMain.setOpaque(false);
		
	    

	    //taopnphu chứa nút trở về và labal đếm câu hỏi 
	    JPanel pnPhu = new JPanel();
	    pnPhu.setLayout(new BoxLayout(pnPhu, BoxLayout.X_AXIS));
	    
	    // tạo nút quay trở lại
	    btquaylai = new JButton("");
	    //lấy ảnh
		Image back = Toolkit.getDefaultToolkit().createImage(AiLaTrieuPhuUI.class.getResource("/image/back.jpg"));
		// Điều chỉnh kích thước ảnh
		Image resizedBack = back.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		btquaylai.setIcon(new  ImageIcon(resizedBack) );
		//kích thuớc nút
	    btquaylai.setPreferredSize(new Dimension(20,20));	  
	    btquaylai.setFocusPainted(false);
	    btquaylai.setContentAreaFilled(false);
	    btquaylai.setBorder(new EmptyBorder(0,0,0,0));
	    
	    
	   
	    
	    
	    //tạo label hiển thị số câu 
	    lbsocau = new JLabel("1/15");
	    Font cochu = new Font("Arial", Font.BOLD, 25);
	    lbsocau.setFont(cochu);
	    lbsocau.setPreferredSize(new Dimension(100,50 ));
	    lbsocau.setAlignmentX(startMenu.CENTER_ALIGNMENT);
	    lbsocau.setOpaque(true);
	    //setcolor
	    lbsocau.setBackground(mau);
	    
	    //add nó vào pnPhu
	    pnPhu.add(btquaylai);
	    // btquay lại cách lbsocau 70 pixel , đồng thời đẩy lbsocau vào giữa)
	    pnPhu.add(Box.createHorizontalStrut(130) );
	    pnPhu.add(lbsocau);
	    pnPhu.setPreferredSize(new Dimension(350,100));
	    pnPhu.setOpaque(false);
	    pane.add(pnPhu, JLayeredPane.PALETTE_LAYER);

	    
	    //jlabel chứa đề 
	     lbcauhoi = new JLabel();
	    //setsize
	    lbcauhoi.setPreferredSize(new Dimension(300,100));
	    lbcauhoi.setOpaque(true);
	    //setcolor
	    lbcauhoi.setBackground(mau);
	    //tạo font
	    Font fontt  = new Font("Times New Roman", Font.BOLD, 18);
	    lbcauhoi.setFont(fontt);
	    //hiển thị văn bản ngay chính giữa
	    lbcauhoi.setHorizontalAlignment(JLabel.CENTER);
        lbcauhoi.setVerticalAlignment(JLabel.CENTER);
	    
	    
        //tạo pn chưa label câu hoi, dùng flowlayout để jlabel không tràn hết ra cả panel
	    
	    JPanel pnCauhoi = new JPanel(new FlowLayout());
	    pnCauhoi.add(lbcauhoi);
	    //lam mờ
	    pnCauhoi.setOpaque(false);
	    pane.add(pnCauhoi, JLayeredPane.PALETTE_LAYER);

	    
 //tạo pn chưu pnquestion và pnPhu
	    
	    JPanel pnTong = new JPanel();
	    pnTong.setLayout(new BorderLayout());
	    pnTong.setOpaque(false);
	    
	    //them 2 pn vao 
	    pnTong.add(pnPhu, BorderLayout.NORTH);
	    pnTong.add(pnCauhoi, BorderLayout.CENTER);
	    
	    
	    //them pn main
	    pnMain.add(pnTong);
	
		//tao3 button
	     bt1   = new JButton(" cau1 ");
	     bt2 = new JButton("cau 2 ");
	     bt3= new JButton("cau 3");
	     bt4=  new JButton("cau 4");
	    
	    //làm màu  cho buuton
	    bt1.setBackground(mau); 
        bt1.setForeground(Color.black);
        
        bt2.setBackground(mau); 
        bt2.setForeground(Color.black);
      
        bt3.setBackground(mau);  
        bt3.setForeground(Color.BLACK);
        
        bt4.setBackground(mau);  
        bt4.setForeground(Color.black);
	    
        
//tạo pnStartButon để chứa 4 nút đó
	    JPanel pnQuestionButton =new JPanel();	
	    pnQuestionButton.setLayout(new BoxLayout(pnQuestionButton, BoxLayout.Y_AXIS));
	    pnQuestionButton.setOpaque(false);
	    
//đặt các thành phần vào pnButtonStart 
	    
	    //đưa các nut vào giữa
	    bt1.setAlignmentX(startMenu.CENTER_ALIGNMENT);
	    bt2.setAlignmentX(startMenu.CENTER_ALIGNMENT);
	    bt3.setAlignmentX(startMenu.CENTER_ALIGNMENT);
	    bt4.setAlignmentX(startMenu.CENTER_ALIGNMENT);
	    
	    //đặt kích thước đồng nhất cho các nút 
	    bt1.setMaximumSize(new Dimension(400 ,100));
	    bt2.setMaximumSize(new Dimension(400 ,100));
	    bt3.setMaximumSize(new Dimension(400 ,100));
	    bt4.setMaximumSize(new Dimension(400 ,100));
	
	   //thêm và căn chỉnh khoảng cách giưã các nút    
	    pnQuestionButton.add(Box.createVerticalStrut(20)); // Căn chỉnh lên giữa
	    pnQuestionButton.add(bt1);
	    pnQuestionButton.add(Box.createVerticalStrut(20)); 
	    pnQuestionButton.add(bt2);
	    pnQuestionButton.add(Box.createVerticalStrut(20)); 
	    pnQuestionButton.add(bt3);
	    pnQuestionButton.add(Box.createVerticalStrut(20)); 
	    pnQuestionButton.add(bt4);
	    pnQuestionButton.add(Box.createVerticalGlue()); // Căn chỉnh xuống giữa
	    
	    pnMain.add(pnQuestionButton);
	    
	    startMenu.add(pane);
	    
	    
	  //  btquaylai.addActionListener(startMenu);
	    
	   btquaylai.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		//	System.out.println(1);
			backToStartMenu();
		}
	});
	    // su ly su kien click 
	   bt1.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
		//	System.out.println(bt1.getText());
			//nếu đúng sẽ chuyển qua câu hỏi tiếp theo
			if(answer.equals(bt1.getText().toString())) {
				getNextQuestion();
			}//nếu sai sẽ chuyển đến cửa sổ gameover và ra màn hình chinhs
			else {
				backToStartMenu();
				new GameOver();
				updateInfo find = new updateInfo(user, questionNumber-1);
				AccessData.update(find);
			}
		}
	});
	   bt2.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			if(answer.equals(bt2.getText().toString())) {
				getNextQuestion();
			}else {
				backToStartMenu();
				new GameOver();
				
				updateInfo find = new updateInfo(user, questionNumber-1);
				AccessData.update(find);
				
			}
		}
	});
	   bt3.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			if(answer.equals(bt3.getText().toString())) {
				getNextQuestion();
			}else {
				backToStartMenu();
				new GameOver();
				updateInfo find = new updateInfo(user, questionNumber-1);
				AccessData.update(find);
			}
		}
	});
	   bt4.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			if(answer.equals(bt4.getText().toString())) {
				getNextQuestion();
			}else {
				backToStartMenu();
				new GameOver();
				updateInfo find = new updateInfo(user, questionNumber-1);
				AccessData.update(find);
			}
		}
	});
			
	    
	   getNextQuestion();
	}
	private void backToStartMenu() {
		pane.setVisible(false);
		startMenu.pane.setVisible(true);
	}
 
	
	private void getNextQuestion() {
		
		if(questionNumber>status.length) {
			backToStartMenu();
			/// hien thi diem cua nguoi choi
		}
		String sql="select * from questions where id='"+String.valueOf(questionNumber)+"'";
		questionNumber++;
	
		try {
		    ResultSet resultSet = stmt.executeQuery(sql);
		    while (resultSet.next()) {
		   
		 
		    	//lấy văn bản theo thứ tự từ cột 1 đến cột 7
		    	lbsocau.setToolTipText(String.valueOf(questionNumber-1));
		        lbsocau.setText(resultSet.getInt("ID") + "/15");
		    	lbcauhoi.setText(resultSet.getString("Question"));
		    	bt1.setText(resultSet.getString("A"));
		    	bt2.setText(resultSet.getString("B"));
		    	bt3.setText(resultSet.getString("C"));
		    	bt4.setText(resultSet.getString("D"));
		    	answer=resultSet.getString("Answer");
		    }
		      
		} catch (SQLException e) {
		    System.err.println("SQL Exception: " + e.getMessage());
		    e.printStackTrace();
		} catch (Exception e) {
		    System.err.println("Unexpected Exception: " + e.getMessage());
		    e.printStackTrace();
		}
		
	}
    
	public void ShowWindow() {
		startMenu.setSize(400, 600);
		startMenu.setLocationRelativeTo(null);
		startMenu.setDefaultCloseOperation(startMenu.EXIT_ON_CLOSE);
		startMenu.setVisible(true);
		startMenu.setResizable(false);
		startMenu.setTitle("Ai Là Triệu Phú");
	}
}
	
