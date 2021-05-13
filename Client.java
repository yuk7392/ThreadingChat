import java.net.Socket;
import java.util.Scanner;


public class Client {
	
	public boolean connect(String name, String ip, String port,Client_Interface ci)
	{
		
		try{
			
            Socket socket        =    new Socket(ip,Integer.parseInt(port));
            Thread thread1        =    new ClientSender(socket, name,ci);
            Thread thread2        =    new ClientReceiver(socket,ci);
           
            thread1.start();
            thread2.start();           
            return true;
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace()); return false;}
		
	}
}
  