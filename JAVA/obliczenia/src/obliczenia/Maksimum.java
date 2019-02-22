package obliczenia;

/**
 * <p>Klasa reprezentująca funkcję max(a,b)</p>
 */
public class Maksimum extends Operator2Arg
{
    public Maksimum(Wyrażenie a, Wyrażenie b){super(a,b);}

    @Override
    public double oblicz() throws Exception
    {
        try {return Math.max(arg1.oblicz(), arg2.oblicz());}
        catch(Exception e) { throw e;}
    }

    public String toString()
    {
        return "maksimum(" + arg1.toString() + "," + arg2.toString() + ")";
    }
}
