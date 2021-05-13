import java.net.*;
import java.io.*;


public class ClientSender extends Thread{
    Socket socket;
    String name;
    Client_Interface ci;
    ObjectOutputStream writer;
    Message msg;
    IntroMessage imsg;
    
    ClientSender(Socket socket, String name,Client_Interface ci){
        this.socket                    =    socket;
        this.name                     =    name;
        this.ci = ci;
    }
 
    public void run(){
        try{
            // 서버로 메시지를 보내는 writer 객체
            writer        =    new ObjectOutputStream(socket.getOutputStream());
            // 최초 입력된 이름을 서버로 보냄
            imsg = new IntroMessage();
            imsg.setMessage(name);
            writer.writeObject(imsg);
            writer.flush();
           
            // 메시지의 입력을 기다리는 루프
            while(true){
               if(ci.buttonPressed) {
            	msg = new Message();
                msg.setMessage(ci.messagePane.getText());
                writer.writeObject(msg);
                writer.flush();
                ci.messagePane.setText("");
                ci.buttonPressed = false;
               } else { System.out.println(); }
            }
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace());}
        finally{
            try{
                socket.close();
            } catch(Exception e){}
        }
    }
}