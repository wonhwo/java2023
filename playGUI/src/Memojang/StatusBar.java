package Memojang;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class StatusBar extends JPanel{
	JPanel statusBar, rightBar, middleBar,leftBar;
	JLabel left,middle,right;
	JTextArea ta;
	public StatusBar(JFrame f, JTextArea ta) {
		this.ta=ta;
		
		left = new JLabel();
		middle = new JLabel();
		right = new JLabel();
		

		
		

		
		leftBar = new JPanel();
		leftBar.setBackground(new Color(153,000,204));
		middleBar = new JPanel();
		middleBar.setBackground(new Color(204,000,255));
		rightBar = new JPanel();
		rightBar.setBackground(new Color(204,051,255));
		
		leftBar.add(left);
		middleBar.add(middle);
		rightBar.add(right);
		this.setLayout(new GridLayout(1,3));
		
		this.add(leftBar);
		this.add(middleBar);
		this.add(rightBar);
		
		Timer timer = new Timer(3000,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
		});
		timer.start();
		
		
		
		
		
	} 	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // 날짜를 생성해서 left 레일블에 표시
	    Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd aa HH:mm:ss");				
		left.setText(sd.format(d));
		
		// 커서의 위치값 가져와서 mid 레이블에 표시	
		int x=0, y=0;
		try {
			y = ta.getCaretPosition(); 
			x = ta.getLineOfOffset(y);			
		} catch (BadLocationException e) {
			e.printStackTrace();
		} 
		
		middle.setText("행 : " + x + " 열 : " + y);		
		
		// 글짜크기 비율값 계산해서 right 레이블에 표시
		Font ft = ta.getFont();
		double rate = ft.getSize()/12.0 * 100;
		right.setText(String.format("%.0f",rate)+"%");
	}
}
