package narzędzia;
/**
 * <p>Klasa reprezentująca liczbę</p>
 */
public class Liczba extends Operand
{
    double wartość;

    /**
     *
     * @param wartość przypisanie wartości do liczby
     */
    public Liczba(double wartość) { this.wartość=wartość; }

    @Override
    public double oblicz() { return wartość; }
}
