package obliczenia;

/**
 * <p>Klasa reprezentująca funkcję cosinus</p>
 */
public class Cosinus extends Operator1Arg
{
    public Cosinus(Wyrażenie arg){super(arg); }

    @Override
    public double oblicz() throws Exception
    {
        try { return Math.cos(arg1.oblicz()); }
        catch(Exception e) { throw e; }
    }

    public String toString()
    {
        return "cos(" + arg1.toString() + ")";
    }
}
