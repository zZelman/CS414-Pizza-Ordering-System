import java.rmi.Naming;

public class RmiClient {
    public static void main(String args[]) throws Exception {
        RmiIntf intf = (RmiIntf) Naming.lookup("//localhost/RmiServer");
        System.out.println(intf.getMessage());
    }
}
