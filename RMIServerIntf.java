package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface shared by server and client
public interface RMIServerIntf extends Remote {
    String processRequest(String msg) throws RemoteException;
}
