package narzędzia;
/**
 * <p>Klasa reprezentująca funkcję max</p>
 */
public class Max extends Funkcja
{
    public Max(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.max(arg1,arg2);
    }

    @Override
    public int arność() { return 2; }
}
