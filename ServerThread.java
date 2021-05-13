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
    // ��� Ŭ���̾�Ʈ�� �������� ����Ʈ�� ���� ���� ArrayList ��ü�� ����
    // �� Ŭ���̾�Ʈ�� �޽����� �������� ��� Ŭ���̾�Ʈ �����忡�� �Ȱ��� �޽����� ����
    // Collections.synchronizedList �� ���� �ڵ� ����ȭ �Ǵ� ����Ʈ�� ���� �� �ִ�
    static List<ObjectOutputStream> list    =    Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
    Namelist namelist = new Namelist();
    ArrayList<Socket> socketlist = new ArrayList<Socket>();
    Socket socket;
    Message msg = new Message();
    IntroMessage imsg = new IntroMessage();
    OutroMessage omsg = new OutroMessage();
    Object name;
    Object str;
    
    // Ŭ���̾�Ʈ���� �޽����� ���� writer ��ü ����
    ObjectOutputStream writer;
    ServerThread(Socket socket){
        this.socket             =    socket;
        
        socketlist.add(socket);
        
        try{
            // writer ��ü�� �����ϰ�, ArrayList �� �߰�
            writer                =    new ObjectOutputStream(socket.getOutputStream());
            list.add(writer);
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace());}
    }
   
    public void run(){
    	SimpleDateFormat getTime = new SimpleDateFormat("aa HH:mm");
        Date timeSet = new Date(); 
        String time = getTime.format(timeSet); 
        // Ŭ���̾�Ʈ�� �̸�
        try{
           ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
           name = reader.readObject();
            
            imsg.setMessage("["+time+"] "+((IntroMessage)name).toString()+" ���� �����̽��ϴ�.");
            sendAll(imsg);
            namelist.addName(((IntroMessage)name).toString());
            sendAll(namelist);
            
            while(true){
               
                // Ŭ���̾�Ʈ���� �Է��� ���� �� ���� ��ٸ���
                str=reader.readObject();
                
                if(str instanceof Message) {
               
                msg.setMessage("["+time+"] "+((IntroMessage)name).toString()+" : "+((Message)str).toString());
                sendAll(msg);
               
            } else break;
            }
           
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace());}
        finally{ // ä���� ������ ���
       
            // ���̻� �޽����� ���� �ʿ䰡 �����Ƿ� ArrayList �� �����ִ� writer ��ü�� �����
            list.remove(writer);
            omsg.setMessage("["+time+"] "+((IntroMessage)name).toString()+" ���� �����̽��ϴ�.");
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