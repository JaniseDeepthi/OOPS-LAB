package socket;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Server started. Waiting for client...");

            Socket socket = server.accept();
            System.out.println("Client connected successfully!");

            socket.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
