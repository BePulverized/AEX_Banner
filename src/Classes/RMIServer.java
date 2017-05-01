package Classes;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

/**
 * Created by BePulverized on 10-4-2017.
 */
public class RMIServer {
    private static final int portNumber = 1099;
    private static final String bindingName = "AEXBanner";
    private Registry registry = null;
    private IEffectenbeurs effectenBeurs;

    public RMIServer() {
        System.out.println("Server: Port number 1099");
        try {
            this.effectenBeurs = new Effectenbeurs();
            System.out.println("Server: effectenbeurs created");
        } catch (RemoteException var4) {
            System.out.println("Server: Cannot create effectenbeurs");
            System.out.println("Server: RemoteException: " + var4.getMessage());
            this.effectenBeurs = null;
        }

        System.out.println("Server: Effectenbeurs created");

        try {
            this.registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number 1099");
        } catch (RemoteException var3) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + var3.getMessage());
            this.registry = null;
        }

        try {
            this.registry.rebind(bindingName, this.effectenBeurs);
        } catch (RemoteException var2) {
            System.out.println("Server: Cannot bind banner");
            System.out.println("Server: RemoteException: " + var2.getMessage());
        }

    }

    private static void printIPAddresses() {
        try {
            InetAddress ex = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + ex.getHostAddress());
            InetAddress[] intf = InetAddress.getAllByName(ex.getCanonicalHostName());
            if(intf != null && intf.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                InetAddress[] enumIpAddr = intf;
                int var3 = intf.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    InetAddress allMyIp = enumIpAddr[var4];
                    System.out.println("    " + allMyIp);
                }
            }
        } catch (UnknownHostException var7) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + var7.getMessage());
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            Enumeration var8 = NetworkInterface.getNetworkInterfaces();

            while(var8.hasMoreElements()) {
                NetworkInterface var9 = (NetworkInterface)var8.nextElement();
                System.out.println("    " + var9.getName() + " " + var9.getDisplayName());
                Enumeration var10 = var9.getInetAddresses();

                while(var10.hasMoreElements()) {
                    System.out.println("        " + ((InetAddress)var10.nextElement()).toString());
                }
            }
        } catch (SocketException var6) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + var6.getMessage());
        }

    }

    public static void main(String[] args) {
        System.out.println("SERVER USING REGISTRY");
        printIPAddresses();
        new RMIServer();
    }
}
