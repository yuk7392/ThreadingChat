import java.io.ObjectInputStream;
import java.net.Socket;
public class ClientReceiver extends Thread{
    Socket socket;
    Client_Interface ci;
    ObjectInputStream iStream;
    Namelist name;
    Message msg;
    IntroMessage imsg;
    OutroMessage omsg;
    Object obj;
    
    ClientReceiver(Socket socket,Client_Interface ci){
        this.socket        =    socket;
        this.ci = ci;
    }
    
    
    public void run(){


    	
        try{
        	iStream = new ObjectInputStream(socket.getInputStream());
        	
        	
        	while(true) {
        	
       
               obj = iStream.readObject();
       
        	
               if(obj instanceof Namelist) {
               setUserPane(((Namelist) obj));
              
               }
               
               if(obj instanceof IntroMessage) {
                   ci.chatPane.setText(ci.chatPane.getText()+((IntroMessage) obj).toString()+"\n");
                   
               	}
     
            if(obj instanceof Message) {
                ci.chatPane.setText(ci.chatPane.getText()+((Message) obj).toString()+"\n");
                
            	}
            
            if(obj instanceof OutroMessage) {
                ci.chatPane.setText(ci.chatPane.getText()+((OutroMessage) obj).toString()+"\n");
                
            	}
            
        	}
        	
        	
        	
        	
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace());}
        
        
     

        	
        	
      
        
        
        
        
        
    }
    
    public void appendUserPane(String s) {
    	
    	ci.userPane.setText(ci.userPane.getText()+s+"\n");
    	
    }
    
    private void setUserPane(Namelist l) {
    	
    	 for(int i=0;i<l.size();i++) 
		 appendUserPane(l.getArrayList().get(i));
    	
    }
    
}