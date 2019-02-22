package narzędzia;
/**
 * <p>Klasa reprezentująca mnożenie</p>
 */
public class Mnożenie extends Funkcja
{
    public Mnożenie(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return arg1*arg2;
    }

    @Override
    public int arność() { return 2; }
}
