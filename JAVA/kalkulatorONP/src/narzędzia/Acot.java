package narzędzia;
/**
 * <p>Klasa reprezentująca funkcję arctg</p>
 */
public class Acot extends Funkcja
{
    public Acot(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.atan(Math.toRadians(arg2));
    }

    @Override
    public int arność() { return 1; }
}
