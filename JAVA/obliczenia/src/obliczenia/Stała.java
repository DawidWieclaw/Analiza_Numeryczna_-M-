package obliczenia;

import struktury.Para;
import struktury.ZbiorNaTablicy;

/**
 * <p>Klasa reprezentująca stałe</p>
 */
public class Stała extends Wyrażenie
{
    public static ZbiorNaTablicy zbior;
    public String nazwa;

    /**
     *
     * @param nazwa nazwa stałej
     */
    public Stała (String nazwa)
    {
        this.nazwa=nazwa;
        try
        {
            zbior = new ZbiorNaTablicy(3);
            zbior.wstaw(new Para("pi", Math.PI));
            zbior.wstaw(new Para("e", Math.E));
            zbior.wstaw(new Para("phi", (1 + Math.sqrt(5.0)) / 2));
        }
        catch(Exception e){}
    }

    @Override
    public double oblicz() throws Exception
    {
        try {return zbior.czytaj(nazwa);}
        catch(Exception e) {throw e;}
    }

    @Override
    public String toString() {
        return nazwa;
    }
}
