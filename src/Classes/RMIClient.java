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
    private static final String bindingName = "AEXBanner";
    private Registry registry = null;
    private IEffectenbeurs effectenBeurs;
    private BannerController controller;


    public RMIClient(String ipAddress, int portNumber, BannerController controller) {
        this.controller = controller;
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        try {
            this.registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException var6) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + var6.getMessage());
            this.registry = null;
        }

        if (this.registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        if (registry != null) {
            try {
                effectenBeurs = (IEffectenbeurs) registry.lookup(bindingName);
                controller.setEffectenbeurs(effectenBeurs);
                controller.subscribeRemoteListener();
            } catch (RemoteException var4) {
                System.out.println("Client: Cannot bind beurs");
                System.out.println("Client: RemoteException: " + var4.getMessage());
                effectenBeurs = null;
            } catch (NotBoundException var5) {
                System.out.println("Client: Cannot bind beurs");
                System.out.println("Client: NotBoundException: " + var5.getMessage());
                effectenBeurs = null;
            }
        }


        if (this.effectenBeurs != null) {
            System.out.println("Client: Beurs bound");
        } else {
            System.out.println("Client: Beurs is null pointer");
        }
    }

    public IEffectenbeurs getEffectenbeurs() {
        return effectenBeurs;
    }


}



