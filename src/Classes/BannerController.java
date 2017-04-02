package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by BePulverized on 2-4-2017.
 */
public class BannerController {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private List<IFonds> koersen;

    public BannerController(AEXBanner banner) {

        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO
                setAllKoersen(effectenbeurs.getKoersen());

            }
        }, 0, 2000);

    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO

    }

    public void setAllKoersen(List<IFonds> koersen)
    {
        if(koersen != null) {
            this.koersen = koersen;
            String koersenAll = " ";
            for (IFonds fonds : koersen) {
                koersenAll = koersenAll + fonds.getNaam() + ": ";
            }
            banner.setKoersen(koersenAll);
        }
    }

}
