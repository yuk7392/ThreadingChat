import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class Client_start extends JFrame {

	private JPanel contentPane;
	private JTextField textbox_nickname;
	private JTextField textbox_ip;
	private JTextField textbox_port;
	private static String SERVER_IP;
    private static int SERVER_PORT;
    ArrayList<profile> profileManager = new ArrayList<profile>();
    JComboBox ServerList;
    String [] arrs;

	public static void main(String[] args) {
		
		Client_start frame = new Client_start();
		frame.setVisible(true);
	}

	public Client_start() {
		setTitle("Connect");
		readConfig();
	
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
		label_connect.setBounds(12, 10, 420, 47);
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
		        	JOptionPane.showMessageDialog(null, "연결에 실패하였습니다.\n 입력하신 정보가 올바른지 확인하세요.\n\n 오류내용 : "+e.getMessage());
		            
		        }
		    }

		}
			
			
			
		});
		button_connect.setFont(new Font("굴림", Font.BOLD, 20));
		button_connect.setBounds(113, 390, 207, 31);
		contentPane.add(button_connect);
		
		JButton button_profile = new JButton("프로필 관리");
		button_profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				profileM m = new profileM();
				m.setVisible(true);
			}
		});
		button_profile.setFont(new Font("굴림", Font.BOLD, 20));
		button_profile.setBounds(113, 431, 207, 31);
		contentPane.add(button_profile);
		
		ServerList = new JComboBox(arrs);
		ServerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = ServerList.getSelectedItem().toString();
				
		        for(int i=0;i<profileManager.size();i++)
		        if(str.equals(profileManager.get(i).getName())){
		        textbox_ip.setText(profileManager.get(i).getIp());
		        textbox_port.setText(profileManager.get(i).getPort());
		        }
		        
			}
		});
		ServerList.setBounds(113, 124, 207, 31);
		contentPane.add(ServerList);
		
		JLabel label_connect_1 = new JLabel("저장한 서버목록");
		label_connect_1.setForeground(Color.BLACK);
		label_connect_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_connect_1.setFont(new Font("굴림", Font.BOLD, 20));
		label_connect_1.setBounds(12, 67, 420, 47);
		contentPane.add(label_connect_1);
		

		
	}
	
	
	
	public void readConfig() {
		
		File profileMain = new File("c:/chattemp");
		File profileDir = new File("c:/chattemp/profile");
		File profile = new File("c:/chattemp/profile/profile.txt");
		
		if(!profileMain.exists()) profileMain.mkdir();
		if(!profileDir.exists()) profileDir.mkdir();
		if(!profile.exists())
			try {
				profile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedReader reader;
			
			try {
				reader = new BufferedReader(new FileReader(profile));

				String line;
				int count = -1;	
				String[] arr = new String[3];
				
				while((line = reader.readLine()) != null) {
				if(!line.equals("END OF PROFILE")) {
				count ++;
				arr[count] = line;
				} else {
					
				count = -1;
				
				profile pf = new profile(arr[0],arr[1],arr[2]);
				profileManager.add(pf);
				arr = new String[3];
				
				
				}
			}
			   ArrayList<String> array = new ArrayList<String>();
			  array.add("여기를 클릭하세요...");
			   
			   
			   for(int i=0; i< profileManager.size(); i++)
			   array.add(profileManager.get(i).getName());
				
			  arrs = array.toArray(new String[array.size()]);

				reader.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		}	
}
