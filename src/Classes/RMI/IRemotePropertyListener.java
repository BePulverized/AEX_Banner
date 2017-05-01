package Classes.RMI;



import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.EventListener;

/**
 * Created by BePulverized on 30-4-2017.
 */
public interface IRemotePropertyListener extends EventListener, Remote{

    void propertyChange(PropertyChangeEvent evt) throws RemoteException;

}
