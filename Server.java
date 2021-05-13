import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args){
        // 서버 소켓 선언
        ServerSocket serverSocket    =    null;
        try{
           
            // 9000 포트를 열어서 서버 소켓을 생성
            serverSocket            =    new ServerSocket(8888);
           
            // 계속해서 루프를 돌면서 클라이언트에서 요청이올 때마다 소켓을 생성
            while(true){
                Socket    socket        =    serverSocket.accept();
               
                // 각 소켓마다 새로운 스레드가 할당되어 개별로 처리된다
                Thread    thread        =    new ServerThread(socket);               
                thread.start();
            }
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace());}
    }
}