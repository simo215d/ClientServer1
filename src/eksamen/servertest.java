package eksamen;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servertest {
    public static void main(String[] args) {
        try {
            //med netcat kan du forbinde således:
            //nc localhost 8000
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server socket started");
            Socket socket;
            socket = serverSocket.accept();
            System.out.println("Client connected: "+socket.getInetAddress().getHostName());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Hey Client, im server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
