package obliczenia;

/**
 * <p>Klasa reprezentująca funkcję sinus</p>
 */
public class Sinus extends Operator1Arg
{
    public Sinus(Wyrażenie arg){super(arg);}

    @Override
    public double oblicz() throws Exception
    {
        try{return Math.sin(arg1.oblicz());}
        catch(Exception e) {throw e;}
    }
    public String toString()
    {
        return "sin(" + arg1.toString() + ")";
    }
}
