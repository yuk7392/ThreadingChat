import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client_Interface extends JFrame {

	private JPanel contentPane;
	JTextPane chatPane;
	JTextPane messagePane;
	JTextPane userPane;
	boolean buttonPressed = false;
	
	public Client_Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		chatPane = new JTextPane();
		chatPane.setEditable(false);
		chatPane.setBounds(12, 10, 385, 473);
		
		messagePane = new JTextPane();
		messagePane.setBounds(12, 493, 385, 64);
		
		userPane = new JTextPane();
		userPane.setEditable(false);
		userPane.setBounds(409, 10, 92, 236);
		
		JButton btnNewButton = new JButton("\uC804\uC1A1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				buttonPressed = true;
				
			}
		});
		btnNewButton.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton.setBounds(409, 493, 97, 64);
		contentPane.add(btnNewButton);
		
		JScrollPane chatScroll = new JScrollPane(chatPane);
		chatScroll.setBounds(12, 10, 385, 473);
		contentPane.add(chatScroll);
		
		JScrollPane userScroll = new JScrollPane(userPane);
		userScroll.setBounds(409, 10, 92, 236);
		contentPane.add(userScroll);
		
		JScrollPane messsageScroll = new JScrollPane(messagePane);
		messsageScroll.setBounds(12, 493, 385, 64);
		contentPane.add(messsageScroll);
		
		JButton btnNewButton_1 = new JButton("\uAE30\uB2A51");
		btnNewButton_1.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_1.setBounds(409, 256, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("\uAE30\uB2A51");
		btnNewButton_1_1.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(409, 289, 97, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("\uAE30\uB2A51");
		btnNewButton_1_2.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_1_2.setBounds(409, 322, 97, 23);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("\uAE30\uB2A51");
		btnNewButton_1_3.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_1_3.setBounds(409, 355, 97, 23);
		contentPane.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_4 = new JButton("\uAE30\uB2A51");
		btnNewButton_1_4.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_1_4.setBounds(409, 388, 97, 23);
		contentPane.add(btnNewButton_1_4);
		
		JButton btnNewButton_1_5 = new JButton("\uAE30\uB2A51");
		btnNewButton_1_5.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_1_5.setBounds(409, 421, 97, 23);
		contentPane.add(btnNewButton_1_5);
		
		JButton btnNewButton_1_6 = new JButton("\uAE30\uB2A51");
		btnNewButton_1_6.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnNewButton_1_6.setBounds(409, 454, 97, 23);
		contentPane.add(btnNewButton_1_6);
		
		
	}
}
