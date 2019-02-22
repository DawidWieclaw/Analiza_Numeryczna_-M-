package narzędzia;
/**
 * <p>Klasa reprezentująca funkcję sinus</p>
 */
public class Cos extends Funkcja
{
    public Cos(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.cos(Math.toRadians(arg2));
    }

    @Override
    public int arność() { return 1; }

}
