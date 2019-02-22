package obliczenia;

/**
 * <p>Klasa bazowa do wszystkich wyrażeń</p>
 */
public abstract class Wyrażenie implements Obliczalny
{
    /** metoda sumująca wyrażenia */
    public static double suma (Wyrażenie...wyr) throws Exception
    {
        double wynik=0;
        for(int i=0; i<wyr.length; i++)
        {
            try{wynik+=wyr[i].oblicz();}
            catch(Exception e) { throw e;}
        }
        return wynik;
    }
    /** metoda mnożąca wyrażenia */
    public static double iloczyn (Wyrażenie...wyr) throws Exception
    {
        double wynik=1;
        for(int i=0; i<wyr.length; i++)
        {
            try {wynik*=wyr[i].oblicz();}
            catch (Exception e) {throw e;}
        }
        return wynik;
    }

    public boolean equals(Object o)
    {
        return this.toString().equals(o.toString());
    }
}