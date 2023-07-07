package Memojang;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.w3c.dom.css.RGBColor;

public class MemoInfo extends JFrame {
	JTabbedPane t= new JTabbedPane(JTabbedPane.LEFT);
	
	public MemoInfo() {
		this.setTitle("메모장 정보");
		JPanel p1 = new JPanel();p1.setBackground(Color.white);
		JPanel p2 = new JPanel();p2.setBackground(Color.getHSBColor(50, 50, 20));
		JPanel p3 = new JPanel();p3.setBackground(Color.white);
		t.add("도움말",p1);
		t.add("메뉴 소개",p2);
		t.add("고객센터안내",p3);
		
		this.add(t);
		setSize(450,350);
		setLocationRelativeTo(this);
		setVisible(true);
		
	}

}
