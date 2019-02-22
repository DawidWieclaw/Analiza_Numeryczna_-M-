package narzędzia;
/**
 * <p>Klasa reprezentująca logarytm</p>
 */
public class Log extends Funkcja
{
    public Log(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return Math.log(arg2)/Math.log(arg1);
    }

    @Override
    public int arność() { return 2; }
}
