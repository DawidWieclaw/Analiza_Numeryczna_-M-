package narzędzia;
/**
 * <p>Klasa reprezentująca funckję exp(x)</p>
 */
public class Exp extends Funkcja
{
    public Exp(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.exp(arg2);
    }

    @Override
    public int arność() { return 1; }
}
