package narzędzia;
/**
 * <p>Klasa reprezentująca funckję min</p>
 */
public class Min extends Funkcja
{
    public Min(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.min(arg1,arg2);
    }

    @Override
    public int arność() { return 2; }
}
