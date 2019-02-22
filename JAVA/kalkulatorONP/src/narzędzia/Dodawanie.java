package narzędzia;
/**
 * <p>Klasa reprezentująca dodwanie</p>
 */
public class Dodawanie extends Funkcja
{

    public Dodawanie(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        while(brakująceArgumenty()>0)
        {
            if(stos.empty())throw new Exception("zle");
            dodajArgument(stos.get());
        }
        return arg1+arg2;
    }

    @Override
    public int arność() { return 2; }
}
