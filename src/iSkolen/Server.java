package iSkolen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Client Accepted");
                new Thread(new HandleAClient(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class HandleAClient implements Runnable{
        private Socket socket;

        public HandleAClient(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    String messageReceived = in.readUTF();
                    System.out.println(messageReceived);
                    out.writeUTF("Hey Client, im server, you wrote: "+messageReceived);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}