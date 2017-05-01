package Classes.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by BePulverized on 30-4-2017.
 */
public interface IRemotePublisher extends Remote{

    void subscribeRemoteListener(IRemotePropertyListener listener, String property)
            throws RemoteException;

    void unsubscribeRemoteListener(IRemotePropertyListener listener, String property)
            throws RemoteException;

}
