package narzędzia;
/**
 * <p>Klasa reprezentująca dzielenie</p>
 */
public class Dzielenie extends Funkcja
{
    public Dzielenie(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws WyjątekONP
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty()) throw new ONP_PustyStos();
            dodajArgument(stos.get());
        }
        return arg2/arg1;
    }

    @Override
    public int arność() { return 2; }
}
