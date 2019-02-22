package obliczenia;

/**
 * <p>Klasa reprezentująca dodawania dwoch liczb</p>
 */
public class Dodawanie extends Operator2Arg
{
    public Dodawanie(Wyrażenie arg1, Wyrażenie arg2) { super(arg1, arg2); }

    @Override
    public double oblicz() throws Exception
    {
        try{return arg1.oblicz() + arg2.oblicz();}
        catch(Exception e) {throw e;}
    }

    public String toString() { return "(" + arg1.toString() + "+" + arg2.toString() + ")"; }
}
