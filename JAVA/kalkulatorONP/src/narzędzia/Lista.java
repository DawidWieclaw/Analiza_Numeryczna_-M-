package narzędzia;

import java.util.LinkedList;
/**
 * <p>Klasa reprezentująca listę</p>
 */
public class Lista
{
    LinkedList<String> list;
    public Lista()
    {
        list=new LinkedList<>();
    }

    /**
     *
     * @param s dodwanie do listy
     */
    public void add(String s)
    {
        list.add(s);
    }
    public void get() { list.getFirst(); }
}

