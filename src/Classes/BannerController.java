package Classes;

import org.w3c.dom.events.Event;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by BePulverized on 2-4-2017.
 */
public class BannerController extends UnicastRemoteObject implements IBanner{

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private List<IFonds> koersen;
    private RMIClient client;

    public BannerController(AEXBanner banner) throws RemoteException{
        super();
        this.banner = banner;
        client = new RMIClient("localhost", 1099, this);

        // Start polling timer: update banner every two seconds
//        pollingTimer = new Timer();
//        pollingTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                // TODO
//                try {
//                    koersen = client.getEffectenbeurs().getKoersen();
//                    setAllKoersen(koersen);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, 0, 1000);

    }

    public void stop() throws RemoteException {
        effectenbeurs.unsubscribeRemoteListener(this, "fondsen");
    }

    public void setEffectenbeurs(IEffectenbeurs beurs)
    {
        this.effectenbeurs = beurs;
    }
    @Override
    public void setAllKoersen(List<IFonds> koersen)
    {
        if(koersen != null) {
            this.koersen = koersen;
            String koersenAll = " ";
            for (IFonds fonds : koersen) {
                koersenAll = koersenAll + fonds.getNaam() + ": " + fonds.getKoers() + ", ";
            }
            banner.setKoersen(koersenAll);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent pchange) throws RemoteException{
        setAllKoersen((ArrayList<IFonds>) pchange.getNewValue());
    }

    public void subscribeRemoteListener() throws RemoteException{
        effectenbeurs.subscribeRemoteListener(this, "fondsen");
    }



}
