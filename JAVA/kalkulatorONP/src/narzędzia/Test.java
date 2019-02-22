package narzędzia;

import java.util.Scanner;
/**
 * <p>Klasa do testowania</p>
 */
public class Test
{
    public static void main(String [] args)//przekazuje porzadny zbior a nie liste
    {
        Zbior zbior=new Zbior();
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
        while(odczyt.hasNextLine())
        {
            String s=odczyt.nextLine();
            String[] k = s.split(" ");
            if(k[2].equals("="))
            {
                for(int i=1; i<k.length; i+=2)
                {
                    try {zbior.add(k[i], Double.parseDouble(k[0])); System.out.println(k[i]);}
                    catch(WyjątekONP e) {System.out.println(e.getMessage());}
                }
                continue;
            }
            if(s.equals("exit")) System.exit(0);
            if(s.equals("clear")) zbior.clear();
            try {
                Wyrażenie w = new Wyrażenie(s, zbior);
                w.licz();
            }
            catch(WyjątekONP e) {System.out.println(e.getMessage());}
        }
    }
}
