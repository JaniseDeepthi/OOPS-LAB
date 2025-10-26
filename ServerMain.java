package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

// Main class to start the RMI server
public class ServerMain {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            RMIServerImpl serverObj = new RMIServerImpl();
            Naming.rebind("rmi://localhost:1099/Server", serverObj);
            System.out.println("RMI Server started and ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
