package Classes;

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
        rnd = new Random();
        fondsList = new ArrayList<>();
        fondsList.add(new Fonds("ABN AMRO", 22.775));
        fondsList.add(new Fonds("ASML", 124.000));
        fondsList.add(new Fonds("DSM", 63.500));

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fonds = (Fonds) fondsList.get(rnd.nextInt());
            }
        }, 0, 10000);
    }
    @Override
    public List<IFonds> getKoersen() {
        return fondsList;
    }
}
