package obliczenia;

/**
 * <p>Klasa reprezentujaca funkcję arcus tangens</p>
 */
public class Arctan extends Operator1Arg
{
    public Arctan(Wyrażenie arg) {super(arg);}

    @Override
    public double oblicz() throws Exception
    {
        try {return Math.atan(arg1.oblicz());}
        catch(Exception e) {throw e;}
    }

    public String toString()
    {
        return "arctan("+ arg1.toString() + ")";
    }
}
