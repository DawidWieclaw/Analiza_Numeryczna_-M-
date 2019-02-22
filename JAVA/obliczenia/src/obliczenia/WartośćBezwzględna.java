package obliczenia;

/**
 * <p>Klasa reprezentująca funkcję ||</p>
 */

public class WartośćBezwzględna extends Operator1Arg
{
    public WartośćBezwzględna(Wyrażenie arg1)
    {
        super(arg1);
    }

    @Override
    public double oblicz() throws Exception
    {
        try {return Math.abs(arg1.oblicz());}
        catch (Exception e) {throw e;}
    }
    public String toString()
    {
        return "|" + arg1.toString() + "|";
    }
}
