package obliczenia;

/**
 * <p>Klasa reprezentująca odejmowanie dwóch liczb</p>
 */
public class Odejmowanie extends Operator2Arg
{
    public Odejmowanie(Wyrażenie a, Wyrażenie b) {super(a,b);}

    @Override
    public double oblicz() throws Exception
    {
        try {return arg1.oblicz()-arg2.oblicz();}
        catch(Exception e) {throw e;}
    }

    public String toString()
    {
        return "(" + arg1.toString() + "-" + arg2.toString() + ")";
    }
}
