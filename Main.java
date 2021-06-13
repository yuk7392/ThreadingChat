import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 468);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Just Chatting");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 50));
		title.setBounds(12, 10, 530, 79);
		contentPane.add(title);
		
		JLabel server_1 = new JLabel("참여자");
		server_1.setHorizontalAlignment(SwingConstants.CENTER);
		server_1.setFont(new Font("나눔고딕", Font.BOLD, 30));
		server_1.setBounds(12, 99, 530, 79);
		contentPane.add(server_1);
		
		JLabel server_2_1 = new JLabel("참여자로 서버에 접속합니다.");
		server_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		server_2_1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		server_2_1.setBounds(12, 175, 530, 38);
		contentPane.add(server_2_1);
		
		JButton button_connect = new JButton("접속하기");
		button_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Client_start cs = new Client_start();
				cs.setVisible(true);
				
			}
		});
		button_connect.setFont(new Font("나눔고딕", Font.BOLD, 20));
		button_connect.setBounds(220, 259, 115, 23);
		contentPane.add(button_connect);
		
		JLabel server_2_1_1 = new JLabel("* 서버를 여실 유저분은, server.exe를 실행하시고");
		server_2_1_1.setForeground(Color.ORANGE);
		server_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		server_2_1_1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		server_2_1_1.setBounds(12, 339, 530, 38);
		contentPane.add(server_2_1_1);
		
		JLabel server_2_1_1_1 = new JLabel("참여자로도 접속해주시기 바랍니다.");
		server_2_1_1_1.setForeground(Color.ORANGE);
		server_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		server_2_1_1_1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		server_2_1_1_1.setBounds(12, 381, 530, 38);
		contentPane.add(server_2_1_1_1);
	}
}
