package narzędzia;

/**
 * <p>Klasa reprezentująca moduł</p>
 */
public class Abs extends Funkcja
{
    public Abs(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        if(arg2>=0) return arg2;
        else return -arg2;
    }

    @Override
    public int arność() { return 1; }
}
