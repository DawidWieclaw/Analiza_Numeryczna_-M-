package narzędzia;

import java.util.TreeMap;
/**
 * <p>Klasa reprezentująca zbiór</p>
 */
public class Zbior
{
    TreeMap<String, Double> zbior=new TreeMap<>();
    String pattern = "\\p{Alpha}\\p{Alnum}*";
        /**
         *
         * @param s nazwa
         * @param d wartosc
         */
    public void add(String s, double d) throws WyjątekONP
    {
        if(!s.matches(pattern)) throw new ONP_NieznanySymbol();
        zbior.put(s,d);
    }
    public double get(String s)
    {
        return zbior.get(s);
    }
    public void clear()
    {
        zbior.clear();
    }
    public boolean isin(String s)
    {
        return zbior.containsKey(s);
    }
}
