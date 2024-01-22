package AiLaTrieuPhuUI;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import AiLaTrieuPhuUI.CuaSoDangKi;
public class StartAiLaTrieuPhu extends JFrame{
	JButton btStart, btDiemCao , btLuatChoi ;
	public JLayeredPane pane;
	public Statement stmt;
	
	public StartAiLaTrieuPhu(String title, Statement _stmt) {
		super(title);
		this.stmt=_stmt;
		addControls();
		addEvents();
			
		
	}
	

	//them control
	public void addControls() {
			Color mau = new Color(204, 229,255);

			
			
			//tạo layer pane (dùng để quản lý các lớp layers)
		    pane =  new JLayeredPane();
			
			//set layout overlay chồng lên và ko che lấp . dùng vị trí tương đối
			pane.setLayout(new OverlayLayout(pane));
			
			JPanel pnbg = new JPanel();
			pnbg.setLayout(new BorderLayout());
			
			//tạo jlabel làm background
			JLabel lblbg= new JLabel();
			
			//lấy ảnh
			Image bgim = Toolkit.getDefaultToolkit().createImage(StartAiLaTrieuPhu.class.getResource("/image/bg3.jpg"));
		
			
			
			//thêm ảnh vào label
			lblbg.setIcon(new  ImageIcon(bgim) );
			pnbg.add(lblbg, BorderLayout.SOUTH);
			//them background vào layer và đặt thấp nhất
			pane.add(pnbg, JLayeredPane.DEFAULT_LAYER);
			 
			
		   
		    JPanel pnMain = new JPanel(); 
		    pnMain.setLayout(new GridLayout(2, 1));
		    //thêm pn main vào layer cao hơn 
		    pane.add(pnMain, JLayeredPane.PALETTE_LAYER);
		    //làm mờ nó
		    pnMain.setOpaque(false);
		    
		    
		    // Tạo tiêu đề 
		    JLabel lbTieude = new JLabel("Ai LÀ TRIỆU PHÚ");
		    //tạo font
		    Font font  = new Font("Arial", Font.BOLD, 18);
		    lbTieude.setFont(font);
		    //màu chư
		    lbTieude.setForeground(Color.WHITE);

		    // Tạo JPanel pntieude
		    JPanel pntieude = new JPanel(new GridBagLayout()); // Hoặc BoxLayout
		    pntieude.add(lbTieude);
		    
		    //làm mờ pn tieu đe
		    pntieude.setOpaque(false);
		    // Đặt pntieude vào vùng trung tâm của pnMain (BorderLayout.CENTER)
		    //   pnMain.add(pntieude, BorderLayout.NORTH);
		    pnMain.add(pntieude);

		
		    //tao3 button
		    btStart   = new JButton(" Bắt Đầu ");
		     btDiemCao = new JButton("Điểm Cao ");
		     btLuatChoi= new JButton("Luật Chơi");
		    
		    //làm màu  cho buuton
		    btStart.setBackground(mau); 
	        btStart.setForeground(Color.BLACK);
	        
	        btDiemCao.setBackground(mau); 
	        btDiemCao.setForeground(Color.black);
	      
	        btLuatChoi.setBackground(mau);  
	        btLuatChoi.setForeground(Color.black);
	        
		    
	//tạo pnStartButon để chứa 3 nút đó
		    JPanel pnStartButton =new JPanel();	
		    pnStartButton.setLayout(new BoxLayout(pnStartButton, BoxLayout.Y_AXIS));
		    pnStartButton.setOpaque(false);
		    
    //đặt các thành phần vào pnButtonStart 
		    
		    //đưa các nut vào giữa
		    btStart.setAlignmentX(CENTER_ALIGNMENT);
		    btDiemCao.setAlignmentX(CENTER_ALIGNMENT);
		    btLuatChoi.setAlignmentX(CENTER_ALIGNMENT);
		    
		    //đặt kích thước đồng nhất cho các nút 
		  
		    
		    btStart.setMaximumSize(new Dimension(200, 30));
		    btDiemCao.setMaximumSize(new Dimension(200, 30));
		    btLuatChoi.setMaximumSize(new Dimension(200, 30));
		    
//		    Dimension btnSize = new Dimension(180, 30);
//		    btStart.setPreferredSize(btnSize);
//		    btDiemcao.setPreferredSize(btnSize);
//		    btLuatChoi.setPreferredSize(btnSize);
		
		    
		
		   //thêm và căn chỉnh khoảng cách giưã các nút
		    
		    pnStartButton.add(Box.createVerticalStrut(20)); // Căn chỉnh lên giữa
		    pnStartButton.add(btStart);
		    pnStartButton.add(Box.createVerticalStrut(20)); 
		    pnStartButton.add(btDiemCao);
		    pnStartButton.add(Box.createVerticalStrut(20)); 
		    pnStartButton.add(btLuatChoi);
		    pnStartButton.add(Box.createVerticalGlue()); // Căn chỉnh xuống giữa
		    
		    pnMain.add(pnStartButton);	   
		    this.add(pane);
	
	}
	public void hideStartMenu() {
		if(pane!=null) {
			pane.setVisible(false);
		}
	}
	public void addEvents() {
		
	btLuatChoi.addActionListener(new HelpEvent());
	btStart.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		//	System.out.println(1);
			enterCuaso();
			
		}
	});
	
	btDiemCao.addActionListener(new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DiemCaoo diemCaoFrame= new DiemCaoo();
			diemCaoFrame.ShowWindow();
		}
	});	
	}		
		
	private void enterCuaso() {
		CuaSoDangKi h1=	new CuaSoDangKi(this,stmt);
		h1.ShowWindow();
	}
	
	class HelpEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "Người chơi phải trả lời câu hỏi từ 1 đến 15, mỗi câu hỏi chỉ được chọn một đáp án chính xác nhất.\n"
					+"Trường hợp khi mà bạn đã bắt đầu chương trình nhưng không chơi hoặc bấm thoát hay là quay lại thì lượt đó sẽ không được lưu vào bảng xếp hạng.\n"+ 
					"Những thắc mắc khác xin liên hệ chương trình!\n");
			
		}	
	}

	public void ShowWindow() {
		this.setSize(400, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		//this.pack();
		this.setResizable(false);
		
	}


	
}
