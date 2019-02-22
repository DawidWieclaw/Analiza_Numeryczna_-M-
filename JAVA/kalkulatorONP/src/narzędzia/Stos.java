package narzędzia;

import java.util.ArrayDeque;
/**
 * <p>Klasa reprezentująca stos</p>
 */
public class Stos
{
    public ArrayDeque<Double> stos=new ArrayDeque<>();

    /**
     *
     * @return czy jest pusty
     */
    public boolean empty() {return stos.isEmpty();}

    /**
     *
     * @return zwraca i usuwa wartosc na czubku
     */
    public double get()
    {
        double d=stos.getFirst();//albo last
        stos.removeFirst();
        return d;
    }

    /**
     *
     * @param d dodwana wartosc na stos
     */
    public void add(double d) { stos.add(d); }
}
