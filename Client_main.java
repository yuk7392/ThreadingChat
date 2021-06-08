import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Client_main extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JTextArea textArea_message;
	private JButton button_send;
	private Socket socket;
	SimpleDateFormat getTime = new SimpleDateFormat("aa HH:mm");
    Date timeSet = new Date(); 
    String time = getTime.format(timeSet);
    JLabel jl;
  
    
	public Client_main(String name,Socket socket) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				PrintWriter pw;
                try {
                    pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                    String request = "quit\r\n";
                    pw.println(request);
      
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            
				
			}
		});
		this.socket = socket;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 541);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(12, 10, 333, 422);
		
		textArea_message = new JTextArea();
		textArea_message.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 char keyCode = e.getKeyChar();
	              if (keyCode == KeyEvent.VK_ENTER) {
	                 sendMessage();
	                }
				
			}
		});
		textArea_message.setBounds(12, 448, 333, 54);
		
		button_send = new JButton("전송");
		button_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textArea_message.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "내용을 입력하신 후 전송 해주세요.");
				else
				sendMessage();
			}
		});
		button_send.setFont(new Font("굴림", Font.BOLD, 18));
		button_send.setBounds(357, 448, 75, 54);
		contentPane.add(button_send);
		
		JScrollPane textArea_message_scrollPane = new JScrollPane(textArea_message);
		textArea_message_scrollPane.setBounds(12, 448, 333, 54);
		textArea_message_scrollPane.getVerticalScrollBar().setValue(textArea_message_scrollPane.getVerticalScrollBar().getMaximum());
		contentPane.add(textArea_message_scrollPane);
		
		JScrollPane textArea_scrollPane = new JScrollPane(textArea);
		textArea_scrollPane.setBounds(12, 10, 333, 422);
		textArea_scrollPane.getVerticalScrollBar().setValue(textArea_scrollPane.getVerticalScrollBar().getMaximum());
		contentPane.add(textArea_scrollPane);
		
		JButton exitButton = new JButton("종료");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
				
			}
		});
		exitButton.setFont(new Font("굴림", Font.BOLD, 18));
		exitButton.setBounds(357, 378, 75, 54);
		contentPane.add(exitButton);
		
		JButton mainButton = new JButton("메인");
		mainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			    dispose();
				Client_start cs = new Client_start();
				cs.setVisible(true);
				
				
				
			}
		});
		mainButton.setFont(new Font("굴림", Font.BOLD, 18));
		mainButton.setBounds(357, 88, 75, 68);
		contentPane.add(mainButton);
		
		JButton musicButton = new JButton("음악");
		musicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AudioInterface intc = new AudioInterface();
				intc.setVisible(true);
				
			}
		});
		musicButton.setFont(new Font("굴림", Font.BOLD, 18));
		musicButton.setBounds(357, 166, 75, 68);
		contentPane.add(musicButton);
		
		JButton functionButton3 = new JButton("배경");
		functionButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				  JFileChooser chooser = new JFileChooser();
				    
					
			      FileNameExtensionFilter filter = new FileNameExtensionFilter(
			            "JPG&GIF Images","jpg","gif");
			      chooser.setFileFilter(filter);
			         
			      int ret = chooser.showOpenDialog(null);
			      
			      if(ret != JFileChooser.APPROVE_OPTION){
			         JOptionPane.showMessageDialog(null,"파일을 선택하지 않았습니다", "경고",JOptionPane.WARNING_MESSAGE);
			         return;
			      }
			      
			      String filePath = chooser.getSelectedFile().getPath();
			      jl.setIcon(new ImageIcon(filePath));
				
				
				
				
				
			}
		});

		functionButton3.setFont(new Font("굴림", Font.BOLD, 18));
		functionButton3.setBounds(357, 244, 75, 68);
		contentPane.add(functionButton3);
		
		JLabel functionLabel = new JLabel("기능");
		functionLabel.setFont(new Font("굴림", Font.BOLD, 30));
		functionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		functionLabel.setBounds(357, 16, 75, 62);
		contentPane.add(functionLabel);
		
		jl = new JLabel();
		contentPane.add(jl);
		jl.setBounds(0, 0, 450, 541);
		
		 new ChatClientReceiveThread(socket).start();
		 
			
		
	}
	
	private void sendMessage() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String message = textArea_message.getText();
            String request = "message:" + message + "\r\n";
            pw.println(request);

            textArea_message.setText("");
            textArea_message.requestFocus();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	 private class ChatClientReceiveThread extends Thread{
	        Socket socket = null;

	        ChatClientReceiveThread(Socket socket){
	            this.socket = socket;
	        }

	        public void run() {
	            try {
	                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
	                while(true) {
	                    String msg = br.readLine();
	                    textArea.append("["+time+"] " + msg);
	                    textArea.append("\n");
	                }
	            }
	            catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	






