import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ServerThread extends Thread{
    // 모든 클라이언트의 스레드의 리스트를 갖고 있을 ArrayList 객체를 생성
    // 한 클라이언트가 메시지를 보내오면 모든 클라이언트 스레드에게 똑같은 메시지를 전달
    // Collections.synchronizedList 를 통해 자동 동기화 되는 리스트를 만들 수 있다
    static List<ObjectOutputStream> list    =    Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
    Namelist namelist = new Namelist();
    ArrayList<Socket> socketlist = new ArrayList<Socket>();
    Socket socket;
    Message msg = new Message();
    IntroMessage imsg = new IntroMessage();
    OutroMessage omsg = new OutroMessage();
    Object name;
    Object str;
    
    // 클라이언트에게 메시지를 보낼 writer 객체 선언
    ObjectOutputStream writer;
    ServerThread(Socket socket){
        this.socket             =    socket;
        
        socketlist.add(socket);
        
        try{
            // writer 객체를 생성하고, ArrayList 에 추가
            writer                =    new ObjectOutputStream(socket.getOutputStream());
            list.add(writer);
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace());}
    }
   
    public void run(){
    	SimpleDateFormat getTime = new SimpleDateFormat("aa HH:mm");
        Date timeSet = new Date(); 
        String time = getTime.format(timeSet); 
        // 클라이언트의 이름
        try{
           ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
           name = reader.readObject();
            
            imsg.setMessage("["+time+"] "+((IntroMessage)name).toString()+" 님이 들어오셨습니다.");
            sendAll(imsg);
            namelist.addName(((IntroMessage)name).toString());
            sendAll(namelist);
            
            while(true){
               
                // 클라이언트에서 입력이 있을 때 까지 기다린다
                str=reader.readObject();
                
                if(str instanceof Message) {
               
                msg.setMessage("["+time+"] "+((IntroMessage)name).toString()+" : "+((Message)str).toString());
                sendAll(msg);
               
            } else break;
            }
           
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace());}
        finally{ // 채팅을 종료할 경우
       
            // 더이상 메시지를 보낼 필요가 없으므로 ArrayList 에 남아있던 writer 객체를 지운다
            list.remove(writer);
            omsg.setMessage("["+time+"] "+((IntroMessage)name).toString()+" 님이 나가셨습니다.");
            sendAll(omsg);
            removeNameFromList(((IntroMessage)name).toString());
            sendAll(namelist);
            try{
                socket.close();
            } catch(Exception e){}
        }
    }
   
    private void sendAll(Object obj){
    	  for(ObjectOutputStream writer : list){   
              try {
				writer.writeObject(obj);
				writer.flush();
				writer.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
          }
    }
   
    private void removeNameFromList(String s) {
    	
    	namelist.removeName(s);
    
    }	
}