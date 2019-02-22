package obliczenia;

/**
 * <p>Klasa reprezentująca funkcje 2 argumentowe</p>
 */
public abstract class Operator2Arg extends Operator1Arg
{
    Wyrażenie arg2;

    /**
     *
     * @param arg1 argument 1
     * @param arg2 argument 2
     */
    public Operator2Arg(Wyrażenie arg1, Wyrażenie arg2)
    {
        super(arg1);
        this.arg2=arg2;
    }

}
