import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Connect_Interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nametbx;
	private JTextField ipaddrtbx;
	private JTextField porttbx;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect_Interface frame = new Connect_Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Connect_Interface() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("POLY TALK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 10, 686, 69);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uB2C9\uB124\uC784");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(12, 119, 311, 69);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("IP \uC8FC\uC18C");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(12, 198, 311, 69);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("\uD3EC \uD2B8");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(12, 277, 311, 69);
		contentPane.add(lblNewLabel_1_2);
		
		JButton confirm = new JButton("\uD655\uC778");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			String name = nametbx.getText();
			String ip = ipaddrtbx.getText();
			String port = porttbx.getText();
			
			Client c = new Client();
			Client_Interface itf = new Client_Interface();
			
			if(c.connect(name, ip, port,itf)) {
				
			JOptionPane.showMessageDialog(null, "연결에 성공하였습니다.");
			dispose();
            itf.setVisible(true);
				
			}else {
				
			JOptionPane.showMessageDialog(null, "연결에 실패하였습니다.");	
				
			}
			
				
			}
		});
		confirm.setFont(new Font("굴림", Font.BOLD, 20));
		confirm.setBounds(235, 425, 239, 23);
		contentPane.add(confirm);
		
		nametbx = new JTextField();
		nametbx.setBounds(337, 144, 116, 21);
		contentPane.add(nametbx);
		nametbx.setColumns(10);
		
		ipaddrtbx = new JTextField();
		ipaddrtbx.setBounds(337, 224, 116, 21);
		contentPane.add(ipaddrtbx);
		ipaddrtbx.setColumns(10);
		
		porttbx = new JTextField();
		porttbx.setBounds(337, 302, 116, 21);
		contentPane.add(porttbx);
		porttbx.setColumns(10);
		

	}
}
