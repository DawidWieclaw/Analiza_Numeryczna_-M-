package narzędzia;

import java.util.Scanner;
/**
 * <p>Klasa reprezentująca wyrażenie w notacji onp</p>
 */
public class Wyrażenie
{
    private Kolejka kolejka;
    public Stos stos;
    private Zbior zmienne;
    public int a;

    /**
     *
     * @param onp wyrażenie w onp
     */
    public Wyrażenie(String onp, Zbior zbior)throws WyjątekONP//kolejka przygotowana
    {
        stos=new Stos();
        kolejka=new Kolejka();
        zmienne=zbior;
        String pattern = "\\p{Alpha}\\p{Alnum}*";
        String [] czesci=onp.split(" ");
        for(int i=0; i<czesci.length; i++)
        {
            if(czesci[i].equals("+")) kolejka.dodaj(new Dodawanie(stos));//jakos stos przekazac czy cos
            else if(czesci[i].equals("abs")) kolejka.dodaj(new Abs(stos));
            else if(czesci[i].equals("*")) kolejka.dodaj(new Mnożenie(stos));
            else if(czesci[i].equals("-")) kolejka.dodaj(new Odejmowanie(stos));
            else if(czesci[i].equals("/")) kolejka.dodaj(new Dzielenie(stos));
            else if(czesci[i].equals("min")) kolejka.dodaj(new Min(stos));
            else if(czesci[i].equals("max")) kolejka.dodaj(new Max(stos));
            else if(czesci[i].equals("log")) kolejka.dodaj(new Log(stos));
            else if(czesci[i].equals("pow")) kolejka.dodaj(new Pow(stos));
            else if(czesci[i].equals("sgn")) kolejka.dodaj(new Sgn(stos));
            else if(czesci[i].equals("floor")) kolejka.dodaj(new Floor(stos));
            else if(czesci[i].equals("ceil")) kolejka.dodaj(new Ceil(stos));
            else if(czesci[i].equals("frac")) kolejka.dodaj(new Frac(stos));
            else if(czesci[i].equals("sin")) kolejka.dodaj(new Sin(stos));
            else if(czesci[i].equals("cos")) kolejka.dodaj(new Cos(stos));
            else if(czesci[i].equals("atan")) kolejka.dodaj(new Atan(stos));
            else if(czesci[i].equals("acot")) kolejka.dodaj(new Acot(stos));
            else if(czesci[i].equals("ln")) kolejka.dodaj(new Ln(stos));
            else if(czesci[i].equals("exp")) kolejka.dodaj(new Exp(stos));
            else if(czesci[i].equals("e")) kolejka.dodaj(new E(stos));
            else if(czesci[i].equals("pi")) kolejka.dodaj(new Pi(stos));
            else if(czesci[i].equals("phi")) kolejka.dodaj(new Phi(stos));
            else if(zmienne.isin(czesci[i]))
            {
                kolejka.dodaj(new Liczba(zmienne.get(czesci[i])));
            }
            else
            {
                try {kolejka.dodaj(new Liczba(Double.parseDouble(czesci[i])));}
                catch(Exception e) {throw new ONP_NieznanySymbol();}
            }

        }
    }

    public void licz()
    {
        while(!kolejka.empty())
        {
            Symbol s=kolejka.getFirst();
            try { stos.add(s.oblicz()); }
            catch(Exception e) {System.out.println(e.getMessage());}
        }
        System.out.println(stos.get());
    }
}
