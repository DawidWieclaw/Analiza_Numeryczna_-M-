package obliczenia;

/**
 * <p>Interjefs bazowy dla wszystkich wyrażeń</p>
 */
public interface Obliczalny
{
    /**
     * @return Wartość obliczona przez wyrażenie
     */
    public abstract double oblicz() throws Exception;

    /**
     *
     * @return zwrocenie reprezentacji wyrażenia w postaci napisu
     */
    public abstract String toString();

    /**
     *
     * @param o porownywany obiekt
     * @return czy są równe
     */
    public abstract boolean equals(Object o);
}
