package Memojang;

import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;

public class FontStyleView extends JFrame implements ActionListener,ListSelectionListener {
	String[] fontName = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	//GraphicsEnvironment 클래스는 시스템의 글꼴, 색상을 나타내는 클래스이다.
	//getLocalGraphicsEnvironment()메소드를 추가하여 현재 환경의 글꼴, 색상을 반환한다.
	//getAvailableFontFamilyNames() 메소드는 현재 사용 가능한 모든 글꼴 이름을 문자열 배열로 반환한다.
	String[] fontStyleName = {"PLAIN","BOLD","ITALIC"};
	String[] fontSize = {"6","7","8","9","10","12","14","17","20","24","30","40"};
	
	JList listFontName,listFontStyle,listFontSize;
	JPanel listPanel,centerPanel,southPanel;
	JScrollPane listScrollPane;
	JLabel textLabel;
	JButton bconfirm,bCancel;
	
	Font newFont = null;
	JTextArea ta;
	public FontStyleView(JTextArea ta) {
		this.ta=ta;
		Container con = getContentPane();//getContentPane()를 이용하여 con에 컨테이너를 가져온다.
		centerPanel = new JPanel(new GridLayout(2,1));
		listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(0,3));//centerPanel,listPanel 빈 그리드 레이아웃을 생성한다. 
		
		listFontName = new JList(fontName); //listFontName이라는 jList를 생성한다.
		listFontName.addListSelectionListener(this);
		//addListSelectionListener()는 리스트 컴포넌트(List Component)에서 선택이 변경될 때마다 이를 감지하는 리스너(ListSelectionListener)를 등록하는데 사용한다.
		listScrollPane = new JScrollPane(listFontName);//스크롤 생성
		listScrollPane.setBorder(new TitledBorder("Font Name"));
		//Font Name에 스크롤을 추가하는 api이다.
		listPanel.add(listScrollPane);//listPanel에 listScrollPane를 추가
		
		listFontName.setSelectionMode(DefaultListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//setSelectionMode()는 JList 컴포넌트의 선택 모드를 설정하는 데 사용됩니다.
		//인자1로는 DefaultListSelectionModel 클래스의 상수 값 중 하나를 받습니다.
		//인자2로는 SINGLE_INTERVAL_SELECTION(사용자가 마우스나 키보드로 선택할 수 있도록 함)
		listFontName.setSelectedValue(ta.getFont().getName(),false);
		//setSelectedValue() 메소드는 JList 컴포넌트에서 초기 선택 항목을 설정하는 데 사용됩니다. 
		//인자 1은 선택하는 값인 ta.getFont().getName()를 입력입니다.
		//인자 2는 그 값이 초기 시작시 false값이 나오도록 설정한 것입니다.
		listFontStyle=new JList(fontStyleName);//FontStyleName이라는 jList를 생성한다.
		listFontStyle.addListSelectionListener(this);
		listScrollPane = (new JScrollPane(listFontStyle));
		listScrollPane.setBorder(new TitledBorder("Font Style Name"));
		listPanel.add(listScrollPane);
		
		listFontSize = new JList(fontSize);//fontSize이라는 jList를 생성한다.
		listFontSize.addListSelectionListener(this);
		listScrollPane = new JScrollPane(listFontSize);
		listScrollPane.setBorder(new TitledBorder("Font Size"));
		listPanel.add(listScrollPane);
		
		listFontSize.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listFontSize.setSelectedValue(""+ta.getFont().getSize(), false);
		
		 textLabel = new JLabel("Hello Andromeda");//textLabel 객체를 생성힌디
	      textLabel.setHorizontalAlignment(JLabel.CENTER);//객체의 정렬방식을 CENTER으로 변경한다.(문자열이 가운데로 이동된다.)
	      textLabel.setFont(new Font( 
	            (String)(listFontName.getSelectedValue()),
	            listFontStyle.getSelectedIndex(),
	            Integer.parseInt((String)(listFontSize.getSelectedValue()))));	
	    //setFont는 textLabel의 폰트를 설정하는 명령어로
	    //첫번째 인자는 폰트의 이름 두번째 인자는 적용할 폰트의 스타일 세번째 인자는 폰트의 크기를 설정한다.
	    //getSelectedValue()나 getSelectedIndex() 메소드를 사용하여 각자의 값을 가져온다.
		//name.getSelectedValue() 메소드는 JList 컴포넌트에서 현재 선택된 항목의 값을 반환한다. 없을시 null값 반환
	    //name.getSelectedIndex() 메소드는 JList 컴포넌트에서 현재 선택된 항목의 인덱스를 반환한다. 없을시 -1값 반환
		centerPanel.add(listPanel);centerPanel.add(textLabel);//위에 있는 centerPanel에 3가지 패널들을 추가한다.
		bconfirm = new JButton("확인");bCancel = new JButton("취소");//확인 취소 버튼을 생성한다.
		bconfirm.addActionListener(this);bCancel.addActionListener(this);//각각의 버튼에 엑션리스너를 추가한다.
		southPanel = new JPanel();//새 패널을 생성하고
		southPanel.add(bconfirm); southPanel.add(bCancel);//버튼들을 추가한다.
		con.add(centerPanel,"Center");//센터패널과 사우스 패널을 센터와 아래에 배치하낟.
		con.add(southPanel,"South");
		newFont = textLabel.getFont();//textLabel에 있는 폰트 정보를 newFont에 저장한다.
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("확인"))
			ta.setFont(newFont);//ta에 있는 택스트에 새로운 폰트를 적용한다.
		this.dispose();//닫기
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		try {
			textLabel.setFont(new Font((String)(listFontName.getSelectedValue()),listFontStyle.getSelectedIndex(),Integer.parseInt((String)(listFontSize.getSelectedValue()))));
			
			newFont = textLabel.getFont();
			
		} catch (Exception e1) {
			e1.getMessage();
		}
		
		
	}
	

}
