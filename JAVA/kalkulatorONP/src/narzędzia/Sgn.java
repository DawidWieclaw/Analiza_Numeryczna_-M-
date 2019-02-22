package narzędzia;
/**
 * <p>Klasa reprezentująca funkcję signum</p>
 */
public class Sgn extends Funkcja
{
    public Sgn(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        if(arg2>0) return 1;
        else if(arg2==0) return 0;
        else return -1;
    }

    @Override
    public int arność() { return 1; }
}
