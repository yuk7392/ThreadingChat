import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class gui extends JFrame {

	private JPanel contentPane;
	private JTextField ip;
	private JTextField port;
	JTextPane log;
	private int count;
	private JLabel lblNewLabel;
	private JButton exitButton;
	private JLabel userText;

	public gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    log = new JTextPane();
	    log.setEditable(false);
		log.setBounds(12, 10, 187, 297);
		
		
		JLabel ipLabel = new JLabel("IP\r\n");
		ipLabel.setFont(new Font("굴림", Font.BOLD, 15));
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ipLabel.setBounds(211, 10, 78, 15);
		contentPane.add(ipLabel);
		
		ip = new JTextField();
		ip.setHorizontalAlignment(SwingConstants.CENTER);
		ip.setEditable(false);
		ip.setBounds(211, 35, 78, 21);
		contentPane.add(ip);
		ip.setColumns(10);
		
		JLabel portLabel = new JLabel("PORT\r\n");
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		portLabel.setFont(new Font("굴림", Font.BOLD, 15));
		portLabel.setBounds(211, 66, 78, 15);
		contentPane.add(portLabel);
		
		port = new JTextField();
		port.setHorizontalAlignment(SwingConstants.CENTER);
		port.setEditable(false);
		port.setColumns(10);
		port.setBounds(211, 91, 78, 21);
		contentPane.add(port);
		
		JScrollPane scrollPane = new JScrollPane(log);
		scrollPane.setBounds(12, 10, 187, 297);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		contentPane.add(scrollPane);
		
		lblNewLabel = new JLabel("CURRENT");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(211, 141, 78, 21);
		contentPane.add(lblNewLabel);
		
		exitButton = new JButton("종료");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("굴림", Font.BOLD, 20));
		exitButton.setBounds(211, 248, 78, 59);
		contentPane.add(exitButton);
		
		userText = new JLabel("0");
		userText.setFont(new Font("굴림", Font.BOLD, 25));
		userText.setHorizontalAlignment(SwingConstants.CENTER);
		userText.setBounds(211, 172, 78, 66);
		contentPane.add(userText);
	}
	
	public void updateLog(String a) {
		
		log.setText(log.getText()+ a+"\n");
		
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
	
	
}



