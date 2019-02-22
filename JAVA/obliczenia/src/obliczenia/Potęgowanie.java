package obliczenia;

/**
 * <p>Klasa reprezentująca potęgowanie dwóch liczb</p>
 */
public class Potęgowanie extends Operator2Arg
{
    public Potęgowanie(Wyrażenie a, Wyrażenie b) {super(a,b);}

    @Override
    public double oblicz() throws Exception
    {
        try {return Math.pow(arg1.oblicz(), arg2.oblicz());}
        catch(Exception e) {throw e;}
    }
    public String toString() { return "(" + arg1.toString() + "^" + arg2.toString() + ")"; }
}
