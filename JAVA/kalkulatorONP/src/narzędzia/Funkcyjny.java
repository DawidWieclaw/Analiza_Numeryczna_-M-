package narzędzia;
/**
 * <p>Interfejs reprezentujacya funkcje</p>
 */
public interface Funkcyjny extends Obliczalny
{
    /**
     *
     * @return zwraca ile argumentow wymaga funkcja
     */
    int arność ();

    /**
     *
     * @return zwraca ile argumentow brakuje
     */
    int brakująceArgumenty ();

    /**
     *
     * @param x dodawany argument
     * @throws Exception gdy jest za malo argumentow
     */
    void dodajArgument (double x) throws Exception;
}
