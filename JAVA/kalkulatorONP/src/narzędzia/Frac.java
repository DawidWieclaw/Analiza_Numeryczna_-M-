package narzędzia;
/**
 * <p>Klasa reprezentująca część ułamkową</p>
 */
public class Frac extends Funkcja
{
    public Frac(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return arg2-Math.floor(arg2);
    }

    @Override
    public int arność() { return 1; }
}
