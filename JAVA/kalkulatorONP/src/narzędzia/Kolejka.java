package narzędzia;

import java.util.ArrayDeque;
/**
 * <p>Klasa reprezentująca kolejke</p>
 */
public class Kolejka
{
    ArrayDeque<Symbol> kolejka;
    public Kolejka()
    {
        kolejka=new ArrayDeque<>();
    }
    public void dodaj(Symbol s) { kolejka.add(s); }
    public int size() { return kolejka.size(); }

    /**
     *
     * @return zwracany pierwszy element kolejki
     */
    public Symbol getFirst()
    {
        Symbol s=kolejka.getFirst();
        kolejka.removeFirst();
        return s;
    }

    /**
     *
     * @return czy kolejka pusta
     */
    public boolean empty() {return kolejka.isEmpty();}

}
