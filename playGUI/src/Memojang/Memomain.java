package Memojang;
import com.zetcode.Tetris;
import java.awt.*;

import java.awt.event.*;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.zetcode.Tetris;

import Calendar.HelloSwing;
import Calendar.MemoCalendar;



public class Memomain extends JFrame implements ActionListener {
	JLabel statusLabel;
	FontStyleView fontStyleView;
	JTextArea ta;
	JFileChooser jfc; JOptionPane jtp;
	boolean isNew = false;
	File file;
	String a=" - 레삐의 메모장";
	StatusBar statusBar;
	JButton newBtn,openBtn,saveBtn,exitBtn,copyBtn,pasteBtn,cutBtn,fontBtn,colBtn;
	public Memomain() {
		setTitle("레삐의 메모장");
		MainView();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ta=new JTextArea();
		JScrollPane jsp = new JScrollPane(ta);
		jfc=new JFileChooser();
		this.add(jsp);
		statusBar= new StatusBar(this,ta);
		this.add(BorderLayout.SOUTH,statusBar);


		
		setBounds(300,100,1000,800);
		setVisible(true);
	}
	public void MainView() {
		KeyStroke Key;
		JMenuBar mb = new JMenuBar();
		JMenu m1,m2,m3,m4,m5,m6;
		JMenuItem item;
		//////////////////////////////////////////////
		JToolBar tb = new JToolBar();
		ImageIcon newF = new ImageIcon("image/new-document.png");
		ImageIcon saveF = new ImageIcon("image/save.png");
		ImageIcon copyF = new ImageIcon("image/copy.png");
		ImageIcon cutF = new ImageIcon("image/paint.png");
		ImageIcon colF = new ImageIcon("image/paint.png");
		
		ImageIcon openF = new ImageIcon("image/open.png");
		ImageIcon extF = new ImageIcon("image/exit.png");
		ImageIcon pasteF = new ImageIcon("image/paste.png");
		ImageIcon fontF = new ImageIcon("image/font.png");
		
		newBtn = new JButton(newF); openBtn = new JButton(openF); saveBtn = new JButton(saveF);
		exitBtn = new JButton(extF); copyBtn = new JButton(colF); pasteBtn = new JButton(pasteF);
		cutBtn = new JButton(cutF); fontBtn = new JButton(fontF); colBtn = new JButton(colF);
		
		newBtn.setToolTipText("새파일을 장석합니다");
		openBtn.setToolTipText("파일을 불러옵니다");
		saveBtn.setToolTipText("파일을 저장합니다");
		exitBtn.setToolTipText("종료합니다");
		copyBtn.setToolTipText("복사합니다.");
		pasteBtn.setToolTipText("붙여넣기");
		fontBtn.setToolTipText("글꼴");
		cutBtn.setToolTipText("글자색");
		
		
		newBtn.addActionListener(this);
		openBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		copyBtn.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				ta.copy();
				
			}
		});
		pasteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.paste();
				
			}
		});
		cutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser color = new JColorChooser();
				Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.yellow);
				if(setColor!=null) {
					ta.setForeground(setColor);}
				
			}
		}); 
		fontBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fontStyleView = new FontStyleView(ta);
				fontStyleView.setBounds(200,200,400,350);
				fontStyleView.setVisible(true);
				
			}
		});
		colBtn.addActionListener(this);
		
		
		newBtn.setToolTipText("새파일 작성");saveBtn.setToolTipText("저장");
		openBtn.setToolTipText("파일 열기");
		tb.add(newBtn);tb.add(openBtn);tb.add(saveBtn);tb.add(exitBtn);tb.add(copyBtn);
		tb.add(pasteBtn);tb.add(cutBtn);tb.add(fontBtn);tb.add(colBtn);
		
		
		//////////////////////////////////////////////
		m1=new JMenu("파일(F)");m1.setMnemonic(KeyEvent.VK_F);
		
		item = new JMenuItem("새로 만들기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m1.add(item);
		
		item = new JMenuItem("열기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m1.add(item);
		m1.addSeparator();
		
		item = new JMenuItem("저장");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m1.add(item);
		
		item = new JMenuItem("다른이름으로 저장");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m1.add(item);
		m1.addSeparator();
		
		item = new JMenuItem("끝내기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m1.add(item);
		
		////////////////////////////////////////////////////
		m2=new JMenu("편집(E)");m2.setMnemonic(KeyEvent.VK_E);
		
		item = new JMenuItem("실행 취소");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		m2.add(item);
		m2.addSeparator();
		
		item = new JMenuItem("잘라내기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.cut();
			}
		});
		m2.add(item);
		
		item = new JMenuItem("복사");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.copy();	
			}
		});
		m2.add(item);
		
		item = new JMenuItem("붙여넣기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.paste();
				
			}
		});
		m2.add(item);
		
		item = new JMenuItem("삭제");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.cut();
				
			}
		});
		m2.add(item);
		m2.addSeparator();
		
		item = new JMenuItem("찾기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m2.add(item);
		
		item = new JMenuItem("찾아 바꾸기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_F3,0);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m2.add(item);
		
		item = new JMenuItem("이동");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m2.add(item);
		m2.addSeparator();
		
		item = new JMenuItem("모두 선택");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.selectAll();
			}
		});
		m2.add(item);
		
		item = new JMenuItem("시간/날짜");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_F5,0);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date d=new Date();
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd-aa-HH-mm-ss");
				ta.append(sd.format(d));
			}
		});
		m2.add(item);
		////////////////////////////////////////////////////////////////////////
		
		m3=new JMenu("서식(O)");m3.setMnemonic(KeyEvent.VK_O);
		
		item = new JMenuItem("배경색(B)");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser color = new JColorChooser();
				Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.yellow);
				if(setColor!=null) {
					ta.setBackground(setColor);
				}
			}
		});
		m3.add(item);
		
		item = new JMenuItem("글자색(E)");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser color = new JColorChooser();
				Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.blue);
				if(setColor!=null) {
					ta.setForeground(setColor);
			}
			}
		});
		m3.add(item);
		
		item = new JMenuItem("글꼴(F)");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fontStyleView = new FontStyleView(ta);
				fontStyleView.setBounds(200,200,400,350);
				fontStyleView.setVisible(true);
				
			}
		});
		m3.add(item);
		
		//////////////////////////////////////////////////////////////////////
		m4=new JMenu("보기(V)");m4.setMnemonic(KeyEvent.VK_V);
		
		JMenu sbmenu = new JMenu("확대하기/축소하기");
		m4.add(sbmenu);
		sbmenu.add(new JMenuItem("확대(I)")).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Font ft=ta.getFont();
				int font_size = ft.getSize()+2;
				String n = ft.getName();
				int s = ft.getStyle();
				ft=new Font(n,s,font_size);
				ta.setFont(ft);
				
			}
		});
		sbmenu.add(new JMenuItem("축소(O)")).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Font ft=ta.getFont();
				int font_size = ft.getSize()-2;
				String n = ft.getName();
				int s = ft.getStyle();
				ft=new Font(n,s,font_size);
				ta.setFont(ft);
				
			}
		});
		sbmenu.add(new JMenuItem("확대하기/축소하기 기본값 복원")).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Font ft=ta.getFont();
				String n = ft.getName();
				int s = ft.getStyle();
				ft=new Font(n,s,12);
				ta.setFont(ft);
				
			}
		});
		m4.addSeparator();
		JCheckBoxMenuItem cb = new JCheckBoxMenuItem("상태 표시줄");
		cb.setState(true);
		cb.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(cb.getState()==true)
					statusBar.setVisible(true);
				else
					statusBar.setVisible(false);
				
			}
		});
		m4.add(cb);
		
		
		m5=new JMenu("도움말(H)");m5.setMnemonic(KeyEvent.VK_H);
		
		item = new JMenuItem("도움말 보기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemoInfo();
				
			}
		});
		m5.add(item);
		
		item = new JMenuItem("메모장 정보");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(this);
		m5.add(item);
		
		m6=new JMenu("잡동사니");m2.setMnemonic(KeyEvent.VK_K);
		
		item = new JMenuItem("폰북");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelloSwing();
			}
		});
		m6.add(item);
		
		item = new JMenuItem("계산기");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Calculator();
				
			}
		});
		m6.add(item);
		
		item = new JMenuItem("스케쥴러");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemoCalendar();
			}
		});
		m6.add(item);
		item = new JMenuItem("미니게임");
		Key = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK);
		item.setAccelerator(Key);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new com.zetcode.Tetris().pu();
			}
		});
		m6.add(item);
		
		mb.add(m1);mb.add(m2);mb.add(m3);mb.add(m4);mb.add(m5);mb.add(m6);
		this.setJMenuBar(mb);
		this.add(tb,BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		
		new Memomain();

	}
	int check() {
		int a=0;
		String data="";
		int ch;
		try {
		if(isNew==true) {
			FileReader fr = new FileReader(file);
			while((ch=fr.read())!=-1) {data=data+(char)ch;}
			fr.close();
			if(!ta.getText().equals(data))
				a=1;
		}
		else if(isNew==false&&!ta.getText().equals("")) {a=1;}}

		catch (Exception e) {
			e.getMessage();
			return a;
		}
		return a;
	
	}
	
	void open() {
		int re = jfc.showOpenDialog(this);
		if(re==jfc.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			String data="";
			int ch;
			try {
				FileReader fr = new FileReader(file);
				while((ch=fr.read())!=-1)
					data=data+(char)ch;
				ta.setText(data);
				setTitle(file.getAbsolutePath()+a);
				fr.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
	void save() { //과제// 처음 저장시 대화 상자가 나와야 하고 그 이후에는 나오면 x 다른 이름으로 저장 때만 대화 상자 나오게 하기
		
		if(getTitle().equals("레삐의 메모장")) {
			try {
				int re = jfc.showSaveDialog(this);
				if(re==jfc.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					FileWriter fw = new FileWriter(file);
					fw.write(ta.getText());
					setTitle(file.getAbsolutePath()+a);
					fw.close();
					JOptionPane.showMessageDialog(this,"파일을 저장했습니다.");
					isNew = false;
				}
				
			} catch (IOException e) {
				e.getMessage();
			}
		}
		else {
	        try {
	        	File file = jfc.getSelectedFile();
	            PrintStream ps=new PrintStream(file);
	            ps.println(ta.getText());
	            ps.close();
	            JOptionPane.showMessageDialog(this,"파일을 저장했습니다.");
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	void saveAs() {
		int re = jfc.showSaveDialog(this);
		if(re==jfc.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(ta.getText());
				setTitle(file.getAbsolutePath()+a);
				fw.close();
				JOptionPane.showMessageDialog(this,"파일을 저장했습니다.");
				isNew = false;
				
			} catch (IOException e) {
				e.getMessage();
			}
		
		}
	}
	void quit() {System.exit(0);}
	
	void introduce(){				
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	panel.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 0));
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
	JLabel main = new JLabel("메모장 정보(version 1.0)");
	main.setBorder(BorderFactory.createEmptyBorder(20 , 0 , 10 , 0));
	main.setFont(new Font("Monospaced", Font.BOLD, 25));
	main.setAlignmentX(Component.CENTER_ALIGNMENT);
	panel.add(main);
	
    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
    separator.setForeground(Color.BLACK);
    separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    panel.add(separator);

	ImageIcon imageIcon = new ImageIcon("src/image.png");
	ImageIcon scaledIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH));
	JLabel imageLabel = new JLabel(scaledIcon);
	imageLabel.setBorder(BorderFactory.createEmptyBorder(10 , 0 , 10 , 0));
	imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 이미지를 수평 방향으로 중앙에 배치
	panel.add(Box.createVerticalGlue()); // 중앙에 배치하기 위한 공간 추가
	panel.add(imageLabel);

	// 각 JLabel 컴포넌트에 수평 방향으로 중앙에 배치되도록 설정
	JLabel versionLabel = new JLabel("개발자 이름 : 이효원");
	versionLabel.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 10 , 0));
	versionLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
	versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	panel.add(versionLabel);

	JLabel osLabel = new JLabel("개발자 이메일 : dlgy9714@gmail.com");
	osLabel.setFont(new Font("Monospaced", Font.BOLD, 10));
	osLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	panel.add(osLabel);

	JLabel userLabel = new JLabel("사용자 이름 : reppy");
	userLabel.setFont(new Font("Monospaced", Font.BOLD, 10));
	userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	panel.add(userLabel);

	JLabel javaLabel = new JLabel("자바 버전 : " + System.getProperty("java.version"));
	javaLabel.setFont(new Font("Monospaced", Font.BOLD, 10));
	javaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	panel.add(javaLabel);

	JLabel vendorLabel = new JLabel("프로그램 설명 : 자바를 이용한 파일, 편집, 서식, 보기, 도움말 기능을 구현한 메모장");
	vendorLabel.setFont(new Font("Monospaced", Font.BOLD, 10));
	
	vendorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	panel.add(vendorLabel);

	JDialog dialog = new JDialog(frame, "메모장 정보", true);
	dialog.setPreferredSize(new Dimension(550, 480));
	dialog.getContentPane().add(panel);
	dialog.pack();
	dialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("새로 만들기")||e.getSource()==newBtn) { 
			int a = check();
			if(a==1) {
				int re = JOptionPane.showConfirmDialog(this, "변경된 내용을 저장하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(re==0)save();
				else if(re==1) {
					ta.setText("");
					isNew = false;
					file=null;
					}
			}
		}
		
		else if(cmd.equals("열기")||e.getSource()==openBtn) {open();}
		
		else if(cmd.equals("저장")||e.getSource()==saveBtn) {save();}
		
		else if(cmd.equals("다른이름으로 저장")) {saveAs();}
		
		else if(cmd.equals("끝내기")||e.getSource()==exitBtn) {quit();}
		
		else if(cmd.equals("찾기")) {	
			Find fi = new Find(this, ta); 
			fi.showFind();
		}
		
		else if(cmd.equals("찾아 바꾸기")) {
			Find fi = new Find(this, ta); 
			fi.showReplace();
		}
		
		else if(cmd.equals("메모장 정보")) {introduce();}
			
		else if(e.getSource()==colBtn) {
			JColorChooser color = new JColorChooser();
			Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.blue);
			if(setColor!=null) {
				ta.setBackground(setColor);
				}	
			}
			
	}
		}
	
			
	
		
		
	

