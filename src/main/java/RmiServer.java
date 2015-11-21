import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

public class RmiServer extends UnicastRemoteObject implements RmiIntf {
    public RmiServer() throws RemoteException {
        super(0);
    }
    
    public String getMessage() {
	System.out.println("Server: getMessage()");
        return "Hello world";
    }
    
    public static void main(String args[]) throws Exception {
        System.out.println("RMI Server started");
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists");
        }
        
        RmiServer server = new RmiServer();
        
        Naming.rebind("//localhost/RmiServer", server);
        System.out.println("peerServer Bound in registry");
    }
    
}
