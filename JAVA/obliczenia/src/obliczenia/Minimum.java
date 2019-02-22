package obliczenia;

/**
 * <p>Klasa reprezentująca funkcję min(a,b)</p>
 */
public class Minimum extends Operator2Arg
{
    public Minimum(Wyrażenie a, Wyrażenie b) {super(a,b);}

    @Override
    public double oblicz() throws Exception
    {
        try {return Math.min(arg1.oblicz(), arg2.oblicz());}
        catch(Exception e) { throw e;}
    }

    public String toString()
    {
        return "min(" + arg1.toString() + "," + arg2.toString() + ")";
    }
}
