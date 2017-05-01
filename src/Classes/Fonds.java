package Classes;

import java.io.Serializable;

/**
 * Created by BePulverized on 2-4-2017.
 */
public class Fonds implements IFonds, Serializable{

    private String naam;
    private double koers;

    public Fonds(String naam, double koers)
    {
        this.naam = naam;
        this.koers = koers;
    }
    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {
        return koers;
    }

    public void setKoers(double koers){
        this.koers = koers;
    }


}
