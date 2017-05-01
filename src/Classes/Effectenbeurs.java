package Classes;

import Classes.RMI.IRemotePropertyListener;
import Classes.RMI.Publisher;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by BePulverized on 2-4-2017.
 */
public class Effectenbeurs extends UnicastRemoteObject implements IEffectenbeurs, Serializable {

    private ArrayList<IFonds> fondsList;
    private Timer timer;
    private Random rnd;
    private Publisher pb = null;

    public Effectenbeurs() throws RemoteException {
        super();
        rnd = new Random();
        fondsList = new ArrayList<>();
        fondsList.add(new Fonds("ABN AMRO", rnd.nextInt(100)));
        fondsList.add(new Fonds("ASML", rnd.nextInt(100)));
        fondsList.add(new Fonds("DSM", rnd.nextInt(100)));

        //Publisher
        String[] props = new String[1];
        props[0] = "fondsen";
        pb = new Publisher(props);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Refresh();
            }
        }, 0, 10000);
    }

    public void Refresh(){
        for(IFonds f : fondsList)
        {
            double modifier = (rnd.nextInt(4)-2 )+ rnd.nextDouble();
            Fonds fonds = (Fonds)f;
            fonds.setKoers(modifier);
        }

        pb.inform(this, "fondsen", null, fondsList);
    }

    @Override
    public List<IFonds> getKoersen() {
        return fondsList;
    }

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException{
        pb.addListener(listener, property);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        pb.removeListener(listener, property);
    }
}
