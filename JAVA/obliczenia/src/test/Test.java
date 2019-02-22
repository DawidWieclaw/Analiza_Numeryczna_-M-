package test;
import obliczenia.*;
public class Test
{
    public static void main(String[] args)
    {
        /*Wyrażenia do przetestowania*/
        Wyrażenie w1 = new Dodawanie(new Liczba(3), new Liczba(5));
        Wyrażenie w2 = new Dodawanie(new Liczba(2), new Mnożenie(new Zmienna("x"), new Liczba(7)));
        Wyrażenie h1 = new Odejmowanie(new Mnożenie(new Liczba(3), new Liczba(11)), new Liczba(1));
        Wyrażenie h2 = new Dodawanie(new Liczba(7), new Liczba(5));
        Wyrażenie w3 = new Dzielenie(h1,h2);
        Wyrażenie h3 = new Mnożenie(new Dodawanie(new Zmienna("x"), new Liczba(13)), new Zmienna("x"));
        Wyrażenie h4 = new Liczba(2);
        Wyrażenie w4 = new Arctan(new Dzielenie(h3,h4));
        Wyrażenie h5 = new Mnożenie(new Zmienna("x"), new Logarytmowanie(new Liczba(2), new Zmienna("y")));
        Wyrażenie w5 = new Dodawanie(new Potęgowanie(new Liczba(2), new Liczba(5)), h5);
        System.out.println(w1.toString());
        try { System.out.println(w1.oblicz()); }
        catch (Exception e) { System.out.println(e.getMessage()); }
        System.out.println(w2.toString());
        try { System.out.println(w2.oblicz()); }
        catch(Exception e) { System.out.println(e.getMessage()); }
        System.out.println(w3.toString());
        try{ System.out.println(w3.oblicz()); }
        catch (Exception e) { System.out.println(e.getMessage()); }
        System.out.println(w4.toString());
        try { System.out.println(w4.oblicz()); }
        catch(Exception e) { System.out.println(e.getMessage()); }
        System.out.println(w5.toString());
        try { System.out.println(w5.oblicz()); }
        catch(Exception e) {System.out.println(e.getMessage()); }

        /*Mój test*/
        Wyrażenie w6 = new Logarytmowanie(new Liczba(-33), new Liczba(2));
        try { System.out.println(w6.oblicz()); }
        catch(Exception e) {System.out.println(e.getMessage()); }

    }
}
