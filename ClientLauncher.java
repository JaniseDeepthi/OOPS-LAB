package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import server.RMIServerIntf; // ✅ works because interface is in server package

public class ClientLauncher {

    public static void main(String[] args) {
        System.out.println("Janise Deepthi YP - 2117240070124");
        System.out.println("=== Multi-threaded RMI Server Test Cases ===\n");

        final String host = "localhost";
        final int port = 1099;
        final String url = "rmi://" + host + ":" + port + "/Server";

        // ---- TC1: Start server and connect client ----
        System.out.println("TC1: Start server and connect client → Expected: Success");
        try {
            RMIServerIntf stub = (RMIServerIntf) Naming.lookup(url);
            System.out.println("Connected to server successfully.\n");
        } catch (Exception e) {
            System.err.println("Failed to connect: " + e.getMessage() + "\n");
        }

        // ---- TC2: Client sends valid request ----
        System.out.println("TC2: Client sends request → Expected: Server responds");
        try {
            RMIServerIntf stub = (RMIServerIntf) Naming.lookup(url);
            String response = stub.processRequest("Request-from-client-1");
            System.out.println("Response: " + response + "\n");
        } catch (Exception e) {
            System.err.println("Error sending request: " + e.getMessage() + "\n");
        }

        // ---- TC3: Multiple clients handled via threads ----
        System.out.println("TC3: Multiple clients → Expected: Handled via threads");
        try {
            Thread t1 = new Thread(() -> sendRequest("Client-A", url));
            Thread t2 = new Thread(() -> sendRequest("Client-B", url));
            Thread t3 = new Thread(() -> sendRequest("Client-C", url));

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            System.out.println("Multiple clients processed concurrently.\n");
        } catch (Exception e) {
            System.err.println("Thread handling error: " + e.getMessage() + "\n");
        }

        // ---- TC4: Server not running (simulate by wrong port) ----
        System.out.println("TC4: Server not running → Expected: Lookup fails");
        try {
            String invalidUrl = "rmi://" + host + ":9999/Server";
            RMIServerIntf stub = (RMIServerIntf) Naming.lookup(invalidUrl);
            stub.processRequest("Test");
        } catch (Exception e) {
            System.err.println("Lookup failed (as expected): " + e.getMessage() + "\n");
        }

        // ---- TC5: Invalid method call ----
        System.out.println("TC5: Invalid method call → Expected: RemoteException");
        try {
            RMIServerIntf stub = (RMIServerIntf) Naming.lookup(url);
            stub.processRequest("INVALID");
        } catch (RemoteException e) {
            System.err.println("RemoteException caught (as expected): " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage() + "\n");
        }

        System.out.println("=== All Test Cases Executed ===");
    }

    private static void sendRequest(String clientName, String url) {
        try {
            RMIServerIntf stub = (RMIServerIntf) Naming.lookup(url);
            String response = stub.processRequest("Request-from-" + clientName);
            System.out.println(clientName + " → " + response);
        } catch (Exception e) {
            System.err.println(clientName + " failed: " + e.getMessage());
        }
    }
}
