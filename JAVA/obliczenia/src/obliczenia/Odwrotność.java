package obliczenia;

/**
 * <p>Klasa reprezentująca odwrotność danej liczby</p>
 */
public class Odwrotność extends Operator1Arg
{
    public Odwrotność(Wyrażenie arg)
    {
        super(arg);
    }

    @Override
    public double oblicz() throws Exception
    {
        if(arg1.oblicz()==0) throw new Exception("dzielenie przez zero");
        try {return 1/arg1.oblicz();}
        catch(Exception e ) { throw e;}
    }

    public String toString()
    {
        return "(1/" + arg1.toString() + ")";
    }
}
