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
        timer = new Timer();
        rnd = new Random();
        fondsList = new ArrayList<>();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fondsList.add(new Fonds("ABN AMRO", rnd.nextInt(100)));
                fondsList.add(new Fonds("ASML", rnd.nextInt(100)));
                fondsList.add(new Fonds("DSM", rnd.nextInt(100)));
            }
        },0, 10000);




    }
    @Override
    public List<IFonds> getKoersen() {
        return fondsList;
    }
}
