import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class modify extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField ipaddr;
	private JTextField port;

	public modify(ArrayList<profile> p,int idx) {
		setTitle("Modify");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 382);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("수정하기");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel.setBounds(12, 10, 355, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("프로필 이름");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1.setBounds(12, 59, 355, 39);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(66, 108, 247, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("IP 주소");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(12, 139, 355, 39);
		contentPane.add(lblNewLabel_1_1);
		
		ipaddr = new JTextField();
		ipaddr.setColumns(10);
		ipaddr.setBounds(66, 188, 247, 21);
		contentPane.add(ipaddr);
		
		JLabel lblNewLabel_1_2 = new JLabel("포트번호");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(12, 219, 355, 39);
		contentPane.add(lblNewLabel_1_2);
		
		port = new JTextField();
		port.setColumns(10);
		port.setBounds(66, 268, 247, 21);
		contentPane.add(port);
		
		name.setText(p.get(idx).getName());
		ipaddr.setText(p.get(idx).getIp());
		port.setText(p.get(idx).getPort());
		
		JButton btnNewButton = new JButton("수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(name.getText().isEmpty() || ipaddr.getText().isEmpty() || port.getText().isEmpty()) 
					
					JOptionPane.showMessageDialog(null, "프로필 이름과 IP, 포트번호는 반드시 기입해야 합니다.\n모두 기입하였는지 확인해주세요.");
				
				else {
					
					int ans = JOptionPane.showConfirmDialog(null, "수정사항은 다음과 같습니다.\n프로필 이름 : "+name.getText()+"\nIP주소 : "+ipaddr.getText()+"\n포트번호 : "+port.getText()+"\n이대로 수정하시겠습니까?","확인 요청중",JOptionPane.YES_NO_OPTION);
					
					if(ans == JOptionPane.CANCEL_OPTION) {
						
						//
						
					}else if(ans == JOptionPane.YES_OPTION) {
						

						p.get(idx).setName(name.getText());
						p.get(idx).setIp(ipaddr.getText());
						p.get(idx).setPort(port.getText());
						
						rewriteConfig(p,idx);
						JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
						dispose();
						profileM M = new profileM();
						M.setVisible(true);
						
						
					}else {
						
						//
						
					}
					
					
					
					
				}
					
				
				
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton.setBounds(141, 310, 97, 23);
		contentPane.add(btnNewButton);
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
		} catch (IOException e) {
	
			e.printStackTrace();
		}    	
		
		
		
	    }

	
	
	
}
