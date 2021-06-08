import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.awt.event.ActionEvent;

public class Client_start extends JFrame {

	private JPanel contentPane;
	private JTextField textbox_nickname;
	private JTextField textbox_ip;
	private JTextField textbox_port;
	private static String SERVER_IP;
    private static int SERVER_PORT;

	public static void main(String[] args) {
		
		Client_start frame = new Client_start();
		frame.setVisible(true);
	}

	public Client_start() {
	
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 527);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_connect = new JLabel("접속하기");
		label_connect.setFont(new Font("굴림", Font.BOLD, 30));
		label_connect.setHorizontalAlignment(SwingConstants.CENTER);
		label_connect.setBounds(12, 10, 410, 47);
		contentPane.add(label_connect);
		
		JLabel label_nickname = new JLabel("닉네임");
		label_nickname.setFont(new Font("굴림", Font.BOLD, 20));
		label_nickname.setHorizontalAlignment(SwingConstants.CENTER);
		label_nickname.setBounds(84, 174, 133, 21);
		contentPane.add(label_nickname);
		

		
		textbox_nickname = new JTextField();
		textbox_nickname.setBounds(200, 174, 116, 21);
		contentPane.add(textbox_nickname);
		textbox_nickname.setColumns(10);
		
		textbox_ip = new JTextField();
		textbox_ip.setColumns(10);
		textbox_ip.setBounds(200, 205, 116, 21);
		contentPane.add(textbox_ip);
		
		JLabel label_ip = new JLabel("IP\r\n");
		label_ip.setHorizontalAlignment(SwingConstants.CENTER);
		label_ip.setFont(new Font("굴림", Font.BOLD, 20));
		label_ip.setBounds(84, 205, 133, 21);
		contentPane.add(label_ip);
		
		textbox_port = new JTextField();
		textbox_port.setColumns(10);
		textbox_port.setBounds(200, 236, 116, 21);
		contentPane.add(textbox_port);
		
		JLabel label_port = new JLabel("포트");
		label_port.setHorizontalAlignment(SwingConstants.CENTER);
		label_port.setFont(new Font("굴림", Font.BOLD, 20));
		label_port.setBounds(84, 236, 133, 21);
		contentPane.add(label_port);
		
		JButton button_connect = new JButton("연결하기");
		button_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
		if(textbox_nickname.getText().isEmpty() || textbox_ip.getText().isEmpty() || textbox_port.getText().isEmpty()) 
		JOptionPane.showMessageDialog(null, "모든 항목을 작성해야 합니다.");
		else {
			
			
            int n=0;
            while(n<1){
                String[] bList = new String[] {"바보", "멍청이"};
                String name = textbox_nickname.getText();
                for(int i=0; i<bList.length; i++) {
                    if(name.contains(bList[i])) {
                        JOptionPane.showMessageDialog(null, "부적절한 문자가 포함되어 있습니다.");
                         return;
                    }
                    else
                        n=1;
                }
            }
			
			SERVER_IP = textbox_ip.getText();
			SERVER_PORT = Integer.parseInt(textbox_port.getText());
			
			 Socket socket = new Socket();
		        try {
		            socket.connect( new InetSocketAddress(SERVER_IP, SERVER_PORT));
		            JOptionPane.showMessageDialog(null, "연결이 완료되었습니다.");
		            dispose();
		            Client_main cm = new Client_main(textbox_nickname.getText(), socket);
		            cm.setVisible(true);

		            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
		            String request = "join:" + textbox_nickname.getText() + "\r\n";
		            pw.println(request);
		        }
		        catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

		}
			
			
			
		});
		button_connect.setFont(new Font("굴림", Font.BOLD, 20));
		button_connect.setBounds(113, 390, 207, 31);
		contentPane.add(button_connect);
		

		
	}
}
