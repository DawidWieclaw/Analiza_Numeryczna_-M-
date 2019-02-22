package narzędzia;
/**
 * <p>Klasa reprezentująca powałę</p>
 */
public class Ceil extends Funkcja
{
    public Ceil(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.ceil(arg2);
    }

    @Override
    public int arność() { return 1; }
}
