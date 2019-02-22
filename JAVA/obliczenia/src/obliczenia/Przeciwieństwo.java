package obliczenia;

/**
 * <p>Klasa reprezentująca funkcję f(x)=-x</p>
 */
public class Przeciwieństwo extends Operator1Arg
{
    public Przeciwieństwo(Wyrażenie arg1)
    {
        super(arg1);
    }

    @Override
    public double oblicz() throws Exception
    {
        try {return -(arg1.oblicz());}
        catch(Exception e) {throw e;}
    }
    public String toString()
    {
        return "(-" + arg1.toString() + ")";
    }
}
