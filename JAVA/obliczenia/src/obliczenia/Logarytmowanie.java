package obliczenia;

/**
 * <p>Klasa reprezentująca logarytm o podstawie jednego argumentu i liczbie logarytmowanej drugiejgo</p>
 */
public class Logarytmowanie extends Operator2Arg
{
    public Logarytmowanie(Wyrażenie a, Wyrażenie b) {super(a,b);}

    @Override
    public double oblicz() throws Exception
    {
        try {return Math.log(arg2.oblicz())/Math.log(arg1.oblicz());}
        catch(Exception e) {throw e;}
    }

    public String toString()
    {
        return "log(" + arg1.toString() + "," + arg2.toString() + ")";
    }
}
