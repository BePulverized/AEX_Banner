package Classes;

import Classes.RMI.IRemotePublisher;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by BePulverized on 2-4-2017.
 */
public interface IEffectenbeurs extends IRemotePublisher{

    List<IFonds> getKoersen() throws RemoteException;
}
