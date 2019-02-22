package obliczenia;

/**
 * <p>Klasa przetrzmująca liczbę</p>
 */
public class Liczba extends Wyrażenie
{
    /**wartość przetrzymuwanej liczby*/
    double wartosc;

    /**
     * @param wartosc nadawana wartość
     */
    public Liczba(double wartosc) { this.wartosc=wartosc; }

    public double oblicz() { return wartosc; }

    public String toString() { return Double.toString(wartosc); }
}
