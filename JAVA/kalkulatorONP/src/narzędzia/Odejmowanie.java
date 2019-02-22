package narzędzia;
/**
 * <p>Klasa reprezentująca odejmwoanie</p>
 */
public class Odejmowanie extends Funkcja
{
    public Odejmowanie(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return arg2-arg1;
    }

    @Override
    public int arność() { return 2; }
}
