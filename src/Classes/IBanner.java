package Classes;

import Classes.RMI.IRemotePropertyListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BePulverized on 10-4-2017.
 */
public interface IBanner extends Remote, IRemotePropertyListener{
    void setAllKoersen(List<IFonds> fondsen) throws RemoteException;
}
