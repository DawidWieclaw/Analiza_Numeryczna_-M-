package obliczenia;
import struktury.*;

/**
 * <p>Klasa reprezentująca zmienną</p>
 */
public class Zmienna extends Wyrażenie
{
    static final ZbiorNaTablicyDynamicznej zmienne = new ZbiorNaTablicyDynamicznej();
    String nazwa;

    /**
     *
     * @param nazwa nazwa danej stałej
     */
    public Zmienna(String nazwa)
    {
        this.nazwa=nazwa;
        try
        {
            zmienne.ustaw(new Para(nazwa, 2));
        }
        catch(Exception e) {}
    }

    public double oblicz() throws Exception
    {
        try{return zmienne.czytaj(nazwa);}
        catch(Exception e) { throw e; }
    }

    public String toString()
    {
        return nazwa;
    }

}
