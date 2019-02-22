package narzędzia;
/**
 * <p>Klasa reprezentująca funkcje zwracającą liczbę pi</p>
 */
public class Pi extends Funkcja
{
    public Pi(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception { return Math.PI; }

    @Override
    public int arność() { return 0; }
}
