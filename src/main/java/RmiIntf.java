import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiIntf extends Remote {
    public String getMessage() throws RemoteException;
}
