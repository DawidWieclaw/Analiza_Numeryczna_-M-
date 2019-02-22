package obliczenia;

/**
 * <p>Klasa reprezetnujaca wszystkie funkcje jedno argumentowe</p>
 */
public abstract class Operator1Arg extends Wyrażenie
{
    /**argument funkcji*/
    Wyrażenie arg1;

    /**
     *
     * @param arg argument funkcji
     */
    public Operator1Arg(Wyrażenie arg)
    {
        this.arg1=arg;
    }
}
