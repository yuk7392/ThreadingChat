import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

class gui extends JFrame {

	private JPanel contentPane;
	private JTextField ip;
	private JTextField port;
	private int count;
	private JLabel lblNewLabel;
	private JButton exitButton;
	private JLabel userText;
	private JButton button_inform;
	private JTextPane inform;
	private JScrollPane scrollPane_1;
	private JButton erase;
	JTextArea log;
	private boolean buttonPressed;
	private String message;

	public gui() {
		setTitle("서버");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel ipLabel = new JLabel("IP\r\n");
		ipLabel.setFont(new Font("굴림", Font.BOLD, 15));
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ipLabel.setBounds(326, 10, 78, 15);
		contentPane.add(ipLabel);
		
		ip = new JTextField();
		ip.setHorizontalAlignment(SwingConstants.CENTER);
		ip.setEditable(false);
		ip.setBounds(326, 35, 78, 21);
		contentPane.add(ip);
		ip.setColumns(10);
		
		JLabel portLabel = new JLabel("PORT\r\n");
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		portLabel.setFont(new Font("굴림", Font.BOLD, 15));
		portLabel.setBounds(326, 66, 78, 15);
		contentPane.add(portLabel);
		
		port = new JTextField();
		port.setHorizontalAlignment(SwingConstants.CENTER);
		port.setEditable(false);
		port.setColumns(10);
		port.setBounds(326, 91, 78, 21);
		contentPane.add(port);
		
		lblNewLabel = new JLabel("유저 수");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(326, 122, 78, 21);
		contentPane.add(lblNewLabel);
		
		exitButton = new JButton("종료");
		exitButton.setToolTipText("서버를 종료시킵니다.");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			int res = JOptionPane.showConfirmDialog(null, "서버를 종료 하시겠습니까?","종료하기",JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(res == JOptionPane.CLOSED_OPTION) {
				
				
			}else if(res == JOptionPane.YES_OPTION) {
				
				System.exit(0);
				JOptionPane.showMessageDialog(null, "서버가 종료되었습니다.");
				
			}else {
				
				
				
			}
			
			
				
			}
		});
		exitButton.setFont(new Font("굴림", Font.BOLD, 20));
		exitButton.setBounds(326, 309, 78, 59);
		contentPane.add(exitButton);
		
		userText = new JLabel("0");
		userText.setFont(new Font("굴림", Font.BOLD, 25));
		userText.setHorizontalAlignment(SwingConstants.CENTER);
		userText.setBounds(326, 153, 78, 77);
		contentPane.add(userText);
		
		log = new JTextArea();
		log.setEditable(false);
		log.setToolTipText("실시간으로 로그가 보여지는 화면입니다.");
		log.setBounds(12, 5, 302, 364);
		
		JScrollPane scrollPane = new JScrollPane(log);
		scrollPane.setBounds(12, 10, 302, 358);
		contentPane.add(scrollPane);
		
		button_inform = new JButton("공지");
		button_inform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                message = inform.getText();
				buttonPressed = true;
				updateLog("공지사항이 전송 중입니다. 내용 : "+message);
				inform.setText("");
			}
		});
		button_inform.setToolTipText("공지사항을 전송합니다.");
		button_inform.setFont(new Font("굴림", Font.BOLD, 20));
		button_inform.setBounds(326, 378, 78, 53);
		contentPane.add(button_inform);
		
		inform = new JTextPane();
		inform.setBounds(12, 372, 302, 59);
		
		scrollPane_1 = new JScrollPane(inform);
		scrollPane_1.setBounds(12, 378, 302, 53);
		contentPane.add(scrollPane_1);
		
		erase = new JButton("삭제");
		erase.setToolTipText("로그 창을 초기화 합니다.");
		erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int res = JOptionPane.showConfirmDialog(null,"로그 기록을 삭제합니다. 계속 하시겠습니까?","기록 지우기",JOptionPane.YES_NO_OPTION);
				
				if(res == JOptionPane.CLOSED_OPTION) {
				
				}else if(res == JOptionPane.YES_OPTION) {
					log.setText("");
					JOptionPane.showMessageDialog(null, "기록이 삭제되었습니다.");
				}else {
					
					
				}
				
			}
		});
		erase.setFont(new Font("굴림", Font.BOLD, 20));
		erase.setBounds(326, 240, 78, 59);
		contentPane.add(erase);
	}
	
	public void updateLog(String a) {
		
		log.setText(log.getText()+ a+"\n");
		
	}
	
	public void updateChatLog(String a) {
		
		log.setText(log.getText()+"[채팅]"+ a+"\n");
		
	}
	
	public void updateNet(String a,int b) {
		
		ip.setText(a);
		port.setText(Integer.toString(b));
		
	}
	
	public void addUser() {
		
	count ++;
		
	}
	public void minUser() {
		
		if(count > 0)
		count --;
		
	}
	
	public void updateUserText() {
		
		userText.setText(Integer.toString(count));
		
	}
	
	public String getMessage() {
		
		return message;
		
	}
	
	public void disableButton() {
		
		buttonPressed =false;
		
	}
	
	public boolean informButtonPressed() {
		
		return buttonPressed ? true : false;
	}
	
	

}



