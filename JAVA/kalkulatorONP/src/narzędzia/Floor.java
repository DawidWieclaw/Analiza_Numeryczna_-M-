package narzędzia;
/**
 * <p>Klasa reprezentująca podłogę</p>
 */
public class Floor extends Funkcja
{
    public Floor(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.floor(arg2);
    }

    @Override
    public int arność() { return 1; }
}
