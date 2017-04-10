package Classes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by BePulverized on 10-4-2017.
 */
public interface IBanner extends Remote{
    void setKoersen(ArrayList<IFonds> fondsen) throws RemoteException;
}
