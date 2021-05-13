import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args){
        // ���� ���� ����
        ServerSocket serverSocket    =    null;
        try{
           
            // 9000 ��Ʈ�� ��� ���� ������ ����
            serverSocket            =    new ServerSocket(8888);
           
            // ����ؼ� ������ ���鼭 Ŭ���̾�Ʈ���� ��û�̿� ������ ������ ����
            while(true){
                Socket    socket        =    serverSocket.accept();
               
                // �� ���ϸ��� ���ο� �����尡 �Ҵ�Ǿ� ������ ó���ȴ�
                Thread    thread        =    new ServerThread(socket);               
                thread.start();
            }
        } catch(Exception e){ System.out.println(e.getMessage()); System.out.println(e.getStackTrace());}
    }
}