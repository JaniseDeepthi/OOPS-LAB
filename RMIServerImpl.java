package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Implementation of the remote interface
public class RMIServerImpl extends UnicastRemoteObject implements RMIServerIntf {

    protected RMIServerImpl() throws RemoteException {
        super();
    }

    @Override
    public String processRequest(String msg) throws RemoteException {
        System.out.println("[Server] Received: " + msg);

        if ("INVALID".equalsIgnoreCase(msg)) {
            throw new RemoteException("Invalid request received");
        }

        return "Server processed: " + msg;
    }
}
