import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class profileM extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField ipaddr;
	private JTextField port;
	DefaultListModel mdl = new DefaultListModel();
	ArrayList<profile> profileManager = new ArrayList<profile>();
	JList list;

	public profileM() {
		
		setForeground(SystemColor.activeCaption);
		setTitle("Profile Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 499);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList();
		list.setBounds(218, 11, 188, 433);
		
		
		JLabel lblNewLabel = new JLabel("프로필 설정");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel.setBounds(12, 11, 194, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("설정할 프로필 이름");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1.setBounds(12, 55, 194, 34);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(12, 99, 194, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("IP 주소");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(12, 130, 194, 21);
		contentPane.add(lblNewLabel_1_1);
		
		ipaddr = new JTextField();
		ipaddr.setColumns(10);
		ipaddr.setBounds(12, 161, 194, 21);
		contentPane.add(ipaddr);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("포트번호");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(12, 192, 194, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		port = new JTextField();
		port.setColumns(10);
		port.setBounds(12, 225, 194, 21);
		contentPane.add(port);
		
		JButton btnNewButton = new JButton("등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(name.getText().isEmpty() || ipaddr.getText().isEmpty() || port.getText().isEmpty()) 
				JOptionPane.showMessageDialog(null, "프로필 이름과 IP, 포트번호는 반드시 기입해야 합니다.\n모두 기입하였는지 확인해주세요.");
				else {
					
					appendConfig(name.getText(),ipaddr.getText(),port.getText());
					JOptionPane.showMessageDialog(null, "프로필 등록이 완료되었습니다.");
					
					readConfig();
				}

			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton.setBounds(62, 256, 97, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("프로필 수정");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel_2.setBounds(12, 289, 194, 34);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("수정");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(list.getSelectedIndex() == -1) 
				JOptionPane.showMessageDialog(null, "프로필을 선택하신 후 다시 시도하세요.");
				else {
					
					dispose();
					modify md = new modify(profileManager,list.getSelectedIndex());
					md.setVisible(true);
					
					
				}
					
					
					
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_1.setBounds(12, 333, 194, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("삭제");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(list.getSelectedIndex() == -1) 
					JOptionPane.showMessageDialog(null, "프로필을 선택하신 후 다시 시도하세요.");
					else {
						
						
						profileManager.remove(list.getSelectedIndex());
						rewriteConfig(profileManager,list.getSelectedIndex());
						
						
						
					}
				
			}
		});
		btnNewButton_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(12, 366, 194, 23);
		contentPane.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(218, 11, 188, 432);
		contentPane.add(scrollPane);
		
		JButton btnNewButton_1_1_1 = new JButton("상세");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(list.getSelectedIndex() == -1) 
					JOptionPane.showMessageDialog(null, "프로필을 선택하신 후 다시 시도하세요.");
					else 
					JOptionPane.showMessageDialog(null, "상세정보\n프로필 이름 : "+profileManager.get(list.getSelectedIndex()).getName()+"\nIP주소 : "+profileManager.get(list.getSelectedIndex()).getIp()+"\n포트번호 : "+profileManager.get(list.getSelectedIndex()).getPort());
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_1_1_1.setBounds(12, 399, 194, 23);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("이전");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Client_start s = new Client_start();
				s.setVisible(true);
				
			}
		});
		btnNewButton_1_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_1_1_1_1.setBounds(12, 432, 194, 23);
		contentPane.add(btnNewButton_1_1_1_1);
		
		readConfig();
		
	}
	
	
	public void appendConfig(String name,String ip,String port) {
		
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
	    
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(profile,true));  
			
			writer.append(name+"\n");
			writer.append(ip+"\n");
			writer.append(port+"\n");
			writer.append("END OF PROFILE\n");
			writer.close();
		    	
		} catch (IOException e) {
	
			e.printStackTrace();
		}    	
		
	    }

      
	public void readConfig() {
		
		mdl.clear();
		profileManager.clear();
		
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
				reader.close();
				for(int i=0;i<profileManager.size();i++)
				mdl.addElement(profileManager.get(i).getName());
				
				list.setModel(mdl);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		}
	
	
public void rewriteConfig(ArrayList<profile> p,int idx) {
		
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
	    
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(profile,false));  
			
			for(int i=0;i<p.size();i++) {
			writer.append(p.get(i).getName()+"\n");
			writer.append(p.get(i).getIp()+"\n");
			writer.append(p.get(i).getPort()+"\n");
			writer.append("END OF PROFILE\n");
			}	
			writer.close();
			
			readConfig();
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}    	
		
		
		
	    }
}
