
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
import java.awt.SystemColor;

public class ChatServer {
    public static final int PORT = 8888;
    static gui GUI;
    static JLabel userNum;
    static int count = 0;
    
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        List<PrintWriter> listWriters = new ArrayList();
        openGUI();
  
        try {
            // 1. 서버 소켓 생성
            serverSocket = new ServerSocket();
            // 2. 바인딩
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind( new InetSocketAddress(hostAddress, PORT) );
            consoleLog("연결 기다림 - " + hostAddress + ":" + PORT);
            GUI.updateNet(hostAddress, PORT);

            // 3. 요청 대기
            while(true) {
                Socket socket = serverSocket.accept();
                new ChatServerProcessThread(socket, listWriters, GUI).start();
               
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( serverSocket != null && !serverSocket.isClosed() ) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consoleLog(String log) {
        GUI.updateLog("[server " + Thread.currentThread().getId() + "] " + log);
    }
    
    public static void openGUI() {
    	
    	GUI = new gui();
    	GUI.getContentPane().setBackground(SystemColor.activeCaption);
    	GUI.setVisible(true);
    	
    }
}
