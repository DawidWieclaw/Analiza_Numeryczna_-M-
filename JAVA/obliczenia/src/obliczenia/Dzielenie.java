package obliczenia;

/**
 * <p>Klasa reprezentująca dzielenie dwóch liczb</p>
 */
public class Dzielenie extends Operator2Arg
{
    public Dzielenie(Wyrażenie a, Wyrażenie b) {super(a,b);}

    @Override
    public double oblicz()throws Exception
    {
        if(arg2.oblicz()==0) throw new Exception("dzielenie przez zero");
        try {return arg1.oblicz()/arg2.oblicz();}
        catch(Exception e) {throw e;}
    }

    public String toString()
    {
        return "(" + arg1.toString() + "/" + arg2.toString() + ")";
    }
}
