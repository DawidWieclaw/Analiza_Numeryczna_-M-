package narzędzia;
/**
 * <p>Klasa reprezentująca funkcję zwracającą liczbę e</p>
 */
public class E extends Funkcja
{
    public E(Stos stos) {super(stos);}

    @Override
    public double oblicz() throws Exception
    {
        return Math.E;
    }

    @Override
    public int arność() { return 0; }
}
