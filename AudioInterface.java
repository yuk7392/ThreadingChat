
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AudioInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	Audio ado;
	private JPanel contentPane;
	 boolean access = true;
	 ArrayList<Integer> color = new ArrayList<Integer>();
	 JLabel statusLabel;
	 JLabel durationLabel;
	 JLabel titleLabel;
	 boolean toggle = false;
	 JLabel volumeLabel;
	 timer timer;
	 boolean isPlaying = false;
	 
	public AudioInterface() {
		setForeground(SystemColor.activeCaptionBorder);
		ado = new Audio();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				if(ado.continueable())
				ado.stop();
			    
				
			}
		});
	

	
       
		setResizable(false);
		setBounds(100, 100, 393, 363);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setForeground(SystemColor.activeCaption);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titleLabel = new JLabel("♪ - "+(ado.continueable() ? ado.getTitle() : "알 수 없음"));
		titleLabel.setBackground(SystemColor.desktop);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("굴림", Font.BOLD, 20));
		titleLabel.setBounds(12, 10, 363, 24);
		contentPane.add(titleLabel);
		
		durationLabel = new JLabel((ado.continueable() ? ado.getCurrentTime().toSeconds()+" / "+ado.getFullTime().toSeconds() : "알 수 없음"));
		durationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		durationLabel.setFont(new Font("굴림", Font.BOLD, 15));
		durationLabel.setBounds(12, 82, 363, 24);
		contentPane.add(durationLabel);
		
		
		statusLabel = new JLabel(ado.continueable() ? ado.getStatus() : "발견된 mp3 없음");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("굴림", Font.BOLD, 20));
		statusLabel.setBounds(12, 44, 363, 24);
		contentPane.add(statusLabel);
		
		JButton playButton = new JButton("▶ / ■");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!ado.continueable())
				JOptionPane.showMessageDialog(null, "실행 할 수 없는 상태입니다.");
				
				else {
				
				if(!toggle) {
		
				ado.play();	
				isPlaying = true;
				titleLabel.setText("♪ - "+(ado.continueable() ? ado.getTitle() : "알 수 없음"));
				durationLabel.setText((ado.continueable() ? (int)Math.floor(ado.getCurrentTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getCurrentTime().toSeconds() % 60)+" / "+(int)Math.floor(ado.getFullTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getFullTime().toSeconds() % 60) : "알 수 없음"));
				statusLabel.setText(ado.continueable() ? "재생 중" : "발견된 mp3 없음");
				volumeLabel.setText("볼륨 : "+(ado.continueable() ? String.format("%.1f", ado.getVolume())+" / 1.0" : "알 수 없음"));
				timer = new timer(durationLabel,volumeLabel,ado,isPlaying);
				timer.start();
				
				
				toggle = true;
				
				
				
				}
				
				
				
				else if(toggle) {
		
					
					ado.pause();
					
					titleLabel.setText("♪ - "+(ado.continueable() ? ado.getTitle() : "알 수 없음"));
					durationLabel.setText((ado.continueable() ? (int)Math.floor(ado.getCurrentTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getCurrentTime().toSeconds() % 60)+" / "+(int)Math.floor(ado.getFullTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getFullTime().toSeconds() % 60) : "알 수 없음"));
					statusLabel.setText(ado.continueable() ? "일시 정지" : "발견된 mp3 없음");
					volumeLabel.setText("볼륨 : "+(ado.continueable() ? String.format("%.1f", ado.getVolume())+" / 1.0" : "알 수 없음"));
					isPlaying = false;
					toggle = false;
					
				}
				}
				
			}
		});
		playButton.setFont(new Font("굴림", Font.BOLD, 20));
		playButton.setBounds(141, 189, 105, 60);
		contentPane.add(playButton);
		
		
		
		
		
		JButton prevButton = new JButton("◀◀");
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ado.continueable())
				JOptionPane.showMessageDialog(null, "실행 할 수 없는 상태입니다.");
				else {
				ado.playPrev();
				
				titleLabel.setText("♪ - "+(ado.continueable() ? ado.getTitle() : "알 수 없음"));
				durationLabel.setText((ado.continueable() ? (int)Math.floor(ado.getCurrentTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getCurrentTime().toSeconds() % 60)+" / "+(int)Math.floor(ado.getFullTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getFullTime().toSeconds() % 60) : "알 수 없음"));
				statusLabel.setText(ado.continueable() ? "재생 중" : "발견된 mp3 없음");
				volumeLabel.setText("볼륨 : "+(ado.continueable() ? String.format("%.1f", ado.getVolume())+" / 1.0" : "알 수 없음"));
				}
				
			}
		});
		prevButton.setFont(new Font("굴림", Font.BOLD, 20));
		prevButton.setBounds(12, 189, 77, 60);
		contentPane.add(prevButton);
		
		JButton nextButton = new JButton("▶▶");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!ado.continueable())
					JOptionPane.showMessageDialog(null, "실행 할 수 없는 상태입니다.");
					else {
					ado.playNext();
				
				
				titleLabel.setText("♪ - "+(ado.continueable() ? ado.getTitle() : "알 수 없음"));
				durationLabel.setText((ado.continueable() ? (int)Math.floor(ado.getCurrentTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getCurrentTime().toSeconds() % 60)+" / "+(int)Math.floor(ado.getFullTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getFullTime().toSeconds() % 60) : "알 수 없음"));
				statusLabel.setText(ado.continueable() ? "재생 중" : "발견된 mp3 없음");
				volumeLabel.setText("볼륨 : "+(ado.continueable() ? String.format("%.1f", ado.getVolume())+" / 1.0" : "알 수 없음"));
					}
				
			}
		});
		nextButton.setFont(new Font("굴림", Font.BOLD, 20));
		nextButton.setBounds(298, 189, 77, 60);
		contentPane.add(nextButton);
		
		volumeLabel = new JLabel("볼륨 : "+(ado.continueable() ? String.format("%.1f", ado.getVolume())+" / 1.0" : "알 수 없음"));
		volumeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		volumeLabel.setFont(new Font("굴림", Font.BOLD, 20));
		volumeLabel.setBounds(12, 116, 363, 24);
		contentPane.add(volumeLabel);
		
		JButton voldownButton = new JButton("-");
		voldownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!ado.continueable())
					JOptionPane.showMessageDialog(null, "실행 할 수 없는 상태입니다.");
					else 
				ado.setVolume(ado.getVolume() - 0.1);
				
			}
		});
		voldownButton.setFont(new Font("굴림", Font.BOLD, 20));
		voldownButton.setBounds(59, 150, 58, 23);
		contentPane.add(voldownButton);
		
		JButton volupButton = new JButton("+");
		volupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!ado.continueable())
					JOptionPane.showMessageDialog(null, "실행 할 수 없는 상태입니다.");
					else 
				ado.setVolume(ado.getVolume() + 0.1);
				
			}
		});
		volupButton.setFont(new Font("굴림", Font.BOLD, 20));
		volupButton.setBounds(276, 150, 58, 23);
		contentPane.add(volupButton);	
		
		JLabel lblNewLabel = new JLabel("여기를 두 번 눌러서 파일 위치 열기");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getClickCount() == 2)
					try {
						Runtime.getRuntime().exec("c:/windows/explorer.exe "+ado.getAudioPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 291, 363, 33);
		contentPane.add(lblNewLabel);
		
	}
}

class timer extends Thread{
	
	private JLabel label;
	private Audio ado;
	private boolean toggle;
	private JLabel vol;
	
	public timer(JLabel lbl,JLabel volume,Audio ado,boolean toggle) {
		
		this.label = lbl;
		this.ado = ado;
		this.toggle = toggle;
		vol = volume;
	}
	
	public void run() {
		
		while(toggle) {
		vol.setText("볼륨 : "+(ado.continueable() ? String.format("%.1f", ado.getVolume())+" / 1.0" : "알 수 없음"));
		label.setText((ado.continueable() ? (int)Math.floor(ado.getCurrentTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getCurrentTime().toSeconds() % 60)+" / "+(int)Math.floor(ado.getFullTime().toSeconds() / 60)+":"+(int)Math.floor(ado.getFullTime().toSeconds() % 60) : "알 수 없음"));
		}
}
}
