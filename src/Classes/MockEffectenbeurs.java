package Classes;

import Classes.RMI.IRemotePropertyListener;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by BePulverized on 2-4-2017.
 */
public class MockEffectenbeurs implements IEffectenbeurs {


    private ArrayList<IFonds> fondsList;
    private Timer timer;

    private Random rnd;

    private Fonds fonds;

    public MockEffectenbeurs()
    {
        timer = new Timer();
        rnd = new Random();
        fondsList = new ArrayList<>();





    }
    @Override
    public List<IFonds> getKoersen() {
        return fondsList;
    }

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {

    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {

    }
}
