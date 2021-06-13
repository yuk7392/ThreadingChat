import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatServerProcessThread extends Thread{
    private String nickname = null;
    private Socket socket = null;
    List<PrintWriter> listWriters = null;
    SimpleDateFormat getTime = new SimpleDateFormat("aa HH:mm");
    Date timeSet = new Date(); 
    String time = getTime.format(timeSet);
    gui GUI;
    
    public ChatServerProcessThread(Socket socket, List<PrintWriter> listWriters,gui g) {
        this.socket = socket;
        this.listWriters = listWriters;
        this.GUI = g;
    }

    @Override
    public void run() {
        try {
        	
            BufferedReader buffereedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            
            while(true) {

                  
            	
                String request = buffereedReader.readLine();

                if( request == null) {
                    consoleLog("클라이언트로부터 연결 끊김");
                    doQuit(printWriter);
                    break;
                }
                
                if(GUI.informButtonPressed()) {
       	         broadcast("inform:"+GUI.getMessage());  
       	         GUI.disableButton();
       	         GUI.updateLog("공지사항 전송이 완료되었습니다.");
       	         }
       			 
                
                
                String[] tokens = request.split(":");
                if("join".equals(tokens[0])) {
                    doJoin(tokens[1], printWriter);
                }
                else if("message".equals(tokens[0])) {
                    doMessage(tokens[1]);
                }
                else if("quit".equals(tokens[0])) {
                    doQuit(printWriter);
                }
               
         
                
            }
        }
        catch(IOException e) {
        	
        	GUI.minUser();
        	GUI.updateUserText();
        	
            consoleLog(this.nickname + " 님이 채팅방을 나갔습니다.");
            broadcast(this.nickname + " 님이 채팅방을 나갔습니다.");
        }
    }

    private void doQuit(PrintWriter writer) {
    	
    	GUI.minUser();
    	GUI.updateUserText();
    	
        removeWriter(writer);

        String data = this.nickname + " 님이 퇴장했습니다.";
        GUI.updateLog(data);
        broadcast(data);
    }

    private void removeWriter(PrintWriter writer) {
        synchronized (listWriters) {
            listWriters.remove(writer);
        }
    }

    private void doMessage(String data) {
        broadcast(this.nickname + ":" + data);
        GUI.updateChatLog(this.nickname + ":" + data);
    }

    private void doJoin(String nickname, PrintWriter writer) {
    	
    	GUI.addUser();
    	GUI.updateUserText();
    	
        this.nickname = nickname;

        String data = nickname + " 님이 입장하였습니다.";
        GUI.updateLog(data);
        broadcast(data);

        // writer pool에 저장
        addWriter(writer);
    }

    private void addWriter(PrintWriter writer) {
        synchronized (listWriters) {
            listWriters.add(writer);
        }
    }

    public void broadcast(String data) {
        synchronized (listWriters) {
            for(PrintWriter writer : listWriters) {
                writer.println(data);
                writer.flush();
            }
        }
    }

    private void consoleLog(String log) {
        GUI.updateLog("["+time+"] "+ log+"\n");
    }
    
}

