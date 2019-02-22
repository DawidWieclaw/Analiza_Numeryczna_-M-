package narzędzia;
/**
 * <p>Klasa reprezentująca potęgowanie</p>
 */
public class Pow extends Funkcja
{
    public Pow(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.pow(arg2,arg1);
    }

    @Override
    public int arność() { return 2; }
}
