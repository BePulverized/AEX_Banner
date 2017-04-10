package Classes;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by BePulverized on 10-4-2017.
 */
public class RMIClient {
    private static final String bindingName = "AexBanner";
    private Registry registry = null;
    private IBanner banner;


    public RMIClient(String ipAddress, int portNumber) {

        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        try {
            this.registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException var6) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + var6.getMessage());
            this.registry = null;
        }

        if(this.registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        if(registry != null) {
            try {
                banner = (IBanner) registry.lookup(bindingName);
            } catch (RemoteException var4) {
                System.out.println("Client: Cannot bind beurs");
                System.out.println("Client: RemoteException: " + var4.getMessage());
                banner = null;
            } catch (NotBoundException var5) {
                System.out.println("Client: Cannot bind beurs");
                System.out.println("Client: NotBoundException: " + var5.getMessage());
                banner = null;
            }
        }




        if(this.banner != null) {
            System.out.println("Client: Beurs bound");
        } else {
            System.out.println("Client: Beurs is null pointer");
        }



    }


}
