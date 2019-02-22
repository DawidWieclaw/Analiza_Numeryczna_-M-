package narzędzia;
/**
 * <p>Klasa reprezentująca fnkcję logarytm</p>
 */
public class Ln extends Funkcja
{
    public Ln(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.log(arg2);
    }

    @Override
    public int arność() { return 1; }
}
