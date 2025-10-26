package socket;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Janise Deepthi YP");
            System.out.println("2117240070124");
            System.out.println("Connected to server successfully!");
            
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
